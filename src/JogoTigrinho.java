import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JogoTigrinho {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double saldo = 100; // Saldo inicial do jogador
        double total_perdas = 0;
        System.out.println("🎰 Bem-vindo ao Jogo do Tigrinho!");

        while (saldo > 0) {
            System.out.println("\nSeu saldo atual: R$" + saldo);

            System.out.println("\nDeseja colocar mais dinheiro? Sim(1) Não(2)?");
            int mais_dinheiro = scanner.nextInt();

            if(mais_dinheiro == 1){
                System.out.println("\nQuanto deseja colocar?");
                int quantidade = scanner.nextInt();

                saldo += quantidade;
            }

            System.out.print("Digite sua aposta por máquina (ou 0 para sair): R$");
            double aposta = scanner.nextDouble();
            if (aposta == 0) {
                break;
            }

            System.out.print("Quantas máquinas deseja jogar ao mesmo tempo? ");
            int numMaquinas = scanner.nextInt();

            double totalAposta = aposta * numMaquinas;

            if (totalAposta > saldo) {
                System.out.println("Saldo insuficiente para jogar em " + numMaquinas + " máquinas! Tente novamente.");
                continue;
            }

            saldo -= totalAposta;
            List<Machine> maquinas = new ArrayList<>();

            // Criando e rodando as máquinas
            for (int i = 0; i < numMaquinas; i++) {
                System.out.println("\n🎰 Máquina " + (i + 1) + " rodando...");
                maquinas.add(new Machine(aposta));
            }

            // Mostrando os resultados
            for (int i = 0; i < numMaquinas; i++) {
                Machine maquina = maquinas.get(i);
                System.out.println("🕹️ Máquina " + (i + 1) + ": " + maquina.getResultado());

                if (maquina.isGanhou()) {
                    saldo += maquina.getPremio();
                    total_perdas -= aposta;
                    System.out.println("🎉 Máquina " + (i + 1) + " ganhou! Prêmio: R$" + maquina.getPremio());
                } else {
                    System.out.println("😢 Máquina " + (i + 1) + " perdeu!");
                    total_perdas += aposta;
                }
            }

        }
        if(total_perdas > 0){
            System.out.println("Total de perdas R$" + total_perdas);
        }
        System.out.println("Obrigado por jogar! Seu saldo final: R$" + saldo);
        scanner.close();
    }


}
