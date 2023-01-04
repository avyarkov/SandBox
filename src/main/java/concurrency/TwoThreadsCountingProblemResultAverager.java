package concurrency;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

public class TwoThreadsCountingProblemResultAverager {
    public static void main(String[] args) throws InterruptedException {
        var realOutputStream = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        int numberOfAttempts = 10;
        for (int i = 0; i < numberOfAttempts; i++) {
            TwoThreadsCountingProblem.main(new String[]{""});
        }

        String result = byteArrayOutputStream.toString();
        String[] results = result.split("\n");
        var filteredResults = IntStream.range(0, 9 * numberOfAttempts)
            .filter(i -> i % 3 == 2)
            .mapToObj(i -> results[i])
            .toArray(String[]::new);

        int atomicIntegerTotal = 0, synchronizedTotal = 0, volatileIntegerWithAtomicFieldUpdaterTotal = 0;
        for (int i = 0; i < numberOfAttempts; i++) {
            atomicIntegerTotal += Integer.parseInt(filteredResults[3 * i].split(" ")[0]);
            synchronizedTotal += Integer.parseInt(filteredResults[3 * i + 1].split(" ")[0]);
            volatileIntegerWithAtomicFieldUpdaterTotal += Integer.parseInt(filteredResults[3 * i + 2].split(" ")[0]);
        }

        int atomicIntegerAverage = atomicIntegerTotal / numberOfAttempts;
        int synchronizedAverage = synchronizedTotal / numberOfAttempts;
        int volatileIntegerWithAtomicFieldUpdaterAverage = volatileIntegerWithAtomicFieldUpdaterTotal / numberOfAttempts;

        for (int i = 0; i < filteredResults.length; i++) {
            realOutputStream.println(filteredResults[i]);
            if (i % 3 == 2) realOutputStream.println("-------");
        }
        realOutputStream.println("atomicIntegerAverage = " + atomicIntegerAverage);
        realOutputStream.println("synchronizedAverage = " + synchronizedAverage);
        realOutputStream.println("volatileIntegerWithAtomicFieldUpdaterAverage = " + volatileIntegerWithAtomicFieldUpdaterAverage);
    }
}
