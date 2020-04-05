package Sem3;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyExecutorTest  {
    @Test
    public void one_task_chek() {
        MyExecutor executor = new MyExecutor(1);
        CountDownLatch latch = new CountDownLatch(1);
        executor.execute(() -> latch.countDown());
        try {
            latch.await(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, latch.getCount());
    }

    @Test
    public void n_tasks_chek() {
        int n = 100;
        MyExecutor executor = new MyExecutor(n);
        CountDownLatch latch = new CountDownLatch(n);
        for (int i = 0; i < n; i++)
        executor.execute(() -> latch.countDown());
        try {
            latch.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, latch.getCount());
    }

    @Test
    public void more_n_tasks_chek() {
        int pool = 5;
        int threads = 15;

        MyExecutor executor = new MyExecutor(pool);
        CountDownLatch latch = new CountDownLatch(threads);
        Object obj = new Object();

        for (int i = 0; i < threads; i++) {
            executor.execute(() -> {
                latch.countDown();

                //после проверки того, что экзекютор не превышает pool задач, на больше не нужно останавливать потоки
                if (latch.getCount() < threads - pool)
                    return;

                //останавливаем первые 5 потоков, чтобы убедится, что экзекютор не выполняет больше задач
                synchronized (obj) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //проверяем, что пул задач непревышен
        Assert.assertEquals(threads - pool,  latch.getCount());
        synchronized (obj) {
            //позваляем первым 5 потокам завершиться
            obj.notify();
        }

        try {
            latch.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertEquals(0, latch.getCount());
    }
}
