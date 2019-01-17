package polynomial;

/**
 * <p>This interface represents a Polynomial which is made up of number of terms. A polynomial
 * contains degree, which is the highest power of the variable in a terms. </p>
 * <p>For this interface, we are only considering polynomials whose powers are positive and
 * coefficient values are integral numbers.</p>
 * <p>Any two polynomials are supposed to be equal if they contain exactly the same terms. We
 * expect to expose certain capabilities to the implementation of this interface which includes
 * evaluating the polynomial, adding two existing polynomials, finding the derivative of the
 * polynomial or even finding if the given two polynomials are equal. </p>
 * <p>There are set of behaviors which this polynomial interface are expected to
 * follow:</p>
 * <ul>
 * <li><strong>addTerm: </strong> Should be able to add a new term to the existing polynomial.</li>
 * <li><strong>getDegree: </strong> Should return the highest power out of all the terms in the
 * polynomial.</li>
 * <li><strong>getCoefficient: </strong>Given a power, polynomial interface should return the
 * coefficient corresponding to that power.</li>
 * <li><strong>evaluate: </strong> Should be able to evaluate the value of the polynomial if any
 * value is substituted in all the variables of the polynomial.</li>
 * <li><strong>add: </strong>Merges two polynomial to make it as a single polynomial.</li>
 * <li><strong>derivative: </strong>Finds out the derivative of given polynomial and returns a new
 * polynomial object.</li>
 * </ul>
 * The implementation of this interface should consider the following constraints-
 * <ul>
 * <ol>Should not use any of the Java's inbuilt List or any other data structure from {@link
 * java.util.Collections}</ol>
 * <ol>Should not hold any terms whose coefficient is 0.</ol>
 * <ol>Should not hold any terms whose power is negative.</ol>
 * <ol>Should implement a Constructor that creates polynomial object using the String representing
 * that polynomial.In this String representation, terms should be separated by space.</ol>
 * <ol>Should have a method that determines if two given polynomials are equal.</ol>
 * <ol>Should provide string representation of the polynomial. An empty polynomial should be
 * represented as "0".</ol>
 * </ul>
 */
public interface Polynomial {
  /**
   * This method takes two values as implied from the method signature -
   * <strong>coefficient</strong> and <strong>power</strong> of the new term that needs to be
   * added. Since the polynomial only supports terms having positive power and integral coefficient
   * numbers, any input values not belonging to the range should throw {@link
   * IllegalArgumentException}. Note that, this method should mutate and add new term to the calling
   * polynomial.
   *
   * @param coefficient represents coefficient of the term in Integer.
   * @param power       contains power of the term in Integer.
   * @throws IllegalArgumentException in case the term is invalid.
   */
  void addTerm(Integer coefficient, Integer power) throws IllegalArgumentException;

  /**
   * Returns degree of this polynomial. Degree of any polynomial is the highest power of the
   * variable in a term.
   *
   * @return value containing degree of polynomial in int.
   */
  int getDegree();

  /**
   * Fetches the coefficient of the term corresponding to the provided power.
   *
   * @return value containing coefficient of the power.
   */
  int getCoefficient(Integer power);

  /**
   * Evaluates value of the polynomial for the given substituting Double value for the variable.
   *
   * @param value of type Double.
   * @return evaluated value of the polynomial of type Double.
   */
  Double evaluate(Double value);

  /**
   * Takes polynomial as an input and returns the added polynomial. This method should return a new
   * instance of the polynomial which contains the sum of previous as well as the new polynomial.
   *
   * @param polynomial of type Polynomial which needs to be added with the current polynomial.
   */
  Polynomial add(Polynomial polynomial);

  /**
   * Calculates derivative of the polynomial and returns the new Polynomial instance created by
   * differentiating the current polynomial.
   *
   * @return polynomial of type Polynomial.
   */
  Polynomial derivative();
}
