package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WelcomeScreen implements IScreen {
    private int width;
    private int height;
    private Button startButton;

    public WelcomeScreen(int width, int height) {
        this.width = width;
        this.height = height;
        startButton = new Button("Start");
    }


    public Scene getScene() {
        // welcome screen
        Image welcome = null;
        try {
            welcome = new Image(new FileInputStream("images/FarmingSimulator.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView welcomeImage = new ImageView(welcome);

        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(welcomeImage, startButton);
        vbox.getStyleClass().add("vBox");

        return new Scene(vbox, width, height);
    }

    public Button getStartButton() {
        return startButton;
    }
}
