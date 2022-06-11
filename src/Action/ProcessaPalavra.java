package Action;

public class ProcessaPalavra {

    private String palavra;

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public void enviaPalavraServidor() {

    }

    public void mostraLetras () {

    }

    public void addPalavraNova (String novaPalvra){

    }

    public char[] codificaPalavra(){
        char[] palavraCodificada = new char[palavra.length()];
        for (int i = 0; i < palavra.length(); i++){
            palavraCodificada[i] = '_';
        }
        return palavraCodificada;
    }

    public Boolean verificaLetra (char letra){
        for (int i = 0; i < palavra.length(); i++){
            if(palavra.charAt(i) == letra){
                return true;
            }
        }
        return false;
    }
}
