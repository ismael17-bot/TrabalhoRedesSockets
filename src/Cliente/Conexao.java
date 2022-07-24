package Cliente;

import java.io.*;
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
// import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Conexao {

    private Socket socket;
    BufferedReader reader;
    PrintStream pw;

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
        pw = new PrintStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void close() throws IOException {
        socket.close();
        _this = null;
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    /**
     * @param dados = "acao:{info}"
     * @return a:1;b:3,b:2,c:4 /// , ; :
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
        // System.out.println("------------------- i DADOS i
        // ---------------------------");
        // System.out.println("DADOS enviado: " + dados + " Len: " + dados.length());
        // System.out.println("------------------- f DADOS f
        // ---------------------------");
        pw.print(dados.length());
        pw.flush();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pw.print(dados);
        pw.flush();
    }

    private String receber() throws IOException {
        String mensagem = "";
        String msg = "";
        if (socket.isConnected()) {
            // System.out.println("--------------------- i RECEBIDO i -------------------
            // ");

            msg = reader.readLine();

            while (msg != null && !msg.equals(";-;FIM;-;")) {
                System.out.println("msg: " + msg + " len:" + msg.length());
                mensagem += msg;
                msg = reader.readLine();
            }
        }
        // System.out.println("MENSAGEM: " + mensagem + " MSG: " + msg);
        // System.out.println("--------------------- f RECEBIDO f -------------------
        // ");

        return mensagem;
    }
}