package howtoinvestfordummies.controller;

import java.util.Scanner;

import howtoinvestfordummies.model.StockInvestmentStrategies;

/**
 * Class to operate creation of portfolio in the model.
 */
public class CreatePortfolio extends StockInvestmentCommandImpl {

  /**
   * Constructs object of this class.
   *
   * @param scan of type Scanner.
   * @param out  of type Appendable.
   */
  CreatePortfolio(Scanner scan, Appendable out) {
    super(scan, out);
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
        transmitMessage("Enter Portfolio name to create or press # to return to the menu.");
        String portfolioName = scan.next();
        if (portfolioName.equalsIgnoreCase("#")) {
          break;
        }
        model.createPortfolio(portfolioName);
        transmitMessage("Portfolio " + portfolioName + " created successfully!");
      } catch (Exception e) {
        transmitMessage(e.getMessage());
      }
    }
  }
}
