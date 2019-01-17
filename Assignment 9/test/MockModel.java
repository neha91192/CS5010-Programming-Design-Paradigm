import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.InvestmentStrategyType;
import howtoinvestfordummies.model.Portfolio;
import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.model.Transaction;

public class MockModel implements StockInvestmentStrategies {

  private Map<String, IPortfolio> portfolios;
  private Date invalidDate;
  private String portfolioName;
  private String appState;

  /**
   * Constructor for mock model.
   */
  public MockModel(Date invalidDate, String portfolioName) {
    portfolios = new HashMap<>();
    this.invalidDate = invalidDate;
    this.portfolioName = portfolioName;
    this.appState = "";
  }

  @Override
  public void buySharesOfStock(Date date, double amount, String symbol, String
          portfolioName) throws IllegalArgumentException {
    if (date.compareTo(invalidDate) == 0) {
      throw new IllegalArgumentException("Trading is closed for this time. Please try "
              + "again later");
    }
    if (!portfolios.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio Not found");
    }
    this.appState = "Shares bought";
  }

  @Override
  public void buyStocksWithCommission(Transaction transaction) throws
          IllegalArgumentException {
    if (!portfolios.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio Not found");
    }
    this.appState = "Shares bought";
  }

  @Override
  public IPortfolio createPortfolio(String portfolioName) throws Exception {
    if (portfolioName.equals(this.portfolioName)) {
      throw new Exception("Portfolio with this name is already present");
    }

    IPortfolio portfolio = new Portfolio(portfolioName);
    portfolios.put(portfolioName, portfolio);
    this.appState = "Portfolio created";
    return portfolio;
  }

  @Override
  public List<IPortfolio> getPortfolios() {
    List<IPortfolio> portfolioList = new ArrayList<>();
    for (String portfolioName : portfolios.keySet()) {
      portfolioList.add(new Portfolio(portfolioName,
              portfolios.get(portfolioName).getInvestments()));
    }
    this.appState = "Getting portfolio";
    return portfolioList;
  }

  @Override
  public IPortfolio viewPortfolio(String portfolioName) {
    IPortfolio portfolio = new Portfolio(portfolioName);
    this.appState = "Viewing portfolio";
    return portfolio;
  }

  @Override
  public String checkPortfolioStatus(Date date, String portfolioName)
          throws IllegalArgumentException {
    if (date.compareTo(invalidDate) == 0) {
      throw new IllegalArgumentException("Trading is closed for this time. Please try "
              + "again later");
    }
    if (!portfolios.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio not found");
    }
    return appState;
  }

  @Override
  public List<InvestmentStrategyType> getHighLevelInvestments() {
    List<IPortfolio> portfolioList = new ArrayList<>();
    for (String portfolioName : portfolios.keySet()) {
      portfolioList.add(new Portfolio(portfolioName,
              portfolios.get(portfolioName).getInvestments()));
    }
    this.appState = "Getting portfolio";
    return null;
  }

  @Override
  public IPortfolio initiateHighLevelInvestment(Transaction transaction,
                                                InvestmentStrategyType type) {
    IPortfolio portfolio = new Portfolio(transaction.getPortfolioName());
    this.appState = "Viewing high level portfolio";
    return portfolio;
  }
}
