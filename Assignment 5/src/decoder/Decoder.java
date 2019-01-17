package decoder;

/**
 * <p>This interface represents common operations required to perform decoding operation. It
 * supports any number of code symbols required for encoding or decoding operation. All the encoded
 * values are supposed to be stored as prefix tree and therefore, this type of encoding schemes
 * generates codes such that no code is a prefix of another code.</p>
 * <p>Prefix tree structure contains two types of nodes - Transition nodes which is responsible for
 * expanding the tree to fit encoded values and the corresponding result of those values can be
 * taken from the end or leaf nodes known as Character nodes</p>
 * <p>The following are the methods supported by this interface:</p>
 * <ul>
 * <li>
 * <strong>addCode: It takes character and its corresponding code to add in the prefix
 * tree.</strong>
 * </li>
 * <li>
 * <strong>decode: Finds the decoded message of the string for a given encoded value.</strong>
 * </li>
 * <li>
 * <strong>allCodes: List outs all the possible encoding values stored by prefix tree.</strong>
 * </li>
 * <li>
 * <strong>isCodeComplete: Checks if the prefix tree can accommodate new encoded values.</strong>
 * </li>
 * </ul>
 */
public interface Decoder {

  /**
   * <p>This method takes a symbol and its code as a character and string respectively.
   * It adds this code to the coding tree and should not return anything.</p>
   * <p>If the code contains symbols other than the coding symbols, this method should throw an
   * IllegalStateException with appropriate message.</p>
   *
   * @param symbol encoded symbol for a given code in Character.
   * @param code   decode value for a given symbol in String.
   * @throws IllegalStateException in case the input code is not a valid coding symbol.
   */

  void addCode(Character symbol, String code) throws IllegalStateException;

  /**
   * <p>This method takes an encoded message as a string, and returns the decoded message
   * as a string using the coding tree created thus far.</p>
   * <p>If the decoding fails when not all codes have been added, the traversal leads to a
   * leaf that does not exist, this method should throw an IllegalStateException with appropriate
   * message.</p>
   *
   * @param message in String that needs to be decoded.
   * @return decoded message for the given encoded message.
   * @throws IllegalStateException in case the decoding fails.
   */
  String decode(String message) throws IllegalStateException;

  /**
   * <p>This method returns the codes entered thus far as a string. The result should represent
   * each
   * symbol x and its code yyy on a separate line, in the form x:yyy.</p>
   *
   * @return codes for all the respective symbols in coding tree in String.
   */
  String allCodes();

  /**
   * <p>This method returns true if the code entered so far is complete, false otherwise. A
   * complete
   * code can be described when every valid encoded message can be successfully decoded. Using this
   * representation, the code is complete when the prefix tree is full which means every transition
   * nodes has exactly the number of children equal to the number of coding symbols supported. In
   * case of a tree supporting "01" coding symbols, every transition node should have two children.
   * </p>
   *
   * @return boolean representing whether the code is complete.
   */
  boolean isCodeComplete();
}
