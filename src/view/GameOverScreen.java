package view;

import javafx.scene.Scene;
import model.Player;

public class GameOverScreen implements IScreen {
    private int width;
    private int height;
    private Player player;

    public GameOverScreen(int width, int height, Player player) {
        this.width = width;
        this.height = height;
        this.player = player;
    }


    @Override
    public Scene getScene() {
        return null;
    }
}
