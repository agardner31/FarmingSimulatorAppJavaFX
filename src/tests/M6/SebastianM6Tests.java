package tests.M6;

import model.Farm;
import model.Tractor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SebastianM6Tests {
    private Tractor tractorAP;
    private Tractor tractorOJ;
    private Tractor tractorMF;
    @Before
    public void setup() {
        tractorAP = new Tractor("Apprentice");
        tractorOJ = new Tractor("Ordinary Joe");
        tractorMF = new Tractor("Master Farmer");
    }

    @Test
    public void testTractorInstantiation() {
        assertEquals(tractorAP.getMultiplier(), 2);
        assertEquals(tractorOJ.getMultiplier(), 2);
        assertEquals(tractorMF.getMultiplier(), 2);

        assertEquals(tractorAP.getBaseBuyPrice(), 50);
        assertEquals(tractorOJ.getBaseBuyPrice(), 50);
        assertEquals(tractorMF.getBaseBuyPrice(), 50);

        String apString = "Tractor\n\n$"
                + tractorAP.getBuyPrice() + ".00";
        String ojString = "Tractor\n\n$"
                + tractorOJ.getBuyPrice() + ".00";
        String mfString = "Tractor\n\n$"
                + tractorMF.getBuyPrice() + ".00";

        assertEquals(tractorAP.toString(), apString);
        assertEquals(tractorOJ.toString(), ojString);
        assertEquals(tractorMF.toString(), mfString);
    }

    @Test
    public void testTractorUsage() {
        Farm farmAP = new Farm("Apprentice");
        Farm farmOJ = new Farm("Ordinary Joe");
        Farm farmMF = new Farm("Master Farmer");
        int limitIncreaseAP = tractorAP.getMultiplier();
        int limitIncreaseOJ = tractorOJ.getMultiplier();
        int limitIncreaseMF = tractorMF.getMultiplier();
        int i;

        //apprentice
        for (i = 0; i < farmAP.getDailyHarvestLimit(); i++) {
            assertTrue(farmAP.harvestCountCheck());
            farmAP.incrementDailyHarvestCount();
        }

        assertFalse(farmAP.harvestCountCheck());

        int oldDailyHarvestLimit = farmAP.getDailyHarvestLimit();
        farmAP.setDailyHarvestLimit(limitIncreaseAP);
        for (; i < oldDailyHarvestLimit + limitIncreaseAP; i++) {
            assertTrue(farmAP.harvestCountCheck());
            farmAP.incrementDailyHarvestCount();
        }

        assertFalse(farmAP.harvestCountCheck());


        //ordinary joe
        for (i = 0; i < farmOJ.getDailyHarvestLimit(); i++) {
            assertTrue(farmOJ.harvestCountCheck());
            farmOJ.incrementDailyHarvestCount();
        }

        assertFalse(farmOJ.harvestCountCheck());

        oldDailyHarvestLimit = farmOJ.getDailyHarvestLimit();

        farmOJ.setDailyHarvestLimit(limitIncreaseOJ);
        for (; i < oldDailyHarvestLimit + limitIncreaseOJ; i++) {
            assertTrue(farmOJ.harvestCountCheck());
            farmOJ.incrementDailyHarvestCount();
        }

        assertFalse(farmOJ.harvestCountCheck());


        //master farmer
        for (i = 0; i < farmMF.getDailyHarvestLimit(); i++) {
            assertTrue(farmMF.harvestCountCheck());
            farmMF.incrementDailyHarvestCount();
        }

        assertFalse(farmMF.harvestCountCheck());

        oldDailyHarvestLimit = farmMF.getDailyHarvestLimit();
        farmMF.setDailyHarvestLimit(limitIncreaseMF);
        for (; i < oldDailyHarvestLimit + limitIncreaseMF; i++) {
            assertTrue(farmMF.harvestCountCheck());
            farmMF.incrementDailyHarvestCount();
        }

        assertFalse(farmMF.harvestCountCheck());
    }
}
