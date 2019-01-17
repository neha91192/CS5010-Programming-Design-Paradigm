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
    if (validateAnswerFormat(answer)) {
      return EvaluationResult.CORRECT.name();
    } else {
      return EvaluationResult.INCORRECT.name();
    }
  }


  /**
   * Implements the abstract functionality to verify if the provided answer has the valid format. An
   * answer to Likert type of Question is valid in case the provided answer belongs to one of the
   * option values described in {@link LikertScale} enumeration.
   *
   * @param answer of type String which needs to be validated.
   */
  @Override
  public boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    try {
      Integer.parseInt(answer);
      if(LikertScale.contains(answer)){
        return true;
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_CORRECT_ANSWER_LIKERT);
    }
    return false;
  }


}
