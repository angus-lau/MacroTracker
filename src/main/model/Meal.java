package model;

public class Meal {
    private String name;
    private double portion;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private boolean favourite;
    private int date;

    public Meal(String meal,
                double portion,
                double calories,
                double protein,
                double fat,
                double carbs) {
        name = meal;
        this.portion = portion;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        favourite = false;
        date = 000000;

    }

    //EFFECT: return name of meal
    public String getName() {
        return name;
    }

    //EFFECT: return portion size in grams
    public double getPortion() {
        return portion;
    }

    //EFFECT: return calorie size in grams
    public double getCalories() {
        return calories;
    }

    //EFFECT: return protein size in grams
    public double getProtein() {
        return protein;
    }

    //EFFECT: return fat size in grams
    public double getFat() {
        return fat;
    }

    //EFFECT: return carb size in grams
    public double getCarbs() {
        return carbs;
    }

    //EFFECT: return favourite status
    public boolean isFavourite() {
        return favourite;
    }

    //EFFECT: return date of meal
    public int getDate() {
        return date;
    }

    public int getMonth(String month) {
        if (month.length() > 2) {
            return Integer.parseInt(month) / 10000;
        } else {
            return Integer.parseInt(month);
        }
    }

    //REQUIRES: portion > 0;
    //MODIFIES: this
    //EFFECT: add amount (in grams) to portion amount.
    public void addPortion(double amount) {
        portion = portion + amount;
    }

    //REQUIRES: calories > 0;
    //MODIFIES: this
    //EFFECT: add amount (in grams) to calories amount.
    public void addCalories(double amount) {
        calories = calories + amount;
    }

    //REQUIRES: protein > 0;
    //MODIFIES: this
    //EFFECT: add amount (in grams) to protein amount.
    public void addProtein(double amount) {
        protein = protein + amount;
    }

    //REQUIRES: fat > 0;
    //MODIFIES: this
    //EFFECT: add amount (in grams) to fat amount.
    public void addFat(double amount) {
        fat = fat + amount;
    }

    //REQUIRES: carbs > 0;
    //MODIFIES: this
    //EFFECT: add amount (in grams) to carbs amount.
    public void addCarbs(double amount) {
        carbs = carbs + amount;
    }

    //MODIFIES: this
    //EFFECT: If is a favourite meal, set to True, otherwise False.
    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    //MODIFIES: this
    //EFFECT: Set the meal consumed date in format of MM/DD/YY
    public void setDate(int date) {
        this.date = date;
    }

}
