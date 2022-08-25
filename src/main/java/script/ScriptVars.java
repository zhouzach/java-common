package script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;


//https://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/#helloworld
public class ScriptVars {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        File f = new File("/Users/Zach/java-common/push.sh");
        // expose File object as variable to script
        engine.put("file", f);

        // evaluate a script string. The script accesses "file"
        // variable and calls method on it
        engine.eval("print(file.getAbsolutePath())");
    }
}
