package howtoinvestfordummies.controller;

import howtoinvestfordummies.model.StockInvestmentStrategies;

/**
 * This interface represents common command interface for performing stock investment related
 * operations. Every class implementing this interface would be responsible for performing
 * designated operation whenever execute method is invoked.
 */
public interface StockInvestmentCommand {
  /**
   * Represents the common method referred by the command classes to perform their operations.
   *
   * @param model of type StockInvestmentStrategies.
   */
  void go(StockInvestmentStrategies model);
}
