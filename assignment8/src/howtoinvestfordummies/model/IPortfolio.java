package howtoinvestfordummies.model;

import java.util.Date;
import java.util.List;

/**
 * Represents common interface dedicated for portfolio related operations. After the portfolio is
 * created, user may wish to find certain details which are already present or calculated as per
 * request.
 */
public interface IPortfolio {
  /**
   * Returns the unique name of portfolio in String.
   *
   * @return name designated for the requested portfolio.
   */
  String getName();

  /**
   * <p>This method returns the total value of the portfolio up to the given date. It should return
   * the sum of the latest value up to 2 digits specified for the date for the all the investments
   * present in the portfolio.</p>
   *
   * @param date in Date for which values needs to be fetched.
   * @return value containing total portfolio value for the specified date.
   */
  double getValue(Date date);

  /**
   * <p>This method returns the total cost of investment made by user in this portfolio. It
   * therefore sums up all the investment cost values and returns the final values.</p>
   *
   * @param date in Date for which cost of investment needs to be fetched.
   * @return cost representing total portfolio cost up to the specified date.
   */
  double getCostBasis(Date date);

  /**
   * <p>Adds investment object in the existing list of portfolios and returns the newly updated
   * Portfolio.</p>
   *
   * @param investment of type IInvestment containing Investment object that needs to be added.
   * @return portfolio of type IPortfolio consisting of updated portfolio.
   */
  IPortfolio addInvestments(IInvestment investment);

  /**
   * <p>This method returns list of investments. The implementation should ensure that the
   * investment list is not updatable.</p>
   *
   * @return investmentList containing existing investments in the portfolio.
   */
  List<IInvestment> getInvestments();

}
