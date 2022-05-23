package Cliente;

import java.io.PrintStream;
import java.net.Socket;
// import java.util.Scanner;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        new Cliente().conexao();
    }

    public void conexao() {
        try {
            Socket socket = new Socket("10.0.101.106", 7070);
            PrintStream pw = new PrintStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            // String a = ;
            String texto = "";
            do {
                texto = scanner.nextLine();
                System.out.println("Texto:" + texto);
                pw.print(texto.length());
                pw.print(texto);
                pw.flush();
            } while (!texto.equals("sair"));
            pw.close();
            scanner.close();

            // Scanner in = new Scanner(socket.getInputStream());
            // System.out.println("Server response: " + in.nextLine());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
