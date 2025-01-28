package org.java5thsem.repository;

import org.java5thsem.entity.CompletedTask;

import java.util.ArrayList;
import java.util.List;

public class CompletedTasksRepo {

    private static CompletedTasksRepo instance;
    private List<CompletedTask> tasks;

    public CompletedTasksRepo() {
        tasks = new ArrayList<>();
    }

    public static CompletedTasksRepo getInstance() {
        if (instance == null) {
            instance = new CompletedTasksRepo();
        }
        return instance;
    }

    public void addTask(CompletedTask task) {
        tasks.add(task);
    }

    public void removeTask(CompletedTask task) {
        tasks.remove(task);
    }

    public List<CompletedTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<CompletedTask> tasks) {
        this.tasks = tasks;
    }

    public CompletedTask getTask(int index) {
        return tasks.get(index);
    }

    public void removeAllTasks() {
        tasks.clear();
    }
}
