public class Machine {
    private Rolo rolo1, rolo2, rolo3;
    private Thread t1, t2, t3;
    private boolean ganhou;
    private double premio;
    private double aposta;

    public Machine(double aposta) {
        this.aposta = aposta;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void joinThread(Thread t) {
        try {
            t.join();
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

    // Add to Machine.java
    public void runMachine() {
        rolo1 = new Rolo();
        rolo2 = new Rolo();
        rolo3 = new Rolo();

        t1 = new Thread(rolo1);
        t2 = new Thread(rolo2);
        t3 = new Thread(rolo3);

        this.ganhou = false;
        this.premio = 0;

        t1.start();
        t2.start();
        t3.start();

        Thread display = new Thread(() -> {
            while (t1.isAlive() || t2.isAlive() || t3.isAlive()) {
                System.out.print("\r🎰 " + rolo1.getResultado() + " | " + rolo2.getResultado() + " | " + rolo3.getResultado());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        display.start();

        sleep(2000);

        rolo1.parar();
        rolo2.parar();
        rolo3.parar();

        joinThread(t1);
        joinThread(t2);
        joinThread(t3);
        joinThread(display);

        System.out.println("\n -> Resultado final: " + getResultado());

        verificarResultado(aposta);
    }

}
