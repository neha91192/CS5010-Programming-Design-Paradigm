package howtoinvestfordummies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents the generic implementation for portfolio.
 */
public abstract class AbstractPortfolio implements IPortfolio {
  /**
   * Data structure to store list of investments in this portfolio. It contains elements of type
   * {@link IInvestment}.
   */
  protected List<IInvestment> investments;

  /**
   * Data to store portfolio values.
   */
  protected PortfolioData portfolioData;

  /**
   * Constructor to create the portfolio object. It initialises portfolio with the name and an empty
   * investment list.
   *
   * @param investments is a list of investment in that portfolio
   */
  public AbstractPortfolio(List<IInvestment> investments) {
    this.investments = investments;
  }

  /**
   * Constructor to create portfolio object with the name and list of investments of the portfolio.
   *
   * @param portfolioName in String.
   */
  public AbstractPortfolio(String portfolioName) {
    this.portfolioData = new PortfolioData(portfolioName, PortfolioType.SIMPLE,
            0, null, null);
    this.investments = new ArrayList<>();
  }

  /**
   * Returns name of the portfolio.
   *
   * @return name of the portfolio in String.
   */
  @Override
  public String getName() {
    return portfolioData.getPortfolioName();
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
    return investments.stream()
            .filter(investment -> investment.getPurchaseDate().compareTo(date) <= 0)
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
    return investments.stream()
            .filter(investment ->
                    investment.getPurchaseDate().compareTo(date) <= 0)
            .mapToDouble(investment -> investment.getInvestmentCost() + investment
                    .getCommissionFees()).sum();
  }

  /**
   * Adds investment object to the existing investments in the portfolio and returns the updated
   * Portfolio object.
   *
   * @param investment object of type IInvestment that needs to be added.
   * @return portfolio object updated by adding this new investment.
   */
  @Override
  public IPortfolio addInvestments(IInvestment investment) {
    investments.add(investment);
    List<IInvestment> newList = new ArrayList<>(investments);
    return new Portfolio(portfolioData.getPortfolioName(), newList);
  }


  /**
   * Returns the new list containing same investments present in this portfolio.
   *
   * @return list of investments of type IInvestment.
   */
  @Override
  public List<IInvestment> getInvestments() {
    return new ArrayList<>(investments);
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
      sb.append(i + ". " + investment.toString());
    }
    sb.append("Current Portfolio Investment Cost: ");
    sb.append(getCostBasis(new Date()) + " " + Currency.USD + "\n");
    sb.append("Current Portfolio Investment Value: ");
    sb.append(getValue(new Date()) + " " + Currency.USD + "\n");
    sb.append("Today's date: " + new Date() + "\n");
    return sb.toString();
  }

  /**
   * Creates a copy of data and returns a new instance of PortfolioData.
   *
   * @param data of type PortfolioData
   * @return portfolioData
   */
  protected PortfolioData getPortfolioCopy(PortfolioData data) {
    return new PortfolioData(data.getPortfolioName(), data.getType(),
            data.getFrequencyValue(), data.getTransactionStartDate(),
            data.getTransactionEndDate());
  }
}
