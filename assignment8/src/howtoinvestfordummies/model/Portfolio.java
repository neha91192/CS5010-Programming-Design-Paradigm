package howtoinvestfordummies.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents implementation for {@link IPortfolio} interface. This class provides features related
 * to operations at portfolio level which includes finding cost and value of all the investments in
 * the portfolio, list all the investments and add investment to the portfolio.
 */
public class Portfolio implements IPortfolio {
  /**
   * Data structure to store list of investments in this portfolio. It contains elements of type
   * {@link IInvestment}.
   */
  private List<IInvestment> investments;
  /**
   * Unique name of this portfolio in string.
   */
  private String portfolioName;

  /**
   * Constructor to create the portfolio object. It initialises portfolio with the name and an empty
   * investment list.
   *
   * @param portfolioName name of the portfolio in String.
   */
  public Portfolio(String portfolioName) {
    this.portfolioName = portfolioName;
    investments = new ArrayList<>();
  }

  /**
   * Constructor to create portfolio object with the name and list of investments of the portfolio.
   *
   * @param portfolioName name of the portfolio in String.
   * @param investments   list containing object of type IInvestment.
   */
  public Portfolio(String portfolioName, List<IInvestment> investments) {
    this.portfolioName = portfolioName;
    this.investments = investments;
  }

  /**
   * Returns name of the portfolio.
   *
   * @return name of the portfolio in String.
   */
  @Override
  public String getName() {
    return portfolioName;
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
            .mapToDouble(IInvestment::getInvestmentCost).sum();
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
    return new Portfolio(this.portfolioName, newList);
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
    sb.append("Portfolio Name: " + portfolioName + "\n");
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
}
