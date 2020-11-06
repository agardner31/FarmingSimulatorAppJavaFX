package model;

public interface Item {
    void setPrice(String difficulty, String type);

    void setPriceHelper(String type, double difficultyMultiplier);

    int getBuyPrice();

    int getBaseBuyPrice();
}
