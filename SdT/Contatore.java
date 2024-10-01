package SdT;

public class Contatore {
    private int valore = 0;

    public synchronized void incrementa() {
        valore++;
    }

    public synchronized int getValore() {
        return valore;
    }
}