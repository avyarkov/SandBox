package _sandbox_;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.LongStream;

@SuppressWarnings("ALL")
public class LambdaSandbox {
    public static void main(String[] args) {
        int v = 10;
        Function<Integer, Integer> add = x -> {
            int w = v;
            x += w;
            w += 5;
            x += w;
            return x;
        };
        System.out.println(add.apply(0));

        BiFunction<Long, Long, Long> fun =
            (a, b) -> LongStream.range(a, b + 1).reduce((x, y) -> x * y).getAsLong();

        Function<List, List> fun2 = list -> new ArrayList(new HashSet(list));
    }
}
