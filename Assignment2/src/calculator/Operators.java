package calculator;

/**
 * Set of operators as Enumeration supported by {@link AbstractCalculator}.
 */
public enum Operators {
  ADD('+'), SUBTRACT('-'), MULTIPLY('*');

  /**
   * The signed value for all the operators.
   */
  private final char sign;

  /**
   * Constructor which sets sign value of type Character. It could be '+', '-', '*'.
   */
  Operators(char sign) {
    this.sign = sign;
  }

  /**
   * Returns value of the sign for any given enum value.
   * @return
   */
  public char getSign() {
    return this.sign;
  }
}


