package questionaire;

/**
 *
 */
public enum QuestionType {
  YES_NO(1), LIKERT(2), MULTIPLE_CHOICE(3), MULTIPLE_ANSWER(4);

  /**
   * Value of the natural ordering.
   */
  private final Integer order;

  /**
   * Constructor which sets question ordering order.
   */
  QuestionType(Integer order) {
    this.order = order;
  }

  /**
   * Returns order of Question ordering in Integer.
   * @return order of type Integer.
   */
  public Integer getOrder() {
    return this.order;
  }
}
