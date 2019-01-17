package questionaire;

/**
 * Multiple Choice type questions are of type Multiple Answers. It calls constructor of its Super
 * class which checks validity of question and options and set the desired values in the
 * constructor. This implementation accepts any additional whitespace provided in user answer but
 * the answer must contain valid option number.
 */
public class MultipleChoiceQuestion extends MultipleAnswersQuestion {

  /**
   * Sets three values for MultipleChoiceQuestion - the question text which is inherited from
   * AbstractQuestion, and correctAnswer which is deemed to be a valid correctAnswer if the format
   * matches with the expected correctAnswer format of this question, and the options. The
   * constructor of super class handles this validation by calling abstract method {@link
   * AbstractQuestion#validateAnswerFormat(String)} of calling implementation.
   *
   * @param text          representing question description in String.
   * @param options       representing list of options to set.
   * @param correctAnswer representing correct answer of the question.
   * @throws IllegalArgumentException in case correctAnswer or option is not of valid format.
   */
  public MultipleChoiceQuestion(String text, String[] options, String correctAnswer)
          throws IllegalArgumentException {
    super(text, options, correctAnswer);
  }

  /**
   * It checks if the provided answer is of valid answer format and if it is true, it simply checks
   * if the answer provided is equal to the correct answer. It ignores additional whitespaces and so
   * "1" or " 1" would be regarded as valid correct answer.It returns "Correct" if answer matches
   * with the correct answer, otherwise in the failure of any of these cases, it returns
   * "Incorrect".
   *
   * @param answer in String that needs to be validated.
   * @return String of type {@link EvaluationResult#getValue()}
   */
  @Override
  public String evaluate(String answer) {
    try {
      if (validateAnswerFormat(answer) && this.correctAnswer.trim().equals(answer.trim())) {
        return EvaluationResult.CORRECT.name();
      }
      return EvaluationResult.INCORRECT.name();
    } catch (IllegalArgumentException e) {
      return EvaluationResult.INCORRECT.name();
    }
  }

  /**
   * Abstract method implementation which when invoked by object of this class would check for valid
   * correctAnswer format related to correctAnswer format of this question type. For
   * MultipleChoiceQuestion type question, it should be a String separated by whitespace character.
   * String should have only one option which should be of type Integer and is the correct answer.
   *
   * @param answer in String that needs to be validated.
   * @return boolean in case answer is valid.
   * @throws IllegalArgumentException in case the correctAnswer format is incorrect for this type of
   *                                  question.
   */
  @Override
  protected boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    String[] answerOptions = this.splitString(answer);
    if (answerOptions.length != 1) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_MULTIPLE_ANSWER_MCQ);
    } else {
      try {
        Integer.parseInt(answerOptions[0]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_NOT_AN_OPTION);
      }
    }
    return true;
  }

  /**
   * Returns priority of Multiple Choice type of Question.
   *
   * @return order of this question.
   */
  protected Integer getQuestionOrder() {
    return QuestionOrder.MULTIPLE_CHOICE_QUESTION;
  }

  /**
   * Checks if this type of Question equals MultipleChoiceQuestion type of Question.
   *
   * @param question of type {@link MultipleChoiceQuestion}
   * @return true if it is equal, otherwise false
   */
  protected boolean equalsMultipleChoiceQuestion(MultipleChoiceQuestion question) {
    return true;
  }

  /**
   * Overrides equals method of Object to determine equality of two Questions. Any two Questions are
   * equal if they are of same instance and their Question Text is exactly the same.
   * <p>This method immediately returns false if incoming object is not of type {@link
   * AbstractQuestion}</p>
   * <p>Otherwise it checks if the object is an instance of YesNoQuestion by calling {@link
   * MultipleChoiceQuestion#equalsMultipleChoiceQuestion(MultipleChoiceQuestion)} and then checks if
   * comparing two question texts returns 0. The output of these two condition is returned.</p>
   *
   * @param object which needs to be checked for equality.
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof AbstractQuestion)) {
      return false;
    }
    AbstractQuestion question = (AbstractQuestion) object;
    return (question.equalsMultipleChoiceQuestion(this)
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
