package howtoinvestfordummies.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.StockInvestmentStrategies;

/**
 * Represents implementation class for {@link StockInvestmentCommand}. It performs all the common
 * applications at this level and subclasses of {@link StockInvestmentCommand} inherits method
 * provided by this class.
 */
public abstract class StockInvestmentCommandImpl implements StockInvestmentCommand {
  /**
   * Required to transmit output.
   */
  protected Appendable out;
  /**
   * Required to scan inputs provided by user.
   */
  protected Scanner scan;

  /**
   * Constructor of this class.
   *
   * @param scan of type Scanner.
   * @param out  of type Appendable.
   */
  StockInvestmentCommandImpl(Scanner scan, Appendable out) {
    this.scan = scan;
    this.out = out;
  }

  @Override
  public abstract void execute(StockInvestmentStrategies m);

  /**
   * Transmits message sent by common command classes back to the controller.
   *
   * @param message in string.
   */
  protected void transmitMessage(String message) {
    try {
      out.append(message);
      out.append("\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Exception encountered in transmitting "
              + "message");
    }

  }

  /**
   * Converts String date and time to an object of type Date.
   *
   * @param inputDate in string representing date inputted by user.
   * @param inputTime in string representing time inputted by user.
   * @return object of type Date.
   */
  protected Date formatDate(String inputDate, String inputTime) {
    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    Date providedDate = new Date();
    try {
      providedDate = dateFormat.parse(inputDate + " " + inputTime + ":00");
    } catch (ParseException e) {
      transmitMessage(e.getMessage());
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
    sb.append("Portfolios created by you:\n");
    for (int i = 1; i <= portfolioList.size(); i++) {
      sb.append(i);
      sb.append(".");
      sb.append(portfolioList.get(i - 1).getName());
      sb.append("\n");
    }
    return sb.toString();
  }
}
