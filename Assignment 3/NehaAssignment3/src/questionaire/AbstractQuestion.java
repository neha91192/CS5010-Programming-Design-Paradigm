package questionaire;

/**
 * This is Abstract implementation class for {@link IQuestion} interface. All Question types that
 * are expected to follow this behavior can extend this class and reuse common functionality.
 * <p>This class implements the following methods from {@link IQuestion} interface:</p>
 * <ul>
 * <li><Strong>getQuestionText</Strong></li>
 * <li><Strong>evaluate</Strong></li>
 * </ul>
 * <p>Additionally, it has abstracted an answer validator method: <code>validateAnswerFormat()
 * </code> which is responsible for checking if the answer format is correct or not for a given
 * Question type.</p>
 */
public abstract class AbstractQuestion implements IQuestion {
  /**
   * This represents complete question text or description. This field has been made immutable.
   */
  private final String text;
  /**
   * Represents correctAnswer of this question. Before setting up this value, validity of the answer
   * is checked depending upon the type of the question. Once the answer is acceptable, it is set in
   * the constructor.
   */
  final String correctAnswer;

  /**
   * <p>This represents constructor of the AbstractQuestion class. By default, it stores two
   * general attributes, question text and correctAnswer. Both the attributes are of type
   * String.</p>
   * <p>Before setting up the correct answer, it checks if the answer format is valid.
   * Checking the answer format depends upon the Question type and therefore it has been abstracted
   * at this level.</p>
   * <p>Both the values, text and correctAnswer are immutable fields. They are not intended to be
   * changed or updated at the current object level. If implementer wishes to update this data, a
   * new object can be created with the updated data.</p>
   * <p>The object will not be created in case the correctAnswer is not in valid format. It throws
   * {@link IllegalArgumentException}</p> in that case.
   *
   * @param text          of type String containing description or text of Question.
   * @param correctAnswer of type String containing correct answer of the Question.
   * @throws IllegalArgumentException in case the correctAnswer is not in desired format.
   */
  public AbstractQuestion(String text, String correctAnswer) throws IllegalArgumentException {
    this.text = text;
    if (this.validateAnswerFormat(correctAnswer)) {
      this.correctAnswer = correctAnswer;
    } else {
      this.correctAnswer = "";
    }

  }

  /**
   * Returns value of question text. The Question Text attribute is common between all the question
   * types and therefore the default implementation works fine for the current requirement. In
   * future, any question type which has different way of storing question text can override this
   * method.
   *
   * @return text of type String.
   */
  public String getQuestionText() {
    return this.text;
  }

  /**
   * Default implementation of evaluate method. If the two answer strings are equal, then the
   * evaluation result would be "Correct" otherwise it will be "Incorrect". Not enforcing other
   * question classes to implement this method by making it abstract because some question types
   * don't need to be evaluated or can use this default implementation.
   *
   * @param answer of type String which needs to be evaluated.
   */
  @Override
  public String evaluate(String answer) {
    if (this.correctAnswer.equals(answer)) {
      return EvaluationResult.CORRECT.name();
    } else {
      return EvaluationResult.INCORRECT.name();
    }
  }

  /**
   * This method has been made abstract for the reason that the validity of every answer format
   * depends on its question and it is the job of Concrete Question classes to define it's validity.
   * If the answer format doesn't need to be validated, Classes should implement and return true in
   * that case.
   *
   * @param answer of type String which needs to be validated.
   */
  protected abstract boolean validateAnswerFormat(String answer) throws IllegalArgumentException;


}
