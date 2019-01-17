package howtoinvestfordummies.model;

import java.util.Date;

/**
 * Represents interface to check business logic of the application. It supports various checks to
 * ensure that trading activity is made within the guidelines which includes working hours, weekends
 * etc.
 */
public interface ITradingInformation {
  /**
   * States whether the date of investment activity is correct. It validates with the trading
   * information present in the implementation and returns true if trading is allowed otherwise
   * false.
   *
   * @param date provided by user in Date.
   * @return true if trading can be done otherwise false.
   */
  boolean isTradingHours(Date date);
}
