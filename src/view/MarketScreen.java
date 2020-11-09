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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MarketScreen implements IScreen {
    private int width;
    private int height;
    private Player player;
    private Label displayMarketLabel;
    private Label moneyLabel;
    private Plot[] plots;
    private GridPane inventoryPane;
    private Button inventoryButton;
    private Button farmButton;
    private HBox plotBox;
    private GridPane marketPane;
    private GridPane forHirePane;
    private Market market;
    private Inventory playerInventory;
    private Inventory marketInventory;
    private Label hireMessage;

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
        inventoryPane = getInventoryPane();
        marketPane = getMarketPane();
        forHirePane = getForHirePane();
        hireMessage = new Label("Hire a worker to assist you with your farming.");
    }

    private GridPane getInventoryPane() {
        inventoryPane = new GridPane();
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
                } else if (crop != null && (crop instanceof Fertilizer || crop instanceof Pesticide)) {
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
        playerInventory.setInventoryPane(inventoryPane);
        inventoryPane.getStyleClass().add("inventoryPane");
        return inventoryPane;
    }

    private GridPane getForHirePane() {
        forHirePane = new GridPane();
        int[] skillLevels = {1, 2, 3};
        Label workerLabel = null;
        FarmWorker helper = null;

        for (int i = 0; i < 3; i++) {
            helper = new FarmWorker(i+1);
            workerLabel = new Label(helper.toString());
            forHirePane.add(workerLabel, i, 0);
            FarmWorker finalHelper = helper;
            workerLabel.setOnMouseClicked((e) -> {
                if(this.player.hireWorker(finalHelper)) {
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

    private GridPane getMarketPane() {
        marketPane = new GridPane();
        int j = -1;
        for (int i = 0; i < 20; i++) {
            Item crop = null;
            Label cropLabel = new Label("");
            try {
                crop = marketInventory.getInventoryList().get(i);
                if (crop != null && crop instanceof Crop) {
                    cropLabel = new Label(((Crop) crop).toString("buy"));
                } else if (crop != null && (crop instanceof Fertilizer || crop instanceof Pesticide)) {
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
                            playerInventory.addToPane(finalCrop);
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
        marketInventory.setInventoryPane(marketPane);
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

        VBox inventoryWithLabel = new VBox(inventoryLabel, inventoryPane);

        Label forHireLabel = new Label("Farm Workers for Hire");

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

        Text buySell = new Text("Click on item in your inventory to sell it or"
                + " click on item in the market inventory to buy");
        VBox vbox = new VBox(moneyLabel, displayMarketLabel, marketPane, forHireLabel, forHirePane, hireMessage, inventoryWithLabel,
                buySell);
        buySell.getStyleClass().add("moneyLabel");
        inventoryLabel.getStyleClass().add("inventoryLabel");
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
