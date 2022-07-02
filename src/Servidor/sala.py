import json
from pydoc import cli

import secrets
# from threading import Thread

from cliente import cliente


class partida():
    def __init__(self, id, nome, senha, criador: cliente):
        # Thread.__init__(self)
        self.cliente = {criador.get_id(): criador}
        self.criador = criador
        self.id = id
        self.name = nome
        self.senha = senha
        self.letras = []
        self.vez = criador.get_id()

    # def run():
    #     while(True):
    #         10
    #     return

    def lista_jogadores(self):
        string = ''
        for v in self.cliente.values():
            string += f"{v},"
        return string[0, -1]

    def entrar(self, cliente: cliente):
        try:
            self.cliente[cliente.get_id()] = cliente
            cliente.partida = self
        except:
            return False
        return True

    def sair(self, cliente):
        try:
            self.cliente.pop(cliente.get_id())
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
        _partida = partida(id, info['nome'], info['sehna'], cliente)
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
