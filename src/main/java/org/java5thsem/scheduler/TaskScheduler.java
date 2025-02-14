package org.java5thsem.scheduler;

import org.java5thsem.entity.ScheduledTask;
import org.java5thsem.entity.CompletedTask;
import org.java5thsem.repository.CompletedTasksRepo;
import org.java5thsem.thread.TaskExecutorCallable;
import org.java5thsem.thread.TaskExecutorThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.java5thsem.constant.Constants.*;
import java.util.concurrent.TimeUnit;

public class TaskScheduler implements Task {
    private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);
    private final CompletedTasksRepo completedTasksRepo = CompletedTasksRepo.getInstance();

    public void scheduleTask(ScheduledTask task) {
        long delayInSeconds = Math.max(task.getTime(), 0);
        logger.info(SCHEDULE_WITH_DELAY, task.getId(), delayInSeconds);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(delayInSeconds);

                TaskExecutorThread taskThread = new TaskExecutorThread(new TaskExecutorCallable(task));
                taskThread.start();
                taskThread.join();

                CompletedTask completedTask = taskThread.getResult();
                if (completedTask != null) {
                    completedTasksRepo.addTask(completedTask);
                    logger.info(STORED_COMPLETED_TASK, completedTask);
                }

            } catch (Exception e) {
                logger.error(ERROR_RETRIEVING_TASK, e);
            }
        }).start();
    }
}
