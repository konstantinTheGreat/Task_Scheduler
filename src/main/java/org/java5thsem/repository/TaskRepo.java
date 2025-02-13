package org.java5thsem.repository;

import org.java5thsem.entity.ScheduledTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.java5thsem.constant.Constants.*;

public class TaskRepo {

    private static final Logger logger = LoggerFactory.getLogger(TaskRepo.class);
    private static TaskRepo instance;
    private final List<ScheduledTask> tasks;

    private TaskRepo() {
        tasks = new ArrayList<>();
    }

    public static synchronized TaskRepo getInstance() {
        if (instance == null) {
            instance = new TaskRepo();
            logger.info(TASK_REPO_INSTANCE);
        }
        return instance;
    }

    public void addTask(ScheduledTask task) {
        tasks.add(task);
        logger.info(TASK_ID_ADDED, task.getId());
    }

    public void removeTask(ScheduledTask task) {
        if (tasks.remove(task)) {
            logger.info(TASK_ID_REMOVED, task.getId());
        } else {
            logger.warn(NON_EXISTENT_TASK, task.getId());
        }
    }

    public List<ScheduledTask> getTasks() {
        logger.info(ALL_TASKS, tasks.size());
        return new ArrayList<>(tasks);
    }

    public void setTasks(List<ScheduledTask> newTasks) {
        tasks.clear();
        tasks.addAll(newTasks);
        logger.info(TASK_UPDATED, tasks.size());
    }

    public ScheduledTask getTask(int index) {
        try {
            ScheduledTask task = tasks.get(index);
            logger.info(TASK_ID_RETRIEVED, task.getId(), index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            logger.error(INVALID_INDEX, index, e);
            return null;
        }
    }

    public void removeAllTasks() {
        tasks.clear();
        logger.info(ALL_TASKS_REMOVED);
    }
}
