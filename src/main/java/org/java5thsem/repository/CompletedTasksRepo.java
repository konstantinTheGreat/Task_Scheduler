package org.java5thsem.repository;

import org.java5thsem.entity.CompletedTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.java5thsem.constant.Constants.*;

public class CompletedTasksRepo {

    private static final Logger logger = LoggerFactory.getLogger(CompletedTasksRepo.class);
    private static CompletedTasksRepo instance;
    private final List<CompletedTask> tasks;

    private CompletedTasksRepo() {
        tasks = new ArrayList<>();
    }

    public static synchronized CompletedTasksRepo getInstance() {
        if (instance == null) {
            instance = new CompletedTasksRepo();
            logger.info(REPO_INSTANCE);
        }
        return instance;
    }

    public void addTask(CompletedTask task) {
        tasks.add(task);
        logger.info(COMPLETED_TASK_ID_ADDED, task.getId());
    }

    public void removeTask(CompletedTask task) {
        if (tasks.remove(task)) {
            logger.info(COMPLETED_TASK_ID_REMOVED, task.getId());
        } else {
            logger.warn(ERROR_COMP_TASK_ID, task.getId());
        }
    }

    public List<CompletedTask> getTasks() {
        logger.info(ALL_COMPLETED_TASKS, tasks.size());
        return new ArrayList<>(tasks); // Return a copy to avoid external modification
    }

    public void setTasks(List<CompletedTask> newTasks) {
        tasks.clear();
        tasks.addAll(newTasks);
        logger.info(COMPLETED_TASKS_UPDATED, tasks.size());
    }

    public CompletedTask getTask(int index) {
        try {
            CompletedTask task = tasks.get(index);
            logger.info(GET_COMPLETED_TASK_ID, task.getId(), index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            logger.error(INVALID_INDEX, index, e);
            return null;
        }
    }

    public void removeAllTasks() {
        tasks.clear();
        logger.info(ALL_COMPLETED_TASK_ID_REMOVED);
    }
}
