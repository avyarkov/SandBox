package _sandbox_;

import java.util.Arrays;
import java.util.Objects;

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
    }
}
