package model;

import java.util.Random;

public class Farm {
    private Plot[] plots;

    private final int initialCapacity = 5;

    public Farm(String difficulty) {
        plots = new Plot[initialCapacity];
//        String[] randomType = new String[]{"Pumpkin", "Tomato", "Corn"};
//        Random rand = new Random();
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(null, i + 1);
        }
    }

    public Farm(int size, String difficulty) {
        plots = new Plot[size];
        String[] randomType = new String[]{"Pumpkin", "Tomato", "Corn"};
        Random rand = new Random();
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(new Crop(randomType[rand.nextInt(3)],
                    difficulty), i + 1);
        }
    }

    public Plot[] getPlots() {
        return plots;
    }
}
