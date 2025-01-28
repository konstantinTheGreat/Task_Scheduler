package org.java5thsem.entity;

public class CompletedTask {
    private static int nextId = 1;
    private int id;
    private int scheduledId;
    private int result;
    private Methods method;

    public CompletedTask(int scheduledId, int result, Methods method) {
        this.id = nextId++;
        this.scheduledId = scheduledId;
        this.result = result;
        this.method = method;
    }

    public Methods getMethod() {
        return method;
    }

    public int getId() {
        return id;
    }

    public int getScheduledId() {
        return scheduledId;
    }

    public int getResult() {
        return result;
    }
}
