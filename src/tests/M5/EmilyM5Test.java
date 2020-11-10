package tests.M5;

import model.FarmWorker;
import model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmilyM5Test {
    private Player player;
    private FarmWorker farmWorker;

    @Before
    public void setup() {
        player = new Player();
        farmWorker = new FarmWorker(1);
    }

    @Test
    public void testFarmWorkerInstantiation() {
        //test toString method
        assertEquals(farmWorker.toString(), farmWorker.getName() + "\n" + farmWorker.getSkill() + "\nDaily Wage:" + farmWorker.getWage());
        //test getSkill
        assertEquals(1, farmWorker.getSkill());
        //test getWage
        assertEquals(5, farmWorker.getWage());
    }

    @Test
    public void testNewPlayerMethods() {
        FarmWorker farmWorker1 = new FarmWorker(1);
        FarmWorker farmWorker2 = new FarmWorker(2);
        FarmWorker farmWorker3 = new FarmWorker(3);
        FarmWorker[] farmWorkers = {farmWorker1, farmWorker2, farmWorker3};
        player.hireWorker(farmWorker1);
        player.hireWorker(farmWorker2);
        player.hireWorker(farmWorker3);

        //test hireFarmWorker and getFarmWorker
        assertEquals(farmWorkers, player.getFarmWorkers());

        //test getFarmWorkerEfficiency
        assertEquals(6, player.getFarmWorkerEfficiency());
    }
}
