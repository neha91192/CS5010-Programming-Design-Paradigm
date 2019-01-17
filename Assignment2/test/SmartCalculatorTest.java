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
    return new SmartCalculator();
  }

  /**
   * Returns new instance of type SmartCalculator.
   *
   * @return instance of type SmartCalculator.
   */
  @Override
  protected Calculator smart() {
    return new SmartCalculator();
  }


  /**
   * Checks for multiple equals case.
   */
  @Test
  public void testMultipleEqualsAddSmart() {
    insertValues(calculator2, "4+4=");
    assertEquals("8", calculator2.getResult());
    insertValues(calculator4, "4+4==");
    assertEquals("12", calculator4.getResult());
    insertValues(calculator6, "4+4===");
    assertEquals("16", calculator6.getResult());
    insertValues(calculator2, "C");
    insertValues(calculator4, "C");
    insertValues(calculator6, "C");
    insertValues(calculator2, "4+4====");
    assertEquals("20", calculator2.getResult());
    insertValues(calculator4, "4+4=====");
    assertEquals("24", calculator4.getResult());
    insertValues(calculator6, "4+4======");
    assertEquals("28", calculator6.getResult());
  }

  /**
   * Checks for multiple equals case.
   */
  @Test
  public void testMultipleEqualsSubtract() {
    insertValues(calculator2, "100-4=");
    assertEquals("96", calculator2.getResult());
    insertValues(calculator4, "100-4==");
    assertEquals("92", calculator4.getResult());
    insertValues(calculator6, "100-4===");
    assertEquals("88", calculator6.getResult());
    calculator2.input('C');
    calculator4.input('C');
    calculator6.input('C');
    insertValues(calculator2, "100-4====");
    assertEquals("84", calculator2.getResult());
    insertValues(calculator4, "100-4=====");
    assertEquals("80", calculator4.getResult());
    insertValues(calculator6, "100-4======");
    assertEquals("76", calculator6.getResult());
  }

  /**
   * Checks for multiple equals case.
   */
  @Test
  public void testMultipleEqualsMultiply() {
    insertValues(calculator2, "4*4=");
    assertEquals("16", calculator2.getResult());
    insertValues(calculator4, "4*4==");
    assertEquals("64", calculator4.getResult());
    insertValues(calculator6, "4*4===");
    assertEquals("256", calculator6.getResult());
    calculator2.input('C');
    calculator4.input('C');
    calculator6.input('C');
    insertValues(calculator2, "4*4====");
    assertEquals("1024", calculator2.getResult());
    insertValues(calculator4, "4*4=====");
    assertEquals("4096", calculator4.getResult());
    insertValues(calculator6, "4*4======");
    assertEquals("16384", calculator6.getResult());
  }

  /**
   * Checks for multiple equals case.
   */
  @Test
  public void testMultipleEqualsSmart() {
    insertValues(calculator2, "32+24=");
    assertEquals("56", calculator2.getResult());
    insertValues(calculator4, "32+24==");
    assertEquals("80", calculator4.getResult());
    calculator2.input('C');
    insertValues(calculator6, "32+24===");
    assertEquals("104", calculator6.getResult());
    calculator2.input('C');
    insertValues(calculator2, "32+24====");
    assertEquals("128", calculator2.getResult());
  }


  /**
   * Checks for valid sequence for every stage.
   */
  @Test
  public void testValidSequencePrint() {
    insertValues(calculator2, "2+");
    assertEquals("2+", calculator2.getResult());
    insertValues(calculator4, "2+2+");
    assertEquals("4+", calculator4.getResult());
    insertValues(calculator6, "23+2=");
    assertEquals("25", calculator6.getResult());
    calculator2.input('C');
    calculator4.input('C');
    calculator6.input('C');
    insertValues(calculator2, "23+2=-5+2*");
    assertEquals("22*", calculator2.getResult());
    insertValues(calculator4, "23+2=-5+2");
    assertEquals("20+2", calculator4.getResult());
    insertValues(calculator6, "32+24-10=");
    assertEquals("46", calculator6.getResult());
    calculator2.input('C');
    insertValues(calculator2, "23+2-5*100");
    assertEquals("20*100", calculator2.getResult());

  }


  /**
   * Checks for skipping second operands.Skipping the second operand: the input 3 2 + = produces 64.
   * The input 3 2 + = = produces 96, and so on. The state at the end of each “=” is the result of
   * the computation thus far.
   */
  @Test
  public void testSkippingSecondOperand() {
    insertValues(calculator2, "32+=");
    assertEquals("64", calculator2.getResult());
    insertValues(calculator4, "32+=-=");
    assertEquals("0", calculator4.getResult());
  }


  /**
   * Checks for consecutive operators.Two consecutive operators: 3 2 + - 2 4 = ignores the first
   * operator, and produces 8 as the result.
   */
  @Test
  public void testMultipleConsecutiveOperators() {
    insertValues(calculator2, "32+-*2=");
    assertEquals("64", calculator2.getResult());

    insertValues(calculator4, "32+-42*10=");
    assertEquals("-100", calculator4.getResult());
  }

  /**
   * Begin with operator: + 3 2 - 2 4 = ignores the “+” and produces 8 as the result.
   */
  @Test
  public void testFirstPositiveIgnore() {
    insertValues(calculator2, "+32-");
    assertEquals("32-", calculator2.getResult());
    insertValues(calculator4, "+0");
    assertEquals("0", calculator4.getResult());
    insertValues(calculator6, "+5000");
    assertEquals("5000", calculator6.getResult());
    insertValues(calculator2, "C");
    insertValues(calculator2, "-32+10=");
    assertEquals("42", calculator2.getResult());
  }

  /**
   * Begin with operator: + 3 2 - 2 4 = ignores the “+” and produces 8 as the result.
   */
  @Test
  public void testFirstMultiplyIgnore() {
    insertValues(calculator2, "*32");
    assertEquals("32", calculator2.getResult());
    insertValues(calculator4, "*32+5=");
    assertEquals("37", calculator4.getResult());
    insertValues(calculator6, "*2-80=");
    assertEquals("-78", calculator6.getResult());
  }


  /**
   * Like SimpleCalculator it does not allow negative inputs although it can handle negative
   * numbers, and it uses IllegalArgumentException to report all invalid inputs and sequences.
   * However it throws a RuntimeException if a valid input causes an operand to overflow. If the
   * operand does not overflow but the result of the arithmetic does, then the result reported
   * should be 0. For example, a + b - 1 0 should result in -10 if a+b will overflow.
   */
  @Test
  public void testFirstNegativeIgnore() {
    insertValues(calculator2, "-+32");
    assertEquals("32", calculator2.getResult());
    insertValues(calculator4, "-*-32+5=");
    assertEquals("37", calculator4.getResult());
    insertValues(calculator6, "*+-280+20-10=");
    assertEquals("290", calculator6.getResult());
  }

}
