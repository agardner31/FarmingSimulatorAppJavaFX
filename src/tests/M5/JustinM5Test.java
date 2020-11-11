package tests.M5;

import model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JustinM5Test {
    private Player player;
    private Plot testPlot;

    @Before
    public void setup() {
        player = new Player();
        testPlot = new Plot();
    }

    @Test
    public void testWaterAndDry() {
        int water = testPlot.getWaterLevel();
        int dryAmount = 30;
        testPlot.dry(dryAmount);
        assertEquals(testPlot.getWaterLevel(), water - dryAmount);
        assertEquals(dryAmount, water - testPlot.getWaterLevel());
    }

}
