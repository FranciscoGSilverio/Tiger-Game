import java.util.Random;

class Rolo implements Runnable {
    private String[] simbolos = {"🐅", "🍒", "🔔", "💰", "🍀"};
    private String resultado = "❓";
    private volatile boolean girando = true;
    private final Random random = new Random();

    public void run() {
        while (girando) {
            resultado = simbolos[random.nextInt(simbolos.length)];
            try {
                Thread.sleep(100); // Spin speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getResultado() {
        return resultado;
    }

    public void parar() {
        girando = false;
    }
}
