package howtoinvestfordummies.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import howtoinvestfordummies.model.adapter.IStockAdapter;
import howtoinvestfordummies.model.adapter.RemoteStockAdapterImpl;

/**
 * Represents special case of portfolio which handles recurring investments. Whenever user initiates
 * any high level investment from the view through controller, this portfolio needs to be configured
 * and should be leveraged to support all kinds of recurring investment requests and handling.
 */
public class RecurringPortfolio extends AbstractPortfolio {

  private ITradingInformation tradingInformation;
  private IStockAdapter adapter;

  /**
   * Constructor required for generating RecurringPortfolio object.
   *
   * @param portfolioName        name of the portfolio
   * @param frequencyValue       frequency for each recurring transaction
   * @param transactionStartDate starting date for the recurring transaction
   * @param transactionEndDate   ending date for the recurring transaction
   * @param recurringStocks      all the stocks to be bought
   */
  public RecurringPortfolio(String portfolioName, int frequencyValue,
                            Date transactionStartDate, Date transactionEndDate,
                            List<IInvestment> recurringStocks) {
    super(recurringStocks);
    this.portfolioData = new PortfolioData(portfolioName, PortfolioType.RECURRING,
            frequencyValue, transactionStartDate, transactionEndDate);
    adapter = RemoteStockAdapterImpl.getInstance();
    tradingInformation = new TradingInformation();
  }

  /**
   * Constructor to create portfolio object with the name and list of investments of the portfolio.
   *
   * @param investments list containing object of type IInvestment.
   */
  public RecurringPortfolio(List<IInvestment> investments) {
    super(investments);
  }

  /**
   * Updates date to next transaction date. Adds up the date to the given provided value.
   *
   * @param currentDate of type Date.
   * @param value       of type int.
   * @return updated date of type Date.
   */
  private Date updateDate(Date currentDate, int value) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDate);
    calendar.add(Calendar.DATE, value);
    return calendar.getTime();
  }

  /**
   * Calculates the number of transactions done for the recurring investment till the date passed as
   * parameter.
   *
   * @param date till which the recurring investments need to be obtained
   * @return all the investments till date
   */
  private List<IInvestment> getRecurringInvestments(Date date) {
    Date latestTransaction = date;
    if (portfolioData.getTransactionEndDate() != null) {
      latestTransaction = date.compareTo(portfolioData.getTransactionEndDate()) <= 0 ? date :
              portfolioData.getTransactionEndDate();
    }
    List<IInvestment> recurringInvestments = new ArrayList<>();
    for (IInvestment stock : investments) {
      double cost = stock.getInvestmentCost();
      Date dateOfPurchase = portfolioData.getTransactionStartDate();
      while (latestTransaction.compareTo(dateOfPurchase) >= 0) {
        IInvestment investment = getInvestmentForRecurringPurchase(stock
                        .getInvestmentName(),
                dateOfPurchase, cost);
        investment.setCommissionFees(stock.getCommissionFees());
        recurringInvestments
                .add(investment);
        dateOfPurchase = updateDate(dateOfPurchase, portfolioData.getFrequencyValue());
      }
    }
    return recurringInvestments;
  }

  /**
   * Create a stocks object for the parameters passed.
   *
   * @param stock          for which the investment needs to be done
   * @param dateOfPurchase is the date on which the stock needs to be bought
   * @param amount         to be used to purchase the stock
   * @return the stock object with the details provided
   */
  private IInvestment getInvestmentForRecurringPurchase(String stock, Date dateOfPurchase,
                                                        double amount) {
    double noOfShares = 0.0;
    try {
      dateOfPurchase = handlePurchaseOnHolidays(dateOfPurchase);
      double pricePerShare = adapter.getStockPriceForCalculatingValue(stock,
              dateOfPurchase);
      noOfShares = amount / pricePerShare;
      noOfShares = Math.round(noOfShares * 100);
      noOfShares = noOfShares / 100;
    } catch (Exception e) {
      e.getMessage();
    }
    return new Stock(stock, noOfShares, dateOfPurchase, amount);
  }

  /**
   * Checks for the holidays and in case it is a closed day, it updates the date by a given value.
   *
   * @param dateOfPurchase date to be checked for purchase.
   * @return updated date in case there was a holiday.
   */
  private Date handlePurchaseOnHolidays(Date dateOfPurchase) {
    if (tradingInformation.isTradingHours(dateOfPurchase)) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(dateOfPurchase);
      if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
        dateOfPurchase = updateDate(dateOfPurchase, 2);
      } else {
        dateOfPurchase = updateDate(dateOfPurchase, 1);
      }
    }
    return dateOfPurchase;
  }

  /**
   * Calculates value of all the investments up to the specified date present in this portfolio. It
   * first filters out investments based on the date and then sums up the current value of each
   * investments in double and returns the sum in the end.
   *
   * @param date for which investment value needs to be fetched.
   * @return value of the investment up to 2 digits in double.
   */
  @Override
  public double getValue(Date date) {
    List<IInvestment> recurringInvestments = getRecurringInvestments(date);
    return recurringInvestments.stream()
            .mapToDouble(investment -> investment.getValue(date)).sum();
  }

  /**
   * Calculates total cost of the investments in the portfolio up to a specified date. It first
   * filters out the investments based on the purchase date and then sums up the cost value of each
   * investments and returns that in the end.
   *
   * @param date for which investment cost needs to be fetched.
   * @return cost of investment in double.
   */
  @Override
  public double getCostBasis(Date date) {
    List<IInvestment> recurringInvestments = getRecurringInvestments(date);
    return recurringInvestments.stream().mapToDouble(investment ->
            investment.getInvestmentCost() + investment.getCommissionFees()).sum();
  }

  @JsonGetter("data")
  @Override
  public PortfolioData getPortfolioData() {
    return getPortfolioCopy(this.portfolioData);
  }

  /**
   * Returns toString representation of Portfolio object. It first writes the name of the portfolio,
   * and calls toString of respective investments and then appends total investment cost and value
   * at the end.
   *
   * @return string representation of the Portfolio object.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Portfolio Name: " + portfolioData.getPortfolioName() + "\n");
    int i = 1;
    for (IInvestment investment : investments) {
      sb.append(i + ". " + investment.getInvestmentName() + "\n");
      sb.append("Percentage Weight:" + investment.getPercentageWeight() + "%\n");
      i++;
    }
    sb.append("Investment Cost: ");
    sb.append(getCostBasis(portfolioData.getTransactionStartDate()) + "\n");
    sb.append("Investment Start Date: ");
    sb.append(portfolioData.getTransactionStartDate() + "\n");
    if (portfolioData.getTransactionEndDate() != null) {
      sb.append("Investment End Date: ");
      sb.append(portfolioData.getTransactionEndDate() + "\n");
    } else {
      sb.append("Investment End Date: ONGOING\n");
    }
    sb.append("Investment Frequency in Days: ");
    sb.append(portfolioData.getFrequencyValue());

    return sb.toString();
  }
}
