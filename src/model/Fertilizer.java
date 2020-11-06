package model;

public class Fertilizer implements Item {
    private String type = "Fertilizer";

    private int buyPrice;

    private int baseBuyPrice;

    public Fertilizer(String difficulty) {
        setPrice(difficulty, type);
    };

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

    @Override
    public void setPriceHelper(String type, double difficultyMultiplier) {
        if (type.equals("Fertilizer")) {
            baseBuyPrice = 3;
            buyPrice = (int) (baseBuyPrice * difficultyMultiplier);
        }
        buyPrice = (int) (buyPrice + Math.random() * .5 * buyPrice - .25 * buyPrice);
    }

    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    @Override
    public int getBaseBuyPrice() {
        return baseBuyPrice;
    }

    @Override
    public String toString() {
        return toString("");
    }

    public String toString(String type) {
        if (type.equals("buy")) {
            return this.type + "\n\n" + "$" + buyPrice + ".00";
        } else {
            return this.type;
        }
    }

    public String getType() {
        return type;
    }
}
