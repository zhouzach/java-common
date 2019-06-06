package copy;

import lombok.Data;

@Data
public class ShallowCopy implements Cloneable{

    private Integer id;
    private String name;
    private Food food;


    public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }


    public static void main(String[] args){
        ShallowCopy cloneObj = new ShallowCopy();
        cloneObj.setId(1);
        cloneObj.setName("a");
        Food food = new Food();
        food.setColour("green");
        food.setAge(10);
        cloneObj.setFood(food);
        System.out.println(cloneObj);


        ShallowCopy cloneObj1 = (ShallowCopy)cloneObj.clone();
        System.out.println(cloneObj1);
        cloneObj1.setId(2);
        System.out.println("set int id:");
        System.out.println(cloneObj);
        System.out.println(cloneObj1);
        System.out.println("set red food:");
        food.setColour("red");
        cloneObj1.setFood(food);
        System.out.println(cloneObj);
        System.out.println(cloneObj1);

    }
}

@Data
class Food{
    private String colour;
    private Integer age;
}