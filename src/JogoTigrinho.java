import java.util.Scanner;

public class JogoTigrinho {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double saldo = 100.0; // Saldo inicial do jogador

        System.out.println("🎰 Bem-vindo ao Jogo do Tigrinho!");

        while (saldo > 0) {
            System.out.println("\nSeu saldo atual: R$" + saldo);
            System.out.print("Digite sua aposta (ou 0 para sair): R$");
            double aposta = scanner.nextDouble();

            if (aposta == 0) {
                System.out.println("Obrigado por jogar! Seu saldo final: R$" + saldo);
                break;
            }

            if (aposta > saldo) {
                System.out.println("Saldo insuficiente! Tente novamente.");
                continue;
            }

            /*System.out.println("Pressione ENTER para girar os rolos...");
            try {
                System.in.read(); // Aguarda o jogador pressionar ENTER
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            // Criando os rolos
            Rolo rolo1 = new Rolo();
            Rolo rolo2 = new Rolo();
            Rolo rolo3 = new Rolo();

            // Criando e iniciando as threads dos rolos
            Thread t1 = new Thread(rolo1);
            Thread t2 = new Thread(rolo2);
            Thread t3 = new Thread(rolo3);

            t1.start();
            t2.start();
            t3.start();

            // Espera os rolos pararem
            try {
                t1.join(); //pausa a thread atual té que a thread alvo termine
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Mostrando resultado final
            System.out.println("\nResultado final: " + rolo1.getResultado() + " | " + rolo2.getResultado() + " | " + rolo3.getResultado());

            // Verificando se o jogador ganhou
            if (rolo1.getResultado().equals(rolo2.getResultado()) && rolo2.getResultado().equals(rolo3.getResultado())) {
                double premio = aposta * 5; // Ganha 5x o valor apostado
                saldo += premio;
                System.out.println("🎉 PARABÉNS! Você ganhou R$" + premio + "!");
            } else {
                saldo -= aposta;
                System.out.println("😢 Você perdeu R$" + aposta + ". Tente novamente!");
            }
        }

        scanner.close();
    }
}
