package decoder;

import java.util.HashMap;
import java.util.Map;

/*
 * This class extends {@link GenericCode} class to represent a transition node in the coding tree.
 * This node can contain any number of children and stores encoded coding symbol value.
 */
public class CodeSymbol extends GenericCode {
  /**
   * List of codes that are children of the current code. The possible number of children at each
   * level would be the total values defined.
   */
  private Map<Character, Code> children;

  /**
   * Contains coding symbol value which this node class can hold.
   */
  private String codingSymbols;

  /**
   * Constructs CodeSymbol by updating symbol value, initialising its children.
   *
   * @param symbol        value of type Character.
   * @param codingSymbols coding symbol values supported by the node.
   */
  CodeSymbol(Character symbol, String codingSymbols) {
    super(symbol);
    this.codingSymbols = codingSymbols;
    children = new HashMap<>();
  }

  /**
   * Determines if calling object is of type {@link CharacterSymbol}.
   *
   * @return boolean if it is of type CharacterSymbol.
   */
  @Override
  protected boolean isCharacterSymbol() {
    return false;
  }

  /**
   * Determines if calling object is of type {@link CodeSymbol}.
   *
   * @return boolean if it is of type CodeSymbol.
   */
  @Override
  protected boolean isCodeSymbol() {
    return true;
  }

  /**
   * Appends a new character node of type {@link CharacterSymbol} at the end of transition node.
   *
   * @param character containing the value of character symbol.
   * @param code      of type CharacterSymbol that needs to be added.
   */
  @Override
  protected void addCharacterNode(Character character, Code code) {
    this.children.put(character, code);
  }

  /**
   * Appends a new transition node of type {@link CodeSymbol} at the end of transition node.
   *
   * @param character containing the coding symbol value that needs to be added.
   */
  @Override
  protected void addTransitionNode(Character character) {
    this.children.put(character, new CodeSymbol(character, this.codingSymbols));
  }

  /**
   * Fetches all the children of {@link CodeSymbol} node.
   *
   * @return map containing children.
   */
  @Override
  protected Map<Character, Code> getChildrenNodes() {
    return this.children;
  }

  /**
   * Checks if the coding tree is complete. It directly returns true if it is of type {@link
   * CharacterSymbol}. Otherwise it checks if the size of children is complete, that is, it matches
   * with the total number of children. If that is true, it checks recursively for the same
   * condition and returns the boolean value at the end.
   *
   * @return boolean indicating if the code is complete.
   */
  @Override
  public boolean isCodeComplete() {
    if (this.isCharacterSymbol()) {
      return true;
    }
    boolean isComplete = this.getChildrenNodes().size() == codingSymbols.length();
    if (isComplete) {
      for (Character child : this.getChildrenNodes().keySet()) {
        isComplete = this.getChildrenNodes().get(child).isCodeComplete();
        if (!isComplete) {
          break;
        }
      }
    }
    return isComplete;
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

}
