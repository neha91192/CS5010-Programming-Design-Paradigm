package questionaire;

/**
 * Describes a fixed, 5-point LikertScale for {@link LikertQuestion} type.
 */
public enum LikertScale {

  STRONGLY_AGREE("1"), AGREE("2"), NEITHER_AGREE("3"), DISAGREE("4"), STRONGLY_DISAGREE("5");

  /**
   * Option value of LikertScale. It can be between 1-5 for the current implementation.
   */
  private final String value;

  /**
   * Constructor which sets evaluation result value.
   */
  LikertScale(String value) {
    this.value = value;
  }

  /**
   * Returns value of LikertScale in String.
   *
   * @return value of type String.
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Checks if the provided option value is one of the option values supported by this enumeration
   * class.
   *
   * @param value of type String containing option values.
   * @return true in case the option value is present, otherwise false.
   */
  public static boolean contains(String value) {
    for (LikertScale likertValue : LikertScale.values()) {
      if (likertValue.value.equals(value)) {
        return true;
      }
    }
    return false;
  }
}


