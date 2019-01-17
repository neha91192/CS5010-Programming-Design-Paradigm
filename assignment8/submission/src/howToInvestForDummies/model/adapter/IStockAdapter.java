package howtoinvestfordummies.model.adapter;

import java.util.Date;

/**
 * Adapter interface to extra stock data from various external resources.
 */
public interface IStockAdapter {
  /**
   * Returns buying price for the symbol at a given date.
   *
   * @param symbol of the company for which stock needs to be bought.
   * @param date   for which price needs to be fetched.
   * @return price of type double.
   * @throws Exception in case price was not found or symbol is incorrect.
   */
  double getStockPriceForBuying(String symbol, Date date) throws Exception;

  /**
   * Returns stock price of a company for calculating values at a particular date.
   *
   * @param stockSymbol of the company for which stock value needs to be found.
   * @param date        for which price needs to be fetched.
   * @return price of type double.
   * @throws Exception in case price was not found or symbol is incorrect.
   */
  double getStockPriceForCalculatingValue(String stockSymbol, Date date) throws Exception;

}
