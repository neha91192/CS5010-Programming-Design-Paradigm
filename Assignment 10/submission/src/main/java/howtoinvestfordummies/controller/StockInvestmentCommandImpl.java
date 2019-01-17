package howtoinvestfordummies.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.view.IInvestmentView;

/**
 * Represents implementation class for {@link StockInvestmentCommand}. It performs all
 * the common
 * applications at this level and subclasses of {@link StockInvestmentCommand} inherits
 * method
 * provided by this class.
 */
public abstract class StockInvestmentCommandImpl implements StockInvestmentCommand {
  protected IInvestmentView view;

  /**
   * Constructor of this class.
   */
  StockInvestmentCommandImpl(IInvestmentView view) {
    this.view = view;
  }

  @Override
  public abstract void execute(StockInvestmentStrategies m);

  /**
   * Converts String date and time to an object of type Date.
   *
   * @return object of type Date.
   */
  protected Date formatDatetime() {
    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    Date providedDate = new Date();
    while (true) {
      view.transmitMessage("Please provide date of investment in (MM-dd-YYYY HH:mm "
              + "24-Hrs format)");
      String inputDate = view.read();
      String inputTime = view.read();
      try {
        providedDate = dateFormat.parse(inputDate + " " + inputTime + ":00");
        break;
      } catch (ParseException e) {
        view.transmitMessage(e.getMessage());
      }
    }
    return providedDate;
  }

  /**
   * Formats the portfolio data fetched from model to represent the same in view.
   *
   * @param portfolioList representing list of IPortfolio objects.
   * @return string representing names of the portfolios.
   */
  protected String formatPortfolioNames(List<IPortfolio> portfolioList) {
    StringBuilder sb = new StringBuilder();
    if (portfolioList.isEmpty()) {
      view.transmitMessage("You do not have any portfolios created yet. Please create "
              + "by going "
              + "to option 1 from main menu. Press # to go back to the main menu.");
    } else {
      sb.append("Portfolios created by you:\n");
      for (int i = 1; i <= portfolioList.size(); i++) {
        sb.append(i);
        sb.append(".");
        sb.append(portfolioList.get(i - 1).getName());
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  /**
   * Will ask user to enter the amount he wishes to invest on stock. If the input is
   * not a valid
   * amount, will prompt user to enter correct amount.
   *
   * @param message to be displayed to the user
   * @return amount to be invested on the stock as Integer
   */
  protected double getAmount(String message) {
    double amount;
    while (true) {
      view.transmitMessage(message);
      String inputAmount = view.read();
      try {
        amount = Double.parseDouble(inputAmount);
        if (amount < 1) {
          view.transmitMessage("Negative amount is invalid, continue");
          continue;
        }
        break;
      } catch (NumberFormatException ne) {
        view.transmitMessage("Input is not a number, continue");
      }
    }
    return amount;
  }

  /**
   * Will ask user to enter Yes or No if he wishes to add add new value for the stock.
   * If the input
   * is not as expected, will prompt user to enter expected input.
   *
   * @param message of type String.
   * @return boolean value for adding new value or not
   */
  protected boolean addNewValue(String message) {
    boolean addNewValue;
    while (true) {
      view.transmitMessage(message);
      String inputChoice = view.read();
      try {
        if (inputChoice.equalsIgnoreCase("Y")) {
          addNewValue = true;
        } else if (inputChoice.equalsIgnoreCase("N")) {
          addNewValue = false;
        } else {
          view.transmitMessage("Input is not a Y or N, continue");
          continue;
        }
        break;
      } catch (NumberFormatException ne) {
        view.transmitMessage("Input is invalid, continue");
      }
    }
    return addNewValue;
  }
}
