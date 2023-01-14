package concurrency;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@SuppressWarnings("DuplicatedCode")
public class TwoThreadsCountingProblem {
    static final int LIMIT = 100_000_000;

    static class CountingRunnableWithAtomicInteger implements Runnable {
        AtomicInteger count = new AtomicInteger(0);

        @Override
        public void run() {
            for (int i = 0; i < LIMIT; i++) {
                count.incrementAndGet();
            }
        }
    }

    static class CountingRunnableWithSynchronized implements Runnable {
        int count = 0;

        @Override
        public void run() {
            for (int i = 0; i < LIMIT; i++) {
                synchronized (this) {
                    count++;
                }
            }
        }
    }

    static class CountingRunnableWithVolatileIntegerAndAtomicFieldUpdater implements Runnable {
        volatile int count = 0;
        static final AtomicIntegerFieldUpdater<CountingRunnableWithVolatileIntegerAndAtomicFieldUpdater> updater =
            AtomicIntegerFieldUpdater.newUpdater(CountingRunnableWithVolatileIntegerAndAtomicFieldUpdater.class, "count");

        @Override
        public void run() {
            for (int i = 0; i < LIMIT; i++) {
                updater.incrementAndGet(this);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start, finish;

        System.out.println("With AtomicInteger:");
        start = System.nanoTime();
        var countingRunnableWithAtomicInteger = new CountingRunnableWithAtomicInteger();
        Thread thread1 = new Thread(countingRunnableWithAtomicInteger);
        Thread thread2 = new Thread(countingRunnableWithAtomicInteger);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        finish = System.nanoTime();

        System.out.println(countingRunnableWithAtomicInteger.count);
        System.out.println(Duration.ofNanos(finish - start).toMillis() + " milliseconds");

        System.out.println("With synchronized:");
        start = System.nanoTime();
        var countingRunnableWithSynchronized = new CountingRunnableWithSynchronized();
        Thread thread3 = new Thread(countingRunnableWithSynchronized);
        Thread thread4 = new Thread(countingRunnableWithSynchronized);
        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
        finish = System.nanoTime();

        System.out.println(countingRunnableWithSynchronized.count);
        System.out.println(Duration.ofNanos(finish - start).toMillis() + " milliseconds");

        System.out.println("With volatile int and AtomicIntegerFieldUpdater:");
        start = System.nanoTime();
        var countingRunnableWithVolatileIntegerAndAtomicFieldUpdater = new CountingRunnableWithVolatileIntegerAndAtomicFieldUpdater();
        Thread thread5 = new Thread(countingRunnableWithVolatileIntegerAndAtomicFieldUpdater);
        Thread thread6 = new Thread(countingRunnableWithVolatileIntegerAndAtomicFieldUpdater);
        thread5.start();
        thread6.start();
        thread5.join();
        thread6.join();
        finish = System.nanoTime();

        System.out.println(countingRunnableWithVolatileIntegerAndAtomicFieldUpdater.count);
        System.out.println(Duration.ofNanos(finish - start).toMillis() + " milliseconds");
    }
}
