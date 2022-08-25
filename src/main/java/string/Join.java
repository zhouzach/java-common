package string;


import java.util.ArrayList;
import java.util.List;

public class Join {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("hive");
        list.add("spark");

        String res = String.join(";", list);
        System.out.println(res);
    }
}
