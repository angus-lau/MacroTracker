package persistence;

import model.MealFile;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import model.Meal;
import java.util.List;

//tests from/inspired JSONSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MealFile mf = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMealFile() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            MealFile mf = reader.read();
            assertEquals("User 1", mf.getName());
            assertEquals(0, mf.numMeals());
            assertEquals(0, mf.numFavouriteMeals());
            assertEquals(0, mf.getCalorieGoal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMealFile() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMealFile.json");
        try {
            MealFile mf = reader.read();
            assertEquals("User 1", mf.getName());
            List<Meal> meal = mf.getMeals();
            List<Meal> favouriteMeals = mf.getFavouriteMeals();
            assertEquals(1, mf.numMeals());
            assertEquals(1, mf.numFavouriteMeals());
            assertEquals(1000, mf.getCalorieGoal());
            checkMeal("MEAL111", 1,1,1,1,1, false, 123123, mf.getMeals().get(0));
            checkMeal("FAVMEAL1111", 1,1,1,1,1,true, 0, mf.getFavouriteMeals().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

