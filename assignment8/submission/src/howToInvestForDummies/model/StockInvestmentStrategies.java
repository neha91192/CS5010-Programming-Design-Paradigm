package howtoinvestfordummies.model;

import java.util.Date;

/**
 * Handles all the operations related to Stock Investment. It extends {@link InvestmentStrategies}
 * and for the initial version, it provides support to specialized features related to buying of
 * stocks.
 */
public interface StockInvestmentStrategies extends InvestmentStrategies {
  /**
   * <p>This method allows trading of the stocks which includes certain shares(integer or fraction)
   * for a given amount. Since the entire purpose of this interface is to make user understand about
   * the stock investment, through this method, user can understand buying of stocks at a certain
   * date which could be a past date. Date should also include the time in 24Hr format so that user
   * explores various scenarios which could happen in actual trading (For Example, restricting user
   * to not buying anything after stock exchange operation hours or on holidays.). Implementor is
   * free to choose the strategy for selecting operation hours as per the exchange timings and
   * timezone. If the user does not provide time, current time will be taken into consideration for
   * today's date.</p>
   * <p>Note that, user can buy shares for a given portfolio name. The minimum amount required for
   * user to invest should be greater than 0.0. Along with the mentioned data, user must provide
   * company ticker symbol which helps in identifying the stocks that user wants to buy. If any of
   * these parameters are not as expected, the method should throw {@link
   * IllegalArgumentException}.</p>
   *
   * @param date          for which user needs to buy stock. It should be in "MM-dd-yyyy HH:mm:ss"
   *                      format.
   * @param amount        in double which describes total cost of investment of user for this
   *                      purchase.
   * @param symbol        unique company ticker symbol in String to identify stock of that company.
   * @param portfolioName name of the Portfolio in String for which user wants to buy stock.
   * @throws IllegalArgumentException in case parameters provided are incorrect.
   */
  void buySharesOfStock(Date date, double amount, String symbol, String portfolioName)
          throws IllegalArgumentException;

}
