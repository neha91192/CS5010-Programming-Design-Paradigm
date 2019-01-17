package howtoinvestfordummies.model.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Fetches remote web api stock data.
 */
public class RemoteStockAdapterImpl extends AbstractStockAdapter {
  /**
   * Singleton instance object of this class.
   */
  private static RemoteStockAdapterImpl instance;
  /**
   * API key for using this api.
   */
  private static final String API_KEY = "XHZ2ZY8TU05FP1B8";

  /**
   * Private constructor of this class.
   */
  private RemoteStockAdapterImpl() {

  }

  /**
   * Returns the same instance everytime if the instance is already created otherwise initialises a
   * new one.
   *
   * @return instance of this class.
   */
  public static IStockAdapter getInstance() {
    if (instance == null) {
      instance = new RemoteStockAdapterImpl();
    }
    return instance;
  }


  /**
   * Fetches lowest stock price returned by {@link RemoteStockAdapterImpl#getDataFromAPI(String)}
   * method for a given date.
   *
   * @param stockSymbol unique symbol for the stock company in String.
   * @param date        for which stock price needs to be fetched.
   * @return price of stock in double.
   * @throws Exception in case stock data was not found for this date or symbol.
   */
  @Override
  public double getStockPriceForBuying(String stockSymbol, Date date) throws Exception {
    String result = getDataFromAPI(stockSymbol);
    String queryDate = getDateInString(date);
    String[] lines = result.split("\n");
    double price = 0f;
    for (String line : lines) {
      String[] entry = line.split(",");
      if (entry[0].compareTo(queryDate) == 0) {
        price = Double.parseDouble(entry[3]);
      }
    }

    return price;
  }

  /**
   * Fetches closing stock price returned by {@link RemoteStockAdapterImpl#getDataFromAPI(String)}
   * method for a given date.
   *
   * @param stockSymbol unique symbol for the stock company in String.
   * @param date        for which stock price needs to be fetched.
   * @return price of stock in double.
   * @throws Exception in case stock data was not found for this date or symbol.
   */
  @Override
  public double getStockPriceForCalculatingValue(String stockSymbol, Date date) throws Exception {
    String result = getDataFromAPI(stockSymbol);
    String queryDate = getDateInString(date);
    String[] lines = result.split("\n");
    double price = 0f;
    for (String line : lines) {
      String[] entry = line.split(",");
      if (entry[0].compareTo(queryDate) <= 0) {
        price = Double.parseDouble(entry[4]);
      }
    }

    return price;
  }


  /**
   * Makes call to stock api to fetch time-based data and returns output in string format.
   *
   * @param stockSymbol representing unique company stock.
   * @return output data in String.
   * @throws Exception in case data was not found or api is incorrect.
   */
  private String getDataFromAPI(String stockSymbol) throws Exception {
    String url_api = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY"
            + "&symbol=STOCK_SYMBOL&outputsize=full&apikey=" + API_KEY + "&datatype=csv";
    URL url = null;

    try {
      url = new URL(url_api.replace("STOCK_SYMBOL", stockSymbol));
    } catch (MalformedURLException e) {
      throw new MalformedURLException("the alphavantage API has either changed or "
              + "no longer works");
    }
    InputStream in = null;
    StringBuilder output = new StringBuilder();
    try {

      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return output.toString();
  }
}
