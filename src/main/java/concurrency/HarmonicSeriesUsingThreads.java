package concurrency;

public class HarmonicSeriesUsingThreads {
    public static class HarmonicSeries {
        static double sum(int n, int k) {
            double res = 0;
            for (int i = n; i < k; i++) {
                res += 1.0 / (i + 1);
            }
            return res;
        }
    }

    static class MyRunnable implements Runnable {
        int begin, end;
        volatile double result;

        public MyRunnable(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            result = HarmonicSeries.sum(begin, end);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final double NANOS_IN_A_SECOND = 1_000_000_000;
        final int limit = 2_000_000_000;
        long startTime, finishTime;

        startTime = System.nanoTime();
        System.out.println("Result: " + HarmonicSeries.sum(0, limit));
        finishTime = System.nanoTime();
        System.out.println("Time spent: " + (finishTime - startTime) / NANOS_IN_A_SECOND);

        System.out.println("~~~~~~~");
        startTime = System.nanoTime();
        int numberOfThreads = 5;
        int chunk = limit / numberOfThreads;
        MyRunnable[] runnable = new MyRunnable[numberOfThreads];
        Thread[] thread = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            int begin = i * chunk;
            int end = (i != numberOfThreads - 1) ? (i + 1) * chunk : limit;
            runnable[i] = new MyRunnable(begin, end);
            thread[i] = new Thread(runnable[i]);
            thread[i].start();
        }
        double result = 0;
        for (int i = 0; i < numberOfThreads; i++) {
            thread[i].join();
            result += runnable[i].result;
        }
        System.out.println("Result: " + result);
        finishTime = System.nanoTime();
        System.out.println("Time spent: " + (finishTime - startTime) / NANOS_IN_A_SECOND);
    }
}
