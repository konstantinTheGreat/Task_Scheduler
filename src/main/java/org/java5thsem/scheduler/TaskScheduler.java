package org.java5thsem.scheduler;

import org.java5thsem.entity.CompletedTask;
import org.java5thsem.entity.ScheduledTask;
import org.java5thsem.repository.CompletedTasksRepo;
import org.java5thsem.repository.TaskRepo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler implements Task {

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    private TaskRepo taskRepo = TaskRepo.getInstance();
    private CompletedTasksRepo completedTasksRepo = CompletedTasksRepo.getInstance();


    public void scheduleTask(ScheduledTask task) {
        long delayInSeconds = task.getTime();

        delayInSeconds = Math.max(delayInSeconds, 0);

        executorService.schedule(() -> completeTask(task), delayInSeconds, TimeUnit.SECONDS);
    }


    @Override
    public void completeTask(ScheduledTask task) {
        try {
            int result = 0;
            switch (task.getMethod()) {
                case ADD:
                    result = task.getFirstVar() + task.getSecondVar();
                    break;
                case SUBTRACT:
                    result = task.getFirstVar() - task.getSecondVar();
                    break;
                case MULTIPLY:
                    result = task.getFirstVar() * task.getSecondVar();
                    break;
                case DIVIDE:
                    if (task.getSecondVar() != 0) {
                        result = task.getFirstVar() / task.getSecondVar();
                    } else {
                        System.out.println("Task ID " + task.getId() + ": Division by zero is not allowed.");
                        return;
                    }
                    break;
                default:
                    System.out.println("Task ID " + task.getId() + ": Unknown operation.");
                    return;
            }

            CompletedTask completedTask = new CompletedTask(task.getId(), result, task.getMethod());
            completedTasksRepo.addTask(completedTask);
            System.out.println("Task ID " + task.getId() + " completed: " + completedTask);

        } catch (Exception e) {
            System.out.println("Error executing task ID " + task.getId() + ": " + e.getMessage());
        }
    }


    public void shutdown() {
        executorService.shutdown();
    }
}
