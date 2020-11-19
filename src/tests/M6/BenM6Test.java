package tests.M6;

import model.FarmMachine;
import model.Irrigation;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BenM6Test {
    private FarmMachine irrigationAP;
    private FarmMachine irrigationOJ;
    private FarmMachine irrigationMF;

    @Before
    public void setup() {
        irrigationAP = new Irrigation("Apprentice");
        irrigationOJ = new Irrigation("Ordinary Joe");
        irrigationMF = new Irrigation("Master Farmer");
    };

    @Test
    public void testIrrigationInstantiation() {
        assertEquals(irrigationAP.getMultiplier(), 2);
        assertEquals(irrigationOJ.getMultiplier(), 2);
        assertEquals(irrigationMF.getMultiplier(), 2);

        assertEquals(irrigationAP.getBaseBuyPrice(), 25);
        assertEquals(irrigationOJ.getBaseBuyPrice(), 25);
        assertEquals(irrigationMF.getBaseBuyPrice(), 25);

        String apString = "Irrigation\n\n$"
                + irrigationAP.getBuyPrice() + ".00";
        String ojString = "Irrigation\n\n$"
                + irrigationOJ.getBuyPrice() + ".00";
        String mfString = "Irrigation\n\n$"
                + irrigationMF.getBuyPrice() + ".00";

        assertEquals(irrigationAP.toString(), apString);
        assertEquals(irrigationOJ.toString(), ojString);
        assertEquals(irrigationMF.toString(), mfString);
    }
}
