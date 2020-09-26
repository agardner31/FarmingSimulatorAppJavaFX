package main;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class UserInterface extends Application {

    @Override
    public void start(Stage primaryStage) {

        // configuration scene
        VBox configVBox = new VBox();
        Label configTitle = new Label("Configuration");
        configTitle.setFont(new Font(20));
        HBox nameBox = new HBox();
        Label name = new Label("Name: ");
        TextField enterName = new TextField();
        nameBox.getChildren().addAll(name, enterName);
        nameBox.setAlignment(Pos.CENTER);
        HBox difficultyBox = new HBox();
        Label difficulty = new Label("Difficulty: ");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Apprentice",
                        "Ordinary Joe",
                        "Master Farmer"
                );
        ComboBox levels = new ComboBox(options);
        difficultyBox.getChildren().addAll(difficulty, levels);
        difficultyBox.setAlignment(Pos.CENTER);
        HBox seasonsBox = new HBox();
        Label seasons = new Label("Season: ");
        ObservableList<String> seasonsOptions =
                FXCollections.observableArrayList(
                        "Fall",
                        "Winter",
                        "Spring",
                        "Summer"
                );
        ComboBox seasonsList = new ComboBox(seasonsOptions);
        seasonsBox.getChildren().addAll(seasons, seasonsList);
        seasonsBox.setAlignment(Pos.CENTER);
        HBox seedBox = new HBox();
        Label seed = new Label("Seed: ");
        ObservableList<String> seedOptions =
                FXCollections.observableArrayList(
                        "Tomato",
                        "Pumpkin",
                        "Corn"
                );
        ComboBox seedList = new ComboBox(seedOptions);
        seedBox.getChildren().addAll(seed, seedList);
        seedBox.setAlignment(Pos.CENTER);
        Button startGame = new Button("Start Game");
        Label warning = new Label("");

        configVBox.getChildren().addAll(configTitle, nameBox, difficultyBox,
                seasonsBox, seedBox, startGame, warning);
        configVBox.setAlignment(Pos.BASELINE_CENTER);
        configVBox.setPadding(new Insets(50));
        configVBox.setSpacing(10);
        BackgroundFill configBackground = new BackgroundFill(Color.valueOf("#658E6E"),
                new CornerRadii(1), null);
        configVBox.setBackground(new Background(configBackground));
        Scene configurations = new Scene(configVBox, 800, 700);

        // start game button
        startGame.setOnAction((event) -> {    // lambda expression
            if ((enterName.getText() == null) || (enterName.getText().equals(""))) {
                warning.setText("Enter a valid name.");
            } else if (levels.getValue() == null) {
                warning.setText("Choose a difficulty.");
            } else if (seasonsList.getValue() == null) {
                warning.setText("Choose a season.");
            } else if (seedList.getValue() == null) {
                warning.setText("Choose a seed.");
            } else {
                primaryStage.setScene(enterFarm(levels.getValue().toString()));
            }
        });

        primaryStage.setTitle("FARM GAME");
        primaryStage.setScene(configurations);
        primaryStage.show();
    }

    public static Scene enterFarm(String difficulty) {
        // farm scene
        Player player = new Player(difficulty);
        double startingMoney = player.getMoney();

        VBox vbox = new VBox();
        BackgroundFill farmBackground = new BackgroundFill(Color.valueOf("#658E6E"),
                new CornerRadii(1), null);
        vbox.setBackground(new Background(farmBackground));
        vbox.setMaxSize(800, 800);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.setPadding(new Insets(50));
        vbox.setSpacing(10);

        Label startingMoneyLabel = new Label("Money: $" + startingMoney + "0");
        startingMoneyLabel.setFont(new Font(16));
        vbox.getChildren().add(startingMoneyLabel);

        Label displayDateLabel = new Label("Day 1");
        displayDateLabel.setFont(new Font(14));
        vbox.getChildren().add(displayDateLabel);

        Plot[] plots = player.getFarm().getPlots();
        HBox plotBox = new HBox();
        plotBox.setSpacing(10);
        plotBox.setAlignment(Pos.CENTER);
        plotBox.setPadding(new Insets(20));
        for (int i = 0; i < plots.length; i++) {
            Label plotLabel = new Label("Plot #" + (i + 1) + "\n"
                    + "Crops: " + plots[i].getNumCrops());
            plotLabel.setAlignment(Pos.CENTER);
            plotLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    plotLabel.setScaleX(1.5);
                    plotLabel.setScaleY(1.5);
                }
            });
            plotLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    plotLabel.setScaleX(1);
                    plotLabel.setScaleY(1);
                }
            });
            plotLabel.setStyle("-fx-border-color: black; -fx-border-radius: 2;"
                    + "-fx-border-width: 2");
            plotLabel.setWrapText(true);
            plotLabel.setScaleX(1);
            plotLabel.setScaleY(1);
            plotLabel.setPadding(new Insets(5));
            plotBox.getChildren().add(plotLabel);
        }
        vbox.getChildren().add(plotBox);

        Scene farm = new Scene(vbox, 800, 700);
        return farm;
    }

    /**
     * Main method.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}