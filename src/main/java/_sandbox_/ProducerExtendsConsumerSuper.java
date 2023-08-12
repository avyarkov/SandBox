package _sandbox_;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
class ProducerExtendsConsumerSuper {
    private static class Class1 {}
    private static class Class2 extends Class1 {}
    private static class Class3 extends Class2 {}

    @SuppressWarnings("RedundantTypeArguments")
    public static void main(String[] args) {
        List<? extends Class2> pe = List.<Class3>of(new Class3());
        // pe.add(new Class3());
        // java: incompatible types: _sandbox_.ProducerExtendsConsumerSuper.Class3 cannot be converted to capture#1 of ? extends _sandbox_.ProducerExtendsConsumerSuper.Class2
        Class2 class2 = pe.get(0);
        Class1 class1 = pe.get(0);

        List<? super Class2> cs = new ArrayList<>(List.<Class1>of(new Class1()));
        // Class2 c = cs.get(0);
        // java: incompatible types: capture#1 of ? super _sandbox_.ProducerExtendsConsumerSuper.Class2 cannot be converted to _sandbox_.ProducerExtendsConsumerSuper.Class2
        Object object = cs.get(0);
        cs.add(new Class2());
        cs.add(new Class3());

        Optional<? super Class2> optional = Optional.<Class1>of(new Class1());
        // Class2 class21 = optional.get();
        // java: incompatible types: capture#1 of ? super _sandbox_.ProducerExtendsConsumerSuper.Class2 cannot be converted to _sandbox_.ProducerExtendsConsumerSuper.Class2
        Object object1 = optional.get();

        runAnotherOddity();
    }

    public static void runAnotherOddity() {
        // double d = getMiddle(1, 2.3);
        // java: incompatible types: inferred type does not conform to upper bound(s)
        //    inferred: java.lang.Number&java.lang.Comparable<? extends java.lang.Number&java.lang.Comparable<?>&java.lang.constant.Constable&java.lang.constant.ConstantDesc>&java.lang.constant.Constable&java.lang.constant.ConstantDesc
        //    upper bound(s): java.lang.Double,java.lang.Object
        Number d = getMiddle(1, 2.3);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}
