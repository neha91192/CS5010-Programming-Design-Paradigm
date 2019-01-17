package howtoinvestfordummies.model;

import java.util.Date;

import howtoinvestfordummies.model.adapter.IStockAdapter;
import howtoinvestfordummies.model.adapter.LocalStockAdapterImpl;
import howtoinvestfordummies.model.adapter.RemoteStockAdapterImpl;

/**
 * <p>Represents a type of Investment user can perform. A stock of a company is simply
 * a part of ownership in that company which can be represented in shares. Any stock
 * can be
 * identified with its ticker symbol. The value of the stock on a particular day is the
 * money the
 * investor would receive if he/she sold the stock on that day.</p>
 * <strong>Changes:</strong>
 * <ul> <li>Added methods to set and read commission fees and percentage weights at
 * investment level. </li> </ul>
 */
public class Stock extends Investment {
  private IStockAdapter localAdapter;
  /**
   * Represents publicly traded company’s symbol is given as unique “ticker symbol”
   * used to
   * trading.
   */
  private String tickerSymbol;
  /**
   * Number of units of stock purchased in double. For simplicity we have considered
   * the amount in
   * fractions instead of real world integral values for representing shares.
   */
  private double shares;
  /**
   * Represents percentage weight of this stock as a part of the whole portfolio.
   */
  private double percentageWeight;
  /**
   * Represents commission fee for this stock as a part of the whole portfolio.
   */
  private double commissionFees;

  /**
   * Constructor required for generating stock object.
   *
   * @param tickerSymbol   symbol of the company whose stock has be created.
   * @param shares         amount of share bought in double.
   * @param dateOfPurchase purchase date of type Date.
   * @param cost           spent on buying this stock in double.
   */
  Stock(String tickerSymbol, double shares, Date dateOfPurchase, double cost) {
    super(cost, dateOfPurchase);
    this.tickerSymbol = tickerSymbol;
    this.shares = shares;
    this.percentageWeight = 100;
    localAdapter = LocalStockAdapterImpl.getInstance();
  }

  /**
   * Represents toString version of the Stock object. It includes tickerSymbol,
   * investment cost,
   * investment value at the stock level.
   *
   * @return string representation of stock.
   */
  @Override
  public String toString() {
    String cost = Double.toString(this.cost);
    String value = Double.toString(getValue(dateOfPurchase));
    if (value.equals("0.0")) {
      value = "Not Found";
    }
    return tickerSymbol
            + ":\n\tInvestment Cost: " + cost + " " + currency.toString() + "\n"
            + "\tInvestment Value: " + value + " " + currency.toString() + "\n"
            + "\tCommission: " + commissionFees + " " + currency.toString() + "\n"
            + "\tShares: " + shares + "\n"
            + "\tCurrent value: " + getCurrentValue() + " " + currency.toString() + "\n"
            + "\tDate of Purchase: " + dateOfPurchase.toString() + "\n";
  }

  /**
   * Finds current value of the stock. If the value is present locally, it takes value
   * from the
   * local file first and if it is not present, it returns the current value of the
   * investment
   * through web api call.
   *
   * @return value of the investment in double.
   */
  @Override
  public double getValue(Date date) {
    double currentValue;
    try {
      currentValue = localAdapter.getStockPriceForCalculatingValue(this.tickerSymbol,
              date);
    } catch (Exception e) {
      currentValue = getValueFromLocal(date);
    }
    return round(currentValue * shares * 100);
  }

  /**
   * Fetches value from local data in case remote adapter fails to fetch data.
   *
   * @param date of purchase of stock.
   * @return value of stock in double.
   */
  private double getValueFromLocal(Date date) {
    double value = 0f;
    try {
      value = localAdapter.getStockPriceForCalculatingValue(this.tickerSymbol, date);
    } catch (Exception e) {
      return 0f;
    }
    return value;
  }

  /**
   * Returns percentage weight for this stock.
   *
   * @return percentageWeight weight of this stock.
   */
  @Override
  public double getPercentageWeight() {
    return percentageWeight;
  }

  /**
   * Sets percentage weight for this stock.
   *
   * @param percentageWeight weight of this stock.
   */
  @Override
  public void setPercentageWeight(double percentageWeight) {
    this.percentageWeight = percentageWeight;
  }

  /**
   * Returns commission fee for this stock level transaction.
   *
   * @return commissionFees in double.
   */
  @Override
  public double getCommissionFees() {
    return commissionFees;
  }

  /**
   * Sets commission fees value for this stock.
   *
   * @param commissionFees of this stock.
   */
  @Override
  public void setCommissionFees(double commissionFees) {
    this.commissionFees = commissionFees;
  }

  /**
   * Returns name of investment. For this level, it will be name of the stock.
   *
   * @return name in String.
   */
  @Override
  public String getInvestmentName() {
    return this.tickerSymbol;
  }

  /**
   * Returns shares purchased in this stock.
   *
   * @return shares in double.
   */
  @Override
  public double getShares() {
    return shares;
  }

  /**
   * Returns current value of the stock fetched from adapter.
   *
   * @return value of the stock in double.
   */
  private double getCurrentValue() {

    double currentValue;
    try {
      currentValue = localAdapter.getStockPriceForCalculatingValue(this.tickerSymbol, new
              Date());
    } catch (Exception e) {
      currentValue = getCostFromLocal(new Date());
    }
    return round(currentValue * shares * 100);
  }

  /**
   * Fetches value from local data in case remote adapter fails to fetch data.
   *
   * @param date of purchase of stock.
   * @return value of stock in double.
   */
  private double getCostFromLocal(Date date) {
    double value = 0f;
    try {
      value = localAdapter.getStockPriceForBuying(this.tickerSymbol, date);
    } catch (Exception e) {
      return 0f;
    }
    return value;
  }


  /**
   * Formats the value of float upto 2 decimals.
   *
   * @param value in double.
   * @return formatted value in double.
   */
  private double round(double value) {
    double result = Math.round(value);
    result = result / 100;
    return result;
  }

}
