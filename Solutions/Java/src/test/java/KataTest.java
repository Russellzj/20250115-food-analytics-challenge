import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class KataTest {
    private Kata fixture;

    @BeforeEach
    public void setUp() {
        fixture = new Kata();
        String acceptableData = """
           1,Fruit,Apple,74.41,2024-01-23T06:58:00
           2,Vegetable,Spinach,73.91,2024-03-02T14:16:00
           255,Vegetable,Pepper,"$96.52",2024-01-12T11:47:00
           1686,"Fruit","Banana",95.6,2024-01-25T02:10:00
           1687, Vegetable,"Pepper",7.87,2024-01-19T06:22:00
           1688,"Protein",Tofu,18.67,2024-02-14T18:54:00
           4687,Grain,Corn,70.31,2024-02-06T15:37:00,""";
    }

    @Test
    public void helloShouldReturnMessageWithSpecifiedName() {
        String name = "Ingage";
        assertEquals(String.format("Hello %s", name), fixture.hello(name));
    }

    @Test
    public void importFoodFromString() {
        String foodData = "1, Fruit,Apple,74.41,2024-01-23T06:58:00";
        Foods foods = new Foods();
        foods.addFood(foodData);
        Food food = foods.getFoods().getFirst();
        assertNotEquals(food, null);
        assertEquals(food.getId(), 1);
        assertEquals(food.getCategory(), "Fruit");
        assertEquals(food.getSubcategory(), "Apple");
        assertEquals(food.getValue(), 74.41);
        assertEquals(food.getDate(), LocalDateTime.parse("2024-01-23T06:58:00"));
   }

   String acceptableData = """
           1,Fruit,Apple,74.41,2024-01-23T06:58:00
           2,Vegetable,Spinach,73.91,2024-03-02T14:16:00
           255,Vegetable,Pepper,"$96.52",2024-01-12T11:47:00
           1686,"Fruit","Banana",95.6,2024-01-25T02:10:00
           1687, Vegetable,"Pepper",7.87,2024-01-19T06:22:00
           1688,"Protein",Tofu,18.67,2024-02-14T18:54:00
           4687,Grain,Corn,70.31,2024-02-06T15:37:00,""";
   @Test
    public void importFoodFromStringShouldAcceptAll() {
        Foods foods = new Foods();
        int totalFoods = 1;
        for (String food : acceptableData.split("\n")) {
            foods.addFood(food);
            assertEquals(totalFoods, foods.getFoods().size());
            totalFoods++;
        }
   }

   String unaccpetableData = """
           Vegetable,Spinach,88.18,2024-03-09T22:54:00
           3597,Fruit-Grape,3.52,2024-03-08T17:10:00
           3597,3.52,2024-03-08T17:10:00
           255,Vegetable,Pepper,"$$96.52",2024-01-12T11:47:00
           $15,Vegetable,Spinach,88.18,2024-03-09T22:54:00
           """;
   @Test
    public void importFoodFromStringShouldNotAcceptAll() {
        Foods foods = new Foods();
        for (String food : unaccpetableData.split("\n")) {
            foods.addFood(food);
            assertEquals(0, foods.getFoods().size());
        }
    }

    @Test
    public void shouldProduceAccurateCalculation() {
       Foods foods = new Foods();
       for (String food : acceptableData.split("\n")) {
           foods.addFood(food);
       }
       assertEquals(437.29 , foods.getTotalValue());
       assertEquals(62.47 , foods.getAverageValue());
       assertEquals(7.87, foods.getMinimumValue());
       assertEquals(96.52, foods.getMaximumValue());
    }





}
