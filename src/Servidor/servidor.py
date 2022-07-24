from sala import gerenciador_salas
import socket

from cliente import cliente
# from banco import *

HOST = socket.gethostname()  # Endereco IP do Servidor
# HOST = '127.0.0.1'  # Endereco IP do Servidor
PORT = 7070         # Porta que o Servidor esta
print(f"{socket.gethostbyname(socket.gethostname())}")

# insert(campos={'nome': 'jose2', 'lv': 0,
#    'xp': 0, 'pontos': 0}, tabela='clientes')

serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

serversocket.bind((HOST, PORT))

serversocket.listen(5)


gerenciador_sala = gerenciador_salas()


count = 0
c2 = True
while count < 2 or c2:
    # accept connections from outside

    try:
        (clientsocket, address) = serversocket.accept()

        print(f"Connected by {address}")

        c = cliente(clientsocket, gerenciador_sala)
        c.start()
        c.resposta()
        count += 1
    except:
        print('Deu ruim')
