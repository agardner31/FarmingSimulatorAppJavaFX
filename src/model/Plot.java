package model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class Plot implements Serializable {

    private Crop crop;

    private String type;

    private String title;

    private int waterLevel;

    private int fertilizerLevel;

    public Plot() {
        this(new Crop("Pumpkin", "Apprentice"), 0);
        type = "Pumpkin";
    }

    public Plot(Crop crop, int title) {
        this.crop = crop;
        if (crop != null) {
            this.type = crop.getType();
        }
        this.title = "Plot " + title;
        waterLevel = 50;
        fertilizerLevel = 0;
    }

    public Image getImg() {
        return ImageSelector.getImg(crop);
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    public void setFertilizerLevel(int fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
    }

    public void water(int input) {
        waterLevel += input;
        if (waterLevel > 100) {
            if (this.crop != null) {
                this.crop.setStage(CropStage.DEAD);
            }
            waterLevel = 100;
        }
    }

    public void dry(int input) {
        waterLevel -= input;
        if (waterLevel <= 0) {
            waterLevel = 0;
            if (this.crop != null) {
                this.crop.setStage(CropStage.DEAD);
            }
        }

        if (fertilizerLevel > 20) {
            fertilizerLevel -= 20;
        } else {
            fertilizerLevel = 0;
        }
    }
}
