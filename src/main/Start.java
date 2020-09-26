package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Start extends Application {

    public static void main(String [] args) {
        launch(args);
    }

    Stage start;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        start = primaryStage;
        Label welcome = new Label("Farming Game");
        Button startButton = new Button("Change to next scene");
        startButton.setOnAction(e -> start.setScene(scene1));

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(welcome, startButton);
        scene1 = new Scene(vBox, 200, 200);
    }
}
