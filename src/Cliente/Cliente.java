package Cliente;

public class Cliente {
    public String nome;
    public int id;

    private static Cliente _this = new Cliente();

    public static Cliente get() {
        return _this;
    }
    
    private Cliente(){}
}
