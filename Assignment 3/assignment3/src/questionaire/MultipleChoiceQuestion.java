package questionaire;

/**
 * Multiple Choice type questions are of type Multiple Answers.
 */
public class MultipleChoiceQuestion extends AbstractQuestion{

  /**
   *
   */
  private String[] options;
  private static final Integer OPTIONS_MAX_LENGTH = 8;
  private static final Integer OPTIONS_MIN_LENGTH = 3;

  /**
   *
   * @param text
   * @param answer
   * @param options
   */
  public MultipleChoiceQuestion(String text, String[] options, String answer) {
    super(text, answer);
    if (isAnswerValidOption(options.length)) {
      setOptions(options);
    }
  }

  /**
   *
   * @param answer
   * @return
   * @throws IllegalArgumentException
   */
  @Override
  public boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    String[] answerBuffer = answer.split(" ");
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
  private void setOptions(String[] options) throws IllegalArgumentException {
    if (options.length < OPTIONS_MIN_LENGTH || options.length > OPTIONS_MAX_LENGTH) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_OPTIONS_SIZE_MCQ);
    }
    this.options = options;
  }

  /**
   *
   * @return
   */
  private boolean isAnswerValidOption(Integer optionLength) throws IllegalArgumentException {
    if (Integer.parseInt(answer) < 1 || Integer.parseInt(answer) > optionLength) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_NOT_AN_OPTION);
    }
    return true;
  }


  /**
   * Creates a new copy of question that user is attempting, while creating new object, it overrides
   * the answer value with that of user's. Returns attempted question instance.
   *
   * @return object of type {@link IQuestion}
   */
  @Override
  public IQuestion setUserAnswer(String userAnswer) {
    IQuestion attemptedQuestion = new MultipleChoiceQuestion(this.text, this.options, userAnswer);
    return attemptedQuestion;
  }

  /**
   *
   * @return
   */
  @Override
  public QuestionType getQuestionType() {
    return QuestionType.MULTIPLE_CHOICE;
  }

}
