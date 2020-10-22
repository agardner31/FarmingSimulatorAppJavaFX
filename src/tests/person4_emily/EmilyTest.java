package tests.person4_emily;

import model.Crop;
import model.Plot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EmilyTest {

    private Plot plot;

    @Before
    public void setUp()  {
        plot = new Plot();
    }

    @Test
    public void testPlotInitialization() {
        assertEquals(plot.getCrop().getType(), "None");
        assertEquals(plot.getTitle(), "");
    }

    /*@Test
    public void testNumCrops() {
        plot.setNumCrops(5);
        assertEquals(plot.getNumCrops(), 5);
    }*/

    @Test
    public void testType() {
        plot.setCrop(new Crop("Tomato", "Apprentice"));
        assertEquals(plot.getCrop().getType(), "Tomato");
    }
}