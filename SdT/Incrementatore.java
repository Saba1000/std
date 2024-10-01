package SdT;

public class Incrementatore implements Runnable {
    private Contatore contatore;
    private int X;

    public Incrementatore(Contatore contatore, int X) {
        this.contatore = contatore;
        this.X = X;
    }

    @Override
    public void run() {
        for (int i = 0; i <= X; i++) {
            contatore.incrementa();  // Incrementa il contatore
            try {
                Thread.sleep(120);  // Attendi 120 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
