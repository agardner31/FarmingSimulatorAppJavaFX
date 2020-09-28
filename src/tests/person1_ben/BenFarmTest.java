package tests.person1_ben;

import main.Farm;
import main.Plot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BenFarmTest {

    private Farm farm;

    @Before
    public void setUp()  {
        farm = new Farm();
    }

    @Test
    public void testPlotNoParams() {
        Plot[] plots = new Plot[10];
        plots[0] = new Plot(0, "None", 1);
        plots[1] = new Plot(0, "None", 2);
        plots[2] = new Plot(0, "None", 3);
        plots[3] = new Plot(0, "None", 4);
        plots[4] = new Plot(0, "None", 5);
        plots[5] = new Plot(0, "None", 6);
        plots[6] = new Plot(0, "None", 7);
        plots[7] = new Plot(0, "None", 8);
        plots[8] = new Plot(0, "None", 9);
        plots[9] = new Plot(0, "None", 10);
        for (int i = 0; i < 10; i++) {
            assertEquals(plots[i].getNumCrops(), farm.getPlots()[i].getNumCrops());
            assertEquals(plots[i].getType(), farm.getPlots()[i].getType());
            assertEquals(plots[i].getTitle(), farm.getPlots()[i].getTitle());
        }
    }

    @Test
    public void testPlotSizeParam() {
        int size = 6;
        farm = new Farm(6);
        Plot[] plots = new Plot[size];
        plots[0] = new Plot(0, "None", 1);
        plots[1] = new Plot(0, "None", 2);
        plots[2] = new Plot(0, "None", 3);
        plots[3] = new Plot(0, "None", 4);
        plots[4] = new Plot(0, "None", 5);
        plots[5] = new Plot(0, "None", 6);
        for (int i = 0; i < size; i++) {
            assertEquals(plots[i].getNumCrops(), farm.getPlots()[i].getNumCrops());
            assertEquals(plots[i].getType(), farm.getPlots()[i].getType());
            assertEquals(plots[i].getTitle(), farm.getPlots()[i].getTitle());
        }
    }
}