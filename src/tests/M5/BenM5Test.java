package tests.M5;

import model.Fertilizer;
import model.Item;
import model.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BenM5Test {
    private Player player;
    private Item fertilizer;

    @Before
    public void setup() {
        player = new Player();
        fertilizer = new Fertilizer(player.getDifficulty());
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
        assertEquals(((Fertilizer) fertilizer).toString("buy"), buyToString);
    }

}
