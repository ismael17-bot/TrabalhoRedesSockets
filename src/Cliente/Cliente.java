package Cliente;


// import java.io.PrintStream;
// import java.net.Socket;
// import java.util.Scanner;
// import java.util.Scanner;


public class Cliente {
    public String nome;
    public int id;
    public int ponto;
    public int xp;
    public int lv;

    private static Cliente _this = new Cliente();

    public static Cliente get() {
        return _this;
    }
    

    private Cliente(){}


    public Cliente(String info){
        String[] parts = info.split(";");

        for (String part : parts) {
            String[] s = part.split(":");
            switch (s[0]) {
                case "nome":
                    nome = s[1];
                    break;
                case "id":
                    id = Integer.parseInt(s[1]);
                    break;
                case "ponto":
                    ponto = Integer.parseInt(s[1]);
                    break;
                case "xp":
                    xp = Integer.parseInt(s[1]);
                    break;
                case "lv":
                    lv = Integer.parseInt(s[1]);
                    break;
                default:
                    break;
            }

        }
        
    }

    @Override
    public String toString() {
        return "id: " + id + ", nome: " + nome;
    }
}
