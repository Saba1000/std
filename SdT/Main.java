package SdT;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int t = 0;
        int n;

        // Input dell'utente per il numero di thread e il valore massimo N
        Scanner num = new Scanner(System.in);
        System.out.println("Quanti Thread vuoi creare oltre al Main?");
        try {
            t = num.nextInt();
        } catch (Exception e) {
            System.out.println("\nNon è un numero valido");
            return;
        }

        System.out.println("Scrivi un numero?");
        try {
            n = num.nextInt();
        } catch (Exception e) {
            System.out.println("\nNon è un numero valido");
            return;
        }

        Thread[] threads = new Thread[t];
        Contatore[] contatori = new Contatore[t];
        Random random = new Random();

        for (int i = 0; i < t; i++) {
            int X = random.nextInt(n + 1);  // Genera un valore casuale tra 0 e N
            contatori[i] = new Contatore();  // Ogni thread ha il proprio contatore
            Incrementatore inc = new Incrementatore(contatori[i], X);
            threads[i] = new Thread(inc, "Thread-" + (i + 1));
            threads[i].start();
        }

        // Monitoraggio dello stato dei thread
        boolean tuttiCompletati = false;
        while (!tuttiCompletati) {
            tuttiCompletati = true;
            for (int i = 0; i < t; i++) {
                if (threads[i].isAlive()) {
                    System.out.println(threads[i].getName() + " è attivo, valore corrente: " + contatori[i].getValore());
                    tuttiCompletati = false;
                } else {
                    System.out.println(threads[i].getName() + " COMPLETATO");
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("TUTTI I THREAD COMPLETATI");
    }
}