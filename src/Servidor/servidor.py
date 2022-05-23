from asyncio.windows_events import NULL
import socket

from cliente import cliente
from banco import *

HOST = socket.gethostname()  # Endereco IP do Servidor
# HOST = '127.0.0.1'  # Endereco IP do Servidor
PORT = 7070         # Porta que o Servidor esta
print(f"{socket.gethostbyname(socket.gethostname())}")

# insert(campos={'nome': 'jose2', 'lv': 0,
#    'xp': 0, 'pontos': 0}, tabela='clientes')

serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

serversocket.bind((HOST, PORT))

serversocket.listen(5)
count = 0
c2 = False
while count == 0 or c2:
    # accept connections from outside
    if(c2 == False):
        c2 = True
        (clientsocket, address) = serversocket.accept()

        print(f"Connected by {address}")
        c = cliente(clientsocket, c2)
        c.start()
        c.resposta()
        count += 1
# now do something with the clientsocket
# in this case, we'll pretend this is a threaded server
# clientsocket.send(b'teste')
# clientsocket.close()
# ct = client_thread(clientsocket)
# ct.run()
