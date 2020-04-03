package Sem3;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyExecutor implements Executor {
    PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue(1,new ExecutorComparator());
    private int pullSize;

    public MyExecutor(int pullSize) {
        this.pullSize = pullSize;
    }

    @Override
    public void execute(Runnable runnable) {
        queue.put(runnable);
        Run();
    }

    private void Run() {
        if (queue.size() < pullSize)
            return;
        for (int i = 0; i < pullSize; i++) {
            try {
                queue.poll(100, TimeUnit.MILLISECONDS).run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ExecutorComparator implements Comparator<Runnable> {
    @Override
    public int compare(Runnable r1, Runnable r2) {
        return 0;
    }
}