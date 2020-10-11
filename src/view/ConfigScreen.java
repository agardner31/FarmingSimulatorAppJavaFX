package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConfigScreen implements IScreen {
    private int width;
    private int height;
    private Button startGame;
    private Label warning;
    private TextField enterName;
    private ComboBox<String> levels;
    private ObservableList<String> options;
    private ObservableList<String> seasonsOptions;
    private ComboBox<String> seasonsList;
    private ObservableList<String> seedOptions;
    private ComboBox<String> seedList;

    public ConfigScreen(int width, int height) {
        this.width = width;
        this.height = height;
        startGame = new Button("Start Game");
        warning = new Label("");
        enterName = new TextField();
        options = FXCollections.observableArrayList("Apprentice", "Ordinary Joe", "Master Farmer");
        levels = new ComboBox<>(options);
        seasonsOptions = FXCollections.observableArrayList("Fall", "Winter", "Spring", "Summer");
        seasonsList = new ComboBox<>(seasonsOptions);
        seedOptions = FXCollections.observableArrayList("Tomato", "Pumpkin", "Corn");
        seedList = new ComboBox<>(seedOptions);
    }

    public Scene getScene() {
        // configuration scene
        Label configTitle = new Label("Configuration");
        configTitle.getStyleClass().add("configTitle");

        Label name = new Label("Name: ");
        HBox nameBox = new HBox(name, enterName);
        nameBox.getStyleClass().add("nameBox");

        Label difficulty = new Label("Difficulty: ");
        HBox difficultyBox = new HBox(difficulty, levels);
        difficultyBox.getStyleClass().add("difficultyBox");

        Label seasons = new Label("Season: ");
        HBox seasonsBox = new HBox(seasons, seasonsList);
        seasonsBox.getStyleClass().add("seasonsBox");

        Label seed = new Label("Seed: ");
        HBox seedBox = new HBox(seed, seedList);
        seedBox.getStyleClass().add("seedBox");

        VBox configVBox = new VBox(configTitle, nameBox, difficultyBox,
                seasonsBox, seedBox, startGame, warning);
        configVBox.getStyleClass().add("vBox");

        return new Scene(configVBox, width, height);
    }

    public Button getStartGame() {
        return startGame;
    }

    public Label getWarning() {
        return warning;
    }

    public TextField getEnterName() {
        return enterName;
    }

    public ComboBox<String> getLevels() {
        return levels;
    }

    public ComboBox<String> getSeasonsList() {
        return seasonsList;
    }

    public ComboBox<String> getSeedList() {
        return seedList;
    }
}
