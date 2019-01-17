package decoder;

/**
 *
 */
public interface Decoder {

  /**
   * <p>An addCode method that takes a symbol and its code as a character and string respectively.
   * This method should add this code to the coding tree. It should not return anything.</p>
   * <p>This method should throw an IllegalStateException if the code contains symbols other than
   * the coding symbols (other than 1 and 0 in the above example).</p>
   *
   * @param symbol encoded symbol for a given code in Character.
   * @param code   decode value for a given symbol in String.
   */

  void addCode(Character symbol, String code) throws IllegalStateException;

  /**
   * <p>A decode method that takes an encoded message as a string, and returns the decoded message
   * as a string using the coding tree created thus far.</p>
   * <p>This method should throw an IllegalStateException if the decoding fails (e.g. not all codes
   * have been added, so the traversal leads to a leaf that does not exist)</p>
   *
   * @param message in String that needs to be decoded.
   * @return decoded message for the given encoded message.
   */
  String decode(String message) throws IllegalStateException;

  /**
   * A allCodes method that returns the codes entered thus far as a string. This string contains
   * each symbol x and its code yyy on a separate line, in the form x:yyy.
   *
   * @return codes for all the respective symbols in coding tree.
   */
  String allCodes();

  /**
   * A isCodeComplete method that returns true if the code entered so far is complete,  false
   * otherwise. A code is said to be complete if every valid encoded message can be successfully
   * decoded. If the decoding is done by using a coding tree, then this condition is fulfilled if
   * the coding tree is full (i.e. every non-leaf node has exactly the same number of children,
   * equal to the number of coding symbols). In other words, a coding tree for coding symbols {0,1}
   * is full if each non-leaf node has exactly two children.
   *
   * @return boolean representing whether the code is complete.
   */
  boolean isCodeComplete();
}
