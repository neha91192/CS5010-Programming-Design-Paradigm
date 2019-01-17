package fib;

/**
 * <p>FibonacciCounter interface is a blueprint to design a counter that counts Fibonacci numbers.
 * It starts counting from 1, 2 and so on and also performs increment or decrement operations on the
 * counter. The current count has corresponding Fibonacci number, which this interface should return
 * on the request. For example, if the count has been incremented by 5, the current count will now
 * become 6 and the fibonacci number for this count will be 5.</p>
 * <p>The minimum value of the count is 1. If the user attempts to decrement count below 1, an
 * exception should be thrown.</p> <p>Performs the following functions -
 * <ul>
 * <li><strong>incrementCount: </strong> Responsible for incrementing fibonacci counter and returns
 * FibonacciCounter object.</li>
 * <li><strong>decrementCount: </strong> Responsible for decrementing fibonacci counter and returns
 * FibonacciCounter object. The count at anytime cannot go below 1.</li>
 * <li><strong>getCount: </strong> Responsible for returning count of Fibonacci
 * Counter.</li>
 * <li><strong>getFibonacciNumber: Returns fibonacci number for the current count.</strong> </li>
 * </ul></p>
 *
 * @author nehashukla
 */
public interface FibonacciCounter {
  /**
   * Returns a FibonacciCounter object with its count incremented by 1.
   */
  FibonacciCounter incrementCount() throws FibonacciCounterException;

  /**
   * Returns a FibonacciCounter object with its count decremented by 1.
   */
  FibonacciCounter decrementCount() throws FibonacciCounterException;

  /**
   * Returns the current count of the counter.
   */
  int getCount();

  /**
   * Returns the Fibonacci number corresponding to the current count.
   */
  int getFibonacciNumber();

}
