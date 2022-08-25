package collection;

import lombok.val;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamHelper {


    private static List<String> list = Arrays.asList("ab", "bc", "cd");

    static List<Object[]> list1 = new ArrayList<Object[]>() {
        {
            add(new Object[]{"Toyotc", 14, 2, 49.2});
            add(new Object[]{"lily", 18, 2, 89.2});
            add(new Object[]{"Toyotb", 14, 1, 79.2});
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
                ).entrySet().stream()
                .sorted(
                        (e1, e2) -> {
                            val list1 = e1.getKey();
                            val list2 = e2.getKey();

                            for (int i = 0; i < list1.size(); i++) {

                                int res = String.valueOf(list1.get(i)).compareTo(String.valueOf(list2.get(i)));
                                if (res == 0) {
                                    continue;
                                } else {
                                    return res;
                                }
                            }
                            return 0;
                        }
//                        Comparator.comparing((c1,c2)->String.valueOf(c1).compareTo(String.valueOf(c2)))
//                        .thenComparing(m -> m.get(Key2))
//                        .thenComparing(m -> m.get(Key3))
//                        .thenComparing(m -> m.get(Key4)))
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new))
                .forEach((k, v) -> {
                            System.out.println(k + " : ");
                            v.forEach(vv -> System.out.println(" " + vv[0] + ", " + vv[1] + ", " + vv[2] + ", " + vv[3]));
                            System.out.println();
                        }
                );


    }


    public static void main(String[] args) {
//        anyMatch();

        groupByMultiKeys();

    }

}
