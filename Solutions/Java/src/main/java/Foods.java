import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Foods {
    private List<Food> foods = new ArrayList<Food>();

    public void addFood(String foodData) {
        Food newFood = importData(foodData);
        if (newFood != null) {
            foods.add(newFood);
        } else {
            System.out.println("Invalid food Data");
        }
    }

    public static Food importData(String data) {
        Food food  = new Food();
        String[] foodData = data.split(",");
        if (foodData.length == 5) {
            if(foodData[0].matches("\\d+")) {
                food.setId(Integer.parseInt(foodData[0]));
            } else {
                System.out.println("Invalid ID");
                return null;
            }
            if (foodData[1].matches("[A-Za-z ]+")) {
                food.setCategory(foodData[1]);
            } else {
                System.out.println("Invalid Category");
                return null;
            }
            if (foodData[2].matches("[A-Za-z ]+")) {
                food.setSubcategory(foodData[2]);
            } else {
                System.out.println("Invalid Subcategory");
                return null;
            }
            if (foodData[3].matches("\\d+.\\d+")) {
                food.setValue(Double.parseDouble(foodData[3]));
            } else {
                System.out.println("Invalid Value");
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
        System.out.println("Not enough data");
        return null;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
