package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Farm;
import model.Player;
import view.*;
import view.FarmScreen;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;


public class Controller extends Application {
    private static Stage mainWindow;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 880;
    private static String currentView; //"Welcome", "Config", "Farm", "Market"
    private static String name;
    private static MediaPlayer mediaPlayer;


    @Override
    public void start(Stage primaryStage) {
        mainWindow = primaryStage;
        primaryStage.setTitle("FARM GAME");
        initWelcomeScreen();
    }

    public static void initWelcomeScreen() {
        currentView = "Welcome";
        WelcomeScreen welcomeScreen = new WelcomeScreen(WIDTH, HEIGHT);
        Button startButton = welcomeScreen.getStartButton();

        //de-serialize previous player if it exists
        Player previousGame = null;
        try {
            FileInputStream fi = new FileInputStream(new File("previous game/previousGame.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            //get previous game
            previousGame = (Player) oi.readObject();

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            //previousGame = null;
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Player finalPreviousGame = previousGame;
        startButton.setOnAction(e -> goToConfig(finalPreviousGame));
        playMusic(); //plays background music on repeat
        Scene scene = welcomeScreen.getScene();
        scene.getStylesheets().add("file:resources/css/WelcomeScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private static void goToConfig(Player previousPlayer) {
        currentView = "Config";
        ConfigScreen configScreen = new ConfigScreen(previousPlayer, WIDTH, HEIGHT);
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
                name = enterName.getText();
                //line below is probably where the exception comes from
                String level = "Apprentice"; //levels.getValue();
                String seed = "Tomato"; //seedList.getValue();
                String season = "Winter"; //seasonsList.getValue();
                Player player = new Player(level, new Farm(level),
                        seed, season);
                enterFarm(player, level, false);
            }
        });
        Scene scene = configScreen.getScene();
        scene.getStylesheets().add("file:resources/css/ConfigScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }


    public static void enterFarm(Player player, String difficulty, boolean inventoryVisible) {
        boolean plotCheck = true;
        for (int i = 0; i < player.getFarm().getNumPlots(); i++) {
            if (player.getFarm().getPlots().get(i).getCrop() != null) {
                plotCheck = false;
            }
        }

        //if money is less than cheapest possible item
        if (player.getMoney() < player.getCheapestPrice() && plotCheck) {
            gameOver(player);
        } else {
            currentView = "Farm";
            FarmScreen farmScreen = new FarmScreen(WIDTH, HEIGHT, player, inventoryVisible);
            Scene scene = farmScreen.getScene();
            scene.getStylesheets().add("file:resources/css/FarmScreen.css");
            mainWindow.setScene(scene);
            mainWindow.show();
        }
    }

    public static void enterMarket(Player player, String difficulty) {
        currentView = "Market";
        MarketScreen marketScreen = new MarketScreen(WIDTH, HEIGHT, difficulty, player);
        Scene scene = marketScreen.getScene();
        scene.getStylesheets().add("file:resources/css/FarmScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void gameOver(Player player) {
        currentView = "GameOver";
        GameOverScreen gameOverScreen = new GameOverScreen(WIDTH, HEIGHT, player, name);
        Scene scene = gameOverScreen.getScene();
        scene.getStylesheets().add("file:resources/css/GameOverScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void win(Player player) {
        currentView = "Win";
        WinScreen winScreen = new WinScreen(WIDTH, HEIGHT, player);
        Scene scene = winScreen.getScene();
        scene.getStylesheets().add("file:resources/css/WinScreen.css");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private static void playMusic() {
        Media backgroundMusic = new Media(new File("audio/background.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(backgroundMusic);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
    }

    public static String getCurrentView() {
        return currentView;
    }

    /**
     * Main method.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}