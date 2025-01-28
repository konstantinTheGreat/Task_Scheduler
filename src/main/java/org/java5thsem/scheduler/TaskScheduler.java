package org.java5thsem;

import org.java5thsem.repository.TaskRepo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TaskScheduler implements Task {

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    TaskRepo taskRepo = TaskRepo.getInstance();

    @Override
    public void completeTask(ScheduledTask task) {
        taskRepo.getTask(task.getId());
        if (task.getMethod() == Methods.ADD) {

        }

    }
}
