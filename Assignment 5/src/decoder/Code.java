package decoder;

/**
 * This is an abstract data type interface to represent each node of Coding Tree. It performs the
 * following functions:
 * <ul>
 * <li><strong>addCode: </strong> Takes node of type itself and a corresponding encoded value which
 * describes that node./li>
 * <li><strong>decode: </strong> For a given encoded message, it calculates the decoded value of
 * the same.</li>
 * <li><strong>allCodes: </strong> Returns a String containing information about all the codes
 * supported in the tree.</li>
 * <li><strong>isComplete: </strong> States whether the coding tree is completed or not.</li>
 * <li><strong>getSymbol:</strong> Returns the value of symbol present in each node.</li>
 * </ul>
 */
public interface Code {
  /**
   * Takes two parameters- Code of type itself {@link CharacterSymbol} and the encoded value of the
   * symbol in String. It returns nothing and updates the reference when being called. In case the
   * encoded value is already present in the tree, it updates the value in this call for the given
   * new character.
   *
   * @param characterCode of type Code which needs to be added.
   * @param code          containing the encoded value in String.
   * @throws IllegalStateException in case code cannot be added.
   */
  void addCode(Code characterCode, String code) throws IllegalStateException;

  /**
   * This method decodes the encoded string and returns the original value corresponding to the
   * encoded message.
   *
   * @param message containing encoded values in String.
   * @return decoded message of type string.
   * @throws IllegalStateException in case decoding fails.
   */
  String decode(String message) throws IllegalStateException;

  /**
   * This method traverses the complete coding tree to find out all the possible encoded values for
   * the supported characters. The format of the string will be x:yyy where x is the original
   * character and yyy will be the decoded value.
   *
   * @return a string representing all the codes.
   */
  String allCodes();

  /**
   * This method checks if the code is complete. A coding tree is said to be complete when all the
   * children of transition nodes (which may contain children) is full to its capacity (number of
   * coding symbols supported). It returns true in that case, otherwise false.
   *
   * @return boolean indicating if the coding tree is complete or not.
   */
  boolean isCodeComplete();

  /**
   * Returns the value of the symbol present in the character node. For example, if the node has
   * been reached from the symbol representing '0', the current node will have value as '0'.
   *
   * @return symbol of type Character.
   */
  Character getSymbol();

}
