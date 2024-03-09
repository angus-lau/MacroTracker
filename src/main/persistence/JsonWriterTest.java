package persistence;

import model.Meal;
import model.Meals;
import model.MealFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    //FROM/INSPIRATION JSONSerializationDemo
    @Test
    void testWriterInvalidFile() {
        try {
            MealFile mf = new MealFile("My work room");
            JsonWriter writer = new JsonWriter("./data/my\1232illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MealFile mf = new MealFile("MealFile123");
            JsonWriter writer = new JsonWriter("./data/testWriterMealFile.json");
            writer.open();
            writer.write(mf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            mf = reader.read();
            assertEquals("", mf.getName());
            assertEquals(0, mf.numMeals());
            assertEquals(0, mf.numFavouriteMeals());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MealFile mf = new MealFile("User2");
            mf.addMeal(new Meal("Burger",1,1,1,1,1));
            mf.addFavouriteMeals(new Meal("Soup", 1,1,1,1,1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(mf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            mf = reader.read();
            assertEquals("User2", mf.getName());
            List<Meal> meal = mf.getMeals();
            List<Meal> favouriteMeal = mf.getFavouriteMeals();
            assertEquals(1, meal.size());
            assertEquals(1, favouriteMeal.size());
            checkMeal("MEAL111",1,1,1,1,1,false,0, mf.getMeals().get(0));
            checkMeal("FAVMEAL1111", 1,1,1,1,1,false,0, mf.getFavouriteMeals().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}