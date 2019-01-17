package cs5010.register;

/**
 * This class represents an exception where the cash register does not have enough change to
 * dispense the required amount.
 */
public class InsufficientCashException extends Exception {
  /**
   * Constructor of this class which sets message to the parent exception class.
   *
   * @param msg containing details of the exception in String.
   */
  public InsufficientCashException(String msg) {
    super(msg);
  }
}
