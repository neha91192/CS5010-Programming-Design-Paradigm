import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;
import calculator.SmartCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Covers test cases specific to smart calculator.
 */
public class SmartCalculatorTest extends AbstractCalculatorTest {
  /**
   * Returns SimpleCalculator Instance.
   *
   * @return new instance of type SimpleCalculator.
   */
  @Override
  protected Calculator simple() {
    return new SimpleCalculator();
  }

  /**
   * Returns new instance of type SimpleCalculator.
   *
   * @return instance of type SimpleCalculator.
   */
  @Override
  protected Calculator smart() {
    return new SimpleCalculator();
  }

  /**
   *
   */
  private Calculator smartcalculator;

  /**
   *
   */
  @Before
  public void setUp() {
    smartcalculator = new SmartCalculator();

  }

  /**
   * Calculate basic sequence: operand  operator operand = result. 2+2=4 3-1=2 2*2=4 Input “=”
   * multiple times: 3 2 + 2 4 = produces 56 as before. However 3 2 + 2 4 = = and 3 2 + 2 4 = = =
   * are also valid input sequences, and produce 80 and 104 respectively.
   */
  @Test
  public void testMultipleEqualsSmart() {
    assertEquals("10", smartcalculator.input('2').input('+').input('2')
            .input('=').input('=').input('=').input('=').getResult());
    smartcalculator.input('C');
    assertEquals("56", smartcalculator.input('3').input('2').input('+')
            .input('2').input('4').input('=').getResult());
    smartcalculator.input('C');
    assertEquals("56", smartcalculator.input('3').input('2').input('+')
            .input('2').input('4').input('=').getResult());
    smartcalculator.input('C');
    assertEquals("80", smartcalculator.input('3').input('2').input('+')
            .input('2').input('4').input('=').input('=').getResult());
    smartcalculator.input('C');
    assertEquals("104", smartcalculator.input('3').input('2').input('+')
            .input('2').input('4').input('=').input('=').input('=').getResult());
  }

  /**
   *
   */
  @Test
  public void testValidSequencePrint() {
    smartcalculator.input('C');
    assertEquals("2+", smartcalculator.input('2').input('+').getResult());
    smartcalculator.input('C');
    assertEquals("4+", smartcalculator.input('2').input('+').input('2').input('+').getResult());
    smartcalculator.input('C');
    assertEquals("22*2", smartcalculator.input('2').input('3').input('+').input('2')
            .input('=').input('-').input('5').input('+').input('2').input('*').input('2')
            .getResult());
    Calculator calculator1 = new SimpleCalculator();
    assertEquals("22*", calculator1.input('2').input('3').input('+').input('2')
            .input('=').input('-').input('5').input('+').input('2').input('*')
            .getResult());
    Calculator calculator2 = new SimpleCalculator();
    assertEquals("20+2", calculator2.input('2').input('3').input('+').input('2')
            .input('=').input('-').input('5').input('+').input('2')
            .getResult());
    Calculator calculator3 = new SimpleCalculator();
    assertEquals("46", calculator3.input('3').input('2').input('+').input('2').input('4')
            .input('-').input('1').input('0').input('=').getResult());
    smartcalculator.input('C');
    assertEquals("4+1", smartcalculator.input('4').input('+').input('1').getResult());
    smartcalculator.input('C');
    assertEquals("20*100", smartcalculator.input('2').input('3').input('+').input('2')
            .input('-').input('5').input('*').input('1').input('0').input('0')
            .getResult());

  }

  /**
   * 11 2147483647
   */
  @Test
  public void testOverflow() {

  }

  /**
   * Operand overflow -2,147,483,648 to 2,147,483,647
   *
   * 11111111111
   *
   * 2,147,483,648(Runtime error).
   */
  @Test(expected = RuntimeException.class)
  public void testOperandOverflowPositive() {
//    calculator.input('1').input('1').input('1').input('1').input('1')
//            .input('1').input('1').input('1').input('1').input('1').input('1');
//    calculator.input('C');
//   calculator.input('2').input('1').input('4').input('7').input('4')
//            .input('8').input('3').input('6').input('4').input('8').input('2').input('9');
    smartcalculator.input('1').input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1').input('1').input('1').input('1');
//    calculator.input('1').input('1').input('1').input('1').input('1')
//            .input('1').input('1').input('1').input('1').input('1').input('1');
  }


  /**
   * Skipping the second operand: the input 3 2 + = produces 64. The input 3 2 + = = produces 96,
   * and so on. The state at the end of each “=” is the result of the computation thus far.
   */
  @Test
  public void testSkippingSecondOperand() {
    assertEquals("64", smartcalculator.input('3').input('2').input('+')
            .input('=').getResult());
    smartcalculator.input('C');
    assertEquals("0", smartcalculator.input('3').input('2').input('+')
            .input('=').input('-').input('=').getResult());
  }


  /**
   * Two consecutive operators: 3 2 + - 2 4 = ignores the first operator, and produces 8 as the
   * result.
   */
  @Test
  public void testConsecutiveOperators() {
//    assertEquals("64", calculator.input('3').input('2').input('+').input('-').input('*')
//            .input('2').input('=').getResult());
//    calculator.input('C');
    smartcalculator.input('C');
    assertEquals("-100", smartcalculator.input('3').input('2').input('+').input('-').input('4')
            .input('2').input('*').input('1').input('0').input('=').getResult());
  }

  /**
   * Begin with operator: + 3 2 - 2 4 = ignores the “+” and produces 8 as the result.
   */
  @Test
  public void testFirstPositiveIgnore() {
    assertEquals("32", smartcalculator.input('+').input('3').input('2').getResult());
  }

  /**
   * Begin with operator: + 3 2 - 2 4 = ignores the “+” and produces 8 as the result.
   */
  @Test
  public void testFirstMultiplyIgnore() {
    assertEquals("32", smartcalculator.input('*').input('3').input('2').getResult());
  }


  /**
   * Like SimpleCalculator it does not allow negative inputs although it can handle negative
   * numbers, and it uses IllegalArgumentException to report all invalid inputs and sequences.
   * However it throws a RuntimeException if a valid input causes an operand to overflow. If the
   * operand does not overflow but the result of the arithmetic does, then the result reported
   * should be 0. For example, a + b - 1 0 should result in -10 if a+b will overflow.
   */
  @Test
  public void testFirstNegativeError() {
    assertEquals("32", smartcalculator.input('-').input('+').input('3').input('2').getResult());
  }

}
