package model;

import java.util.Random;

public class Farm {
    private Plot[] plots;

    private final int initialCapacity = 10;

    private boolean rain = false;
    private boolean drought = false;
    private boolean locusts = false;


    public Farm(String difficulty) {
        plots = new Plot[initialCapacity];
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(null, i + 1);
        }
    }

    public Farm(int size, String difficulty) {
        plots = new Plot[size];
        for (int i = 0; i < plots.length; i++) {
            plots[i] = new Plot(null, i + 1);
        }
    }

    public void recalculateRainOdds(String difficulty, String season) {
        Random rand = new Random();
        int randBound = 3;
        if (difficulty.equals("Ordinary Joe")) {
            randBound++;
        } else if (difficulty.equals("Master Farmer")){
            randBound += 2;
        }
        if (season.equals("Winter")) {
            randBound++;
        } else if (season.equals("Summer")) {
            randBound += 2;
        }
        int randNum = rand.nextInt(randBound);
        if (randNum == 1) {
            rain = true;
        } else {
            rain = false;
        }
    }

//    public int randomRainOrDrought() {
//
//    }

    public void recalculateDroughtOdds(String difficulty, String season) {
        Random rand = new Random();
        int randBound = 3;
        if (difficulty.equals("Ordinary Joe")) {
            randBound++;
        } else if (difficulty.equals("Apprentice")){
            randBound += 2;
        }
        if (season.equals("Fall")) {
            randBound++;
        } else if (season.equals("Spring")) {
            randBound += 2;
        }
        int randNum = rand.nextInt(randBound);
        if (randNum == 1) {
            drought = true;
        } else {
            drought = false;
        }
    }

    public void recalculateLocustsOdds(String difficulty, String season) {
        Random rand = new Random();
        int randBound = 3;
        if (difficulty.equals("Ordinary Joe")) {
            randBound++;
        } else if (difficulty.equals("Apprentice")){
            randBound += 2;
        }
        if (season.equals("Spring")) {
            randBound++;
        } else if (season.equals("Winter")) {
            randBound += 2;
        }
        int randNum = rand.nextInt(randBound);
        if (randNum == 1) {
            locusts = true;
        } else {
            locusts = false;
        }
    }

//    public int randomLocustKills() {
//        this.getPlots().
//        Random rand = new Random();
//        int randBound = 3;
//        int randNum = rand.nextInt(randBound);
//        if (randNum == 1) {
//
//        }
//    }

    public boolean getRain() {
        return this.rain;
    }

    public boolean getDrought() {
        return this.drought;
    }

    public boolean getLocusts() {
        return this.locusts;
    }

    public Plot[] getPlots() {
        return plots;
    }
}
