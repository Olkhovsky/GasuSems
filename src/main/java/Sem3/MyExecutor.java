package Sem3;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyExecutor implements Executor {
    PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue(1, new RunComparator());
    private int poolSize;
    private int threads = 0;

    public MyExecutor(int poolSize) {
        this.poolSize = poolSize;
    }

    @Override
    public void execute(Runnable runnable) {
        queue.add(runnable);
        CreateThread();
    }

    private void CreateThread() {
        if (threads >= poolSize)
            return;
        threads++;
        new Thread(() -> Process()).start();
    }

    private void Process() {
        while (true) {
            try {
                Runnable task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

class RunComparator implements Comparator<Runnable> {
    @Override
    public int compare(Runnable t1, Runnable t2) {
        return 0;
    }
}