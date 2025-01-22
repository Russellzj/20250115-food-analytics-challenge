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
                return null;
            }
            if (foodData[1].matches("[A-Za-z ]+")) {
                food.setCategory(foodData[1]);
            } else {
                return null;
            }
            if (foodData[2].matches("[A-Za-z ]+")) {
                food.setSubcategory(foodData[2]);
            } else {
                return null;
            }
            if (foodData[3].matches("\\d+.\\d+")) {
                food.setValue(Double.parseDouble(foodData[3]));
            } else {
                return null;
            }
            if (foodData[4].matches(
                    "([0-9]{4})-?(1[0-2]|0[1-9])-?(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):" +
                            "?([0-5][0-9]):?([0-5][0-9])")) {
                food.setDate(LocalDateTime.parse(foodData[4]));
            } else {
                System.out.println("Invalid date");
                return null;
            }
            return food;
        }
        return null;
    }
}
