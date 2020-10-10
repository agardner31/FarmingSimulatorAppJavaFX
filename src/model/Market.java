package model;

import javafx.scene.PointLight;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Market {
    private Inventory marketInventory;
    private Inventory playerInventory;
    private String difficulty;
    private Player player;
    public Market(Player player, String difficulty) {
        marketInventory = new Inventory("Pumpkin", difficulty);
        playerInventory = player.getInventory();
        this.player = player;
    }

    private void fillMarketInventory() {
        for (int i = 0; i < marketInventory.getInventoryArray().length; i++) {
            marketInventory.getInventoryArray()[i] = new Crop("Pumpkin", difficulty, CropStage.SEED);
        }
    }

    public Inventory getMarketInventory() {
        return marketInventory;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void buy (Crop crop, int price) {
        player.setMoney(player.getMoney() - price);
    }

    public void sell(Crop crop) {
        player.setMoney(player.getMoney() + crop.getSellPrice());
    }
}
