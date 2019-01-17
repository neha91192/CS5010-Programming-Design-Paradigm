package questionaire;

/**
 * This concrete class implements functionality for Likert type question. This is an opinion based
 * question class and therefore there is no "Correct" or "Incorrect" answer for this type of
 * question.It is expected for this type of question to evaluate correct in case the provided answer
 * belongs to {@link LikertScale}.
 */
public class LikertQuestion extends AbstractQuestion {
  /**
   * The default answer of LikertQuestion. This can be instantiated with anything value that is a
   * valid format for this type of Question. The evaluate method takes care of not considering this
   * attribute while evaluating the answer.
   */
  private static final String DEFAULT_ANSWER = LikertScale.STRONGLY_AGREE.getValue();

  /**
   * Constructor of this class which accepts text of LikertQuestion and sets this value along with a
   * default answer value.
   *
   * @param text of type String containing text attribute of Question.
   */
  public LikertQuestion(String text) {
    super(text, DEFAULT_ANSWER);
  }

  /**
   * This method evaluates correctness of answer to this question type. Generally speaking, an
   * answer to this type of question is valid if it lies within the range mentioned in {@link
   * LikertScale}. The answer value is the represented in String. For example, if user wants to
   * select {@link LikertScale#STRONGLY_AGREE}, the answer parameter should contain the
   * corresponding value to this scale, which is determined by calling {@link
   * LikertScale#getValue()}
   *
   * @return evaluation output which could be "Correct" or "Incorrect".
   * @see LikertScale enumeration class.
   * @see EvaluationResult enumeration class.
   */
  @Override
  public String evaluate(String answer) {
    try {
      if (validateAnswerFormat(answer)) {
        return EvaluationResult.CORRECT.name();
      }
    } catch (IllegalArgumentException e) {
      return EvaluationResult.INCORRECT.name();
    }
    return EvaluationResult.INCORRECT.name();
  }


  /**
   * Implements the abstract functionality to verify if the provided answer has the valid format. An
   * answer to Likert type of Question is valid in case the provided answer belongs to one of the
   * option values described in {@link LikertScale} enumeration.
   *
   * @param answer of type String which needs to be validated.
   */
  @Override
  protected boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    try {
      Integer.parseInt(answer);
      if (LikertScale.contains(answer)) {
        return true;
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_FORMAT);
    }
    return false;
  }

  /**
   * Returns priority of Likert type of Question fetched from {@link QuestionOrder}.
   *
   * @return order of this question.
   */
  protected Integer getQuestionOrder() {
    return QuestionOrder.LIKERT_QUESTION;
  }


  /**
   * Checks if this type of Question equals LikertQuestion type of Question.
   *
   * @param question of type {@link LikertQuestion}
   * @return true if it is equal, otherwise false
   */
  protected boolean equalsLikertQuestion(LikertQuestion question) {
    return true;
  }


  /**
   * Overrides equals method of Object to determine equality of two Questions. Any two Questions are
   * equal if they are of same instance and their Question Text is exactly the same.
   * <p>This method immediately returns false if incoming object is not of type {@link
   * AbstractQuestion}</p>
   * <p>Otherwise it checks if the object is an instance of LikertQuestion by calling {@link
   * LikertQuestion#equalsLikertQuestion(LikertQuestion)} and then checks if comparing two question
   * texts returns 0. The output of these two condition is returned.</p>
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof AbstractQuestion)) {
      return false;
    }
    AbstractQuestion question = (AbstractQuestion) object;
    return (question.equalsLikertQuestion(this)
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
