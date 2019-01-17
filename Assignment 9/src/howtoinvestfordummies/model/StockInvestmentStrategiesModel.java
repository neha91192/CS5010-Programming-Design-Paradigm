package howtoinvestfordummies.model;

import java.util.Date;

import howtoinvestfordummies.model.adapter.IStockAdapter;
import howtoinvestfordummies.model.adapter.RemoteStockAdapterImpl;

public class StockInvestmentStrategiesModel extends InvestmentStrategiesModel
        implements StockInvestmentStrategies {

  private IStockAdapter adapter;
  private ITradingInformation tradingInformation;

  /**
   * Constructor to create StockInvestmentStrategyModel object.
   */
  public StockInvestmentStrategiesModel() {
    adapter = RemoteStockAdapterImpl.getInstance();
    tradingInformation = new TradingInformation();
  }

  /**
   * For a given valid date verified by {@link ITradingInformation#isTradingHours(Date)}, it buys
   * share for the company provided in stockSymbol at a given amount under a given portfolio. If any
   * of these entries are incorrect, it throws {@link IllegalArgumentException} otherwise returns
   * true.
   *
   * @param date          for which user needs to buy stock in Date.
   * @param amount        in double which describes total cost of investment of user for this
   *                      purchase.
   * @param stockSymbol   unique company ticker symbol in String to identify stock of that company.
   * @param portfolioName name of the Portfolio in String for which user wants to buy stock.
   * @throws IllegalArgumentException in case parameters provided are incorrect.
   */
  @Override
  public void buySharesOfStock(Date date, double amount, String stockSymbol, String
          portfolioName) throws IllegalArgumentException {

    IInvestment stockObj = buyHelper(date, amount, stockSymbol, portfolioName);
    IPortfolio portfolio = portfolios.get(portfolioName);
    portfolios.put(portfolioName, portfolio.addInvestments(stockObj));
  }

  /**
   * For a given valid date verified by {@link ITradingInformation#isTradingHours(Date)}, it buys
   * share for the company provided in stockSymbol at a given amount under a given portfolio, adding
   * commission fee. If any of these entries are incorrect, it throws {@link
   * IllegalArgumentException} otherwise returns true.
   *
   * @param transaction object holds all the details of the stocks to be bought i.e. portfolio name,
   *                    stock symbol, amount to be invested, commission fee, frequency, date.
   * @throws IllegalArgumentException in case parameters provided are incorrect.
   */
  @Override
  public void buyStocksWithCommission(Transaction transaction) throws
          IllegalArgumentException {
    String stock = transaction.getStocks().get(0);
    IInvestment stockObj = buyHelper(transaction.getTransactionStartDate(),
            transaction.getAmount(), stock,
            transaction.getPortfolioName());
    stockObj.setCommissionFees(transaction.getCommissionFees().get(stock));
    IPortfolio portfolio = portfolios.get(transaction.getPortfolioName());
    portfolios.put(transaction.getPortfolioName(), portfolio.addInvestments(stockObj));
  }

  /**
   * This helper method assists in calling adapter to find stock price per share. Once it is
   * identified, it creates a new stock object and returns with the parameters set in the same.
   *
   * @param date          of buying this stock.
   * @param amount        in double.
   * @param stock         in String to buy.
   * @param portfolioName name of the Portfolio,
   * @return object of type IInvestment.
   */
  private IInvestment buyHelper(Date date, double amount, String stock, String
          portfolioName) {
    if (!isPortfolioPresent(portfolioName)) {
      throw new IllegalArgumentException("Portfolio Not found");
    }
    if (!tradingInformation.isTradingHours(date)) {
      throw new IllegalArgumentException("Trading is closed for this time. Please try "
              + "again later");
    }
    if (amount <= TradingInformation.MINIMUM_INVESTMENT) {
      throw new IllegalArgumentException("Please enter a valid amount.");
    }
    double pricePerShare;
    try {
      pricePerShare = adapter.getStockPriceForBuying(stock, date);
    } catch (Exception e) {
      System.out.println("error in  adapter");
      throw new IllegalArgumentException(e.getMessage());
    }

    double noOfShares = amount / pricePerShare;
    noOfShares = Math.round(noOfShares * 100);
    noOfShares = noOfShares / 100;
    return new Stock(stock, noOfShares, date, amount);
  }
}
