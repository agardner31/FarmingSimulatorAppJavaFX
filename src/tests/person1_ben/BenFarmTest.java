package tests.person1_ben;

import model.Farm;

import org.junit.Before;


public class BenFarmTest {

    private Farm farm;

    @Before
    public void setUp()  {
        farm = new Farm("Apprentice");
    }

    /*@Test
    public void testPlotNoParams() {
        Plot[] plots = new Plot[10];
        plots[0] = new Plot(0, "None", 1, "Apprentice");
        plots[1] = new Plot(0, "None", 2, "Apprentice");
        plots[2] = new Plot(0, "None", 3, "Apprentice");
        plots[3] = new Plot(0, "None", 4, "Apprentice");
        plots[4] = new Plot(0, "None", 5, "Apprentice");
        plots[5] = new Plot(0, "None", 6, "Apprentice");
        plots[6] = new Plot(0, "None", 7, "Apprentice");
        plots[7] = new Plot(0, "None", 8, "Apprentice");
        plots[8] = new Plot(0, "None", 9, "Apprentice");
        plots[9] = new Plot(0, "None", 10, "Apprentice");
        for (int i = 0; i < 10; i++) {
            assertEquals(plots[i].getNumCrops(), farm.getPlots()[i].getNumCrops());
            assertEquals(plots[i].getType(), farm.getPlots()[i].getType());
            assertEquals(plots[i].getTitle(), farm.getPlots()[i].getTitle());
        }
    }

    @Test
    public void testPlotSizeParam() {
        int size = 6;
        farm = new Farm(6, "Apprentice");
        Plot[] plots = new Plot[size];
        plots[0] = new Plot(0, "None", 1, "Apprentice");
        plots[1] = new Plot(0, "None", 2, "Apprentice");
        plots[2] = new Plot(0, "None", 3, "Apprentice");
        plots[3] = new Plot(0, "None", 4, "Apprentice");
        plots[4] = new Plot(0, "None", 5, "Apprentice");
        plots[5] = new Plot(0, "None", 6, "Apprentice");
        for (int i = 0; i < size; i++) {
            assertEquals(plots[i].getNumCrops(), farm.getPlots()[i].getNumCrops());
            assertEquals(plots[i].getType(), farm.getPlots()[i].getType());
            assertEquals(plots[i].getTitle(), farm.getPlots()[i].getTitle());
        }
    }*/
}