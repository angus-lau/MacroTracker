package ui;

import model.Meal;
import model.MealFile;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

//Represents a Calorie App and what it contains
public class CalorieApp {
    private ArrayList<Meal> listOfMeals;
    private ArrayList<String> stringListOfMeals;
    private ArrayList<Meal> listOfFavouriteMeals;
    private ArrayList<String> stringListOfFavouriteMeals;
    private Scanner input;
    private Meal meal;
    private int calorieGoal;
    private int date;
    private static final String JSON_STORE = "./data/testReaderGeneralMealFile.json";
    private MealFile mealFile;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: run the calorie app
    public CalorieApp() {
        mealFile = new MealFile("User 1");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCalorie();
    }

    //MODIFIES: this
    //EFFECTS: process user input
    private void runCalorie() {
        boolean runProgram = true;
        int userInput;

        init();

        while (runProgram) {
            displayMenu();

            if (input.hasNextInt()) {
                userInput = input.nextInt();
                if (userInput == 9) {
                    System.out.println("Goodbye... for now!");
                    runProgram = false;
                } else {
                    pickOption(userInput);
                }
            } else {
                input.next();
                System.out.println("Invalid input.");
            }

        }
    }

    //EFFECT: display option menu to user
    private void displayMenu() {
        System.out.println("\nPlease pick from the following choices");
        System.out.println("\t1: Set a new calorie goal");
        System.out.println("\t2: Set today's date");
        System.out.println("\t3: Log a new meal");
        System.out.println("\t4: Retrieve a log of meals on a certain day");
        System.out.println("\t5: See your favourites");
        System.out.println("\t6: Add to your favourites");
        System.out.println("\t7: Save Meal File to file");
        System.out.println("\t8: Load Meal File to file");
        System.out.println("\t9: Quit");
    }

    //MODIFIES: this
    //EFFECT: process user input
    private void pickOption(int input) {
        switch (input) {
            case 1:
                setGoal();
                break;
            case 3:
                addNewMeal();
                break;
            case 2:
                getDate();
                break;
            case 4:
                getLog();
                break;
            case 5:
                getFavourites();
                break;
            case 6:
                addFavourites();
                break;
            case 7:
                saveMealFile();
                break;
            case 8:
                loadMealFile();
                break;
            default:
                System.out.println("Sorry, that is not an option");
                break;
        }

    }
    //EFFECTS: save Meal File to File
    // from JSONSerializationDemo
    private void saveMealFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(mealFile);
            jsonWriter.close();
            System.out.println("Saved " + mealFile.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Meal File from file
    // from JSONSerializationDemo
    private void loadMealFile() {
        try {
            mealFile = jsonReader.read();
            listOfMeals = new ArrayList<>(mealFile.getMeals());
            for (Meal m : listOfMeals) {
                stringListOfMeals.add(m.getName());
            }
            listOfFavouriteMeals = new ArrayList<>(mealFile.getFavouriteMeals());
            for (Meal m : listOfFavouriteMeals) {
                stringListOfFavouriteMeals.add(m.getName());
            }
            System.out.println("Loaded " + mealFile.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECT: initialize the lists
    private void init() {
        listOfMeals = new ArrayList<>();
        listOfFavouriteMeals = new ArrayList<>();
        stringListOfFavouriteMeals = new ArrayList<>();
        stringListOfMeals = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //REQUIRES: non-negative, non-zero goal.
    //MODIFIES: this
    //EFFECT: update calorie goal
    private void setGoal() {
        System.out.println("What is your calorie goal?");
        calorieGoal = input.nextInt();
        System.out.println("Your goal is now: " + calorieGoal);
        mealFile.setCalorieGoal(calorieGoal);
    }

    //MODIFIES: this
    //EFFECT: update today's date
    private void getDate() {
        System.out.println("Please set today's date for your meals in the format of MMDDYY");
        date = input.nextInt();
    }

    //REQUIRES: caloriesGoal cannot be 0.
    //MODIFIES: this
    //EFFECT: update remaining calories based on goal and consumed meals
    private double remainingCalories(Meal m) {
        calorieGoal -= m.getCalories();
        return calorieGoal;
    }

    //MODIFIES: this
    //EFFECT: adds new consumed meal to mealList
    private void addNewMeal() {
        System.out.print("Meal: ");
        String mealName = input.next();
        System.out.print("Portion: ");
        double portions = input.nextDouble();
        System.out.print("Calories: ");
        double calories = input.nextDouble();
        System.out.print("Protein: ");
        double proteins = input.nextDouble();
        System.out.print("Fat: ");
        double fats = input.nextDouble();
        System.out.print("Carbs: ");
        double carbs = input.nextDouble();
        meal = new Meal(mealName, portions, calories, proteins, fats, carbs);
        meal.setDate(date);
        listOfMeals.add(meal);
        mealFile.addMeal(meal);
        stringListOfMeals.add(meal.getName());
//        System.out.println("You have " + remainingCalories(meal) + " left.");
    }

    //EFFECTS: search for meals that were eaten on given date
    private void getLog() {
        System.out.println("Please insert the date of the log you're looking for.");
        date = input.nextInt();
        findMeals(date);

    }

    //MODIFIES: this
    //EFFECTS: looks for meals eaten on given date
    private void findMeals(int date) {
        ArrayList<Meal> foundMeals = new ArrayList<>();
        for (Meal m : listOfMeals) {
            if (m.getDate() == date) {
                foundMeals.add(m);
            }
        }
        if (foundMeals.isEmpty()) {
            System.out.println("Sorry, no meals were found on that date.");
        } else {
            System.out.println("Here are the meals eaten on that date");
            System.out.println(stringListOfMeals);
        }
    }

    //EFFECT: returns list of favourite meals
    private void getFavourites() {
        System.out.println("Here are your favourite meals");
        System.out.println(stringListOfFavouriteMeals);
    }

    //MODIFIES: this
    //EFFECT: adds meal to favourites
    private void addFavourites() {
        System.out.print("Meal: ");
        String mealName = input.next();
        System.out.print("Portion: ");
        double portions = input.nextDouble();
        System.out.print("Calories: ");
        double calories = input.nextDouble();
        System.out.print("Protein: ");
        double proteins = input.nextDouble();
        System.out.print("Fat: ");
        double fats = input.nextDouble();
        System.out.print("Carbs: ");
        double carbs = input.nextDouble();
        meal = new Meal(mealName, portions, calories, proteins, fats, carbs);
        meal.setFavourite(true);

        listOfFavouriteMeals.add(meal);
        mealFile.addFavouriteMeals(meal);
        stringListOfFavouriteMeals.add(mealName);

        System.out.println("Meal Added");
    }



}

