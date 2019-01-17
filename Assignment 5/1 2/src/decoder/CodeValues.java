package decoder;

/**
 *
 */
public enum CodeValues {
  ZERO("0"), ONE("1");

  /**
   * Value of Code.
   */
  private final String value;

  /**
   * Constructor which sets code value.
   */
  CodeValues(String value) {
    this.value = value;
  }

  /**
   * Returns value of the code in String.
   * @return value of type String.
   */
  public String getValue() {
    return this.value;
  }

}
