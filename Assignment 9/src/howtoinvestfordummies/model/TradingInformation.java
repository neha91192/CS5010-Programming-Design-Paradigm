package howtoinvestfordummies.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Represents class to handle business logic of this application. Information such as operating
 * hours of the stock trading, validation related to the same is provided in this class.
 */
class TradingInformation implements ITradingInformation {
  static final double MINIMUM_INVESTMENT = 0.00;
  private static final int OPENING_HOUR = 9;
  private static final int OPENING_MIN = 0;
  private static final int OPENING_SEC = 0;
  private static final int CLOSING_HOUR = 15;
  private static final int CLOSING_MIN = 59;
  private static final int CLOSING_SEC = 59;

  /**
   * Checks if the user date is acceptable for trading. It first creates closing date object and
   * opening date object and compares if the current date is withing the range. It also checks if
   * the date provided by user is not on weekends.
   *
   * @param date provided by user in Date.
   * @return true if trading can be done otherwise false.
   */
  @Override
  public boolean isTradingHours(Date date) {
    Calendar userDate = Calendar.getInstance();
    userDate.setTime(date);

    Calendar closingTime = Calendar.getInstance();
    closingTime.setTime(date);
    closingTime.set(Calendar.HOUR, CLOSING_HOUR);
    closingTime.set(Calendar.MINUTE, CLOSING_MIN);
    closingTime.set(Calendar.SECOND, CLOSING_SEC);
    Date closing = closingTime.getTime();

    Calendar openingTime = Calendar.getInstance();
    openingTime.setTime(date);
    openingTime.set(Calendar.HOUR_OF_DAY, OPENING_HOUR);
    openingTime.set(Calendar.MINUTE, OPENING_MIN);
    openingTime.set(Calendar.SECOND, OPENING_SEC);
    Date opening = openingTime.getTime();

    return (date.after(opening) && date.before(closing)
            && userDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
            && userDate.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);
  }


}
