package howtoinvestfordummies.controller;

import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.view.IInvestmentView;

/**
 * Class to operate creation of portfolio in the model.
 */
public class CreatePortfolio extends StockInvestmentCommandImpl {

  /**
   * Constructs object of this class.
   *
   * @param view of type IInvestmentView.
   */
  CreatePortfolio(IInvestmentView view) {
    super(view);
  }

  /**
   * This method takes model object to create portfolio with a required name. If the portfolio name
   * is unique, it will allow user to create portfolio.
   *
   * @param model of type StockInvestmentStrategies interface.
   */
  @Override
  public void execute(StockInvestmentStrategies model) {
    while (true) {
      try {
        view.transmitMessage("Enter Portfolio name to create or press # to return to "
                + "the menu.");
        String portfolioName = view.read();
        if (portfolioName.equalsIgnoreCase("#")) {
          break;
        }
        model.createPortfolio(portfolioName);
        view.transmitMessage("Portfolio " + portfolioName + " created successfully!");
      } catch (Exception e) {
        view.transmitMessage(e.getMessage());
      }
    }
  }
}
