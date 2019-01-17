package polynomial;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents an Abstract Datatype for any Node in Polynomial List representation. As the name
 * suggests, this node represents each node of polynomial which is a term containing coefficient and
 * power. This interface defines set of operations which could be operated at node level and helps
 * to represent a polynomial as a list of terms. Following are the methods described for this
 * PolynomialADTNode interface:
 * <ul>
 * <li><strong>addTerm:</strong> Adds a term object to the current calling node.</li>
 * <li><strong>getDegree:</strong> Returns degree by considering all the connected polynomial
 * nodes.</li>
 * <li><strong>getCoefficient:</strong> For any given power or degree, returns coefficient present
 * in the polynomial.</li>
 * <li><strong>evaluate:</strong></li> Calculates value of the current node for a given value of x.
 * <li><strong>addPolynomial: </strong> Adds two polynomials and returns a polynomial object.</li>
 * </ul>
 */
public interface PolynomialADTNode {

  /**
   * Takes {@link Term} and adds new term to the existing PolynomialADTNode.
   *
   * @param term of type {@link Term}.
   * @throws IllegalArgumentException in case Term is invalid.
   */
  PolynomialADTNode addTerm(Term term);

  /**
   * Returns highest degree in the PolynomialADTNode.
   *
   * @return degree of type Integer.
   */
  Integer getDegree();

  /**
   * Takes power in Integer and returns corresponding coefficient of that term.
   *
   * @param power of type Integer.
   * @return coefficient of type Integer.
   */
  Integer getCoefficient(Integer power);

  /**
   * For the corresponding value, it evaluates value of the polynomial.
   *
   * @param value of type Double.
   * @return result of evaluation of type Double.
   */
  Double evaluate(Double value);

  /**
   * Finds derivative of the existing polynomial and returns new derived polynomial.
   *
   * @return derived polynomial of type PolynomialADTNode.
   */
  PolynomialADTNode derivative();

  /**
   * Returns the current term of the node.
   *
   * @return term which represents current Term object in PolynomialADTNode.
   */
  Term getCurrentTerm();

  /**
   * This map function helps to build another PolynomialADTNode having different term values. So for
   * each PolynomialADTNode, you can apply updates in {@link Term} object which builds up new
   * PolynomialADTNode instance and returns the same at the end.
   *
   * @param converter which helps in applying conversion between two terms.
   * @return instance of PolynomialADTNode.
   */
  PolynomialADTNode map(Function<PolynomialADTNode, Term> converter);


  /**
   * This filter function helps in taking {@link Term} as an input to Predicate and tests if it
   * works for a particular condition. If the condition holds true, it keeps that value in the
   * current PolynomialADTNode, otherwise omits to consider it in the final output.
   *
   * @param predicate function used to test a condition.
   * @return PolynomialADTNode considering the value to be retained.
   */
  PolynomialADTNode filter(Predicate<Term> predicate);

  /**
   * This reduce function takes {@link Term} as an input to return a value in Double.
   *
   * @param converter applies conversion of Term to Double object.
   * @return Double containing reduced value.
   */
  Double reduce(Function<Term, Double> converter);


}
