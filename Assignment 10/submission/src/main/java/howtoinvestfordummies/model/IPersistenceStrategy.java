package howtoinvestfordummies.model;

import java.util.Map;

/**
 * This class determines strategy used to adopt persistence in the application. It could be file,
 * database etc.
 */
public interface IPersistenceStrategy {
  /**
   * The general implementation to save model in a given data store "name" is provided with this
   * method.
   *
   * @param model to be saved.
   * @param name  of the data store.
   */
  void save(InvestmentStrategies model, String name);

  /**
   * This method provides general method to retrieve investment data from the store "name".
   *
   * @param name of the data store.
   * @return investment data in Map.
   */
  Map<String, IPortfolio> restore(String name);
}