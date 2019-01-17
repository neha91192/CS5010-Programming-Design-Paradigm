package howtoinvestfordummies.model;

import java.util.Date;
import java.util.List;

/**
 * <p>This interface is designed with an intention to helps users learn about how money could be
 * invested using different options available in market. It allows user to create investment
 * portfolios, try out buying and selling of different investment options, and other various
 * investment strategies to see how they can grow (or shrink) their money with time.</p>
 * <p>To start with any investment, user has to create a portfolio which can contain different
 * investment entities. Different Investment Strategies may extend this interface to add respective
 * investments in the portfolio created by the implementation of this class. For the first version,
 * we have added {@link StockInvestmentStrategies} interface which is extending this Interface to
 * expose features specific to Stock Investments.</p>
 * <p>Apart from creating Portfolios, user can explore portfolios by viewing details of the
 * same. User may either list all the existing portfolios, read composition of a desired portfolio
 * or check investment cost and value of that portfolio for any given period.</p>
 */
public interface InvestmentStrategies {
  /**
   * Creates a new portfolio object for the provided portfolio name. User may provide suitable
   * portfolio name serves the intend of keeping all relevant investment entities at same place.
   * User has to provide a unique portfolio name which doesn't match with the existing one.
   *
   * @param portfolioName unique value of the portfolio name in String.
   * @return portfolio created by user of type IPortfolio.
   * @throws Exception in case portfolio with the provided name is already present.
   */
  IPortfolio createPortfolio(String portfolioName) throws Exception;

  /**
   * Returns list of portfolio objects created by the user. The implementation should ensure that
   * the original portfolio data structure is not modified.
   *
   * @return collection of portfolios created by user of type IPortfolio.
   */
  List<IPortfolio> getPortfolios();

  /**
   * Provides a way for the user to check complete details of the portfolio for a given portfolio
   * name.
   *
   * @param portfolioName name of the portfolio for which user wants to check details.
   * @return an object of type IPortfolio containing composition of the same.
   */
  IPortfolio viewPortfolio(String portfolioName);

  /**
   * At any given time, user may want to check the status of investment belonging to any portfolio.
   * This method ensures the comparison of user investment cost at portfolio level with its value
   * for the provided date. The implementation should ensure that only valid date is provided in the
   * input. In case the date provided is non-working date, closing price for the previous working
   * day should be taken into consideration for evaluating current portfolio value.
   *
   * @param date          for which portfolio needs to be checked.
   * @param portfolioName in String for which investment needs to be checked.
   * @return portfolio Status represented as String.
   * @throws IllegalArgumentException in case date is invalid or portfolio name is not found.
   */
  String checkPortfolioStatus(Date date, String portfolioName) throws IllegalArgumentException;

}
