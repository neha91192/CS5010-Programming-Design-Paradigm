import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

/**
 * Test for {@link LookAndSayIterator}.
 */
public class LookAndSayIteratorTest {
  private RIterator<BigInteger> startEndIterator;
  private RIterator<BigInteger> startIterator;
  private RIterator<BigInteger> defaultIterator;

  /**
   * Test for {@link LookAndSayIterator}.
   */
  @Before
  public void setup() {
    BigInteger start = new BigInteger("33442");
    BigInteger end = new BigInteger("59999999");
    startEndIterator = new LookAndSayIterator(start, end);
    startIterator = new LookAndSayIterator(start);
    defaultIterator = new LookAndSayIterator();
  }

  /**
   * Test for {@link LookAndSayIterator}.
   */
  @Test
  public void testDefault() {
    int i = 33442;
    while (defaultIterator.hasNext()) {
      System.out.print(Integer.toString(i) + ": ");
      System.out.println(defaultIterator.next());
      i++;
    }
  }

  /**
   * Test for {@link LookAndSayIterator}.
   */
  @Test
  public void testStartEnd() {
    while (startEndIterator.hasNext()) {
      startEndIterator.next();
    }
  }

  /**
   * Test for {@link LookAndSayIterator}.
   */
  @Test
  public void testStart() {
    int i = 33442;
    while (startIterator.hasNext()) {
      System.out.print(Integer.toString(i) + ": ");
      System.out.println(startIterator.next());
      i++;
    }
  }
}
