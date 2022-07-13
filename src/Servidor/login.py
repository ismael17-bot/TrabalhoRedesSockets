

import json

from banco import insert, select


class Login():
    def __init__(self, info):
        self.id = '-1'
        self._login(info)

    def _login(self, texto=""):
        j = json.loads(texto)
        print(j)
        if('id' in j):
            self.id = j['id']
        else:
            insert({'nome': j['nome'], 'xp': 0,
                   'lv': 0, 'pontos': 0}, 'clientes')
            d = select(
                'SELECT id FROM clientes WHERE nome="'+j['nome']+'" AND xp=0 AND lv=0 AND pontos=0 ORDER BY id desc')
            self.id = d.iloc[0]['id']
