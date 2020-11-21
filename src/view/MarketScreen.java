package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.text.Text;
import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MarketScreen implements IScreen {
    private int width;
    private int height;
    private Player player;
    private Label displayMarketLabel;
    private Label moneyLabel;
    private Plot[] plots;
    private GridPane playerInventoryPane;
    private GridPane forHirePane;
    private GridPane machinePane;
    private Button inventoryButton;
    private Button farmButton;
    private HBox plotBox;
    private GridPane marketPane;
    private Market market;
    private Inventory playerInventory;
    private Inventory marketInventory;
    private MediaPlayer buyOrSellNoise;

    public MarketScreen(int width, int height, String difficulty, Player player) {
        this.width = width;
        this.height = height;
        this.player = player;
        displayMarketLabel = new Label("Market");
        moneyLabel = new Label("Money: $" + player.getMoney() + ".00");
        market = new Market(player, difficulty);
        marketInventory = market.getMarketInventory();
        playerInventory = market.getPlayerInventory();
        inventoryButton = new Button("Inventory");
        playerInventoryPane = getInventoryPane();
        marketPane = getMarketPane();
        forHirePane = getForHirePane();
        machinePane = getMachinePane();
        Media chaching = new Media(new File("audio/chaching.mp3").toURI().toString());
        buyOrSellNoise = new MediaPlayer(chaching);
    }

    private GridPane getInventoryPane() {
        GridPane inventoryPane = new GridPane();
        int j = -1;
        for (int i = 0; i < Inventory.getCapacity(); i++) {
            Item crop = null;
            Label cropLabel = new Label("");
            try {
                crop = playerInventory.getInventoryList().get(i);
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
            final int targetCrop = i;
            final Item finalCrop = crop;
            cropLabel.setOnMouseClicked((e) -> {
                if (finalCrop instanceof Crop
                        && ((Crop) finalCrop).getStage().equals(CropStage.MATURE)) {
                    if (playerInventory.removeItem(targetCrop)) {
                        //removeFromPane(targetCrop, playerInventoryPane);
                        buyOrSellNoise.stop();
                        buyOrSellNoise.play();
                        market.sell(finalCrop);
                        Controller.enterMarket(player, player.getDifficulty());
                    }
                }
            });
            Label finalCropLabel = cropLabel;
            cropLabel.setOnMouseEntered(e -> {
                finalCropLabel.setScaleX(1.5);
                finalCropLabel.setScaleY(1.5);
                finalCropLabel.setBackground(new Background(
                        new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                                null)));
            });
            cropLabel.setOnMouseExited(e -> {
                finalCropLabel.setScaleX(1);
                finalCropLabel.setScaleY(1);
                finalCropLabel.setBackground(null);
            });

            inventoryPane.add(cropLabel, i % 10, j);
        }
        //playerInventory.setInventoryPane(inventoryPane);
        //setPlayerInventoryPane(inventoryPane);
        inventoryPane.getStyleClass().add("inventoryPane");
        return inventoryPane;
    }


    private GridPane getForHirePane() {
        forHirePane = new GridPane();
        int[] skillLevels = {1, 2, 3};
        Label workerLabel = null;
        FarmWorker helper = null;
        for (int i = 0; i < 3; i++) {
            helper = new FarmWorker(i + 1);
            workerLabel = new Label(helper.toString());
            forHirePane.add(workerLabel, i, 0);
            FarmWorker finalHelper = helper;
            workerLabel.setOnMouseClicked((e) -> {
                if (this.player.hireWorker(finalHelper)) {
                    buyOrSellNoise.stop();
                    buyOrSellNoise.play();
                    Controller.enterMarket(player, player.getDifficulty());
                }
            });
            Label finalWorkerLabel = workerLabel;
            workerLabel.setOnMouseEntered(e -> {
                finalWorkerLabel.setBackground(new Background(
                        new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                                null)));
            });
            workerLabel.setOnMouseExited(e -> {
                finalWorkerLabel.setBackground(null);
            });
            workerLabel.getStyleClass().add("cropBox");
        }
        forHirePane.getStyleClass().add("inventoryPane");
        return forHirePane;
    }

    private GridPane getMachinePane() {
        machinePane = new GridPane();

        FarmMachine irrigation = new Irrigation(player.getDifficulty());
        FarmMachine tractor = new Tractor(player.getDifficulty());
        Label irrigationLabel = new Label(irrigation.toString());
        Label tractorLabel = new Label(tractor.toString());
        Label plotLabel = new Label(player.getFarm().plotPurchaseString());
        plotLabel.setOnMouseEntered(e -> {
            plotLabel.setBackground(new Background(
                    new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                            null)));
        });

        plotLabel.setOnMouseExited(e -> {
            plotLabel.setBackground(null);
        });
        plotLabel.getStyleClass().add("cropBox");
        if (player.getFarm().hasIrrigation()) {
            irrigationLabel.setBackground(new Background(
                    new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                            null)));
        } else {
            irrigationLabel.setOnMouseEntered(e -> {
                irrigationLabel.setBackground(new Background(
                        new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                                null)));
            });

            irrigationLabel.setOnMouseExited(e -> {
                irrigationLabel.setBackground(null);
            });
        }
        if (player.getFarm().hasTractor()) {
            tractorLabel.setBackground(new Background(
                    new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                            null)));
        } else {
            tractorLabel.setOnMouseEntered(e -> {
                tractorLabel.setBackground(new Background(
                        new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                                null)));
            });

            tractorLabel.setOnMouseExited(e -> {
                tractorLabel.setBackground(null);
            });
        }
        machinePane.add(irrigationLabel, 0, 0);
        machinePane.add(tractorLabel, 1, 0);
        machinePane.add(plotLabel, 2, 0);
        irrigationLabel.setOnMouseClicked((e) -> {
            if (player.buyMachine(irrigation)) {
                buyOrSellNoise.stop();
                buyOrSellNoise.play();
                Controller.enterMarket(player, player.getDifficulty());
            }
        });
        irrigationLabel.getStyleClass().add("cropBox");

        tractorLabel.setOnMouseClicked((e) -> {
            if (player.buyMachine(tractor)) {
                buyOrSellNoise.stop();
                buyOrSellNoise.play();
                Controller.enterMarket(player, player.getDifficulty());
            }
        });
        tractorLabel.getStyleClass().add("cropBox");

        plotLabel.setOnMouseClicked((e) -> {
            if (player.buyPlot()) {
                buyOrSellNoise.stop();
                buyOrSellNoise.play();
                Controller.enterMarket(player, player.getDifficulty());
            }
        });

        machinePane.getStyleClass().add("inventoryPane");
        return machinePane;
    }


    private GridPane getMarketPane() {
        marketPane = new GridPane();
        int j = -1;
        for (int i = 0; i < Inventory.getCapacity(); i++) {
            Item crop = null;
            Label cropLabel = new Label("");
            try {
                crop = marketInventory.getInventoryList().get(i);
                if (crop != null && crop instanceof Crop) {
                    cropLabel = new Label(((Crop) crop).toString("buy"));
                } else if (crop != null && (crop instanceof Fertilizer
                        || crop instanceof Pesticide)) {
                    cropLabel = new Label(((Item) crop).toString("buy"));
                }
            } catch (IndexOutOfBoundsException e) { }
            cropLabel.getStyleClass().add("cropBox");
            if (i % 10 == 0) {
                j++;
            }
            final Item finalCrop = crop;
            cropLabel.setOnMouseClicked((e) -> {
                if (finalCrop != null) {
                    int price = finalCrop.getBuyPrice();
                    if (player.getMoney() >= price) {
                        if (playerInventory.addItem(finalCrop)) {
                            buyOrSellNoise.stop();
                            buyOrSellNoise.play();
                            addToPane(finalCrop, marketPane);
                            market.buy(finalCrop, price);
                            //moneyLabel.setText("Money: $" + player.getMoney() + ".00");
                            Controller.enterMarket(player, player.getDifficulty());
                        }
                    }
                }
            });
            Label finalCropLabel = cropLabel;
            cropLabel.setOnMouseEntered(e -> {
                finalCropLabel.setScaleX(1.5);
                finalCropLabel.setScaleY(1.5);
                finalCropLabel.setBackground(new Background(
                        new BackgroundFill(Color.valueOf("#B9E1C1"), null,
                                null)));
            });
            cropLabel.setOnMouseExited(e -> {
                finalCropLabel.setScaleX(1);
                finalCropLabel.setScaleY(1);
                finalCropLabel.setBackground(null);
            });

            marketPane.add(cropLabel, i % 10, j);
        }



        //setPlayerInventoryPane(marketPane);
        marketPane.getStyleClass().add("inventoryPane");
        return marketPane;
    }

    private void remove(int targetCrop) {
        ((Label) marketPane.getChildren().get(targetCrop)).setText("");
        marketPane.getChildren().get(targetCrop).setOnMouseClicked((e) -> {

        });
    }



    @Override
    public Scene getScene() {
        // farm scene

        Label inventoryLabel = new Label("Items");
        Label forHireLabel = new Label("Farm Workers for Hire");
        Label machineLabel = new Label("Farm Machinery and Plots for Sale");

        VBox inventoryWithLabel = new VBox(inventoryLabel, playerInventoryPane);

        //moves to market scene
        farmButton = new Button();
        ImageView farmIcon = null;
        try {
            farmIcon = new ImageView(new Image(new FileInputStream("images/FarmIcon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        farmIcon.setPreserveRatio(true);
        farmIcon.setFitHeight(50);
        farmButton.setGraphic(farmIcon);
        farmButton.setVisible(true);
        farmButton.setOnAction((e) -> {
            Controller.enterFarm(player, player.getDifficulty(), true);
        });

        Region spacer1 = new Region();
        Region spacer2 = new Region();
        Region spacer3 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        VBox forHireSide = new VBox(forHireLabel, forHirePane);
        VBox machineSide = new VBox(machineLabel, machinePane);
        HBox toolsPane = new HBox(spacer1, forHireSide, spacer2, machineSide, spacer3);

        Text buySell = new Text("Click on item in your inventory to sell it or"
                + " click on item in the market inventory to buy");
        VBox vbox = new VBox(moneyLabel, displayMarketLabel, marketPane,
                toolsPane, inventoryWithLabel, buySell);
        buySell.getStyleClass().add("moneyLabel");
        inventoryLabel.getStyleClass().add("inventoryLabel");
        forHireLabel.getStyleClass().add("inventoryLabel");
        machineLabel.getStyleClass().add("inventoryLabel");
        moneyLabel.getStyleClass().add("moneyLabel");
        displayMarketLabel.getStyleClass().add("displayDateLabel");
        marketPane.getStyleClass().add("plotBox"); //change to marketPane css later
        //vbox.getStyleClass().add("vBox");
        vbox.setSpacing(20);

        HBox buttonRow = new HBox(farmButton);
        buttonRow.setSpacing(10);
        VBox finalScene = new VBox(buttonRow, vbox);
        finalScene.setSpacing(10);
        finalScene.setStyle("-fx-background-color: #658E6E; -fx-padding: 15");

        return new Scene(finalScene, width, height);
    }

    public void addToPane(Item item, GridPane inventoryPane) {
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

    private void removeFromPane(int targetCrop, GridPane inventoryPane) {
        Label temp = (Label) inventoryPane.getChildren().get(targetCrop);
        temp.setText("");
    }

    public void setPlayerInventoryPane(GridPane inventoryPane) {
        this.playerInventoryPane = inventoryPane;
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
