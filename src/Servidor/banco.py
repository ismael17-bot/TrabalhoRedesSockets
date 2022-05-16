from pickle import TRUE
from numpy import array
import pandas as pd
import sqlite3

con = sqlite3.connect("data/banco.db")


def select(sql):
    dados = pd.read_sql_query(sql, con)
    print(dados)
    return dados


def insert(campos={}, tabela=''):
    try:
        d = pd.DataFrame([campos.values()], columns=campos.keys())
        d.to_sql(tabela, con, if_exists='append', index=False)
        return True
    except:
        return False
