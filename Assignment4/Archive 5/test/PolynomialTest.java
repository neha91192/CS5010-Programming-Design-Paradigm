import org.junit.Before;
import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests class for {@link polynomial.Polynomial}
 */
public class PolynomialTest {
  private Polynomial polynomial1;
  private Polynomial polynomial2;

  /**
   *
   */
  @Before
  public void setUp() {
    polynomial1 = new PolynomialImpl();
    polynomial2 = new PolynomialImpl();
  }

  /**
   *
   */
  @Test
  public void testCreateEmptyPolynomial() {
    assertEquals("0", polynomial1.toString());
  }

  /**
   *
   */
  @Test
  public void testCreateEmptyPolynomialOfDegree0Positive() {
    polynomial1.addTerm(4, 0);
    assertEquals("4", polynomial1.toString());
  }

  /**
   *
   */
  @Test
  public void testCreateEmptyPolynomialOfDegree0Negative() {
    polynomial1.addTerm(-4, 0);
    assertEquals("-4", polynomial1.toString());
  }

  /**
   *
   */
  @Test
  public void testAddTermToEmptyPolynomial() {
    polynomial1.addTerm(2, 2);
    assertEquals("2x^2", polynomial1.toString());
  }

  /**
   *
   */
  @Test
  public void testAddTermToExistingPolynomial1() {
    polynomial1.addTerm(2, 2);
    assertEquals("2x^2", polynomial1.toString());

    polynomial1.addTerm(2, 2);
    assertEquals("4x^2", polynomial1.toString());

    polynomial1.addTerm(5, 1);
    assertEquals("4x^2+5x^1", polynomial1.toString());

    polynomial1.addTerm(-7, 1);
    assertEquals("4x^2-2x^1", polynomial1.toString());

    polynomial1.addTerm(4, 0);
    assertEquals("4x^2-2x^1+4", polynomial1.toString());

    polynomial1.addTerm(-4, 0);
    assertEquals("4x^2-2x^1", polynomial1.toString());

    polynomial1.addTerm(2, 1);
    assertEquals("4x^2", polynomial1.toString());
  }

  /**
   *
   */
  @Test
  public void testAddTermToExistingPolynomial2() {
    polynomial1.addTerm(2, 2);
    assertEquals("2x^2", polynomial1.toString());

    polynomial1.addTerm(2, 2);
    assertEquals("4x^2", polynomial1.toString());

    polynomial1.addTerm(5, 1);
    assertEquals("4x^2+5x^1", polynomial1.toString());

    polynomial1.addTerm(-4, 2);
    assertEquals("5x^1", polynomial1.toString());

    polynomial1.addTerm(7, 7);
    assertEquals("7x^7+5x^1", polynomial1.toString());
  }

  /**
   *
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTermWithNegativePowers() {
    polynomial1.addTerm(2, -2);

  }

  /**
   *
   */
  @Test
  public void testCreatePolynomialWithString1() {
    polynomial2 = new PolynomialImpl("4x^3 +3x^1 -5");
    assertEquals("4x^3+3x^1-5", polynomial2.toString());

    polynomial1 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    assertEquals("-2x^5-3x^4+11x^1-5", polynomial1.toString());

    polynomial1 = new PolynomialImpl("-5");
    assertEquals("-5", polynomial1.toString());

    polynomial1 = new PolynomialImpl("102");
    assertEquals("102", polynomial1.toString());
  }
  /**
   *
   */
  @Test
  public void testCreatePolynomialWithString2() {
    polynomial2 = new PolynomialImpl("100x^3 +3x^20 -5");
    assertEquals("3x^20+100x^3-5", polynomial2.toString());

    polynomial1 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1 -3x^5");
    assertEquals("-5x^5-3x^4+11x^1-5", polynomial1.toString());

//    polynomial1 = new PolynomialImpl("0");
//    assertEquals("0", polynomial1.toString());

  }

  /**
   *
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePolynomialWithInvalidString1() {
    polynomial1 = new PolynomialImpl("4x2 +p");
  }

  /**
   *
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePolynomialWithInvalidString2() {
    polynomial1 = new PolynomialImpl("4x^2 +p");
  }

  /**
   *
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePolynomialWithInvalidString3() {
    polynomial1 = new PolynomialImpl("-5a");
  }

  /**
   *
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePolynomialWithInvalidString4() {
    polynomial2 = new PolynomialImpl("+3a^4 -2a^5 -5 -2a^4 +11a^1");

  }

  /**
   *
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePolynomialWithInvalidString5() {
    polynomial2 = new PolynomialImpl("+3X^4 -2X^5 -5 -2X^4 +11X^1");

  }
  /**
   *
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePolynomialWithInvalidString6() {
    polynomial2 = new PolynomialImpl("3x^2-4x^1 -2");
  }

  /**
   * Returns highest degree.
   */
  @Test
  public void testGetDegree1() {
    polynomial1.addTerm(2, 2);
    assertEquals("2x^2", polynomial1.toString());

    polynomial1.addTerm(2, 2);
    assertEquals("4x^2", polynomial1.toString());

    polynomial1.addTerm(5, 1);
    assertEquals("4x^2+5x^1", polynomial1.toString());
    assertEquals(2, polynomial1.getDegree());
  }

  /**
   * Returns highest degree.
   */
  @Test
  public void testGetDegree2() {
    polynomial1.addTerm(2, 2);
    assertEquals("2x^2", polynomial1.toString());

    polynomial1.addTerm(2, 2);
    assertEquals("4x^2", polynomial1.toString());

    polynomial1.addTerm(5, 1);
    assertEquals("4x^2+5x^1", polynomial1.toString());

    polynomial1.addTerm(-4, 2);
    assertEquals("5x^1", polynomial1.toString());
    assertEquals(1, polynomial1.getDegree());

    polynomial1.addTerm(3, 7);
    assertEquals("3x^7+5x^1", polynomial1.toString());
    assertEquals(7, polynomial1.getDegree());

    polynomial1.addTerm(3, 6);
    assertEquals("3x^7+3x^6+5x^1", polynomial1.toString());
    assertEquals(7, polynomial1.getDegree());
  }

  /**
   * Returns highest degree.
   */
  @Test
  public void testGetCoefficientForPower() {
    polynomial1.addTerm(2, 2);
    assertEquals("2x^2", polynomial1.toString());
    assertEquals(2, polynomial1.getCoefficient(2));

    polynomial1.addTerm(2, 2);
    assertEquals("4x^2", polynomial1.toString());
    assertEquals(4, polynomial1.getCoefficient(2));

    polynomial1.addTerm(5, 1);
    assertEquals("4x^2+5x^1", polynomial1.toString());
    assertEquals(5, polynomial1.getCoefficient(1));

    polynomial1.addTerm(7, 0);
    assertEquals("4x^2+5x^1+7", polynomial1.toString());
    assertEquals(7, polynomial1.getCoefficient(0));

    polynomial1.addTerm(-7, 0);
    assertEquals("4x^2+5x^1", polynomial1.toString());
    assertEquals(0, polynomial1.getCoefficient(0));

    polynomial1.addTerm(-5, 0);
    assertEquals("4x^2+5x^1-5", polynomial1.toString());
    assertEquals(-5, polynomial1.getCoefficient(0));

    polynomial1.addTerm(-4, 2);
    assertEquals("5x^1-5", polynomial1.toString());
    assertEquals(0, polynomial1.getCoefficient(2));
  }


  /**
   * Returns highest degree.
   */
  @Test
  public void testEvaluate1() {
    polynomial1.addTerm(2, 2);
    assertEquals("2x^2", polynomial1.toString());
    assertEquals(8.0, polynomial1.evaluate(2.0), 0.00001);

    polynomial1.addTerm(2, 2);
    assertEquals("4x^2", polynomial1.toString());
    assertEquals(16.0, polynomial1.evaluate(2.0), 0.00001);

    polynomial1.addTerm(5, 1);
    assertEquals("4x^2+5x^1", polynomial1.toString());
    assertEquals(26.0, polynomial1.evaluate(2.0), 0.00001);
  }

  /**
   * Returns highest degree.
   */
  @Test
  public void testEvaluate2() {
    polynomial1.addTerm(-19, 119);
    assertEquals("-19x^119", polynomial1.toString());
    assertEquals(-62.08648613554426, polynomial1.evaluate(1.01), 0.00001);

  }


  /**
   * Returns highest degree.
   */
  @Test
  public void testDerivative() {
    polynomial1.addTerm(2, 2);
    assertEquals("2x^2", polynomial1.toString());
    Polynomial newPolynomial = polynomial1.derivative();
    assertEquals("4x^1", newPolynomial.toString());

    polynomial1.addTerm(7, 3);
    assertEquals("7x^3+2x^2", polynomial1.toString());
    Polynomial newPolynomial2 = polynomial1.derivative();
    assertEquals("21x^2+4x^1", newPolynomial2.toString());

    polynomial1.addTerm(7, 0);
    assertEquals("7x^3+2x^2+7", polynomial1.toString());
    Polynomial newPolynomial3 = polynomial1.derivative();
    assertEquals("21x^2+4x^1", newPolynomial3.toString());
  }

  /**
   * Returns highest degree.
   */
  @Test
  public void testAddPolynomial() {
    polynomial1.addTerm(2, 2);
    assertEquals("2x^2", polynomial1.toString());
    polynomial1.addTerm(3, 3);
    assertEquals("3x^3+2x^2", polynomial1.toString());

    polynomial1.addTerm(-1, 0);
    assertEquals("3x^3+2x^2-1", polynomial1.toString());

    polynomial2.addTerm(4, 1);
    Polynomial newPolynomial = polynomial1.add(polynomial2);

    assertEquals("3x^3+2x^2+4x^1-1", newPolynomial.toString());
    polynomial2.addTerm(-4, 3);
    assertEquals("-4x^3+4x^1", polynomial2.toString());

    Polynomial newPolynomial2 = polynomial1.add(polynomial2);
    assertEquals("-1x^3+2x^2+4x^1-1", newPolynomial2.toString());
  }

  /**
   * Tests for Reflexivity of implemented equals method.
   */
  @Test
  public void testEqualsReflexivity() {
    polynomial1 = new PolynomialImpl("3x^1 +4x^3 -5");
    assertEquals("4x^3+3x^1-5", polynomial1.toString());
    polynomial2 = new PolynomialImpl("3x^1 +4x^3 -5");
    assertEquals("4x^3+3x^1-5", polynomial2.toString());

    assertEquals(true, polynomial1.equals(polynomial2));
  }

  /**
   * Tests for symmetry of implemented equals method.
   */
  @Test
  public void testEqualsSymmetry() {
    polynomial1 = new PolynomialImpl("3x^1 +4x^3 -5");
    assertEquals("4x^3+3x^1-5", polynomial1.toString());
    polynomial2 = new PolynomialImpl("4x^3 +3x^1 -5");
    assertEquals("4x^3+3x^1-5", polynomial2.toString());

    boolean result1 = polynomial1.equals(polynomial2);
    boolean result2 = polynomial2.equals(polynomial1);
    assertEquals(result1, result2);
  }

  /**
   * Tests for transitivity of implemented equals method.
   */
  @Test
  public void testEqualsTransitive() {
    polynomial1 = new PolynomialImpl("3x^1 +4x^3 -5");
    assertEquals("4x^3+3x^1-5", polynomial1.toString());
    polynomial2 = new PolynomialImpl("4x^3 +3x^1 -5");
    assertEquals("4x^3+3x^1-5", polynomial2.toString());
    Polynomial polynomial3 = new PolynomialImpl("-5 +3x^1 +4x^3");
    assertEquals("4x^3+3x^1-5", polynomial3.toString());


    boolean result1 = polynomial1.equals(polynomial2);
    boolean result2 = polynomial2.equals(polynomial3);
    boolean result3 = polynomial3.equals(polynomial1);
    assertEquals(result1, result2);
    assertEquals(result2, result3);
    assertEquals(result1, result3);
  }

}
