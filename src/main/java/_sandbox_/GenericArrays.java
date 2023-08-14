package _sandbox_;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.IntFunction;

public class GenericArrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                firstAndLast(String[]::new, "a", "b", "c")));
        System.out.println(Arrays.toString(
                firstAndLastWithReflection("a", "b", "c")));

        //noinspection unchecked
        Optional<String>[] arrayOfParameterizedTypes = (Optional<String>[]) new Optional<?>[1];
        arrayOfParameterizedTypes[0] = Optional.of("hello");
        System.out.println(arrayOfParameterizedTypes[0]);
    }

    @SafeVarargs
    private static <T> T[] firstAndLast(IntFunction<T[]> constructor, T... a) {
        T[] res = constructor.apply(2);
        res[0] = a[0];
        res[1] = a[a.length - 1];
        return res;
    }

    @SafeVarargs
    private static <T> T[] firstAndLastWithReflection(T... a) {
        //noinspection unchecked
        T[] res = (T[]) Array.newInstance(a.getClass().getComponentType(), 2);
        res[0] = a[0];
        res[1] = a[a.length - 1];
        return res;
    }
}
