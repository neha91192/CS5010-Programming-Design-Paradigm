package calculator;

/**
 * This class extends AbstractCalculator to perform its own set of related input operations.
 */
public class SmartCalculator extends AbstractCalculator {
  /**
   * Last operand.
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
   * Performs different input related actions as supposed to be supported by Simple Calculator.
   *
   * @param input user input of type char.
   */
  @Override
  public Calculator input(char input) {
    checkValidInputCharacter(input);
    //if input character is number
    if (Character.isDigit(input)) {
      //if operand1 needs to be modified
      Long operand;
      if (operator == null) {
        if (operand1 == null) {
          operand1 = 0;
        }
        operand =  Long.valueOf(operand1 * 10L + (input - '0'));
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
        operand =  Long.valueOf(operand2 * 10L + (input - '0'));
        if (isNumberInRange(operand)) {
          operand2 = operand2 * 10 + (input - '0');
          result = operand1 + String.valueOf(operator)+operand2+"";
        } else {
          throw new RuntimeException(ExceptionMessageConstants.INPUT_SIZE_ERROR);
        }
        lastOperand = operand2;
      }
      //if operator needs to be modified
    } else if (isOperator(input)) {
      //else let + gets added in the start for smart calculator
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
      //if = is encountered
    } else if (input == EQUALS) {
      if(operand1!=null && operator!=null && isOperator(operator)) {
        if(operand2==null) {
          operand2 = lastOperand;
        }
        calculate();
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
  @Override
  protected void calculate() {
    super.calculate();
    operand2 = null;
    result = operand1 + "";
    System.out.println(result);
  }
}
