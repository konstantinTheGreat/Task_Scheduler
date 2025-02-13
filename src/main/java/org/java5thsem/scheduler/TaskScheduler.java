package org.java5thsem.scheduler;

import org.java5thsem.entity.CompletedTask;
import org.java5thsem.entity.ScheduledTask;
import org.java5thsem.repository.CompletedTasksRepo;
import org.java5thsem.repository.TaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.java5thsem.constant.Constants.*;

public class TaskScheduler implements Task {
    private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    private TaskRepo taskRepo = TaskRepo.getInstance();
    private CompletedTasksRepo completedTasksRepo = CompletedTasksRepo.getInstance();

    public void scheduleTask(ScheduledTask task) {
        long delayInSeconds = Math.max(task.getTime(), 0);

        logger.info(SCHEDULE_WITH_DELAY, task.getId(), delayInSeconds);

        executorService.schedule(() -> completeTask(task), delayInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void completeTask(ScheduledTask task) {
        try {
            logger.info(EXECUTE_TASK_ID, task.getId(), task.getMethod());

            int result;
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
                        logger.warn(DIVISION_BY_ZERO, task.getId());
                        return;
                    }
                    break;
                default:
                    logger.warn(UNKNOWN_OPERATION, task.getId());
                    return;
            }

            CompletedTask completedTask = new CompletedTask(task.getId(), result, task.getMethod());
            completedTasksRepo.addTask(completedTask);
            logger.info(TASK_COMPLETED, task.getId(), completedTask);

        } catch (Exception e) {
            logger.error(ERROR_EXECUTING_TASK, task.getId(), e.getMessage(), e);
        }
    }

    public void shutdown() {
        logger.info(SHUTTING_DOWN);
        executorService.shutdown();
    }
}
