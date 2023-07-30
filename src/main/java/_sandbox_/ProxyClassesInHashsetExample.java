package _sandbox_;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Stream;

class ProxyClassesInHashsetExample {

    public static void main(String[] args) {
        run(new String[]{"one", "two", "three", "four", "five", "six", "seven"});
        // run(generateStrings(10));
    }

    public static void run(String[] strings) {
        Object[] proxys = Arrays.stream(strings)
                .map(string -> Proxy.newProxyInstance(null, new Class[0], new TracingHandler(string)))
                .toArray();

        HashSet<Object> hashSet = new HashSet<>(Arrays.asList(proxys));

        System.out.println(hashSet);
    }

    private static String[] generateStrings(@SuppressWarnings("SameParameterValue") int size) {
        return Stream.generate(() -> UUID.randomUUID().toString()).limit(size).toArray(String[]::new);
    }

    @SuppressWarnings("ClassCanBeRecord")
    private static class TracingHandler implements InvocationHandler {
        private final String target;

        public TracingHandler(String target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(target + "." + method.getName());
            return method.invoke(target, args);
        }
    }

}
