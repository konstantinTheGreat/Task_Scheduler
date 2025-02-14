package org.java5thsem.thread;

import org.java5thsem.entity.CompletedTask;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TaskExecutorThread extends Thread {
    private final FutureTask<CompletedTask> futureTask;

    public TaskExecutorThread(Callable<CompletedTask> task) {
        this.futureTask = new FutureTask<>(task);
    }

    @Override
    public void run() {
        futureTask.run();
    }

    public CompletedTask getResult() throws Exception {
        return futureTask.get();
    }
}
