package model;

public class Fertilizer implements Item {
    private int buyPrice;

    private int baseBuyPrice;

    public Fertilizer() {};

    @Override
    public void setPrice(String difficulty, String type) {

    }

    @Override
    public void setPriceHelper(String type, double difficultyMultiplier) {

    }

    @Override
    public int getBuyPrice() {
        return 0;
    }

    @Override
    public int getBaseBuyPrice() {
        return 0;
    }
}
