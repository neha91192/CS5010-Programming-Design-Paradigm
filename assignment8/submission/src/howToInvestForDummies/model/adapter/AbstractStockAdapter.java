package howtoinvestfordummies.model.adapter;

import java.util.Calendar;
import java.util.Date;

/**
 * Abstract adapter to perform common implementation for all the adapters implementing {@link
 * IStockAdapter}. Other methods are abstracted at this level.
 */
public abstract class AbstractStockAdapter implements IStockAdapter {
  /**
   * Parses Date object and converts it into required String format. This method converts Date in
   * String of format yyyy-mm-dd.
   *
   * @param date of type Date.
   * @return date value in string.
   */
  protected String getDateInString(Date date) {
    Calendar dateCalendar = Calendar.getInstance();
    dateCalendar.setTime(date);

    int dayInt = dateCalendar.get(Calendar.DATE);
    String day = Integer.toString(dayInt);
    if (dayInt < 10) {
      day = "0" + day;
    }
    int monthInt = dateCalendar.get(Calendar.MONTH) + 1;
    String month = Integer.toString(monthInt);
    if (monthInt < 10) {
      month = "0" + month;
    }
    String year = Integer.toString(dateCalendar.get(Calendar.YEAR));
    return year + "-" + month + "-" + day;
  }

  /**
   * Returns buying price for the symbol at a given date.
   *
   * @param symbol of the company for which stock needs to be bought.
   * @param date   for which price needs to be fetched.
   * @return price of type double.
   * @throws Exception in case price was not found or symbol is incorrect.
   */
  @Override
  public abstract double getStockPriceForBuying(String symbol, Date date) throws Exception;

  /**
   * Returns stock price of a company for calculating values at a particular date.
   *
   * @param stockSymbol of the company for which stock value needs to be found.
   * @param date        for which price needs to be fetched.
   * @return price of type double.
   * @throws Exception in case price was not found or symbol is incorrect.
   */
  @Override
  public abstract double getStockPriceForCalculatingValue(String stockSymbol, Date date)
          throws Exception;
}
