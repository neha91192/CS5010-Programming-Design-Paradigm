package fib;

/**
 * <p>Implementation class for {@link FibonacciCounter}. It implements four methods -
 * incrementCount, decrementCount, getCount, getFibonacciNumber.</p>
 *
 * @author nehashukla
 */
public class FibCounter implements FibonacciCounter {
  /**
   * Counter attribute of FibonacciCounter of type {@link int}.
   */
  private int count;
  /**
   * Fibonacci Number of the particular count.
   */
  private int fibonacciNumber;
  /**
   * Previous fibonacci number value used to calculate the next.
   */
  private int lastFibonacciNumber = 0;
  /**
   * Previous to previous fibonacci number value used to calculate the next.
   */
  private int secondLastFibonacciNumber = 1;
  /**
   * Minimum count value that is supported by this FibonacciCounter implementation.
   */
  private static int MIN_COUNT = 1;

  /**
   * Default Constructor for FibCounter implementation class. The default value for count is 1 and
   * fibonacciNumber is 0.
   */
  public FibCounter() {
    this.count = 1;
    this.fibonacciNumber = 0;
  }

  /**
   * <p>Increments the count for this FibonacciCounter object. While incrementing the count, it
   * also calculates the next fibonacci number by calling findNextFibonacciNumber(). If this
   * operation is successful,the count will be incremented and the updated values will be returned
   * in FibonacciCounter object. If fibonacci number value has reached the maximum integer value, it
   * throws {@link FibonacciCounterException} with appropriate message.</p>
   *
   * @return FibonacciCounter object containing the updated count.
   * @throws FibonacciCounterException in case the counter has reached the maximum count value.
   */
  @Override
  public FibonacciCounter incrementCount() throws FibonacciCounterException {
    findNextFibonacciNumber();
    this.count++;
    return this;
  }

  /**
   * <p>Decrements the count for this FibonacciCounter object. While decrementing the count, it
   * also calculates the previous fibonacci number by calling findPreviousFibonacciNumber(). If this
   * operation is successful, the count will be decremented and the updated values will be returned
   * in FibonacciCounter object. If fibonacci number value has reached the minimum integer value, it
   * throws {@link FibonacciCounterException} with appropriate message.</p>
   *
   * @return FibonacciCounter object containing the updated count.
   * @throws FibonacciCounterException in case the counter has reached the minimum count value.
   */
  @Override
  public FibonacciCounter decrementCount() throws FibonacciCounterException {
    if (this.count > MIN_COUNT) {
      findPreviousFibonacciNumber();
      this.count--;
      return this;
    } else {
      throw new FibonacciCounterException("Current count has reached the minimum limit for "
              + "this Fibonacci Counter.");
    }
  }

  /**
   * Returns the current counter value.
   *
   * @return count of type integer.
   */
  @Override
  public int getCount() {
    return this.count;
  }

  /**
   * Returns the Fibonacci Number for the current count. This value is pre-calculated on every
   * decrement or increment count call.
   *
   * @return fibonacci number for current counter of type integer.
   */
  @Override
  public int getFibonacciNumber() {
    return this.fibonacciNumber;
  }

  /**
   * Calculates the next fibonacci number when the counter needs to be incremented. It simply finds
   * fibonacci number for the incremented count by maintaining two pointers derived from previous
   * two fibonacci numbers and updates fibonacci number value for the incremented count. When
   * fibonacci number has been calculated successfully, it updates pointer references for the next
   * count, otherwise throws {@link FibonacciCounterException} if the count has reached maximum
   * Integer value.
   *
   * @throws FibonacciCounterException in case the counter has reached the maximum count value.
   */
  private void findNextFibonacciNumber() throws FibonacciCounterException {
    int fibonacciSum = this.lastFibonacciNumber + this.secondLastFibonacciNumber;
    if (fibonacciSum > 0) {
      this.fibonacciNumber = fibonacciSum;
      this.secondLastFibonacciNumber = this.lastFibonacciNumber;
      this.lastFibonacciNumber = this.fibonacciNumber;
    } else {
      throw new FibonacciCounterException("Current count has reached the maximum limit for"
              + " this Fibonacci Counter.");
    }
  }

  /**
   * Calculates the previous fibonacci number when the counter needs to be decremented. It assigns
   * previous pointer value to the fibonacci number for the decremented count and carefully updates
   * pointer references by moving both the previous pointers one step back.
   */
  private void findPreviousFibonacciNumber() {
    this.fibonacciNumber = this.secondLastFibonacciNumber;
    this.secondLastFibonacciNumber = this.lastFibonacciNumber - this.secondLastFibonacciNumber;
    this.lastFibonacciNumber = this.fibonacciNumber;
  }
}
