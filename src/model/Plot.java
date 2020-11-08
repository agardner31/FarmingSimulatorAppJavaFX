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

    private int fertilizerLevel;

    public Plot() {
        this(new Crop("Pumpkin", "Apprentice"), 0);
        type = "Pumpkin";
    }

    public Plot(Crop crop, int title) {
        this.crop = crop;
        if (crop != null) {
            this.type = ((Crop) crop).getType();
        }
        this.title = "Plot " + title;
        try {
            img = new Image(new FileInputStream("images/empty.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        waterLevel = 50;
        fertilizerLevel = 0;
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
