package ui;

import model.MealFile;
import model.Meal;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import persistence.JsonReader;
import persistence.JsonWriter;

//EFFECTS: create the Meal File GUI
public class MealFileGUI extends Container {
    private static final String STATUS = "RUNNING";
    JLabel statusLabel;
    JButton addNewMeal;
    JButton addNewFavouriteMeal;
    JButton setCalorieGoal;
    JButton seeFavouriteMeals;
    JButton seeKrabbyPatty;
    JButton saveButton;
    JButton loadButton;
    JFrame mainFrame;
    JPanel mainArea;
    JPanel headerSpace;
    JPanel newMealPanel;
    JPanel newFavouriteMealPanel;
    JPanel newSeeFavouriteMeal;
    JPanel newCaloriesPanel;
    JPanel newKrabbyPattyPanel;
    MealFile mealFile = new MealFile("User 1");
    JTextField mealTextField;
    JTextField portionTextField;
    JTextField caloriesTextField;
    JTextField proteinTextField;
    JTextField fatTextField;
    JTextField carbsTextField;
    JTextField dateTextField;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE_FILE = "./data/functionalGUI.json";

    //MODIFIES: this
    //EFFECT: starts the main file gui
    public MealFileGUI() {
        jsonReader = new JsonReader(JSON_STORE_FILE);
        jsonWriter = new JsonWriter(JSON_STORE_FILE);
        mainFrame = new JFrame("Meal Tracker V1.0");
        mainFrame.setSize(700, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel ma = mainArea();
        JPanel hs = headerSpace();
        mainFrame.add(ma, BorderLayout.CENTER);


        mainFrame.add(hs, BorderLayout.NORTH);
        mainFrame.setVisible(true);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    //MODIFIES: this
    //EFFECT: create header space
    public JPanel headerSpace() {
        headerSpace = new JPanel();
        JLabel header = new JLabel("Macro Tracker V1.0");
        statusLabel = new JLabel(STATUS);
        statusLabel.setForeground(Color.green);
        headerSpace.add(statusLabel);
        headerSpace.add(Box.createHorizontalStrut(400));
        headerSpace.add(header);
        headerSpace.add(Box.createHorizontalStrut(800));

        return headerSpace;
    }


    public JPanel mainArea() {
        mainArea = new JPanel();
        addNewMeal = new JButton("Add New Meal");
        addNewFavouriteMeal = new JButton("Add New Favourite Meal");
        setCalorieGoal = new JButton("Set New Calorie Goal");
        seeFavouriteMeals = new JButton("See Favourite Meals");
        seeKrabbyPatty = new JButton("Click for Krabby Patty");

        addNewMeal = newMealButton();
        addNewFavouriteMeal = newFavouriteMealButton();
        setCalorieGoal = setCalorieGoalButton();
        seeFavouriteMeals = seeFavouriteMealsButton();
        seeKrabbyPatty = setKrabbyPattyButton();
        saveButton = saveButton();
        loadButton = loadButton();

        mainArea.add(addNewMeal);
        mainArea.add(addNewFavouriteMeal);
        mainArea.add(setCalorieGoal);
        mainArea.add(seeFavouriteMeals);

        mainArea.add(saveButton);
        mainArea.add(loadButton);
        mainArea.add(seeKrabbyPatty);
        return mainArea;
    }

    //MODIFIES: this
    //EFFECT: Creates a new button
    public JButton newMealButton() {
        JButton newMealButton = new JButton("Add New Meal");
        newMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                newMealPanel = newMealPanel();
                mainFrame.getContentPane().add(newMealPanel);

                mainFrame.setVisible(true);

                revalidate();
                repaint();
            }
        });
        return newMealButton;
    }

    //MODIFIES: this
    //EFFECT: Creates a new button
    public JButton newFavouriteMealButton() {
        JButton newFavouriteMealButton = new JButton("Add New Favourite Meal");
        newFavouriteMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                newFavouriteMealPanel = newFavouriteMealPanel();
                mainFrame.getContentPane().add(newFavouriteMealPanel);

                mainFrame.setVisible(true);

                revalidate();
                repaint();
            }
        });
        return newFavouriteMealButton;
    }

    //MODIFIES: this
    //EFFECT: Creates a new button
    public JButton setCalorieGoalButton() {
        JButton setCalorieGoalButton = new JButton("Set Calorie Goal");
        setCalorieGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                newCaloriesPanel = newSetCaloriesPanel();
                mainFrame.getContentPane().add(newCaloriesPanel);

                mainFrame.setVisible(true);

                revalidate();
                repaint();
            }
        });
        return setCalorieGoalButton;
    }

    //MODIFIES: this
    //EFFECT: Creates a new button
    public JButton setKrabbyPattyButton() {
        JButton seeKrabbyPattyButton = new JButton("Click for Krabby Patty");
        seeKrabbyPattyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                newKrabbyPattyPanel = newSetKrabbyPattyPanel();
                mainFrame.getContentPane().add(newKrabbyPattyPanel);
                mainFrame.pack();

                mainFrame.setVisible(true);

                revalidate();
                repaint();
            }
        });
        return seeKrabbyPattyButton;
    }

    //MODIFIES: this
    //EFFECT: Creates a new button
    public JButton seeFavouriteMealsButton() {
        JButton favouriteMealButton = new JButton("See Favourite Meals");
        favouriteMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                newSeeFavouriteMeal = newSeeFavouriteMealsPanel();
                mainFrame.getContentPane().add(newSeeFavouriteMeal);

                mainFrame.setVisible(true);

                revalidate();
                repaint();
            }
        });
        return favouriteMealButton;
    }

    //MODIFIES: this
    //EFFECT: Creates a new button that writes data to a JSON file
    public JButton saveButton() {
        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(300,25));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(mealFile);
                    jsonWriter.close();
                } catch (FileNotFoundException exception) {
                    statusLabel.setText("No File Found");
                    statusLabel.setForeground(Color.RED);
                }

                statusLabel.setText("Saved");
            }
        });
        return saveButton;
    }

    //MODIFIES: this
    //EFFECT: Creates a button that reads the JSON data
    public JButton loadButton() {
        loadButton = new JButton("Load");
        loadButton.setPreferredSize(new Dimension(300,25));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mealFile = jsonReader.read();
                } catch (IOException ex) {
                    statusLabel.setText("No File Found");
                    statusLabel.setForeground(Color.RED);
                }
                statusLabel.setText("Loaded");
            }
        });
        return loadButton;
    }

    //MODIFIES: this
    //EFFECT: Creates a new panel that displays after clicking on btn
    public JPanel newMealPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel overallMealPanel = newAddMealPanel();
        JPanel okButton = okButtonPanelForNewMeal();
        panel.add(overallMealPanel);
        panel.add(okButton);
        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a new panel that displays after clicking on btn
    public JPanel newFavouriteMealPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel overallFavouriteMealPanel = newAddFavouriteMealPanel();
        JPanel okButton = okButtonPanelForNewFavouriteMeal();

        panel.add(overallFavouriteMealPanel);
        panel.add(okButton);

        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a new panel that displays after clicking on btn
    public JPanel okButtonPanelForNewMeal() {
        JPanel panel = new JPanel();
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mealName = mealTextField.getText();
                double portion = Double.parseDouble(portionTextField.getText());
                double calories = Double.parseDouble(caloriesTextField.getText());
                double protein = Double.parseDouble(proteinTextField.getText());
                double fat = Double.parseDouble(fatTextField.getText());
                double carbs = Double.parseDouble(carbsTextField.getText());
                int date = Integer.parseInt(dateTextField.getText());
                Meal meal = new Meal(mealName, portion, calories, protein, fat, carbs);
                meal.setDate(date);
                mealFile.addMeal(meal);
                newMealPanel.setVisible(false);
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().add(headerSpace(),BorderLayout.NORTH);
                mainFrame.getContentPane().add(mainArea, BorderLayout.CENTER);
                mainFrame.setVisible(true);
                revalidate();
                repaint();
            }
        });
        panel.add(button);
        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a new panel that displays after clicking on btn
    public JPanel okButtonPanelForNewFavouriteMeal() {
        JPanel panel = new JPanel();
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mealName = mealTextField.getText();
                double portion = Double.parseDouble(portionTextField.getText());
                double calories = Double.parseDouble(caloriesTextField.getText());
                double protein = Double.parseDouble(proteinTextField.getText());
                double fat = Double.parseDouble(fatTextField.getText());
                double carbs = Double.parseDouble(carbsTextField.getText());
                int date = Integer.parseInt(dateTextField.getText());
                Meal meal = new Meal(mealName, portion, calories, protein, fat, carbs);
                meal.setDate(date);
                meal.setFavourite(true);
                newFavouriteMealPanel.setVisible(false);
                mealFile.addFavouriteMeals(meal);
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().add(headerSpace(),BorderLayout.NORTH);
                mainFrame.getContentPane().add(mainArea, BorderLayout.CENTER);
                mainFrame.setVisible(true);
                revalidate();
                repaint();
            }
        });
        panel.add(button);
        return panel;
    }

    //THIS
    //EFFECT: Creates a new panel that displays after clicking on btn
    public JPanel okButtonPanelForCalorie() {
        JPanel panel = new JPanel();
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCaloriesPanel.setVisible(false);
                int calorieGoal = Integer.parseInt(caloriesTextField.getText());
                mealFile.setCalorieGoal(calorieGoal);
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().add(headerSpace(),BorderLayout.NORTH);
                mainFrame.getContentPane().add(mainArea, BorderLayout.CENTER);
                mainFrame.setVisible(true);
                revalidate();
                repaint();

            }
        });
        panel.add(button);

        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a new panel that displays after clicking on btn
    public JPanel okButtonPanelForKrabbyPatty() {
        JPanel panel = new JPanel();
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newKrabbyPattyPanel.setVisible(false);
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().add(headerSpace(),BorderLayout.NORTH);
                mainFrame.getContentPane().add(mainArea, BorderLayout.CENTER);
                mainFrame.setVisible(true);
                revalidate();
                repaint();

            }
        });
        panel.add(button);

        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a new panel that displays after clicking on btn
    public JPanel okButtonPanelForSeeFavouriteMeals() {
        JPanel panel = new JPanel();
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newSeeFavouriteMeal.setVisible(false);
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().add(headerSpace(),BorderLayout.NORTH);
                mainFrame.getContentPane().add(mainArea, BorderLayout.CENTER);
                mainFrame.setVisible(true);
                revalidate();
                repaint();
            }
        });
        panel.add(button);

        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a new panel that displays after clicking on btn
    public JPanel newSeeFavouriteMealsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel favouriteMealPanel = newSeeFavouriteMealInnerPanel();
        JPanel okButton = okButtonPanelForSeeFavouriteMeals();

        panel.add(favouriteMealPanel);
        panel.add(okButton);

        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a list of favourite meals and the delete button
    public JPanel newSeeFavouriteMealInnerPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Favourite Meals");
        DefaultListModel<String> favouriteMeals = new DefaultListModel<>();
        for (Meal m : mealFile.getFavouriteMeals()) {
            favouriteMeals.addElement(m.getName());
        }

        JList<String> favouriteMealsList = new JList<>(favouriteMeals);
        favouriteMealsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton deleteButton = new JButton("Delete Selected Expenses");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedMeal(favouriteMealsList, favouriteMeals);
            }
        });
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);
        panel.add(favouriteMealsList);
        panel.add(deleteButton, BorderLayout.SOUTH);

        return panel;
    }

    //REQUIRES: Non-empty list
    //MODIFIES: getFavouriteMeals
    //EFFECT: delete selected meal from list of favourite meals
    private void deleteSelectedMeal(JList<String> list, DefaultListModel<String> model) {
        int selectedMeal = list.getSelectedIndex();
        if (!(selectedMeal <= -1)) {
            String deletedMealName = model.getElementAt(selectedMeal);
            model.remove(selectedMeal);

            ArrayList<Meal> favouriteMealsCopy = new ArrayList<>(mealFile.getFavouriteMeals());
            for (Meal m : favouriteMealsCopy) {
                if (deletedMealName.equals(m.getName())) {
                    System.out.println();
                    mealFile.removeFavouriteMeal(m);
                    break;
                }
            }

        }
    }

    //MODIFIES: this
    //EFFECT: Creates a new panel that displays after clicking on btn
    public JPanel newSetCaloriesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel caloriePanel = newSetCaloriePanel();
        JPanel okButton = okButtonPanelForCalorie();

        panel.add(caloriePanel);
        panel.add(okButton);

        return panel;
    }

    //MODIFIES: this
    //EFFECT: create new panel
    public JPanel newSetKrabbyPattyPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel krabbyPattyPanel = newSetKrabbyPattysPanel();
        JPanel okButton = okButtonPanelForKrabbyPatty();

        panel.add(krabbyPattyPanel);
        panel.add(okButton);

        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a new form after parent btn is pressed.
    public JPanel newSetCaloriePanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Calorie Goal");
        caloriesTextField = new JTextField();
        caloriesTextField.setPreferredSize(new Dimension(100, 25));

        panel.add(label);
        panel.add(caloriesTextField);

        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a new form after parent btn is pressed.
    public JPanel newSetKrabbyPattysPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        ImageIcon image = new ImageIcon("./data/krabby-patty.jpg");
        JLabel label = new JLabel(image);
        panel.add(label);
        return panel;
    }

    //MODIFIES this
    //EFFECT: Creates a new form after parent btn is pressed.
    public JPanel newAddMealPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,500));
        JPanel mealPanel = newMealNamePanel();
        JPanel portionPanel = newPortionPanel();
        JPanel caloriePanel = newCaloriesPanel();
        JPanel proteinPanel = newProteinPanel();
        JPanel fatPanel = newFatPanel();
        JPanel carbsPanel = newCarbsPanel();
        JPanel datePanel = newDatePanel();
        panel.add(mealPanel);
        panel.add(portionPanel);
        panel.add(caloriePanel);
        panel.add(proteinPanel);
        panel.add(fatPanel);
        panel.add(carbsPanel);
        panel.add(datePanel);

        return panel;
    }

    //MODIFIES: this
    //EFFECT: Creates a new form after parent btn is pressed.
    public JPanel newAddFavouriteMealPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,500));

        JPanel mealPanel = newMealNamePanel();
        JPanel portionPanel = newPortionPanel();
        JPanel caloriePanel = newCaloriesPanel();
        JPanel proteinPanel = newProteinPanel();
        JPanel fatPanel = newFatPanel();
        JPanel carbsPanel = newCarbsPanel();
        JPanel datePanel = newDatePanel();

        panel.add(mealPanel);
        panel.add(portionPanel);
        panel.add(caloriePanel);
        panel.add(proteinPanel);
        panel.add(fatPanel);
        panel.add(carbsPanel);
        panel.add(datePanel);

        return panel;
    }

    //EFFECT: creates a new text entry for Meal Name
    public JPanel newMealNamePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Meal Name");
        mealTextField = new JTextField();
        mealTextField.setPreferredSize(new Dimension(200, 25));
        panel.add(label);
        panel.add(mealTextField);
        return panel;
    }

    //EFFECT: creates a new text entry for Portion size
    public JPanel newPortionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Portion Size (g):");
        portionTextField = new JTextField();
        portionTextField.setPreferredSize(new Dimension(200, 25));
        panel.add(label);
        panel.add(portionTextField);
        return panel;
    }

    //EFFECT: creates a new text entry for calorie amount
    public JPanel newCaloriesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Calories (g): ");
        caloriesTextField = new JTextField();
        caloriesTextField.setPreferredSize(new Dimension(200, 25));
        panel.add(label);
        panel.add(caloriesTextField);
        return panel;
    }

    //EFFECT: creates a new text entry for protein amount
    public JPanel newProteinPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Protein (g): ");
        proteinTextField = new JTextField();
        proteinTextField.setPreferredSize(new Dimension(200, 25));
        panel.add(label);
        panel.add(proteinTextField);
        return panel;
    }

    //EFFECT: creates a new text entry for fat amount
    public JPanel newFatPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Fat (g): ");
        fatTextField = new JTextField();
        fatTextField.setPreferredSize(new Dimension(200, 25));
        panel.add(label);
        panel.add(fatTextField);
        return panel;
    }

    //EFFECT: creates a new text entry for carb amount
    public JPanel newCarbsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Carbohydrates (g): ");
        carbsTextField = new JTextField();
        carbsTextField.setPreferredSize(new Dimension(200, 25));
        panel.add(label);
        panel.add(carbsTextField);
        return panel;
    }

    //EFFECT: creates a new text entry for setting the date
    public JPanel newDatePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Date (DDMMYY): ");
        dateTextField = new JTextField();
        dateTextField.setPreferredSize(new Dimension(200, 25));
        panel.add(label);
        panel.add(dateTextField);
        return panel;
    }


}
