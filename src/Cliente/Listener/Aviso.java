package Cliente.Listener;

import java.util.ArrayList;

public class Aviso {
    private ArrayList<AvisoI> listenerContador = new ArrayList<>();

    public void executa() {
        for (AvisoI contador : listenerContador) {
            System.out.println("aqui 4");
            contador.onAviso(-1);
        }
    }

    public void addListener(AvisoI contadorListener) {
        System.out.println("aqui 3");
        listenerContador.add(contadorListener);
    }
}
