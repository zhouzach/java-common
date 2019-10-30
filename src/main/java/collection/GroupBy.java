package collection;

import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GroupBy {

    public static void main(String[] args) {


        val objects1 = new Object[]{1, "a"};
        val objects2 = new Object[]{2, "b"};
        val objects3 = new Object[]{1, "c"};
        val objects4 = new Object[]{2, "b"};
        val objects5 = new Object[]{3, "e"};
        val list = new ArrayList<Object[]>();

        list.add(objects1);
        list.add(objects2);
        list.add(objects3);
        list.add(objects4);
        list.add(objects5);

        list.stream()
                .collect(Collectors.groupingBy(a -> a[0]))
                .forEach((k, l) -> {
                            System.out.print("k: " + k + ", list: ");
                            l.forEach(a -> System.out.print(Arrays.toString(a) + " - "));
                            System.out.println();
                        }
                );


    }
}
