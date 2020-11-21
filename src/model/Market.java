package model;

import java.io.Serializable;

public class Market implements Serializable {
    private Inventory marketInventory;
    private Inventory playerInventory;
    private String difficulty;
    private Player player;
    public Market(Player player, String difficulty) {
        marketInventory = new Inventory(difficulty);
        playerInventory = player.getInventory();
        this.difficulty = difficulty;
        this.player = player;
        fillMarketInventory();
    }

    private void fillMarketInventory() {
        marketInventory.addItem(new Crop("Pumpkin", difficulty, CropStage.SEED));
        marketInventory.addItem(new Crop("Corn", difficulty, CropStage.SEED));
        marketInventory.addItem(new Crop("Tomato", difficulty, CropStage.SEED));
        marketInventory.addItem(new Fertilizer(difficulty));
        marketInventory.addItem(new Pesticide(difficulty));
        marketInventory.addItem(new Crop("Magic Beans", difficulty, CropStage.SEED));
    }

    public Inventory getMarketInventory() {
        return marketInventory;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void buy(Item crop, int price) {
        if (crop != null) {
            player.setMoney(player.getMoney() - price);
        }
    }

    public void sell(Item crop) {
        if (crop != null && crop instanceof Crop) {
            player.setMoney(player.getMoney() + ((Crop) crop).getSellPrice());
        }
    }
}
