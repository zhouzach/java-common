package utils;

import org.junit.jupiter.api.Test;

public class ParameterToolTester {


    @Test
    public void fromArgsTester(){

        String[] args = new String[]{"--port", "9000"};


        final String hostname;
        final int port;
        try {
            final ParameterTool params = ParameterTool.fromArgs(args);
            hostname = params.has("hostname") ? params.get("hostname") : "localhost";
            port = params.getInt("port");
        } catch (Exception e) {
            System.err.println("No port specified. Please run 'SocketWindowWordCount " +
                    "--hostname <hostname> --port <port>', where hostname (localhost by default) " +
                    "and port is the address of the text server");
            System.err.println("To start a simple text server, run 'netcat -l <port>' and " +
                    "type the input text into the command line");
            return;
        }

        System.out.println(hostname);
        System.out.println(port);

    }
}
