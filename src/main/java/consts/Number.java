package consts;

import java.lang.annotation.Native;
import java.math.BigDecimal;

public class Number {
    /**
     * A constant holding the maximum value an {@code int} can
     * have, 2<sup>31</sup>-1.
     */
    @Native
    public static final int   MAX_VALUE = 0x7fffffff;


    public static void main(String[] args){

        double dd = Number.MAX_VALUE / 4;
        System.out.println(dd);
        BigDecimal d = BigDecimal.valueOf(Number.MAX_VALUE / 4);

        System.out.println(d);

    }
}
