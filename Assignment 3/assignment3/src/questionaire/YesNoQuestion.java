package questionaire;

/**
 *
 */
public class YesNoQuestion extends AbstractQuestion {


  /**
   * Sets two values for YesNo type of question. The question text which is used from
   * AbstractQuestion, and answer which is deemed to be a valid answer if the format matches with
   * the expected answer format of this question. The constructor of super class handles this
   * validation by calling abstract method {@link AbstractQuestion#validateAnswerFormat(String)} of
   * calling implementation.
   */
  public YesNoQuestion(String text, String answer) throws IllegalArgumentException {
    super(text, answer);
  }


  /**
   * Abstract method implementation which when invoked by object of this class would check for valid
   * answer format related to answer format of this question type. For YesNoQuestion type question,
   * it should be "Yes", "No", "YES", "NO", "yes", "no".
   *
   * @throws IllegalArgumentException in case the answer format is incorrect for this type of
   *                                  question.
   */
  //todo should we support other format of answer
  @Override
  public boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    if (!(answer.equalsIgnoreCase("Yes") ||
            answer.equalsIgnoreCase("No"))) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_CORRECT_ANSWER_YESNO);
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
    IQuestion attemptedQuestion;
    try {
      attemptedQuestion = new YesNoQuestion(this.text, userAnswer);
    } catch (IllegalArgumentException e) {
      String incorrectAnswer;
      if (this.answer.equalsIgnoreCase("Yes")) {
        incorrectAnswer = "No";
      } else {
        incorrectAnswer = "Yes";
      }
      attemptedQuestion = new YesNoQuestion(this.text, incorrectAnswer);
    }

    return attemptedQuestion;
  }

  /**
   * Implements something similar to equals method. Compares user attempted question object with
   * that of standard question calling object. Evaluation returns "Correct" if text and answer
   * fields are same. For this type of question, answer can be case insensitive and so "Yes", "YES"
   * or "yes" would be regarded as the same.
   *
   * @return String of type {@link EvaluationResult#getValue()}
   */

  public String evaluate(IQuestion question) {
    if (!(question instanceof YesNoQuestion)) {
      return EvaluationResult.INCORRECT.name();
    }
    YesNoQuestion attemptedQuestion = (YesNoQuestion) question;
    if (this.text.equals(attemptedQuestion.text) &&
            this.answer.equalsIgnoreCase(attemptedQuestion.answer)) {
      return EvaluationResult.CORRECT.name();
    } else {
      return EvaluationResult.INCORRECT.name();
    }
  }

  /**
   *
   * @return
   */
  @Override
  public QuestionType getQuestionType() {
    return QuestionType.YES_NO;
  }


}
