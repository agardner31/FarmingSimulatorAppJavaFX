package model;

import java.io.Serializable;

public class Player implements Serializable {
    private String difficulty;

    private String season;

    private Farm farm;

    private int money;

    private Inventory inventory;

    private int day;

    private FarmWorker[] farmWorkers;

    private int numCropsHarvested;

    private int cheapestPrice;

    public Player() {
        this("Apprentice", new Farm("Apprentice"), "Corn", "Spring");
    }

    public Player(String difficulty, String startSeed) {

        this(difficulty, new Farm(difficulty), startSeed, "Spring");

    }

    public Player(String difficulty, Farm farm, String startSeed, String season) {
        this.difficulty = difficulty;
        this.farm = farm;
        this.season = season;
        farmWorkers = new FarmWorker[3];
        if (difficulty.equals("Apprentice")) {
            this.money = 300;
        } else if (difficulty.equals("Ordinary Joe")) {
            this.money = 200;
        } else {
            this.money = 100;
        }
        this.inventory = new Inventory(startSeed, this.difficulty);
        day = 1;
        numCropsHarvested = 0;
        Pesticide cheapestPesticide = new Pesticide(difficulty);
        cheapestPrice = cheapestPesticide.getCheapestPrice();
    }

    public int getFarmWorkerEfficiency() {
        int efficiency = 0;
        for (int i = 0; i < farmWorkers.length; i++) {
            if (farmWorkers[i] != null) {
                efficiency += farmWorkers[i].getSkill();
            }
        }
        return efficiency;
    }

    public FarmWorker[] getFarmWorkers() {
        return farmWorkers;
    }

    public void setFarmWorkers(FarmWorker[] farmWorkers) {
        this.farmWorkers = farmWorkers;
    }

    public boolean hireWorker(FarmWorker newWorker) {
        int count = 0;
        if (this.money >= newWorker.getWage()) {
            while (count < farmWorkers.length) {
                if (farmWorkers[count] == null) {
                    farmWorkers[count] = newWorker;
                    this.money -= newWorker.getWage();
                    return true;
                }
                count++;
            }
        }
        return false;
    }

    public int getNumCropsHarvested() {
        return numCropsHarvested;
    }

    public void incrementNumCropsHarvested() {
        numCropsHarvested++;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getSeason() {
        return season;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCheapestPrice() {
        return cheapestPrice;
    }

    public int addMoney(int money) {
        this.money += money;
        return money;
    }

    public int getDay() {
        return day;
    }

    public void incrementDay() {
        farm.resetCounts();
        day++;
    }

    public boolean buyMachine(FarmMachine machine) {
        if (machine instanceof Irrigation) {
            if (money >= machine.getBuyPrice() && !farm.hasIrrigation()) {
                money -= machine.getBuyPrice();
                farm.setIrrigation(true);
                farm.setDailyWaterLimit(machine.getMultiplier());
                return true;
            }
        } else {
            if (money >= machine.getBuyPrice() && !farm.hasTractor()) {
                money -= machine.getBuyPrice();
                farm.setTractor(true);
                farm.setDailyHarvestLimit(machine.getMultiplier());
                return true;
            }
        }
        return false;
    }

    public boolean buyPlot() {
        if (money >= farm.getPlotPrice()) {
            money -= farm.getPlotPrice();
            farm.recalcPlotPrice();
            farm.addPlot();
            return true;
        }
        return false;
    }
}
