from socket import socket
import string
# realiza as conexões


def pegar(socket: socket):
    tamanho = socket.recv(1024)

    if(tamanho.isdigit()):
        dado = socket.recv(int.from_bytes(tamanho, "big"))
    else:
        dado = socket.recv(1024)
    print(dado)
    return dado.decode('utf-8')


def enviar(socket: socket, dado: string):
    return socket.sendall(bytes(dado, 'utf-8'))
