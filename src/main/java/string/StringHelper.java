package string;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class StringHelper {

    public static void main(String[] args){

        decimalFormat();


    }

    public static void decimalFormat(){
        String num = "123456.789";

        int i = num.indexOf(".");
        System.out.println(i);
        int j = num.indexOf(",");
        if(-1 == i){
            System.out.println(true);
        }
        System.out.println(j);

        String[] intNum = num.split("\\.");
        System.out.println(Arrays.toString(intNum));

        long l =Long.parseLong(intNum[0]);
        System.out.println(l);

        DecimalFormat df2 = new DecimalFormat("#");
        double input = 1234567.1;
//        System.out.println("double : " + input);
//        System.out.println("double : " + String.format("%.2f", input));
        System.out.println("double : " + input);
        System.out.println(" default double : " + df2.format(input));

        // DecimalFormat, default is RoundingMode.HALF_EVEN
        df2.setRoundingMode(RoundingMode.DOWN);
        System.out.println("\n down double : " + Double.parseDouble(df2.format(input)));
        System.out.println("down double : " + Long.parseLong(df2.format(input)));

        df2.setRoundingMode(RoundingMode.UP);
        System.out.println("up double : " + df2.format(input));

    }
}
