package howtoinvestfordummies.model;

import java.util.Date;

/**
 * Represents any form of Investment a user may initiate in course of this application. An
 * investment may have current value, money invested so far as cost and date of purchase.
 */
public interface IInvestment {
  /**
   * Returns current value of the investment. To determine the current value of investment, the
   * implementor may use external web resource or local data.
   *
   * @param date for which investment value needs to be found.
   * @return cost in double representing the current value of user holdings in this investment.
   */
  double getValue(Date date);

  /**
   * Returns cost of investment made by user.
   *
   * @return cost in double representing the amount invested by the user.
   */
  double getInvestmentCost();

  /**
   * This method returns the purchase date of investment. It helps in finding when this investment
   * was made which includes the date as well as time.
   *
   * @return date in Date consisting of purchase date.
   */
  Date getPurchaseDate();

}
