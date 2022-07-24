from asyncio.windows_events import NULL
import json
from socket import socket
import sys

from threading import Thread
from banco import sqlQuery, select, insert
# import random
# import sys

from conexao import *
from login import *
# from sala import partida


# Thread do cliente

class cliente(Thread):
    partida = NULL  # : partida

    def __init__(self,  socket: socket, salas):
        Thread.__init__(self)
        self.socket = socket
        self.conexao = True
        self.g_salas = salas

    def get_id(self):
        return self.id

    def _switch(self, vl=""):
        print(f"{vl} {type(vl)}")
        if(len(vl) < 3 or vl.find(';-;') == -1):
            return ""

        [key, vl] = vl.split(';-;')

        switch = {
            'entrar_sala': entrar_sala,
            'sair_sala': sair_sala,
            'criar_sala': criar_sala,
            'lista_sala': lista_sala,
            'info_sala': info_sala,
            'add_palavra': add_palavra,
            'excluir_palavra': excluir_palavra,
            'lista_palavras': lista_palavras,
            'lista_jogadores': lista_jogadores,
            'chutar_palavra': chutar_palavra,
            'chutar_letra': chutar_letra,
            'vez': vez,
            'palavra_len': palavra_len,
            'letras': letras,
            'acertos': acertos,
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
            print(f"ID: {self.id}")
            enviar(socket, self.id)
        except:
            self.conexao = False

        try:

            while self.conexao:
                # sleep(10000)
                texto = pegar(socket)
                sys.stdout.write(f"Text: {texto} \n")
                resposta = self._switch(texto)

                enviar(socket, resposta)
        except:
            if(self.partida != NULL):
                self.partida.sair(self)
        self.c = False

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

    def update_dados(self):
        sqlQuery(
            f"update clientes set pontos={self.pontos} WHERE id={self.id}")

    def __str__(self) -> str:
        # a:1;b:3,b:2,c:4 /// , ; :
        return f"xp:{self.xp};lv:{self.lv};p:{self.pontos};n:{self.name};id:{self.id}"


def criar_sala(vl: str, cliente: cliente):
    info = json.loads(vl)
    cliente.g_salas.criar_sala(cliente, info)

    return 'ok'


def entrar_sala(vl: str, cliente: cliente):
    info = json.loads(vl)

    partida = cliente.g_salas.get_sala(info['id'])
    return partida.entrar(cliente)


def sair_sala(vl: str, cliente: cliente):
    return cliente.partida.sair(cliente)


def lista_sala(vl: str, cliente: cliente):
    return cliente.g_salas.lista_sala_string()


def lista_jogadores(vl: str, cliente: cliente):
    return cliente.partida.lista_jogadores()


def vez(vl: str, cliente: cliente):
    return cliente.partida.vez


def sair(vl: str, cliente: cliente):
    return cliente.sair()


def a(vl, cliente):
    return 10


def add_palavra(vl: str, cliente: cliente):
    info = json.loads(vl)

    if(insert({'palavra': info['palavra']}, 'custom')):
        return 'ok'
    return 'error'


def excluir_palavra(vl: str, cliente: cliente):
    info = json.loads(vl)
    palavra = info['palavra']
    if(sqlQuery(f'DELETE FROM custom WHERE palavra="{palavra}"')):
        return 'ok'
    return 'error'


def lista_palavras(vl: str, cliente: cliente):
    info = json.loads(vl)

    if(info['p']):
        data = select("SELECT palavra FROM padrao order by 1")
    else:
        data = select("""SELECT DISTINCT(palavra) as palavra from (
            SELECT palavra FROM custom 
            union all 
            SELECT palavra FROM padrao
        ) order by 1""")

    print(data.loc[:, 'palavra'].values)
    return ','.join(data.loc[:, 'palavra'].values)


def chutar_palavra(vl: str, cliente: cliente):
    info = json.loads(vl)

    if(cliente.partida.chutar_palavra(info['palavra'])):
        return 'ok'
    return 'erro'


def chutar_letra(vl: str, cliente: cliente):
    info = json.loads(vl)
    return cliente.partida.chutar_letra(info['letra'])


def palavra_len(vl: str, cliente: cliente):
    return cliente.partida.palavra_len()


def letras(vl: str, cliente: cliente):
    return ','.join(cliente.partida.letras)


def acertos(vl: str, cliente: cliente):
    return cliente.partida.get_acertos()


def info_sala(vl: str, cliente: cliente):
    return 'erro'


def default(vl, cliente):
    return 'erro'
