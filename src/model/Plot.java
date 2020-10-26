package model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Plot {

    private Crop crop;

    private String type;

    private String title;

    private Image img;

    private int waterLevel;

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
        try {
            img = new Image(new FileInputStream("images/dirt.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        waterLevel = 50;
    }

    public void setImage(Image img) {
        this.img = img;
    }
    public Image getImg() {
        try {
            if (crop == null) {
                img = new Image(new FileInputStream("images/empty.jpg"));
            } else {
                String cropStage = crop.getStage().toString();
                if (cropStage.equals("Dirt")) {
                    img = new Image(new FileInputStream("images/empty.jpg"));
                } else if (cropStage.equals("Seed")) {
                    img = new Image(new FileInputStream("images/seeds.jpg"));
                } else if (cropStage.equals("Immature")) {
                    if (crop.getType().equals("Pumpkin")) {
                        img = new Image(new FileInputStream("images/immaturePumpkin.jpg"));
                    } else if (crop.getType().equals("Tomato")) {
                        img = new Image(new FileInputStream("images/immatureTomato.jpg"));
                    } else if (crop.getType().equals("Corn")) {
                        img = new Image(new FileInputStream("images/immatureCorn.jpg"));
                    }
                } else if (cropStage.equals("Mature")) {
                    if (crop.getType().equals("Pumpkin")) {
                        img = new Image(new FileInputStream("images/maturePumpkin.jpg"));
                    } else if (crop.getType().equals("Tomato")) {
                        img = new Image(new FileInputStream("images/matureTomato.jpg"));
                    } else if (crop.getType().equals("Corn")) {
                        img = new Image(new FileInputStream("images/matureCorn.jpg"));
                    }
                } else if (cropStage.equals("Harvested")) {
                    img = new Image(new FileInputStream("images/empty.jpg"));
                } else if (cropStage.equals("Dead")) {
                    img = new Image(new FileInputStream("images/empty.jpg"));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return img;
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

    public void dry() {
        if (waterLevel > 30) {
            waterLevel -= 30;
        } else {
            waterLevel = 0;
            //crop.setStage(CropStage.DEAD);
        }
    }
}
