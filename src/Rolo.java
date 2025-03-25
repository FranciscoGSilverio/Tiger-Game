import java.util.Random;
import java.util.Scanner;

class Rolo implements Runnable {
    private String[] simbolos = {"🐅", "🍒", "🔔", "💰", "🍀"};
    private String resultado;
    private Random random = new Random();

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) { // Simula os giros do rolo
                resultado = simbolos[random.nextInt(simbolos.length)];
                System.out.print("\rGirando... " + resultado);
                Thread.sleep(200); // Tempo entre cada troca de símbolo
            }
            System.out.println(" ");
            System.out.println(" -> Parou em: " + resultado);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String[] getSimbolos() {
        return simbolos;
    }

    public String getResultado() {
        return resultado;
    }
}
