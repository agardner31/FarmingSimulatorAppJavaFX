package tests.person1_ben;

import model.Farm;
import model.Player;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BenPlayerTest {

    private Player testPlayer;

    @Before
    public void setUp() {
        testPlayer = new Player();
    }

    @Test
    public void testMoneyNoParams() {
        int expectedMoney = 300;
        assertEquals(testPlayer.getMoney(), expectedMoney);
    }

    @Test
    public void testMoneySingleParam() {
        Player appPlayer = new Player("Apprentice", "Corn");
        Player ojPlayer = new Player("Ordinary Joe", "Corn");
        Player mfPlayer = new Player("Master Farmer", "Corn");
        int expectedMoneyApp = 300;
        int expectedMoneyOJ = 200;
        int expectedMoneyMF = 100;
        assertEquals(appPlayer.getMoney(), expectedMoneyApp);
        assertEquals(ojPlayer.getMoney(), expectedMoneyOJ);
        assertEquals(mfPlayer.getMoney(), expectedMoneyMF);
    }

    @Test
    public void testMoneyMultiParam() {
        Player appPlayer = new Player("Apprentice", new Farm("Apprentice"), "Corn", "Spring");
        Player ojPlayer = new Player("Ordinary Joe", new Farm("Ordinary Joe"), "Corn", "Spring");
        Player mfPlayer = new Player("Master Farmer", new Farm("Master Farmer"), "Corn", "Spring");
        int expectedMoneyApp = 300;
        int expectedMoneyOJ = 200;
        int expectedMoneyMF = 100;
        assertEquals(appPlayer.getMoney(), expectedMoneyApp);
        assertEquals(ojPlayer.getMoney(), expectedMoneyOJ);
        assertEquals(mfPlayer.getMoney(), expectedMoneyMF);
    }
}