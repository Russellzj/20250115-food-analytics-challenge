import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        Food food = Kata.importData(foodData);
        assertNotEquals(food, null);
        assertEquals(food.getId(), 1);
   }


}
