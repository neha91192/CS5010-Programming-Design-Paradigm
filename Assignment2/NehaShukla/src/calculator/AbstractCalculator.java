package calculator;

/**
 * This class implements {@link Calculator} interface which has two basic methods to implement:
 *
 * <ul>
 * <li>
 * <strong>input:</strong> It takes one input character at a time and always returns current
 * calculator object as an output.
 * </li>
 * <li>
 * <strong>getResult:</strong> It returns current output of calculator that should be shown on the
 * screen.
 * </li>
 * </ul>
 */
public abstract class AbstractCalculator implements Calculator {

  /**
   * Operand 1 of the calculator.
   */
  protected Integer operand1;
  /**
   * Operand 2 of the calculator.
   */
  protected Integer operand2;
  /**
   * Operator of the calculator.
   */
  protected Character operator;
  /**
   * Result of operation.
   */
  protected String result;
  /**
   * Clear input of the calculator.
   */
  protected static final char CLEAR = 'C';
  /**
   * Equals input of the calculator.
   */
  protected static final char EQUALS = '=';

  /**
   * Constructor for this abstract class which initialises result string.
   *
   * @param result of type empty string.
   */
  public AbstractCalculator(String result) {
    this.result = "";
  }

  /**
   * This abstract method originates the way different calculators could take inputs. So any type of
   * calculator which extends this class has to implement this method in its own way.
   */
  @Override
  public abstract Calculator input(char input);

  /**
   * Returns current result of the smart calculator.
   *
   * @return result of type String containing current result of the calculator.
   */
  @Override
  public String getResult() {
    return result;
  }

  /**
   * Checks if a given number is in the integer range. It could be positive max or negative min.
   *
   * @param number of type long which supports range and makes it possible to compare the overflow
   *               scenarios.
   * @return boolean indicating if the number is in the range or not.
   */
  protected boolean isNumberInRange(Long number) {
    return (number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE);
  }

  /**
   * Checks if the input character is an operator. It checks its presence from the {@link Operators}
   * enums which contains all the possible operators values.
   *
   * @param c of type character representing the input value of the operator.
   */
  protected boolean isOperator(char c) {
    return (c == Operators.ADD.getSign() || c == Operators.SUBTRACT.getSign()
            || c == Operators.MULTIPLY.getSign());
  }


  /**
   * Checks if the input character is supported by the calculator. All the valid input characters
   * are operators defined in {@link Operators} enum, digits (0-9) inclusive, equals sign and 'C'
   * clear character.
   */
  protected void checkValidInputCharacter(char c) throws IllegalArgumentException {
    if (!(isOperator(c) || Character.isDigit(c) || c == EQUALS || c == CLEAR)) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_INPUT_CHARACTER);
    }
  }

  /**
   * Performs common calculation required for all types of calculator.
   */
  protected void calculate() {
    if (operator == Operators.ADD.getSign()) {
      long sum = (long) operand1 + operand2;
      if (isNumberInRange(sum)) {
        operand1 = operand1 + operand2;
      } else {
        operand1 = 0;
      }
    }
    if (operator == Operators.SUBTRACT.getSign()) {
      long difference = (long) operand1 - operand2;
      if (isNumberInRange(difference)) {
        operand1 = operand1 - operand2;
      } else {
        operand1 = 0;
      }
    }
    if (operator == Operators.MULTIPLY.getSign()) {
      long product = (long) operand1 * operand2;
      if (isNumberInRange(product)) {
        operand1 = operand1 * operand2;
      } else {
        operand1 = 0;
      }
    }
  }

  /**
   * Clears all the character operand and operator values.
   */
  protected void clear() {
    operand1 = null;
    operand2 = null;
    operator = null;
    result = "";
  }


}
