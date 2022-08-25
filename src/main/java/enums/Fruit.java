package enums;

import java.util.Arrays;

public enum Fruit {
    Apple("apple"), Banana("banana"), Orange("orange");

    private String value;

    Fruit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args){
        Fruit[] fruits = { Fruit.Banana, Fruit.Orange, Fruit.Orange};

        boolean b = Arrays.stream(fruits)
                .anyMatch(fruit -> fruit.equals(Fruit.Apple) || fruit == Fruit.Orange);

        if(b){
            System.out.println("contains");
        }
//                .forEach(channel -> System.out.println(channel.name()));


    }
}
