import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;
import calculator.SmartCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link SimpleCalculator}. Checks for specific cases that belongs to
 * SimpleCalculator.
 */
public class SimpleCalculatorTest extends AbstractCalculatorTest {

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
   * Returns new instance of type SmartCalculator.
   *
   * @return instance of type SmartCalculator.
   */
  @Override
  protected Calculator smart() {
    return new SimpleCalculator();
  }

  /**
   * A valid sequence can contain a sequence of operands and operators. Checks for Simple Calculator
   * Invalid cases.
   */
  @Test
  public void testInvalidSequenceAddFirst() {
    try {
      insertValues(calculator1, "+");
      calculator1.input('+').input('3').input('+').input('2')
              .input('=').input('-').input('5').input('+').input('2').input('*').input('2');
    } catch (IllegalArgumentException e) {
      assertEquals("", calculator1.getResult());
    }

  }

  /**
   * A valid sequence can contain a sequence of operands and operators. Checks for Simple Calculator
   * Invalid cases.
   */
  @Test
  public void testInvalidSequenceSubtractFirst() {
    try {
      insertValues(calculator1, "-");
    } catch (IllegalArgumentException e) {
      assertEquals("", calculator1.getResult());
    }

  }

  /**
   * A valid sequence can contain a sequence of operands and operators. Checks for Simple Calculator
   * Invalid cases.
   */
  @Test
  public void testInvalidSequenceMultiplyFirst() {
    try {
      insertValues(calculator1, "*");
    } catch (IllegalArgumentException e) {
      assertEquals("", calculator1.getResult());
    }
  }

  /**
   * A valid sequence can contain a sequence of operands and operators. Checks for Simple Calculator
   * Invalid cases.
   */
  @Test
  public void testInvalidSequenceOperatorFirst() {
    try {
      insertValues(calculator1, "+*=+*");
    } catch (IllegalArgumentException e) {
      assertEquals("", calculator1.getResult());
    }

  }

  /**
   * A valid sequence can contain a sequence of operands and operators. It catches
   * IllegalArgumentException in case equals is encountered after operand.
   */
  @Test
  public void testInvalidSequenceOperandAfterEquals() {
    try {
      insertValues(calculator1, "3+2=5");
    } catch (IllegalArgumentException e) {
      assertEquals("5", calculator1.getResult());
    }
  }

  /**
   * A valid sequence can contain a sequence of operands and operators. It catches
   * IllegalArgumentException in case equals is encountered after operand.
   */
  @Test
  public void testValidSequenceOperatorAfterEquals() {
    insertValues(calculator1, "3+2=+5");
    assertEquals("5+5", calculator1.getResult());
    insertValues(calculator3, "3+2=+5=");
    assertEquals("10", calculator3.getResult());
  }


  /**
   * A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = , 3 2
   * + 2 4 = - 10 =, etc.) 23+* 23+- 2-+ 3-* 79*+ 89+* 8+21=29*- 8-20=-12*- 98+2+* 3*2=34
   * (invalid).Checks for invalid sequences.
   */
  @Test
  public void testInvalidSequenceMultipleConsecutiveOperators1() {
    try {
      insertValues(calculator1, "3+-4");
    } catch (IllegalArgumentException e) {
      assertEquals("3+", calculator1.getResult());
    }
  }

  /**
   * A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = , 3 2
   * + 2 4 = - 10 =, etc.) 23+* 23+- 2-+ 3-* 79*+ 89+* 8+21=29*- 8-20=-12*- 98+2+* 3*2=34 (invalid).
   * Checks for invalid sequences.
   */
  @Test
  public void testInvalidSequenceMultipleConsecutiveOperators2() {
    try {
      insertValues(calculator1, "3+2+-");
    } catch (IllegalArgumentException e) {
      assertEquals("5+", calculator1.getResult());
    }
  }

  /**
   * A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = , 3 2
   * + 2 4 = - 10 =, etc.) 23+* 23+- 2-+ 3-* 79*+ 89+* 8+21=29*- 8-20=-12*- 98+2+* 3*2=34 (invalid).
   * Checks for invalid sequences.
   */
  @Test
  public void testInvalidSequenceMultipleConsecutiveOperators3() {
    try {
      insertValues(calculator1, "782--*2*2");
    } catch (IllegalArgumentException e) {
      assertEquals("782-", calculator1.getResult());
    }
  }


  /**
   * Checks for missing input error The calculator does not “infer” any missing inputs. For example,
   * although 32+=, +12+3, etc. is valid input on a normal calculator, this calculator will report
   * that as an error. +12-3 = error 12+= error.
   */
  @Test
  public void testMissingInputs1() {
    try {
      insertValues(calculator1, "3+=");
    } catch (IllegalArgumentException e) {
      assertEquals("3+", calculator1.getResult());
    }
  }

  /**
   * Checks for missing input error The calculator does not “infer” any missing inputs. For example,
   * although 32+=, +12+3, etc. is valid input on a normal calculator, this calculator will report
   * that as an error. +12-3 = error 12+= error.
   */
  @Test
  public void testMissingInputs2() {
    try {
      insertValues(calculator1, "3-=");
    } catch (IllegalArgumentException e) {
      assertEquals("3-", calculator1.getResult());
    }
  }

  /**
   * Checks for missing input error The calculator does not “infer” any missing inputs. For example,
   * although 32+=, +12+3, etc. is valid input on a normal calculator, this calculator will report
   * that as an error. +12-3 = error 12+= error.
   */
  @Test
  public void testMissingInputs3() {
    try {
      insertValues(calculator1, "3*=");
    } catch (IllegalArgumentException e) {
      assertEquals("3*", calculator1.getResult());
    }
  }

  /**
   * Checks for missing input error The calculator does not “infer” any missing inputs. For example,
   * although 32+=, +12+3, etc. is valid input on a normal calculator, this calculator will report
   * that as an error. +12-3 = error 12+= error.
   */
  @Test
  public void testMissingInputs4() {
    try {
      insertValues(calculator1, "3*7=-1+5-=");
    } catch (IllegalArgumentException e) {
      assertEquals("25-", calculator1.getResult());
    }
  }

  /**
   * Checks for multiple equals case.
   */
  @Test
  public void testMultipleEqualsAdd() {
    insertValues(calculator1, "4+4=");
    assertEquals("8", calculator1.getResult());
    insertValues(calculator2, "4+4==");
    assertEquals("8", calculator2.getResult());
    insertValues(calculator3, "4+4===");
    assertEquals("8", calculator3.getResult());
    insertValues(calculator4, "4+4====");
    assertEquals("8", calculator4.getResult());
    insertValues(calculator5, "4+4=====");
    assertEquals("8", calculator5.getResult());
    insertValues(calculator6, "4+4=====");
    assertEquals("8", calculator6.getResult());
  }

  /**
   * Checks for multiple equals case.
   */
  @Test
  public void testMultipleEqualsSubtract() {
    insertValues(calculator1, "9-4=");
    assertEquals("5", calculator1.getResult());
    insertValues(calculator2, "9-4==");
    assertEquals("5", calculator2.getResult());
    insertValues(calculator3, "9-4===");
    assertEquals("5", calculator3.getResult());
    insertValues(calculator4, "9-4====");
    assertEquals("5", calculator4.getResult());
    insertValues(calculator5, "9-4=====");
    assertEquals("5", calculator5.getResult());
    insertValues(calculator6, "9-4=====");
    assertEquals("5", calculator6.getResult());
  }

  /**
   * Checks for multiple equals case.
   */
  @Test
  public void testMultipleEqualsMultiply() {
    insertValues(calculator1, "4*4=");
    assertEquals("16", calculator1.getResult());
    insertValues(calculator2, "4*4==");
    assertEquals("16", calculator2.getResult());
    insertValues(calculator3, "4*4===");
    assertEquals("16", calculator3.getResult());
    insertValues(calculator4, "4*4====");
    assertEquals("16", calculator4.getResult());
    insertValues(calculator5, "4*4=====");
    assertEquals("16", calculator5.getResult());
    insertValues(calculator6, "4*4=====");
    assertEquals("16", calculator6.getResult());
  }

  /**
   * The calculator does not allow inputting negative numbers, although it can handle negative
   * results.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeNumber() {
    calculator1.input('-').input('3');
  }


}
