package consts;

import java.lang.annotation.Native;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Number {
    /**
     * A constant holding the maximum value an {@code int} can
     * have, 2<sup>31</sup>-1.
     */
    @Native
    public static final int   MAX_VALUE = 0x7fffffff;


    public static void max(final String str) {
        double dd = Number.MAX_VALUE / 4;
        System.out.println(dd);
        BigDecimal d = BigDecimal.valueOf(Number.MAX_VALUE / 4);

        System.out.println(d);
    }

    public static void main(String[] args){

        boolean b =isNumeric("2000000000000");
        System.out.println(b);
    }

    public static boolean isNumeric(final String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
