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
 * <p>If a new Question type needs to extend this Abstract class, following needs to be
 * implemented:</p>
 * <ul>
 * <li>Add an Ordering value in {@link QuestionOrder} which will decide natural order of the
 * new question type in Question bank.</li>
 * <li>Implement {@link AbstractQuestion#getQuestionOrder()} in its class and return this
 * value.</li>
 * <li>Override a new method which will state if any question is equal to this new question. It is
 * desirable to override equals method as shown in {@link YesNoQuestion#equals(Object)}</li>
 * </ul>
 */
public abstract class AbstractQuestion implements IQuestion, Comparable {
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
   * Every collection of items has a natural order. To support this behavior of question object in a
   * questionaire, this attribute will help in deciding the priority of this question in the
   * Question Bank.
   */
  private final Integer order;

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
    this.order = getQuestionOrder();
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

  /**
   * Abstract method which any of the Concrete class inheriting this abstract class has to
   * implement. It returns order followed for that particular type of question in Question bank.
   */
  protected abstract Integer getQuestionOrder();

  /**
   * <p>This method helps in comparing two objects with certain order. It accepts object
   * instance and checks for further order only if it is an instance of type {@link
   * AbstractQuestion}. If it is not, it assigns lower priority to the incoming object and returns
   * -1 in that case.</p>
   * </p>For this implementation, we compare the order of a question which is retrieved from
   * {@link AbstractQuestion#getQuestionOrder()} method and the following could be the cases:</p>
   * <ul>
   * <li><strong>this.order&lt;question.order:</strong> returns -1</li>
   * <li><strong>this.order&gt;question.order:</strong> returns 1</li>
   * <li><strong>this.order equals question.order:</strong> It calls the equals implementation in
   * the respective classes which defines equality for two question objects. If the question order
   * is same, it checks for lexicographical order of Question text and returns the value fetched
   * from {@link AbstractQuestion#compareQuestionText(String)}</li>
   * </ul>
   *
   * @param object of type Object which needs to be compared with "this".
   * @return int depending upon the cases discussed above.
   */
  @Override
  public int compareTo(Object object) {
    if (object instanceof AbstractQuestion) {
      AbstractQuestion question = (AbstractQuestion) object;
      if (this.order < question.getQuestionOrder()) {
        return -1;
      }
      if (this.order > question.getQuestionOrder()) {
        return 1;
      } else if (question.equals(this)) {
        return 0;
      }
      return this.compareQuestionText(question.getQuestionText());
    }
    return -1;
  }

  /**
   * Checks for the Alphabetical sort of two String objects. Uses the boilerplate compareTo function
   * implemented by String.
   *
   * @param questionText of type String.
   * @return -1, 0, 1 depending upon the lexicographic order of text.
   */
  protected int compareQuestionText(String questionText) {
    return this.getQuestionText().compareTo(questionText);
  }

  /**
   * Checks if this type of Question equals YesNo type of Question.
   *
   * @param question of type {@link YesNoQuestion}
   * @return true if it is equal, otherwise false
   */
  protected boolean equalsYesNoQuestion(YesNoQuestion question) {
    return false;
  }

  /**
   * Checks if this type of Question equals LikertQuestion type of Question.
   *
   * @param question of type {@link LikertQuestion}
   * @return true if it is equal, otherwise false
   */
  protected boolean equalsLikertQuestion(LikertQuestion question) {
    return false;
  }

  /**
   * Checks if this type of Question equals MultipleChoiceQuestion type of Question.
   *
   * @param question of type {@link MultipleChoiceQuestion}
   * @return true if it is equal, otherwise false
   */
  protected boolean equalsMultipleChoiceQuestion(MultipleChoiceQuestion question) {
    return false;
  }

  /**
   * Checks if this type of Question equals MultipleAnswersQuestion type of Question.
   *
   * @param question of type {@link MultipleAnswersQuestion}
   * @return true if it is equal, otherwise false
   */
  protected boolean equalsMultipleAnswerQuestion(MultipleAnswersQuestion question) {
    return false;
  }


}
