package questionaire;

/**
 *
 */
public class MultipleAnswersQuestion extends AbstractQuestion {
  /**
   *
   */
  private String[] options;

  private static final Integer OPTIONS_MAX_LENGTH = 8;
  private static final Integer OPTIONS_MIN_LENGTH = 3;
  private static final String WHITECHAR_REGEX = "\\s+";

  /**
   *
   * @param text
   * @param answer
   * @param options
   */
  public MultipleAnswersQuestion(String text, String[] options, String answer)
          throws IllegalArgumentException {
    super(text, answer);
    if (isAnswerValidOption(options.length)) {
      setOptions(options);
    }

  }

  /**
   *
   * @param answer
   * @return
   */
  @Override
  public boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    answer = answer.replaceAll(WHITECHAR_REGEX, " ");
    String[] answers = answer.split(" ");
    for (String option : answers) {
      try {
        if (!option.equals("")) {
          Integer.parseInt(option);
        }
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
    String[] answers = answer.split(" ");
    for (String answer : answers) {
      if (Integer.parseInt(answer) < 1 || Integer.parseInt(answer) > optionLength) {
        throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_NOT_AN_OPTION);
      }
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
    IQuestion attemptedQuestion = new MultipleAnswersQuestion(this.text,this.options,userAnswer);
    return attemptedQuestion;
  }

  /**
   *
   * @return
   */
  @Override
  public QuestionType getQuestionType() {
    return QuestionType.MULTIPLE_ANSWER;
  }
}
