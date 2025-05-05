public class MachineTask implements Runnable {
    private final User user;
    private final int machineId;

    public MachineTask(User user, int machineId) {
        this.user = user;
        this.machineId = machineId;
    }

    @Override
    public void run() {
        user.setStartTime(System.currentTimeMillis());
        System.out.println("🧑‍💻 User " + user.getId() + " started on Machine " + machineId);
        Machine machine = new Machine(0); // No bet needed here
        user.setEndTime(System.currentTimeMillis());
    }

    public User getUser() {
        return user;
    }
}
