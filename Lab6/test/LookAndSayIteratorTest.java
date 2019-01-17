import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test for {@link LookAndSayIterator}.
 */
public class LookAndSayIteratorTest {
  private RIterator<BigInteger> startEndIterator;
  private RIterator<BigInteger> testIterator;
  private RIterator<BigInteger> startIterator;
  private RIterator<BigInteger> defaultStartIterator;
  private List<BigInteger> expectedValues;

  /**
   * Test for {@link LookAndSayIterator}.
   */
  @Before
  public void setup() {
    expectedValues = new ArrayList<>();
    startEndIterator = new LookAndSayIterator(new BigInteger("300"),
            new BigInteger("5000000"));
    startIterator = new LookAndSayIterator(new BigInteger("" + 9999999));
    defaultStartIterator = new LookAndSayIterator(new BigInteger("" + 1),
            new BigInteger("" + 5000));
  }

  /**
   * Seed is invalid: lower bound for Iterator with start and end values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSeedInvalid1() {
    testIterator = new LookAndSayIterator(new BigInteger("" + 0),
            new BigInteger("" + 1111111));
  }

  /**
   * Seed is invalid: lower bound for iterator with only start values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSeedInvalid2() {
    testIterator = new LookAndSayIterator(new BigInteger("" + 0));
  }

  /**
   * Seed is invalid: lower bound for Iterator with start and end values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSeedInvalid3() {
    testIterator = new LookAndSayIterator(new BigInteger("" + -11),
            new BigInteger("" + 1111111));
  }

  /**
   * Seed is invalid: lower bound for iterator with only start values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSeedInvalid4() {
    testIterator = new LookAndSayIterator(new BigInteger("" + Integer.MIN_VALUE));
  }

  /**
   * Seed is invalid: greater than end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSeedInvalid5() {
    testIterator = new LookAndSayIterator(new BigInteger("" + Integer.MAX_VALUE),
            new BigInteger("" + 33333));
  }


  /**
   * Test for default seed as 1.
   */
  @Test
  public void testDefaultStartValueForSeed() {
    testIterator = new LookAndSayIterator();
    assertEquals(BigInteger.ONE, testIterator.next());
  }

  /**
   * Test for default start and custom end for {@link LookAndSayIterator}. This method checks for
   * both, next and prev methods. It uses pre-calculated list to verify results where default start
   * and custom end values have been provided.
   */
  @Test
  public void testDefaultStartCustomEnd() {
    expectedValues.add(new BigInteger("" + 1));
    expectedValues.add(new BigInteger("" + 11));
    expectedValues.add(new BigInteger("" + 21));
    expectedValues.add(new BigInteger("" + 1211));
    expectedValues.add(new BigInteger("" + 111221));
    int i = 0;
    while (i < expectedValues.size()) {
      assertEquals(expectedValues.get(i), defaultStartIterator.next());
      i++;
    }
    //asserts the same end value even after multiple calls to next.
    assertFalse(defaultStartIterator.hasNext());
    assertEquals(expectedValues.get(expectedValues.size() - 1), defaultStartIterator.next());
    assertEquals(expectedValues.get(expectedValues.size() - 1), defaultStartIterator.next());
    assertEquals(expectedValues.get(expectedValues.size() - 1), defaultStartIterator.next());
    int j = expectedValues.size() - 1;
    while (j >= 0) {
      assertEquals(expectedValues.get(j), defaultStartIterator.prev());
      j--;
    }
    //asserts the same end value even after multiple calls to prev.
    assertFalse(defaultStartIterator.hasPrevious());
    assertEquals(expectedValues.get(0), defaultStartIterator.prev());
    assertEquals(expectedValues.get(0), defaultStartIterator.prev());
    assertEquals(expectedValues.get(0), defaultStartIterator.prev());
  }

  /**
   * Test for custom start and end for {@link LookAndSayIterator}. This method checks for both, next
   * and prev methods. It uses pre-calculated list to verify results where custom start and end
   * values have been provided.
   */
  @Test
  public void testCustomStartEnd() {
    expectedValues.add(new BigInteger("" + 300));
    expectedValues.add(new BigInteger("" + 1320));
    expectedValues.add(new BigInteger("" + 11131210));
    int i = 0;
    while (i < expectedValues.size()) {
      assertEquals(expectedValues.get(i), startEndIterator.next());
      i++;
    }
    //asserts the same end value even after multiple calls to next.
    assertFalse(startEndIterator.hasNext());
    assertEquals(expectedValues.get(expectedValues.size() - 1), startEndIterator.next());
    assertEquals(expectedValues.get(expectedValues.size() - 1), startEndIterator.next());
    assertEquals(expectedValues.get(expectedValues.size() - 1), startEndIterator.next());
    int j = expectedValues.size() - 1;
    while (j >= 0) {
      assertEquals(expectedValues.get(j), startEndIterator.prev());
      j--;
    }
    //asserts the same end value even after multiple calls to prev.
    assertFalse(startEndIterator.hasPrevious());
    assertEquals(expectedValues.get(0), startEndIterator.prev());
    assertEquals(expectedValues.get(0), startEndIterator.prev());
    assertEquals(expectedValues.get(0), startEndIterator.prev());
  }

  /**
   * Test for custom start and default end (size exceeds 100) for {@link LookAndSayIterator}. This
   * method checks for both, next and prev methods. It uses pre-calculated list to verify results
   * where custom start and default end values have been provided.
   */
  @Test
  public void testStopOneAfterMaxEndLimit() {
    expectedValues.add(new BigInteger("" + 9999999));
    expectedValues.add(new BigInteger("" + 79));
    expectedValues.add(new BigInteger("" + 1719));
    expectedValues.add(new BigInteger("" + 11171119));
    expectedValues.add(new BigInteger("" + 31173119));
    expectedValues.add(new BigInteger("132117132119"));
    expectedValues.add(new BigInteger("11131221171113122119"));
    expectedValues.add(new BigInteger("311311222117311311222119"));
    expectedValues.add(new BigInteger("1321132132211713211321322119"));
    expectedValues.add(new BigInteger("11131221131211132221171113122113121113222119"));
    expectedValues.add(new BigInteger("3113112221131112311332211731131122211311123113322119"));
    expectedValues.add(new BigInteger("13211321322113311213212322211713211321322113311213"
            + "2123222119"));
    expectedValues.add(new BigInteger("111312211312111322212321121113121112133221171113122"
            + "1131211132221232112111312111213322119"));
    expectedValues.add(new BigInteger("311311222113111231133211121312211231131112311211"
            + "2322211731131122211311123113321112131221123113111231121123222119"));

    int i = 0;
    while (i < expectedValues.size()) {
      assertEquals(expectedValues.get(i), startIterator.next());
      i++;
    }
    //asserts the same end value even after multiple calls to next.
    assertFalse(startIterator.hasNext());
    assertEquals(expectedValues.get(expectedValues.size() - 1), startIterator.next());
    assertEquals(expectedValues.get(expectedValues.size() - 1), startIterator.next());
    assertEquals(expectedValues.get(expectedValues.size() - 1), startIterator.next());
    int j = expectedValues.size() - 1;
    while (j >= 0) {
      assertEquals(expectedValues.get(j), startIterator.prev());
      j--;
    }
    //asserts the same end value even after multiple calls to prev.
    assertFalse(startIterator.hasPrevious());
    assertEquals(expectedValues.get(0), startIterator.prev());
    assertEquals(expectedValues.get(0), startIterator.prev());
    assertEquals(expectedValues.get(0), startIterator.prev());
  }

}
