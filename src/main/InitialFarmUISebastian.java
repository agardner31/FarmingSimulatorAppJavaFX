package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InitialFarmUISebastian extends Application {

    Scene configurations;
    Scene farm;

    @Override
    public void start(Stage primaryStage) {

        // configuration scene
        VBox configVBox = new VBox();
        HBox nameBox = new HBox();
        Label name = new Label("Name: ");
        TextField enterName = new TextField();
        nameBox.getChildren().addAll(name, enterName);
        HBox difficultyBox = new HBox();
        Label difficulty = new Label("Difficulty: ");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "",
                        "Apprentice",
                        "Ordinary Joe",
                        "Master Farmer"
                );
        ComboBox levels = new ComboBox(options);
        difficultyBox.getChildren().addAll(difficulty, levels);
        HBox seasonsBox = new HBox();
        Label seasons = new Label("Season: ");
        ObservableList<String> seasonsOptions =
                FXCollections.observableArrayList(
                        "",
                        "Fall",
                        "Winter",
                        "Spring",
                        "Summer"
                );
        ComboBox seasonsList = new ComboBox(seasonsOptions);
        seasonsBox.getChildren().addAll(seasons, seasonsList);
        HBox seedBox = new HBox();
        Label seed = new Label("Seed: ");
        ObservableList<String> seedOptions =
                FXCollections.observableArrayList(
                        "",
                        "Tomato",
                        "Pumpkin",
                        "Corn"
                );
        ComboBox seedList = new ComboBox(seedOptions);
        seedBox.getChildren().addAll(seed, seedList);
        Button startGame = new Button("Start Game");
        Label warning = new Label("");
        configVBox.getChildren().addAll(nameBox, difficultyBox, seasonsBox, seedBox, startGame, warning);
        Scene configurations = new Scene(configVBox, 800, 700);
        System.out.print(levels.getValue());

        // farm scene
        // int difficulty = 0; //THIS VARIABLE WILL EVENTUALLY BE SET BY THE USER FROM THE INITIAL CONFIG SCREEN.
        Player player = new Player("Apprentice");
        double startingMoney = player.getMoney();
        VBox vbox = new VBox();
        vbox.setMaxSize(800, 800);
        Label startingMoneyLabel = new Label("Starting money: $" + startingMoney);
        vbox.getChildren().add(startingMoneyLabel);
        Label displayDateLabel = new Label("Today's date: Day 1");
        vbox.getChildren().add(displayDateLabel);
        Plot[] plots = player.getFarm().getPlots();
        int plotNumber;
        for (int i = 0; i < plots.length; i++) {
            plotNumber = i + 1;
            vbox.getChildren().add(new Label("Plot " + plotNumber));
            vbox.getChildren().add(new Label("Title: " + plots[i].getTitle() + " Type: " + plots[i].getType() + " Number of Crops " + plots[i].getNumCrops()));
        }
        Scene farm = new Scene(vbox, 800, 700);

        // start game button
        startGame.setOnAction((event) -> {    // lambda expression
            if ((enterName.getText() == null) || (enterName.getText().equals(""))) {
                warning.setText("Enter a valid name.");
            } else if ((levels.getValue() == null) || (levels.getValue().equals(""))) {
                warning.setText("Choose a difficulty.");
            } else if ((seasonsList.getValue() == null) || (seasonsList.getValue().equals(""))) {
                warning.setText("Choose a season.");
            } else if ((seedList.getValue() == null) || (seedList.getValue().equals(""))) {
                warning.setText("Choose a seed.");
            } else {
                primaryStage.setScene(farm);
            }
            System.out.print(levels.getValue());
        });

        primaryStage.setTitle("FARM GAME");
        primaryStage.setScene(configurations);
        primaryStage.show();
    }



    /**
     * Main method.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}