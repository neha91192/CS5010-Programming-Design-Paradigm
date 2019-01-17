package encoder;

import java.io.IOException;
import java.util.Map;

/**
 * Represents encoder interface which gives a base implementation details for any encoder
 * algorithm.
 */
public interface Encoder {
  /**
   * This method takes fileName as input and returns the encoded value in String. The file should be
   * present in the resource folder.
   *
   * @param fileName in String.
   * @return encoded value in String.
   */
  String encode(String fileName) throws IOException;

  /**
   * Returns encoding Table generated through encoder algorithm.
   *
   * @return codingTable which contains symbol.
   */
  Map<Character, String> getCodingTable();

}
