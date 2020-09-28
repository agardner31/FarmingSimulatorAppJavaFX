package main;

public class Farm {
    private Plot[] plots;

    private final int initialCapacity = 10;

    public Farm() {
        plots = new Plot[initialCapacity];
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(0, "None", i + 1);
        }
    }

    public Farm(int size) {
        plots = new Plot[size];
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(0, "None", i + 1);
        }
    }

    public Plot[] getPlots() {
        return plots;
    }
}
