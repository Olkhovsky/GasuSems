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
}
