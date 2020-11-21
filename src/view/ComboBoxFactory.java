package view;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public class ComboBoxFactory { //allows classes using ComboBox to be serializable
    private static ComboBox<String> levels;
    private static ComboBox<String> seasons;
    private static ComboBox<String> seeds;
    public ComboBoxFactory() {
        levels = new ComboBox<>(FXCollections.observableArrayList("Apprentice", "Ordinary Joe", "Master Farmer"));
        seasons = new ComboBox<>(FXCollections.observableArrayList("Fall", "Winter", "Spring", "Summer"));
        seeds = new ComboBox<>(FXCollections.observableArrayList("Tomato", "Pumpkin", "Corn"));
    }

    public static ComboBox<String> getLevels() {
        return levels;
    }
    public static ComboBox<String> getSeasons() {
        return seasons;
    }
    public static ComboBox<String> getSeeds() {
        return seeds;
    }
}
