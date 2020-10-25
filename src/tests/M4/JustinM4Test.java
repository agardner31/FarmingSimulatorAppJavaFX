package tests.M4;
import model.Crop;
import model.CropStage;
import model.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JustinM4Test {
    private Player player;
    private Crop testCrop;
    private Crop testCrop2;
    private Crop testCrop3;

    @Before
    public void setup() {
        player = new Player();
        testCrop = new Crop("Corn", player.getDifficulty());
        testCrop2 = new Crop("Tomato", player.getDifficulty());
        testCrop3 = new Crop("Pumpkin", player.getDifficulty());
    }

    @Test
    public void cropsIncrementTest() {
        //test corn

        assertEquals(testCrop.getStage(), CropStage.SEED);

        testCrop.grow();
        assertEquals(testCrop.getStage(), CropStage.IMMATURE);

        testCrop.grow();
        assertEquals(testCrop.getStage(), CropStage.DEAD);

        //test tomato

        assertEquals(testCrop2.getStage(), CropStage.SEED);

        testCrop2.grow();
        assertEquals(testCrop2.getStage(), CropStage.IMMATURE);

        testCrop2.grow();
        assertEquals(testCrop2.getStage(), CropStage.DEAD);

        //test pumpkin

        assertEquals(testCrop3.getStage(), CropStage.SEED);

        testCrop3.grow();
        assertEquals(testCrop3.getStage(), CropStage.IMMATURE);

        testCrop3.grow();
        assertEquals(testCrop3.getStage(), CropStage.DEAD);
    }

    @Test
    public void cropsIncrementTestWater() {
        //test corn
        assertEquals(testCrop.getStage(), CropStage.SEED);

        testCrop.water();
        testCrop.grow();
        assertEquals(testCrop.getStage(), CropStage.IMMATURE);

        testCrop.water();
        testCrop.grow();
        assertEquals(testCrop.getStage(), CropStage.MATURE);

        testCrop.water();
        testCrop.grow();
        assertEquals(testCrop.getStage(), CropStage.DEAD);

        //test tomato
        assertEquals(testCrop2.getStage(), CropStage.SEED);

        testCrop2.water();
        testCrop2.grow();
        assertEquals(testCrop2.getStage(), CropStage.IMMATURE);

        testCrop2.water();
        testCrop2.grow();
        assertEquals(testCrop2.getStage(), CropStage.MATURE);

        testCrop2.water();
        testCrop2.grow();
        assertEquals(testCrop2.getStage(), CropStage.DEAD);

        //test pumpkin
        assertEquals(testCrop3.getStage(), CropStage.SEED);

        testCrop3.water();
        testCrop3.grow();
        assertEquals(testCrop3.getStage(), CropStage.IMMATURE);

        testCrop3.water();
        testCrop3.grow();
        assertEquals(testCrop3.getStage(), CropStage.MATURE);

        testCrop3.water();
        testCrop3.grow();
        assertEquals(testCrop3.getStage(), CropStage.DEAD);
    }
}
