package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Player;

public class ConfigScreen implements IScreen {
    private int width;
    private int height;
    private Button startGame;
    private Label warning;
    private TextField enterName;
    private Player player;

    public ConfigScreen(Player player, int width, int height) {
        this.width = width;
        this.height = height;
        startGame = new Button("Start Game");
        warning = new Label("");
        enterName = new TextField();
        this.player = player;
        ComboBoxFactory factory = new ComboBoxFactory();
    }

    public Scene getScene() {
        // configuration scene

        ComboBox<String> levels = ComboBoxFactory.getLevels();
        ComboBox<String> seasonsList = ComboBoxFactory.getSeasons();
        ComboBox<String> seedList = ComboBoxFactory.getSeeds();

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

        Button loadGameButton = new Button("Load Previous Game");
        loadGameButton.setVisible(false);
        if (player != null) {
            loadGameButton.setVisible(true);
        }
        loadGameButton.setOnMouseClicked((e) -> {
            if (player != null) {
                Controller.enterFarm(player, player.getDifficulty(), false);
            }
        });

        configVBox.getChildren().add(loadGameButton);

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
        return ComboBoxFactory.getLevels();
    }

    public ComboBox<String> getSeasonsList() {
        return ComboBoxFactory.getSeasons();
    }

    public ComboBox<String> getSeedList() {
        return ComboBoxFactory.getSeeds();
    }
}
