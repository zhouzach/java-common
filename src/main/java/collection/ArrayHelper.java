package collection;

import java.util.Arrays;

public class ArrayHelper {

    public static void print(String[] array) {
        System.out.println(Arrays.toString(array));
    }


    public static String[] init1() {
        String[] array = {"Toyota", "Mercedes", "BMW", "Volkswagen", "Skoda"};
        return array;
    }

    public static String[] init2() {
        String array[] = new String[]{
                "Toyota", "Mercedes", "BMW", "Volkswagen", "Skoda"};
        return array;
    }

    public static String[] copy(String[] a) {
        String[] b = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    public static void main(String[] args) {
        print(init1());

        print(copy(init1()));
    }
}
