package model;

import java.util.Random;

public class FarmWorker {

    private int wage;
    private boolean paid;
    private String name;

    public FarmWorker(String difficulty) {
        setWage(difficulty);
        selectName();
        this.paid = true;

    }

    private void setWage(String skill) {
        if (skill.equals("Apprentice")) {
            this.wage = 10;
        } else if (skill.equals("Ordinary Joe")) {
            this.wage = 20;
        } else {
            this.wage = 30;
        }
    }

    private void setPaid(boolean paid) {
        this.paid = paid;
    }

    private boolean getPaid() {
        return this.paid;
    }

    private void selectName() {
        String[] potentialNames = {"Anna", "Emily", "Sebastian", "Ben", "Justin"};
        Random rand = new Random();
        this.name = potentialNames[rand.nextInt(potentialNames.length)];
    }

    public String getName() {
        return name;
    }

    public int getWage() {
        return wage;
    }
}
