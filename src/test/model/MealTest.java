package model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {
    Meal M1;

    @BeforeEach
    void runBefore() {
        M1 = new Meal("Hamburger", 100, 153, 30.2,3.6, 0);
    }

    @Test
    void testAddPortions() {

        M1.addPortion(100);
        assertEquals(200, M1.getPortion());
    }

    @Test
    void testAddProtein() {
        assertEquals(30.2, M1.getProtein());

        M1.addProtein(100);
        assertEquals(130.2, M1.getProtein());
    }

    @Test
    void testAddCalories() {
        assertEquals(153, M1.getCalories());

        M1.addCalories(100);
        assertEquals(253, M1.getCalories());
    }

    @Test
    void testAddCarbs() {
        assertEquals(0, M1.getCarbs());

        M1.addCarbs(100);
        assertEquals(100, M1.getCarbs());
    }

    @Test
    void testAddFats() {
        assertEquals(3.6, M1.getFat());

        M1.addFat(100);
        assertEquals(103.6, M1.getFat());
    }

    @Test
    void testGetMonth() {
        M1.setDate(012524);
        assertEquals(01, M1.getMonth("012524"));

        M1.setDate(123123);
        assertEquals(12, M1.getMonth("123123"));

    }

    @Test
    void testGetDate() {
        M1.setDate(012524);
        assertEquals(012524, M1.getDate());
    }

    @Test
    void testSetFavourite() {
        M1.setFavourite(false);
        assertFalse(M1.isFavourite());
    }
}