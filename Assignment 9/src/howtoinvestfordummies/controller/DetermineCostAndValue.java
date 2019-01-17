package howtoinvestfordummies.controller;

import java.util.Date;

import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.view.IInvestmentView;

/**
 * Class to operate cost and value fetch for a given date from the model.
 */
public class DetermineCostAndValue extends StockInvestmentCommandImpl {
  /**
   * Constructs object of this class.
   *
   * @param view of type IInvestmentView.
   */
  DetermineCostAndValue(IInvestmentView view) {
    super(view);
  }

  /**
   * This method takes model object to fetch cost and investment values of portfolio with a required
   * name. If the portfolio name is unique, it will allow user to see values in the portfolio.
   *
   * @param model of type StockInvestmentStrategies interface.
   */
  @Override
  public void execute(StockInvestmentStrategies model) {
    view.transmitMessage("Enter 'l' to list existing portfolio for which you want to "
            + "find cost and"
            + "value or 'c' to continue if you know portfolio name or # to execute back");
    while (true) {
      String input = view.read();
      if (input.equals("#")) {
        break;
      }
      if (!input.equalsIgnoreCase("c") && !input.equalsIgnoreCase("l")) {
        view.transmitMessage("Incorrect option selected");
      }
      if (input.equalsIgnoreCase("l")) {
        String list = formatPortfolioNames(model.getPortfolios());
        view.transmitMessage(list);
      }
      if (input.equalsIgnoreCase("c")) {
        view.transmitMessage("Enter name of the portfolio:");
        String name = view.read();
        Date dateValue = formatDatetime();
        try {
          String output = model.checkPortfolioStatus(dateValue, name);
          view.transmitMessage(output);
        } catch (IllegalArgumentException e) {
          view.transmitMessage(e.getMessage());
        }
      }
      view.transmitMessage("Press # to execute back or press 'c' to check value for "
              + "other portfolio");
    }
  }
}
