package questionaire;

/**
 * <p>This interface imposes a behavior to return Question text and evaluate answer of the question
 * depending upon what user has entered using object of the implementation class.</p>
 * <p>Evaluation of the answer is left for the implementer of this interface. Implementation
 * should return either two result format - "Correct" or "Incorrect".</p>
 */
public interface IQuestion {
  /**
   * Returns the complete text of the question. The purpose of this Getter method is to make the
   * Question text public by returning current question text set by the program. By implementing
   * this method, it would be helpful in identifying the question by its text or when grouped in set
   * of questions.
   */
  String getQuestionText();

  /**
   * This method accepts user answer as String and verifies if this values is "Correct" or
   * "Incorrect". Finding the correctness of the answer is kept for the implementer of this
   * interface and depending upon what the question type is, implementer can decide the conditions
   * to evaluate answer of the Question. Implementer has the freedom to add additional constraints
   * while evaluating answer of the Question.
   *
   * @param answer of type String which needs to be evaluated.
   */
  String evaluate(String answer);


}
