package org.supersimplestocks.core.pojos;

import java.util.ArrayList;
import java.util.List;
import org.supersimplestocks.core.enums.StockType;
import org.supersimplestocks.core.utils.StockUtils;

/**
 *
 * @author kurt
 */
public class SimpleStock {

    private String symbol;

    private StockType type;

    private float lastDividend;

    private float fixedDividend;

    private float parValue;

    private float marketPrice;

    private final List<Trade> trades = new ArrayList<>();

    public SimpleStock() {
    }

    public SimpleStock(String symbol, StockType type, float lastDividend, float fixedDividend, float parValue) {
        this.symbol = symbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public StockType getType() {
        return type;
    }

    public void setType(StockType type) {
        this.type = type;
    }

    public float getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(float lastDividend) {
        this.lastDividend = lastDividend;
    }

    public float getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(String fixedDividend) {
        float result;
        if (fixedDividend.contains("%")) {
            fixedDividend = fixedDividend.replace("%", "");
            result = Float.parseFloat(fixedDividend) / 100;
        } else {
            result = Float.parseFloat(fixedDividend);
        }
        this.fixedDividend = result;
    }

    public float getParValue() {
        return parValue;
    }

    public void setParValue(float parValue) {
        this.parValue = parValue;
    }

    public float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public float getDividendYield() {
        return StockUtils.dividendYield(this);
    }

    public float getPERatio() {
        return StockUtils.peRatio(this);
    }

    public float getVolumeWeightedStockPrice() {
        return StockUtils.volumeWeightedStockPrice(this);
    }

    public List<Trade> getTrades() {
        return trades;
    }

    @Override
    public String toString() {
        return "SimpleStock{" + "symbol=" + symbol + ", type=" + type + ", lastDividend=" + lastDividend + ", fixedDividend=" + fixedDividend + ", parValue=" + parValue + ", marketPrice=" + marketPrice + '}';
    }

}
