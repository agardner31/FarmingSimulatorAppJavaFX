package model;

public class Inventory {
    private Crop[] inventoryArray;

    private int size;

    private static final int CAPACITY = 30;

    public Inventory() {
        size = 0;
        inventoryArray = new Crop[CAPACITY];
    }

    public boolean addItem(Crop item) {
        if (size == CAPACITY) {
            return false;
        }
        inventoryArray[size++] = item;
        return true;
    }

    public void removeItem(int index) {
        inventoryArray[index] = null;
        size--;
    }

    public Crop[] getInventoryArray() {
        return inventoryArray;
    }

    public int getSize() {
        return size;
    }

    public static int getCAPACITY() {
        return CAPACITY;
    }
}
