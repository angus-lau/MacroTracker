package model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class FavouriteMealsTest {
    Meal M1;
    Meal M2;
    Meal M3;
    Meal M4;
    FavouriteMeals FML1;

    @BeforeEach
    void runBefore() {
        M1 = new Meal("Hamburger", 100, 259, 30, 14, 27);
        M2 = new Meal("Rice", 30, 25, 1, 1, 5);
        M3 = new Meal("Chicken", 100, 153, 30.2, 3.6, 0);
        M4 = new Meal("Yogurt", 100, 63, 5.3, 1.6, 0);
        FML1 = new FavouriteMeals();
    }

    @Test
    void testAddMeal() {
        M1.setFavourite(true);
        M2.setFavourite(false);
        FML1.addMeal(M1);
        FML1.addMeal(M2);
        assertEquals(1, FML1.mealListSize());
    }

    @Test
    void testRemoveMeal() {
        M1.setFavourite(true);
        M2.setFavourite(true);
        FML1.addMeal(M1);
        FML1.addMeal(M2);
        FML1.removeFavouriteMeal(M2);
        assertEquals(1, FML1.mealListSize());
    }

    @Test
    void testIsEmpty() {
        assertTrue(FML1.isEmpty());
    }

    @Test
    void testMealListSize() {
        assertEquals(0, FML1.mealListSize());

        M1.setFavourite(true);
        M2.setFavourite(true);
        FML1.addMeal(M1);
        FML1.addMeal(M2);
        assertEquals(2, FML1.mealListSize());
    }
}