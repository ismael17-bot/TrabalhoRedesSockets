from itertools import count
from socket import socket
import string
# realiza as conexões


def pegar(socket: socket):

    tamanho = socket.recv(1024)
    print(f"tam: {tamanho}")

    if(tamanho.isdigit()):
        dado = socket.recv(int.from_bytes(tamanho, "big"))
    else:
        dado = tamanho

    print(f"recebido: {dado}")
    return dado.decode('utf-8')


def enviar(socket: socket, dado: string):
    dado = bytes(f"{dado}", 'utf-8')
    print(f"envidado: {dado}")
    socket.send(dado)
    socket.send(bytes("\n;-;FIM;-;\n", 'utf-8'))
