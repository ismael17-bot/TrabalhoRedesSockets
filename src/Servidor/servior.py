import socket

from cliente import cliente
HOST = socket.gethostname()  # Endereco IP do Servidor
# HOST = '127.0.0.1'  # Endereco IP do Servidor
PORT = 7070         # Porta que o Servidor esta
print(f"{socket.gethostbyname(socket.gethostname())}")

serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

serversocket.bind((HOST, PORT))

serversocket.listen(5)

# while True:
# accept connections from outside
(clientsocket, address) = serversocket.accept()
print(f"Connected by {address}")
cliente(clientsocket).start()
# now do something with the clientsocket
# in this case, we'll pretend this is a threaded server
# clientsocket.send(b'teste')
# clientsocket.close()
# ct = client_thread(clientsocket)
# ct.run()
