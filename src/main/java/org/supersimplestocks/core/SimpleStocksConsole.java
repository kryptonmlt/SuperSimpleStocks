package org.supersimplestocks.core;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import org.supersimplestocks.core.pojos.SimpleStock;
import org.supersimplestocks.core.pojos.Trade;
import org.supersimplestocks.core.utils.GeneralUtils;
import org.supersimplestocks.core.utils.StockUtils;

/**
 *
 * @author kurt
 */
public class SimpleStocksConsole {

    private final Map<String, SimpleStock> stocks;

    public SimpleStocksConsole(Map<String, SimpleStock> stocks) {
        this.stocks = stocks;
    }

    /**
     * General Menu of console
     *
     * @param scanner
     */
    public void generalMenu(Scanner scanner) {
        System.out.println("-----------------------------------------");
        System.out.println("Commands Available:");
        System.out.println("stock - to enter stock menu");
        System.out.println("gbce - to calculate the gbce all share index");
        System.out.println("menu - to go back to this menu");
        System.out.println("exit - to exit application");
        String input = scanner.nextLine();
        menuBranchCheck(scanner, input);
    }

    /**
     * General Menu branch check
     *
     * @param scanner
     * @param input
     */
    private void menuBranchCheck(Scanner scanner, String input) {
        switch (input.toLowerCase()) {
            case "stock":
                stockOption(scanner);
                break;
            case "gbce":
                System.out.println("Geometric Mean: " + StockUtils.geometricMean(stocks.values()));
                generalMenu(scanner);
                break;
            case "menu":
                generalMenu(scanner);
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                generalMenu(scanner);
        }
    }

    /**
     * Stock Menu branch check
     *
     * @param scanner
     * @param stock
     * @param input
     */
    private void stockBranchCheck(Scanner scanner, SimpleStock stock, String input) {
        switch (input.toLowerCase()) {
            case "marketprice":
                marketPriceOption(scanner, stock);
                break;
            case "stock":
                stockOption(scanner);
                break;
            case "trade":
                tradeOption(scanner, stock);
                break;
            case "volume":
                volumeOption(scanner, stock);
                break;
            case "menu":
                generalMenu(scanner);
                break;
            case "exit":
                System.exit(0);
                break;
        }
    }

    /**
     * Stocks Option was selected
     *
     * @param scanner
     */
    private void stockOption(Scanner scanner) {
        System.out.println("-----------------------------------------");
        System.out.println(Arrays.toString(stocks.keySet().toArray()));
        System.out.println("Please write the Stock's symbol you would like to use:");
        String input = scanner.nextLine();
        while (!stocks.containsKey(input)) {
            System.out.println("Choose a stock which appears in the list below!");
            System.out.println(Arrays.toString(stocks.keySet().toArray()));
            input = scanner.nextLine();
        }
        SimpleStock stock = stocks.get(input);
        System.out.println("Commands Available:");
        System.out.println("marketprice - to set market price");
        System.out.println("trade - to initiate trades");
        System.out.println("volume - to calculate volume weighted stock (15mins)");
        System.out.println("menu - to go to menu");
        System.out.println("exit - to exit application");
        while (true) {
            input = scanner.nextLine();
            stockBranchCheck(scanner, stock, input);
        }

    }

    /**
     * Market Option was selected
     *
     * @param scanner
     * @param stock
     */
    private void marketPriceOption(Scanner scanner, SimpleStock stock) {
        System.out.println("-----------------------------------------");
        System.out.println(stock.getSymbol() + " - Please input Market Price:");
        String input = scanner.nextLine();
        while (!GeneralUtils.isNumeric(input)) {
            System.out.println("Market Price must be a number:");
            input = scanner.nextLine();
        }
        float marketPrice = Float.parseFloat(input);
        stock.setMarketPrice(marketPrice);
        System.out.println("Dividend Yield: " + stock.getDividendYield());
        System.out.println("PE/Ratio: " + stock.getPERatio());
        stockOption(scanner);
    }

    /**
     * Trade Option was selected
     *
     * @param scanner
     * @param stock
     */
    private void tradeOption(Scanner scanner, SimpleStock stock) {
        System.out.println("-----------------------------------------");
        System.out.println("(Reminder) menu - to go to menu");
        System.out.println("Submit any trades in the format (timestamp shares buy/sell tradePrice) example below:");
        System.out.println("Example:");
        System.out.println("20/10/2016-15:28 100 buy 10");
        while (true) {
            String input = scanner.nextLine();
            stockBranchCheck(scanner, stock, input);
            Trade trade = GeneralUtils.getTrade(input);
            if (trade != null) {
                stock.getTrades().add(trade);
                System.out.println("Trade added successfully to " + stock.getSymbol() + "! add more trades or use menu.");
            }
        }
    }

    /**
     * Volume Option was selected
     *
     * @param scanner
     * @param stock
     */
    private void volumeOption(Scanner scanner, SimpleStock stock) {
        System.out.println("-----------------------------------------");
        System.out.println("(Reminder) menu - to go to menu");
        System.out.println(stock.getSymbol() + " - Volume Weighted Stock Price (Last 15mins): " + stock.getVolumeWeightedStockPrice());
        stockOption(scanner);
    }

}
