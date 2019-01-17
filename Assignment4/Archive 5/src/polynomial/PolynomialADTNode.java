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
   * @param term of type {@link Term}.
   * @throws IllegalArgumentException in case Term is invalid.
   */
  PolynomialADTNode addTerm(Term term) throws IllegalArgumentException;

  /**
   *
   * @return
   */
  Integer getDegree();

  /**
   *
   * @param power
   * @return
   */
  Integer getCoefficient(Integer power);

  /**
   *
   * @param value
   * @return
   */
  Double evaluate(Double value);

  /**
   * Takes in the entire polynomial node and returns merged Polynomial.
   */
  PolynomialADTNode addPolynomial(PolynomialADTNode polynomial);

  /**
   *
   * @return
   */
  PolynomialADTNode derivative();

  /**
   *
   * @param converter
   * @return
   */
  PolynomialADTNode map(Function<PolynomialADTNode, Term> converter);

  /**
   *
   * @param converter
   * @return
   */
  PolynomialADTNode mapToNode(Function<PolynomialADTNode, PolynomialADTNode> converter) ;

  /**
   *
   * @param predicate
   * @return
   */
  PolynomialADTNode filter(Predicate<Term> predicate);

  /**
   *
   * @return
   */
  Term getCurrentTerm();

  /**
   *
   * @param converter
   * @return
   */
  Double reduce(Function<Term, Double> converter);
}
