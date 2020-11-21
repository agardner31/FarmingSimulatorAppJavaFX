package tests.M6;

import model.Tractor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SebastianM6Tests {
    Tractor tractorAP;
    Tractor tractorOJ;
    Tractor tractorMF;
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

    /*@Test
    public void testTractorUsage() {

    }*/
}
