package grok;

import io.krakens.grok.api.Grok;
import io.krakens.grok.api.GrokCompiler;
import io.krakens.grok.api.Match;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrokDemo {

    public static void main(String[] args){

//        simple();

        String log = "192.168.0.2 PUT /logout.html 17820 0.678";
//        System.out.println(regular(log));
//        grokForRegular("");
//        grokForRegular(log);

        String value = "100##abc";
        simpleRegular(value);

    }

    //https://blog.csdn.net/wenqingping5/article/details/118546032
    public static void simple(){
        GrokCompiler grokCompiler = GrokCompiler.newInstance();
        // 进行注册, registerDefaultPatterns()方法注册的是Grok内置的patterns
        grokCompiler.registerDefaultPatterns();
        // 添加自定的正则表达式，解析日期（例如：2021-07-07 13:45:39.210）
        grokCompiler.register("DATETIME","[0-9,\\.\\-: ]+");

        /* 传入自定义的pattern, 会从已注册的patterns里面进行配对,
         * 例如： %{DATETIME:datetime}
         * 配对成功后, 会在match时按照固定的解析格式将解析结果存入map中, 此处datetime作为输出的key
         */
        Grok grok = grokCompiler.compile("%{DATETIME:datetime} %{LOGLEVEL:level} %{INT:pid} --- \\[%{NOTSPACE:thread}\\] %{NOTSPACE:classed}\\s*:%{GREEDYDATA:message}");
        String logMsg = "2021-07-07 13:45:39.210  INFO 16100 --- [SpringContextShutdownHook] com.zaxxer.hikari.HikariDataSource       : Shutdown completed.";
        // 通过match()方法进行匹配, 对log进行解析, 按照指定的格式进行输出
        Match grokMatch = grok.match(logMsg);
        // 获取结果
        Map<String, Object> resultMap = grokMatch.capture();
        System.out.print(resultMap);

    }

    public static void simpleRegular(String value){
        GrokCompiler grokCompiler = GrokCompiler.newInstance();
        // 进行注册, registerDefaultPatterns()方法注册的是Grok内置的patterns
        grokCompiler.registerDefaultPatterns();

        Grok grok = grokCompiler.compile("(^100##)");
//        Grok grok = grokCompiler.compile("(?<myword>^100##)");
        // 通过match()方法进行匹配, 对log进行解析, 按照指定的格式进行输出
        Match grokMatch = grok.match(value);
        // 获取结果
        Map<String, Object> resultMap = grokMatch.capture();
        System.out.print(resultMap);

    }

    public static void grokForRegular(String log){
        GrokCompiler grokCompiler = GrokCompiler.newInstance();
        // 进行注册, registerDefaultPatterns()方法注册的是Grok内置的patterns
        grokCompiler.registerDefaultPatterns();

        /* 传入自定义的pattern, 会从已注册的patterns里面进行配对,
         * 例如： %{DATETIME:datetime}
         * 配对成功后, 会在match时按照固定的解析格式将解析结果存入map中, 此处datetime作为输出的key
         */
//        Grok grok = grokCompiler.compile("%{DATETIME:datetime} %{LOGLEVEL:level} %{INT:pid} --- \\[%{NOTSPACE:thread}\\] %{NOTSPACE:classed}\\s*:%{GREEDYDATA:message}");
        Grok grok = grokCompiler.compile("^\"?(?<client>[^ ]++)(?:[^\\s]*+\\s++){1}(?<method>[^ ]++)(?:[^\\s]*+\\s++){1}(?<request>/\\w++\\.\\w++\\s++)(?<num>[^ ]++)(?:[^\\s]*+\\s++){1}(?<dur>.+)");
        // 通过match()方法进行匹配, 对log进行解析, 按照指定的格式进行输出
        Match grokMatch = grok.match(log);
        // 获取结果
        Map<String, Object> resultMap = grokMatch.capture();
        System.out.print(resultMap);

    }

    public static String regular(String log) {
        Pattern p = Pattern.compile("^\"?(?<client>[^ ]++)(?:[^\\s]*+\\s++){1}(?<method>[^ ]++)(?:[^\\s]*+\\s++){1}(?<request>/\\w++\\.\\w++\\s++)(?<num>[^ ]++)(?:[^\\s]*+\\s++){1}(?<dur>.+)");
        Matcher m = p.matcher(log);
        if(m.find()){
//            return m.group("dur");
//            return m.group("client");
            return m.group();
        } else {
            return "";
        }
    }
}
