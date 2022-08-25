package script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

//https://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/#helloworld
public class InvokeScriptFunction {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        // JavaScript code in a String
        String script = "function hello(name) { print('Hello, ' + name); }";
        // evaluate script
        engine.eval(script);

        // javax.script.Invocable is an optional interface.
        // Check whether your script engine implements or not!
        // Note that the JavaScript engine implements Invocable interface.
        Invocable inv = (Invocable) engine;

        // invoke the global function named "hello"
        inv.invokeFunction("hello", "Scripting!!" );
    }
}
