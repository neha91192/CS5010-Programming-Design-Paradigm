package fib;

/**
 * Custom Exception class for FibonacciCounter Interface.
 */
public class FibonacciCounterException extends Exception {
  /**
   * Initialises message pertaining to this exception.
   *
   * @param message of type {@link String} containing exception details.
   */
  public FibonacciCounterException(String message) {
    super(message);
  }
}
