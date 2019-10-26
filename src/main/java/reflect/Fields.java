package reflect;

import java.lang.reflect.Field;

public class Fields {

    public static void main(String[] args) {

        try {
            Class cls = Class.forName("models.Report");
            System.out.println("cls: " + cls);
            System.out.println("Fields =");

            // returns the array of Field objects representing the public fields
            Field f[] = cls.getFields();

            System.out.println("f.length: " + f.length);
            for (int i = 0; i < f.length; i++) {
                System.out.println(f[i]);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
