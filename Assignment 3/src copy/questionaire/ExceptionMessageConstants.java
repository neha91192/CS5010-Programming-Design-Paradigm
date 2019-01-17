package questionaire;

/**
 * Constants class designated to keep set of Exceptions messages.
 */
public final class ExceptionMessageConstants {
  /**
   * Error message indicating invalid correct correctAnswer input for Yes or No type questions.
   */
  public static final String INVALID_ANSWER_FORMAT = "Not a valid correctAnswer format";

  /**
   * Error message indicating invalid size of options.
   */
  public static final String INVALID_OPTIONS_SIZE_MCQ = "There should be at least 3 options or"
          + " maximum 8 options";
  /**
   * Error message if the multiple choice answer has more than two.
   */
  public static final String INVALID_MULTIPLE_ANSWER_MCQ = "Cannot have multiple answers for "
          + "Multiple Choice type questions";
  /**
   * Error message if the input is given in incorrect order.
   */
  public static final String INVALID_ANSWER_NOT_AN_OPTION = "Provided correctAnswer is not a valid "
          + "option number";

}

