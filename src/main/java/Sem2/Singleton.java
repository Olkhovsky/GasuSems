package Sem2;

import java.util.concurrent.atomic.AtomicInteger;

public class Singleton implements Comparable<Singleton> {
    private static boolean isExist;
    private static Object lock = new Object();

    private Singleton() {
        isExist = true;

    }

    public static Singleton Create() {
        synchronized (lock) {
            if (isExist)
                return null;
            return new Singleton();
        }
    }

    @Override
    public int compareTo(Singleton singleton) {
        return 0;
    }
}
