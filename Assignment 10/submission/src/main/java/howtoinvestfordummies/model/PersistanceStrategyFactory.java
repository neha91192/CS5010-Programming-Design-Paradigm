package howtoinvestfordummies.model;

public class PersistanceStrategyFactory {

  /**
   * Private constructor of this class.
   */
  private PersistanceStrategyFactory() {

  }

  /**
   * Returns the implementation of {@link HighLevelInvestmentStrategy} for the provided
   * type. The
   * type should match the string value provided in {@link InvestmentStrategyType} for the
   * respective Strategy type.
   *
   * @param type in String representing investment strategy.
   * @return strategy belonging to HighLevelInvestmentStrategy implementations.
   */
  public static IPersistenceStrategy getPersistanceStrategy(String type) {
    IPersistenceStrategy strategy = null;
    if (type.equals("FILE")) {
      strategy = new FileStrategy();
    }
    return strategy;
  }
}

