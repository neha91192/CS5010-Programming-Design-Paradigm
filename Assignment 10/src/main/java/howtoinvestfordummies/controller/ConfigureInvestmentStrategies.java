package howtoinvestfordummies.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.InvestmentStrategyType;
import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.model.Transaction;
import howtoinvestfordummies.view.IInvestmentView;

/**
 * Class to configure an existing portfolio in the model to support High Level
 * Investments.
 */
public class ConfigureInvestmentStrategies extends StockInvestmentCommandImpl {

  private InvestmentStrategyType investmentType;

  /**
   * Constructs object of this class.
   *
   * @param view of type IInvestmentView.
   */
  ConfigureInvestmentStrategies(IInvestmentView view) {
    super(view);
  }

  /**
   * This method takes model object to initiate high level investemnt strategy.
   *
   * @param model of type StockInvestmentStrategies interface.
   */
  @Override
  public void execute(StockInvestmentStrategies model) {
    selectHighLevelInvestmentStrategies(model);
    while (true) {
      view.transmitMessage("Enter name of the portfolio to start"
              + " recurring investment");
      String portfolioName = view.read();
      if (portfolioName.equalsIgnoreCase("#")) {
        return;
      }
      int numberOfStocks = getNumberOfStocks();
      view.transmitMessage("Enter " + numberOfStocks + " Stock symbol/s to add");
      List<String> stockSymbols = new ArrayList<>(numberOfStocks);
      for (int i = 1; i <= numberOfStocks; i++) {
        stockSymbols.add(view.read());
      }

      List<Double> stockWeights = askForWeights(numberOfStocks, stockSymbols);

      double totalAmount = getAmount("Enter the total amount you want to invest on this"
              + " group of stocks");
      Map<String, Double> stockCommissions = getCommissions(numberOfStocks, stockSymbols);
      Date startDate = formatStartDate();
      Date endDate = formatEndDate();
      int frequencyInterval = getFrequencyInterval();
      Transaction transaction = Transaction.getBuilder()
              .stocks(stockSymbols)
              .portfolioName(portfolioName)
              .amount(totalAmount)
              .stockWeights(stockWeights)
              .transactionStartDate(startDate)
              .transactionEndDate(endDate)
              .frequencyValue(frequencyInterval)
              .commissionFees(stockCommissions)
              .build();
      try {
        String output = "Congratulations, you have successfully started this "
                + "recurring investment";
        IPortfolio portfolio = model.initiateHighLevelInvestment(transaction,
                investmentType);
        view.transmitMessage(output);
        view.transmitMessage(portfolio.toString());
      } catch (IllegalArgumentException e) {
        view.transmitMessage(e.getMessage());
      }
      view.transmitMessage("Press # to execute back or press 'c' to check value for other"
              + " portfolio");
    }


  }

  /**
   * Will ask user to enter the number of stocks he wants to group for recurring
   * investment. If the
   * input is not a valid number will prompt user to enter correct number.
   *
   * @return number of stocks to be grouped for recurring investment as Integer
   */
  private int getNumberOfStocks() {
    int numberOfStocks = 0;
    while (true) {
      view.transmitMessage("Enter the number of Stocks to add in this investment");
      String numOfStocks = view.read();
      if (numOfStocks.equalsIgnoreCase("#")) {
        break;
      }
      try {
        numberOfStocks = Integer.parseInt(numOfStocks);
        if (numberOfStocks < 1) {
          view.transmitMessage("Negative number is invalid, continue");
          continue;
        }
        break;
      } catch (NumberFormatException ne) {
        view.transmitMessage("Input is not a number, continue");
      }
    }
    return numberOfStocks;
  }

  /**
   * Will ask user if he wishes to add weights for each of the stock he has added in
   * this group. If
   * the input is not a valid, will prompt user to enter correct input.
   *
   * @param numberOfStocks is the count of stock symbols in the group
   * @param stockSymbols   are the list of stock symbols in String
   * @return list of weights for each stock in double
   */
  private List<Double> askForWeights(int numberOfStocks, List<String> stockSymbols) {
    boolean presetStockWeights = addNewValue("Do you want to provide stock weights "
            + "(preset is equal weights for each stock)? Y/N");

    List<Double> stockWeights = new ArrayList<>();

    double presetWeight = Math.round((100.0 / numberOfStocks) * 100.0) / 100.0;
    for (String stock : stockSymbols) {
      stockWeights.add(presetWeight);
    }
    if (presetStockWeights) {
      while (true) {
        stockWeights = getStockWeights(numberOfStocks, stockSymbols);
        int total = 0;
        for (Double stockWeight : stockWeights) {
          total += stockWeight;
        }
        if (total != 100) {
          view.transmitMessage("Weights do not add up to 100, please enter again.");
          continue;
        }
        break;
      }
    }
    return stockWeights;
  }

  /**
   * Will ask user to enter the weights for each of the stock he has added in this
   * group. If the
   * input is not a valid number, or If the weights do not add up to 100 will prompt
   * user to enter
   * correct weights.
   *
   * @param numberOfStocks is the total number of stocks in the group
   * @param stockSymbols   are the stocks symbols in the group
   * @return a list of weights for each stock in the group in Integer
   */
  private List<Double> getStockWeights(int numberOfStocks, List<String> stockSymbols) {
    List<Double> stockWeights = new ArrayList<>(numberOfStocks);
    double stockWeight;
    view.transmitMessage("Enter " + numberOfStocks + " weights (in %age) for the Stocks"
            + " in group");
    for (int i = 1; i <= numberOfStocks; i++) {
      while (true) {
        view.transmitMessage(i + ". Weight for " + stockSymbols.get(i - 1) + ": ");
        String strWeight = view.read();
        try {
          stockWeight = Integer.parseInt(strWeight);
          if (stockWeight < 1) {
            view.transmitMessage("Negative weight is invalid, continue");
            continue;
          }
          stockWeights.add(stockWeight);
          break;
        } catch (NumberFormatException ne) {
          view.transmitMessage("Input is not a number, continue");
        }
      }
    }
    return stockWeights;
  }

  /**
   * Will ask user to enter the commissions for each of the stock he has added in this
   * group. If the
   * input is not a valid number, will prompt user to enter correct weights.
   *
   * @param numberOfStocks is the total number of stocks in the group
   * @param stockSymbols   are the stocks symbols in the group
   * @return a list of commissions for each stock in the group in Integer
   */
  private Map<String, Double> getCommissions(int numberOfStocks, List<String>
          stockSymbols) {
    Map<String, Double> stockCommissions = new HashMap<>(numberOfStocks);
    for (int i = 1; i <= numberOfStocks; i++) {
      while (true) {
        boolean addComm = addNewValue("Do you want to add commission for "
                + stockSymbols.get(i - 1) + "? Y/N");
        double stockCommission;
        if (addComm) {
          view.transmitMessage("Enter commission (in USD) for " + stockSymbols.get(i - 1));
          String strCommission = view.read();
          try {
            stockCommission = Double.parseDouble(strCommission);
            if (stockCommission < 0) {
              view.transmitMessage("Negative commission is invalid, continue");
              continue;
            }
            stockCommissions.put(stockSymbols.get(i - 1), stockCommission);
            break;
          } catch (NumberFormatException ne) {
            view.transmitMessage("Input is not a number, continue");
          }
        } else {
          break;
        }
      }
    }
    return stockCommissions;
  }

  /**
   * Will ask user to enter the frequency interval (in DAYS) in which he wishes to
   * invest on the
   * group of stocks for recurring investment. If the input is not a valid number, will
   * prompt user
   * to enter correct number.
   *
   * @return frequency interval (in DAYS) of investment on the group of stocks as Integer
   */
  private int getFrequencyInterval() {
    int frequencyInterval;
    while (true) {
      view.transmitMessage("Enter the frequency interval (in DAYS) for investing on "
              + "this group of stocks");
      String frequency = view.read();
      try {
        frequencyInterval = Integer.parseInt(frequency);
        if (frequencyInterval < 1) {
          view.transmitMessage("Negative frequency is invalid, continue");
          continue;
        }
        break;
      } catch (NumberFormatException ne) {
        view.transmitMessage("Input is not a number, continue");
      }
    }
    return frequencyInterval;
  }


  /**
   * Converts String date to an object of type Date.
   *
   * @return object of type Date.
   */
  private Date formatStartDate() {
    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    Date providedDate;
    while (true) {
      view.transmitMessage("Enter the Start Date for recurring investment in "
              + "(MM-dd-YYYY format)");
      String inputDate = view.read();
      try {
        providedDate = dateFormat.parse(inputDate + " 00:00:00");
        break;
      } catch (ParseException e) {
        view.transmitMessage(e.getMessage());
      }
    }
    return providedDate;
  }

  /**
   * Converts String date to an object of type Date.
   *
   * @return object of type Date.
   */
  private Date formatEndDate() {
    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    Date providedDate;
    while (true) {
      view.transmitMessage("Enter the End Date for recurring investment in (MM-dd-YYYY "
              + "format) or Enter NA if you want the investment to be Ongoing");
      String inputDate = view.read();
      try {
        if (inputDate.equalsIgnoreCase("NA")) {
          providedDate = null;
        } else {
          providedDate = dateFormat.parse(inputDate + " 00:00:00");
        }
        break;
      } catch (ParseException e) {
        view.transmitMessage(e.getMessage());
      }
    }
    return providedDate;
  }

  /**
   * Provides option to select the investment strategy out of all available high-level
   * strategies.
   *
   * @param model of type StockInvestmentStrategies Interface.
   */
  private void selectHighLevelInvestmentStrategies(StockInvestmentStrategies model) {
    view.transmitMessage("Here is/are our investment strategies. Select the "
            + "corresponding option for more details");
    List<InvestmentStrategyType> types = model.getHighLevelInvestments();
    int i = 0;
    for (InvestmentStrategyType type : types) {
      view.transmitMessage(i + 1 + "." + " " + type.getDisplayValue());
    }
    while (true) {
      String input = view.read();
      if (input.equals("#")) {
        break;
      }
      int option = Integer.parseInt(input);
      if (option > types.size() || option < 0) {
        view.transmitMessage("Incorrect value selected");
      } else {
        investmentType = types.get(option - 1);
        break;
      }
    }
  }
}
