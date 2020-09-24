package BestTeam89_Farming.src.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Date;

public class InitialFarmUISebastian extends Application {

    @Override
    public void start(Stage primaryStage) {
        int difficulty = 0; //THIS VARIABLE WILL EVENTUALLY BE SET BY THE USER FROM THE INITIAL CONFIG SCREEN.
        Date date = new Date(2020, 1, 1);
        Player player = new Player(difficulty);
        double startingMoney = Player.getMoney();
        VBox vbox = new VBox();
        vbox.setMaxSize(800, 800);
        Label startingMoneyLabel = new Label("Starting money: $" + startingMoney);
        vbox.getChildren().add(startingMoneyLabel);
        Label displayDateLabel = new Label("Today's date: " + date.toString());
        Plot[] plots = Player.getFarm().getPlots();
        for (int i = 0; i < plots.length; i++) {
            vbox.getChildren().add(plots[i]);
        }
        Scene scene = new Scene(vBox 800, 700);
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