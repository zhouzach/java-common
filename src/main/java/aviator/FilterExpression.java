package aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

public class FilterExpression {
    public static void main(String[] args) {

       numberFilter2();
    }


    public static void numberFilter(){
        /**
         * ==和!=作用于Number之间、String之间、Pattern之间、变量之间、其他类型与nil之间以及String和java.util.Date之间，
         * =~仅能作用于String和Pattern之间
         */
        String expression = "a == 12";
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 12);
        Boolean result = (Boolean)compiledExp.execute(env);
        System.out.println(result);

    }

    public static void numberFilter2(){
        /**
         * ==和!=作用于Number之间、String之间、Pattern之间、变量之间、其他类型与nil之间以及String和java.util.Date之间，
         * =~仅能作用于String和Pattern之间
         */
        String expression = "a >= 12 && b == 'disk'";

        Map<String, Object> env = new HashMap<>();
        env.put("a", 20);
        env.put("b","disk");
        env.put("s1", "hello");
//        Boolean result = (Boolean)AviatorEvaluator.execute(expression, env);
//        System.out.println(result);


//        Map<String, Object> env1 = new HashMap<String, Object>();
        env.put("s2", "he");
        Boolean result1 = (Boolean) AviatorEvaluator.execute("string.contains(s1,s2)", env);

        Boolean res = (Boolean)AviatorEvaluator.execute(expression, env) && result1;
        System.out.println(res);



    }

    public static void strFilter(){
        /**
         * ==和!=作用于Number之间、String之间、Pattern之间、变量之间、其他类型与nil之间以及String和java.util.Date之间，
         * =~仅能作用于String和Pattern之间
         */
        String expression = "name.e";
//        expression.equals()
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        Map<String, Object> env = new HashMap<>();
        env.put("name", "lily");
        Object result = compiledExp.execute(env);
        System.out.println(result);

    }

}
