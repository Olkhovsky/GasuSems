package Sem2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Singleton {
    private static AtomicReference<Singleton> instance = new AtomicReference<>();
    private static AtomicInteger idCounter = new AtomicInteger(0);
    private AtomicInteger id = new AtomicInteger();

    private Singleton() {
        id.set(idCounter.getAndIncrement());
    }

    public static Singleton GetInstanse() {
        instance.compareAndSet(null, new Singleton());
        return instance.get();
    }

    public int GetId() {
        return id.get();
    }
}
