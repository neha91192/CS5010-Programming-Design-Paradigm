import org.junit.Before;
import org.junit.Test;


import fib.FibCounter;
import fib.FibonacciCounter;
import fib.FibonacciCounterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Junit test class for {@link FibCounter}. It performs test cases for incrementing count,
 * decrementing count, getting current count, and fetching fibonacci number for the current count.
 *
 * @author nehashukla
 */
public class FibCounterTest {
  FibonacciCounter fibonacciCounter;

  /**
   * Initialises Fibonacci counter with default values.
   */
  @Before
  public void setUp() {
    fibonacciCounter = new FibCounter();
  }

  /**
   * Helper method which calls incrementCount of {@link FibCounter} n times.
   *
   * @param n of type int consisting of value by which counter needs to be incremented.
   */
  private void incrementBy(int n) throws FibonacciCounterException {
    for (int i = 0; i < n; i++) {
      fibonacciCounter.incrementCount();
    }
  }

  /**
   * Helper method which calls decrementCount of {@link FibCounter} n times.
   *
   * @param n of type int consisting of value by which counter needs to be decremented.
   */
  private void decrementBy(int n) throws FibonacciCounterException {
    for (int i = 0; i < n; i++) {
      fibonacciCounter.decrementCount();
    }
  }

  /**
   * Calls incrementCount of {@link FibCounter} to test for the test case where counter is
   * incremented successfully. Test case fails if the method catches {@link
   * FibonacciCounterException} or if expected value doesn't match with the actual value.
   */
  @Test
  public void testIncrementCountSuccess() throws FibonacciCounterException {
    int count = 9;
    incrementBy(7);
    assertEquals(count, fibonacciCounter.incrementCount().getCount());
  }

  /**
   * Calls incrementCount of {@link FibCounter} to test for the test case where counter cannot be
   * incremented successfully. Test case fails if the method executes without throwing {@link
   * FibonacciCounterException}.
   */
  @Test(expected = FibonacciCounterException.class)
  public void testIncrementCountMaxCount() throws FibonacciCounterException {
    incrementBy(51);
    fail("Expected FibonacciCounterException but the code completed with no such exception");
  }

  /**
   * Calls incrementCount of {@link FibCounter} to test for the test case where counter is
   * incremented by 1 successfully, i.e, base case for calculating Fibonacci number. Test case fails
   * if the method catches {@link FibonacciCounterException} or if expected value doesn't match with
   * the actual value.
   */
  @Test
  public void testIncrementCountBaseCase() throws FibonacciCounterException {
    int count = 2;
    assertEquals(count, fibonacciCounter.incrementCount().getCount());
  }


  /**
   * Calls decrementCount of {@link FibCounter} to test for the test case where counter is
   * decremented successfully. Test case fails if the method catches {@link
   * FibonacciCounterException} or if expected value doesn't match with the actual value.
   */
  @Test
  public void testDecrementCountSuccess() throws FibonacciCounterException {
    int count = 5;
    incrementBy(5);
    assertEquals(count, fibonacciCounter.decrementCount().getCount());
  }

  /**
   * Calls decrementCount of {@link FibCounter} to test for the test case where counter cannot be
   * decremented successfully. Test case fails if the method executes without throwing {@link
   * FibonacciCounterException}
   */
  @Test(expected = FibonacciCounterException.class)
  public void testDecrementCountMinCount() throws FibonacciCounterException {
    incrementBy(2);
    decrementBy(3);
    fail("Expected FibonacciCounterException but code completed with no such exception");
  }

  /**
   * Calls decrementCount of {@link FibCounter} to test for the test case where counter is
   * incremented and decremented by 1 successfully, i.e, base case for calculating Fibonacci number.
   * Test case fails if the method catches {@link FibonacciCounterException} or if the expected
   * value doesn't match with the actual value.
   */
  @Test
  public void testDecrementCountBaseCase() throws FibonacciCounterException {
    int count = 1;
    incrementBy(1);
    assertEquals(count, fibonacciCounter.decrementCount().getCount());
  }

  /**
   * Calls getCount of {@link FibCounter} to test for the test case where count is returned
   * successfully. Test case fails if the method catches {@link FibonacciCounterException} or if
   * expected value doesn't match with the actual value.
   */
  @Test
  public void testGetCount() throws FibonacciCounterException {
    int count = 34;
    incrementBy(33);
    assertEquals(count, fibonacciCounter.getCount());
  }


  /**
   * Calls getFibonacci of {@link FibCounter} to test for the test case where fibonacci number is
   * returned after incrementing counter n times. Test case fails if the method catches {@link
   * FibonacciCounterException} or if expected value doesn't match with the actual value.
   */
  @Test
  public void testGetFibonacciNumberIncrement() throws FibonacciCounterException {
    int fibonacciNumber = 21;
    incrementBy(8);
    assertEquals(fibonacciNumber, fibonacciCounter.getFibonacciNumber());
  }

  /**
   * Calls getFibonacci of {@link FibCounter} to test for the test case where fibonacci number is
   * returned after incrementing and decrementing counter n times. Test case fails if the method
   * catches {@link FibonacciCounterException} or if expected value doesn't match with the actual
   * value.
   */
  @Test
  public void testGetFibonacciNumberDecrement() throws FibonacciCounterException {
    int fibonacciNumber = 0;
    incrementBy(3);
    decrementBy(3);
    assertEquals(fibonacciNumber, fibonacciCounter.getFibonacciNumber());
  }
}
