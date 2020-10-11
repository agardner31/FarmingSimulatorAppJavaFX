package tests.person5_justin;

import model.Farm;
import model.Plot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JustinTests {

    private Farm farm;
    private Plot plot;

    @Before
    public void setUp()  {
        farm = new Farm("Master Farmer");
    }

    @Test
    public void testPlotArrayInitialization() {
        Plot[] plots = new Plot[10];
        for (int i = 0; i < 10; i++) {
            plots[i] = new Plot();
        }
        for (int i = 0; i < 10; i++) {
//            assertEquals(plots[i].getNumCrops(), 0);
            assertEquals(plots[i].getType(), "None");
            assertEquals(plots[i].getTitle(), "");
        }
    }

    @Test
    public void testFarmInitialization() {
        int size = 4;
        farm = new Farm(4, "Master Farmer");
        Plot[] plots = new Plot[size];
        for (int i = 0; i < size; i++) {
            plots[i] = new Plot();
        }
        for (int i = 0; i < size; i++) {
//            assertEquals(farm.getPlots()[i].getNumCrops(), 0);
            assertEquals(farm.getPlots()[i].getType(), "None");
            assertEquals(farm.getPlots()[i].getTitle(), "Plot 1");
//            assertEquals(plots[i].getNumCrops(), 0);
            assertEquals(plots[i].getType(), "None");
            assertEquals(plots[i].getTitle(), "");
//            assertEquals(farm.getPlots()[i].getNumCrops(), plots[i].getNumCrops());
            assertEquals(farm.getPlots()[i].getType(), plots[i].getType());
            assertEquals(farm.getPlots()[i].getTitle(), plots[i].getTitle());
        }
    }
}
