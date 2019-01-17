import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link LookAndSayIterator}.
 */
public class LookAndSayIteratorTest {
  private RIterator<BigInteger> startEndIterator;
  private RIterator<BigInteger> startIterator;
  private RIterator<BigInteger> defaultIterator;
  BigInteger seed;

  /**
   * Test for {@link LookAndSayIterator}.
   */
  @Before
  public void setup() {
    seed = new BigInteger("300");
    BigInteger end = new BigInteger("5000000");
    startEndIterator = new LookAndSayIterator(seed, end);
    startIterator = new LookAndSayIterator(seed);
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
   * Test for {@link LookAndSayIterator}. 300 1320 11131210
   */
  @Test
  public void testStartEnd() {
    BigInteger start = seed;
    while (startEndIterator.hasNext()) {
      System.out.println(startEndIterator.next());
//      BigInteger nextValue = startEndIterator.next();
//      BigInteger actual = computeReverse(nextValue);
//      assertEquals(start, actual);
//      start = start.add(new BigInteger("" + 1));
    }
    System.out.println(startEndIterator.next());

    while (startEndIterator.hasPrevious()) {
      System.out.println(startEndIterator.prev());
//      BigInteger nextValue = startEndIterator.next();
//      BigInteger actual = computeReverse(nextValue);
//      assertEquals(start, actual);
//      start = start.add(new BigInteger("" + 1));
    }
    System.out.println(startEndIterator.prev());
  }

  /**
   * Test for {@link LookAndSayIterator}.
   */
  @Test
  public void testStart() {
    BigInteger start = seed;
    while (startIterator.hasNext()) {
      BigInteger nextValue = startIterator.next();
      BigInteger actual = computeReverse(nextValue);
      assertEquals(start, actual);
      start = start.add(new BigInteger("" + 1));
    }
  }

  private BigInteger computeReverse(BigInteger lookAndSaySequence) {
    StringBuilder sb = new StringBuilder();
    String sequence = lookAndSaySequence.toString();
    for (int i = 0; i < sequence.length() - 1; ) {
      for (int j = 0; j < Character.getNumericValue(sequence.charAt(i)); j++) {
        sb.append(sequence.charAt(i + 1));
      }
      i = i + 2;
    }
    return new BigInteger(sb.toString());
  }
}
