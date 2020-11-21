package tests.M6;
import model.Farm;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JustinM6Test {
    private Farm farm;

    @Before
    public void setup() {
        farm = new Farm(10, "Apprentice");
    }

    @Test
    public void testPlotAdding() {
        assertEquals(farm.getNumPlots(), 10);
        farm.addPlot();
        assertEquals(farm.getNumPlots(), 11);
        farm.addPlot();
        assertEquals(farm.getNumPlots(), 12);
    }
}
