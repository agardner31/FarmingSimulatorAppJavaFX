package view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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

    public FarmScreen(int width, int height, String difficulty) {
        this.width = width;
        this.height = height;
        player = new Player(difficulty);
        displayDateLabel = new Label("Day 1");
        moneyLabel = new Label("Money: $" + player.getMoney() + ".00");
        plots = player.getFarm().getPlots();
        inventory = player.getInventory();
        inventoryPane = new GridPane();
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


        for (int i = 0; i < 20; i++) {
            inventory.getInventoryArray()[i] = new Crop("Pumpkin", player.getDifficulty());
        }



        Label inventoryLabel = new Label("Items");
        inventoryLabel.getStyleClass().add("inventoryLabel");

        int j = -1;
        for (int i = 0; i < Inventory.getCAPACITY(); i++) {
            Crop crop = inventory.getInventoryArray()[i];
            VBox cropBox = new VBox();
            Label cropType = new Label("");
            Label cropStage = new Label("");
            Label cropPrice = new Label("");
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
                System.out.println(i);
            }
            final int temp_index = i;
            System.out.println(temp_index);
            cropBox.setOnMouseClicked((e) -> {
                doSomething(temp_index); //how to get the specific inventory item
            });

            inventoryPane.add(cropBox, i % 10, j);
        }
        inventoryPane.getStyleClass().add("inventoryPane");
        VBox inventoryWithLabel = new VBox(inventoryLabel, inventoryPane);
        inventoryWithLabel.setVisible(false);



        Button inventoryButton = new Button("Inventory");
        inventoryButton.setOnAction((e) -> {
            if (!inventoryWithLabel.isVisible()) {
                inventoryWithLabel.setVisible(true);
            } else {
                inventoryWithLabel.setVisible(false);
            }
        });
        inventoryButton.getStyleClass().add("inventoryButton");

        VBox vbox = new VBox(moneyLabel, displayDateLabel, plotBox, inventoryWithLabel);
        vbox.getStyleClass().add("vBox");

        VBox finalScene = new VBox(inventoryButton, vbox);
        finalScene.setStyle("-fx-background-color: #658E6E; -fx-padding: 15");

        return new Scene(finalScene, width, height);
    }

    private void doSomething(int index) {
        Crop tempCrop = inventory.getInventoryArray()[index];
        VBox temp = (VBox) inventoryPane.getChildren().get(index);
        Label temp2 = (Label) temp.getChildren().get(0);
        Label temp3 = (Label) temp.getChildren().get(1);
        Label temp4 = (Label) temp.getChildren().get(2);
        temp2.setText("");
        temp3.setText("");
        temp4.setText("");
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
