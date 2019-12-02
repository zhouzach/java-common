package collection;

import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamHelper {


    private static List<String> list = Arrays.asList("ab", "bc", "cd");

    static List<Object[]> list1 = new ArrayList<Object[]>() {
        {
            add(new Object[]{"Toyota", 14, 1,49.2});
            add(new Object[]{"lily", 18, 2,89.2});
            add(new Object[]{"Toyota", 14, 1,79.2});
        }
    };

    public static void anyMatch() {
        boolean b = list.stream().anyMatch("bc"::equals);
        System.out.println(b);
    }

    public static void groupByMultiKeys() {

        Function<Object[], List> classifier = (i) -> {
            return Arrays.asList(i[0], i[1]);
        };

        int n = 3;
        list1.stream()
                .collect(
                        Collectors.groupingBy(
                                a -> {
                                    List<Object> list = new ArrayList<>();
                                    for (int i = 0; i < n; i++) list.add(a[i]);
                                    return list;
                                }
                        )
                ).forEach((k, v) -> {
                    System.out.println(k + " : ");
                    v.forEach(vv -> System.out.println(" " + vv[0] +", "+ vv[1] +", "+ vv[2]+", "+vv[3]));
                    System.out.println();
                }


        );
    }


    public static void main(String[] args) {
//        anyMatch();

        groupByMultiKeys();

    }

}
