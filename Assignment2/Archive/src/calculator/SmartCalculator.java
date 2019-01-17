package calculator;

/**
 * This class extends AbstractCalculator to perform its own set of related input operations.
 */
public class SmartCalculator extends SimpleCalculator {
  /**
   * Last operand.
   */
  private Integer lastOperand;

  /**
   * Constructor for this abstract class which initialises result string.
   *
   * @param result of type empty string.
   */
//  public SmartCalculator(String result) {
//    super("");
//  }
  /**
   * Default constructor which sets result string by calling parent class {@link
   * AbstractCalculator}.
   */

  /**
   * Performs different input related actions as supposed to be supported by Simple Calculator.
   *
   * @param input user input of type char.
   */
  @Override
  public Calculator input(char input) {
    checkValidInputCharacter(input);
    if (Character.isDigit(input)) {
      super.input(input);
      if (operand2 != null) {
        lastOperand = operand2;
      }
    } else if (isOperator(input)) {
      if(operand1!=null) {
        operatorFound = false;
        super.input(input);
        if (operatorFound) {
          lastOperand = operand1;
        }
      }
    } else if (input == EQUALS) {
      if (operand2 == null) {
        operand2 = lastOperand;
        operatorFound = true;
      }
      super.input(input);
    } else if (input == CLEAR) {
      super.clear();
      lastOperand = null;
    }
    return this;
  }
}