package Cliente;

public class Sala {
    public String id;
    public String nome;

    @Override
    public String toString() {
        return "id: " + id + ", nome: " + nome;
    }

    public Sala(String info) {
        String[] parts = info.split(";");

        for (String part : parts) {
            String[] s = part.split(":");
            switch (s[0]) {
                case "nome":
                    nome = s[1];
                    break;
                case "id":
                    id = s[1];
                default:
                    break;
            }

        }

    }
}
