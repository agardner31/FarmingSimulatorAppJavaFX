package model;

public interface Item {
    void setPrice(String difficulty, String type);

    int getBuyPrice();

    int getBaseBuyPrice();
}
