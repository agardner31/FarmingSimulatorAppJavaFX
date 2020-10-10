package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Crop;
import model.Inventory;
import model.Market;
import model.Player;
import model.Plot;

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
    private Market market;
    private Inventory playerInventory;
    private Inventory marketInventory;

    public MarketScreen(int width, int height, String difficulty, String startSeed, Player player) {
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
    }

    private GridPane getInventoryPane() {
        inventoryPane = new GridPane();
        int j = -1;
        for (int i = 0; i < Inventory.getCAPACITY(); i++) {
            Crop crop = playerInventory.getInventoryArray()[i];
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
                market.sell(crop);
                playerInventory.removeItem(targetCrop); //how to get the specific inventory item
            });

            inventoryPane.add(cropLabel, i % 10, j);
        }
        playerInventory.setInventoryPane(inventoryPane);
        inventoryPane.getStyleClass().add("inventoryPane");
        return inventoryPane;
    }


    private GridPane getMarketPane() {
        marketPane = new GridPane();
        int j = -1;
        for (int i = 0; i < Inventory.getCAPACITY(); i++) {
            Crop crop = marketInventory.getInventoryArray()[i];
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
                int price = crop.getBuyPrice();
                if (player.getMoney() >= price) {
                    playerInventory.addItem(crop);
                    market.buy(crop, price);
                }
            });

            marketPane.add(cropLabel, i % 10, j);
        }
        marketInventory.setInventoryPane(marketPane);
        marketPane.getStyleClass().add("inventoryPane");
        return marketPane;
    }

    @Override
    public Scene getScene() {
        // farm scene

        Label inventoryLabel = new Label("Items");

        VBox inventoryWithLabel = new VBox(inventoryLabel, inventoryPane);

        //moves to market scene
        farmButton = new Button("Farm");
        farmButton.setVisible(true);
        farmButton.setOnAction((e) -> {
            Controller.enterFarm(player.getDifficulty(), "Pumpkin");
        });

        Text buySell = new Text("Click on item in your inventory to sell it or"
                + " click on item in the market inventory to buy");
        VBox vbox = new VBox(moneyLabel, displayMarketLabel, marketPane, inventoryWithLabel, buySell);

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
