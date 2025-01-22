import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kata {
    public String hello(String name) {
        return String.format("Hello %s", name);
    }

    public static void main(String[] args) {
        List<Food> foods = new ArrayList<Food>();

        File file = new File("dataset.csv");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                for (String foodData : line.split(",")) {

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }

    public static Food importData(String data) {
        Food food  = new Food();
        String[] foodData = data.split(",");
        if (foodData.length == 5) {
            if(foodData[0].matches("\\d+")) {
                food.setId(Integer.parseInt(foodData[0]));
            } else {
                System.out.println(foodData[0]);
                System.out.println("Invalid ID");
                return null;
            }
            return food;
        }
        System.out.println("Not Enough Data Points");
        return null;
    }
}
