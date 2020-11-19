package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Player;

public class GameOverScreen implements IScreen {
    private int width;
    private int height;
    private Player player;
    private Label name;
    private Label gameOver;
    private Label count;
    private Button playAgain;

    public GameOverScreen(int width, int height, Player player, String name) {
        this.width = width;
        this.height = height;
        this.player = player;
        this.name = new Label("Sorry " + name + "...");
        this.gameOver = new Label("Game Over!");
        if (player.getDay() == 1) {
            if (player.getNumCropsHarvested() == 1) {
                this.count = new Label("You lasted " + player.getDay() + " day and harvested "
                        + player.getNumCropsHarvested() + " crop");
            } else {
                this.count = new Label("You lasted " + player.getDay() + " day and harvested "
                        + player.getNumCropsHarvested() + " crops");
            }
        } else {
            if (player.getNumCropsHarvested() == 1) {
                this.count = new Label("You lasted " + player.getDay() + " days and harvested "
                        + player.getNumCropsHarvested() + " crop");
            } else {
                this.count = new Label("You lasted " + player.getDay() + " days and harvested "
                        + player.getNumCropsHarvested() + " crops");
            }
        }
        this.playAgain = new Button("Play Again");
    }


    @Override
    public Scene getScene() {
        name.getStyleClass().add("name");
        gameOver.getStyleClass().add("gameOver");
        count.getStyleClass().add("count");

        playAgain.setOnAction((e) -> {
            Controller.initWelcomeScreen();
        });

        Region spacer1 = new Region();
        VBox.setVgrow(spacer1, Priority.ALWAYS);
        Region spacer2 = new Region();
        VBox.setVgrow(spacer2, Priority.ALWAYS);
        Region spacer3 = new Region();
        VBox.setVgrow(spacer3, Priority.ALWAYS);

        VBox finalScene = new VBox(spacer1, name, gameOver, playAgain, count, spacer2, spacer3);
        finalScene.getStyleClass().add("finalScene");


        return new Scene(finalScene, width, height);
    }
}
