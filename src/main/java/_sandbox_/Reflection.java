package _sandbox_;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflection {
    public static void main(String[] args) {
        Pair pair = new Pair("name", 239, 566);

        Class<?> clazz = pair.getClass();
        Class<?> clazz2 = Pair.class;
        Class<?> clazz3;
        try {
            clazz3 = Class.forName("_sandbox_.Pair");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(clazz + " ; " + clazz2 + " ; " + clazz3);

        System.out.println("Fields: " + Arrays.toString(clazz.getFields()));
        System.out.println("Declared Fields: " + Arrays.toString(clazz.getDeclaredFields()));

        System.out.println("Methods: " + Arrays.toString(clazz.getMethods()));
        System.out.println("Declared Methods: " + Arrays.toString(clazz.getDeclaredMethods()));

        System.out.println("Constructors: " + Arrays.toString(clazz.getConstructors()));
        System.out.println("Declared Constructors: " + Arrays.toString(clazz.getDeclaredConstructors()));

        System.out.println();

        try {
            Field field = clazz.getDeclaredField("x");
            field.setAccessible(true);

            long x = (long) field.get(pair);
            System.out.println(x);

            field.set(pair, 69);
            System.out.println(pair);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            Method method = clazz.getDeclaredMethod("multiply", int.class);
            method.setAccessible(true);

            Pair pair2 = (Pair) method.invoke(pair, 10);
            System.out.println(pair2);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(long.class, long.class);
            constructor.setAccessible(true);

            Pair pair3 = (Pair) constructor.newInstance(123, 456);
            System.out.println(pair3);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

@SuppressWarnings("unused")
class Pair {
    public static final double PI = 3.14;
    public String name;
    private final long x, y;

    public Pair(String name, long x, long y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    private Pair(long x, long y) {
        this("private_pair", x, y);
    }

    private Pair multiply(int m) {
        return new Pair(name, m * x, m * y);
    }

    @Override
    public String toString() {
        return "Pair{name='" + name + "', x=" + x + ", y=" + y + "}";
    }
}
