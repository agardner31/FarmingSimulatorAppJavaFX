package model;

import java.util.Random;

public class FarmWorker {

    private int wage;
    private boolean paid;
    private String name;
    private int skill;


    public FarmWorker(int skill) {
        setWage(skill);
        selectName();
        this.paid = true;
        this.skill = skill;
    }

    private void setWage(int skill) {
        if (skill == 1) {
            this.wage = 10;
        } else if (skill == 2) {
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

    public String toString() {
        String level = "";
        if (skill == 1) {
            level = "Apprentice";
        } else if (skill == 2) {
            level = "Ordinary Joe";
        } else {
            level = "Master Farmer";
        }
        String label = this.name + "\n" + level + "\nDaily Wage: " + this.wage;
        return label;
    }
}
