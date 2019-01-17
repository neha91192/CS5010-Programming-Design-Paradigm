package howtoinvestfordummies.model;

/**
 * Added this new Interface to represent Abstract Level High Investment Strategy. This
 * class has
 * been added to support common implementations between different High Level Investment
 * Strategies.
 */
public class AbstractHighLevelInvestmentStrategy implements HighLevelInvestmentStrategy {

  /**
   * Configures recurring portfolio to create a new type of investment strategy.
   *
   * @param details containing values required to set up investment strategy.
   * @return portfolio containing details about the configuration.
   */
  @Override
  public IPortfolio initiate(Transaction details) {
    return null;
  }
}
