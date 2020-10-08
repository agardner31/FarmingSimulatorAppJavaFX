package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Crop;
import model.CropStage;
import model.Inventory;
import model.Player;
import model.Plot;

public class FarmScreen implements IScreen {
    private int width;
    private int height;
    private Player player;
    private Label displayDateLabel;
    private Label moneyLabel;
    private Plot[] plots;
    private Inventory inventory;
    private GridPane inventoryPane;
    private Button inventoryButton;
    private HBox plotBox;

    public FarmScreen(int width, int height, String difficulty, String startSeed) {
        this.width = width;
        this.height = height;
        player = new Player(difficulty, startSeed);
        displayDateLabel = new Label("Day 1");
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
            VBox cropBox = new VBox();
            Label cropType = new Label();
            Label cropStage = new Label();
            Label cropPrice = new Label();
            if (crop != null) {
                cropType = new Label(crop.getType());
                if (crop.getStage().equals(CropStage.SEED)) {
                    cropStage = new Label("Seed");
                } else if (crop.getStage().equals(CropStage.IMMATURE)) {
                    cropStage = new Label("Immature");
                } else {
                    cropStage = new Label("Mature");
                }
                if (crop.getStage().equals(CropStage.MATURE)) {
                    cropPrice = new Label("$" + crop.getSellPrice() + ".00");
                } else {
                    cropPrice = new Label("$0.00");
                }
            }
            cropBox.getChildren().addAll(cropType, cropStage, cropPrice);
            cropBox.getStyleClass().add("cropBox");
            if (i % 10 == 0) {
                j++;
            }
            final int tempIndex = i;
            cropBox.setOnMouseClicked((e) -> {
                removeItem(tempIndex); //how to get the specific inventory item
            });

            inventoryPane.add(cropBox, i % 10, j);
        }
        inventory.setInventoryPane(inventoryPane);
        inventoryPane.getStyleClass().add("inventoryPane");
        return inventoryPane;
    }

    public void removeItem(int index) {
        inventory.getInventoryArray()[index] = null;
        removeFromPane(index);
        inventory.setSize(inventory.getSize() - 1);
    }

    private void removeFromPane(int index) {
        VBox temp = (VBox) inventoryPane.getChildren().get(index);
        Label temp2 = (Label) temp.getChildren().get(0);
        Label temp3 = (Label) temp.getChildren().get(1);
        Label temp4 = (Label) temp.getChildren().get(2);
        temp2.setText("");
        temp3.setText("");
        temp4.setText("");
    }

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

        VBox vbox = new VBox(moneyLabel, displayDateLabel, plotBox, inventoryWithLabel);

        inventoryButton.getStyleClass().add("inventoryButton");
        inventoryLabel.getStyleClass().add("inventoryLabel");
        moneyLabel.getStyleClass().add("moneyLabel");
        displayDateLabel.getStyleClass().add("displayDateLabel");
        plotBox.getStyleClass().add("plotBox");
        vbox.getStyleClass().add("vBox");

        VBox finalScene = new VBox(inventoryButton, vbox);
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
