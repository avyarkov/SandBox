package _sandbox_;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntFunction;

public class ArrayAsObject {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        Integer[] b = {1, 2, 3};
        System.out.println(a.getClass());
        System.out.println(b.getClass());
        System.out.println(a.hashCode());
        System.out.println(Arrays.hashCode(a));
        System.out.println(b.hashCode());
        System.out.println(Arrays.hashCode(b));
        System.out.println(Objects.hash(1, 2, 3));

        System.out.println("~~~~~~~");
        runIntArrayAsParameterizedTypeExample();
        System.out.println("~~~~~~~");
        runArrayStoreExceptionExample();
    }

    public static void runIntArrayAsParameterizedTypeExample() {
        IntFunction<int[]> f = int[]::new;
        var a = f.apply(4);
        System.out.println(Arrays.toString(a));
    }

    @SuppressWarnings("ALL")
    public static void runArrayStoreExceptionExample() {
        Integer[] a = {1, 2, 3};
        Number[] b = a;
        System.out.println(a.getClass() + " " + b.getClass());

        Double d = 1.5;

        // Throws ArrayStoreException
        b[0] = d;
    }
}
