package concurrency;

import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class LongAdderTest {
    public static void main(String[] args) throws InterruptedException {
        class MutableLong {
            public long v;
        }
        var mutableLong = new MutableLong();
        var threads = IntStream.range(0, 10)
                .mapToObj(i -> new Thread(
                        () -> {
                            for (int j = 0; j < 1_000_000; j++) {
                                mutableLong.v += 1;
                            }
                        }))
                .peek(Thread::start)
                .toArray(Thread[]::new);
        for (var thread : threads) {
            thread.join();
        }
        System.out.println(mutableLong.v);

        var longAdder = new LongAdder();
        var threads2 = IntStream.range(0, 10)
                .mapToObj(i -> new Thread(
                        () -> {
                            for (int j = 0; j < 1_000_000; j++) {
                                longAdder.increment();
                            }
                        }))
                .peek(Thread::start)
                .toArray(Thread[]::new);
        for (var thread : threads2) {
            thread.join();
        }
        System.out.println(longAdder.sum());
    }
}
