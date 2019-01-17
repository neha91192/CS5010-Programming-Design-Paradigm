package decoder;

/*
 * This class extends {@link GenericCode} class to represent a leaf node in the coding tree.
 * This node cannot contain any children and stores character value which contains actual value of
 * the encoded string.
 */
public class CharacterSymbol extends GenericCode {
  /**
   * Initializes value of the symbol corresponding to this Character node.
   *
   * @param symbol of type Character.
   */
  CharacterSymbol(Character symbol) {
    super(symbol);
  }

  /**
   * Determines if calling object is of type {@link CharacterSymbol}.
   *
   * @return boolean if it is of type CharacterSymbol.
   */
  @Override
  protected boolean isCharacterSymbol() {
    return true;
  }

  /**
   * Determines if calling object is of type {@link CodeSymbol}.
   *
   * @return boolean if it is of type CodeSymbol.
   */
  @Override
  protected boolean isCodeSymbol() {
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
   * Overrides {@link GenericCode#isCodeComplete()} by returning true for all the character
   * symbols.
   *
   * @return true indicating that character leaf nodes are always complete.
   */
  @Override
  public boolean isCodeComplete() {
    return true;
  }
}
