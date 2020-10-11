package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Inventory {
    private ObservableList<Crop> inventoryList;

    private int size;

    private static final int CAPACITY = 30;

    private GridPane inventoryPane;

    public Inventory(String startSeed, String difficulty) {
        size = 0;
        inventoryList = FXCollections.observableArrayList();
        inventoryPane = new GridPane();
        addStartSeed(startSeed, difficulty);
    }

    private void addStartSeed(String startSeed, String difficulty) {
        for (int i = 0; i < 3; i++) {
            Crop starterCrop = new Crop(startSeed, difficulty, CropStage.SEED);
            addItem(starterCrop);
        }
    }

    public boolean addItem(Crop item) {
        if (size == CAPACITY) {
            return false;
        }

        inventoryList.add(item);
        addToPane(item);
        size++;
        return true;
    }


    public void removeItem(int targetCrop) {
        if (targetCrop >=0 && targetCrop < inventoryList.size()) {
            inventoryList.set(targetCrop, null);
            removeFromPane(targetCrop);
            size--;
        }
    }

    private void addToPane(Crop item) {
        for (int i = 0; i < Inventory.getCAPACITY(); i++) {
            if (i >= inventoryPane.getChildren().size()) {
                Label label = new Label(item.toString("sell"));
                inventoryPane.getChildren().add(label);
            }
            String labelContent = "foo";

            if (inventoryPane.getChildren().get(i) != null) {
                labelContent = ((Label) inventoryPane.getChildren().get(i)).getText();
            }

            if (inventoryPane.getChildren().get(i) == null ||
                    labelContent.equals("")) {
                Label temp = (Label) inventoryPane.getChildren().get(i);
                temp.setText(item.toString("sell"));
                return;
            }
        }
    }

    private void removeFromPane(int targetCrop) {
        Label temp = (Label) inventoryPane.getChildren().get(targetCrop);
        temp.setText("");
    }

    public void setInventoryPane(GridPane inventoryPane) { this.inventoryPane = inventoryPane; }

    public GridPane getInventoryPane() { return inventoryPane; }

    public ObservableList<Crop> getInventoryList() { return inventoryList; }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }

    public static int getCAPACITY() { return CAPACITY; }
}
