import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link SimpleCalculator}
 */
public class SimpleCalculatorTest extends AbstractCalculatorTest {


  @Override
  protected Calculator simple() {
    return new SimpleCalculator();
  }

  @Override
  protected Calculator smart() {
    return new SimpleCalculator();
  }

  Calculator simpleCalculator;

  /**
   * Sets up instance of simple calculator.
   */
  @Before
  public void setUp() {
    simpleCalculator = new SimpleCalculator();

  }


  /**
   * A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = , 3 2
   * + 2 4 = - 10 =, etc.) +-*23 -*+2 *+-23.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSequenceOperatorFirst() {
    simpleCalculator.input('+').input('3').input('+').input('2')
            .input('=').input('-').input('5').input('+').input('2').input('*').input('2');
    Calculator calculator2 = new SimpleCalculator();
    calculator2.input('-').input('3').input('+').input('2')
            .input('=').input('-').input('5').input('+').input('2').input('*').input('2');
    Calculator calculator3 = new SimpleCalculator();
    calculator3.input('*').input('3').input('+').input('2')
            .input('=').input('-').input('5').input('+').input('2').input('*').input('2');
  }

  /**
   * A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = , 3 2
   * + 2 4 = - 10 =, etc.) +-*23 -*+2 *+-23.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSequenceOperandAfterEquals() {
    simpleCalculator.input('3').input('+').input('2')
            .input('=').input('5');
  }

  /**
   * A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = , 3 2
   * + 2 4 = - 10 =, etc.) 23+* 23+- 2-+ 3-* 79*+ 89+* 8+21=29*- 8-20=-12*- 98+2+* 3*2=34
   * (invalid).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSequenceOperatorAfter() {
    simpleCalculator.input('3').input('+').input('-')
            .input('4');
    Calculator calculator2 = new SimpleCalculator();
    calculator2.input('3').input('+').input('2')
            .input('+').input('-');
    Calculator calculator3 = new SimpleCalculator();
    calculator3.input('7').input('8').input('2')
            .input('-').input('-').input('*').input('+').input('2').input('*').input('2');
  }

  /**
   * Check if the sequence is valid --> operand, operator, operand etc simple add: + + + simple
   * minus: - - - simple multiplication: * * * all permutation: * + - 32 + 24 = -10, multiple =
   * 90+12-11-==34-10 as invalid,  90+12-11==+34-10, 90+12-11==34-10 think more public void.
   *
   *
   * A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = , 3 2
   * + 2 4 = - 10 =, etc.) 23+= 2-= 3*=.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSequenceOperatorAfterEquals() {
    simpleCalculator.input('3').input('+').input('=');
    Calculator calculator2 = new SimpleCalculator();
    calculator2.input('3').input('2').input('2')
            .input('-').input('2').input('+').input('=');
    Calculator calculator3 = new SimpleCalculator();
    calculator3.input('0').input('+').input('9')
            .input('*').input('7').input('*').input('=');
  }

  /**
   * The only valid operand characters are 0-9. Check for all numbers 0 1 2 3 4 5 6 7 8 9.
   */
  @Test
  public void testValidOperands() {
    assertEquals("36+9", simpleCalculator.input('1').input('+').input('2').input('+').input('3')
            .input('+').input('4').input('+').input('5').input('+').input('6').input('+')
            .input('7').input('+').input('8').input('+').input('9').getResult());
  }

  /**
   * The only valid operand characters are 0-9. '', !, @, #, $, %, ^, &, *, ?.
   */
  //todo How many invalid operands should be tested?
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOperands() {
    simpleCalculator.input('i').input('x').input('&').input('p').input('l')
            .input('#').input('@').input('!').input('s').input('a').input('b').input('n')
            .input('^').input('%').input('$').input('+').input('>').getResult();
  }

  /**
   * The only valid operators are +, - and *.
   */

  public void testValidOperators() {

  }

  /**
   * The only valid operators are +, - and *.
   */
  public void testInvalidOperators() {

  }

  /**
   * check clear works with multiple combinations Erases past input memory.
   */
  @Test
  public void testClearWorks() {
    assertEquals("", simpleCalculator.input('2').input('C').getResult());
    assertEquals("", simpleCalculator.input('2').input('2').input('C').getResult());
    assertEquals("", simpleCalculator.input('2').input('6').input('+').input('C').getResult());
    assertEquals("", simpleCalculator.input('2').input('6').input('+').input('3')
            .input('-').input('5').input('C').getResult());
    assertEquals("", simpleCalculator.input('2').input('6').input('+').input('3')
            .input('=').input('C').getResult());
    assertEquals("29", simpleCalculator.input('2').input('6').input('+').input('3')
            .input('=').getResult());
    assertEquals("", simpleCalculator.input('C').getResult());
    assertEquals("", simpleCalculator.input('C').getResult());
  }

  /**
   * Missing input error The calculator does not “infer” any missing inputs. For example, although
   * 32+=, +12+3, etc. is valid input on a normal calculator, this calculator will report that as an
   * error. +12-3 = error 12+= error.
   */
  public void testMissingInputs() {

  }


  /**
   * 4, 4==, 4=, 4=== returns the same. 4+3====7.
   */
  @Test
  public void testMultipleEquals() {
    assertEquals("4", simpleCalculator.input('4').getResult());
    simpleCalculator.input('C');
    assertEquals("4", simpleCalculator.input('4').input('=').getResult());
    simpleCalculator.input('C');
    assertEquals("4", simpleCalculator.input('4').input('=').input('=').getResult());
    simpleCalculator.input('C');
    assertEquals("4", simpleCalculator.input('4').input('=').input('=').input('=').getResult());
    simpleCalculator.input('C');
    assertEquals("50", simpleCalculator.input('4').input('4').input('+')
            .input('6').input('=').input('=').getResult());
  }

  /**
   * The calculator does not allow inputting negative numbers, although it can handle negative
   * results.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeNumber() {
    simpleCalculator.input('-').input('3');
  }


  /**
   * Operand overflow -2,147,483,648 to 2,147,483,647
   *
   * 2,147,483,648(Runtime error).
   */
  @Test(expected = RuntimeException.class)
  public void testOperandOverflowPositive() {
    simpleCalculator.input('2').input('1').input('4').input('7').input('4')
            .input('8').input('3').input('6').input('4').input('8').input('2').input('9');
  }

  /**
   * Operand overflow 5-2,147,483,645-9 (Runtime error) 1-3-2,147,483,646 -2,147,483,644
   * -2,147,483,643.
   */
  @Test(expected = RuntimeException.class)
  public void testOperandOverflowNegative() {
    simpleCalculator.input('7').input('-').input('2').input('1').input('4').input('7').input('4')
            .input('8').input('3').input('6').input('4').input('8');
    simpleCalculator.input('C');
    simpleCalculator.input('1').input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1').input('1').input('1').input('1');
  }

  /**
   * Operand overflow 5+2,147,483,645 (0).
   */
  @Test
  public void testSumOverflow() {
    assertEquals("0", simpleCalculator.input('2').input('1').input('4').input('7').input('4')
            .input('8').input('3').input('6').input('4').input('5').input('+')
            .input('5').input('=').getResult());
  }

  /**
   * Operand overflow 1-7-2,147,483,645 (0).
   */
  @Test
  public void testSubtractionUnderflow() {
    assertEquals("-9", simpleCalculator.input('1').input('-').input('4').input('-').input('2')
            .input('1').input('4').input('7').input('4').input('8').input('3').input('6').input('4')
            .input('6').input('-').input('9').input('=').getResult());
  }

  /**
   * Operand overflow 5+2,147,483,645 (0).
   */
  @Test
  public void testMultiplyOverflow() {
    assertEquals("-5", simpleCalculator.input('2').input('1').input('4').input('7').input('4')
            .input('8').input('3').input('6').input('4').input('5').input('*')
            .input('2').input('=').input('-').input('5').input('=').getResult());
  }


}
