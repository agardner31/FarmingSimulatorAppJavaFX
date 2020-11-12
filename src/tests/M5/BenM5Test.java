package tests.M5;

import model.Fertilizer;
import model.Item;
import model.Player;
import model.Plot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BenM5Test {
    private Player player;
    private Item fertilizer;
    private Plot plot;

    @Before
    public void setup() {
        player = new Player();
        fertilizer = new Fertilizer(player.getDifficulty());
        plot = new Plot();
    }

    @Test
    public void testFertilizerInstantiation() {
        int baseBuyPrice = 3;
        assertEquals(fertilizer.getBaseBuyPrice(), baseBuyPrice);

        String type = "Fertilizer";
        assertEquals(((Fertilizer) fertilizer).getType(), type);

        String basicToString = "Fertilizer";
        assertEquals(fertilizer.toString(), basicToString);

        String buyToString = "Fertilizer\n\n$" + fertilizer.getBuyPrice() + ".00";
        assertEquals(fertilizer.toString("buy"), buyToString);
    }

    @Test
    public void testFertilizerLevel() {
        //check initial fertilizer level
        assertEquals(plot.getFertilizerLevel(), 0);

        //set to 100 for testing purposes
        plot.setFertilizerLevel(100);
        assertEquals(plot.getFertilizerLevel(), 100);

        plot.dry(0);
        assertEquals(plot.getFertilizerLevel(), 80);
        plot.dry(0);
        assertEquals(plot.getFertilizerLevel(), 60);
        plot.dry(0);
        assertEquals(plot.getFertilizerLevel(), 40);
        plot.dry(0);
        assertEquals(plot.getFertilizerLevel(), 20);
        plot.dry(0);
        assertEquals(plot.getFertilizerLevel(), 0);

        //test non-20 value
        plot.setFertilizerLevel(10);
        plot.dry(0);
        assertEquals(plot.getFertilizerLevel(), 0);
    }
}
