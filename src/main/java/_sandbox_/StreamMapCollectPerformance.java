package _sandbox_;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMapCollectPerformance {
    static final int NUMBER_OF_ELEMENTS = 10_000_000;

    static class DataPiece {
        int x;

        public DataPiece(int x) {
            this.x = x;
        }

        public static DataPiece tripled(DataPiece dataPiece) {
            return new DataPiece(dataPiece.x * 3);
        }
    }

    static List<DataPiece> initialList = new ArrayList<>(NUMBER_OF_ELEMENTS);

    static {
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            initialList.add(new DataPiece(i));
        }
    }

    static class StreamCollectRunnable implements Runnable {
        @Override
        public void run() {
            //noinspection unused,SimplifyStreamApiCallChains
            var list = initialList.stream()
                    .map(DataPiece::tripled)
                    .collect(Collectors.toList());
        }
    }

    static class StreamToListRunnable implements Runnable {
        @Override
        public void run() {
            //noinspection unused
            var list = initialList.stream()
                    .map(DataPiece::tripled)
                    .toList();
        }
    }

    static class ListRunnable implements Runnable {
        @Override
        public void run() {
            //noinspection MismatchedQueryAndUpdateOfCollection
            var list = new ArrayList<DataPiece>(initialList.size());
            for (var dataPiece : initialList) {
                list.add(DataPiece.tripled(dataPiece));
            }
        }
    }

    static long measure(Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        long finish = System.nanoTime();
        return finish - start;
    }

    public static void main(String[] args) throws InterruptedException {
        long streamCollectorTime = 0;
        long streamToListTime = 0;
        long listTime = 0;

        for (int i = 0; i < 100; i++) {
            streamCollectorTime += measure(new StreamCollectRunnable());
            streamToListTime += measure(new StreamToListRunnable());
            listTime += measure(new ListRunnable());
        }

        System.out.println("streamCollectorTime: " + streamCollectorTime);
        System.out.println("streamToListTime: " + streamToListTime);
        System.out.println("listTime: " + listTime);

        // results
        // streamCollectorTime: 53944397100
        // streamToListTime: 39490938100
        // listTime: 33742458100
    }
}
