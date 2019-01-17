package questionaire;

/**
 *
 */
public class YesNoQuestion extends AbstractQuestion {


  /**
   * Sets two values for YesNo type of question - the question text which is inherited from
   * AbstractQuestion, and correctAnswer which is deemed to be a valid correctAnswer if the format matches with
   * the expected correctAnswer format of this question. The constructor of super class handles this
   * validation by calling abstract method {@link AbstractQuestion#validateAnswerFormat(String)} of
   * calling implementation.
   */
  public YesNoQuestion(String text, String correctAnswer) throws IllegalArgumentException {
    super(text, correctAnswer);
  }


  /**
   * Abstract method implementation which when invoked by object of this class would check for valid
   * correctAnswer format related to correctAnswer format of this question type. For YesNoQuestion type question,
   * it should be "Yes", "No", "YES", "NO", "yes", "no".
   *
   * @throws IllegalArgumentException in case the correctAnswer format is incorrect for this type of
   *                                  question.
   */
  @Override
  public boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    if (!(answer.equalsIgnoreCase("Yes") ||
            answer.equalsIgnoreCase("No"))) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_CORRECT_ANSWER_YESNO);
    }
    return true;
  }

  /**
   * Implements something similar to equals method. Compares user attempted question object with
   * that of standard question calling object. Evaluation returns "Correct" if text and correctAnswer
   * fields are same. For this type of question, correctAnswer can be case insensitive and so "Yes", "YES"
   * or "yes" would be regarded as the same.
   *
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
}
