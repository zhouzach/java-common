package collection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.geeksforgeeks.org/initializing-a-list-in-java/
 */
public class ListHelper {

    public static void main(String args[]) {

        buildUnmodifiableList();
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

        List.of("first","second");


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
            } };
        System.out.println("ArrayList : " + list1.toString());

        // For LinkedList
        List<Integer> llist = new LinkedList<Integer>() {
            {
                add(2);
                add(4);
            } };
        System.out.println("LinkedList : " + llist.toString());

        // For Stack
        List<Integer> stack = new Stack<Integer>() {
            {
                add(3);
                add(1);
            } };
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
}
