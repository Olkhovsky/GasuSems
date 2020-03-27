package Sem2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Singleton {
    private static AtomicBoolean isExist = new AtomicBoolean(false);
    private static AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private Singleton() {
        isExist.set(true);
        id =  idCounter.get();
        idCounter.set(id + 1);
    }

    public static Singleton Create() {
            if (isExist.get())
                return null;
            return new Singleton();
    }

    public int GetId() {
        return id;
    }
}
