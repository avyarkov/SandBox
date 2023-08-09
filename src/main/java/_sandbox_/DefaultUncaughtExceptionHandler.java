package _sandbox_;

public class DefaultUncaughtExceptionHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> System.out.println(e.getMessage()));

        @SuppressWarnings({"divzero", "NumericOverflow", "unused"})
        int x = 1 / 0;
    }
}
