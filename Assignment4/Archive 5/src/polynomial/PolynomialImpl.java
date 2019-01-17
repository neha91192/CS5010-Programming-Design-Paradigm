package polynomial;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * <p>This class implements several operations defined in {@link Polynomial} interface.</p>
 * <p>The implementation details as well as assumptions are as follows:</p>
 * <ul>
 * <ol>Supports polynomial operations only for single variable x.</ol>
 * <ol>Two polynomials are equal if they contain exactly the same terms.</ol>
 * <ol>Supports the storage of <strong>head</strong> of type {@link PolynomialADTNode} which keeps
 * the track of first term in the polynomial. Majority of the operation involves accessing or
 * updating this head.</ol>
 * </ul>
 */
public class PolynomialImpl implements Polynomial, Comparable {

  /**
   * Represents the first term of the Polynomial of type {@link PolynomialADTNode}.
   */
  private PolynomialADTNode head;

  /**
   * Constructs polynomial with no coefficients. Sets the head as {@link PolynomialADTEmptyNode}.
   */
  public PolynomialImpl() {
    head = new PolynomialADTEmptyNode();
  }

  /**
   * <p>Takes polynomial as String and initialises each term in the polynomial. The input string is
   * separated by space which helps in collecting the terms one by one and therefore further helps
   * in retrieving specifics of the terms such as coefficient and power. </p>
   * <p>To perform this operation, this method uses {@link Scanner} class to parse the polynomial
   * and further calls {@link PolynomialImpl#parseTerm(String)} which returns {@link Term}
   * object.</p>
   * <p>Each term is then added to the head using {@link PolynomialADTNode#addTerm(Term)}. The head
   * is updated when all the terms have been parsed.</p>
   *
   * @param polynomial of type String, separated by space between two terms.
   */
  public PolynomialImpl(String polynomial) throws IllegalArgumentException {
    Scanner scanner = new Scanner(polynomial);
    head = new PolynomialADTEmptyNode();
    while (scanner.hasNext()) {
      try {
        Term term = parseTerm(scanner.next());
        head = head.addTerm(term);
      } catch (InputMismatchException e) {
        throw new IllegalArgumentException("Invalid polynomial String");
      }
    }
  }

  /**
   * Constructs Polynomial instance with the given head. Helps in instantiating the new object of
   * this class with the new head.
   */
  private PolynomialImpl(PolynomialADTNode head) {
    this.head = head;
  }

  /**
   * Takes two parameters - coefficient and power and updates the polynomial. Calls {@link
   * PolynomialADTNode#addTerm(Term)} to perform addition of this term at a specific position.
   *
   * @param coefficient contains coefficient of the term in Integer.
   * @param power       contains power of the term in Integer.
   * @throws IllegalArgumentException in case negative power is provided which makes the term
   *                                  invalid.
   */
  @Override
  public void addTerm(Integer coefficient, Integer power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Negative powers are not allowed");
    }
    Term newTerm = new Term(coefficient, power);
    head = head.addTerm(newTerm);
  }

  /**
   * Returns the highest degree of the polynomial. Uses {@link PolynomialADTNode#getDegree()} to
   * find the highest degree of the polynomial starting from head node.
   *
   * @return value containing degree of the polynomial in int.
   */
  @Override
  public int getDegree() {
    return this.head.getDegree();
  }

  /**
   * Finds the coefficient corresponding to given power.
   * <p>Uses {@link PolynomialADTNode#getCoefficient(Integer)} to find coefficient of the given
   * power starting from head node.</p>
   *
   * @param power of the term in Integer.
   * @return coefficient of the term in int.
   */
  @Override
  public int getCoefficient(Integer power) {
    return this.head.getCoefficient(power);
  }

  /**
   * Substitutes the value provided in the input with the variable of the polynomial. Evaluates the
   * final value starting from head node.
   *
   * @param value of type Double.
   */
  @Override
  public Double evaluate(Double value) {
    return this.head.evaluate(value);
  }

  /**
   * Takes two polynomial and returns a new polynomial of combined terms of both the polynomials.
   *
   * @param polynomial of type Polynomial which needs to be added to the current polynomial.
   * @return polynomial object of type Polynomial.
   */
  @Override
  public Polynomial add(Polynomial polynomial) {
    PolynomialImpl newPolynomial = new PolynomialImpl();

    int degree = Math.max(this.getDegree(),polynomial.getDegree());

    while(degree!=-1) {
      int newCoefficient = this.getCoefficient(degree)+polynomial.getCoefficient(degree);
      newPolynomial.addTerm(newCoefficient,degree);
      degree--;
    }
    return newPolynomial;
  }

  /**
   * Formats the string by adding spaces between the terms.
   *
   * @param polynomial of type String.
   * @return polynomial of type String.
   */
  private String format(String polynomial) {
    polynomial = polynomial.replaceAll("-", " -");
    polynomial = polynomial.replaceAll("\\+", " \\+");
    return polynomial;
  }

  /**
   * Finds the derivative of the given polynomial. Returns the new derived instance of {@link
   * Polynomial} obtained from {@link PolynomialADTNode#derivative()}.
   *
   * @return derived polynomial instance.
   */
  @Override
  public Polynomial derivative() {
    PolynomialADTNode newHead = this.head.derivative();
    return new PolynomialImpl(newHead);
  }

  /**
   * Returns String representation of this polynomial. Calls {@link PolynomialADTNode#toString()} to
   * find String representation of respective nodes and returns the accumulated string obtained by
   * head node.
   *
   * @return value of type String.
   */
  @Override
  public String toString() {
    String value = head.toString();
    if (!value.equals("") && value.substring(0, 1).equals("+")) {
      return value.substring(1);
    } else return value;
  }

  /**
   * Finds relative comparison between two polynomials. A polynomial is greater than the other if
   * the sum of all the terms for a given value is greater than the other polynomial. In that case,
   * we return 1, otherwise -1. We use {@link Polynomial#evaluate(Double)} function to find this
   * relation between two polynomial that needs to be compared. If the sum equals to 0, this method
   * returns 0.
   *
   * @param object that needs to be compared with the current polynomial Object.
   * @return int depending upon the result of comparison.
   * @throws ClassNotFoundException in case the object's type prevents it from being compared to
   *                                this object
   */
  @Override
  public int compareTo(Object object) {
    PolynomialImpl polynomial = (PolynomialImpl) object;
    if (this.evaluate(1.0) > polynomial.evaluate(1.0)) {
      return 1;
    } else if (this.evaluate(1.0) < polynomial.evaluate(1.0)) {
      return -1;
    }
    return 0;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof PolynomialImpl)) {
      return false;
    }
    PolynomialImpl polynomialObj = (PolynomialImpl) object;

    return this.head.equals(polynomialObj.head);
  }


  /**
   *
   * @return
   */
  @Override
  public int hashCode() {
    return Objects.hash(head);
  }

  /**
   * Takes term as string and converts into {@link Term}.
   */
  private Term parseTerm(String term) throws IllegalArgumentException, InputMismatchException {
    Scanner termScanner = new Scanner(term).useDelimiter("x\\^");
    int coefficient = termScanner.nextInt();
    int power = 0;
    if (termScanner.hasNextInt()) {
      power = termScanner.nextInt();
      if (power < 0) {
        throw new IllegalArgumentException("Powers cannot be negative");
      }
    }
    if (termScanner.hasNext()) {
      System.out.println(termScanner.nextInt());
      throw new IllegalArgumentException("Invalid string");
    }
    return new Term(coefficient, power);
  }


}
