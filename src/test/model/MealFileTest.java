package model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MealFileTest {
    private MealFile MF1;
    private Meal M1;
    private Meal M2;
    private Meal M3;
    private Meal M4;

    @BeforeEach
    void runBefore() {
        MF1 = new MealFile("User 1");
        MF1.setCalorieGoal(1000);
        MF1.addMeal(M1);
        MF1.addFavouriteMeals(M2);
    }

    @Test
    void testCalorieGoal() {
        assertEquals(1000, MF1.getCalorieGoal());
        MF1.setCalorieGoal(200);
        assertEquals(200, MF1.getCalorieGoal());
    }

    @Test
    void testAddMeals() {
        assertEquals(1, MF1.getMeals().size());
        MF1.addMeal(M3);
        assertEquals(2, MF1.getMeals().size());
    }

    @Test
    void testAddFavouriteMeals() {
        assertEquals(1, MF1.getFavouriteMeals().size());
        MF1.addFavouriteMeals(M4);
        assertEquals(2, MF1.getFavouriteMeals().size());
    }
}
