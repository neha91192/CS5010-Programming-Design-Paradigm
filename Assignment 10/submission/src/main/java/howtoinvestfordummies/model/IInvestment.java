package howtoinvestfordummies.model;

import java.util.Date;

/**
 * <p>Represents any form of Investment a user may initiate in course of this
 * application. An investment may have current value, money invested so far as cost and
 * date of
 * purchase.</p>
 * <strong>Changes:</strong>
 * <ul>
 * <li>Added methods to set and read commission fees and percentage weights at
 * investment level.
 * </li>
 * </ul>
 */
public interface IInvestment {
  /**
   * Returns current value of the investment. To determine the current value of
   * investment, the
   * implementor may use external web resource or local data.
   *
   * @param date for which investment value needs to be found.
   * @return cost in double representing the current value of user holdings in this
   *         investment.
   */
  double getValue(Date date);

  /**
   * Returns cost of investment made by user.
   *
   * @return cost in double representing the amount invested by the user.
   */
  double getInvestmentCost();

  /**
   * <p>This method returns the purchase date of investment. It helps in finding when this
   * investment was made which includes the date as well as time.</p>
   *
   * @return date in Date consisting of purchase date.
   */
  Date getPurchaseDate();

  /**
   * Returns percentage weight of this stock.
   *
   * @return percentageWeight weight of this stock.
   */
  double getPercentageWeight();

  /**
   * Sets percentage weight for this stock.
   *
   * @param percentageWeight weight of this stock.
   */
  void setPercentageWeight(double percentageWeight);

  /**
   * Returns commission fees of this stock.
   *
   * @return commission fees of this stock.
   */
  double getCommissionFees();

  /**
   * Sets commission fees for this stock.
   *
   * @param commissionFees of this stock.
   */
  void setCommissionFees(double commissionFees);

  /**
   * Returns name of this investment.
   *
   * @return string representing name of this investment.
   */
  String getInvestmentName();

  /**
   * Returns shares purchased in this stock.
   *
   * @return shares in double.
   */
  double getShares();
}
