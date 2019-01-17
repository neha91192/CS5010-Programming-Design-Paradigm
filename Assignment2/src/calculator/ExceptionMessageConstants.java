package calculator;

/**
 * Constants class designated to keep set of Exceptions messages which will be returned depending
 * upon the calculator inputs.
 */
public final class ExceptionMessageConstants {
  /**
   * Error message indicating input operand greater than 32 bits.
   */
  public static final String INPUT_SIZE_ERROR = "Cannot support value greater than 32 bits";
  /**
   * Error message indicating invalid sequence. Either operator is missing or operands and = is
   * pressed as an input.
   */
  public static final String MISSING_INPUT_ERROR = "Missing input error. Input sequence should be "
          + "operand, operator, operand";
  /**
   * Error message if the input is given in incorrect order.
   */
  public static final String INVALID_SEQUENCE_ERROR = "Invalid sequence. Correct sequence is "
          + "operand, operator, operand";
  /**
   * Error message if the input provided is not a valid character for calculator.
   */
  public static final String INVALID_INPUT_CHARACTER = "Not a valid input character. It should be"
          + " either between 0-9, +, -, *, C or =";
}
