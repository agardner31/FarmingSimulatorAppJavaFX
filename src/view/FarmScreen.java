package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Player;
import model.Plot;

public class FarmScreen implements IScreen {
    private int width;
    private int height;
    private Player player;
    private Label displayDateLabel;
    private Label moneyLabel;
    private Plot[] plots;

    public FarmScreen(int width, int height, String difficulty) {
        this.width = width;
        this.height = height;
        player = new Player(difficulty);
        displayDateLabel = new Label("Day 1");
        moneyLabel = new Label("Money: $" + player.getMoney() + ".00");
        plots = player.getFarm().getPlots();
    }
    public Scene getScene() {
        // farm scene
        moneyLabel.getStyleClass().add("moneyLabel");
        displayDateLabel.getStyleClass().add("displayDateLabel");

        HBox plotBox = new HBox();
        for (int i = 0; i < plots.length; i++) {
            Label plotLabel = new Label("Plot #" + (i + 1) + "\n"
                    + "Crops: " + plots[i].getNumCrops());
            plotLabel.setOnMouseEntered(e -> {
                plotLabel.setScaleX(1.5);
                plotLabel.setScaleY(1.5);
            });
            plotLabel.setOnMouseExited(e -> {
                plotLabel.setScaleX(1);
                plotLabel.setScaleY(1);
            });
            plotLabel.getStyleClass().add("plotLabel");
            plotBox.getChildren().add(plotLabel);
        }
        plotBox.getStyleClass().add("plotBox");

        VBox vbox = new VBox(moneyLabel, displayDateLabel, plotBox);
        vbox.getStyleClass().add("vBox");

        return new Scene(vbox, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public Label getDisplayDateLabel() {
        return displayDateLabel;
    }

    public void setDisplayDateLabel(int day) {
        displayDateLabel = new Label("Day " + day);
    }

    public Label getMoneyLabel() {
        return moneyLabel;
    }

    public void setMoneyLabel() {
        this.moneyLabel = new Label("Money: $" + player.getMoney() + ".00");
    }

    public Plot[] getPlots() {
        return plots;
    }
}
