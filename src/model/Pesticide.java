package model;

import java.io.Serializable;

public class Pesticide implements Serializable, Item {
    private int buyPrice;

    private int baseBuyPrice;

    private int cheapestPrice;

    public Pesticide(String difficulty) {
        setPrice(difficulty);
    };

    public void setPrice(String difficulty) {
        setPrice(difficulty, "Default Pesticide");
    }
    @Override
    public void setPrice(String difficulty, String type) {
        double difficultyMultiplier;
        if (difficulty.equals("Apprentice")) {
            difficultyMultiplier = 1;
        } else if (difficulty.equals("Ordinary Joe")) {
            difficultyMultiplier = 1.5;
        } else {
            difficultyMultiplier = 2;
        }
        baseBuyPrice = 2;
        buyPrice = (int) (baseBuyPrice * difficultyMultiplier);
        cheapestPrice = (int) (.75 * buyPrice);
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

    public int getCheapestPrice() {
        return cheapestPrice;
    }

    @Override
    public String toString() {
        return toString("");
    }

    public String toString(String type) {
        if (type.equals("buy")) {
            return "Pesticide" + "\n\n" + "$" + buyPrice + ".00";
        } else {
            return "Pesticide";
        }
    }

}
