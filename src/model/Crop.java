package model;

public class Crop {
    private String type;

    private CropStage stage;

    private int buyPrice;

    private int sellPrice;

    public Crop(String type, String difficulty) {
        this(type, difficulty, CropStage.SEED);
    }

    public Crop(String type, String difficulty, CropStage stage) {
        this.type = type;
        this.stage = stage;
        setPrice(difficulty, type);
    }

    private void setPrice(String difficulty, String type) {
        if (difficulty.equals("Apprentice")) {
            setPriceHelper(type, 3);
        } else if (difficulty.equals("Ordinary Joe")) {
            setPriceHelper(type, 2);
        } else {
            setPriceHelper(type, 1);
        }
    }

    private void setPriceHelper(String type, int difficulty) {
        if (type.equals("Pumpkin")) {
            buyPrice = 8 / difficulty;
            sellPrice = 16 * difficulty;
        } else if (type.equals("Watermelon")) {
            buyPrice = 5 / difficulty;
            sellPrice = 10 * difficulty;
        } else if (type.equals("Tomato")) {
            buyPrice = 3 / difficulty;
            sellPrice = 6 * difficulty;
        } else if (type.equals("Squash")) {
            buyPrice = 4 / difficulty;
            sellPrice = 8 * difficulty;
        }
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public String getType() {
        return type;
    }

    public CropStage getStage() {
        return stage;
    }

    public CropStage incrementStage() {
        if (stage.equals(CropStage.SEED)) {
            stage = CropStage.IMMATURE;
        } else if (stage.equals(CropStage.IMMATURE)) {
            stage = CropStage.MATURE;
        }
        return stage;
    }
}
