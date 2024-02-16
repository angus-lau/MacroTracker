package model;

import java.util.ArrayList;

public class FavouriteMeals extends Meals {
    private ArrayList<Meal> favouriteMeals;

    public FavouriteMeals() {
        favouriteMeals = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add Meal to list of favourite meals
    @Override
    public void addMeal(Meal meal) {
        if (meal.isFavourite()) {
            favouriteMeals.add(meal);
        } else {
            super.addMeal(meal);
        }
    }

    //REQUIRES: non-empty mealList
    //MODIFIES: this
    //EFFECTS: remove meal from mealList
    public void removeFavouriteMeal(Meal meal) {
        ArrayList<Meal> foundMeals = new ArrayList<>();
        for (Meal m : favouriteMeals) {
            String name = m.getName();
            if ((name.equals(meal.getName()))) {
                foundMeals.add(m);
            }
        }
        favouriteMeals.removeAll(foundMeals);
    }

    @Override
    //EFFECTS: return True if list is empty, else False
    public boolean isEmpty() {
        return favouriteMeals.isEmpty();
    }

    @Override
    // EFFECTS: return # of meals in list
    public int mealListSize() {
        return favouriteMeals.size();
    }
}