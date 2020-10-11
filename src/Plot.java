package model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Plot {

    private Crop crop;

    private String type;

    private String title;

    private Image img;

    public Plot() {
        type = "Dirt";
        title = "";
        try {
            img = new Image(new FileInputStream("resources/images/dirt.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Plot(Crop crop, int title) {
        this.crop = crop;
        this.type = crop.getType();
        this.title = "Plot " + title;
        try {
            img = new Image(new FileInputStream("resources/images/seedling.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getImg() {
        return img;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop() { this.crop = crop; }

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
}
