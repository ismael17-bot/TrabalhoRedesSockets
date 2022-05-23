import json
from socket import socket

from threading import Thread
# import random
import sys

from conexao import *
from login import *
from sala import gerenciador_salas

gerenciador_sala = gerenciador_salas()


# Thread do cliente

class cliente(Thread):
    def __init__(self,  socket: socket, *c):
        Thread.__init__(self)
        self.socket = socket
        self.conexao = True
        self.c = c

    def get_id(self):
        return self.id

    def _switch(self, vl=""):
        [key, vl] = vl.split(':')

        switch = {
            'entrar_sala': a,
            'criar_sala': criar_sala,
            'lista_sala': a,
            'add_palavra': a,
            'add_': a,
            'add_': a,
            'add_': a,
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
                enviar(" '-' ")
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


def criar_sala(vl: string, cliente: cliente):
    info = json.load(vl)
    gerenciador_sala.criar_sala(cliente, info)
    return 'sucesso'


def a(vl, cliente):
    return 10


def default(vl, cliente):
    return 'erro'
