from socket import socket

from threading import Thread
# import random
import sys

from conexao import *
from login import *

# Thread do cliente


class cliente(Thread):
    def __init__(self,  socket: socket):
        Thread.__init__(self)
        self.socket = socket
        self.conexao = True

    def run(self):
        socket = self.socket
        texto = pegar(socket)

        login = Login(texto)

        enviar(socket, login.id)

        while self.conexao:
            try:
                enviar(" '-' ")
            except:
                self.conexao = False
                continue
            texto = pegar(socket)
            resposta = ""
            enviar(socket, resposta)
            # sys.stdout.write(f"Text: {texto} \n")

            # sys.stdout.write(f"{self.socket}")
            # sys.stdout.flush()
        # self.socket.send(b'teste')
        # self.socket
        # self.socket.close()
    def resposta(self):
        if(self.conexao):
            print('10')
