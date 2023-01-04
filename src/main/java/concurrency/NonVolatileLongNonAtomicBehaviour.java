package concurrency;// I couldn't get the non-atomic behaviour on my computer, probably because it is x64.
// Maybe try to run in on a x32 computer if I ever have a chance...

public class NonVolatileLongNonAtomicBehaviour {
    static final long LEFT_HALF_ONE = 1L << 32;
    static final long RIGHT_HALF_ONE = 1L;
    static final long UNEXPECTED_VALUE = LEFT_HALF_ONE + RIGHT_HALF_ONE;

    static long longVariable = 0;

    static class SetterRunnable implements Runnable {
        @Override
        public void run() {
            int iteration = 0;
            while (!Thread.interrupted()) {
                System.out.print("setter ");
                if (iteration++ % 2 == 0) {
                    longVariable = LEFT_HALF_ONE;
                } else {
                    longVariable = RIGHT_HALF_ONE;
                }
            }
        }
    }

    static class GetterRunnable implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                System.out.print("GETTER ");
                if (longVariable == UNEXPECTED_VALUE) {
                    System.out.println("\n!!!\nUnexpected long value found!\n!!!\n");
                    System.exit(1);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread setterThread = new Thread(new SetterRunnable());
        Thread getterThread = new Thread(new GetterRunnable());
        setterThread.start();
        getterThread.start();
        Thread.sleep(1000);
        setterThread.interrupt();
        getterThread.interrupt();
    }
}
