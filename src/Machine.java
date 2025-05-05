import java.util.*;

public class Machine implements Runnable {
    private final int id;
    private final Queue<User> userQueue = new LinkedList<>();

    public Machine(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public synchronized void addUser(User user) {
        userQueue.offer(user);
    }

    @Override
    public void run() {
        while (!userQueue.isEmpty()) {
            User user = userQueue.poll();
            if (user == null) continue;

            user.setStartTime(System.currentTimeMillis());

            System.out.printf("\n🎮 Máquina %d rodando para User %d (Prioridade: %d)\n",
                    id, user.getId(), user.getPriority());

            // ✅ Pass IDs to runMachine
            MachineGame game = new MachineGame(user.getAposta());
            game.runMachine(user.getId(), id);

            user.setEndTime(System.currentTimeMillis());
        }
    }

}
