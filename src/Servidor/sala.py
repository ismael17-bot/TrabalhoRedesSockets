import json
from pydoc import cli
import secrets
from threading import Thread

# from cliente import cliente


class gerenciador_salas():
    def __init__(self) -> None:
        self.salas = {}

    def get_sala(self, id):
        return self.salas[id]

    def criar_sala(self, cliente, info: dict):
        _partida = partida(self._criar_id(), info.nome, info.sehna, cliente)
        _partida.start()
        cliente.partida = _partida
        return True

    def lista_sala(self):
        return self.salas

    def lista_sala_string(self):
        string = json.dumps(self.salas)
        print(string)
        return string

    def _criar_id(self):
        id = secrets.token_hex(64)
        if(id in self.salas):
            id = self._criar_id()
        return id


class partida(Thread):
    def __init__(self, id, nome, senha, criador):
        Thread.__init__(self)
        self.cliente = {criador.get_id(): criador}
        self.criador = criador
        self.id = id
        self.name = nome
        self.senha = senha
        self.letras = []
        self.vez = criador.get_id()

    def run():
        # while(True):
        #     10
        return

    def entrar(self, cliente):
        try:
            self.cliente[cliente.get_id()] = cliente
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
        return '{'+f"q:{len(self.cliente)},p:{self.senha==''},nome:{self.name},id:{self.id}"+'}'
