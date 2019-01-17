package howtoinvestfordummies.controller;

import java.util.Scanner;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.StockInvestmentStrategies;

/**
 * Class to view composition of the portfolio in the model.
 */
public class ViewPortfolioComposition extends StockInvestmentCommandImpl {
  /**
   * Constructs object of this class.
   *
   * @param scan of type Scanner.
   * @param out  of type Appendable.
   */
  ViewPortfolioComposition(Scanner scan, Appendable out) {
    super(scan, out);
  }

  /**
   * This method takes the required model to find output data required to transmit back to user. If
   * the input is l, it will list all the existing portfolio names. If the input is c, it will
   * assume that user is aware of portfolio names and it will continue buying stock.
   *
   * @param model of type StockInvestmentStrategies Interface.
   */
  @Override
  public void go(StockInvestmentStrategies model) {
    while (true) {
      transmitMessage("Enter Portfolio name to view or press # to return to the menu.");
      String input = scan.next();
      if (input.equalsIgnoreCase("#")) {
        break;
      }
      try {
        IPortfolio portfolio = model.viewPortfolio(input);
        transmitMessage(portfolio.toString());

      } catch (IllegalArgumentException e) {
        transmitMessage(e.getMessage());
      }

    }
  }
}
