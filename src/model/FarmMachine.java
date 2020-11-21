package model;

import java.io.Serializable;

public interface FarmMachine {
    int getMultiplier();

    void setPrice(String difficulty);

    int getBuyPrice();

    int getBaseBuyPrice();
}
