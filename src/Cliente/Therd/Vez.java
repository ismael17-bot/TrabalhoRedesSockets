package Cliente.Therd;

import java.util.concurrent.TimeUnit;

import Cliente.Listener.Aviso;

public class Vez extends Thread {

    private Aviso aviso;
    private boolean on = true;

    public Vez(Aviso aviso) {
        super();
        this.aviso = aviso;
        // System.out.println("aviso:" + aviso);
        this.start();
    }

    public void off() {
        on = false;
    }

    @Override
    public void run() {
        super.run();
        while (on) {
            this.aviso.executa();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
