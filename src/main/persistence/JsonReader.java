package persistence;

import model.Meal;
import model.MealFile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads mealFile from JSON data stored in file
// from JSONSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    // from JSONSerializationDemo
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads mealFile from file and returns it;
    // throws IOException if an error occurs reading data from file
    // from JSONSerializationDemo
    public MealFile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMealFile(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // from JSONSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: this
    // EFFECTS: parses MealFile from JSON object and returns it
    // from JSONSerializationDemo
    private MealFile parseMealFile(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MealFile mf = new MealFile(name);
        addMeals(mf, jsonObject);
        addFavouriteMeals(mf, jsonObject);
//        String calories = jsonObject.getString("Calories");
//        jsonObject.put("Calories", calories);
        return mf;
    }

    // MODIFIES: this
    // EFFECTS: parses meals from JSON object and adds them to mealFile
    // from JSONSerializationDemo
    private void addMeals(MealFile mf, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(mf, nextMeal);
        }
    }

    // MODIFIES: this
    // EFFECTS: parses meal from JSON object and adds it to mealFile
    // from JSONSerializationDemo
    private void addFavouriteMeals(MealFile mf, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Favourite Meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addFavouriteMeal(mf, nextMeal);
        }
    }

    // MODIFIES: this
    // EFFECTS: parses meal from JSON object and adds it to mealFile
    private void addMeal(MealFile mf, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double portion = jsonObject.getDouble("portion");
        double calories = jsonObject.getDouble("calories");
        double protein = jsonObject.getDouble("protein");
        double fat = jsonObject.getDouble("fat");
        double carbs = jsonObject.getDouble("carbs");
        boolean favourite = jsonObject.getBoolean("favourite");
        int date = jsonObject.getInt("date");
        
        Meal meal = new Meal(name, portion, calories, protein, fat, carbs);
        meal.setDate(date);
        meal.setFavourite(favourite);
        mf.addMeal(meal);
    }

//     MODIFIES: this
//     EFFECTS: parses Favourite Meal from JSON object and adds it to mealFile
    private void addFavouriteMeal(MealFile mf, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double portion = jsonObject.getDouble("portion");
        double calories = jsonObject.getDouble("calories");
        double protein = jsonObject.getDouble("protein");
        double fat = jsonObject.getDouble("fat");
        double carbs = jsonObject.getDouble("carbs");
        boolean favourite = jsonObject.getBoolean("favourite");
        int date = jsonObject.getInt("date");

        Meal meal = new Meal(name, portion, calories, protein, fat, carbs);
        meal.setDate(date);
        meal.setFavourite(favourite);
        mf.addFavouriteMeals(meal);
    }
}
