package Cliente;

import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public void conexao() {
        try {
            Socket socket = new Socket("127.0.0.1", 7070);
            Scanner in = new Scanner(socket.getInputStream());
            System.out.println("Server response: " + in.nextLine());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
