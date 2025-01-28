package org.java5thsem.repository;

import org.java5thsem.entity.ScheduledTask;

import java.util.ArrayList;
import java.util.List;

public class TaskRepo {

    private static TaskRepo instance;
    private List<ScheduledTask> tasks;

    public TaskRepo() {
        tasks = new ArrayList<>();
    }

    public static TaskRepo getInstance() {
        if (instance == null) {
            instance = new TaskRepo();
        }
        return instance;
    }

    public void addTask(ScheduledTask task) {
        tasks.add(task);
    }

    public void removeTask(ScheduledTask task) {
        tasks.remove(task);
    }

    public List<ScheduledTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<ScheduledTask> tasks) {
        this.tasks = tasks;
    }

    public ScheduledTask getTask(int index) {
        return tasks.get(index);
    }

    public void removeAllTasks() {
        tasks.clear();
    }
}
