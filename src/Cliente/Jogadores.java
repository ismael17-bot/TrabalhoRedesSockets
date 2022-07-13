package Cliente;

public class Jogadores {
    public int id;
    public int xp;
    public int lv;
    public int pontos;
    public String nome;

    @Override
    public String toString() {
        return "nome: " + nome + ", pontos: " + pontos;
    }

    public Jogadores(String info) {
        String[] parts = info.split(";");

        for (String part : parts) {
            String[] s = part.split(":");
            switch (s[0]) {
                case "n":
                    nome = s[1];
                    break;
                case "id":
                    id = Integer.parseInt(s[1]);
                    break;
                case "xp":
                    xp = Integer.parseInt(s[1]);
                    break;
                case "lv":
                    lv = Integer.parseInt(s[1]);
                    break;
                case "p":
                    pontos = Integer.parseInt(s[1]);
                    break;
                default:
                    break;
            }

        }

    }
}
