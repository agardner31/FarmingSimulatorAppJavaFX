package model;

import javafx.scene.layout.GridPane;

public class Inventory {
    private Crop[] inventoryArray;

    private int size;

    private static final int CAPACITY = 30;

    private GridPane inventoryPane;

    public Inventory(String startSeed, String difficulty) {
        size = 0;
        inventoryArray = new Crop[CAPACITY];
        addStartSeed(startSeed, difficulty);
        inventoryPane = new GridPane();
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

        inventoryArray[size++] = item;
        return true;
    }

    public void setInventoryPane(GridPane inventoryPane) { this.inventoryPane = inventoryPane; }

    public GridPane getInventoryPane() { return inventoryPane; }

    public Crop[] getInventoryArray() { return inventoryArray; }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }

    public static int getCAPACITY() { return CAPACITY; }
}
