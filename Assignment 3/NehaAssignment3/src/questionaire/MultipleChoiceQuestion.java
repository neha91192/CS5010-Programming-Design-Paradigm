package questionaire;

/**
 * Multiple Choice type questions are of type Multiple Answers.
 */
public class MultipleChoiceQuestion extends AbstractQuestion {

  /**
   *
   */
  private final String[] options;
  private static final Integer OPTIONS_MAX_LENGTH = 8;
  private static final Integer OPTIONS_MIN_LENGTH = 3;
  private static final String DELIMITER = " ";

  /**
   *
   * @param text
   * @param answer
   * @param options
   */
  public MultipleChoiceQuestion(String text, String[] options, String answer)
          throws IllegalArgumentException{
    super(text, answer);
    isOptionsSizeValid(options);
    this.options = options;
    isAnswerValidOption();
  }

  /**
   *
   * @param answer
   * @return
   * @throws IllegalArgumentException
   */
  @Override
  public boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    String[] answerBuffer = answer.split(DELIMITER);
    if (answerBuffer.length != 1) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_MULTIPLE_ANSWER_MCQ);
    } else {
      try {
        Integer.parseInt(answerBuffer[0]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_NOT_AN_OPTION);
      }
    }
    return true;
  }

  /**
   *
   * @param options
   * @throws IllegalArgumentException
   */
  private void isOptionsSizeValid(String[] options) throws IllegalArgumentException {
    if (options.length < OPTIONS_MIN_LENGTH || options.length > OPTIONS_MAX_LENGTH) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_OPTIONS_SIZE_MCQ);
    }
  }

  /**
   *
   * @return
   */
  private void isAnswerValidOption() throws IllegalArgumentException {
    if (Integer.parseInt(correctAnswer) < 1 || Integer.parseInt(correctAnswer) > options.length) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_NOT_AN_OPTION);
    }
  }

  /**
   * Implements something similar to equals method. Compares user attempted question object with
   * that of standard question calling object. Evaluation returns "Correct" if text and correctAnswer
   * fields are same. For this type of question, correctAnswer can be case insensitive and so "Yes", "YES"
   * or "yes" would be regarded as the same.
   *
   * @return String of type {@link EvaluationResult#getValue()}
   */
  public String evaluate(String answer) {
    try{
      if (validateAnswerFormat(answer) && this.answersEqual(answer)) {
        return EvaluationResult.CORRECT.name();
      }
      return EvaluationResult.INCORRECT.name();
    } catch (IllegalArgumentException e) {
      return EvaluationResult.INCORRECT.name();
    }
  }
  /**
   *
   */
  private boolean answersEqual(String answer) {
    return (this.correctAnswer.trim().equals(answer.trim()));
  }



}
