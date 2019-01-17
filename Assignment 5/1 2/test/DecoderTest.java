import org.junit.BeforeClass;
import org.junit.Test;

import decoder.Decoder;
import decoder.DecoderImpl;

import static org.junit.Assert.assertEquals;

public class DecoderTest {

  private static Decoder decoder;

  @BeforeClass
  public static void setUp() {
    decoder = new DecoderImpl("01");
    decoder.addCode('b', "00");
    decoder.addCode('c', "011");
    decoder.addCode('a', "100");
    decoder.addCode('e', "101");
    decoder.addCode('d', "11");
    decoder.addCode('\t', "010");
  }
  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAddCode() {
//    decoder.addCode('b', "00");
//    decoder.addCode('c', "01");
//    decoder.addCode('a', "100");
//    decoder.addCode('e', "101");
//    decoder.addCode('d', "11");

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddCodeInvalidSymbols() {
//    decoder.addCode('a', "03");
//    decoder.addCode('b', "214");
//    decoder.addCode('c', "332");
//    decoder.addCode('e', "103");
//    decoder.addCode('d', "32");

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testDecode() {
    String val = decoder.decode("10010100");
    System.out.println(val);
    assertEquals("aeb", val);
    String val2 = decoder.decode("00101110100010011");
    System.out.println(val2);
    assertEquals("bed	bad", val2);

  }
  /**
   * Checks for the case where answer is right.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecodeInvalid() {
    //gives value upto decoding value is found
    assertEquals("bed	bad", decoder.decode("00101110100010011"));

  }


  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAllCodes() {
    String val = decoder.allCodes();
    System.out.println(val);

  }

}
