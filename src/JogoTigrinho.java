import java.util.*;

public class JogoTigrinho {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int NUM_MACHINES = 3;

        System.out.print("Quantos jogadores deseja simular? ");
        int numUsers = scanner.nextInt();

        List<User> users = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= numUsers; i++) {
            int priority = rand.nextInt(10) + 1;
            users.add(new User(i, priority));
        }

        System.out.println("\n📋 Jogadores criados:");
        for (User user : users) {
            System.out.println("User " + user.getId() + " | Priority: " + user.getPriority());
        }

        Scheduler scheduler = new Scheduler(users, NUM_MACHINES);
        scheduler.runFCFS();
        scheduler.runPriority();

        System.out.println("\n🏁 Simulação concluída.");
    }
}
