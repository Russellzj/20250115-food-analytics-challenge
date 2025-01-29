import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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


   @Test
    public void importFoodFromStringShouldAcceptAll() {
       String acceptableData = """
           1,Fruit,Apple,74.41,2024-01-23T06:58:00
           2,Vegetable,Spinach,73.91,2024-03-02T14:16:00
           255,Vegetable,Pepper,"$96.52",2024-01-12T11:47:00
           1686,"Fruit","Banana",95.6,2024-01-25T02:10:00
           1687, Vegetable,"Pepper",7.87,2024-01-19T06:22:00
           1688,"Protein",Tofu,18.67,2024-02-14T18:54:00
           4687,Grain,Corn,70.31,2024-02-06T15:37:00,""";
        Foods foods = new Foods();
        int totalFoods = 1;
        for (String food : acceptableData.split("\n")) {
            foods.addFood(food);
            assertEquals(totalFoods, foods.getFoods().size());
            totalFoods++;
        }
   }


   @Test
    public void importFoodFromStringShouldNotAcceptAll() {
       String unaccpetableData = """
           Vegetable,Spinach,88.18,2024-03-09T22:54:00
           3597,Fruit-Grape,3.52,2024-03-08T17:10:00
           3597,3.52,2024-03-08T17:10:00
           255,Vegetable,Pepper,"$$96.52",2024-01-12T11:47:00
           $15,Vegetable,Spinach,88.18,2024-03-09T22:54:00
           """;
        Foods foods = new Foods();
        for (String food : unaccpetableData.split("\n")) {
            foods.addFood(food);
            assertEquals(0, foods.getFoods().size());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,Fruit,Apple,74.41,2024-01-23T06:58:00",
            "2,Vegetable,Spinach,73.91,2024-03-02T14:16:00",
            "3,Protein,Chicken,59.46,2024-01-03T21:25:00",
            "4,Fruit,Apple,22.65,2024-02-15T23:57:00",
            "5,Protein,Chicken,56.56,2024-03-06T04:10:00",
            "6,Protein,Tofu,22.82,2024-02-23T15:16:00",
            "7,Dairy,Milk,76.12,2024-01-15T12:46:00",
            "8,Grain,Oats,28.51,2024-01-20T14:21:00",
            "9,Dairy,Milk,10.18,2024-01-09T19:16:00",
            "10,Dairy,Yogurt,60.77,2024-01-04T22:55:00",
            "4687,Grain,Corn,70.31,2024-02-06T15:37:00,",
            "1874,Dairy,Milk,'9.99',2024-03-08T22:05:00"
    })
    public void shouldAcceptData(String food) {
        Foods foods = new Foods();
        foods.addFood(food);
        assertEquals(1, foods.getFoods().size());
    }

    @Test
    public void shouldProduceAccurateCalculation() {
        String acceptableData = """
           1,Fruit,Apple,74.41,2024-01-23T06:58:00
           2,Vegetable,Spinach,73.91,2024-03-02T14:16:00
           255,Vegetable,Pepper,"$96.52",2024-01-12T11:47:00
           1686,"Fruit","Banana",95.6,2024-01-25T02:10:00
           1687, Vegetable,"Pepper",7.87,2024-01-19T06:22:00
           1688,"Protein",Tofu,18.67,2024-02-14T18:54:00
           4687,Grain,Corn,70.31,2024-02-06T15:37:00,""";
       Foods foods = new Foods();
       for (String food : acceptableData.split("\n")) {
           foods.addFood(food);
       }
       assertEquals(437.29 , foods.getTotalValue());
       assertEquals(62.47 , foods.getAverageValue());
       assertEquals(7.87, foods.getMinimumValue());
       assertEquals(96.52, foods.getMaximumValue());
    }

    @Test
    public void shouldProduceCorrectStatisticsForCategory() {
        String acceptableData = """
           1,Fruit,Apple,74.41,2024-01-23T06:58:00
           2,Vegetable,Spinach,73.91,2024-03-02T14:16:00
           255,Vegetable,Pepper,"$96.52",2024-01-12T11:47:00
           1686,"Fruit","Banana",95.6,2024-01-25T02:10:00
           1687, Vegetable,"Pepper",7.87,2024-01-19T06:22:00
           1688,"Protein",Tofu,18.67,2024-02-14T18:54:00
           5168,"Protein", Tofu, 19.58, 2025-01-29T04:22:00
           4687,Grain,Corn,70.31,2024-02-06T15:37:00,""";
        Foods foods = new Foods();
        for (String food : acceptableData.split("\n")) {
            foods.addFood(food);
        }
        assertEquals(3 , foods.getTotalCategory("Vegetable"));
        assertEquals(2, foods.getTotalCategory("Fruit"));
        assertEquals(178.3, foods.getTotalValueCategory("Vegetable"));
        assertEquals(59.43, foods.getAvgCategoryValue("Vegetable"));
        assertEquals(7.87, foods.getMinimumValueCategory("Vegetable"));
        assertEquals(96.52, foods.getMaximumValueCategory("Vegetable"));
    }

    @Test
    public void shouldProduceCorrectStatisticsForSubCategory() {
        String acceptableData = """
           1,Fruit,Apple,74.41,2024-01-23T06:58:00
           2,Vegetable,Spinach,73.91,2024-03-02T14:16:00
           255,Vegetable,Pepper,"$96.52",2024-01-12T11:47:00
           1686,"Fruit","Banana",95.6,2024-01-25T02:10:00
           1687, Vegetable,"Pepper",7.87,2024-01-19T06:22:00
           1688,"Protein",Tofu,18.67,2024-02-14T18:54:00
           5168,"Protein", Tofu, 19.58, 2025-01-29T04:22:00
           4687,Grain,Corn,70.31,2024-02-06T15:37:00,""";
        Foods foods = new Foods();
        for (String food : acceptableData.split("\n")) {
            foods.addFood(food);
        }
        assertEquals(2, foods.getTotalSubCategory("Tofu"));
        assertEquals(38.25, foods.getTotalValueSubCategory("Tofu"));
        assertEquals(19.13, foods.getAvgSubCategoryValue("Tofu"));
        assertEquals(18.67, foods.getMinimumValueSubcategory("Tofu"));
        assertEquals(19.58, foods.getMaximumValueSubCategory("Tofu"));
    }


}
