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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.*;

import java.io.*;
import java.util.List;
import java.util.Random;

public class FarmScreen implements IScreen {
    private int width;
    private int height;
    private Player player;
    private Label displayDateLabel;
    private Label moneyLabel;
    private List<Plot> plots;
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
    private GridPane machinePane;
    private Label machineErrorMessage;
    private MediaPlayer sprayNoise;
    private MediaPlayer waterNoise;
    private MediaPlayer plantNoise;
    private MediaPlayer harvestNoise;
    private MediaPlayer nextDayNoise;
    private MediaPlayer fertilizeNoise;
    private Button saveGameButton;


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
        machinePane = getMachinePane();
        saveGameButton = new Button("Save Game");
        ImageView inventoryIcon = null;
        setUpAudio();
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
        machineErrorMessage = new Label("");
    }

    private void setUpAudio() {
        Media spray = new Media(new File("audio/spray.mp3").toURI().toString());
        sprayNoise = new MediaPlayer(spray);
        Media droplet = new Media(new File("audio/droplet.mp3").toURI().toString());
        waterNoise = new MediaPlayer(droplet);
        Media blop = new Media(new File("audio/blop.mp3").toURI().toString());
        plantNoise = new MediaPlayer(blop);
        Media woosh = new Media(new File("audio/woosh.mp3").toURI().toString());
        harvestNoise = new MediaPlayer(woosh);
        Media rooster = new Media(new File("audio/rooster.mp3").toURI().toString());
        nextDayNoise = new MediaPlayer(rooster);
        Media tick = new Media(new File("audio/tick.mp3").toURI().toString());
        fertilizeNoise = new MediaPlayer(tick);

    }

    private GridPane getMachinePane() {
        machinePane = new GridPane();

        Label irrigationLabel = new Label();
        if (player.getFarm().hasIrrigation()) {
            irrigationLabel.setText("Farm is\nIrrigated");
        } else {
            irrigationLabel.setText("No\nIrrigation");
        }
        machinePane.add(irrigationLabel, 0, 0);
        irrigationLabel.getStyleClass().add("cropBox");

        Label tractorLabel = new Label();
        if (player.getFarm().hasTractor()) {
            tractorLabel.setText("Have\nTractor");
        } else {
            tractorLabel.setText("No\nTractor");
        }
        machinePane.add(tractorLabel, 1, 0);
        tractorLabel.getStyleClass().add("cropBox");

        machinePane.getStyleClass().add("inventoryPane");
        return machinePane;
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
        setInventoryPane(inventoryPane);
        inventoryPane.getStyleClass().add("inventoryPane");
        return inventoryPane;
    }

    public Scene getScene() {
        // farm scene

        Label inventoryLabel = new Label("Items");

        VBox inventoryWithLabel = new VBox(inventoryLabel, inventoryPane);
        inventoryWithLabel.setVisible(inventoryVisible);



        incrementTimeButton.setOnAction((e) -> {
            nextDayNoise.stop();
            nextDayNoise.play();
            int workerEfficiency = this.player.getFarmWorkerEfficiency();
            player.getFarm().recalculateRainOdds(player.getDifficulty(), player.getSeason());
            player.getFarm().recalculateDroughtOdds(player.getDifficulty(), player.getSeason());
            player.getFarm().recalculateLocustsOdds(player.getDifficulty(), player.getSeason());
            player.getFarm().recalculateRandomRainOrDrought();
            player.getFarm().randomLocustKills(-1);
            for (int i = 0; i < player.getFarm().getNumPlots(); i++) {
                if (plots.get(i).getCrop() != null) {
                    if (plots.get(i).getFertilizerLevel() > 0) {
                        CropStage tempStage = plots.get(i).getCrop().getStage();
                        if (tempStage.equals(CropStage.SEED)) {
                            plots.get(i).getCrop().setStage(CropStage.IMMATURE);
                        }
                    }
                    if (player.getFarm().getRain() && !player.getFarm().getDrought()) {
                        plots.get(i).water(player.getFarm().getRandomRainOrDrought());
                    } else if (player.getFarm().getDrought() && !player.getFarm().getRain()) {
                        plots.get(i).dry(player.getFarm().getRandomRainOrDrought());
                    }
                    if (player.getFarm().getLocusts()) {
                        if (!plots.get(i).getCrop().hasPesticides()
                                && player.getFarm().randomLocustKills(1) > 0) {
                            plots.get(i).getCrop().setStage(CropStage.DEAD);
                        }
                    }
                    plots.get(i).getCrop().grow();
                    if (plots.get(i).getCrop().getStage().equals(CropStage.MATURE)
                            && (workerEfficiency > 0)) {
                        this.player.addMoney(plots.get(i).getCrop().getSellPrice());
                        plots.get(i).setCrop(null);
                        workerEfficiency--;
                    }
                } else {
                    if (player.getFarm().getRain() && !player.getFarm().getDrought()) {
                        plots.get(i).water(player.getFarm().getRandomRainOrDrought());
                    } else if (player.getFarm().getDrought() && !player.getFarm().getRain()) {
                        plots.get(i).dry(player.getFarm().getRandomRainOrDrought());
                    }
                }
                if (!player.getFarm().getRain()) {
                    plots.get(i).dry(10);
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
            randomEventText.setText("Locusts ate " + (player.getFarm().getLocustKills() - 1)
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

        machineErrorMessage.getStyleClass().add("errorMessage");

        Region spacer1 = new Region();
        Region spacer2 = new Region();
        Region spacer3 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        HBox toolsPane = new HBox(spacer1, farmWorkerPane, spacer2, machinePane, spacer3);
        toolsPane.getStyleClass().add("inventoryPane");

        VBox vbox = new VBox(moneyLabel, displayDateLabel, plotBox, machineErrorMessage,
                toolsPane, inventoryWithLabel);

        inventoryButton.getStyleClass().add("inventoryButton");
        inventoryLabel.getStyleClass().add("inventoryLabel");
        moneyLabel.getStyleClass().add("moneyLabel");
        displayDateLabel.getStyleClass().add("displayDateLabel");
        vbox.getStyleClass().add("vBox");

        saveGameButton.setOnAction(e -> {
            try {
                FileOutputStream f = new FileOutputStream(new File("previous game/previousGame.txt"));
                ObjectOutputStream o = new ObjectOutputStream(f);

                // Write player to previousGame.txt
                o.writeObject(player);


                o.close();
                f.close();
            } catch (IOException ie) {
                System.out.println("Error initializing stream");
                ie.printStackTrace();
            }
        });

        HBox buttonRow = new HBox(inventoryButton, marketButton,
                incrementTimeButton, randomEventText, saveGameButton);
        buttonRow.setSpacing(10);
        VBox finalScene = new VBox(buttonRow, vbox);


        finalScene.setStyle("-fx-background-color: #658E6E; -fx-padding: 15");

        return new Scene(finalScene, width, height);
    }

    private ScrollPane fillPlotPane() {
        HBox plotBox = new HBox(20);
        for (int i = 0; i < player.getFarm().getNumPlots(); i++) {
            Plot temp = plots.get(i);
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
                if (temp.getCrop() != null && temp.getCrop().hasPesticides()) {
                    pesticideButton.setVisible(false);
                } else {
                    pesticideButton.setVisible(true);
                }
            } else {
                plantAndHarvestButton.setVisible(true);
                pesticideButton.setVisible(false);
            }
            plantAndHarvestButton.setOnAction((e) -> {
                if (temp.getCrop() != null) {
                    if (temp.getCrop().getStage().toString().equals("Mature")) {
                        if (!inventory.isFull()) {
                            if (player.getFarm().harvestCountCheck()) {
                                player.getFarm().incrementDailyHarvestCount();
                                harvestNoise.stop();
                                harvestNoise.play();
                                harvestCrop(temp);
                                displayGrowth(temp, plantAndHarvestButton, growStage, img,
                                        waterLevel, fertilizerLevel, pesticideButton);
                            } else {
                                updateLimitMessage();
                            }
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
                if (player.getFarm().waterCountCheck()) {
                    player.getFarm().incrementDailyWaterCount();
                    temp.water(20);
                    waterNoise.stop();
                    waterNoise.play();
                    displayGrowth(temp, plantAndHarvestButton, growStage, img,
                            waterLevel, fertilizerLevel, pesticideButton);
                } else {
                    updateLimitMessage();
                }
            });
            HBox plantAndHarvestPlusWaterButtons = new HBox(plantAndHarvestButton,
                    waterButton);
            plantAndHarvestPlusWaterButtons.setSpacing(10);
            plantAndHarvestPlusWaterButtons.setAlignment(Pos.CENTER);

            Button fertilizeButton = new Button("Fertilize");
            fertilizeButton.setOnAction((e) -> {
                Item fertString = new Fertilizer("Apprentice");
                if (targetPlantCrop != -1
                        && targetCropLabel.getText().equals(fertString.toString())) {
                    fertilizeNoise.stop();
                    fertilizeNoise.play();
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
                        waterNoise.stop();
                        sprayNoise.play();
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

            Button climbButton = new Button("Climb");
            climbButton.setVisible(false);
            if (temp.getCrop() != null && temp.getCrop().getType().equals("Magic Beans")
                    && ((temp.getCrop().getStage().equals(CropStage.IMMATURE))
                    || temp.getCrop().getStage().equals(CropStage.MATURE))) {
                climbButton.setVisible(true);
            }
            climbButton.setOnAction((e) -> {
                Controller.win(player);
            });

            HBox fertilizerPesticideBox = new HBox(fertilizeButton, pesticideButton);
            fertilizerPesticideBox.setSpacing(10);
            fertilizerPesticideBox.setAlignment(Pos.CENTER);

            VBox onePlot = new VBox(boxOfLabels, img, plantAndHarvestPlusWaterButtons,
                    fertilizerPesticideBox, climbButton);
            plotBox.getChildren().add(onePlot);
        }

        plotBox.getStyleClass().add("plotBox");
        ScrollPane plotScrollPane = new ScrollPane();
        plotScrollPane.setContent(plotBox);
        plotScrollPane.getStyleClass().add("plotScrollPane");
        plotScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return plotScrollPane;
    }

    private void updateLimitMessage() {
        if (player.getFarm().bothCheck()) {
            machineErrorMessage.setText("Water and harvesting limits have been reached for today");
        } else if (!player.getFarm().waterCountCheck()) {
            machineErrorMessage.setText("Water limit has been reached for today");
        } else if (!player.getFarm().harvestCountCheck()) {
            machineErrorMessage.setText("Harvest limit has been reached for today");
        }
    }


    private void displayGrowth(Plot temp, Button plantAndHarvestButton, Label growStage,
                               ImageView img, Label waterLevel, Label fertilizerLabel,
                               Button pesticideButton) {
        plantAndHarvestButton.setText(getPlantAndHarvestButtonString(temp));
        if (getPlantAndHarvestButtonString(temp).equals("Wait")) {
            plantAndHarvestButton.setVisible(false);
            if (temp.getCrop() != null && temp.getCrop().hasPesticides()) {
                pesticideButton.setVisible(false);
            } else {
                pesticideButton.setVisible(true);
            }
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
        plantNoise.stop();
        plantNoise.play();
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
        harvestNoise.stop();
        harvestNoise.play();
        if (plot.getFertilizerLevel() > 0) {
            Random rand = new Random();
            int randNum = rand.nextInt(3); //33% chance to get double yield
            if (randNum == 1) {
                addToPane(plot.getCrop());
                inventory.addItem(plot.getCrop());
            }
        }
        addToPane(plot.getCrop());
        inventory.addItem(plot.getCrop());
        player.incrementNumCropsHarvested();

        // win the game
        /*if (player.getNumCropsHarvested() >= 15) {
            Controller.win();
        }*/
        plot.setCrop(null);
    }

    public void setInventoryPane(GridPane inventoryPane) {
        this.inventoryPane = inventoryPane;
    }

    public void addToPane(Item item) {
        for (int i = 0; i < Inventory.getCapacity(); i++) {
            if (i >= inventoryPane.getChildren().size()) {
                Label label = new Label(item.toString());
                inventoryPane.getChildren().add(label);
                return;
            }
            Label temp = (Label) inventoryPane.getChildren().get(i);
            if (temp.getText().equals("")
                    || inventoryPane.getChildren().get(i) == null) {
                temp.setText(item.toString());
                return;
            }
        }
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

    public List<Plot> getPlots() {
        return plots;
    }
}
