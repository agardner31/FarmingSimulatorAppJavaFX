package model;

import java.util.Random;

public class Farm {
    private Plot[] plots;

    private final int initialCapacity = 10;

    private boolean rain = false;
    private boolean drought = false;
    private boolean locusts = false;
    private int locustKillsLeft = 0;
    private int locustKills;
    private int randomRainOrDrought;


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
        int randBound = 6;
        if (difficulty.equals("Ordinary Joe")) {
            randBound++;
        } else if (difficulty.equals("Master Farmer")) {
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

    public void recalculateRandomRainOrDrought() {
        int returnVal;
        Random rand = new Random();
        int randBound = 3;
        int randNum = rand.nextInt(randBound);
        if (randNum == 1) {
            returnVal = 10;
        } else if (randNum == 2) {
            returnVal = 20;
        } else {
            returnVal = 30;
        }
        this.randomRainOrDrought = returnVal;
    }

    public int getRandomRainOrDrought() {
        return this.randomRainOrDrought;
    }

    public void recalculateDroughtOdds(String difficulty, String season) {
        Random rand = new Random();
        int randBound = 7;
        if (difficulty.equals("Ordinary Joe")) {
            randBound++;
        } else if (difficulty.equals("Apprentice")) {
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
        int randBound = 5;
        if (difficulty.equals("Ordinary Joe")) {
            randBound++;
        } else if (difficulty.equals("Apprentice")) {
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

    public int randomLocustKills(int input) {
        if (input == -1) {
            int numFullPlots = 0;
            for (int i = 0; i < plots.length; i++) {
                if (plots[i] != null && plots[i].getCrop() != null
                        && !plots[i].getCrop().getStage().equals(CropStage.DEAD)) {
                    numFullPlots++;
                }
            }
            Random rand = new Random();
            int randBound = 3;
            int randNum = rand.nextInt(randBound);
            if (randNum == 1) {
                locustKills = numFullPlots;
            } else if (randNum == 2) {
                locustKills = numFullPlots / 3;
            } else {
                locustKills = numFullPlots / 2;
            }
            if (locustKills < 0) {
                locustKills = 0;
            }
            locustKillsLeft = locustKills;
        } else {
            locustKillsLeft--;
        }
        return locustKillsLeft;
    }

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

    public int getLocustKills() {
        return this.locustKills;
    }
}
