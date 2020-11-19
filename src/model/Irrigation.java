package model;

public class Irrigation implements FarmMachine {
    private int multiplier;

    private int buyPrice;
    private int baseBuyPrice;

    public Irrigation(String difficulty) {
        multiplier = 2;
        setPrice(difficulty);
    }

    @Override
    public int getMultiplier() {
        return multiplier;
    }

    @Override
    public void setPrice(String difficulty) {
        if (difficulty.equals("Apprentice")) {
            setPriceHelper(1);
        } else if (difficulty.equals("Ordinary Joe")) {
            setPriceHelper(1.25);
        } else {
            setPriceHelper(1.5);
        }
    }

    public void setPriceHelper(double difficultyMultiplier) {
        buyPrice = 25;
        baseBuyPrice = buyPrice;
        buyPrice = (int) (buyPrice * difficultyMultiplier);
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
        return "Irrigation\n\n$" + buyPrice + ".00";
    }
}
