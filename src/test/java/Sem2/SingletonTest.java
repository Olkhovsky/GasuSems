package Sem2;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.*;

public class SingletonTest {
    @Test
    public void OneHundredTimesBefore() throws InterruptedException {
        int threadsSize = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(threadsSize);
        ConcurrentSkipListSet<Integer> ids = new ConcurrentSkipListSet<>();
        CountDownLatch start = new CountDownLatch(threadsSize);
        CountDownLatch exit = new CountDownLatch(threadsSize);
        for (int i = 0; i < threadsSize; i++)
        executor.execute(new Runnable() {
            @Override
            public void run() {
                start.countDown();
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Singleton singleton = Singleton.GetInstanse();
                ids.add(singleton.GetId());
                exit.countDown();
            }
        });
        exit.await();
        Assert.assertEquals(1, ids.size());
    }
}
