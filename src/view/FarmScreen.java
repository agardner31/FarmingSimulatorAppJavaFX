package view;

import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

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
    private ScrollPane plotBox;
    private Button incrementTimeButton;
    private boolean inventoryVisible;
    private int targetPlantCrop;
    private Label targetCropLabel;
    private GridPane farmWorkerPane;

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
        farmWorkerPane = fillWorkerPane();
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

    private GridPane fillWorkerPane() {
        farmWorkerPane = new GridPane();
        Label workerLabel;
        FarmWorker[] farmWorkers = this.player.getFarmWorkers();
        for (int i = 0; i < farmWorkers.length; i++) {
            if (farmWorkers[i] != null) {
                workerLabel = new Label(farmWorkers[i].toString());
            } else {
                workerLabel = new Label("Farm Worker\nPosition Vacant");
            }
            workerLabel.getStyleClass().add("cropBox");
            farmWorkerPane.add(workerLabel, i, 0);

            int firedWorker = i;
            Label finalWorkerLabel = workerLabel;

            workerLabel.setOnMouseClicked((e) -> {
                farmWorkers[firedWorker] = null;
                finalWorkerLabel.setText("Farm Worker\nPosition Vacant");
                player.setFarmWorkers(farmWorkers);

            });
            workerLabel.setOnMouseEntered((f) -> {
                finalWorkerLabel.setBackground(new Background(
                        new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                                null)));
            });
            workerLabel.setOnMouseExited((f) -> {
                finalWorkerLabel.setBackground(null);
            });
        }
        farmWorkerPane.getStyleClass().add("inventoryPane");

        player.setFarmWorkers(farmWorkers);
        return farmWorkerPane;
    }

    public void payWorkers() {
        FarmWorker[] farmWorkers = this.player.getFarmWorkers();
        int wage;
        int money = this.player.getMoney();
        for (int i = 0; i < farmWorkers.length; i++) {
            if (farmWorkers[i] != null) {
                wage = farmWorkers[i].getWage();
                if (wage <= money) {
                    money -= wage;
                } else {
                    farmWorkers[i] = null;
                }
            }
        }
        this.player.setMoney(money);
        this.player.setFarmWorkers(farmWorkers);
    }

    private GridPane getInventoryPane() {
        inventoryPane = new GridPane();
        int j = -1;
        for (int i = 0; i < Inventory.getCapacity(); i++) {
            Item crop = null;
            Label cropLabel = new Label("");
            try {
                crop = inventory.getInventoryList().get(i);
                if (crop != null && crop instanceof Crop) {
                    if (((Crop) crop).getStage().equals(CropStage.MATURE)) {
                        cropLabel = new Label(((Crop) crop).toString("sell"));
                    } else {
                        cropLabel = new Label(((Crop) crop).toString("neither"));
                    }
                } else if (crop != null && (crop instanceof Fertilizer
                        || crop instanceof Pesticide)) {
                    cropLabel = new Label(crop.toString());
                }
            } catch (IndexOutOfBoundsException e) { }
            cropLabel.getStyleClass().add("cropBox");
            if (i % 10 == 0) {
                j++;
            }
            final int finalCropIndex = i;
            final Item finalCrop = crop;
            final Label finalCropLabel = cropLabel;
            cropLabel.setOnMouseClicked((e) -> {
                if (finalCrop instanceof Crop) {
                    if (finalCrop != null && ((Crop) finalCrop).getStage().equals(CropStage.SEED)) {
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
                } else if (finalCrop instanceof Fertilizer || finalCrop instanceof Pesticide) {
                    if (finalCrop != null) {
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
            int workerEfficiency = this.player.getFarmWorkerEfficiency();
            player.getFarm().recalculateRainOdds(player.getDifficulty(), player.getSeason());
            player.getFarm().recalculateDroughtOdds(player.getDifficulty(), player.getSeason());
            player.getFarm().recalculateLocustsOdds(player.getDifficulty(), player.getSeason());
            player.getFarm().recalculateRandomRainOrDrought();
            player.getFarm().randomLocustKills(-1);
            for (int i = 0; i < plots.length; i++) {
                if (plots[i].getCrop() != null) {
                    if (plots[i].getFertilizerLevel() > 0) {
                        CropStage tempStage = plots[i].getCrop().getStage();
                        if (tempStage.equals(CropStage.SEED)) {
                            plots[i].getCrop().setStage(CropStage.IMMATURE);
                        }
                    }
                    if (player.getFarm().getRain() && !player.getFarm().getDrought()) {
                        plots[i].water(player.getFarm().getRandomRainOrDrought());
                    } else if (player.getFarm().getDrought() && !player.getFarm().getRain()) {
                        plots[i].dry(player.getFarm().getRandomRainOrDrought());
                    }
                    if (player.getFarm().getLocusts()) {
                        if (player.getFarm().randomLocustKills(1) > 0) {
                            plots[i].getCrop().setStage(CropStage.DEAD);
                        }
                    }
                    plots[i].getCrop().grow();
                    if (plots[i].getCrop().getStage().equals(CropStage.MATURE)
                            && (workerEfficiency > 0)) {
                        this.player.addMoney(plots[i].getCrop().getSellPrice());
                        plots[i].setCrop(null);
                        workerEfficiency--;
                    }
                } else {
                    if (player.getFarm().getRain() && !player.getFarm().getDrought()) {
                        plots[i].water(player.getFarm().getRandomRainOrDrought());
                    } else if (player.getFarm().getDrought() && !player.getFarm().getRain()) {
                        plots[i].dry(player.getFarm().getRandomRainOrDrought());
                    }
                }
                if (!player.getFarm().getRain()) {
                    plots[i].dry(10);
                }
            }
            payWorkers();
            player.incrementDay();
            Controller.enterFarm(player, player.getDifficulty(), inventoryVisible);
        });


        Text randomEventText = new Text("");
        randomEventText.setFont(Font.font("Verdana", 28));
        randomEventText.setFill(Color.RED);
        if (player.getFarm().getRain() && !player.getFarm().getDrought()) {
            randomEventText.setText("It rained today! +"
                    + player.getFarm().getRandomRainOrDrought() + "% moisture");
        } else if (player.getFarm().getDrought() && !player.getFarm().getRain()) {
            randomEventText.setText("There was a drought! -"
                    + player.getFarm().getRandomRainOrDrought() + "% moisture");
        } else if (player.getFarm().getLocusts()) {
            randomEventText.setText("Locusts ate " + player.getFarm().getLocustKills()
                    + " of your crops");
        } else {
            randomEventText.setText("");
        }





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

        VBox vbox = new VBox(moneyLabel, displayDateLabel, plotBox,
                farmWorkerPane, inventoryWithLabel);

        inventoryButton.getStyleClass().add("inventoryButton");
        inventoryLabel.getStyleClass().add("inventoryLabel");
        moneyLabel.getStyleClass().add("moneyLabel");
        displayDateLabel.getStyleClass().add("displayDateLabel");
        vbox.getStyleClass().add("vBox");

        HBox buttonRow = new HBox(inventoryButton, marketButton,
                incrementTimeButton, randomEventText);
        buttonRow.setSpacing(10);
        VBox finalScene = new VBox(buttonRow, vbox);


        finalScene.setStyle("-fx-background-color: #658E6E; -fx-padding: 15");

        return new Scene(finalScene, width, height);
    }

    private ScrollPane fillPlotPane() {
        HBox plotBox = new HBox(20);
        for (int i = 0; i < plots.length; i++) {
            Plot temp = plots[i];
            Label plotNumber = new Label("Plot #" + (i + 1));
            Label plotType;
            Label growStage;
            Label waterLevel;
            Label fertilizerLevel;
            Button pesticideButton = new Button("Spray");
            Label hasPesticidesLabel = new Label("");
            if (temp.getCrop() == null) {
                plotType = new Label("Empty");
                growStage = new Label("Dirt");
                waterLevel = new Label("Moisture: " + temp.getWaterLevel() + "%");
                fertilizerLevel = new Label("Fertilizer: " + temp.getFertilizerLevel() + "%");
                hasPesticidesLabel = new Label("");
            } else {
                plotType = new Label(temp.getCrop().getType());
                growStage = new Label((temp.getCrop()).getStage().toString());
                waterLevel = new Label(String.valueOf("Moisture: "
                        + (temp.getWaterLevel())) + '%');
                fertilizerLevel = new Label("Fertilizer: " + temp.getFertilizerLevel() + "%");
                if (temp.getCrop().hasPesticides()) {
                    hasPesticidesLabel = new Label("Crop is sprayed");
                } else {
                    hasPesticidesLabel = new Label("Crop not sprayed");
                }
            }
            VBox boxOfLabels = new VBox(plotNumber, plotType, growStage, waterLevel,
                    fertilizerLevel, hasPesticidesLabel);
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
                pesticideButton.setVisible(true);
            } else {
                plantAndHarvestButton.setVisible(true);
                pesticideButton.setVisible(false);
            }


            plantAndHarvestButton.setOnAction((e) -> {
                if (temp.getCrop() != null) {
                    if (temp.getCrop().getStage().toString().equals("Mature")) {
                        if (!inventory.isFull()) {
                            harvestCrop(temp);
                            displayGrowth(temp, plantAndHarvestButton, growStage, img,
                                    waterLevel, fertilizerLevel, pesticideButton);
                        }
                    } else if (temp.getCrop().getStage().equals(CropStage.DEAD)) {
                        plotType.setText("Empty");
                        temp.setCrop(null);
                        displayGrowth(temp, plantAndHarvestButton, growStage, img,
                                waterLevel, fertilizerLevel, pesticideButton);
                    }
                } else {
                    Item fertString = new Fertilizer("Apprentice");
                    if (targetPlantCrop != -1
                            && !targetCropLabel.getText().equals(fertString.toString())) {
                        plant(temp);
                        displayGrowth(temp, plantAndHarvestButton, growStage, img,
                                waterLevel, fertilizerLevel, pesticideButton);
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
                    temp.water(20);
                    displayGrowth(temp, plantAndHarvestButton, growStage, img,
                            waterLevel, fertilizerLevel, pesticideButton);
                }
            );
            HBox plantAndHarvestPlusWaterButtons = new HBox(plantAndHarvestButton,
                    waterButton);
            plantAndHarvestPlusWaterButtons.setSpacing(10);
            plantAndHarvestPlusWaterButtons.setAlignment(Pos.CENTER);

            Button fertilizeButton = new Button("Fertilize");
            fertilizeButton.setOnAction((e) -> {
                Item fertString = new Fertilizer("Apprentice");
                if (targetPlantCrop != -1
                        && targetCropLabel.getText().equals(fertString.toString())) {
                    temp.setFertilizerLevel(100);
                    player.getInventory().removeItem(targetPlantCrop);
                    displayGrowth(temp, plantAndHarvestButton, growStage, img,
                            waterLevel, fertilizerLevel, pesticideButton);
                    targetCropLabel.setScaleX(1);
                    targetCropLabel.setScaleY(1);
                    targetPlantCrop = -1;
                    Controller.enterFarm(player, player.getDifficulty(), true);
                }
            });


            pesticideButton.setOnAction((e) -> {
                Item pesticideString = new Pesticide("Apprentice");
                if (targetPlantCrop != -1
                        && targetCropLabel.getText().equals(pesticideString.toString())) {
                    if (temp.getCrop() != null) {
                        temp.getCrop().spray();
                    }
                    player.getInventory().removeItem(targetPlantCrop);
                    displayGrowth(temp, plantAndHarvestButton, growStage, img,
                            waterLevel, fertilizerLevel, pesticideButton);
                    targetCropLabel.setScaleX(1);
                    targetCropLabel.setScaleY(1);
                    targetPlantCrop = -1;
                    Controller.enterFarm(player, player.getDifficulty(), true);
                }
            });
            HBox fertilizerPesticideBox = new HBox(fertilizeButton, pesticideButton);
            fertilizerPesticideBox.setSpacing(10);
            fertilizerPesticideBox.setAlignment(Pos.CENTER);

            VBox onePlot = new VBox(boxOfLabels, img, plantAndHarvestPlusWaterButtons,
                    fertilizerPesticideBox);
            plotBox.getChildren().add(onePlot);
        }

        plotBox.getStyleClass().add("plotBox");
        ScrollPane plotScrollPane = new ScrollPane();
        plotScrollPane.setContent(plotBox);
        plotScrollPane.getStyleClass().add("plotScrollPane");
        plotScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return plotScrollPane;
    }


    private void displayGrowth(Plot temp, Button plantAndHarvestButton, Label growStage,
                               ImageView img, Label waterLevel, Label fertilizerLabel,
                               Button pesticideButton) {
        plantAndHarvestButton.setText(getPlantAndHarvestButtonString(temp));
        if (getPlantAndHarvestButtonString(temp).equals("Wait")) {
            plantAndHarvestButton.setVisible(false);
            pesticideButton.setVisible(true);
        } else {
            plantAndHarvestButton.setVisible(true);
            pesticideButton.setVisible(false);
        }
        img.setImage(temp.getImg());
        if (temp.getCrop() == null) {
            growStage.setText("Dirt");
        } else {
            growStage.setText(temp.getCrop().getStage().toString());
        }
        waterLevel.setText("Moisture: " + temp.getWaterLevel() + "%");
        fertilizerLabel.setText("Fertilizer: " + temp.getFertilizerLevel() + "%");
    }

    private void plant(Plot temp) {
        temp.setCrop((Crop) player.getInventory().getInventoryList().get(targetPlantCrop));
        player.getInventory().removeItem(targetPlantCrop);
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
        if (plot.getFertilizerLevel() > 0) {
            Random rand = new Random();
            int randNum = rand.nextInt(3); //33% chance to get double yield
            if (randNum == 1) {
                inventory.addToPane(plot.getCrop());
                inventory.addItem(plot.getCrop());
            }
        }
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
