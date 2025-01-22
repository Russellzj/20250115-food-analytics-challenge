import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kata {
    public String hello(String name) {
        return String.format("Hello %s", name);
    }

    public static void main(String[] args) {
        Foods foods = new Foods();
        File file = new File("Solutions/Java/data/datasetTest.csv");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                foods.addFood(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }
}
