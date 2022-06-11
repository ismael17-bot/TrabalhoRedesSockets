package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
// import java.util.Scanner;

public class Conexao {

    private Socket socket;

    private static Conexao _this;

    public static Conexao get(String host) throws IOException {
        return _this = new Conexao(host);
    }

    public static Conexao get() {
        return _this;
    }

    private Conexao(String host) throws UnknownHostException, IOException {
        open(host);
    }

    private void open(String host) throws UnknownHostException, IOException {
        socket = new Socket(host, 7070);
    }

    private void close() throws IOException {
        socket.close();
        _this = null;
    }

    /**
     * @param dados = "acao:{info}"
     */
    public String conexao(String dados) throws IOException {
        enviar(dados);
        if (dados.equals("sair")) {
            close();
            return "";
        }
        return receber();
    }

    private void enviar(String dados) throws IOException {
        PrintStream pw = new PrintStream(socket.getOutputStream());
        pw.print(dados.length());
        pw.print(dados);
        pw.flush();
    }

    private String receber() throws IOException {
        String mensagem = "";
        boolean valida = false;
        if (socket.isConnected()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = reader.readLine();
            while (msg != null) {
                if (msg.isEmpty() && !valida) {
                    valida = true;
                    mensagem = "";
                }
                mensagem += msg;
                msg = reader.readLine();
            }
            reader.close();
        }

        return mensagem;
    }
}