package _sandbox_;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalSetScale {
    public static void main(String[] args) {
        double d = 1.0 * 123456 / 100000;
        System.out.println(d);
        BigDecimal bd = new BigDecimal(d);
        System.out.println(bd);
        System.out.println(bd.setScale(3, RoundingMode.HALF_UP));
    }
}
