package org.java5thsem.thread;

import org.java5thsem.entity.CompletedTask;
import org.java5thsem.entity.ScheduledTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;

import static org.java5thsem.constant.Constants.*;

public class TaskExecutorCallable implements Callable<CompletedTask> {
    private static final Logger logger = LoggerFactory.getLogger(TaskExecutorCallable.class);
    private final ScheduledTask task;

    public TaskExecutorCallable(ScheduledTask task) {
        this.task = task;
    }

    @Override
    public CompletedTask call() {
        try {
            logger.info(EXECUTE_TASK_ID, task.getId());

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
                        return null;
                    }
                    break;
                default:
                    logger.warn(UNKNOWN_OPERATION, task.getId());
                    return null;
            }

            CompletedTask completedTask = new CompletedTask(task.getId(), result, task.getMethod());
            logger.info(TASK_COMPLETED, task.getId(), result);
            return completedTask;

        } catch (Exception e) {
            logger.error(ERROR_EXECUTING_TASK, task.getId(), e);
            return null;
        }
    }
}
