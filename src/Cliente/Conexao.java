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

    public Conexao(String host) throws UnknownHostException, IOException {
        open(host);
    }

    private void open(String host) throws UnknownHostException, IOException {
        socket = new Socket(host, 7070);
    }

    private void close() throws IOException {
        socket.close();
    }

    /**
     * @param dados = "acao:{info}"
     */
    public String conexao(String dados) throws IOException {
        if (dados.equals("sair")) {
            close();
            return "";
        }
        enviar(dados);
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