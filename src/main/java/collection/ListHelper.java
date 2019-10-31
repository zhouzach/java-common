package collection;

import models.Report;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.geeksforgeeks.org/initializing-a-list-in-java/
 */
public class ListHelper {

    private static List<Integer> list = Arrays.asList(1, 2, 3);

    public static void main(String args[]) {

//        buildUnmodifiableList();
        sort();
    }

    public static void buildUnmodifiableList() {
        List<Integer> fixedSizeList = Arrays.asList(1, 2, 3);

        List<Integer> unmodifiableList = Collections.unmodifiableList(Arrays.asList(1, 2, 3));

        List<Integer> singletonList = Collections.singletonList(2);
//        System.out.println("List : " + singletonList.toString());


        // Creating a List using Syntax 3
        List<Integer> streamList = Stream.of(1, 2, 3)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));

        // Printing the list
        System.out.println("List using Syntax 3: " + streamList.toString());


    }

    public static void buildModifiableList() {
        // Creating a mutable list using Arrays.asList()
        List<Integer> arrayList = new ArrayList<>(
                Arrays.asList(1, 2, 3));

        // Print the list
        System.out.println("List : " + arrayList.toString());

        arrayList.add(5);

        // Print the list
        System.out.println("Modified list : " + arrayList.toString());


        // For ArrayList
        List<Integer> list1 = new ArrayList<Integer>() {
            {
                add(1);
                add(3);
            }
        };
        System.out.println("ArrayList : " + list1.toString());

        // For LinkedList
        List<Integer> llist = new LinkedList<Integer>() {
            {
                add(2);
                add(4);
            }
        };
        System.out.println("LinkedList : " + llist.toString());

        // For Stack
        List<Integer> stack = new Stack<Integer>() {
            {
                add(3);
                add(1);
            }
        };
        System.out.println("Stack : " + stack.toString());


        // Creating a List using Syntax 1
        List<Integer> streamList = Stream.of(1, 2, 3)
                .collect(Collectors.toList());
        streamList.add(4);

        // Printing the list
        System.out.println("List using Syntax 1: "
                + streamList.toString());

        // Creating a List using Syntax 2
        List<Integer> streamArrayList = Stream.of(3, 2, 1)
                .collect(Collectors.toCollection(ArrayList::new));
        streamArrayList.add(4);

        // Printing the list
        System.out.println("List using Syntax 2: " + streamArrayList.toString());
    }

    public static void sort() {
        list.stream()
                .sorted((e1, e2) -> -e1.toString().compareTo(e2.toString())).collect(Collectors.toList())
                .forEach(System.out::println);

    }

    public  List<Report.Cell> secondarySort(List<Report.Cell> cells){
        // firstly sorted by cell's x
        Comparator<Report.Cell> firstComparator = Comparator.comparing(Report.Cell::getX);
        // secondly sorted by cell's y
        Comparator<Report.Cell> secondComparator = firstComparator.thenComparing(Comparator.comparing(Report.Cell::getY));

        return cells.stream().sorted(secondComparator).collect(Collectors.toList());
    }

    public static void sort4Chinese() {

        // http://zhuhengwei.com/2016/02/27/Java%E8%87%AA%E5%AE%9A%E4%B9%89%E6%AF%94%E8%BE%83%E5%99%A8%E5%AE%9E%E7%8E%B0%E4%B8%AD%E6%96%87%E6%8E%92%E5%BA%8F/
        // https://blog.csdn.net/yangchaofeng1229/article/details/6765031
        List<String> list = new ArrayList<>();
        list.add("b国中"); // 中->20013 unicode编码的4E2D
        list.add("b国美"); // 中->20013 unicode编码的4E2D
        list.add("国英"); // 英-->33521 unicode编码的82F1
        list.add("1国英"); // 英-->33521 unicode编码的82F1
        list.add("1国美"); // 英-->33521 unicode编码的82F1
        list.add("国美"); // 美->32654 unicode编码的7F8E
        // 汉字unicode编码表 http://www.chi2ko.com/tool/CJK.htm
        // 输出字符编码对应的十进制
        //char a = '美';
        //System.out.println((int) a);

        System.out.println("排序前-->" + list);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String o1 = "";
                String o2 = "";
                if (s1 != null) o1 = s1;
                if (s2 != null) o2 = s2;
                Collator instance = Collator.getInstance(Locale.CHINA);
                return instance.compare(o1, o2);
            }
        });
        System.out.println("排序后-->" + list);
        Collections.reverse(list);
        System.out.println("排序后逆序-->" + list);
    }
}
