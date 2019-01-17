package howtoinvestfordummies.model;

/**
 * This interface helps in providing method implementation for supporting new type of
 * investment
 * strategy. Any new strategy type can implement this interface and configure Recurring
 * portfolio
 * implementation logic for the same.
 */
public interface HighLevelInvestmentStrategy {
  /**
   * This method initiates HighLevelInvestmentStrategy. Depending upon what parameters
   * are required
   * to set up this strategy, implementation of this class can override this method to
   * initiate the
   * transaction.
   *
   * @param details containing values required to set up investment strategy.
   * @return portfolio object created by this strategy.
   */
  IPortfolio initiate(Transaction details);
}
