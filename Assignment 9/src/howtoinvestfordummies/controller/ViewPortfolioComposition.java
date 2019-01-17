package howtoinvestfordummies.controller;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.view.IInvestmentView;

/**
 * Class to view composition of the portfolio in the model.
 */
public class ViewPortfolioComposition extends StockInvestmentCommandImpl {
  /**
   * Constructs object of this class.
   *
   * @param view of type Appendable.
   */
  ViewPortfolioComposition(IInvestmentView view) {
    super(view);
  }

  /**
   * This method takes the required model to find output data required to transmit back to user. If
   * the input is l, it will list all the existing portfolio names. If the input is c, it will
   * assume that user is aware of portfolio names and it will continue buying stock.
   *
   * @param model of type StockInvestmentStrategies Interface.
   */
  @Override
  public void execute(StockInvestmentStrategies model) {
    while (true) {
      view.transmitMessage("Enter Portfolio name to view or press # to return to the menu.");
      String input = view.read();
      if (input.equalsIgnoreCase("#")) {
        break;
      }
      try {
        IPortfolio portfolio = model.viewPortfolio(input);
        view.transmitMessage(portfolio.toString());

      } catch (IllegalArgumentException e) {
        view.transmitMessage(e.getMessage());
      }
    }
  }
}
