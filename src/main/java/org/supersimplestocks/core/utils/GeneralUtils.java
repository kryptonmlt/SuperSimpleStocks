package org.supersimplestocks.core.utils;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.supersimplestocks.core.enums.Action;
import org.supersimplestocks.core.enums.StockType;
import org.supersimplestocks.core.pojos.SimpleStock;
import org.supersimplestocks.core.pojos.Trade;
import static org.supersimplestocks.core.utils.GeneralUtils.LOGGER;

/**
 *
 * @author kurt
 */
public class GeneralUtils {

    private final static DateTimeFormatter FMT = DateTimeFormat.forPattern("dd/MM/yyyy-HH:mm");
    final static Logger LOGGER = Logger.getLogger(GeneralUtils.class);

    private GeneralUtils() {

    }

    /**
     * Checks whether it is a number
     * @param str
     * @return true if number
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * parses String[] to build stock object
     * @param input
     * @return 
     */
    public static SimpleStock getStock(String[] input) {
        SimpleStock stock = new SimpleStock();
        stock.setSymbol(input[0]);
        stock.setType(StockType.getType(input[1]));
        stock.setLastDividend(Float.parseFloat(input[2]));
        if (stock.getType() == StockType.PREFERRED) {
            stock.setFixedDividend(input[3]);
            stock.setParValue(Float.parseFloat(input[4]));
        } else {
            stock.setParValue(Float.parseFloat(input[3]));
        }
        return stock;
    }

    /**
     * parses line and builds Trade object
     * @param input
     * @return 
     */
    public static Trade getTrade(String input) {
        try {
            String[] fields = input.split(" ");
            Trade trade = new Trade();
            trade.setDate(DateTime.parse(fields[0], FMT));
            trade.setShares(Float.parseFloat(fields[1]));
            trade.setAction(Action.getType(fields[2]));
            trade.setTradePrice(Float.parseFloat(fields[3]));
            return trade;
        } catch (Exception e) {
            LOGGER.error("Error parsing trade: ", e);
            System.out.println("Please Enter Trade in expected format");
        }
        return null;
    }

    /**
     * Checks whether date is within the past 15 minutes
     * @param date
     * @return true if date is within the past 15 minutes
     */
    public static boolean isDateInPast15Mins(DateTime date) {
        DateTime fifteenMinutesAgo = DateTime.now().minusMinutes(15);
        return date.isAfter(fifteenMinutesAgo);
    }
}
