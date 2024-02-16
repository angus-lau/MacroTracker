package model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MealsTest {
    Meal M1;
    Meal M2;
    Meal M3;
    Meal M4;
    Meals ML1;

    @BeforeEach
    void runBefore() {
        M1 = new Meal("Hamburger", 100, 259, 30, 14, 27);
        M2 = new Meal("Rice", 30, 25, 1, 1, 5);
        M3 = new Meal("Chicken", 100, 153, 30.2, 3.6, 0);
        M4 = new Meal("Yogurt", 100, 63, 5.3, 1.6, 0);
        ML1 = new Meals();
    }

    @Test
    void testAddMeal() {
        assertTrue(ML1.isEmpty());

        ML1.addMeal(M1);
        assertEquals(1, ML1.mealListSize());

        ML1.addMeal(M2);
        assertEquals(2, ML1.mealListSize());
    }

    @Test
    void testRemoveMeal() {
        M1.setDate(021024);
        ML1.addMeal(M1);
        M2.setDate(123123);
        ML1.addMeal(M2);
        ML1.removeMeal(M2, 123123);
        assertEquals(1, ML1.mealListSize());

    }

    @Test
    void testEatenMealsOnDate() {
        ML1.addMeal(M1);
        ML1.addMeal(M2);
        ML1.addMeal(M3);
        ML1.addMeal(M4);
        M1.setDate(123123);
        M2.setDate(123123);
        M3.setDate(112123);
        M4.setDate(123123);

        ArrayList<Meal> expected = new ArrayList<>();
        expected.add(M1);
        expected.add(M2);
        expected.add(M4);

        assertTrue(expected.equals(ML1.eatenMealsOnDate(123123)));

    }

    @Test
    void testProteinPerMonth() {
        ML1.addMeal(M1);
        ML1.addMeal(M2);
        ML1.addMeal(M3);
        ML1.addMeal(M4);
        M1.setDate(123123);
        M2.setDate(123123);
        M3.setDate(111111);
        M4.setDate(111111);
        assertEquals(31, ML1.proteinPerMonth("12"));
    }

    @Test
    void testCaloriesPerMonth() {
        ML1.addMeal(M1);
        ML1.addMeal(M2);
        ML1.addMeal(M3);
        ML1.addMeal(M4);
        M1.setDate(123123);
        M2.setDate(123123);
        M3.setDate(111111);
        M4.setDate(111111);
        assertEquals(284, ML1.caloriesPerMonth("12"));
    }

    @Test
    void testFatPerMonth() {
        ML1.addMeal(M1);
        ML1.addMeal(M2);
        ML1.addMeal(M3);
        ML1.addMeal(M4);
        M1.setDate(123123);
        M2.setDate(123123);
        M3.setDate(111111);
        M4.setDate(111111);
        assertEquals(15, ML1.fatPerMonth("12"));
    }

    @Test
    void testCarbsPerMonth() {
        ML1.addMeal(M1);
        ML1.addMeal(M2);
        ML1.addMeal(M3);
        ML1.addMeal(M4);
        M1.setDate(123123);
        M2.setDate(123123);
        M3.setDate(111111);
        M4.setDate(111111);
        assertEquals(32, ML1.carbsPerMonth("12"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(ML1.isEmpty());
    }

    @Test
    void testMealListSize(){
        assertEquals(0, ML1.mealListSize());
        ML1.addMeal(M1);
        assertEquals(1, ML1.mealListSize());
    }

}
