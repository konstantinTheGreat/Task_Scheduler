package org.java5thsem.entity;

public class ScheduledTask   {
    private static int nextId = 1;
    private int id;
    private int time;
    private Methods method;
    private int firstVar;
    private int secondVar;

    public ScheduledTask(int time, Methods method, int firstVar, int secondVar) {
        this.id = nextId++;
        this.time = time;
        this.method = method;
        this.firstVar = firstVar;
        this.secondVar = secondVar;
    }

    public int getTime() {
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
