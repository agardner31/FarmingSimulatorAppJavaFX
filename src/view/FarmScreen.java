package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Crop;
import model.Inventory;
import model.Player;
import controller.Controller;
import model.Plot;

public class FarmScreen implements IScreen {
    private int width;
    private int height;
    private Player player;
    private Label displayDateLabel;
    private Label moneyLabel;
    private Plot[] plots;
    private Inventory inventory;
    private GridPane inventoryPane;
    private Button inventoryButton;
    private Button marketButton;
    private HBox plotBox;

    public FarmScreen(int width, int height, String difficulty, String startSeed, Player player) {
        this.width = width;
        this.height = height;
        this.player = player;
        displayDateLabel = new Label("Day 1");
        moneyLabel = new Label("Money: $" + player.getMoney() + ".00");
        plots = player.getFarm().getPlots();
        inventory = player.getInventory();
        inventoryPane = getInventoryPane();
        inventoryButton = new Button("Inventory");
        plotBox = fillPlotPane();
    }

    private GridPane getInventoryPane() {
        inventoryPane = new GridPane();
        int j = -1;
        for (int i = 0; i < Inventory.getCapacity(); i++) {
            Crop crop;
            Label cropLabel = new Label("");
            try {
                crop = inventory.getInventoryList().get(i);
                if (crop != null) {
                    cropLabel = new Label(crop.toString("sell"));
                }
            } catch (IndexOutOfBoundsException e) { }
            cropLabel.getStyleClass().add("cropBox");
            if (i % 10 == 0) {
                j++;
            }
            /*final int targetCrop = i;
            cropLabel.setOnMouseClicked((e) -> {
                inventory.removeItem(targetCrop); //how to get the specific inventory item
            });*/

            inventoryPane.add(cropLabel, i % 10, j);
        }
        inventory.setInventoryPane(inventoryPane);
        inventoryPane.getStyleClass().add("inventoryPane");
        return inventoryPane;
    }

    public Scene getScene() {
        // farm scene

        Label inventoryLabel = new Label("Items");

        VBox inventoryWithLabel = new VBox(inventoryLabel, inventoryPane);
        inventoryWithLabel.setVisible(false);

        inventoryButton.setOnAction((e) -> {
            if (!inventoryWithLabel.isVisible()) {
                inventoryWithLabel.setVisible(true);
            } else {
                inventoryWithLabel.setVisible(false);
            }
        });

        //moves to market scene
        marketButton = new Button("Market");
        marketButton.setVisible(true);
        marketButton.setOnAction((e) -> {
            Controller.enterMarket(player, player.getDifficulty());
        });

        VBox vbox = new VBox(moneyLabel, displayDateLabel, plotBox, inventoryWithLabel);

        inventoryButton.getStyleClass().add("inventoryButton");
        inventoryLabel.getStyleClass().add("inventoryLabel");
        moneyLabel.getStyleClass().add("moneyLabel");
        displayDateLabel.getStyleClass().add("displayDateLabel");
        plotBox.getStyleClass().add("plotBox");
        vbox.getStyleClass().add("vBox");

        HBox buttonRow = new HBox(inventoryButton, marketButton);
        buttonRow.setSpacing(10);
        VBox finalScene = new VBox(buttonRow, vbox);
        finalScene.setStyle("-fx-background-color: #658E6E; -fx-padding: 15");

        return new Scene(finalScene, width, height);
    }

    private HBox fillPlotPane() {
        HBox plotBox = new HBox();

        //plot box 1
        Plot temp = plots[0];
        Label plotLabel1 = new Label("Plot #" + 2 + "\nPlot Type:" + temp.getType() + "\nGrowth Stage:" + (temp.getCrop()).getStage().toString());
        plotLabel1.setOnMouseEntered(e -> {
            plotLabel1.setScaleX(1.5);
            plotLabel1.setScaleY(1.5);
        });
        plotLabel1.setOnMouseExited(e -> {
            plotLabel1.setScaleX(1);
            plotLabel1.setScaleY(1);
        });

        plotLabel1.getStyleClass().add("plotLabel");
        ImageView img1 = new ImageView(temp.getImg());
        Button growButton1 = new Button("Grow!");
        growButton1.setOnAction(e -> {
            plots[0].getCrop().incrementStage();
            plotLabel1.setText("Plot #" + 1 + "\nPlot Type:" + plots[0].getType() + "\nGrowth Stage:"
                    + (plots[0].getCrop()).getStage().toString());
        });
        VBox singlePlot1 = new VBox(plotLabel1, img1, growButton1);

        //plot box 2
        temp = plots[1];
        Label plotLabel2 = new Label("Plot #" + 2 + "\nPlot Type:" + temp.getType() + "\nGrowth Stage:" + (temp.getCrop()).getStage().toString());
        plotLabel2.setOnMouseEntered(e -> {
            plotLabel2.setScaleX(1.5);
            plotLabel2.setScaleY(1.5);
        });
        plotLabel2.setOnMouseExited(e -> {
            plotLabel2.setScaleX(1);
            plotLabel2.setScaleY(1);
        });

        plotLabel2.getStyleClass().add("plotLabel");
        ImageView img2 = new ImageView(temp.getImg());
        Button growButton2 = new Button("Grow!");
        growButton2.setOnAction(e -> {
            plots[1].getCrop().incrementStage();
            plotLabel2.setText("Plot #" + 2 + "\nPlot Type:" + plots[1].getType() + "\nGrowth Stage:"
                    + (plots[1].getCrop()).getStage().toString());
        });
        VBox singlePlot2 = new VBox(plotLabel2, img2, growButton2);


        //plot box 3
        temp = plots[2];
        Label plotLabel3 = new Label("Plot #" + 3 + "\nPlot Type:" + temp.getType() + "\nGrowth Stage:" + (temp.getCrop()).getStage().toString());
        plotLabel3.setOnMouseEntered(e -> {
            plotLabel3.setScaleX(1.5);
            plotLabel3.setScaleY(1.5);
        });
        plotLabel3.setOnMouseExited(e -> {
            plotLabel3.setScaleX(1);
            plotLabel3.setScaleY(1);
        });

        plotLabel3.getStyleClass().add("plotLabel");
        ImageView img3 = new ImageView(temp.getImg());
        Button growButton3 = new Button("Grow!");
        growButton3.setOnAction(e -> {
            plots[2].getCrop().incrementStage();
            plotLabel3.setText("Plot #" + 3 + "\nPlot Type:" + plots[2].getType() + "\nGrowth Stage:"
                    + (plots[2].getCrop()).getStage().toString());
        });

        VBox singlePlot3 = new VBox(plotLabel3, img3, growButton3);

        //plot box 4
        temp = plots[3];
        Label plotLabel4 = new Label("Plot #" + 4 + "\nPlot Type:" + temp.getType() + "\nGrowth Stage:"
                + (temp.getCrop()).getStage().toString());
        plotLabel4.setOnMouseEntered(e -> {
            plotLabel4.setScaleX(1.5);
            plotLabel4.setScaleY(1.5);
        });
        plotLabel4.setOnMouseExited(e -> {
            plotLabel4.setScaleX(1);
            plotLabel4.setScaleY(1);
        });

        plotLabel4.getStyleClass().add("plotLabel");
        ImageView img4 = new ImageView(temp.getImg());
        Button growButton4 = new Button("Grow!");
        growButton4.setOnAction(e -> {
            plots[3].getCrop().incrementStage();
            plotLabel4.setText("Plot #" + 4 + "\nPlot Type:" + plots[3].getType() + "\nGrowth Stage:"
                    + (plots[3].getCrop()).getStage().toString());
        });
        VBox singlePlot4 = new VBox(plotLabel4, img4, growButton4);

        //plot box 5
        temp = plots[4];
        Label plotLabel5 = new Label("Plot #" + 3 + "\nPlot Type:" + temp.getType() + "\nGrowth Stage:" + (temp.getCrop()).getStage().toString());
        plotLabel5.setOnMouseEntered(e -> {
            plotLabel5.setScaleX(1.5);
            plotLabel5.setScaleY(1.5);
        });
        plotLabel5.setOnMouseExited(e -> {
            plotLabel5.setScaleX(1);
            plotLabel5.setScaleY(1);
        });

        plotLabel5.getStyleClass().add("plotLabel");
        ImageView img5 = new ImageView(temp.getImg());
        Button growButton5 = new Button("Grow!");
        growButton5.setOnAction(e -> {
            plots[4].getCrop().incrementStage();
            plotLabel5.setText("Plot #" + 5 + "\nPlot Type:" + plots[4].getType() + "\nGrowth Stage:"
                    + (plots[4].getCrop()).getStage().toString());
        });
        VBox singlePlot5 = new VBox(plotLabel5, img5, growButton5);

        plotBox.getChildren().addAll(singlePlot1, singlePlot2, singlePlot3, singlePlot4, singlePlot5);

        return plotBox;
    }


    public void harvestCrop() {
        //harvest a crop
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public Label getDisplayDateLabel() {
        return displayDateLabel;
    }

    public void setDisplayDateLabel(int day) {
        displayDateLabel = new Label("Day " + day);
    }

    public Label getMoneyLabel() {
        return moneyLabel;
    }

    public void setMoneyLabel() {
        this.moneyLabel = new Label("Money: $" + player.getMoney() + ".00");
    }

    public Plot[] getPlots() {
        return plots;
    }
}
