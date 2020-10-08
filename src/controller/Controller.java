package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import view.ConfigScreen;
import view.FarmScreen;
import view.WelcomeScreen;


public class Controller extends Application {
    private Stage mainWindow;
    private final int width = 820;
    private final int height = 820;

    @Override
    public void start(Stage primaryStage) {
        mainWindow = primaryStage;
        primaryStage.setTitle("FARM GAME");
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(width, height);
        Button startButton = welcomeScreen.getStartButton();
        startButton.setOnAction(e -> goToConfig());

        Scene scene = welcomeScreen.getScene();
        scene.getStylesheets().add("file:resources/css/WelcomeScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToConfig() {
        ConfigScreen configScreen = new ConfigScreen(width, height);
        Button startGame = configScreen.getStartGame();
        Label warning = configScreen.getWarning();
        TextField enterName = configScreen.getEnterName();
        ComboBox<String> levels = configScreen.getLevels();
        ComboBox<String> seasonsList = configScreen.getSeasonsList();
        ComboBox<String> seedList = configScreen.getSeedList();
        startGame.setOnAction((event) -> {    // lambda expression
            if ((enterName.getText() == null) || (enterName.getText().equals(""))) {
                warning.setText("Enter a valid name.");
            } else if (levels.getValue() == null) {
                warning.setText("Choose a difficulty.");
            } else if (seasonsList.getValue() == null) {
                warning.setText("Choose a season.");
            } else if (seedList.getValue() == null) {
                warning.setText("Choose a seed.");
            } else {
                enterFarm(levels.getValue(), seedList.getValue());
            }
        });
        Scene scene = configScreen.getScene();
        scene.getStylesheets().add("file:resources/css/ConfigScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void enterFarm(String difficulty, String seed) {
        FarmScreen farmScreen = new FarmScreen(width, height, difficulty, seed);

        Scene scene = farmScreen.getScene();
        scene.getStylesheets().add("file:resources/css/FarmScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    /**
     * Main method.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}