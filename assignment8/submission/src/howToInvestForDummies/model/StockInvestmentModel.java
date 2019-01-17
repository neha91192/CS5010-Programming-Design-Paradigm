package howtoinvestfordummies.model;

import java.util.Date;

import howtoinvestfordummies.model.adapter.IStockAdapter;
import howtoinvestfordummies.model.adapter.LocalStockAdapterImpl;

public class StockInvestmentModel extends InvestmentStrategiesModel implements StockInvestmentStrategies {

  private IStockAdapter adapter;
  private ITradingInformation tradingInformation;

  public StockInvestmentModel() {
    adapter = LocalStockAdapterImpl.getInstance();
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
  public void buySharesOfStock(Date date, double amount, String stockSymbol, String portfolioName)
          throws IllegalArgumentException {
    if (!isPortfolioPresent(portfolioName)) {
      throw new IllegalArgumentException("Portfolio Not found");
    }
    if (!tradingInformation.isTradingHours(date)) {
      throw new IllegalArgumentException("Trading is closed for this time. Please try again later");
    }
    if (amount <= TradingInformation.MINIMUM_INVESTMENT) {
      throw new IllegalArgumentException("Please enter a valid amount.");
    }
    double pricePerShare;
    try {
      pricePerShare = adapter.getStockPriceForBuying(stockSymbol, date);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }

    double noOfShares = amount / pricePerShare;
    noOfShares = Math.round(noOfShares * 100);
    noOfShares = noOfShares / 100;
    IInvestment stockObj = new Stock(stockSymbol, noOfShares, date, amount);

    IPortfolio portfolio = portfolios.get(portfolioName);
    portfolios.put(portfolioName, portfolio.addInvestments(stockObj));
  }
}
