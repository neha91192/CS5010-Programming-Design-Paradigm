import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import calculator.Calculator;

import static org.junit.Assert.assertEquals;

/**
 * Common Test class to check {@link Calculator} Interface. It performs common test cases for {@link
 * SimpleCalculatorTest} and {@link SmartCalculatorTest}
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

  /**
   * Set of calculator instances for simple and smart calculators.
   */
  Calculator calculator1 = simple();
  Calculator calculator2 = smart();
  Calculator calculator3 = simple();
  Calculator calculator4 = smart();
  Calculator calculator5 = simple();
  Calculator calculator6 = smart();

  private static Set<Character> validCharacters;

  /**
   * Initializes characters allowed in calculator.
   */
  @BeforeClass
  public static void initialize() {
    validCharacters = new HashSet<>();
    validCharacters.add('0');
    validCharacters.add('1');
    validCharacters.add('2');
    validCharacters.add('3');
    validCharacters.add('4');
    validCharacters.add('5');
    validCharacters.add('6');
    validCharacters.add('7');
    validCharacters.add('8');
    validCharacters.add('9');
    validCharacters.add('+');
    validCharacters.add('-');
    validCharacters.add('*');
    validCharacters.add('=');
    validCharacters.add('C');
  }


  /**
   * Inserts one character at a time.
   *
   * @param calc   calculator instance.
   * @param values Input values in String
   * @throws RuntimeException if operand overflows
   */
  protected void insertValues(Calculator calc, String values) throws RuntimeException {
    for (char c : values.toCharArray()) {
      calc.input(c);
    }
  }


  /**
   * Both the calculator cannot work with the whole number longer than 32 bits. This check for the
   * operand greater than Integer.MAX_VALUE. It expects Runtime exception when at any point, the
   * value range is not between 2,147,483,647 to 2,147,483,648.
   */
  @Test
  public void test1NumberLongerThan32() {
    try {
      String simpleOperand = "21474836492";
      insertValues(calculator1, simpleOperand);
    } catch (RuntimeException e) {
      assertEquals("214748364", calculator1.getResult());
    }

  }

  /**
   * Both the calculator cannot work with the whole number longer than 32 bits. This check for the
   * operand greater than Integer.MAX_VALUE. It expects Runtime exception when at any point, the
   * value range is not between 2,147,483,647 to 2,147,483,648.
   */
  @Test
  public void test2NumberLongerThan32() {
    try {
      String smartOperand = "11111111111";
      insertValues(calculator2, smartOperand);
    } catch (RuntimeException e) {
      assertEquals("1111111111", calculator2.getResult());
    }

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
    assertEquals("2147483647", calculator1.getResult());
    String smartOperand1 = "11111111";
    insertValues(calculator2, smartOperand1);
    assertEquals("11111111", calculator2.getResult());
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
   * Calculate basic sequence: operand - operator = result for both the calculators. Operand 1 is
   * multiple, operand 2 is single.
   */
  @Test
  public void testBasicSequenceSubtract1() {
    insertValues(calculator1, "0-1000000=");
    assertEquals("-1000000", calculator1.getResult());

    insertValues(calculator2, "1-00000=");
    assertEquals("1", calculator2.getResult());

    insertValues(calculator3, "00000-10");
    assertEquals("0-10", calculator3.getResult());

  }


  /**
   * Calculate basic sequence: operand + operator = result. Operand 1 is multiple, operand 2 is
   * single. 20*5=100 5*20=100 10*12=120.
   */
  @Test
  public void testBasicSequenceMultiply1() {
    insertValues(calculator1, "20*5=");
    assertEquals("100", calculator1.getResult());

    insertValues(calculator2, "5*20=");
    assertEquals("100", calculator2.getResult());

    insertValues(calculator1, "C");
    insertValues(calculator1, "10*12=");
    assertEquals("120", calculator1.getResult());

  }

  /**
   * Calculate basic sequence: operand + operator = result. Operand 1 is multiple, operand 2 is
   * single. 20*5=100 5*20=100 10*12=120.
   */
  @Test
  public void testBasicSequenceMultiply2() {
    insertValues(calculator1, "20*00000=");
    assertEquals("0", calculator1.getResult());

    insertValues(calculator2, "20*00000");
    assertEquals("20*0", calculator2.getResult());

    insertValues(calculator3, "0*00000=");
    assertEquals("0", calculator1.getResult());

  }

  /**
   * Calculate basic sequence: operand --> operator --> operand = result for both the calculator.
   */
  @Test
  public void testBasicSequenceSingle() {
    insertValues(calculator1, "2+2=");
    assertEquals("4", calculator1.getResult());

    insertValues(calculator2, "2-2=");
    assertEquals("0", calculator2.getResult());

    insertValues(calculator3, "2*5=");
    assertEquals("10", calculator3.getResult());

  }

  /**
   * Tests getResult functionality at each step of the calculation.
   */
  @Test
  public void testPrintEachStep1() {

    insertValues(calculator1, "2");
    assertEquals("2", calculator1.getResult());

    insertValues(calculator2, "23");
    assertEquals("23", calculator2.getResult());

    insertValues(calculator3, "23+");
    assertEquals("23+", calculator3.getResult());

    insertValues(calculator4, "23-");
    assertEquals("23-", calculator4.getResult());

    insertValues(calculator5, "23*");
    assertEquals("23*", calculator5.getResult());

  }

  /**
   * Tests getResult functionality at each step of the calculation.
   */
  @Test
  public void testPrintEachStep2() {

    insertValues(calculator1, "23+1");
    assertEquals("23+1", calculator1.getResult());

    insertValues(calculator2, "23+11");
    assertEquals("23+11", calculator2.getResult());

    insertValues(calculator3, "23-1");
    assertEquals("23-1", calculator3.getResult());

    insertValues(calculator4, "23-11");
    assertEquals("23-11", calculator4.getResult());

    insertValues(calculator5, "23*1");
    assertEquals("23*1", calculator5.getResult());

    insertValues(calculator6, "23*11");
    assertEquals("23*11", calculator6.getResult());
  }

  /**
   * Tests getResult functionality at each step of the calculation.
   */
  @Test
  public void testPrintEachStep3() {

    insertValues(calculator1, "23+1=+");
    assertEquals("24+", calculator1.getResult());

    insertValues(calculator2, "23+11=-1");
    assertEquals("34-1", calculator2.getResult());

    insertValues(calculator3, "23*1=*");
    assertEquals("23*", calculator3.getResult());

    insertValues(calculator4, "23-11=-0");
    assertEquals("12-0", calculator4.getResult());

    insertValues(calculator5, "23*1=+8");
    assertEquals("23+8", calculator5.getResult());

    insertValues(calculator6, "23+000=*9");
    assertEquals("23*9", calculator6.getResult());
  }


  /**
   * A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = , 3 2
   * + 2 4 = - 10 =, etc.). Checks for valid sequence possible for both the calculators.
   */
  @Test
  public void testValidSequenceMultipleValues() {
    insertValues(calculator1, "23+2=-5+2*2");
    assertEquals("22*2", calculator1.getResult());

    insertValues(calculator2, "23+2=-5+2*2-");
    assertEquals("44-", calculator2.getResult());

    insertValues(calculator3, "23+2=-5+2*");
    assertEquals("22*", calculator3.getResult());

    insertValues(calculator4, "23+2=-5+2");
    assertEquals("20+2", calculator4.getResult());

    insertValues(calculator5, "32+24-10=");
    assertEquals("46", calculator5.getResult());

    insertValues(calculator6, "23+2-5*100");
    assertEquals("20*100", calculator6.getResult());

  }

  /**
   * Checks for boundary conditions - Operand overflow.
   */
  @Test
  public void testHigherOperandValues1() {
    try {
      insertValues(calculator1, "23-23323323323233232");

    } catch (RuntimeException e) {
      assertEquals("23-233233233", calculator1.getResult());
    }

  }

  /**
   * Checks for boundary conditions - Operand overflow.
   */
  @Test
  public void testHigherOperandValues2() {
    try {
      insertValues(calculator2, "23323323323233332-23");

    } catch (RuntimeException e) {
      assertEquals("233233233", calculator2.getResult());
    }

  }

  /**
   * The only valid operand characters are 0-9. Check for all numbers 0 1 2 3 4 5 6 7 8 9.
   */
  @Test
  public void testValidOperands() {
    insertValues(calculator1, "1+2+3+4+5+6+7+8+9");
    assertEquals("36+9", calculator1.getResult());
  }

  /**
   * The only valid operand characters are 0-9. '', !, @, #, $, %, ^, &, *, ?.
   */
  @Test
  public void testInvalidInput() {
    try {
      for (int i = 0; i < 128; i++) {
        if (!validCharacters.contains((char) i)) {
          calculator1.input((char) i);
        }
      }
    } catch (IllegalArgumentException e) {
      assertEquals("",calculator1.getResult());
      calculator1.input('C');
    }
  }

  /**
   * Both the calculator displays empty values if the input is =.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEquals1() {
    assertEquals("", calculator1.input('=').getResult());
  }

  /**
   * Both the calculator displays empty values if the input is =.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEquals2() {
    insertValues(calculator2, "3=");
  }


  /**
   * check clear works with multiple combinations Erases past input memory.
   */
  @Test
  public void testClearWorks() {

    insertValues(calculator1, "2C");
    assertEquals("", calculator1.getResult());
    insertValues(calculator2, "22C");
    assertEquals("", calculator2.input('2').input('2').input('C').getResult());
    insertValues(calculator3, "26+C");
    assertEquals("", calculator3.getResult());
    insertValues(calculator4, "26+3C");
    assertEquals("", calculator4.getResult());
    insertValues(calculator5, "26+3=C");
    assertEquals("", calculator5.getResult());
    insertValues(calculator6, "26+3=+C");
    assertEquals("", calculator5.getResult());

  }

  /**
   * check clear works with multiple combinations Erases past input memory.
   */
  @Test
  public void testClearOnError() {
    try {
      insertValues(calculator1, "23323323323233232");
    } catch (RuntimeException e) {
      assertEquals("233233233", calculator1.getResult());
      calculator1.input('C');
      assertEquals("", calculator1.getResult());
    }

  }

  /**
   * Checks Operand overflow condition -2,147,483,648 to 2,147,483,647 2,147,483,648(Runtime
   * error).
   */
  @Test(expected = RuntimeException.class)
  public void testOperandOverflowPositive() {
    insertValues(calculator1, "14748364829");
  }

  /**
   * Checks Operand overflow condition for negative value.
   */
  @Test(expected = RuntimeException.class)
  public void testOperandOverflowNegative() {
    insertValues(calculator1, "7-2147483648");
    assertEquals("7", calculator1.getResult());
  }

  /**
   * Checks Sum overflow condition.
   */
  @Test
  public void testSumOverflow1() {
    insertValues(calculator1, "2147483645+5=");
    assertEquals("0", calculator1.getResult());
  }

  /**
   * Checks Sum overflow.
   */
  @Test
  public void testSumOverflow2() {
    insertValues(calculator1, "2147483645+5=-10=");
    assertEquals("-10", calculator1.getResult());
  }

  /**
   * Checks Subtraction overflow condition.
   */
  @Test
  public void testSubtractionUnderflow() {
    insertValues(calculator1, "1-4-2147483646-9=");
    assertEquals("-9", calculator1.getResult());
  }

  /**
   * Checks multiply overflow condition.
   */
  @Test
  public void testMultiplyOverflow() {
    insertValues(calculator1, "2147483645*2=-5=");
    assertEquals("-5", calculator1.getResult());
  }
}
