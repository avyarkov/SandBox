package _sandbox_;

public class DefaultInterfaceMethodInvocation {
    interface Interface {
        default void method() {
            System.out.println("method");
        }
    }

    static class Clazz implements Interface {
        @Override
        public void method() {
            Interface.super.method();
        }
    }

    public static void main(String[] args) {
        Clazz a = new Clazz();
        a.method();
    }
}
