package calculator;

/**
 * This class extends AbstractCalculator to perform its own set of related input operations.
 * <p>The following are features of Simple Calculator:</p>
 * <ul>
 * <li>
 * For calculation, it supports only operand values up to 32 bits.
 * </li>
 * <li>
 * Basic sequence of any operation is operand --> operator --> operand. If it encounters equals, it
 * evaluates then and there, and the evaluated value act as the first operand for the next sequence.
 * Having said that, it doesn't allow anymore operand and therefore throws IllegalArgumentException,
 * as it violates basic operand-->operator-->operand flow for the next sequence of evaluation. What
 * it does is that, it allows next valid operator and then continues with the same basic sequence
 * flow.
 * </li>
 * <li>
 * Supports valid characters as 0-9 digits, +, -, * operators, 'C' clear button as input, '=' to
 * evaluate the values.
 * </li>
 * <li>
 * Considering that it strictly follows Basic sequence, it doesn't allow to press equals button
 * unless operand2 has been given in the input. That is, a+= is not allowed.
 * </li>
 * <li>
 * At any moment of time equals is pressed after evaluation, it returns the same result value that
 * was evaluated before. That is, 2+2=4 will be equal to 2+2==4
 * </li>
 *
 * It uses some basic memory attributes available in the {@link AbstractCalculator} to calculate
 * values. They are operand1, operand2, operator, result.
 * </ul>
 */
public class SimpleCalculator extends AbstractCalculator {

  /**
   * Default constructor which sets result string by calling parent class {@link
   * AbstractCalculator}.
   */
  public SimpleCalculator() {
    super("");
  }


  /**
   * Simple Calculator follows basic implementation of handling digits. It uses the implementation
   * present in {@link AbstractCalculator} for handling Digits.
   * <p>Following are the consideration while designing handle inputs in Simple Calculator:</p>
   * <ul>
   * <li>
   * If the input that needs to appended in the existing operand is in the range, it allows to be
   * appended depending upon the range supported by this calculator.
   * </li>
   * See {@link AbstractCalculator#handleDigit(char)} for more details.
   * </ul>
   */
  @Override
  protected void handleDigit(char input) {
    super.handleDigit(input);
  }

  /**
   * This method defines how uniquely operator input is handled in the Simple calculator. In
   * evaluating any value, everything boils down to whether a basic sequence has been completed or
   * not. Following are the steps.
   * <ul>
   * <li>It checks if the operand1 has been encountered. Since in simple calculator, values cannot
   * start from operators, it throws IllegalArgumentException in that case.</li>
   * <li>It updates the operator value only if the operand2 value has not been encountered. If
   * operator is already present and operand2 is also left to be filled, it doesn't let another
   * operator been added in the input and throws IllegalArgumentException.</li>
   * <li>If the basic sequence is completed, it evaluates the expression, updates operand1 value
   * with the output, operator value with the current input operator value, and result object for
   * display.</li>
   * </ul>
   *
   * @param input of type character operator.
   */
  protected void handleOperator(char input) {
    if (operand1 != null) {
      if (operator == null || operator == EQUALS) {
        operator = input;
      } else if (operand2 == null) {
        throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_SEQUENCE_ERROR);
      } else {
        calculate();
        operator = input;
      }
      result = operand1 + "" + input;
    } else {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_SEQUENCE_ERROR);
    }
  }

  /**
   * Simple calculator implementation keeps a track of all the values - operators and operands that
   * has been encountered and fills up basic sequence required for calculation.
   * <p>
   * At any moment of time, equals is encountered, it checks if the operand1 has been found, if this
   * is the case, it checks if the operator and the next operand is not null. If both of them are
   * null, it throws IllegalArgumentException since it is not allowed in simple calculator
   * implementation to evaluate any output unless basic sequence is not completed.
   * </p>
   * <p>
   * If the basic sequence is completed, it calls calculate method for evaluation, and updates
   * operator value as Equals. This step helps in keeping track of how the next input needs to be
   * handled. So if the next input is operand, it shows invalid or continues evaluating if next
   * input is an operator or equals.
   * </p>
   * <p>
   * It throws and exception in case none of the values are have been encountered before.
   * </p>
   *
   * @param input of type character which is an equals sign.
   */
  @Override
  protected void handleEquals(char input) {
    if (operand1 != null && operator != null) {
      if (operand2 == null && isOperator(operator)) {
        throw new IllegalArgumentException(ExceptionMessageConstants.MISSING_INPUT_ERROR);
      } else {
        calculate();
        operator = EQUALS;
      }
    } else {
      throw new IllegalArgumentException(ExceptionMessageConstants.MISSING_INPUT_ERROR);
    }
  }

  /**
   * Resets all the values stored in the memory of basic Abstract calculator. It clears out
   * operand1, operand2, operator, and result String.
   */
  @Override
  protected void handleClear() {
    super.handleClear();
  }

  /**
   * Calls parent calculate() method to perform basic operation and resets values for the next
   * iteration.
   */
  protected void calculate() {
    super.calculate();
    operand2 = null;
    operator = null;
    result = operand1 + "";
  }

}
