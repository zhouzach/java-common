import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {


        UUID uuid = Generators.timeBasedGenerator().generate();

        System.out.println(uuid.toString().replaceAll("-",""));
    }
}
