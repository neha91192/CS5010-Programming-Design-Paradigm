package calculator;

/**
 * This class extends AbstractCalculator to perform its own set of related input operations.
 */
public class SmartCalculator extends AbstractCalculator {
  /**
   * Last operand that was encountered and could be used for evaluating the result in case operand2
   * is not given in the input.
   */
  private Integer lastOperand;

  /**
   * Default constructor which sets result string by calling parent class {@link
   * AbstractCalculator}.
   */
  public SmartCalculator() {
    super("");
  }


  /**
   * <p>
   * This method follows the abstract calculator implementation for handling digits except for the
   * fact that it is designated to support "smart" inputs. In that sense, it means, whenever
   * operand2 is missing, it should still evaluate if '=' is pressed.
   * </p>
   * The smart implementation in that case remembers last operand that was encountered and can be
   * considered in case operand2 is missing. See {@link AbstractCalculator#handleDigit(char)} for
   * more details.
   *
   * @param input of type character which is digit.
   */
  @Override
  protected void handleDigit(char input) {
    super.handleDigit(input);
    if (operand2 != null) {
      lastOperand = operand2;
    }
  }

  /**
   * This method defines how uniquely operator input is handled in the Smart calculator. In
   * evaluating any value, everything boils down to whether a basic sequence has been completed or
   * not. It follows the same strategy as done by {@link SmartCalculator#handleOperator(char)}
   * except by extending the fact that any number of operator can be allowed in the basic sequence
   * of the evaluation and the last one needs to be considered at the end.
   * <p>
   * This is how it does:
   * <ul>
   * <li>
   * If the operand1 has been encountered and operand2 still needs to be filled, it updates the
   * operator value in every iteration. Also, updating the lastOperand value helps in identifying
   * the operand2 needs to be considered in case equals is encountered before operand2 input value
   * is given by the user.
   * </li>
   * <li>
   * If the basic sequence is completed, it evaluates the value, see {@link
   * AbstractCalculator#calculate()} for more details, and updates result object.
   * </li>
   * </ul>
   * </p>
   *
   * @param input of type character operator.
   */
  @Override
  protected void handleOperator(char input) {
    if (operand1 != null) {
      if (operand2 == null) {
        operator = input;
        lastOperand = operand1;
      } else {
        calculate();
        operator = input;
      }
      result = operand1 + "" + input;
    }
  }

  /**
   * This handling is similar to that of abstract calculator. It entails the following details:
   * <p>Only when equals is encountered and operand2 is not yet given in the input, it takes the
   * value of lastOperand. </p>
   * <p>In this implementation, it sets the value of the lastOperand to the operand2 and then
   * evaluates further as it was done in Simple Calculator. If the operand1 is also not given and
   * '=' is given in the input, it will throw IllegalArgumentException.
   * </p> See {@link AbstractCalculator#calculate()} for more details about evaluating any output.
   */
  @Override
  protected void handleEquals(char input) {
    if (operand1 != null && operator != null && isOperator(operator)) {
      if (operand2 == null) {
        operand2 = lastOperand;
      }
      calculate();
    } else {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_SEQUENCE_ERROR);
    }
  }

  /**
   * Clears all the input variables. Additionally clears lastOperand value. See {@link
   * AbstractCalculator#handleClear()} for more details.
   */
  @Override
  protected void handleClear() {
    super.handleClear();
    lastOperand = null;
  }


  /**
   * Calls parent calculate() method to perform basic operation and resets values for the next
   * iteration. See {@link AbstractCalculator#calculate()} for more details.
   */
  @Override
  protected void calculate() {
    super.calculate();
    operand2 = null;
    result = operand1 + "";
  }

}
