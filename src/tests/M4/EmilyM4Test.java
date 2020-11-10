package tests.M4;

import model.Crop;
import model.CropStage;
import model.Plot;
import org.junit.Test;

import static org.junit.Assert.fail;

public class EmilyM4Test {

    private Crop testCrop;
    private Plot testPlot;

    @Test
    public void testPumpkinImmaturePlotImage() {
        try {
            testCrop = new Crop("Pumpkin", "Apprentice", CropStage.IMMATURE);
            testPlot = new Plot(testCrop, 0);
        } catch (Exception FileNotFoundException) {
            fail();
        }
    }

    @Test
    public void testPumpkinMaturePlotImage() {
        try {
            testCrop = new Crop("Pumpkin", "Apprentice", CropStage.MATURE);
            testPlot = new Plot(testCrop, 0);
        } catch (Exception FileNotFoundException) {
            fail();
        }
    }

    @Test
    public void testPumpkinSeedsPlotImage() {
        try {
            testCrop = new Crop("Pumpkin", "Apprentice", CropStage.SEED);
            testPlot = new Plot(testCrop, 0);
        } catch (Exception FileNotFoundException) {
            fail();
        }
    }


    @Test
    public void testPumpkinDeadPlotImage() {
        try {
            testCrop = new Crop("Pumpkin", "Apprentice", CropStage.DEAD);
            testPlot = new Plot(testCrop, 0);
        } catch (Exception FileNotFoundException) {
            fail();
        }
    }
}
