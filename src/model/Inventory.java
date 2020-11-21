package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {
    private ArrayList<Item> inventoryList;

    private int size;

    private static final int CAPACITY = 30;


    public Inventory(String startSeed, String difficulty) {
        this(difficulty);
        addStartSeed(startSeed, difficulty);
    }

    public Inventory(String difficulty) {
        size = 0;
        inventoryList = new ArrayList();
    }

    private void addStartSeed(String startSeed, String difficulty) {
        for (int i = 0; i < 3; i++) {
            Item starterCrop = new Crop(startSeed, difficulty, CropStage.SEED);
            addItem(starterCrop);
        }
    }

    public boolean addItem(Item item) {
        if (size == CAPACITY) {
            return false;
        }

        inventoryList.add(item);
        //addToPane(item);
        size++;
        return true;
    }


    public boolean removeItem(int targetItem) {
        if (targetItem >= 0 && targetItem < inventoryList.size()) {
            inventoryList.remove(targetItem);
            size--;
            return true;
        }
        return false;
    }

    /*public void addToPane(Item item) {
        for (int i = 0; i < Inventory.getCapacity(); i++) {
            if (i >= inventoryPane.getChildren().size()) {
                Label label = new Label(item.toString());
                inventoryPane.getChildren().add(label);
                return;
            }
            Label temp = (Label) inventoryPane.getChildren().get(i);
            if (temp.getText().equals("")
                    || inventoryPane.getChildren().get(i) == null) {
                temp.setText(item.toString());
                return;
            }
        }
    }*/

    /*private void removeFromPane(int targetCrop) {
        Label temp = (Label) inventoryPane.getChildren().get(targetCrop);
        temp.setText("");
    }*/

    /*public void setInventoryPane(GridPane inventoryPane) {
        this.inventoryPane = inventoryPane;
    }

    public GridPane getInventoryPane() {
        return inventoryPane;
    }*/

    public ArrayList<Item> getInventoryList() {
        return inventoryList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static int getCapacity() {
        return CAPACITY;
    }

    public boolean isFull() {
        return size == CAPACITY;
    }
}
