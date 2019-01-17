import org.junit.Test;

import calculator.Calculator;

import static org.junit.Assert.assertEquals;

/**
 * Common Test class to check {@link Calculator} Interface.
 */
public abstract class AbstractCalculatorTest {
  /**
   * Abstract method which returns new instance of SimpleCalculator object when called. This method
   * has to be implemented by SimpleCalculatorTest so that abstraction can be achieved while testing
   * on the duration object for all the cases under simple calculator.
   */
  protected abstract Calculator simple();

  /**
   * Abstract method which returns new instance of SmartCalculator object when called. This method
   * has to be implemented by SmartCalculatorTest so that abstraction can be achieved while testing
   * on the duration object for all the cases under Smart calculator.
   */
  protected abstract Calculator smart();


  Calculator calculator1 = simple();
  Calculator calculator2 = smart();


  private void insertValues(Calculator calc, String values) throws RuntimeException {
    for (char c : values.toCharArray()) {
      calc.input(c);
    }
  }


  /**
   * Both the calculator cannot work with the whole number longer than 32 bits. This check for the
   * operand greater than Integer.MAX_VALUE. It expects Runtime exception when at any point, the
   * value range is not between 2,147,483,647 to 2,147,483,648.
   */
  @Test(expected = RuntimeException.class)
  public void testNumberLongerThan32() {
    String simpleOperand = "21474836492";
    insertValues(calculator1, simpleOperand);
    String smartOperand = "11111111111";
    insertValues(calculator2, smartOperand);
  }

  /**
   * Both the calculator cannot work with the whole number longer than 32 bits. This check for the
   * operand greater than Integer.MAX_VALUE. It expects Runtime exception when at any point, the
   * value range is not between -2,147,483,647 to 2,147,483,648.
   */
  @Test
  public void testNumberNotLongerThan32() {
    String simpleOperand = "2147483647";
    insertValues(calculator1, simpleOperand);
    String smartOperand1 = "11111111";
    insertValues(calculator2, smartOperand1);
  }

  /**
   * Both the calculator displays empty values if the input is blank.
   */
  @Test
  public void testEmptyInput() {
    assertEquals("", calculator1.getResult());
    assertEquals("", calculator2.getResult());
  }

  /**
   * Calculate basic sequence: operand + operator = result for both the calculators. Operand 1 is
   * multiple, operand 2 is single.
   */
  @Test
  public void testBasicSequenceAdd() {
    insertValues(calculator1, "23+5=");
    assertEquals("28", calculator1.getResult());

    insertValues(calculator2, "5+23=");
    assertEquals("28", calculator2.getResult());

    insertValues(calculator1, "C");
    insertValues(calculator1, "20+10=");
    assertEquals("30", calculator1.getResult());
  }

  /**
   * Calculate basic sequence: operand - operator = result for both the calculators. Operand 1 is
   * multiple, operand 2 is single.
   */
  @Test
  public void testBasicSequenceSubtract() {
    insertValues(calculator1, "20-1=");
    assertEquals("19", calculator1.getResult());

    insertValues(calculator2, "1-20=");
    assertEquals("-19", calculator2.getResult());

    insertValues(calculator1, "C");
    insertValues(calculator1, "20-10=");
    assertEquals("10", calculator1.getResult());

  }


  /**
   * Calculate basic sequence: operand + operator = result. Operand 1 is multiple, operand 2 is
   * single. 20*5=100 5*20=100 10*12=120.
   */
  @Test
  public void testBasicSequenceMultiply() {
    insertValues(calculator1, "20*5=");
    assertEquals("100", calculator1.getResult());

    insertValues(calculator2, "5*20=");
    assertEquals("100", calculator2.getResult());

    insertValues(calculator1, "C");
    insertValues(calculator1, "10*12=");
    assertEquals("120", calculator1.getResult());

  }

  /**
   * Calculate basic sequence: operand  operator operand = result for both the calculator.
   */
  @Test
  public void testBasicSequenceSingle() {
    insertValues(calculator1, "2+2=");
    assertEquals("4", calculator1.getResult());

    insertValues(calculator2, "2-2=");
    assertEquals("0", calculator2.getResult());

    insertValues(calculator1, "C");
    insertValues(calculator1, "2*5=");
    assertEquals("10", calculator1.getResult());

  }

  /**
   * A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = , 3 2
   * + 2 4 = - 10 =, etc.) 23+2=25-5=20*2=40 23+2=25*5=100*2=200 23-2=21+5=26*10=260
   * 23-2=21*5=105+10=115 23*2=56-5=51+10=61 23*2=56+4=60-10=50.
   */
  @Test
  public void testValidSequenceMultipleValues() {
    insertValues(calculator1, "23+2=-5+2*2");
    assertEquals("22*2", calculator1.getResult());


//    assertEquals("22*2", simpleCalculator.input('2').input('3').input('+').input('2')
//            .input('=').input('-').input('5').input('+').input('2').input('*').input('2')
//            .getResult());
//    Calculator calculator1 = new SimpleCalculator();
//    assertEquals("22*", calculator1.input('2').input('3').input('+').input('2')
//            .input('=').input('-').input('5').input('+').input('2').input('*')
//            .getResult());
//    Calculator calculator2 = new SimpleCalculator();
//    assertEquals("20+2", calculator2.input('2').input('3').input('+').input('2')
//            .input('=').input('-').input('5').input('+').input('2')
//            .getResult());
//    Calculator calculator3 = new SimpleCalculator();
//    assertEquals("46", calculator3.input('3').input('2').input('+').input('2').input('4')
//            .input('-').input('1').input('0').input('=').getResult());
//    simpleCalculator.input('C');
//    assertEquals("20*100", simpleCalculator.input('2').input('3').input('+').input('2')
//            .input('-').input('5').input('*').input('1').input('0').input('0')
//            .getResult());

  }


}
