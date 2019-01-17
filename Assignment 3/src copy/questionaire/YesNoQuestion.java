package questionaire;

/**
 * Implements the strategy required for implementing Yes No type of Questions. While setting
 * question text, it checks if correct answer is either "Yes" or "No" and is case insensitive. User
 * can answer "YES" or "yes" and evaluation result will be correct in case correct answer is "Yes".
 */
public class YesNoQuestion extends AbstractQuestion {

  /**
   * Constant for Yes.
   */
  private static final String YES = "Yes";
  /**
   * Constant for No.
   */
  private static final String NO = "No";

  /**
   * <p>Sets two values for YesNo type of question - the question text which is inherited from
   * AbstractQuestion, and correctAnswer which is deemed to be a valid correctAnswer if the format
   * matches with the expected correctAnswer format of this question.</p>
   * <p>The constructor of super class handles this validation by calling abstract method {@link
   * AbstractQuestion#validateAnswerFormat(String)} of calling implementation.</p>
   *
   * @param text          representing question description in String.
   * @param correctAnswer representing correct answer of the question.
   */
  public YesNoQuestion(String text, String correctAnswer) throws IllegalArgumentException {
    super(text, correctAnswer);
  }


  /**
   * Abstract method implementation which when invoked by object of this class would check for valid
   * correctAnswer format related to correctAnswer format of this question type. For YesNoQuestion
   * type question, it should be "Yes", "No", "YES", "NO", "yes", "no".
   *
   * @param answer in String that needs to be validated.
   * @throws IllegalArgumentException in case the correctAnswer format is incorrect for this type of
   *                                  question.
   */
  @Override
  protected boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    if (!(answer.equalsIgnoreCase(YES) || answer.equalsIgnoreCase(NO))) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_FORMAT);
    }
    return true;
  }

  /**
   * It simply checks if the answer provided is equal to the correct answer. It returns "Correct" if
   * answer matches with the correct answer, otherwise "Incorrect".
   *
   * @param answer in String that needs to be validated.
   * @return String of type {@link EvaluationResult#getValue()}
   */
  @Override
  public String evaluate(String answer) {
    if (this.correctAnswer.equalsIgnoreCase(answer)) {
      return EvaluationResult.CORRECT.name();
    } else {
      return EvaluationResult.INCORRECT.name();
    }
  }

  /**
   * Returns the order of YesNo type of Question in Question Bank fetched from {@link
   * QuestionOrder}.
   *
   * @return order of this question.
   */
  protected Integer getQuestionOrder() {
    return QuestionOrder.YES_NO_QUESTION;
  }


  /**
   * Checks if this type of Question equals YesNo type of Question.
   *
   * @param question of type {@link YesNoQuestion}
   * @return true if it is equal, otherwise false.
   */
  protected boolean equalsYesNoQuestion(YesNoQuestion question) {
    return true;
  }

  /**
   * Overrides equals method of Object to determine equality of two Questions. Any two Questions are
   * equal if they are of same instance and their Question Text is exactly the same.
   * <p>This method immediately returns false if incoming object is not of type {@link
   * AbstractQuestion}</p>
   * <p>Otherwise it checks if the object is an instance of YesNoQuestion by calling {@link
   * YesNoQuestion#equalsYesNoQuestion(YesNoQuestion)} and then checks if comparing two question
   * texts returns 0. The output of these two condition is returned.</p>
   *
   * @param object which needs to be checked for equality.
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof AbstractQuestion)) {
      return false;
    }
    AbstractQuestion question = (AbstractQuestion) object;
    return (question.equalsYesNoQuestion(this)
            && this.compareQuestionText(question.getQuestionText()) == 0);
  }

  /**
   * Overriding hashcode implementation to include question text.
   *
   * @return int which contains hashcode value.
   */
  @Override
  public int hashCode() {
    int prime = 31;
    int result = 1;
    if (getQuestionText() != null) {
      result = prime * result + getQuestionText().hashCode();
    }
    if (getQuestionOrder() != null) {
      result = prime * result + getQuestionOrder() ^ (getQuestionOrder() >>> 32);
    }
    return result;
  }
}
