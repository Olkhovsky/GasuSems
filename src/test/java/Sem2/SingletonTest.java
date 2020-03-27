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
        ExecutorService executor = Executors.newFixedThreadPool(100);
        ConcurrentSkipListSet<Singleton> singletons = new ConcurrentSkipListSet<>();
        for (int i = 0; i < 100; i++)
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Singleton singleton = Singleton.Create();
                if (singleton != null)
                    singletons.add(singleton);
            }
        });
        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.SECONDS);
        Assert.assertEquals(singletons.size(),1);
    }
}
