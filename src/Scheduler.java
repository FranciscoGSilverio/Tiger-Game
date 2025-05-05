import java.util.*;

public class Scheduler {
    private List<User> users;
    private int numMachines;

    public Scheduler(List<User> users, int numMachines) {
        this.users = users;
        this.numMachines = numMachines;
    }

    public void runFCFS() {
        System.out.println("\n🚦 Iniciando simulação FCFS...");

        long startSim = System.currentTimeMillis();
        int machine = 1;

        for (User user : users) {
            user.setMachineId(machine);
            user.setStartTime(System.currentTimeMillis());

            System.out.printf("\n🎮 User %d (Prioridade: %d) usando Máquina %d\n",
                    user.getId(), user.getPriority(), machine);

            Machine m = new Machine(user.getAposta());
            m.runMachine();

            user.setEndTime(System.currentTimeMillis());
            machine = (machine % numMachines) + 1;
        }

        System.out.println("\n📊 Relatório FCFS:");
        printResults(users, startSim);
    }

    public void runPriority() {
        System.out.println("\n🚦 Iniciando simulação por Prioridade...");

        long startSim = System.currentTimeMillis();
        List<User> sorted = new ArrayList<>(users);
        sorted.sort(Comparator.comparingInt(User::getPriority)); // 1 is highest priority

        int machine = 1;
        for (User user : sorted) {
            user.setMachineId(machine);
            user.setStartTime(System.currentTimeMillis());

            System.out.printf("\n🎮 User %d (Prioridade: %d) usando Máquina %d\n",
                    user.getId(), user.getPriority(), machine);

            Machine m = new Machine(user.getAposta());
            m.runMachine();

            user.setEndTime(System.currentTimeMillis());
            machine = (machine % numMachines) + 1;
        }

        System.out.println("\n📊 Relatório Prioridade:");
        printResults(sorted, startSim);
    }

    private void printResults(List<User> users, long simStart) {
        for (User user : users) {
            System.out.printf("User %d | Máquina %d | Espera: %d ms | Execução: %d ms\n",
                    user.getId(), user.getMachineId(),
                    user.getWaitingTime(simStart),
                    user.getExecutionTime());
        }
    }
}
