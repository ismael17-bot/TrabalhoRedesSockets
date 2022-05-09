from socket import socket

from threading import Thread
# import random
import sys

from conexao import *

# Thread do cliente


class cliente(Thread):
    def __init__(self,  socket: socket):
        Thread.__init__(self)
        self.socket = socket

    def run(self):
        socket = self.socket
        texto = pegar(socket)
        sys.stdout.write(f"Text: {texto} \n")

        sys.stdout.write(f"{self.socket}")
        sys.stdout.flush()
        # self.socket.send(b'teste')
        # self.socket
        # self.socket.close()
