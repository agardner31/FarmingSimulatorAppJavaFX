package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WelcomeScreen implements IScreen {
    private int width;
    private int height;
    private Button startButton;

    public WelcomeScreen(int width, int height) {
        this.width = width;
        this.height = height;
        startButton = new Button("Enter game");
    }


    public Scene getScene() {
        // welcome screen
        Label welcome = new Label("Farming Game");
        welcome.getStyleClass().add("welcome");

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(welcome, startButton);
        vBox.getStyleClass().add("vBox");

        return new Scene(vBox, width, height);
    }

    public Button getStartButton() {
        return startButton;
    }
}
