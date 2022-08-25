package script;

import javax.script.*;


//https://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/#helloworld
public class MultiScopes {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        engine.put("x", "hello");
        // print global variable "x"
        engine.eval("print(x);");
        // the above line prints "hello"

        // Now, pass a different script context
        ScriptContext newContext = new SimpleScriptContext();
        Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);

        // add new variable "x" to the new engineScope
        engineScope.put("x", "world");

        // execute the same script - but this time pass a different script context
        engine.eval("print(x);", newContext);

        // the above line prints "world"
    }
}
