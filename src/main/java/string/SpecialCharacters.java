package string;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SpecialCharacters {

    /**
     *
     * https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
     *
     * there are 12 characters with special meanings: the backslash \, the caret ^,
     * the dollar sign $, the period or dot ., the vertical bar or pipe symbol |, the question mark ?,
     * the asterisk or star *, the plus sign +, the opening parenthesis (, the closing parenthesis ),
     * and the opening square bracket [, the opening curly brace {,
     * These special characters are often called "metacharacters".
     * @param str
     * @return
     */
    public static String[] split(String str, String separator){
        return str.split(Pattern.quote(separator));
    }

    public static void main(String[] args){
        System.out.println(Arrays.toString(split("db.table", ".")));
    }
}
