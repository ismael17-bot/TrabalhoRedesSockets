import java.io.IOException;

import Cliente.Conexao;
import Cliente.Telas.TelaUsuario;

public class Main {
    public static void main(String[] args) {
        try {
            Conexao.get("batata");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        new TelaUsuario();
    }
}
