package _sandbox_;

@SuppressWarnings("unused")
public class DiamondProblemWithDefaultInterfaceMethods {
    interface InterfaceA {
        void method();
    }
    interface InterfaceB extends InterfaceA {
        default void method() {
            System.out.println("method in interface B");
        }
    }
    interface InterfaceC extends InterfaceA {
        default void method() {
            System.out.println("method in interface C");
        }
    }
    /*
    interface InterfaceD extends InterfaceB, InterfaceC {
    }
    entails error:
    InterfaceD inherits unrelated defaults for method() from types InterfaceB and InterfaceC
     */

    // solution: implement method explicitly
    interface InterfaceD extends InterfaceB, InterfaceC {
        @Override
        default void method() {
            InterfaceB.super.method();
        }
    }
}
