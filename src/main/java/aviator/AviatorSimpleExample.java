package aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Feature;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBigInt;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//https://www.cnblogs.com/cristin/p/8496665.html
//https://gitee.com/zhouzach/aviator/blob/aviator-5.2.1/src/test/java/com/googlecode/aviator/AviatorEvaluatorUnitTest.java
public class AviatorSimpleExample {
    public static void main(String[] args) {
//        AviatorEvaluator.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.EVAL);
        AviatorEvaluator.setOption(Options.FEATURE_SET, Feature.getFullFeatures());
        AviatorEvaluator.setOption(Options.MATH_CONTEXT, new MathContext(5, RoundingMode.UP));
        Object rt = AviatorEvaluator.exec("100.356M * 3");
//        System.out.println(rt);



//        exeMin();

//        exeReg();
//        listEqual();
//        strEqual();
//        exeContain();
        exeNoContain();
    }

    public static void exeContain(){

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("s1", "hello");
        env.put("s2", "he");
        Boolean result = (Boolean) AviatorEvaluator.execute("string.contains(s1,s2)", env);

        System.out.println(result); // killme2008
    }

    public static void exeNoContain(){

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("s1", "hello");
        env.put("s2_no_contains", "hek");
        Boolean result = !(Boolean) AviatorEvaluator.execute("string.contains(s1,s2_no_contains)", env);

        System.out.println(result); // killme2008
    }

    public static void listEqual(){
        Map<String, Object> env = new HashMap<String, Object>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(20);
        list.add(10);
        env.put("list", list);
        env.put("value",3);
        Object result = AviatorEvaluator.execute("count(list)", env);
        System.out.println(result);  // 3
        result = AviatorEvaluator.execute("reduce(list,+,0)", env);
        System.out.println(result);  // 33
        result = AviatorEvaluator.execute("filter(list,seq.gt(9))", env);
        System.out.println(result);  // [10, 20]
        result = AviatorEvaluator.execute("include(list,10)", env);
        System.out.println(result);  // true
        result = AviatorEvaluator.execute("sort(list)", env);
        System.out.println(result);  // [3, 10, 20]
        AviatorEvaluator.execute("map(list,println)", env);

    }
    public static void exeReg(){
        String email = "killme2008@gmail.com";

        Map<String, Object> env = new HashMap<String, Object>();

        env.put("email", email);

        String username = (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env);

        System.out.println(username); // killme2008
    }

    public static void exeRegIp(){
        String email = "55.3.244.1 GET /index.html 15824 0.043";

        Map<String, Object> env = new HashMap<String, Object>();

        env.put("email", email);

        String username = (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env);

        System.out.println(username); // killme2008
    }

    public static void exeMin(){
//        AviatorEvaluator.addFunction(new MinFunction());
        String expression = "min(a,b)";
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
//        Double result = (Double) compiledExp.execute(env);
        Object result = compiledExp.execute(env);
        System.out.println(result);
    }

    static class MinFunction extends AbstractFunction {
        @Override public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorBigInt(Math.min(left.doubleValue(), right.doubleValue()));
        }

        public String getName() {
            return "min";
        }
    }

    public static void strEqual(){
        AviatorEvaluator.addFunction(new strEqualFunction());
        String expression = "streq(a,b)";
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        Map<String, Object> env = new HashMap<>();
        env.put("a", "cpu");
        env.put("b", "cpu1");
        Object result = compiledExp.execute(env);
//        Object result = compiledExp.execute(env);
        System.out.println(result);
    }

    static class strEqualFunction extends AbstractFunction {
        @Override public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
            String left = FunctionUtils.getStringValue(arg1, env);
            String right = FunctionUtils.getStringValue(arg2, env);
            return new AviatorString(String.valueOf(left.equals(right)));
//            return new AviatorBigInt(Math.min(left.doubleValue(), right.doubleValue()));
        }

        public String getName() {
            return "streq";
        }
    }

}
