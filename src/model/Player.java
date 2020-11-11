package model;

public class Player {
    private String difficulty;

    private String season;

    private Farm farm;

    private int money;

    private Inventory inventory;

    private int day;

    private FarmWorker[] farmWorkers;

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

    public int addMoney(int money) {
        this.money += money;
        return money;
    }

    public int getDay() {
        return day;
    }

    public void incrementDay() {
        day++;
    }
}
