package tests.person2_anna;

import main.Farm;
import main.Plot;
import main.Player;
import main.Farm

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class AnnaTests {

    private Farm farm;
    private Player player;
    private Plot plot;

    @Before
    public void setUp()  {
        farm = new Farm();
        player = new Player();
        plot = new Plot();
    }
    @Test
    public void testPlayerSetDifficulty() {
        player.setDifficulty("Apprentice");
        assertEquals(player.getDifficulty(), "Apprentice");
    }
    @Test
    public void testPlayerSetFarm() {
        player.setFarm(farm);
        assertEquals(plot.getFarm(), farm);
    }
    @Test
    public void testPlotSetTitle() {
        plot.setTitle("HotPlot");
        assertEquals(plot.getTitle(), "HotPlot");
    }

}