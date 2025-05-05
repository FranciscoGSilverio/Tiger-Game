import java.util.*;

public class Scheduler {
    private final List<User> users;
    private final int numMachines;

    public Scheduler(List<User> users, int numMachines) {
        this.users = users;
        this.numMachines = numMachines;
    }

    public void runFCFS() {
        System.out.println("\n🚦 Iniciando simulação FCFS...");
        runScheduling((a, b) -> 0); // No sorting — keep original order
    }

    public void runPriority() {
        System.out.println("\n🚦 Iniciando simulação por Prioridade...");
        runScheduling(Comparator.comparingInt(User::getPriority)); // Sort by priority
    }

    private void runScheduling(Comparator<User> comparator) {
        long startSim = System.currentTimeMillis();

        // Step 1: Create and map users to machines
        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < numMachines; i++) {
            machines.add(new Machine(i + 1));
        }

        List<User> sorted = new ArrayList<>(users);
        sorted.sort(comparator);

        for (int i = 0; i < sorted.size(); i++) {
            User user = sorted.get(i);
            int assignedMachine = i % numMachines;
            machines.get(assignedMachine).addUser(user);
            user.setMachineId(assignedMachine + 1);
        }

        // Step 2: Run all machines in parallel
        List<Thread> threads = new ArrayList<>();
        for (Machine machine : machines) {
            Thread t = new Thread(machine);
            threads.add(t);
            t.start();
        }

        // Step 3: Start a rolling animation in a separate thread
        Thread animationThread = new Thread(() -> {
            String[] frames = {"|", "/", "-", "\\"};
            int i = 0;
            while (threads.stream().anyMatch(Thread::isAlive)) {
                i++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        animationThread.start();

        // Step 4: Wait for all machines to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Ensure the animation thread finishes
        try {
            animationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final report
        System.out.println("\n\n📊 Relatório:");
        for (User user : sorted) {
            System.out.printf("User %d | Máquina %d | Espera: %d ms | Execução: %d ms\n",
                    user.getId(), user.getMachineId(),
                    user.getWaitingTime(startSim),
                    user.getExecutionTime());
        }
    }
}
