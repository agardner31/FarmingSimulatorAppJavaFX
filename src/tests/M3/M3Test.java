package tests.M3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/*import org.junit.Before;*/

public class M3Test {

    private Player testPlayerApprenticePumpkin;
    private Player testPlayerApprenticeCorn;
    private Player testPlayerApprenticeTomato;
    private Player testPlayerOrdinaryPumpkin;
    private Player testPlayerOrdinaryCorn;
    private Player testPlayerOrdinaryTomato;
    private Player testPlayerMasterPumpkin;
    private Player testPlayerMasterCorn;
    private Player testPlayerMasterTomato;
    private Market testMarket;
    private Player player;
    private String difficulty;


    @Before
    public void setUp() {
        testPlayerApprenticePumpkin = new Player("Apprentice", "Pumpkin");
        testPlayerApprenticeCorn = new Player("Apprentice", "Corn");
        testPlayerApprenticeTomato = new Player("Apprentice", "Tomato");
        testPlayerOrdinaryPumpkin = new Player("Ordinary Joe", "Pumpkin");
        testPlayerOrdinaryCorn = new Player("Ordinary Joe", "Corn");
        testPlayerOrdinaryTomato = new Player("Ordinary Joe", "Tomato");
        testPlayerMasterPumpkin = new Player("Master Farmer", "Pumpkin");
        testPlayerMasterCorn = new Player("Master Farmer", "Corn");
        testPlayerMasterTomato = new Player("Master Farmer", "Tomato");
    }

    @Test
    public void justinCropBuySellPrice() {
        Crop crop = new Crop("Pumpkin", "Apprentice", CropStage.SEED);
        Crop crop2 = new Crop("Tomato", "Apprentice", CropStage.SEED);
        Crop crop3 = new Crop("Pumpkin", "Ordinary Joe", CropStage.SEED);
        Crop crop4 = new Crop("Corn", "Ordinary Joe", CropStage.SEED);

        assertNotSame(crop.getBuyPrice(), crop.getSellPrice());
        assertNotSame(crop2.getBuyPrice(), crop.getSellPrice());
        assertNotSame(crop3.getBuyPrice(), crop.getSellPrice());
        assertNotSame(crop4.getBuyPrice(), crop.getSellPrice());
    }

    @Test
    public void benTestCropArrayCreation() {
        ObservableList<Crop> testArray1 =
                testPlayerApprenticeCorn.getInventory().getInventoryList();
        ObservableList<Crop> testArray2 =
                testPlayerOrdinaryPumpkin.getInventory().getInventoryList();
        ObservableList<Crop> testArray3 =
                testPlayerMasterTomato.getInventory().getInventoryList();

        ObservableList<Crop> expected1 = FXCollections.observableArrayList();
        expected1.add(new Crop("Corn", "Apprentice", CropStage.SEED));
        expected1.add(new Crop("Corn", "Apprentice", CropStage.SEED));
        expected1.add(new Crop("Corn", "Apprentice", CropStage.SEED));

        ObservableList<Crop> expected2 = FXCollections.observableArrayList();
        expected2.add(new Crop("Pumpkin", "Ordinary Joe", CropStage.SEED));
        expected2.add(new Crop("Pumpkin", "Ordinary Joe", CropStage.SEED));
        expected2.add(new Crop("Pumpkin", "Ordinary Joe", CropStage.SEED));

        ObservableList<Crop> expected3 = FXCollections.observableArrayList();
        expected3.add(new Crop("Tomato", "Master Farmer", CropStage.SEED));
        expected3.add(new Crop("Tomato", "Master Farmer", CropStage.SEED));
        expected3.add(new Crop("Tomato", "Master Farmer", CropStage.SEED));

        assertEquals(expected1, testArray1);
        assertEquals(expected2, testArray2);
        assertEquals(expected3, testArray3);
    }

    @Test
    public void AnnaTestCropIncrementation() {
        Crop dirtCrop = new Crop("Pumpkin", "Apprentice", CropStage.DIRT);
        Crop seedCrop = new Crop("Pumpkin", "Apprentice", CropStage.SEED);
        Crop immatureCrop = new Crop("Pumpkin", "Apprentice", CropStage.IMMATURE);
        Crop matureCrop = new Crop("Pumpkin", "Apprentice", CropStage.MATURE);
        Crop harvestedCrop = new Crop("Pumpkin", "Apprentice", CropStage.HARVESTED);
        dirtCrop.incrementStage();
        seedCrop.incrementStage();
        immatureCrop.incrementStage();
        matureCrop.incrementStage();
        harvestedCrop.incrementStage();
        assertEquals(dirtCrop.getStage(), CropStage.SEED);
        assertEquals(seedCrop.getStage(), CropStage.IMMATURE);
        assertEquals(immatureCrop.getStage(), CropStage.MATURE);
        assertEquals(matureCrop.getStage(), CropStage.HARVESTED);
        assertEquals(harvestedCrop.getStage(), CropStage.DIRT);
    }
}
