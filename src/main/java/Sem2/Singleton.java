package Sem2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Singleton {
    private static AtomicReference<Singleton> instance = new AtomicReference<>();
    private static AtomicInteger idCounter = new AtomicInteger(0);
    private int id;

    private Singleton() {
        id =  idCounter.get();
        idCounter.set(id + 1);
    }

    public static Singleton GetInstanse() {
        Singleton obj = instance.get();
        if (obj != null)
            return obj;
        return Create();
    }

    public int GetId() {
        return id;
    }

    private static Singleton Create() {
        Singleton obj = new Singleton();
        instance.set(obj);
        return obj;
    }
}
