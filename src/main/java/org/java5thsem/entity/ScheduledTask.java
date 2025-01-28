package org.java5thsem;

public class ScheduledTask   {
    private static int nextId = 1;
    private int id;
    private String time;
    private Methods method;
    private int firstVar;
    private int secondVar;

    public ScheduledTask(String time, Methods method, int firstVar, int secondVar) {
        this.id = nextId++;
        this.time = time;
        this.method = method;
        this.firstVar = firstVar;
        this.secondVar = secondVar;
    }

    public String getTime() {
        return time;
    }

    public Methods getMethod() {
        return method;
    }

    public int getFirstVar() {
        return firstVar;
    }

    public int getSecondVar() {
        return secondVar;
    }

    public int getId() {
        return id;
    }

}
