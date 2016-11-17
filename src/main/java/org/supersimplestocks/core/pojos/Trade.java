package org.supersimplestocks.core.pojos;

import org.joda.time.DateTime;
import org.supersimplestocks.core.enums.Action;

/**
 *
 * @author kurt
 */
public class Trade {

    private DateTime date;

    private float shares;

    private Action action;

    private float tradePrice;

    public Trade(DateTime date, float shares, Action action, float tradePrice) {
        this.date = date;
        this.shares = shares;
        this.action = action;
        this.tradePrice = tradePrice;
    }

    public Trade() {
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public float getShares() {
        return shares;
    }

    public void setShares(float shares) {
        this.shares = shares;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public float getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(float tradePrice) {
        this.tradePrice = tradePrice;
    }

    @Override
    public String toString() {
        return "Trade{" + "date=" + date + ", shares=" + shares + ", action=" + action + ", tradePrice=" + tradePrice + '}';
    }

}
