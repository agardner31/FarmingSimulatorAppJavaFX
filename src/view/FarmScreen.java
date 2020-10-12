package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Crop;
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
    private Button marketButton;
    private HBox plotBox;

    public FarmScreen(int width, int height, String difficulty, String startSeed, Player player) {
        this.width = width;
        this.height = height;
        this.player = player;
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
        for (int i = 0; i < Inventory.getCapacity(); i++) {
            Crop crop;
            Label cropLabel = new Label("");
            try {
                crop = inventory.getInventoryList().get(i);
                if (crop != null) {
                    cropLabel = new Label(crop.toString("sell"));
                }
            } catch (IndexOutOfBoundsException e) { }
            cropLabel.getStyleClass().add("cropBox");
            if (i % 10 == 0) {
                j++;
            }
            /*final int targetCrop = i;
            cropLabel.setOnMouseClicked((e) -> {
                inventory.removeItem(targetCrop); //how to get the specific inventory item
            });*/

            inventoryPane.add(cropLabel, i % 10, j);
        }
        inventory.setInventoryPane(inventoryPane);
        inventoryPane.getStyleClass().add("inventoryPane");
        return inventoryPane;
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

        //moves to market scene
        marketButton = new Button("Market");
        marketButton.setVisible(true);
        marketButton.setOnAction((e) -> {
            Controller.enterMarket(player, player.getDifficulty());
        });

        VBox vbox = new VBox(moneyLabel, displayDateLabel, plotBox, inventoryWithLabel);

        inventoryButton.getStyleClass().add("inventoryButton");
        inventoryLabel.getStyleClass().add("inventoryLabel");
        moneyLabel.getStyleClass().add("moneyLabel");
        displayDateLabel.getStyleClass().add("displayDateLabel");
        plotBox.getStyleClass().add("plotBox");
        vbox.getStyleClass().add("vBox");

        HBox buttonRow = new HBox(inventoryButton, marketButton);
        buttonRow.setSpacing(10);
        VBox finalScene = new VBox(buttonRow, vbox);
        finalScene.setStyle("-fx-background-color: #658E6E; -fx-padding: 15");

        return new Scene(finalScene, width, height);
    }

    private HBox fillPlotPane() {
        HBox plotBox = new HBox(20);
        for (int i = 0; i < plots.length; i++) {
            Plot temp = plots[i];
            Label plotNumber = new Label("Plot#" + (i + 1));
            Label plotType = new Label(temp.getType());
            Label growStage;
            if (!temp.getType().equals("None")) {
                growStage = new Label((temp.getCrop()).getStage().toString());
            } else {
                growStage = new Label("");
            }
            VBox boxOfLabels = new VBox(plotNumber, plotType, growStage);
            boxOfLabels.setOnMouseEntered((e) -> {
                boxOfLabels.setScaleX(1.5);
                boxOfLabels.setScaleY(1.5);
            });
            boxOfLabels.setOnMouseExited((e) -> {
                boxOfLabels.setScaleX(1);
                boxOfLabels.setScaleY(1);
            });

            final int index = i;
            boxOfLabels.getStyleClass().add("plotLabel");
            ImageView img = new ImageView(temp.getImg());
            Button growButton = new Button("Plant");
            growButton.setOnAction((e) -> {
                if (!temp.getType().equals("None")) {
                    if (temp.getCrop().getStage().toString().equals("Mature")) {
                        if (inventory.isFull()) {
                            return;
                        }
                    }
                    temp.getCrop().incrementStage();
                    if (!temp.getType().equals("None")) {
                        growStage.setText(temp.getCrop().getStage().toString());
                    }
                }
                if ((temp.getCrop()).getStage().toString().equals("Dirt")) {
                    growButton.setText("Plant");
                    img.setImage(temp.getImg());
                } else if ((temp.getCrop()).getStage().toString().equals("Seed")) {
                    growButton.setText("Water");
                    img.setImage(temp.getImg());
                } else if ((temp.getCrop()).getStage().toString().equals("Immature")) {
                    growButton.setText("Water");
                    img.setImage(temp.getImg());
                } else if ((temp.getCrop()).getStage().toString().equals("Mature")) {
                    growButton.setText("Harvest");
                    img.setImage(temp.getImg());
                } else if ((temp.getCrop()).getStage().toString().equals("Harvested")) {
                    harvestCrop(temp, plotType, growStage, boxOfLabels);
                    growButton.setText("New Plant");
                    img.setImage(temp.getImg());
                }
            });
            VBox onePlot = new VBox(boxOfLabels, img, growButton);
            plotBox.getChildren().add(onePlot);
        }
        return plotBox;
    }


    public void harvestCrop(Plot plot, Label plotType, Label growStage, VBox boxOfLabels) {
        inventory.addToPane(plot.getCrop());
        inventory.addItem(plot.getCrop());
        boxOfLabels.setOnMouseClicked((e) -> {
            return;
        });
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
