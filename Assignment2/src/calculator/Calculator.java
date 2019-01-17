package calculator;

/**
 * This is a calculator interface which performs addition, subtraction, multiplication operations
 * only on whole numbers. It takes one character input at a time and displays the current state of
 * the calculation result.
 * It exposes the following two operations:
 * <ul>
 * <li><strong>input: </strong> takes single character as an argument and returns the current
 * state in calculator object.</li>
 * <li><strong>getResult: </strong> returns result of the calculation as a String.</li>
 * </ul>
 */
public interface Calculator {

  /**
   * Takes character input and evaluates Calculator result state.
   *
   * @param input user input of type char.
   * @return Calculator object containing current state of calculation.
   */
  Calculator input(char input);

  /**
   * Returns the result of processing the inputs as a String.
   *
   * @return String containing the result of the calculation.
   */
  String getResult();

}
