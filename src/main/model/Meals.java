package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
//Represents a list of meals

public class Meals {
    private ArrayList<Meal> mealList;

    public Meals() {
        mealList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a meal to the mealList
    public void addMeal(Meal meal) {
        mealList.add(meal);
    }

    //REQUIRES: non-empty mealList
    //MODIFIES: this
    //EFFECTS: remove meal from mealList
    public void removeMeal(Meal meal, int date) {
        ArrayList<Meal> foundMeals = new ArrayList<>();
        for (Meal m : mealList) {
            String name = m.getName();
            if ((name.equals(meal.getName())) && (m.getDate() == date)) {
                foundMeals.add(m);
            }
        }
        mealList.removeAll(foundMeals);
    }

    //EFFECTS: return a list of Meals that were eaten on given date.
    public ArrayList<Meal> eatenMealsOnDate(int date) {
        ArrayList<Meal> onDateMeals = new ArrayList<>();
        for (Meal m : mealList) {
            if (m.getDate() == date) {
                onDateMeals.add(m);
            }
        }
        return onDateMeals;
    }

    //REQUIRES: non-empty list
    //EFFECTS: return total protein consumed per selected month
    public double caloriesPerMonth(String date) {
        double calories = 0;

        for (Meal m : mealList) {
            String stringDate = String.valueOf(m.getDate());
            String stringMonth = String.valueOf(m.getMonth(stringDate));
            if (date.equals(stringMonth)) {
                calories += m.getCalories();
            }
        }
        return calories;

    }

    //REQUIRES: non-empty list
    //EFFECTS: return total protein consumed per selected month
    public double proteinPerMonth(String date) {
        double protein = 0;

        for (Meal m : mealList) {
            String stringDate = String.valueOf(m.getDate());
            String stringMonth = String.valueOf(m.getMonth(stringDate));
            if (date.equals(stringMonth)) {
                protein += m.getProtein();
            }
        }
        return protein;

    }

    //REQUIRES: non-empty list
    //EFFECTS: return total fat consumed per selected month
    public double fatPerMonth(String date) {
        double fat = 0;

        for (Meal m : mealList) {
            String stringDate = String.valueOf(m.getDate());
            String stringMonth = String.valueOf(m.getMonth(stringDate));
            if (date.equals(stringMonth)) {
                fat += m.getFat();
            }
        }
        return fat;

    }

    //REQUIRES: non-empty list
    //EFFECTS: return total carbs consumed per selected month
    public double carbsPerMonth(String date) {
        double carbs = 0;

        for (Meal m : mealList) {
            String stringDate = String.valueOf(m.getDate());
            String stringMonth = String.valueOf(m.getMonth(stringDate));
            if (date.equals(stringMonth)) {
                carbs += m.getCarbs();
            }
        }
        return carbs;

    }

    //EFFECTS: return True if list is empty, else False
    public boolean isEmpty() {
        return mealList.isEmpty();
    }

    // EFFECTS: return # of meals in list
    public int mealListSize() {
        return mealList.size();
    }

    //EFFECT: return convert Meal as JSON object
    // from JSONSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Meals", mealListToJson());
        return json;
    }

    // EFFECTS: return converted meal list as a JSON array
    private JSONArray mealListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meal m : mealList) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }

}

