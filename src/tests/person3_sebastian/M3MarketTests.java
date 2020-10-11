package tests.person3_sebastian;

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
    private String difficulty1;
    private String difficulty2;
    private String difficulty3;
    private int playerMoney;

    @Before
    public void setUp() {
        difficulty1 = "Apprentice";
        difficulty2 = "Ordinary Joe";
        difficulty3 = "Master Farmer";
        player = new Player(difficulty1, "Pumpkin");
        market = new Market(player, player.getDifficulty());
        crop = new Crop("Tomato", player.getDifficulty());
        playerMoney = player.getMoney();
    }

    @Test
    public void testMarketBuy() {
        market.buy(crop, crop.getBuyPrice());
        assertEquals(playerMoney - crop.getBuyPrice(), player.getMoney());
        playerMoney = player.getMoney();
        market.buy(null, 0);
        assertEquals(playerMoney, player.getMoney()); //make sure nothing happens
    }

    @Test
    public void testMarketSell() {
        playerMoney = player.getMoney();
        crop = player.getInventory().getInventoryList().get(2);
        market.sell(crop);
        assertEquals(playerMoney + crop.getSellPrice(), player.getMoney());
        market.sell(null);
        playerMoney = player.getMoney();
        market.sell(null);
        assertEquals(playerMoney, player.getMoney()); //make sure nothing happens
    }

    @Test
    public void testMarketInitialization() {
        market = new Market(player, difficulty1);
        crop = new Crop("Pumpkin", difficulty1);

        assertEquals(crop.getBaseBuyPrice(), market.getMarketInventory().getInventoryList().get(0).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(), market.getMarketInventory().getInventoryList().get(0).getBaseSellPrice());

        crop = new Crop("Corn", difficulty1);

        assertEquals(crop.getBaseBuyPrice(), market.getMarketInventory().getInventoryList().get(1).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(), market.getMarketInventory().getInventoryList().get(1).getBaseSellPrice());

        crop = new Crop("Tomato", difficulty1);

        assertEquals(crop.getBaseBuyPrice(), market.getMarketInventory().getInventoryList().get(2).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(), market.getMarketInventory().getInventoryList().get(2).getBaseSellPrice());


        market = new Market(player, difficulty2);
        crop = new Crop("Pumpkin", difficulty2);

        assertEquals(crop.getBaseBuyPrice(), market.getMarketInventory().getInventoryList().get(0).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(), market.getMarketInventory().getInventoryList().get(0).getBaseSellPrice());

        crop = new Crop("Corn", difficulty2);

        assertEquals(crop.getBaseBuyPrice(), market.getMarketInventory().getInventoryList().get(1).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(), market.getMarketInventory().getInventoryList().get(1).getBaseSellPrice());

        crop = new Crop("Tomato", difficulty2);

        assertEquals(crop.getBaseBuyPrice(), market.getMarketInventory().getInventoryList().get(2).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(), market.getMarketInventory().getInventoryList().get(2).getBaseSellPrice());

        market = new Market(player, difficulty3);
        crop = new Crop("Pumpkin", difficulty3);

        assertEquals(crop.getBaseBuyPrice(), market.getMarketInventory().getInventoryList().get(0).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(), market.getMarketInventory().getInventoryList().get(0).getBaseSellPrice());

        crop = new Crop("Corn", difficulty3);

        assertEquals(crop.getBaseBuyPrice(), market.getMarketInventory().getInventoryList().get(1).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(), market.getMarketInventory().getInventoryList().get(1).getBaseSellPrice());

        crop = new Crop("Tomato", difficulty3);

        assertEquals(crop.getBaseBuyPrice(), market.getMarketInventory().getInventoryList().get(2).getBaseBuyPrice());
        assertEquals(crop.getBaseSellPrice(), market.getMarketInventory().getInventoryList().get(2).getBaseSellPrice());


        player = new Player(difficulty1, "Pumpkin");
        market = new Market(player, difficulty1);
        crop = new Crop("Pumpkin", difficulty1);
        for (int i = 0; i < 3; i++) {
            assertEquals(crop.getBaseBuyPrice(), market.getPlayerInventory().getInventoryList().get(i).getBaseBuyPrice());
            assertEquals(crop.getBaseSellPrice(), market.getPlayerInventory().getInventoryList().get(i).getBaseSellPrice());
        }

        player = new Player(difficulty2, "Pumpkin");
        market = new Market(player, difficulty2);
        crop = new Crop("Pumpkin", difficulty2);
        for (int i = 0; i < 3; i++) {
            assertEquals(crop.getBaseBuyPrice(), market.getPlayerInventory().getInventoryList().get(i).getBaseBuyPrice());
            assertEquals(crop.getBaseSellPrice(), market.getPlayerInventory().getInventoryList().get(i).getBaseSellPrice());
        }

        player = new Player(difficulty3, "Pumpkin");
        market = new Market(player, difficulty3);
        crop = new Crop("Pumpkin", difficulty3);
        for (int i = 0; i < 3; i++) {
            assertEquals(crop.getBaseBuyPrice(), market.getPlayerInventory().getInventoryList().get(i).getBaseBuyPrice());
            assertEquals(crop.getBaseSellPrice(), market.getPlayerInventory().getInventoryList().get(i).getBaseSellPrice());
        }
    }
}