package questionaire;

/**
 *
 */
public enum LikertScale {

  STRONGLY_AGREE("1"), AGREE("2"), NEITHER_AGREE("3"), DISAGREE("4"), STRONGLY_DISAGREE("5");

  /**
   * Value of Evaluation result.
   */
  private final String value;

  /**
   * Constructor which sets evaluation result value.
   */
  LikertScale(String value) {
    this.value = value;
  }

  /**
   * Returns value of Evaluation Result in String.
   * @return value of type String.
   */
  public String getValue() {
    return this.value;
  }

  public static boolean contains(String value) {
    for(LikertScale likertValue: LikertScale.values()) {
      if(likertValue.value == value) {
        return true;
      }
    }
    return false;
  }
}


