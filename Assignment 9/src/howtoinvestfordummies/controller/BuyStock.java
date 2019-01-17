package howtoinvestfordummies.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.model.Transaction;
import howtoinvestfordummies.view.IInvestmentView;

/**
 * Class to operate buying stock at a particular date related commands.
 */
public class BuyStock extends StockInvestmentCommandImpl {

  /**
   * Constructs object of this class.
   *
   * @param view of type IInvestmentView.
   */
  BuyStock(IInvestmentView view) {
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
    view.transmitMessage("Enter 'l' to list existing portfolio for which you want to "
            + "buy stock "
            + "or 'c' to continue if you know portfolio name or press # to return to "
            + "main menu");
    String input = view.read();
    switch (input.toLowerCase()) {
      case "l":
        String output = formatPortfolioNames(model.getPortfolios());
        if (output.equals("")) {
          view.transmitMessage("No portfolio found. Please create one from main menu.");
          break;
        }
        view.transmitMessage(output);
        buy(model);
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
        view.transmitMessage("Incorrect option selected.");
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
    view.transmitMessage("You are in Stock buying menu. At any time you want to return "
            + "to the main"
            + " menu, press #");
    while (true) {
      view.transmitMessage("Enter name of the portfolio to buy shares:");
      String name = view.read();
      if (name.equals("#")) {
        break;
      }
      view.transmitMessage("Please enter Company symbol to buy shares (GOOG, FB, MSFT, "
              + "SBUX, AAPL):");
      String symbol = view.read();
      if (symbol.equals("#")) {
        break;
      }
      double cost = getAmount("Please enter the amount in USD to invest in shares of "
              + "this stock");
      boolean addComm = addNewValue("Do you want to add commission? Y/N");
      double commission = 0.0;
      if (addComm) {
        commission = getAmount("Please enter the commission in USD for this stock");
      }
      Date dateValue = formatDatetime();
      try {
        view.transmitMessage("Please wait while we buy shares for you at the lowest "
                + "price..");
        if (addComm) {
          List<String> stockSymbols = new ArrayList<>();
          stockSymbols.add(symbol);
          Map<String, Double> commissionFees = new HashMap<>();
          commissionFees.put(symbol, commission);
          Transaction transaction = Transaction.getBuilder()
                  .stocks(stockSymbols)
                  .portfolioName(name)
                  .amount(cost)
                  .transactionStartDate(dateValue)
                  .transactionEndDate(dateValue)
                  .commissionFees(commissionFees)
                  .build();
          model.buyStocksWithCommission(transaction);
        } else {
          model.buySharesOfStock(dateValue, cost, symbol, name);
        }
        IPortfolio portfolio = model.viewPortfolio(name);
        view.transmitMessage("Stocks bought successfully!");
        view.transmitMessage("Current value of your portfolio:");
        view.transmitMessage(portfolio.toString());
      } catch (IllegalArgumentException e) {
        view.transmitMessage(e.getMessage());
      }
    }
  }
}