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
        this(new Crop("Pumpkin", "Apprentice"), 0);
        type = "Pumpkin";
    }

    public Plot(Crop crop, int title) {
        this.crop = crop;
        this.type = crop.getType();
        this.title = "Plot " + title;
        try {
            img = new Image(new FileInputStream("images/dirt.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setImage(Image img) {
        this.img = img;
    }
    public Image getImg() {
        String cropStage = crop.getStage().toString();
        try {
            if (cropStage.equals("Dirt")) {
                img = new Image(new FileInputStream("images/empty.jpg"));
            } else if (cropStage.equals("Seed")) {
                img = new Image(new FileInputStream("images/seeds.jpg"));
            } else if (cropStage.equals("Immature")) {
                if (type.equals("Pumpkin")) {
                    img = new Image(new FileInputStream("images/immaturePumpkin.jpg"));
                } else if (type.equals("Tomato")) {
                    img = new Image(new FileInputStream("images/immatureTomato.jpg"));
                } else if (type.equals("Corn")) {
                    img = new Image(new FileInputStream("images/immatureCorn.jpg"));
                }
            } else if (cropStage.equals("Mature")) {
                if (type.equals("Pumpkin")) {
                    img = new Image(new FileInputStream("images/maturePumpkin.jpg"));
                } else if (type.equals("Tomato")) {
                    img = new Image(new FileInputStream("images/matureTomato.jpg"));
                } else if (type.equals("Corn")) {
                    img = new Image(new FileInputStream("images/matureCorn.jpg"));
                }
            } else if (cropStage.equals("Harvested")) {
                img = new Image(new FileInputStream("images/empty.jpg"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return img;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop() {
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
}
