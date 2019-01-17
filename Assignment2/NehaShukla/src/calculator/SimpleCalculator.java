package calculator;

/**
 * This class extends AbstractCalculator to perform its own set of related input operations.
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
   * Performs different input related actions as supposed to be supported by Simple Calculator.
   *
   * @param input user input of type char.
   */
  @Override
  public Calculator input(char input) throws IllegalArgumentException {
    checkValidInputCharacter(input);
    //if input character is number
    if (Character.isDigit(input)) {
      //if operand1 needs to be modified
      if (operator!=null && operator==EQUALS) {
        throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_SEQUENCE_ERROR);
      }
      Long operand;
      if (operator == null) {
        if (operand1 == null) {
          operand1 = 0;
        }
        operand = Long.valueOf(operand1 * 10L + (input - '0'));
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
        operand = Long.valueOf(operand2 * 10L + (input - '0'));
        if (isNumberInRange(operand)) {
          operand2 = operand2 * 10 + (input - '0');
          result = operand1 + String.valueOf(operator)+operand2+"";
        } else {
          throw new RuntimeException(ExceptionMessageConstants.INPUT_SIZE_ERROR);
        }
      }
      //if operator needs to be modified
    } else if (isOperator(input)) {
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

    } else if (input == EQUALS) {
      if (operand1 != null && operator != null) {
        if (operand2 == null && isOperator(operator)) {
          throw new IllegalArgumentException(ExceptionMessageConstants.MISSING_INPUT_ERROR);
        } else {
          calculate();
          operator = EQUALS;
        }
      }
    } else if (input == CLEAR) {
      clear();
    }
    return this;
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
