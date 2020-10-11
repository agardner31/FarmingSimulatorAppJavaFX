package model;

public class Farm {
    private Plot[] plots;

    private final int initialCapacity = 10;

    public Farm(String difficulty) {
        plots = new Plot[initialCapacity];
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(new Crop("Pumpkin", difficulty), i + 1);
        }
    }

    public Farm(int size, String difficulty) {
        plots = new Plot[size];
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(new Crop("Pumpkin", difficulty), i + 1);
        }
    }

    public Plot[] getPlots() {
        return plots;
    }
}
