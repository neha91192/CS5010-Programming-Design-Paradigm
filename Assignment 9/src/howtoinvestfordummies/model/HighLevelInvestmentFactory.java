package howtoinvestfordummies.model;

/**
 * Factory class responsible to return instance of {@link HighLevelInvestmentStrategy}
 * implementation.
 */
public class HighLevelInvestmentFactory {


  /**
   * Private constructor of this class.
   */
  private HighLevelInvestmentFactory() {

  }

  /**
   * Returns the implementation of {@link HighLevelInvestmentStrategy} for the provided type. The
   * type should match the string value provided in {@link InvestmentStrategyType} for the
   * respective Strategy type.
   *
   * @param type in String representing investment strategy.
   * @return strategy belonging to HighLevelInvestmentStrategy implementations.
   */
  public static HighLevelInvestmentStrategy getHighLevelStrategy(String type) {
    HighLevelInvestmentStrategy strategy = null;
    if (type.equals(InvestmentStrategyType.DOLLAR_COST_AVERAGING.name())) {
      strategy = new DollarCostInvestmentStrategy();
    }
    return strategy;
  }
}
