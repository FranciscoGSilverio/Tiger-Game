public class Machine {
    private Rolo rolo1, rolo2, rolo3;
    private Thread t1, t2, t3;
    private boolean ganhou;
    private double premio;

    public Machine(double aposta) {
        rolo1 = new Rolo();
        rolo2 = new Rolo();
        rolo3 = new Rolo();

        t1 = new Thread(rolo1);
        t2 = new Thread(rolo2);
        t3 = new Thread(rolo3);

        this.ganhou = false;
        this.premio = 0;

        // Start spinning
        t1.start();
        t2.start();
        t3.start();

        // Stop each reel at a random delay
        pararRolo(rolo1, t1);
        this.sleep();
        pararRolo(rolo2, t2);
        this.sleep();
        pararRolo(rolo3, t3);

        // Check the final result
        verificarResultado(aposta);
    }

    private void pararRolo(Rolo rolo, Thread t) {
        rolo.parar(); // Stop the reel
        try {
            t.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1500 ); // Random delay between 500ms - 1500ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void verificarResultado(double aposta) {
        if (rolo1.getResultado().equals(rolo2.getResultado()) && rolo2.getResultado().equals(rolo3.getResultado())) {
            premio = aposta * 5;
            ganhou = true;
        }
    }

    public String getResultado() {
        return rolo1.getResultado() + " | " + rolo2.getResultado() + " | " + rolo3.getResultado();
    }

    public boolean isGanhou() {
        return ganhou;
    }

    public double getPremio() {
        return premio;
    }
}
