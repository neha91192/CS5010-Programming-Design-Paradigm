package howtoinvestfordummies.controller;

import java.util.Date;
import java.util.Scanner;

import howtoinvestfordummies.model.StockInvestmentStrategies;

/**
 * Class to operate cost and value fetch for a given date from the model.
 */
public class DetermineCostAndValue extends StockInvestmentCommandImpl {
  /**
   * Constructs object of this class.
   *
   * @param scan of type Scanner.
   * @param out  of type Appendable.
   */
  DetermineCostAndValue(Scanner scan, Appendable out) {
    super(scan, out);
  }

  /**
   * This method takes model object to fetch cost and investment values of portfolio with a required
   * name. If the portfolio name is unique, it will allow user to see values in the portfolio.
   *
   * @param model of type StockInvestmentStrategies interface.
   */
  @Override
  public void execute(StockInvestmentStrategies model) {
    transmitMessage("Enter 'l' to list existing portfolio for which you want to find cost and"
            + "value or 'c' to continue if you know portfolio name or # to execute back");
    while (true) {
      String input = scan.next();
      if (input.equals("#")) {
        break;
      }
      if (!input.equalsIgnoreCase("c") && !input.equalsIgnoreCase("l")) {
        transmitMessage("Incorrect option selected");
      }
      if (input.equalsIgnoreCase("l")) {
        String list = formatPortfolioNames(model.getPortfolios());
        transmitMessage(list);
      }
      if (input.equalsIgnoreCase("c")) {
        transmitMessage("Enter name of the portfolio:");
        String name = scan.next();
        transmitMessage("Please provide date for checking value in " +
                "(MM-dd-YYYY HH:mm 24-Hrs format)");
        String date = scan.next();
        String time = scan.next();
        Date dateValue = formatDate(date, time);
        try {
          String output = model.checkPortfolioStatus(dateValue,
                  name);
          transmitMessage(output);
        } catch (IllegalArgumentException e) {
          transmitMessage(e.getMessage());
        }
      }
      transmitMessage("Press # to execute back or press 'c' to check value for other portfolio");
    }
  }
}
