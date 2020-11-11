package model;

public class Crop implements Item {
    private String type;

    private CropStage stage;

    private int buyPrice;

    private int sellPrice;

    private int baseBuyPrice;

    private int baseSellPrice;

    private boolean hasPesticides;

    public Crop(String type, String difficulty) {
        this(type, difficulty, CropStage.SEED);
    }

    public Crop(String type, String difficulty, CropStage stage) {
        this.type = type;
        this.stage = stage;
        setPrice(difficulty, type);
        hasPesticides = false;
    }

    @Override
    public void setPrice(String difficulty, String type) {
        if (difficulty.equals("Apprentice")) {
            setPriceHelper(type, 2);
        } else if (difficulty.equals("Ordinary Joe")) {
            setPriceHelper(type, 1.5);
        } else {
            setPriceHelper(type, 1);
        }
    }

    public void setPriceHelper(String type, double difficultyMultiplier) {
        if (type.equals("Pumpkin")) {
            buyPrice = 8;
            baseBuyPrice = buyPrice;
            sellPrice = (int) (buyPrice * difficultyMultiplier);
            baseSellPrice = sellPrice;
        } else if (type.equals("Corn")) {
            buyPrice = 6;
            baseBuyPrice = buyPrice;
            sellPrice = (int) (buyPrice * difficultyMultiplier);
            baseSellPrice = sellPrice;
        } else if (type.equals("Tomato")) {
            buyPrice = 5;
            baseBuyPrice = buyPrice;
            sellPrice = (int) (buyPrice * difficultyMultiplier);
            baseSellPrice = buyPrice;
        }
        sellPrice = (int) (sellPrice + Math.random() * .5 * sellPrice - .25 * sellPrice);
        buyPrice = (int) (buyPrice + Math.random() * .5 * buyPrice - .25 * buyPrice);
    }

    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public int getBaseBuyPrice() {
        return baseBuyPrice;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public String getType() {
        return type;
    }

    public CropStage getStage() {
        return stage;
    }

    public void setStage(CropStage stage) {
        this.stage = stage;
    }

    public void grow() { //make sure to change label price by calling to String again
        if (stage.equals(CropStage.SEED)) {
            stage = CropStage.IMMATURE;
        } else if (stage.equals(CropStage.IMMATURE)) {
            stage = CropStage.MATURE;
        } else if (stage.equals(CropStage.MATURE)) {
            stage = CropStage.DEAD;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Crop) {
            Crop temp = (Crop) obj;
            boolean typeCheck = type.equals(temp.getType());
            boolean stageCheck = stage.equals(temp.getStage());
            return typeCheck && stageCheck;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return toString("sell");
    }

    public String toString(String type) {
        if (type.equals("buy")) {
            return this.type + "\n" + stage.toString() + "\n" + "$" + buyPrice + ".00";
        } else if (type.equals("sell")) {
            return this.type + "\n" + stage.toString() + "\n" + "$" + sellPrice + ".00";
        } else {
            return this.type + "\n" + stage.toString() + "\n" + "$0.00";
        }
    }

    public void spray() {
        hasPesticides = true;
        sellPrice *= .8;
    }

    public boolean hasPesticides() {
        return hasPesticides;
    }
}
