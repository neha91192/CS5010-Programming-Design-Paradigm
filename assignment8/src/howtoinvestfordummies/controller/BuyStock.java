package howtoinvestfordummies.controller;

import java.util.Date;
import java.util.Scanner;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.StockInvestmentStrategies;

/**
 * Class to operate buying stock at a particular date related commands.
 */
public class BuyStock extends StockInvestmentCommandImpl {

  /**
   * Constructs object of this class.
   *
   * @param scan of type Scanner.
   * @param out  of type Appendable.
   */
  BuyStock(Scanner scan, Appendable out) {
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
  public void execute(StockInvestmentStrategies model) {
    transmitMessage("Enter 'l' to list existing portfolio for which you want to buy stock "
            + "or 'c' to continue if you know portfolio name or press # to return to main menu");
    String input = scan.next();
    switch (input.toLowerCase()) {
      case "l":
        String output = formatPortfolioNames(model.getPortfolios());
        if (output.equals("")) {
          transmitMessage("No portfolio found. Please create one from main menu.");
          break;
        }
        transmitMessage(output);
        break;
      case "c":
        buy(model);
        break;
      case "q":
        break;
      case "quit":
        break;
      case "#":
        break;
      default:
        transmitMessage("Incorrect option selected.");
        break;
    }
  }

  /**
   * Represents controller logic for buying shares. It interacts with model  to return appropriate
   * message of the buying related operations.
   *
   * @param model of type StockInvestmentStrategies.
   */
  private void buy(StockInvestmentStrategies model) {
    transmitMessage("You are in Stock buying menu. At any time you want to return to the main"
            + " menu, press #");
    while (true) {
      transmitMessage("Enter name of the portfolio to buy shares:");
      String name = scan.next();
      if (name.equals("#")) {
        break;
      }
      transmitMessage("Please enter Company symbol to buy shares (GOOG, FB, MSFT, SBUX, AAPL):");
      String symbol = scan.next();
      if (symbol.equals("#")) {
        break;
      }
      transmitMessage("Please enter amount in USD to invest in shares");
      String amount = scan.next();
      if (amount.equals("#")) {
        break;
      }
      double cost = Double.parseDouble(amount);
      transmitMessage("Please provide date of investment in (MM-dd-YYYY HH:mm 24-Hrs format)");
      String date = scan.next();
      if (date.equals("#")) {
        break;
      }
      String time = scan.next();
      if (time.equals("#")) {
        break;
      }
      Date dateValue = formatDate(date, time);
      try {
        transmitMessage("Please wait while we buy shares for you at the lowest price..");
        model.buySharesOfStock(dateValue, cost, symbol, name);
        IPortfolio portfolio = model.viewPortfolio(name);
        transmitMessage("Stocks bought successfully!");
        transmitMessage("Current value of your portfolio:");
        transmitMessage(portfolio.toString());
      } catch (IllegalArgumentException e) {
        transmitMessage(e.getMessage());
      }

    }

  }
}