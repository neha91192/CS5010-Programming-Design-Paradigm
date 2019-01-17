package howtoinvestfordummies.model;

import java.util.Date;

/**
 * Represents a common class for providing implementations specific to all types of investments.
 */
public abstract class Investment implements IInvestment {
  /**
   * Represents Name for the investment object.
   */
  protected String name;
  /**
   * Represents Currency for the investment.
   */
  protected Currency currency;
  /**
   * Represents total money invested in double.
   */
  protected double cost;
  /**
   * Represents the date of purchase for this investment.
   */
  protected Date dateOfPurchase;

  /**
   * Constructor to initialize Investment object. It takes cost and date of purchase as parameters.
   *
   * @param cost           of investment of type double.
   * @param dateOfPurchase of investment of type Date.
   */
  Investment(double cost, Date dateOfPurchase) {
    this.cost = cost;
    this.dateOfPurchase = dateOfPurchase;
    this.currency = Currency.USD;
  }

  /**
   * This method is abstracted in order to let the child implementation decide the current value of
   * investment. Every type of investment will have a particular logic to calculate and depending
   * upon the current value of each investment unit, it will be derived by each investment type and
   * not at the abstract level. The subclasses may choose to avoid the implementation by making this
   * method as abstract in their own implementation.
   *
   * @return value in double representing value of the investment.
   */
  @Override
  public abstract double getValue(Date date);

  /**
   * Returns cost of the investment. While initiating the investment object, this cost is stored and
   * returned eventually when requested.
   *
   * @return cost of investment in double.
   */
  @Override
  public double getInvestmentCost() {
    return this.cost;
  }

  /**
   * Returns the date of purchase of this investment object.
   *
   * @return date of purchase in Date.
   */
  @Override
  public Date getPurchaseDate() {
    return this.dateOfPurchase;
  }
}
