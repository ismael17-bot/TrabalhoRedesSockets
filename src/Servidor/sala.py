from asyncio.windows_events import NULL
import json
from pydoc import cli
import random

import secrets
# from threading import Thread

# from cliente import cliente


class partida():
    palavras: list
    palavra: str
    index: int

    def __init__(self, id, nome, senha, criador, palavras: list):
        # Thread.__init__(self)
        self.cliente = {criador.get_id(): criador}
        self.criador = criador
        self.id = id

        self.name = nome
        self.senha = senha
        self.letras = []
        self.acertos = {}
        self.vez = criador.get_id()
        self.ordem = [criador.get_id()]
        self.palavras = palavras
        self.index = 0
        random.shuffle(self.palavras)

        self.palavra = self.palavras[self.index]

    def set_palavra(self):
        self.index = (self.index+1) % len(self.palavras)
        if(self.index == 0):
            random.shuffle(self.palavras)
        self.acertos.clear()
        self.letras.clear()
        self.palavra = self.palavras[self.index]

    def palavra_len(self):
        return len(self.palavra)

    def chutar_letra(self, letra):
        try:
            self.letras.append(letra)
            i = 0
            list = []
            for l in self.palavra:
                if l == letra:
                    list.append(f"{i}")
                i += 1
            if len(list) != 0:
                self.acertos[letra] = list
            p = self.palavra
            for l in self.acertos:
                p.replace(l, '')

            if(len(p) == 0):
                self.set_palavra()
                return -2

            self.calc_pontos(len(list))
            self.vezProxima()
        except:
            return -1
        return ','.join(list)

    def calc_pontos(self, acertos, p=False):
        try:
            cliente = self.cliente[self.vez]
            cliente.pontos += acertos*50
            if p:
                cliente.pontos += 500
            cliente.update_dados()
        except:
            return False

    def chutar_palavra(self, p):
        self.vezProxima()
        if self.palavra == p:
            self.calc_pontos(0, True)
            self.set_palavra()
            return True
        return False

    def lista_jogadores(self):
        string = ''
        for v in self.cliente.values():
            string += f"{v},"
        return string[0: -1]

    def get_acertos(self):
        s = []
        for i in self.acertos:
            e = self.acertos[i]
            s.append(f"{i}:{';'.join(e)}")
        return ','.join(s)

    def entrar(self, cliente):
        try:
            self.cliente[cliente.get_id()] = cliente
            self.ordem.append(cliente.get_id())
            cliente.partida = self
        except:
            return False
        return True

    def vezProxima(self):
        try:
            i = self.ordem.index(self.vez)
            i = (i+1) % len(self.ordem)
            self.vez = self.ordem[i]
        except:
            return False
        return True

    def sair(self, cliente):
        try:
            # print("--------------------------------")
            # print(self.cliente)
            # print("--------------------------------")
            id = cliente.get_id()
            self.cliente.pop(id)

            # print(self.cliente)
            # print(f"--------------{id}------------------")
            if self.vez == id:
                self.vezProxima()

            self.ordem.remove(id)
            cliente.partida = NULL
            if len(self.cliente) == 0:
                cliente.g_salas.deleta_sala(self.id)
        except:
            return False
        return True

    def __str__(self) -> str:
        # a:1;b:3,b:2,c:4 /// , ; :
        return f"q:{len(self.cliente)};p:{self.senha==''};nome:{self.name};id:{self.id}"


class gerenciador_salas():
    salas: dict[int, partida]

    def __init__(self) -> None:
        self.salas = {}

    def get_sala(self, id) -> partida:
        return self.salas[id]

    def criar_sala(self, cliente, info: dict):
        id = self._criar_id()
        _partida = partida(
            id, info['nome'], info['senha'], cliente, info['palavras'])
        self.salas[id] = _partida

        # _partida.start()
        cliente.partida = _partida
        return True

    def deleta_sala(self, id):
        return self.salas.pop(id, False) != False

    def lista_sala(self):
        return self.salas

    def lista_sala_string(self):
        # string = json.dumps(self.salas)
        string = ''
        for v in self.salas.values():
            string += f"{v},"
        return string[0:-1]

    def _criar_id(self) -> int:
        id = int(secrets.token_hex(4), 16)
        if(id in self.salas):
            return self._criar_id()
        return id
