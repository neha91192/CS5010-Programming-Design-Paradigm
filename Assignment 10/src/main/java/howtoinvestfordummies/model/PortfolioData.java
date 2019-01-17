package howtoinvestfordummies.model;

import java.util.Date;

/**
 * Represents data class used to store details about the portfolio.
 */
public class PortfolioData {
  /**
   * Constructor of portfolio data object class.
   *
   * @param portfolioName        name of the portfolio in string.
   * @param type                 of the Portfolio.
   * @param frequencyValue       of the purchase in int.
   * @param transactionStartDate in Date.
   * @param transactionEndDate   in Date.
   */
  public PortfolioData(String portfolioName, PortfolioType type, int frequencyValue,
                       Date transactionStartDate, Date transactionEndDate) {
    this.portfolioName = portfolioName;
    this.frequencyValue = frequencyValue;
    this.transactionStartDate = transactionStartDate;
    this.transactionEndDate = transactionEndDate;
    this.type = type;
  }

  /**
   * Unique name of this portfolio in string.
   */
  private String portfolioName;
  /**
   * Represents type of portfolio.
   */
  private PortfolioType type;
  /**
   * Frequency of stocks to be purchased. 0 for one time purchase and integer value for recurring.
   */
  private int frequencyValue;
  /**
   * Start date of transaction in portfolio.
   */
  private Date transactionStartDate;
  /**
   * End date of transaction in portfolio.
   */
  private Date transactionEndDate;

  /**
   * Returns name of the portfolio.
   *
   * @return name in String.
   */
  public String getPortfolioName() {
    return portfolioName;
  }

  /**
   * Returns type of the portfolio.
   *
   * @return type in PortfolioType.
   */
  public PortfolioType getType() {
    return type;
  }

  /**
   * Returns frequency of the transaction in portfolio.
   *
   * @return frequencyValue in int.
   */
  public int getFrequencyValue() {
    return frequencyValue;
  }

  /**
   * Returns startDate of the transaction in portfolio.
   *
   * @return frequencyValue in int.
   */
  public Date getTransactionStartDate() {
    return transactionStartDate;
  }

  public Date getTransactionEndDate() {
    return transactionEndDate;
  }
}
