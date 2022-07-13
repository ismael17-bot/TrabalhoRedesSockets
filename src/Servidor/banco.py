from pickle import TRUE
from numpy import array
import pandas as pd
import sqlite3


def open():
    return sqlite3.connect("data/banco.db")


def close(conn: sqlite3.Connection):
    conn.close()


def select(sql):
    con = open()
    dados = pd.read_sql_query(sql, con)
    close(con)
    return dados


def insert(campos={}, tabela=''):
    try:
        con = open()
        d = pd.DataFrame([campos.values()], columns=campos.keys())
        d.to_sql(tabela, con, if_exists='append', index=False)
        close(con)
        return True
    except:
        close(con)
        return False


def sqlQuery(sql):
    con = open()
    try:
        con.execute(sql)
        con.commit()
        close(con)
        return True
    except:
        close(con)
        return False
