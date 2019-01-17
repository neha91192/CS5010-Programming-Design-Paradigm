package questionaire;

/**
 * Enumeration for storing evaluation result of question.
 */
public enum EvaluationResult {
  CORRECT("Correct"), INCORRECT("Incorrect");

  /**
   * Value of Evaluation result.
   */
  private final String value;

  /**
   * Constructor which sets evaluation result value.
   */
  EvaluationResult(String value) {
    this.value = value;
  }

  /**
   * Returns value of Evaluation Result in String.
   * @return value of type String.
   */
  public String getValue() {
    return this.value;
  }

}
