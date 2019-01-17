package howtoinvestfordummies.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.InvestmentStrategyType;
import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.model.Transaction;
import howtoinvestfordummies.view.IInvestmentGUIView;

/**
 * Represents controller implementation for {@link IInvestmentController}. The main
 * function of this
 * class is take the request from view as form inputs from GUI and performs required
 * operations. The status of the operation is then transmitted back to the graphical
 * view as a display for the user.
 */
public class InvestmentGUIController implements IInvestmentController, ActionListener {

  /**
   * Map containing known commands and line of action for those commands in the form of
   * Function
   * object.
   */
  private IInvestmentGUIView view;
  private StockInvestmentStrategies model;

  /**
   * Represents constructor of this class. It initialises appendable and readable
   * object, scanner
   * and loads command data into the map.
   *
   * @param view  of type IInvestmentView.
   * @param model of type StockInvestmentStrategies.
   * @throws IllegalArgumentException in case the readable or appendable objects are null.
   */
  public InvestmentGUIController(IInvestmentGUIView view, StockInvestmentStrategies model)
          throws IllegalArgumentException {
    if (view == null || model == null) {
      throw new IllegalArgumentException("Please provide valid View or Model object");
    }
    this.view = view;
    this.model = model;
    this.view.setListener(this);
  }

  @Override
  public void start() {
    //set this as the command callback
    this.view.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "create":
        createPortfolio();
        break;

      case "buy":
        buyStock();
        break;

      case "determine":
        determineCostBasisAndValue();
        break;
      case "view":
        viewPortfolio();
        break;

      case "highlevel":
        createHighLevelStrategy();
        break;

      case "save":
        model.save("user");
        view.setSaveFileName();
        break;

      case "retrieve":
        List<IPortfolio> portfolios = model.retrieveInvestmentStrategies("user");
        StringBuilder sb = new StringBuilder();
        for(IPortfolio portfolio: portfolios){
          sb.append(portfolio.getPortfolioData().getPortfolioName()+'\n');
        }
        view.setViewDisplay(sb.toString());
        break;

      default:
        break;
    }
  }

  /**
   * Helper method to create portfolio using model and update the graphical view
   * accordingly.
   */
  private void createPortfolio() {
    String portfolioName = view.getCreatePortfolioInputString();
    try {
      IPortfolio p = model.createPortfolio(portfolioName);
      view.setCreatePortfolioDisplay("Portfolio " + p.getName()
              + " created successfully");
    } catch (Exception ex) {
      view.setCreatePortfolioDisplay("Portfolio name already present");
    }
    view.clearCreatePortfolioInputString();
  }

  /**
   * Helper method to buy stock from the model and update the graphical view accordingly.
   */
  private void buyStock() {
    List<String> inputs = view.getBuyStockInputString();
    String portfolioName = inputs.get(0);
    String symbolInput = inputs.get(1);
    Double amount = 0.0;
    Double commission = 0.0;
    Date date = view.getBuyDate();

    List<String> stockSymbols = new ArrayList<>();
    Map<String, Double> commissionMap = new HashMap<>();
    Transaction transaction;

    try {
      amount = Double.parseDouble(inputs.get(2));
    } catch (IllegalArgumentException iae) {
      view.setBuyStockDisplay("Incorrect amount");
    }
    String addCommission = inputs.get(3);
    try {
      commission = Double.parseDouble(inputs.get(4));
    } catch (IllegalArgumentException iae) {
      view.setBuyStockDisplay("Incorrect amount");
    }
    if (addCommission.equalsIgnoreCase("true")) {
      stockSymbols.add(symbolInput);
      commissionMap.put(symbolInput, commission);
      transaction = Transaction.getBuilder()
              .stocks(stockSymbols)
              .portfolioName(portfolioName)
              .amount(amount)
              .transactionStartDate(date)
              .transactionEndDate(date)
              .commissionFees(commissionMap)
              .build();
      model.buyStocksWithCommission(transaction);
    } else {
      model.buySharesOfStock(date, amount, symbolInput, portfolioName);
    }
    view.setBuyStockDisplay("Stocks bought successfully");
    view.clearBuyStockInputString();
  }


  /**
   * Helper method to determine the cost basis and value of a portfolio from the model
   * and update the graphical view accordingly.
   */
  private void determineCostBasisAndValue() {
    String portfolioName = view.getDetermineCostBasisPortfolio();
    Date date = view.getDetermineDate();
    String output = model.checkPortfolioStatus(date, portfolioName);
    view.setDetermineCostBasisDisplay(output);
    view.clearDetermineCostBasisInputString();
  }

  /**
   * Helper method to view a portfolio from the model and update the graphical view
   * accordingly.
   */
  private void viewPortfolio() {
    String portfolioName = view.getViewInputString();
    IPortfolio p = model.viewPortfolio(portfolioName);
    view.setViewDisplay(p.toString());
    view.clearViewInputString();
  }

  /**
   * Helper method to create high level investment strategy from the model and update
   * the graphical view accordingly.
   */
  private void createHighLevelStrategy() {
    List<String> inputs;
    String portfolioName = "";
    int numOfStocks = 1;
    String symbolsInput;
    List<String> stockSymbols = new ArrayList<>();
    Double amount = 0.0;
    Map<String, Double> commissionMap = new HashMap<>();
    String addWeights;
    String weightsInput;
    List<Double> weights = new ArrayList<>();
    String addCommission;
    String commissionsInput;
    List<Double> commissions = new ArrayList<>();
    Date startDate = view.getStartDate();
    String setEnd;
    Date endDate = view.getEndDate();
    int frequency = 0;
    Transaction transaction;

    inputs = view.getCreateStrategyInputString();
    portfolioName = inputs.get(0);
    try {
      numOfStocks = Integer.parseInt(inputs.get(1));
    } catch (IllegalArgumentException iae) {
      view.setCreateStrategyDisplay("Incorrect number of stocks");
    }
    symbolsInput = inputs.get(2);
    String[] arrOfSymbols = symbolsInput.split(",");
    for (String s : arrOfSymbols) {
      stockSymbols.add(s);
    }
    if (stockSymbols.size() != numOfStocks) {
      view.setCreateStrategyDisplay("Provide exactly " + numOfStocks + " stocks");
    }
    addWeights = inputs.get(3);
    weightsInput = inputs.get(4);
    double weight = Math.round((100.0 / numOfStocks) * 100.0) / 100.0;
    String[] arrOfWeights = weightsInput.split(",");
    for (int i = 0; i < numOfStocks; i++) {
      weights.add(weight);
    }
    if (addWeights.equalsIgnoreCase("true")) {
      for (String s : arrOfWeights) {
        try {
          weight = Double.parseDouble(s);
        } catch (IllegalArgumentException iae) {
          view.setCreateStrategyDisplay("Incorrect number of stocks");
        }
        weights.add(weight);
      }
    }
    try {
      amount = Double.parseDouble(inputs.get(5));
    } catch (IllegalArgumentException iae) {
      view.setCreateStrategyDisplay("Incorrect amount");
    }
    addCommission = inputs.get(6);
    commissionsInput = inputs.get(7);
    double commission = 0.0;
    String[] arrOfCommissions = commissionsInput.split(",");
    for (int i = 0; i < numOfStocks; i++) {
      commissions.add(commission);
    }
    if (addCommission.equalsIgnoreCase("true")) {
      for (String s : arrOfCommissions) {
        try {
          commission = Double.parseDouble(s);
        } catch (IllegalArgumentException iae) {
          view.setCreateStrategyDisplay("Incorrect commission");
        }
        commissions.add(commission);
      }
    }
    for (int i = 0; i < numOfStocks; i++) {
      commissionMap.put(stockSymbols.get(i), commissions.get(i));
    }
    setEnd = inputs.get(8);
    try {
      frequency = Integer.parseInt(inputs.get(9));
    } catch (IllegalArgumentException iae) {
      view.setCreateStrategyDisplay("Incorrect frequency");
    }
    if (setEnd.equalsIgnoreCase("false")) {
      transaction = Transaction.getBuilder()
              .stocks(stockSymbols)
              .portfolioName(portfolioName)
              .amount(amount)
              .stockWeights(weights)
              .transactionStartDate(startDate)
              .frequencyValue(frequency)
              .commissionFees(commissionMap)
              .build();
    } else {
      transaction = Transaction.getBuilder()
              .stocks(stockSymbols)
              .portfolioName(portfolioName)
              .amount(amount)
              .stockWeights(weights)
              .transactionStartDate(startDate)
              .transactionEndDate(endDate)
              .frequencyValue(frequency)
              .commissionFees(commissionMap)
              .build();
    }

    IPortfolio p = model.initiateHighLevelInvestment(transaction,
            InvestmentStrategyType.DOLLAR_COST_AVERAGING);

    view.setCreateStrategyDisplay(p.toString());
    view.clearCreateStrategyInputString();
  }
}
