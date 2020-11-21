package model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageSelector { //allows classes using Image to be serializable
    public static Image getImg(Crop crop) {
        Image img = null;
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
                    } else if (crop.getType().equals("Magic Beans")) {
                        img = new Image(new FileInputStream("images/beanstalk.jpg"));
                    }
                } else if (cropStage.equals("Mature")) {
                    if (crop.getType().equals("Pumpkin")) {
                        img = new Image(new FileInputStream("images/maturePumpkin.jpg"));
                    } else if (crop.getType().equals("Tomato")) {
                        img = new Image(new FileInputStream("images/matureTomato.jpg"));
                    } else if (crop.getType().equals("Corn")) {
                        img = new Image(new FileInputStream("images/matureCorn.jpg"));
                    } else if (crop.getType().equals("Magic Beans")) {
                        img = new Image(new FileInputStream("images/beanstalk.jpg"));
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
}
