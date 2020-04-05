package Sem3;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyExecutor implements Executor {
    PriorityBlockingQueue<Thread> queue = new PriorityBlockingQueue(1, new ThreadComparator());
    private int poolSize;
    private Thread executorThread;
    private AtomicInteger threads = new AtomicInteger(0);
    Object syncObject;

    public MyExecutor(int poolSize) {
        this.poolSize = poolSize;
        syncObject = new Object();
        executorThread = new Thread(() -> Process());
        executorThread.start();
    }

    @Override
    public void execute(Runnable runnable) {
        Thread thread = CreateThread(runnable);
        queue.add(thread);
    }

    private Thread CreateThread(Runnable runnable) {
        return new Thread(() -> {
            runnable.run();
            threads.getAndDecrement();
            synchronized(syncObject) {
                syncObject.notify();
            }
        });
    }

    private void Process() {
        while (true) {
            try {
                CheckThreads();
                Thread thread = queue.take();
                threads.getAndIncrement();
                thread.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void CheckThreads() {
        synchronized (syncObject) {
            while (threads.get() >= poolSize) {
                try {
                    syncObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ThreadComparator implements Comparator<Thread> {
    @Override
    public int compare(Thread t1, Thread t2) {
        return 0;
    }
}