package view;

import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Crop;
import model.CropStage;
import model.Inventory;
import model.Player;
import model.Plot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Button incrementTimeButton;
    private boolean inventoryVisible;
    private int targetPlantCrop;
    private Label targetCropLabel;

    public FarmScreen(int width, int height, Player player, boolean inventoryVisible) {
        this.inventoryVisible = inventoryVisible;
        this.width = width;
        this.height = height;
        this.player = player;
        displayDateLabel = new Label("Day " + player.getDay());
        moneyLabel = new Label("Money: $" + player.getMoney() + ".00");
        plots = player.getFarm().getPlots();
        inventory = player.getInventory();
        inventoryPane = getInventoryPane();
        inventoryButton = new Button();
        ImageView inventoryIcon = null;
        try {
            inventoryIcon = new ImageView(new Image(
                    new FileInputStream("images/InventoryIcon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inventoryIcon.setPreserveRatio(true);
        inventoryIcon.setFitHeight(50);
        inventoryButton.setGraphic(inventoryIcon);


        incrementTimeButton = new Button();
        ImageView nextDayIcon = null;
        try {
            nextDayIcon = new ImageView(new Image(new FileInputStream("images/NextDayIcon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nextDayIcon.setPreserveRatio(true);
        nextDayIcon.setFitHeight(50);
        incrementTimeButton.setGraphic(nextDayIcon);
        plotBox = fillPlotPane();
        targetPlantCrop = -1;
    }

    private GridPane getInventoryPane() {
        inventoryPane = new GridPane();
        int j = -1;
        for (int i = 0; i < Inventory.getCapacity(); i++) {
            Crop crop = null;
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
            final int finalCropIndex = i;
            final Crop finalCrop = crop;
            final Label finalCropLabel = cropLabel;
            cropLabel.setOnMouseClicked((e) -> {
                if (finalCrop != null && finalCrop.getStage().equals(CropStage.SEED)) {
                    if (targetPlantCrop == -1) {
                        targetCropLabel = finalCropLabel;
                        targetCropLabel.setScaleX(1.5);
                        targetCropLabel.setScaleY(1.5);
                        targetPlantCrop = finalCropIndex;
                    } else {
                        targetCropLabel = finalCropLabel;
                        targetCropLabel.setScaleX(1);
                        targetCropLabel.setScaleY(1);
                        targetPlantCrop = -1;
                    }
                }
            });

            cropLabel.setOnMouseEntered((f) -> {
                finalCropLabel.setBackground(new Background(
                        new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                                null)));
            });

            cropLabel.setOnMouseExited((f) -> {
                finalCropLabel.setBackground(null);
            });

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
        inventoryWithLabel.setVisible(inventoryVisible);

        incrementTimeButton.setOnAction((e) -> {
            for (int i = 0; i < plots.length; i++) {
                if (plots[i].getCrop() != null) {
                    plots[i].getCrop().grow();
                }
                plots[i].dry();
            }
            player.incrementDay();
            Controller.enterFarm(player, player.getDifficulty(), inventoryVisible);
        });

        inventoryButton.setOnAction((e) -> {
            if (!inventoryWithLabel.isVisible()) {
                inventoryWithLabel.setVisible(true);
                inventoryVisible = true;
            } else {
                inventoryWithLabel.setVisible(false);
                inventoryVisible = false;
            }
        });

        //moves to market scene
        ImageView marketIcon = null;
        try {
            marketIcon = new ImageView(new Image(new FileInputStream("images/MarketIcon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        marketIcon.setPreserveRatio(true);
        marketIcon.setFitHeight(50);
        marketButton = new Button();
        marketButton.setVisible(true);
        marketButton.setGraphic(marketIcon);
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

        HBox buttonRow = new HBox(inventoryButton, marketButton, incrementTimeButton);
        buttonRow.setSpacing(10);
        VBox finalScene = new VBox(buttonRow, vbox);
        finalScene.setStyle("-fx-background-color: #658E6E; -fx-padding: 15");

        return new Scene(finalScene, width, height);
    }

    private HBox fillPlotPane() {
        HBox plotBox = new HBox(20);
        for (int i = 0; i < plots.length; i++) {
            Plot temp = plots[i];
            Label plotNumber = new Label("Plot #" + (i + 1));
            Label plotType;
            Label growStage;
            Label waterLevel;
            if (temp.getCrop() == null) {
                plotType = new Label("Empty");
                growStage = new Label("Dirt");
                waterLevel = new Label("Moisture: " + temp.getWaterLevel() + "%");
            } else {
                plotType = new Label(temp.getCrop().getType());
                growStage = new Label((temp.getCrop()).getStage().toString());
                waterLevel = new Label(String.valueOf("Moisture: "
                        + (temp.getWaterLevel())) + '%');
            }
            VBox boxOfLabels = new VBox(plotNumber, plotType, growStage, waterLevel);
            boxOfLabels.setOnMouseEntered((e) -> {
                boxOfLabels.setBackground(new Background(
                        new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                                null)));
            });
            boxOfLabels.setOnMouseExited((e) -> {
                boxOfLabels.setBackground(null);
            });

            boxOfLabels.getStyleClass().add("plotLabel");
            ImageView img = new ImageView(temp.getImg());
            img.setPreserveRatio(true);
            img.setFitHeight(120);
            Button plantAndHarvestButton = new Button(getPlantAndHarvestButtonString(temp));
            if (getPlantAndHarvestButtonString(temp).equals("Wait")) {
                plantAndHarvestButton.setVisible(false);
            } else {
                plantAndHarvestButton.setVisible(true);
            }
            plantAndHarvestButton.setOnAction((e) -> {
                if (temp.getCrop() != null) {
                    if (temp.getCrop().getStage().toString().equals("Mature")) {
                        if (!inventory.isFull()) {
                            harvestCrop(temp);
                            displayGrowth(temp, plantAndHarvestButton, growStage, img, waterLevel);
                        }
                    } else if (temp.getCrop().getStage().equals(CropStage.DEAD)) {
                        plotType.setText("Empty");
                        temp.setCrop(null);
                        displayGrowth(temp, plantAndHarvestButton, growStage, img, waterLevel);
                    }
                } else {
                    if (targetPlantCrop != -1) {
                        String waterLevelText = "";
                        Pattern p = Pattern.compile("\\d+");
                        Matcher m = p.matcher(waterLevel.getText());
                        while (m.find()) {
                            waterLevelText = m.group();
                        }
                        int waterLevelInt = (int) Integer.parseInt(waterLevelText);
                        plant(temp, waterLevelInt);
                        displayGrowth(temp, plantAndHarvestButton, growStage, img, waterLevel);
                        plotType.setText(temp.getCrop().getType());
                        targetCropLabel.setScaleX(1);
                        targetCropLabel.setScaleY(1);
                        targetPlantCrop = -1;
                        Controller.enterFarm(player, player.getDifficulty(), true);
                    }
                }
            });

            Button waterButton = new Button("Water");
            waterButton.setOnAction((e) -> {
                    if (temp.getCrop() != null) {
                        temp.getCrop().water();
                        temp.setWaterLevel((int) temp.getCrop().getWaterLevel());
                    } else {
                        temp.setWaterLevel(temp.getWaterLevel() + 20);
                        if (temp.getWaterLevel() > 100) {
                            temp.setWaterLevel(100);
                        }
                    }
                    displayGrowth(temp, plantAndHarvestButton, growStage, img, waterLevel);
                }
            );
            HBox plantAndHarvestPlusWaterButtons = new HBox(plantAndHarvestButton, waterButton);
            plantAndHarvestPlusWaterButtons.setSpacing(10);
            plantAndHarvestPlusWaterButtons.setAlignment(Pos.CENTER);
            VBox onePlot = new VBox(boxOfLabels, img, plantAndHarvestPlusWaterButtons);
            plotBox.getChildren().add(onePlot);
        }
        return plotBox;
    }

    private void displayGrowth(Plot temp, Button plantAndHarvestButton, Label growStage,
                               ImageView img, Label waterLevel) {
        plantAndHarvestButton.setText(getPlantAndHarvestButtonString(temp));
        if (getPlantAndHarvestButtonString(temp).equals("Wait")) {
            plantAndHarvestButton.setVisible(false);
        } else {
            plantAndHarvestButton.setVisible(true);
        }
        img.setImage(temp.getImg());
        if (temp.getCrop() == null) {
            growStage.setText("Dirt");
        } else {
            growStage.setText(temp.getCrop().getStage().toString());
        }
        waterLevel.setText("Moisture: " + temp.getWaterLevel() + "%");
    }

    private void plant(Plot temp, int waterLevel) {
        temp.setCrop(player.getInventory().getInventoryList().get(targetPlantCrop));
        player.getInventory().removeItem(targetPlantCrop);
        temp.getCrop().setWaterLevel(waterLevel);
    }

    private String getPlantAndHarvestButtonString(Plot plot) {
        String string;
        if (plot.getCrop() == null || plot.getCrop().getStage().equals(CropStage.DIRT)) {
            string = "Plant";
        } else if (plot.getCrop().getStage().equals(CropStage.SEED)
                || plot.getCrop().getStage().equals(CropStage.IMMATURE)) {
            string  = "Wait";
        } else if (plot.getCrop().getStage().equals(CropStage.MATURE)) {
            string = "Harvest";
        } else if (plot.getCrop().getStage().equals(CropStage.DEAD)) {
            string = "Compost";
        } else {
            throw new IllegalArgumentException("Illegal crop stage");
        }
        return string;
    }



    public void harvestCrop(Plot plot) {
        inventory.addToPane(plot.getCrop());
        inventory.addItem(plot.getCrop());
        plot.setCrop(null);
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
