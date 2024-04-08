package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a users data with their favourite meals, past meals, and calorie goal.
public class MealFile implements Writable {
    private String name;
    private ArrayList<Meal> favouriteMeals;
    private ArrayList<Meal> meals;
    private int calorieGoal;

    //EFFECTS: constructs a MealFile with an empty list of favouriteMeals, Meals and 0 calories.
    public MealFile(String name) {
        this.name = name;
        calorieGoal = 0;
        favouriteMeals = new ArrayList<>();
        meals = new ArrayList<>();
    }

    //REQUIRES: non-zero, or negative
    //MODIFIES: this
    //EFFECT: set amount for calorie goal
    public void setCalorieGoal(int calorie) {
        calorieGoal = calorie;
    }

    public int getCalorieGoal() {
        return calorieGoal;
    }

    //EFFECT: return name of File.
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECT: add meal to list of meals
    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    //MODIFIES: this
    //EFFECT: remove meal from the list of meals
    public void removeFavouriteMeal(Meal meal) {
        favouriteMeals.remove(meal);
        EventLog.getInstance().logEvent(new Event("Removed " + meal.getName() + " from Favourite Meals"));
    }

    //MODIFIES: this
    //EFFECT: add favourite meals to list of favourite meals
    public void addFavouriteMeals(Meal meal) {
        favouriteMeals.add(meal);
        EventLog.getInstance().logEvent(new Event("Added " + meal.getName() + " to Favourite Meals"));
    }

    //EFFECTS: returns number of meals in this File
    public int numMeals() {
        return meals.size();
    }

    //EFFECTS: returns number of favourite meals in this File
    public int numFavouriteMeals() {
        return favouriteMeals.size();
    }

    // EFFECTS: returns list of Meals
    // from JSONSerializationDemo
    public List<Meal> getMeals() {
        return meals;
    }

    // EFFECTS: return list of Favourite Meals
    // from JSONSerializationDemo
    public List<Meal> getFavouriteMeals() {
        return favouriteMeals;
    }

    // EFFECTS: converts user data to JSON object
    // from JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Meals", mealsToJson());
        json.put("Favourite Meals", favouriteMealsToJson());
        json.put("Calorie Goal", calorieGoal);
        return json;

    }

    //EFFECTS: converts meals to JSON array
    // from JSONSerializationDemo
    private JSONArray mealsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meal m : meals) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: converts favourite meals to JSON array
    // from JSONSerializationDemo
    private JSONArray favouriteMealsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meal m : favouriteMeals) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}

