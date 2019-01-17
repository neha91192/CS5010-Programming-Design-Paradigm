package questionaire;

/**
 * Constants class designated to keep set of Exceptions messages.
 */
public final class ExceptionMessageConstants {
  /**
   * Error message indicating invalid correct answer input for Yes or No type questions.
   */
  public static final String INVALID_CORRECT_ANSWER_YESNO = "Not a valid answer format "
          +"for Yes or No type questions";
  /**
   * Error message indicating invalid size of options
   */
  public static final String INVALID_OPTIONS_SIZE_MCQ = "There should be at least 3 options or"
          +" maximum 8 options";
  /**
   * Error message if the input is given in incorrect order.
   */
  public static final String INVALID_MULTIPLE_ANSWER_MCQ = "Cannot have multiple answers for "
          +"Multiple Choice type questions";
  /**
   * Error message if the input is given in incorrect order.
   */
  public static final String INVALID_ANSWER_NOT_AN_OPTION = "Provided answer is not a valid "
          +"option number";
  /**
   * Error message if the input provided is not a valid character for calculator.
   */
  public static final String INVALID_INPUT_CHARACTER = "Not a valid input character. It should be"
          + " either between 0-9, +, -, *, C or =";
}

