package copy;

import lombok.Data;

@Data
public class DeepCopy implements Cloneable {

    private Integer id;
    private String name;
    private CloneableFood cloneableFood;

    public DeepCopy(Integer id, String name, CloneableFood cloneableFood) {
        this.id = id;
        this.name = name;
        this.cloneableFood = cloneableFood;

    }

    public Object clone() {
        DeepCopy deepCopy = null;
        try {
            deepCopy = (DeepCopy) super.clone();

            CloneableFood cloneableFood = (CloneableFood) deepCopy.getCloneableFood().clone();
            deepCopy.setCloneableFood(cloneableFood);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return deepCopy;
    }


    public static void main(String[] args) {
        CloneableFood food = new CloneableFood();
        food.setColour("green");
        food.setAge(10);

        DeepCopy cloneObj = new DeepCopy(1,"a", food);
        System.out.println(cloneObj);


        DeepCopy cloneObj1 = (DeepCopy) cloneObj.clone();
        System.out.println(cloneObj1);

        cloneObj1.setId(2);
        System.out.println("set int id:");
        System.out.println(cloneObj1);
        System.out.println(cloneObj);

        cloneObj1.setName("b");
        System.out.println("set String name:");
        System.out.println(cloneObj1);
        System.out.println(cloneObj);


        System.out.println("set red food:");
        CloneableFood food1 = new CloneableFood();
        food1.setColour("red");
        food1.setAge(10);
        System.out.println(food1);


        cloneObj1.setCloneableFood(food1);
        System.out.println(cloneObj1);
        System.out.println(cloneObj);

    }
}

@Data
class CloneableFood implements Cloneable {
    private String colour;
    private Integer age;

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
