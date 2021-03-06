package tests.M3;

import model.Crop;
import model.Market;
import model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class M3MarketTests {
    private Player player;
    private Market market;
    private Crop crop;
    private String difficulty1 = "Apprentice";
    private String difficulty2 = "Ordinary Joe";
    private String difficulty3 = "Master Farmer";

    @Before
    public void setUp() {
        player = new Player(difficulty1, "Pumpkin");
        market = new Market(player, player.getDifficulty());
        crop = new Crop("Tomato", player.getDifficulty());
        difficulty1 = "Apprentice";
        difficulty2 = "Ordinary Joe";
        difficulty3 = "Master Farmer";
    }

    @Test
    public void testMarketBuy() {
        market.buy(crop, crop.getBuyPrice());
    }

    @Test
    public void testMarketSell() {

    }

    @Test
    public void testMarketInitialization() {
        market = new Market(player, difficulty1);
        crop = new Crop("Pumpkin", difficulty1);

        assertEquals(crop.getBaseBuyPrice(),
                market.getMarketInventory().getInventoryList().get(0).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(),
                ((Crop) market.getMarketInventory().getInventoryList().get(0)).getBaseSellPrice());

        crop = new Crop("Corn", difficulty1);

        assertEquals(crop.getBaseBuyPrice(),
                market.getMarketInventory().getInventoryList().get(1).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(),
                ((Crop) market.getMarketInventory().getInventoryList().get(1)).getBaseSellPrice());

        crop = new Crop("Tomato", difficulty1);

        assertEquals(crop.getBaseBuyPrice(),
                market.getMarketInventory().getInventoryList().get(2).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(),
                ((Crop) market.getMarketInventory().getInventoryList().get(2)).getBaseSellPrice());


        market = new Market(player, difficulty2);
        crop = new Crop("Pumpkin", difficulty2);

        assertEquals(crop.getBaseBuyPrice(),
                market.getMarketInventory().getInventoryList().get(0).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(),
                ((Crop) market.getMarketInventory().getInventoryList().get(0)).getBaseSellPrice());

        crop = new Crop("Corn", difficulty2);

        assertEquals(crop.getBaseBuyPrice(),
                market.getMarketInventory().getInventoryList().get(1).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(),
                ((Crop) market.getMarketInventory().getInventoryList().get(1)).getBaseSellPrice());

        crop = new Crop("Tomato", difficulty2);

        assertEquals(crop.getBaseBuyPrice(),
                market.getMarketInventory().getInventoryList().get(2).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(),
                ((Crop) market.getMarketInventory().getInventoryList().get(2)).getBaseSellPrice());

        market = new Market(player, difficulty3);
        crop = new Crop("Pumpkin", difficulty3);

        assertEquals(crop.getBaseBuyPrice(),
                market.getMarketInventory().getInventoryList().get(0).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(),
                ((Crop) market.getMarketInventory().getInventoryList().get(0)).getBaseSellPrice());

        crop = new Crop("Corn", difficulty3);

        assertEquals(crop.getBaseBuyPrice(),
                market.getMarketInventory().getInventoryList().get(1).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(),
                ((Crop) market.getMarketInventory().getInventoryList().get(1)).getBaseSellPrice());

        crop = new Crop("Tomato", difficulty3);

        assertEquals(crop.getBaseBuyPrice(),
                market.getMarketInventory().getInventoryList().get(2).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(),
                ((Crop) market.getMarketInventory().getInventoryList().get(2)).getBaseSellPrice());

        /*
        player = new Player(difficulty1, "Pumpkin");
        market = new Market(player, difficulty1);
        crop = new Crop("Pumpkin", difficulty1);
        for (int i = 0; i < 3; i++) {
            assertEquals(crop.getBaseBuyPrice(),
                    market.getPlayerInventory().getInventoryList().get(i).getBaseBuyPrice());
            assertEquals(crop.getBaseSellPrice(),
                    ((Crop) market.getMarketInventory().getInventoryList().
                    get(i)).getBaseSellPrice());
        }

        player = new Player(difficulty2, "Pumpkin");
        market = new Market(player, difficulty2);
        crop = new Crop("Pumpkin", difficulty2);
        for (int i = 0; i < 3; i++) {
            assertEquals(crop.getBaseBuyPrice(),
                    market.getPlayerInventory().getInventoryList().get(i).getBaseBuyPrice());
            assertEquals(crop.getBaseSellPrice(),
                    ((Crop) market.getMarketInventory().getInventoryList().
                    get(i)).getBaseSellPrice());
        }

        player = new Player(difficulty3, "Pumpkin");
        market = new Market(player, difficulty3);
        crop = new Crop("Pumpkin", difficulty3);
        for (int i = 0; i < 3; i++) {
            assertEquals(crop.getBaseBuyPrice(),
                    market.getPlayerInventory().getInventoryList().get(i).getBaseBuyPrice());
            assertEquals(crop.getBaseSellPrice(),
                    ((Crop) market.getMarketInventory().getInventoryList().
                    get(i)).getBaseSellPrice());
        }

         //deprecated// */
    }
}