package calculator;

/**
 * This class implements {@link Calculator} interface which has two basic methods to implement.
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
 * All other classes that are supposed to implement Calculator features can extend this class.
 * Considering that all calculations are performed in the calling object, it takes care of the
 * efficient evaluation of the large scale operand values in case any advanced calculator needs to
 * implement this Abstract class. By avoiding new object creation for any operation, memory values
 * are kept intact, without creating unnecessary objects and faster calculation could be performed
 * without any memory issues.
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
   * This is basic calculator input method which performs input functionality depending upon what
   * type of input has been encountered.
   * <p>They are:</p>
   * <ul>
   * <li>
   * <strong>handleDigit</strong> : Common implementation for all the calculators has been written
   * in this abstract class. All the inheriting classes can override this feature or use the default
   * implementation in their concrete implementation class.
   * </li>
   * <li>
   * <strong>handleOperator</strong>: This method has been abstracted as handling operators could
   * widely differ from one type of calculator to another.
   * </li>
   * <li>
   * <strong>handleEquals</strong>: This is method could be differently implemented and so has been
   * abstracted.
   * </li>
   * <li>
   * <strong>handleClear</strong>: This follows common implementation for all the calculators as
   * basic calculator memory values - operand1, operator, operand2 needs to be cleared in all types
   * of calculator. Inheriting classes can override this feature for clearing specific values in
   * their memory.
   * </li>
   * </ul>
   *
   * @param input of type character.
   * @return calculator object of type {@link Calculator}
   */
  @Override
  public Calculator input(char input) {
    checkValidInputCharacter(input);
    if (Character.isDigit(input)) {
      handleDigit(input);
    }
    if (isOperator(input)) {
      handleOperator(input);
    }
    if (input == EQUALS) {
      handleEquals(input);
    }
    if (input == CLEAR) {
      handleClear();
    }
    return this;
  }

  /**
   * Returns current result of the smart calculator. At any moment when it is called, it displays
   * the value in String which needs to be displayed on the screen of calculator.
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
   * @return boolean indicating if the given character is operator or not.
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
   * Performs common calculation required for all types of calculator. It supports add, subtract and
   * multiplication operations. The operators are maintained in {@link Operators} enumeration.
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
   * Handle digit method defines a procedure to evaluate whether an incoming input digit character
   * needs to be appended to the operator1 or operator2.
   * <p>It follows the basic principle of concatenating an Integer value: </p>
   * <p>ab = a*10+b</p>
   * <p>So anytime an input digit is encountered, it
   * decides whether the operand1 needs to be modified, and in that case appends input at the end of
   * operand1 otherwise operand2</p>
   * <p>Before appending any digit, it checks whether the operand could be in overflowing
   * condition. Only if the future operand value (check operand variable) is within the range of
   * Integer, it allows to append otherwise throws RuntimeException.</p>
   * <p>Another crucial functionality that this method handles is not allowing to any operand to
   * append if any valid operator is not encountered before. Also, if the operand values are already
   * evaluated, it doesn't let another operand to get appended unless it encounters a new operator
   * again.</p>
   *
   * @param input character of type digit.
   * @throws RuntimeException in case there is an operand overflow condition. Also, IllegalException
   *                          in case Operand is encountered after an expression is evaluated.
   */
  protected void handleDigit(char input) throws RuntimeException {
    if (operator != null && operator == EQUALS) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_SEQUENCE_ERROR);
    }
    Long operand;
    //if operand1 needs to be modified
    if (operator == null) {
      if (operand1 == null) {
        operand1 = 0;
      }
      operand = operand1 * 10L + (input - '0');
      if (isNumberInRange(operand)) {
        operand1 = operand1 * 10 + (input - '0');
        result = operand1 + "";
      } else {
        throw new RuntimeException(ExceptionMessageConstants.INPUT_SIZE_ERROR);
      }
      //if operand2 needs to be modified
    } else {
      if (operand2 == null) {
        operand2 = 0;
      }
      operand = operand2 * 10L + (input - '0');
      if (isNumberInRange(operand)) {
        operand2 = operand2 * 10 + (input - '0');
        result = operand1 + String.valueOf(operator) + operand2 + "";
      } else {
        throw new RuntimeException(ExceptionMessageConstants.INPUT_SIZE_ERROR);
      }
    }
  }

  /**
   * Abstract method which handles the case when operator is given in the input. Every method
   * inheriting this class should either implement this method otherwise make it abstract.
   *
   * @param input of type character
   */
  protected abstract void handleOperator(char input);

  /**
   * Abstract method which handles the case when operator is given in the equals. Every method
   * inheriting this class should either implement this method otherwise make it abstract.
   *
   * @param input of type character
   */
  protected abstract void handleEquals(char input);

  /**
   * Clears all the basic calculator input values -  operand1, operand2, operator, and result to
   * display.
   */
  protected void handleClear() {
    operand1 = null;
    operand2 = null;
    operator = null;
    result = "";
  }

}
