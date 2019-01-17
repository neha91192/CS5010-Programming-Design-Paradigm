import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import decoder.Decoder;
import decoder.DecoderImpl;
import encoder.Encoder;
import encoder.EncoderImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for {@link Encoder}.
 */
public class EncoderTest {
  /**
   * Encoder for calculating binary coding values.
   */
  private Encoder encoder1;
  /**
   * Decoder for calculating binary coding values.
   */
  private Decoder decoder1;
  /**
   * Decoder for calculating hex coding values.
   */
  private Decoder decoder2;
  /**
   * Encoder for calculating hex coding values.
   */
  private Encoder encoder2;
  /**
   * Plaintext which needs to be encoded or decoded.
   */
  private String plainText;
  /**
   * FileName which needs to be read.
   */
  private String fileName;

  /**
   * Initialises binary and hex encoders and decoders.
   */
  @Before
  public void setUp() {
    encoder1 = new EncoderImpl("01");
    decoder1 = new DecoderImpl("01");
    encoder2 = new EncoderImpl("0123456789abcdef");
    decoder2 = new DecoderImpl("0123456789abcdef");
    fileName = "passage.txt";
    plainText = readFile(fileName);
  }

  /**
   * Reads file from the system.
   *
   * @param fileName of type String
   * @return content of the file.
   */
  private String readFile(String fileName) {
    BufferedReader reader = null;
    StringBuilder sb = new StringBuilder();
    try {
      InputStream fileStream = new FileInputStream(new File(
              "res/" + fileName));
      reader = new BufferedReader(new InputStreamReader(fileStream));


      String line;
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      fail(e.getMessage());
    }
    return sb.toString();
  }


  /**
   * Test for encoding of binary coder.
   */
  @Test
  public void testEncodeBinary() {
    try {
      String value = encoder1.encode(fileName);
      Map<Character, String> codingTable = encoder1.getCodingTable();
      for (Character character : codingTable.keySet()) {
        decoder1.addCode(character, codingTable.get(character));
      }
      String decodedValue = decoder1.decode(value);
      assertEquals(plainText, decodedValue);
    } catch (IOException e) {
      fail(e.getMessage());
    }

  }

  /**
   * Test for encoding of hex coder.
   */
  @Test
  public void testEncodeHex() {
    try {
      String hexValue = encoder2.encode(fileName);
      Map<Character, String> codingTable = encoder2.getCodingTable();
      for (Character c : codingTable.keySet()) {
        decoder2.addCode(c, codingTable.get(c));
      }
      String decodedValue = decoder2.decode(hexValue);
      assertEquals(plainText, decodedValue);
    } catch (IOException e) {
      fail(e.getMessage());
    }

  }


}

