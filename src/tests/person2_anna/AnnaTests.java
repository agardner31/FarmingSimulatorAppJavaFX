package tests.person2_anna;

import model.Farm;
import model.Player;

import model.Plot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class AnnaTests {

    private Farm farm;
    private Player player;
    private Plot plot;

    @Before
    public void setUp()  {
        farm = new Farm("Apprentice");
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
        assertEquals(player.getFarm(), farm);
    }
    @Test
    public void testPlotSetTitle() {
        plot.setTitle("HotPlot");
        assertEquals(plot.getTitle(), "HotPlot");
    }

}