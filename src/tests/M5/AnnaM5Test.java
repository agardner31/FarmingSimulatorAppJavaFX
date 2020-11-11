package tests.M5;

import model.Farm;
import model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AnnaM5Test {
    private Farm farm;

    @Before
    public void setup() {
        farm = new Farm("Apprentice");
    }

    @Test
    public void testRandomRainOrDrought() {
        int min = 0;
        int max = 30;
        int farmRand1;
        int farmRand2;
        int farmRand3;
        int farmRand4;
        int farmRand5;
        int farmRand6;
        int farmRand7;
        farm.recalculateRandomRainOrDrought();
        farmRand1 = farm.getRandomRainOrDrought();
        assertTrue(farmRand1 >= min
                && farmRand1 <= max);
        farm.recalculateRandomRainOrDrought();
        farmRand2 = farm.getRandomRainOrDrought();
        assertTrue(farmRand2 >= min
                && farmRand2 <= max);
        farm.recalculateRandomRainOrDrought();
        farmRand3 = farm.getRandomRainOrDrought();
        assertTrue(farmRand3 >= min
                && farmRand3 <= max);
        farm.recalculateRandomRainOrDrought();
        farmRand4 = farm.getRandomRainOrDrought();
        assertTrue(farmRand4 >= min
                && farmRand4 <= max);
        farm.recalculateRandomRainOrDrought();
        farmRand5 = farm.getRandomRainOrDrought();
        assertTrue(farmRand5 >= min
                && farmRand5 <= max);
        farm.recalculateRandomRainOrDrought();
        farmRand6 = farm.getRandomRainOrDrought();
        assertTrue(farmRand6 >= min
                && farmRand6 <= max);
        farm.recalculateRandomRainOrDrought();
        farmRand7 = farm.getRandomRainOrDrought();
        assertTrue(farmRand7 >= min
                && farmRand7 <= max);

        assertTrue(farmRand1 != farmRand2 || farmRand1 != farmRand3
        || farmRand1 != farmRand4 || farmRand1 != farmRand5
        || farmRand1 != farmRand6 || farmRand1 != farmRand6);
    }
}
