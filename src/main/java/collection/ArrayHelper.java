package collection;

import java.util.Arrays;

public class ArrayHelper {

    public static void print(String[] array){
        System.out.println(Arrays.toString(array));
    }


    public static String[] init(){
        String array[] = new String[] {
                "Toyota", "Mercedes", "BMW", "Volkswagen", "Skoda" };
        return array;
    }
    public static void main(String[] args){

        print(init());
    }
}
