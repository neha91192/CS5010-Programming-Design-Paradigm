package decoder;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>This represents implementation class for {@link Decoder} interface. It has a root of type
 * {@link Code} which contains starting point of the decoder prefix tree. This decoder supports set
 * of coding characters which is initialized at the start.</p>
 * <p>This class performs the following functions:</p>
 * <ul>
 * <li><strong>addCode: </strong>adds the string of code representing a character to the tree.</li>
 * <li><strong>decode: </strong>Reads the tree to find out what could be the original characters in
 * the decoded text.</li>
 * <li><strong>allCodes: </strong>Calls the root node to traverse and find complete codes stored in
 * the prefix tree.</li>
 * <li><strong>isCodeComplete: </strong>Calls the root node to traverse and find if the tree is
 * complete.</li>
 * </ul>
 */
public class DecoderImpl implements Decoder {

  /**
   * Represents the starting point of coding tree of type {@link Code}.
   */
  private Code root;

  /**
   * Represents the set of code symbols supported by this Decoder Implementation.
   */
  private Set<Character> codeSymbols;

  /**
   * Constructs root node to be the instance of {@link CodeSymbol}, a subclass of {@link Code} used
   * for representing transition nodes. It also initialises the set used to store code symbols
   * supported by this implementation.
   *
   * @param code representing code symbols supported by decoder.
   */
  public DecoderImpl(String code) {
    root = new CodeSymbol('0', code);
    codeSymbols = new HashSet<>();
    for (Character c : code.toCharArray()) {
      codeSymbols.add(c);
    }
  }

  /**
   * This method implements {@link Decoder#addCode(Character, String)} feature. It takes character
   * to be added and the corresponding decoded symbol. Before adding anything, it checks for the
   * validity of code using {@link DecoderImpl#containsValidSymbol(String)} method, If the code is
   * not valid, it throws IllegalStateException.
   *
   * @param symbol encoded symbol for a given code in Character.
   * @param code   decode value for a given symbol in String.
   * @throws IllegalStateException case the input code is not a valid coding symbol.
   */
  @Override
  public void addCode(Character symbol, String code) throws IllegalStateException {
    Code character = new CharacterSymbol(symbol);
    if (containsValidSymbol(code)) {
      root.addCode(character, code);
    } else {
      throw new IllegalStateException("Cannot support the given code values");
    }
  }

  /**
   * This method decodes the encoded string and returns the original value corresponding to the
   * encoded message. It calls root of the Coding tree to find the decoded value.
   *
   * @param message in String that needs to be decoded.
   * @return original text of decoded message.
   * @throws IllegalStateException in case the decoding fails.
   */
  @Override
  public String decode(String message) throws IllegalStateException {
    return root.decode(message);
  }

  /**
   * This method traverses the complete coding tree to find out all the possible encoded values for
   * the supported characters. The format of the string will be x:yyy where x is the original
   * character and yyy will be the decoded value.
   *
   * @return a string representing all the codes.
   */
  @Override
  public String allCodes() {
    return root.allCodes();
  }


  /**
   * Checks if the code is complete. A coding tree is said to be complete when all the children of
   * transition nodes (which may contain children) is full to its capacity (number of coding symbols
   * supported). It returns true in that case, otherwise false.
   *
   * @return boolean indicating if the coding tree is complete or not.
   */
  @Override
  public boolean isCodeComplete() {
    return root.isCodeComplete();
  }

  /**
   * Checks if the decoded values which are getting added are valid and supported by this decoder
   * implementation. If the symbol is null or empty, it will be considered as invalid.
   *
   * @param symbols of type String that needs to be added.
   * @return boolean value indicating if the code needs to be added is valid or not.
   */
  private boolean containsValidSymbol(String symbols) {
    if (symbols != null && !symbols.equals("")) {
      for (Character c : symbols.toCharArray()) {
        if (!codeSymbols.contains(c)) {
          return false;
        }
      }
    } else {
      return false;
    }
    return true;
  }

}
