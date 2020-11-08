package tests.person3_sebastian;

import model.Farm;
import model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SebastianTests {
    private Player player;
    private Farm farm;

    @Test
    public void testPlayerInitialization() {
        player = new Player();
        assertEquals("Apprentice", player.getDifficulty());
        assertEquals(300, player.getMoney());
        assertEquals(10, player.getFarm().getPlots().length);

        player = new Player("Ordinary Joe", "Corn");
        assertEquals("Ordinary Joe", player.getDifficulty());
        assertEquals(200, player.getMoney());
        assertEquals(10, player.getFarm().getPlots().length);

        player = new Player("Some different difficulty", "Corn");
        assertEquals("Some different difficulty", player.getDifficulty());
        assertEquals(100, player.getMoney());
        assertEquals(10, player.getFarm().getPlots().length);

        farm = new Farm(20, "Apprentice");
        player = new Player("Some different difficulty", farm, "Corn", "Winter");
        assertEquals("Some different difficulty", player.getDifficulty());
        assertEquals(100, player.getMoney());
        assertEquals(20, player.getFarm().getPlots().length);
    }

    @Test
    public void testPlayerAddMoney() {
        player = new Player();
        player.addMoney(0);
        assertEquals(300, player.getMoney());
        player.addMoney(100);
        assertEquals(400, player.getMoney());
        player.addMoney(-100);
        assertEquals(300, player.getMoney());
    }
}
