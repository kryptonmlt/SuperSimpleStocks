package org.supersimplestocks.core;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.supersimplestocks.core.pojos.SimpleStock;
import org.supersimplestocks.core.utils.GeneralUtils;

/**
 *
 * @author kurt
 */
public class Application {

    final static Logger LOGGER = Logger.getLogger(Application.class);

    /**
     * Path for file where stock data is stored
     *
     * @param args
     */
    public static void main(String[] args) {

        boolean isError = true;
        Map<String, SimpleStock> stocks = new HashMap<>();

        try {
            //reads data and builds stocks map from file
            String fileName = args[0];
            CSVReader reader = new CSVReader(new FileReader(fileName));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                SimpleStock stock = GeneralUtils.getStock(nextLine);
                stocks.put(stock.getSymbol(), stock);
                LOGGER.debug(stock);
            }
            isError = false;
        } catch (FileNotFoundException ex) {
            LOGGER.error("Error Finding File ", ex);
        } catch (IOException ex) {
            LOGGER.error("Error Reading File ", ex);
        }

        //check if no error occured in reading
        if (!isError) {
            //start console options
            SimpleStocksConsole console = new SimpleStocksConsole(stocks);
            Scanner scanner = new Scanner(System.in);
            console.generalMenu(scanner);
        } else {
            System.out.println("Stocks were not loaded. Please restart Application with correct path to data file");
        }
    }

}
