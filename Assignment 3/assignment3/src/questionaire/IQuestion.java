package questionaire;

/**
 *
 */
public interface IQuestion {
  /**
   *
   * @param answer
   * @return
   */
  IQuestion setUserAnswer(String answer);

  /**
   *
   * @return
   */
  String evaluate(IQuestion question);

  /**
   *
   * @return
   */
  QuestionType getQuestionType();

  /**
   *
   * @return
   */
  String getQuestionText();
}
