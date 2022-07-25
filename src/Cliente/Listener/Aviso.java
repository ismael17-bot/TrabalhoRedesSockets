package Cliente.Listener;

import java.util.ArrayList;

public class Aviso {
    private ArrayList<AvisoI> listenerContador = new ArrayList<>();

    public void executa() {
        for (AvisoI contador : listenerContador) {
            contador.onAviso(-1);
        }
    }

    public void addListener(AvisoI contadorListener) {
        listenerContador.add(contadorListener);
    }
}
