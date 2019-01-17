package decoder;

import java.util.Map;
import java.util.Stack;

/**
 * Represents Generic class for implementing {@link Code}. It contains all the possible
 * implementation which is common to both the types of node in Coding Tree- {@link CodeSymbol} and
 * {@link CharacterSymbol}.
 */
public class GenericCode implements Code {
  /**
   * Character value representing this node. If it is of type {@link CodeSymbol}, it can contain any
   * values from supported coding symbols. If it is of type {@link CharacterSymbol} it can contain
   * any character values which is supported by the tree.
   */
  protected Character symbol;
  /**
   * Additional data structure used to indicate the path taken by this node at any instance during
   * its traversal. The stack is emptied once it is no longer required to be referred.
   */
  private static Stack<Character> path = new Stack<>();

  /**
   * Constructor of this class which sets the symbol value in Character. Refer {@link
   * GenericCode#symbol} for more information.
   *
   * @param symbol of type Character.
   */
  GenericCode(Character symbol) {
    this.symbol = symbol;
  }

  /**
   * Starts from the calling node to check the code length at each step. If the code length is 1, it
   * appends a new character of type {@link CharacterSymbol} to the end as leaf node, otherwise it
   * updates the value as the first character in the code for transition node of type {@link
   * CodeSymbol} and recurse with the code substring starting from second character.
   *
   * @param characterSymbol of type {@link Code}.
   * @param code            of type String which needs to be added.
   */
  @Override
  public void addCode(Code characterSymbol, String code) throws IllegalStateException {
    //if it is the last code to be added, add character type code.
    if (code.length() == 1) {
      this.addCharacterNode(code.charAt(0), characterSymbol);
    } else {
      //if it is the transition node that needs to be added.
      if (!this.getChildrenNodes().containsKey(code.charAt(0))) {
        this.addTransitionNode(code.charAt(0));
      }
      this.getChildrenNodes().get(code.charAt(0)).addCode(characterSymbol, code.substring(1));
    }
  }

  /**
   * Traverses over each encoded message character and appends to the string only when a character
   * type node is encountered. When the character type node is reached, it again resets the current
   * pointer to the root to find the remaining values. If message is null or empty, it returns empty
   * decoded string.
   *
   * @param message which is encoded in String.
   * @return String containing decoded value.
   * @throws IllegalStateException in case decoding fails.
   */
  @Override
  public String decode(String message) throws IllegalStateException {
    StringBuilder sb = new StringBuilder("");
    if (message != null && !message.equals("")) {
      GenericCode current = this;
      boolean isComplete = false;
      for (Character character : message.toCharArray()) {
        isComplete = false;
        if (!current.getChildrenNodes().isEmpty()) {
          current = (GenericCode) current.getChildrenNodes().get(character);
          if (current.isCharacterSymbol()) {
            sb.append(current.getSymbol());
            isComplete = true;
            current = this;
          }
        }
      }
      if (!isComplete) {
        throw new IllegalStateException("Cannot decode. Did not find decoded message for the "
                + "given encoded string.");
      }
    }
    return sb.toString();
  }

  /**
   * This method finds all the codes present in the tree. It starts with the root node and if the
   * node is of type {@link CharacterSymbol}, it appends string value in the StringBuilder along
   * with the path it travelled to reach this node. It clears the value from the stack to keep it
   * ready for the next character node. If the node encountered is of type {@link CodeSymbol}, it
   * simply appends in the stack and recursively calls the child nodes. It clears up all the path
   * data at the end.
   *
   * @return String containing all the values of code.
   */
  public String allCodes() {
    StringBuilder sb = new StringBuilder("");
    if (this.isCharacterSymbol()) {
      sb.append(this.getSymbol());
      sb.append(":");
      for (Character c : path) {
        sb.append(c);
      }
      sb.append("\n");
      path.pop();
      return sb.toString();
    }
    for (Character code : this.getChildrenNodes().keySet()) {
      path.add(code);
      sb.append(this.getChildrenNodes().get(code).allCodes());
    }
    if (!path.isEmpty()) {
      path.pop();
    }
    return sb.toString();
  }

  /**
   * This method checks if the code is complete. It has been overridden by the {@link CodeSymbol} to
   * check if the coding tree is complete.
   *
   * @return boolean value containing if the coding tree is complete.
   */
  @Override
  public boolean isCodeComplete() {
    return false;
  }

  /**
   * Returns the value of symbol.
   *
   * @return character containing value of symbol.
   */
  @Override
  public Character getSymbol() {
    return this.symbol;
  }

  /**
   * Determines if calling object is of type {@link CharacterSymbol}.
   *
   * @return boolean if it is of type CharacterSymbol.
   */
  protected boolean isCharacterSymbol() {
    return false;
  }

  /**
   * Determines if calling object is of type {@link CodeSymbol}.
   *
   * @return boolean if it is of type CodeSymbol.
   */
  protected boolean isCodeSymbol() {
    return false;
  }

  /**
   * Appends a new character node of type {@link CharacterSymbol} at the end of transition node.
   *
   * @param character containing the value of character symbol.
   * @param code      of type CharacterSymbol that needs to be added.
   */
  protected void addCharacterNode(Character character, Code code) {
    //This method is overridden by CodeSymbol class.
  }

  /**
   * Appends a new transition node of type {@link CodeSymbol} at the end of transition node.
   *
   * @param character containing the coding symbol value that needs to be added.
   */
  protected void addTransitionNode(Character character) {
    //This method is overridden by CodeSymbol class.
  }

  /**
   * Fetches all the children of a node. This method has been overridden by {@link CodeSymbol}.
   *
   * @return map containing children.
   */
  protected Map<Character, Code> getChildrenNodes() {
    return null;
  }


}
