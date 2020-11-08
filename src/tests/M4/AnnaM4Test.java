package tests.M4;

import model.Crop;
import model.CropStage;
import model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnnaM4Test {
    private Player player;
    private Crop testCrop;
    private Crop testCrop2;
    private Crop testCrop3;

    @Before
    public void setup() {
        player = new Player();
        testCrop = new Crop("Corn", player.getDifficulty());
        testCrop2 = new Crop("Tomato", player.getDifficulty());
    }
//
//    @Test
//    public void cropWaterInitAndIncrementTest() {
//
//        assertEquals(50, testCrop.getWaterLevel());
//
//        testCrop.grow();
//        assertEquals(20, testCrop.getWaterLevel());
//
//        testCrop.grow();
//        assertEquals(0, testCrop.getWaterLevel());
//
//        assertEquals(CropStage.DEAD, testCrop.getStage());
//
//        assertEquals(50, testCrop2.getWaterLevel());
//
//        testCrop2.water();
//        assertEquals(70, testCrop2.getWaterLevel());
//
//        testCrop2.water();
//        assertEquals(90, testCrop2.getWaterLevel());
//
//        testCrop2.water();
//        assertEquals(100, testCrop2.getWaterLevel());
//
//        assertEquals(CropStage.DEAD, testCrop2.getStage());
//    }
}
