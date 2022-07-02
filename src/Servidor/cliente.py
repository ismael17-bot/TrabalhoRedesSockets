import json
from socket import socket

from threading import Thread
# import random
# import sys

from conexao import *
from login import *
from sala import gerenciador_salas, partida


# Thread do cliente

class cliente(Thread):
    partida: partida

    def __init__(self,  socket: socket, salas: gerenciador_salas):
        Thread.__init__(self)
        self.socket = socket
        self.conexao = True
        self.g_salas = salas

    def get_id(self):
        return self.id

    def _switch(self, vl=""):
        [key, vl] = vl.split(':')

        switch = {
            'entrar_sala': entrar_sala,
            'sair_sala': sair_sala,
            'criar_sala': criar_sala,
            'lista_sala': lista_sala,
            'add_palavra': a,
            'excluir_palavra': a,
            'lista_palavras': a,
            'lista_jogadores': lista_jogadores,
            'add_': a,
            'sair': sair,
        }
        case = switch.get(key, default)
        return case(vl, self)

    def run(self):
        socket = self.socket
        texto = pegar(socket)

        login = Login(texto)
        self.login = login
        self.id = login.id
        self.get_dados()

        try:
            enviar(socket, self.id)
        except:
            self.conexao = False

        while self.conexao:
            try:
                10
                # enviar(" '-' ")
            except:
                self.conexao = False
                continue

            texto = pegar(socket)
            resposta = self._switch(texto)

            enviar(socket, resposta)
            # sys.stdout.write(f"Text: {texto} \n")
        self.c = False
        # sys.stdout.write(f"{self.socket}")
        # sys.stdout.flush()
        # self.socket.send(b'teste')
        # self.socket
        # self.socket.close()

    def sair(self):
        self.conexao = False
        return 'ok'

    def get_dados(self):
        d = select(f'SELECT * FROM clientes WHERE id={self.id}')
        d = d.iloc[0]
        self.name = d['nome']
        self.xp = d['xp']
        self.lv = d['lv']
        self.pontos = d['pontos']

    def resposta(self):
        if(self.conexao):
            return 10

    def __str__(self) -> str:
        # a:1;b:3,b:2,c:4 /// , ; :
        return f"xp:{self.xp};lv:{self.lv};p:{self.pontos};n:{self.name};id:{self.id}"


def criar_sala(vl: string, cliente: cliente):
    info = json.load(vl)
    cliente.g_salas.criar_sala(cliente, info)

    return 'ok'


def entrar_sala(vl: string, cliente: cliente):
    info = json.load(vl)

    cliente.g_salas.get_sala(info['id'])
    return cliente.partida.entrar(cliente)


def sair_sala(vl: string, cliente: cliente):
    return cliente.partida.sair()


def lista_sala(vl: string, cliente: cliente):
    return cliente.g_salas.lista_sala_string()


def lista_jogadores(vl: string, cliente: cliente):
    return cliente.partida.lista_jogadores()


def sair(vl: string, cliente: cliente):
    return cliente.sair()


def a(vl, cliente):
    return 10


def default(vl, cliente):
    return 'erro'
