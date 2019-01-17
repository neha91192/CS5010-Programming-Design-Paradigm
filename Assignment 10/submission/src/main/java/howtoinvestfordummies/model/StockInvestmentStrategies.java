package howtoinvestfordummies.model;

import java.util.Date;

/**
 * Handles all the operations related to Stock Investment. It extends
 * {@link InvestmentStrategies}
 * and for the initial version, it provides support to specialized features related to
 * buying of
 * stocks.
 * <strong>Changes/Additions</strong>
 * <ul><li>
 * Added a new method to support buying with commission. This method also supports
 * flexibility to
 * add future enhancements in this application.
 * </li></ul>
 */
public interface StockInvestmentStrategies extends InvestmentStrategies {
  /**
   * <p>This method allows trading of the stocks which includes certain shares(integer
   * or fraction) for a given amount. Since the entire purpose of this interface is to
   * make user
   * understand about the stock investment, through this method, user can understand
   * buying of
   * stocks at a certain date which could be a past date. Date should also include the
   * time in 24Hr
   * format so that user explores various scenarios which could happen in actual
   * trading (For
   * Example, restricting user to not buying anything after stock exchange operation
   * hours or on
   * holidays.). Implementor is free to choose the strategy for selecting operation
   * hours as per the
   * exchange timings and timezone. If the user does not provide time, current time
   * will be taken
   * into consideration for today's date.</p>
   * <p>Note that, user can buy shares for a given portfolio name. The minimum amount
   * required for user to invest should be greater than 0.0. Along with the mentioned
   * data, user
   * must provide company ticker symbol which helps in identifying the stocks that user
   * wants to
   * buy. If any of these parameters are not as expected, the method should throw {@link
   * IllegalArgumentException}.</p>
   *
   * @param date          for which user needs to buy stock. It should be in
   *                      "MM-dd-yyyy HH:mm:ss"
   *                      format.
   * @param amount        in double which describes total cost of investment of user
   *                      for this
   *                      purchase.
   * @param symbol        unique company ticker symbol in String to identify stock of
   *                      that company.
   * @param portfolioName name of the Portfolio in String for which user wants to buy
   *                      stock.
   * @throws IllegalArgumentException in case parameters provided are incorrect.
   */
  void buySharesOfStock(Date date, double amount, String symbol, String portfolioName)
          throws IllegalArgumentException;

  /**
   * <p>This method allows user to buy stocks with different parameters.
   * {@link Transaction} object
   * contains data required to buy stocks such as Date, stock symbol, transaction date,
   * portfolio
   * name, etc. Additionally, if user wants to purchase with commission, this can also
   * be set for
   * each transaction call. If user intends to buy stock with basic parameters, please
   * use {@link
   * StockInvestmentStrategies#buySharesOfStock(Date, double, String, String)} method
   * for the
   * same.</p>
   *
   * @param transaction of type Transaction containing details for buying stocks.
   * @throws IllegalArgumentException in case parameters provided are incorrect.
   */
  void buyStocksWithCommission(Transaction transaction) throws IllegalArgumentException;

}
