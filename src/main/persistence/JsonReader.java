package persistence;

import model.FavouriteMeals;
import model.Meals;
import model.Meal;
import model.MealFile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads MealList from JSON data stored in file
// Pulled from Workroom
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MealFile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMealFile(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses MealFile from JSON object and returns it
    private MealFile parseMealFile(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MealFile mf = new MealFile(name);
        addMeals(mf, jsonObject);
//        addFavouriteMeal(mf, jsonObject);
        return mf;
    }

    // MODIFIES: wf
    // EFFECTS: parses meals from JSON object and adds them to mealFile
    private void addMeals(MealFile mf, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(mf, nextMeal);
//            addFavouriteMeal(mf, nextMeal);
        }
    }

//    private void addFavouriteMeals(MealFile mf, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("FavouriteMeals");
//        for (Object json : jsonArray) {
//            JSONObject nextMeal = (JSONObject) json;
////            addMeal(mf, nextMeal);
//            addFavouriteMeal(mf, nextMeal);
//        }
//    }

    // MODIFIES: wf
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

    // MODIFIES: wf
    // EFFECTS: parses meal from JSON object and adds it to mealFile
//    private void addFavouriteMeal(MealFile mf, JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        double portion = jsonObject.getDouble("portion");
//        double calories = jsonObject.getDouble("calories");
//        double protein = jsonObject.getDouble("protein");
//        double fat = jsonObject.getDouble("fat");
//        double carbs = jsonObject.getDouble("carbs");
//        boolean favourite = jsonObject.getBoolean("favourite");
//        int date = jsonObject.getInt("date");
//
//        Meal meal = new Meal(name, portion, calories, protein, fat, carbs);
//        meal.setDate(date);
//        meal.setFavourite(favourite);
//        mf.addFavouriteMeals(meal);
//    }
}
