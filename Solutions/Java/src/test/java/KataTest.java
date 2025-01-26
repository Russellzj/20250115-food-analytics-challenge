import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class KataTest {
    private Kata fixture;

    @BeforeEach
    public void setUp() {
        fixture = new Kata();
    }

    @Test
    public void helloShouldReturnMessageWithSpecifiedName() {
        String name = "Ingage";
        assertEquals(String.format("Hello %s", name), fixture.hello(name));
    }

    @Test
    public void importFoodFromString() {
        String foodData = "1,Fruit,Apple,74.41,2024-01-23T06:58:00";
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

   @Test
    public void importFoodFromStringShouldAcceptAll() {
        String foodData = "1,Fruit,Apple,74.41,2024-01-23T06:58:00\n" +
       "2,Vegetable,Spinach,73.91,2024-03-02T14:16:00\n" +
       "255,Vegetable,Pepper,\"$96.52\",2024-01-12T11:47:00\n" +
       "1686,\"Fruit\",\"Banana\",95.6,2024-01-25T02:10:00\n" +
       "1687,Vegetable,\"Pepper\",7.87,2024-01-19T06:22:00\n" +
       "1688,\"Protein\",Tofu,18.67,2024-02-14T18:54:00\n" +
       "4687,Grain,Corn,70.31,2024-02-06T15:37:00,";
        Foods foods = new Foods();
        int totalFoods = 1;
        for (String food : foodData.split("\n")) {
            foods.addFood(food);
            assertEquals(totalFoods, foods.getTotalFood());
            totalFoods++;
        }
   }


}
