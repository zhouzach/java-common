package script;

import lombok.Data;
import utils.JsonUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

//https://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/#helloworld
public class PrintScript {
    public static void main(String[] args) throws Exception {
        Row row = new Row("sysA",1656918202474L);
        Map<String, Object> map = new HashMap<>();
        map.put("sys", "linux");
        map.put("myTime", 12);
//        System.out.println(row);

        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");


        engine.put("data", JsonUtils.writeValueAsString(map));
        engine.eval("print(data)");
        engine.eval("print('===============')");
        engine.eval("var e =JSON.parse(data);print(JSON.stringify(e))");
        Object object = engine.get("e");
        Map stringObjectMap = (Map)object;
        stringObjectMap.forEach((k,v)->{
            System.out.println("stringObjectMapK>>>>>>>>>>>>>>>>>>>>>>>"+k);
            System.out.println("stringObjectMapV>>>>>>>>>>>>>>>>>>>>>>"+v);
        });
        engine.eval("print(e.myTime)");

        // evaluate JavaScript code from String
//        engine.eval("var threshold = { sysA: 100, sysB: 200,a:e };print(threshold[e.sys]);");
//        engine.eval("var threshold = { sysA: 100, sysB: 200,a:e };print(e.duration < threshold[e.sys]);");
        engine.eval("var threshold = { sysA: 100, sysB: 200,a:e };e.response = e.duration < threshold[e.sys];print(e.response)");
        engine.eval("print(JSON.stringify(e))");
//        engine.eval("e[e.sys]=e.duration");
        engine.eval("e = [{a:e.sys},{b:2}];");
        Object eval = engine.eval("JSON.stringify(e)");
        System.out.println(eval);
    }

    @Data
    static class Row {
        private String sys;
        private Long duration;

        public Row(){}
        public Row(String sys, Long d){
            this.sys = sys;
            this.duration = d;
        }

        @Override
        public String toString() {
            return "Row{" +
                    "sys='" + sys + '\'' +
                    ", duration=" + duration +
                    '}';
        }
    }
}
