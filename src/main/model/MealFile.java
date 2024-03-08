package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a users data with their favourite meals, past meals, and calorie goal.
public class MealFile implements Writable {
    private String name;
    private ArrayList<Meal> favouriteMeals;
    private ArrayList<Meal> meals;
    private double calorieGoal;

    //EFFECTS: constructs a MealFile with an empty list of favouriteMeals, Meals and 0 calories.
    public MealFile(String name) {
        this.name = name;
        calorieGoal = 0;
        favouriteMeals = new ArrayList<>();
        meals = new ArrayList<>();
    }

    public void setCalorieGoal(int calorie) {
        calorieGoal = calorie;
    }

    public String getName() {
        return name;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void addFavouriteMeals(Meal meal) {
        favouriteMeals.add(meal);
    }

    //EFFECT: returns number of meals in this meal file
    public int numMeals() {
        return meals.size();
    }

    //EFFECT: returns number of favourite meals in this meal file
    public int numFavouriteMeals() {
        return favouriteMeals.size();
    }

    // EFFECTS: returns an unmodifiable list of Meals in this player data
    // from JsonSerializationDemo
    public List<Meal> getMeals() {
        return Collections.unmodifiableList(meals);
    }

    // EFFECTS: returns an unmodifiable list of Favourite Meals in this player data
    // from JsonSerializationDemo
    public List<Meal> getFavouriteMeals() {
        return Collections.unmodifiableList(favouriteMeals);
    }

    // EFFECTS: converts user data to JSON object
    // from JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Meals", mealsToJson());
        json.put("Favourite Meals", favouriteMealsToJson());
        json.put("Calories", calorieGoal);
        return json;

    }

    //EFFECTS: converts meals to JSON Object
    // from JsonSerializationDemo
    private JSONArray mealsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meal m : meals) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }

    private JSONArray favouriteMealsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meal m : favouriteMeals) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
    
    
}

