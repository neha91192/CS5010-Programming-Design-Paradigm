package questionaire;

/**
 *
 */
public class LikertQuestion extends AbstractQuestion {

  LikertScale[] options;

  /**
   *
   * @param text
   */
  public LikertQuestion(String text, String answer) {
    super(text, answer);
    this.options = LikertScale.values();
  }

  @Override
  public boolean validateAnswerFormat(String answer) {
    try{
      Integer.parseInt(answer);
    } catch (NumberFormatException e) {
      return false;
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
    IQuestion attemptedQuestion = new LikertQuestion(this.text, userAnswer);
    return attemptedQuestion;
  }


  /**
   *
   * @param question
   * @return
   */
  @Override
  public String evaluate(IQuestion question) {
    if (!(question instanceof LikertQuestion)) {
      return EvaluationResult.INCORRECT.name();
    }
    LikertQuestion attemptedQuestion = (LikertQuestion) question;
    if (this.text == attemptedQuestion.text &&   validateAnswerFormat(attemptedQuestion.answer) &&
            LikertScale.contains(attemptedQuestion.answer)) {
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
    return QuestionType.LIKERT;
  }

}
