package model;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Market {

    public Market(String startSeed, String difficulty) {
        Inventory inv = new Inventory(startSeed, difficulty);
    }
}
