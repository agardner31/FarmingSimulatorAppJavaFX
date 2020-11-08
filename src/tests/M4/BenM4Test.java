package tests.M4;
import model.Crop;
import model.CropStage;
import model.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BenM4Test {
    private Player player;
    private Crop testCrop;

    @Before
    public void setup() {
        player = new Player();
        testCrop = new Crop("Corn", player.getDifficulty());
    }

    @Test
    public void dayIncrementTestNoWater() {
        assertEquals(player.getDay(), 1);
        assertEquals(testCrop.getStage(), CropStage.SEED);

        hitNextDayButton();

        assertEquals(player.getDay(), 2);
        assertEquals(testCrop.getStage(), CropStage.IMMATURE);

        hitNextDayButton();

        assertEquals(player.getDay(), 3);
        assertEquals(testCrop.getStage(), CropStage.DEAD);
    }

    @Test
//    public void dayIncrementTestWater() {
//        assertEquals(player.getDay(), 1);
//        assertEquals(testCrop.getStage(), CropStage.SEED);
//
//        testCrop.water();
//        hitNextDayButton();
//
//        assertEquals(player.getDay(), 2);
//        assertEquals(testCrop.getStage(), CropStage.IMMATURE);
//
//        testCrop.water();
//        hitNextDayButton();
//
//        assertEquals(player.getDay(), 3);
//        assertEquals(testCrop.getStage(), CropStage.MATURE);
//
//        testCrop.water();
//        hitNextDayButton();
//
//        assertEquals(player.getDay(), 4);
//        assertEquals(testCrop.getStage(), CropStage.DEAD);
//    }

    private void hitNextDayButton() {
        player.incrementDay();
        testCrop.grow();
    }
}