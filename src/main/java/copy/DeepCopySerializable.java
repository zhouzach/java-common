package copy;

import lombok.Data;

import java.io.*;


@Data
public class DeepCopySerializable implements Serializable {


    private Integer id;
    private String name;
    private SerializableFood food;

    public DeepCopySerializable deepClone() throws IOException, ClassNotFoundException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (DeepCopySerializable) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static void main(String[] args) {

        DeepCopySerializable deepCopySerializable = new DeepCopySerializable();
        SerializableFood food = new SerializableFood();
        food.setColour("green");
        food.setAge(10);
        deepCopySerializable.setFood(food);
        deepCopySerializable.setId(1);
        deepCopySerializable.setName("a");
        System.out.println(deepCopySerializable);

        try {

            DeepCopySerializable deepCopySerializable1 = deepCopySerializable.deepClone();
            System.out.println("deepCopySerializable1: " + deepCopySerializable1);

            deepCopySerializable1.setId(2);
            System.out.println("set int id:");
            System.out.println(deepCopySerializable1);
            System.out.println(deepCopySerializable);

            System.out.println("set red food:");
            SerializableFood food1 = new SerializableFood();
            food1.setColour("red");
            food1.setAge(10);
            deepCopySerializable1.setFood(food1);
            System.out.println(deepCopySerializable1);
            System.out.println(deepCopySerializable);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

@Data
class SerializableFood
        implements Serializable
{
    private String colour;
    private Integer age;
}