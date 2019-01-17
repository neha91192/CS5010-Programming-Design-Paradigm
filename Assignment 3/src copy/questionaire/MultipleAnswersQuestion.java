package questionaire;

import java.util.Arrays;

/**
 * This is an implementation MultipleAnswersQuestion. This implementation accepts any additional
 * whitespace provided in user answer and considers options in the answer in any order but the
 * answer must contain valid option number. Also, while evaluating answers, this implementation will
 * not regard any duplicate options.
 */
public class MultipleAnswersQuestion extends AbstractQuestion {
  /**
   * Stores options provided while creating the question.
   */
  private String[] options;
  /**
   * Stores correct answer while creating the question.
   */
  private String[] answers;

  /**
   * Maximum value allowed for options.
   */
  private static final Integer OPTIONS_MAX_LENGTH = 8;
  /**
   * Minimum value allowed for options.
   */
  private static final Integer OPTIONS_MIN_LENGTH = 3;
  /**
   * Delimiter for splitting answer strings.
   */
  private static final String DELIMITER = "\\s+";

  /**
   * Sets three values for MultipleAnswersQuestion - the question text which is inherited from
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
  public MultipleAnswersQuestion(String text, String[] options, String correctAnswer)
          throws IllegalArgumentException {
    super(text, correctAnswer);
    isOptionsSizeValid(options);
    this.options = options;
    isAnswerValidOption();
  }

  /**
   * Implements something similar to equals method. Compares user attempted question object with
   * that of standard question calling object. Evaluation returns "Correct" if text and
   * correctAnswer fields are same. For this type of question, correctAnswer can be case insensitive
   * and so "Yes", "YES" or "yes" would be regarded as the same.
   *
   * @return String of type {@link EvaluationResult#getValue()}
   */
  @Override
  public String evaluate(String answer) {
    try {
      if (validateAnswerFormat(answer) && answersEqual(answer)) {
        return EvaluationResult.CORRECT.name();
      }
      return EvaluationResult.INCORRECT.name();
    } catch (IllegalArgumentException e) {
      return EvaluationResult.INCORRECT.name();
    }

  }

  /**
   * Returns true if answer array contains the same elements as provided in userAnswer.
   *
   * @param userAnswer of type String.
   * @return boolean if answers are equal.
   */
  private boolean answersEqual(String userAnswer) {
    String[] userAnswerOptions = splitString(userAnswer);
    return Arrays.equals(userAnswerOptions, this.answers);
  }

  /**
   * Abstract method implementation which when invoked by object of this class would check for valid
   * correctAnswer format related to correctAnswer format of this question type. For
   * MultipleAnswersQuestion type, it should be a String separated by whitespace character. String
   * can have only as many options supported by the maximum options range. Options provided in the
   * answer should be of type Integer and can be provided in any order.
   *
   * @param answer in String that needs to be validated.
   * @return boolean in case answer is valid.
   * @throws IllegalArgumentException in case the correctAnswer format is incorrect for this type of
   *                                  question.
   */
  @Override
  protected boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    String[] answers = splitString(answer);
    for (String option : answers) {
      try {
        Integer.parseInt(option);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_NOT_AN_OPTION);
      }
    }
    return true;
  }

  /**
   * If the number of options given in the input supports the valid size of options supported by
   * this Question type.
   *
   * @param options containing list of options in String.
   * @throws IllegalArgumentException if the options length is not in range supported.
   */
  private void isOptionsSizeValid(String[] options) throws IllegalArgumentException {
    if (options.length < OPTIONS_MIN_LENGTH || options.length > OPTIONS_MAX_LENGTH) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_OPTIONS_SIZE_MCQ);
    }
  }

  /**
   * Checks if the provided input value is in the range of options length. Sets answers array which
   * stores list of right options  if the answer is valid.
   *
   * @throws IllegalArgumentException in case the answer is not a valid option.
   */
  private void isAnswerValidOption() throws IllegalArgumentException {
    String[] answers = splitString(correctAnswer);
    for (String answer : answers) {
      if (Integer.parseInt(answer) < 1 || Integer.parseInt(answer) > this.options.length) {
        throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_NOT_AN_OPTION);
      }
    }
    this.answers = answers;
  }


  /**
   * Returns priority of Multiple answers type of Question.
   *
   * @return order of this question.
   */
  protected Integer getQuestionOrder() {
    return QuestionOrder.MULTIPLE_ANSWER_QUESTION;
  }

  /**
   * Checks if this type of Question equals MultipleAnswersQuestion type of Question.
   *
   * @param question of type {@link MultipleAnswersQuestion}
   * @return true if it is equal, otherwise false
   */
  protected boolean equalsMultipleAnswerQuestion(MultipleAnswersQuestion question) {
    return true;
  }

  /**
   * Takes String in input and returns an Array after splitting with the required delimiter.
   *
   * @param value of type String
   * @return array of type String
   */
  protected String[] splitString(String value) {
    return value.trim().split(DELIMITER);
  }

  /**
   * Overrides equals method of Object to determine equality of two Questions. Any two Multiple
   * Answer Questions are equal if they are of same instance and their Question Text is exactly the
   * same.
   * <p>This method immediately returns false if incoming object is not of type {@link
   * AbstractQuestion}</p>
   * <p>Otherwise it checks if the object is an instance of YesNoQuestion by calling {@link
   * MultipleAnswersQuestion#equalsMultipleAnswerQuestion(MultipleAnswersQuestion)} and then checks
   * if comparing two question texts returns 0. The output of these two condition is returned.</p>
   *
   * @param object which needs to be checked for equality.
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof AbstractQuestion)) {
      return false;
    }
    AbstractQuestion question = (AbstractQuestion) object;
    return (question.equalsMultipleAnswerQuestion(this)
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
