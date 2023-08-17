package _sandbox_;

import java.util.Collection;
import java.util.List;

public class GenericsRawUsePreGenericsBehaviour {
    @SuppressWarnings("unused")
    private static class SomeType<T> {
        private <E> void test(Collection<E> collection) {
            for (E e : collection) {
                System.out.println(e);
            }
        }

        private void test(List<Integer> integerList) {
            for (Integer integer : integerList) {
                System.out.println(integer);
            }
        }
    }

    public static void main(String[] args) {
        try {
            runRaw();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        System.out.println("-------");

        run();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void runRaw() {
        SomeType someType = new SomeType();
        List<String> stringList = List.of("hello", "wolrd");
        someType.test(stringList);
    }

    private static void run() {
        SomeType<?> someType = new SomeType<>();
        List<String> stringList = List.of("hello", "wolrd");
        someType.test(stringList);
    }
}
