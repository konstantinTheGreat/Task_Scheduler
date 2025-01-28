package org.java5thsem;

import java.util.List;

public class Repo {

    private List<Task> tasks;

    public Repo(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
