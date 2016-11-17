package org.supersimplestocks.core.utils;

import java.util.Collection;
import java.util.Iterator;
import org.supersimplestocks.core.enums.StockType;
import org.supersimplestocks.core.pojos.SimpleStock;
import org.supersimplestocks.core.pojos.Trade;

/**
 *
 * @author kurt
 */
public class StockUtils {

    private StockUtils() {

    }

    /**
     * Calculates the dividend yield
     *
     * @param stock
     * @return
     */
    public static float dividendYield(SimpleStock stock) {
        if (stock.getType() == StockType.COMMON) {
            return stock.getLastDividend() / stock.getMarketPrice();
        }
        return (stock.getFixedDividend() * stock.getParValue()) / stock.getMarketPrice();
    }

    /**
     * Calculates the P/E Ratio
     *
     * @param stock
     * @return
     */
    public static float peRatio(SimpleStock stock) {
        return stock.getMarketPrice() / StockUtils.dividendYield(stock);
    }

    /**
     * Calculates the Volume Weighted Stock Price from the trades of a stock
     * (past 15minutes only)
     *
     * @param stock
     * @return
     */
    public static float volumeWeightedStockPrice(SimpleStock stock) {
        float tradePriceQuantity = 0;
        float quantity = 0;
        for (Trade trade : stock.getTrades()) {
            if (GeneralUtils.isDateInPast15Mins(trade.getDate())) {
                tradePriceQuantity += trade.getTradePrice() * trade.getShares();
                quantity += trade.getShares();
            }
        }
        if (quantity == 0) {
            return 0;
        }
        return tradePriceQuantity / quantity;
    }

    /**
     * Calculates the geometric mean based on all stocks. uses the volume
     * weighted stock price as the price
     *
     * @param stocks
     * @return
     */
    public static float geometricMean(Collection<SimpleStock> stocks) {
        float pricesProduct = 0;
        Iterator<SimpleStock> iter = stocks.iterator();
        while (iter.hasNext()) {
            SimpleStock stock = iter.next();
            float price = stock.getVolumeWeightedStockPrice();
            if (price == 0) {
                System.out.println("removing stock " + stock.getSymbol() + " from geometric mean calculation due to price = 0 (still counts in nth root calculation)");
            } else if (pricesProduct == 0) {
                pricesProduct = price;
            } else {
                pricesProduct *= price;
            }

        }
        return (float) Math.pow(pricesProduct, 1f / (float) stocks.size());
    }
}
