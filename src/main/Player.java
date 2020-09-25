package main;

public class Player {
    private String difficulty;

    private Farm farm;

    private int money;

    public Player() {
        this("Apprentice", new Farm());
    }

    public Player(String difficulty) {

        this(difficulty, new Farm());
    }

    public Player(String difficulty, Farm farm) {
        this.difficulty = difficulty;
        this.farm = farm;
        if (difficulty.equals("Apprentice")) {
            this.money = 300;
        } else if (difficulty.equals("Ordinary Joe")) {
            this.money = 200;
        } else {
            this.money = 100;
        }
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
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
}
