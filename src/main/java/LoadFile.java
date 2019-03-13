import com.sun.tools.javac.main.Option;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class LoadFile {
    public static Properties loadByResource(String filename) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = LoadFile.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return prop;
            }

            prop.load(input);

            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
            return prop;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Properties loadByPath(String filePath) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(filePath);
            prop.load(input);

            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
            return prop;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void printProp(Properties prop){
        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            System.out.println("Key : " + key + ", Value : " + value);
        }
    }

    public static void write2file(String filename){
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(filename);

            prop.setProperty("database", "localhost");
            prop.setProperty("dbuser", "mkyong");
            prop.setProperty("dbpassword", "password");

            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public static void main(String[] args) {

        Properties prop = loadByResource("config.properties");
        String url = prop.getProperty("url");
        String driver = prop.getProperty("driver");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String sql = prop.getProperty("sql");
        String targetDir = prop.getProperty("targetDir");


        String filePath = "/Users/Zach/java-common/src/main/resources/config.properties";
        prop = loadByPath(filePath);

        printProp(prop);

        write2file("app.properties");


    }
}
