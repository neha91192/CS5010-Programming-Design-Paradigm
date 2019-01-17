package howtoinvestfordummies.model.adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Fetches local data stored in csv file.
 */
public class LocalStockAdapterImpl extends AbstractStockAdapter {
  /**
   * Singleton instance object of this class.
   */
  private static LocalStockAdapterImpl instance;

  /**
   * Private constructor of this class.
   */
  private LocalStockAdapterImpl() {

  }

  /**
   * Returns the same instance everytime if the instance is already created otherwise
   * initialises a
   * new one.
   *
   * @return instance of this class.
   */
  public static IStockAdapter getInstance() {
    if (instance == null) {
      instance = new LocalStockAdapterImpl();
    }
    return instance;
  }

  /**
   * Fetches lowest price stored in the "symbol".csv filename for a given date.
   *
   * @param symbol unique symbol for the stock company in String.
   * @param date   for which stock price needs to be fetched.
   * @return price of stock in double.
   * @throws Exception in case stock data was not found for this date or symbol.
   */
  @Override
  public double getStockPriceForBuying(String symbol, Date date) throws Exception {

    String dateInString = getDateInString(date);

    try {
      Scanner scanner = new Scanner(new File("res/" + symbol + ".csv"));
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] datePrice = line.split(",");
        if (datePrice[0].equals(dateInString)) {
          scanner.close();
          return Double.parseDouble(datePrice[3]);
        }
      }
      throw new NoSuchElementException("Stock data not found for this date");
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Stock data not found for this symbol");
    }
  }

  /**
   * Fetches closing price stored in the "symbol".csv filename for a given date.
   *
   * @param stockSymbol unique symbol for the stock company in String.
   * @param date        for which stock price needs to be fetched.
   * @return price of stock in double.
   * @throws Exception in case stock data was not found for this date or symbol.
   */
  @Override
  public double getStockPriceForCalculatingValue(String stockSymbol, Date date) throws
          Exception {
    String dateInString = getDateInString(date);

    try {
      Scanner scanner = new Scanner(new File("res/" + stockSymbol + ".csv"));
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] datePrice = line.split(",");
        if (datePrice[0].compareTo(dateInString) <= 0) {
          scanner.close();
          return Double.parseDouble(datePrice[4]);
        }
      }
      throw new NoSuchElementException("Stock data not found for this date");
    } catch (FileNotFoundException e) {
      throw new Exception("Stock data not found for this symbol");
    }
  }
}
