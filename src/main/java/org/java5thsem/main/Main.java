package org.java5thsem.main;

import org.java5thsem.entity.Methods;
import org.java5thsem.entity.ScheduledTask;
import org.java5thsem.scheduler.TaskScheduler;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler();

        ScheduledTask task1 = new ScheduledTask(2, Methods.ADD, 10, 0);
        ScheduledTask task2 = new ScheduledTask(10, Methods.SUBTRACT, 3, 0);
        ScheduledTask task3 = new ScheduledTask(1, Methods.MULTIPLY, 2, 0);

        taskScheduler.scheduleTask(task1);
        taskScheduler.scheduleTask(task2);
        taskScheduler.scheduleTask(task3);

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
