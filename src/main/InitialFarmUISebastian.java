package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InitialFarmUISebastian extends Application {

    @Override
    public void start(Stage primaryStage) {
        int difficulty = 0; //THIS VARIABLE WILL EVENTUALLY BE SET BY THE USER FROM THE INITIAL CONFIG SCREEN.
        Player player = new Player(difficulty);
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
        Scene scene = new Scene(vbox, 800, 700);
        primaryStage.setTitle("FARM GAME");
        primaryStage.setScene(scene);
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