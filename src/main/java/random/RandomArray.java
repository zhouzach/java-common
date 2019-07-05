package random;

import java.util.Arrays;
import java.util.Random;

public class RandomArray {
    private Random rand = new Random();
    private Integer[] speeds;

    public void run() throws Exception {
        Arrays.fill(speeds, 50);

        while (true) {
            Thread.sleep(100);
            for (int carId = 0; carId < speeds.length; carId++) {
                if (rand.nextBoolean()) {
                    speeds[carId] = Math.min(100, speeds[carId] + 5);
                } else {
                    speeds[carId] = Math.max(0, speeds[carId] - 5);
                }

            }
        }
    }
}
