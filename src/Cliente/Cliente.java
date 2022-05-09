package Cliente;

import java.io.PrintStream;
import java.net.Socket;
// import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        new Cliente().conexao();
    }

    public void conexao() {
        try {
            Socket socket = new Socket("10.0.101.110", 7070);
            PrintStream pw = new PrintStream(socket.getOutputStream());
            pw.print("texto".length());
            // pw.flush();
            pw.print("texto");
            pw.close();

            // Scanner in = new Scanner(socket.getInputStream());
            // System.out.println("Server response: " + in.nextLine());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
