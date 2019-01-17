import org.junit.BeforeClass;
import org.junit.Test;

import decoder.Decoder;
import decoder.DecoderImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link Decoder}.
 */
public class DecoderTest {

  /**
   * Decoder instance for complete coding tree used in common operation.
   */
  private static Decoder decoder1;
  /**
   * Decoder instance for empty coding tree used to check decode operation.
   */
  private static Decoder decoder2;
  /**
   * Decoder instance for nary coding tree used to check different operation.
   */
  private static Decoder decoder3;
  /**
   * Decoder instance for incomplete binary coding tree used to check different operation.
   */
  private static Decoder decoder4;
  /**
   * Decoder instance for empty coding tree used to check add operation.
   */
  private Decoder decoder5;

  /**
   * Sets up the initial values for decoder.
   */
  @BeforeClass
  public static void setUp() {
    decoder1 = new DecoderImpl("01");
    decoder2 = new DecoderImpl("01");
    decoder3 = new DecoderImpl("012");
    decoder4 = new DecoderImpl("01");
    initializeDecoder();
    initializeDecoder4();
  }

  /**
   * Initialises decoder values for complete tree.
   */
  private static void initializeDecoder() {
    decoder1.addCode('b', "00");
    decoder1.addCode('c', "011");
    decoder1.addCode('a', "100");
    decoder1.addCode('e', "101");
    decoder1.addCode('d', "11");
    decoder1.addCode('\t', "010");
  }

  /**
   * Initialises decoder values for incomplete tree.
   */
  private static void initializeDecoder4() {
    decoder4.addCode('c', "00");
    decoder4.addCode('b', "011");
    decoder4.addCode('a', "100");
    decoder4.addCode('d', "101");
    decoder4.addCode('e', "11");
  }

  /**
   * Checks if the base level codes are successfully added at level 1.
   */
  @Test
  public void testAddCodeBase() {
    decoder5 = new DecoderImpl("01");
    decoder5.addCode('a', "0");
    assertEquals("a", decoder5.decode("0"));
    decoder5.addCode('b', "1");
    assertEquals("b", decoder5.decode("1"));
  }

  /**
   * Checks for the add code condition for incomplete binary coding tree.
   */
  @Test
  public void testAddCode1() {
    decoder5 = new DecoderImpl("01");

    decoder5.addCode('b', "00");
    assertEquals("b", decoder5.decode("00"));

    decoder5.addCode('x', "001");
    assertEquals("x", decoder5.decode("001"));

    decoder5.addCode('c', "011");
    assertEquals("c", decoder5.decode("011"));

    decoder5.addCode('a', "100");
    assertEquals("a", decoder5.decode("100"));

    decoder5.addCode('e', "101");
    assertEquals("e", decoder5.decode("101"));

    decoder5.addCode('d', "11");
    assertEquals("d", decoder5.decode("11"));

  }

  /**
   * Checks for the add code condition for nary binary coding tree.
   */
  @Test
  public void testAddCode2() {
    decoder3.addCode('a', "01");
    assertEquals("a", decoder3.decode("01"));

    decoder3.addCode('b', "201");
    assertEquals("b", decoder3.decode("201"));

    decoder3.addCode('c', "021");
    assertEquals("c", decoder3.decode("021"));

    decoder3.addCode('e', "102");
    assertEquals("e", decoder3.decode("102"));

    decoder3.addCode('d', "00");
    assertEquals("d", decoder3.decode("00"));

  }

  /**
   * Checks for the add code condition for complete binary coding tree. Adding a new value would
   * override the coding tree.
   */
  @Test
  public void testAddCode3() {
    decoder5 = new DecoderImpl("01");
    decoder5.addCode('b', "00");
    assertEquals("b", decoder5.decode("00"));

    decoder5.addCode('c', "011");
    assertEquals("c", decoder5.decode("011"));

    decoder5.addCode('a', "100");
    assertEquals("a", decoder5.decode("100"));

    decoder5.addCode('e', "101");
    assertEquals("e", decoder5.decode("101"));

    decoder5.addCode('d', "11");
    assertEquals("d", decoder5.decode("11"));

    decoder5.addCode('\t', "010");
    assertEquals("\t", decoder5.decode("010"));

    //overrides here
    decoder5.addCode('p', "00");
    assertEquals("p", decoder5.decode("00"));
  }

  /**
   * Checks for the case where invalid code is added.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddCodeInvalidSymbols1() {
    decoder1.addCode('a', "031");
  }

  /**
   * Checks for the case where invalid code is added.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddCodeInvalidSymbols2() {
    decoder1.addCode('a', "102");
  }

  /**
   * Checks for the case where invalid code is added.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddCodeInvalidSymbols3() {
    decoder1.addCode('a', "^#$");
  }

  /**
   * Checks for the case where invalid code is added.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddCodeInvalidSymbols4() {
    decoder1.addCode('a', "+1021");
  }

  /**
   * Checks for the case where invalid code is added.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddCodeInvalidSymbols5() {
    decoder1.addCode('a', "pdd");
  }

  /**
   * Checks for the case where invalid code is added.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddCodeInvalidSymbols6() {
    String code = null;
    decoder1.addCode('a', code);
  }

  /**
   * Checks for the case where invalid code is added.
   */

  @Test(expected = IllegalStateException.class)
  public void testAddCodeInvalidSymbols7() {
    String code = "";
    decoder1.addCode('a', code);
  }

  /**
   * Checks for the case where decoding is successful.
   */
  @Test
  public void testDecode() {
    String val = decoder1.decode("10010100");
    assertEquals("aeb", val);
    String val2 = decoder1.decode("00101110100010011");
    String expected = "bed\tbad";
    assertEquals(expected, val2);
  }

  /**
   * Checks for the case where decoding is successful for empty tree.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecode2() {
    String val = decoder2.decode("10010100");
    assertEquals("", val);
  }

  /**
   * Checks for the case where decoded string is null.
   */
  @Test
  public void testDecode3() {
    String decode = null;
    String val = decoder2.decode(decode);
    assertEquals("", val);
  }

  /**
   * Checks for the case where decoded string is empty.
   */
  @Test
  public void testDecode4() {
    String decode = "";
    String val = decoder2.decode(decode);
    assertEquals("", val);
  }

  /**
   * Checks for the case where decoding is unsuccessful in case of incomplete tree.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecodeInvalid() {
    String val = decoder1.decode("0010111010001001101");

  }


  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAllCodes() {
    String expectedValue = "b:00\n\t:010\nc:011\na:100\ne:101\nd:11\n";
    String val = decoder1.allCodes();
    assertEquals(expectedValue, val);

  }

  /**
   * Checks for the case where code is complete for binary values of codes.
   */
  @Test
  public void testIsCodeComplete1() {
    Boolean result = decoder1.isCodeComplete();
    assertEquals(true, result);

  }

  /**
   * Checks for the case where code is complete for n-ary values of codes.
   */
  @Test
  public void testIsCodeComplete2() {
    Boolean result = decoder3.isCodeComplete();
    assertEquals(false, result);

  }

  /**
   * Checks for the case where code is incomplete for any values of codes.
   */
  @Test
  public void testIsCodeComplete3() {
    Boolean result = decoder4.isCodeComplete();
    assertEquals(false, result);

  }

}
