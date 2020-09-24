package main;

public class Player {
    private int difficulty;

    private Farm farm;

    private int money;

    public Player() {
        this(0, new Farm(), 100);
    }

    public Player(int difficulty) {
        this(difficulty, new Farm(), 100 + (difficulty * 100));
    }

    public Player(int difficulty, Farm farm) {
        this(difficulty, farm, 100 + (difficulty * 100));
    }

    public Player(int difficulty, Farm farm, int money) {
        this.difficulty = difficulty;
        this.farm = farm;
        this. money = money;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
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
