import java.util.Random;
import java.util.Scanner;

class Rolo implements Runnable {
    private String[] simbolos = {"🐅", "🍒", "🔔", "💰", "🍀"};
    private String resultado;
    private volatile boolean girando = true; // Control flag


    public Rolo() {
        this.resultado = simbolos[new Random().nextInt(simbolos.length)];
    }
    @Override
    public void run() {
        int index = 0; // Start from the first symbol
        try {
            while (girando) { // Keep cycling through symbols
                resultado = simbolos[index];
                System.out.print("\rGirando... " + resultado);
                Thread.sleep(200); // Simulate time passing

                index = (index + 1) % simbolos.length; // Cycle through symbols
            }
            System.out.println("\n -> Parou em: " + resultado);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResultado() {
        return resultado;
    }

    public void parar() {
        girando = false; // Stop the loop naturally
    }
}
