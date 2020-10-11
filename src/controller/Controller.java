package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.Player;
import view.ConfigScreen;
import view.FarmScreen;
import view.MarketScreen;
import view.WelcomeScreen;


public class Controller extends Application {
    private static Stage mainWindow;
    private final static int width = 830;
    private final static int height = 830;
    private static String currentView; //"Welcome", "Config", "Farm", "Market"

    @Override
    public void start(Stage primaryStage) {
        mainWindow = primaryStage;
        primaryStage.setTitle("FARM GAME");
        initWelcomeScreen();
    }

    private static void initWelcomeScreen() {
        currentView = "Welcome";
        WelcomeScreen welcomeScreen = new WelcomeScreen(width, height);
        Button startButton = welcomeScreen.getStartButton();
        startButton.setOnAction(e -> goToConfig());

        Scene scene = welcomeScreen.getScene();
        scene.getStylesheets().add("file:resources/css/WelcomeScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private static void goToConfig() {
        currentView = "Config";
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

    public static void enterFarm(String difficulty, String seed) {
        currentView = "Farm";
        FarmScreen farmScreen = new FarmScreen(width, height, difficulty, seed);
        Scene scene = farmScreen.getScene();
        scene.getStylesheets().add("file:resources/css/FarmScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void enterMarket(Player player, String difficulty, String seed) {
        currentView = "Market";
        MarketScreen marketScreen = new MarketScreen(width, height, difficulty, seed, player);
        Scene scene = marketScreen.getScene();
        scene.getStylesheets().add("file:resources/css/FarmScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static String getCurrentView() { return currentView; }

    /**
     * Main method.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}