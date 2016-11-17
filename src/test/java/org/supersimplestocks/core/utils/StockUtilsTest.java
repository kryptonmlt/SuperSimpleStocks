package org.supersimplestocks.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import org.joda.time.DateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.supersimplestocks.core.enums.Action;
import org.supersimplestocks.core.enums.StockType;
import org.supersimplestocks.core.pojos.SimpleStock;
import org.supersimplestocks.core.pojos.Trade;

/**
 *
 * @author kurt
 */
public class StockUtilsTest {

    public StockUtilsTest() {
    }

    /**
     * Test of dividendYield method, of class StockUtils.
     */
    @Test
    public void testDividendYield_Common() {
        System.out.println("dividendYield");
        SimpleStock stock = new SimpleStock();
        stock.setLastDividend(10);
        stock.setMarketPrice(5);
        stock.setType(StockType.COMMON);
        float expResult = 2f;
        float result = StockUtils.dividendYield(stock);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of dividendYield method, of class StockUtils.
     */
    @Test
    public void testDividendYield_Preferred() {
        System.out.println("dividendYield");
        SimpleStock stock = new SimpleStock();
        stock.setFixedDividend("10%");
        stock.setMarketPrice(5);
        stock.setParValue(200);
        stock.setType(StockType.PREFERRED);
        float expResult = 4f;
        float result = StockUtils.dividendYield(stock);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of peRatio method, of class StockUtils.
     */
    @Test
    public void testPeRatio() {
        System.out.println("peRatio");
        SimpleStock stock = new SimpleStock();
        stock.setLastDividend(1);
        stock.setMarketPrice(2);
        stock.setType(StockType.COMMON);
        float expResult = 4f;
        float result = StockUtils.peRatio(stock);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of volumeWeightedStockPrice method, of class StockUtils.
     */
    @Test
    public void testVolumeWeightedStockPrice() {
        System.out.println("volumeWeightedStockPrice");
        SimpleStock stock = stockGenerator();
        float expResult = 2f;
        float result = StockUtils.volumeWeightedStockPrice(stock);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of geometricMean method, of class StockUtils.
     */
    @Test
    public void testGeometricMean() {
        System.out.println("geometricMean");
        Collection<SimpleStock> stocks = new ArrayList<>();
        SimpleStock stock = stockGenerator();
        stocks.add(stock);
        stocks.add(stock);
        float expResult = 2F;
        float result = StockUtils.geometricMean(stocks);
        assertEquals(expResult, result, 0.0);
    }

    //can be randomized..
    private SimpleStock stockGenerator() {
        SimpleStock stock = new SimpleStock();
        stock.getTrades().add(new Trade(DateTime.now(), 1, Action.BUY, 2));
        stock.getTrades().add(new Trade(DateTime.now().minusMinutes(10), 3, Action.SELL, 2));
        stock.getTrades().add(new Trade(DateTime.now().minusMinutes(20), 100, Action.SELL, 2));
        return stock;
    }

}
