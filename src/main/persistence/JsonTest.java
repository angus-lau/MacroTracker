package persistence;

import model.Meal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkMeal(String name, double portion, double calories,
                             double protein, double fat, double carbs, boolean favourite, int date, Meal meal) {
        assertEquals(name, meal.getName());
        assertEquals(portion, meal.getPortion());
        assertEquals(calories, meal.getCalories());
        assertEquals(protein, meal.getProtein());
        assertEquals(fat, meal.getFat());
        assertEquals(carbs, meal.getCarbs());
        assertEquals(favourite, meal.isFavourite());
        assertEquals(date, meal.getDate());
    }
}
