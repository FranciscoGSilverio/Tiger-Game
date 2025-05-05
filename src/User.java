public class User {
    private int id;
    private int priority;
    private int machineId;
    private long startTime;
    private long endTime;
    private double aposta = 1.0;

    public User(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public int getId() { return id; }
    public int getPriority() { return priority; }
    public int getMachineId() { return machineId; }
    public void setMachineId(int machineId) { this.machineId = machineId; }

    public void setStartTime(long t) { this.startTime = t; }
    public void setEndTime(long t) { this.endTime = t; }

    public long getWaitingTime(long simulationStartTime) {
        return startTime - simulationStartTime;
    }

    public long getExecutionTime() {
        return endTime - startTime;
    }

    public double getAposta() { return aposta; }
}
