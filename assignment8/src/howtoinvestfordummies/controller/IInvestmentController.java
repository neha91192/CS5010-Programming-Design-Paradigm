package howtoinvestfordummies.controller;

import howtoinvestfordummies.model.StockInvestmentStrategies;

/**
 * This is an interface for Investment related operations from controller. The role of this
 * interface is to provide an entry point for the view of the application to interact with the
 * system. This controller also interacts with the model to find required operations related data
 * requested from the view.
 */
public interface IInvestmentController {
  /**
   * Initiates controller activity whenever this method is called.
   *
   * @param model of type StockInvestmentStrategies.
   * @throws IllegalArgumentException in case invalid model is provided.
   */
  void start(StockInvestmentStrategies model) throws IllegalArgumentException,
          IllegalStateException;
}
