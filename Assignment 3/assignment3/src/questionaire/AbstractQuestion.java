package questionaire;

/**
 *
 */
public abstract class AbstractQuestion implements IQuestion, Comparable<IQuestion> {
  /**
   * This represents complete question text.
   */
  final String text;
  /**
   * Represents answer of this question object. It can be correct or incorrect depending upon the
   * what the standard answer is of the same question. Also, any invalid answer format stored in
   * this object will be regarded as incorrect.
   */
  final String answer;

  /**
   * Responsibility of storing what type of question is this object is.
   */
  final QuestionType questionType;


  /**
   *
   * @param text
   */
  public AbstractQuestion(String text, String answer) throws IllegalArgumentException {
    this.text = text;
    if (this.validateAnswerFormat(answer)) {
      this.answer = answer;
    } else {
      this.answer = "";
    }
    this.questionType = this.getQuestionType();

  }

  /**
   *
   * @param answer
   * @return
   */
  @Override
  public abstract IQuestion setUserAnswer(String answer);

  /**
   *
   * @return
   */
  @Override
  public String evaluate(IQuestion question) {
    return null;
  }

  /**
   *
   * @param answer
   * @return
   */
  protected abstract boolean validateAnswerFormat(String answer);

  /**
   * Abstract method to be implemented by concrete classes to return what type of question is this
   * class is.
   */
  public abstract QuestionType getQuestionType();

  /**
   *
   * @param question
   * @return
   */
  public int compareTo(IQuestion question) {
    if (this.getQuestionType().getOrder() - question.getQuestionType().getOrder() < 0) {
      return -1;
    }
    if (this.getQuestionType().getOrder() - question.getQuestionType().getOrder() > 0) {
      return 1;
    }

    return this.getQuestionText().compareTo(question.getQuestionText());
  }

  public String getQuestionText() {
    return this.text;
  }
}
