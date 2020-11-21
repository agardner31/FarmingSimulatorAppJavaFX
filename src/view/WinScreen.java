package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WinScreen implements IScreen{
    private int width;
    private int height;
    private Button playAgain;

    public WinScreen(int width, int height, Player player) {
        this.width = width;
        this.height = height;
        this.playAgain = new Button("Play Again");

    }

    @Override
    public Scene getScene() {
        playAgain.setOnAction((e) -> {
            Controller.initWelcomeScreen();
        });

        Media cheering = new Media(new File("audio/cheering.mp3").toURI().toString());
        MediaPlayer winNoise = new MediaPlayer(cheering);
        winNoise.play();
        Image win = null;
        try {
            win = new Image(new FileInputStream("images/win.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView winImage = new ImageView(win);
        winImage.setPreserveRatio(true);
        winImage.setFitWidth(width);

        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(winImage, playAgain);
        vbox.getStyleClass().add("vBox");

        return new Scene(vbox, width, height);
    }



}
