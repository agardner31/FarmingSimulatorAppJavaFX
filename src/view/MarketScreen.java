package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Crop;
import model.Inventory;
import model.Player;
import model.Plot;

public class MarketScreen implements IScreen {
    private int width;
    private int height;
    private Player player;
    private Label displayMarketLabel;
    private Label moneyLabel;
    private Plot[] plots;
    private Inventory inventory;
    private GridPane inventoryPane;
    private Button inventoryButton;
    private Button farmButton;
    private HBox plotBox;

    public MarketScreen(int width, int height, String difficulty, String startSeed) {
        this.width = width;
        this.height = height;
        player = new Player(difficulty, startSeed);
        displayMarketLabel = new Label("Market");
        moneyLabel = new Label("Money: $" + player.getMoney() + ".00");
        plots = player.getFarm().getPlots();
        inventory = player.getInventory();
        inventoryPane = getInventoryPane();
        inventoryButton = new Button("Inventory");
        plotBox = fillPlotPane();
    }

    private GridPane getInventoryPane() {
        inventoryPane = new GridPane();
        int j = -1;
        for (int i = 0; i < Inventory.getCAPACITY(); i++) {
            Crop crop = inventory.getInventoryArray()[i];
            Label cropLabel = new Label("");
            if (crop != null) {
                cropLabel = new Label(crop.toString());
            }
            cropLabel.getStyleClass().add("cropBox");
            if (i % 10 == 0) {
                j++;
            }
            final int targetCrop = i;
            cropLabel.setOnMouseClicked((e) -> {
                inventory.removeItem(targetCrop); //how to get the specific inventory item
            });

            inventoryPane.add(cropLabel, i % 10, j);
        }
        inventory.setInventoryPane(inventoryPane);
        inventoryPane.getStyleClass().add("inventoryPane");
        return inventoryPane;
    }

    @Override
    public Scene getScene() {
        // farm scene

        Label inventoryLabel = new Label("Items");

        VBox inventoryWithLabel = new VBox(inventoryLabel, inventoryPane);
        inventoryWithLabel.setVisible(false);

        inventoryButton.setOnAction((e) -> {
            if (!inventoryWithLabel.isVisible()) {
                inventoryWithLabel.setVisible(true);
            } else {
                inventoryWithLabel.setVisible(false);
            }
        });

        //moves to market scene
        farmButton = new Button("Farm");
        farmButton.setVisible(true);
        farmButton.setOnAction((e) -> {
            Controller.enterFarm(player.getDifficulty(), "Pumpkin");
        });

        VBox vbox = new VBox(moneyLabel, displayMarketLabel, plotBox, inventoryWithLabel);

        inventoryButton.getStyleClass().add("inventoryButton");
        inventoryLabel.getStyleClass().add("inventoryLabel");
        moneyLabel.getStyleClass().add("moneyLabel");
        displayMarketLabel.getStyleClass().add("displayDateLabel");
        plotBox.getStyleClass().add("plotBox");
        vbox.getStyleClass().add("vBox");

        HBox buttonRow = new HBox(inventoryButton, farmButton);
        buttonRow.setSpacing(10);
        VBox finalScene = new VBox(buttonRow, vbox);
        finalScene.setStyle("-fx-background-color: #658E6E; -fx-padding: 15");

        return new Scene(finalScene, width, height);
    }

    private HBox fillPlotPane() {
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
        return plotBox;
    }


    public void harvestCrop() {
        //harvest a crop
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
