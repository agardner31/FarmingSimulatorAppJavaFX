package tests.M5;

import model.FarmWorker;
import model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmilyM5Test {
    private Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testPlayerInitialization() {
        FarmWorker[] farmWorkers = player.getFarmWorkers();
        assertEquals(3, farmWorkers.length);
        assertNull(farmWorkers[0]);
        assertNull(farmWorkers[1]);
        assertNull(farmWorkers[2]);
    }

    @Test
    public void testHireWorkers() {
        FarmWorker[] playerWorkers;
        //= player.getFarmWorkers();
        //assertNull(playerWorkers);

        FarmWorker farmWorker = new FarmWorker(1);
        player.hireWorker(farmWorker);
        playerWorkers = player.getFarmWorkers();

        assertNotNull(playerWorkers);
        assertEquals(farmWorker.getName(), playerWorkers[0].getName());
    }

    @Test
    public void testFarmWorkerInitialization() {
        FarmWorker farmWorker = new FarmWorker(1);
        assertEquals(3, farmWorker.getWage());
        assertEquals(1, farmWorker.getSkill());
    }
}
