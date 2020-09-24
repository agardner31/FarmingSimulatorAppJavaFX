package main;

public class Farm {
    private Plot[] plots;

    private final int INITIAL_CAPACITY = 10;

    public Farm() {
        plots = new Plot[INITIAL_CAPACITY];
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(0, null, i + 1);
        }
    }

    public Farm(int size) {
        plots = new Plot[size];
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(0, null, i + 1);
        }
    }
}
