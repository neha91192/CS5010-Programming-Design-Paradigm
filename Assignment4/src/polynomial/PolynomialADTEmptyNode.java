package polynomial;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This class represents Polynomial nodes which contains empty term. It implements {@link
 * PolynomialADTNode} which has defined certain node related features in the specification.
 */
public class PolynomialADTEmptyNode implements PolynomialADTNode {
  /**
   * Represents current term of this Polynomial node.
   */
  private Term term;

  PolynomialADTEmptyNode() {
    this.term = new Term(0, 0);
  }

  /**
   * Adds given term to empty node.
   *
   * @param term containing coefficient.
   */
  @Override
  public PolynomialADTNode addTerm(Term term) {
    return new PolynomialADTElementNode(term, new PolynomialADTEmptyNode());
  }

  /**
   * Returns highest degree of empty node which is 0.
   *
   * @return degree of type integer.
   */
  @Override
  public Integer getDegree() {
    return this.term.getPower();
  }

  /**
   * Returns coefficient of the given degree which is 0.
   *
   * @param power for which coefficient needs to be returned.
   *
   * @return int containing coefficient.
   */
  @Override
  public Integer getCoefficient(Integer power) {
    return 0;
  }

  /**
   * For the corresponding value, it evaluates value of the polynomial.
   *
   * @param value of type Double.
   * @return result of evaluation of type Double.
   */
  @Override
  public Double evaluate(Double value) {
    return this.term.getCoefficient() * value;
  }

  /**
   * Finds derivative of the existing polynomial and returns new derived polynomial.
   *
   * @return derived polynomial of type PolynomialADTNode.
   */
  @Override
  public PolynomialADTNode derivative() {
    return new PolynomialADTEmptyNode();
  }

  /**
   * This map function helps to build another PolynomialADTNode having different term values. So for
   * each PolynomialADTNode, you can apply updates in {@link Term} object which builds up new
   * PolynomialADTNode instance and returns the same at the end.
   *
   * @param converter which helps in applying conversion between two terms.
   * @return instance of PolynomialADTNode.
   */
  @Override
  public PolynomialADTNode map(Function<PolynomialADTNode, Term> converter) {
    return new PolynomialADTEmptyNode();
  }


  /**
   * This filter function helps in taking {@link Term} as an input to Predicate and tests if it
   * works for a particular condition. If the condition holds true, it keeps that value in the
   * current PolynomialADTNode, otherwise omits to consider it in the final output.
   *
   * @param predicate function used to test a condition.
   * @return PolynomialADTNode considering the value to be retained.
   */
  @Override
  public PolynomialADTNode filter(Predicate<Term> predicate) {
    return new PolynomialADTEmptyNode();
  }


  /**
   * This reduce function takes {@link Term} as an input to return a value in Double.
   *
   * @param converter applies conversion of Term to Double object.
   * @return Double containing reduced value.
   */
  @Override
  public Double reduce(Function<Term, Double> converter) {
    return 0.0;
  }

  /**
   * Returns the current term of the node.
   *
   * @return term which represents current Term object in PolynomialADTNode.
   */

  @Override
  public Term getCurrentTerm() {
    return new Term(0, 0);
  }

  /**
   *  Returns string representation of the Empty node which is "0".
   *
   * @return string representing the empty node.
   */
  @Override
  public String toString() {
    return String.format("%d", this.term.getCoefficient());
  }

  /**
   * Checks for equality between polynomialNode and the current Element node. Two polynomials are
   * same if they contain same terms.
   *
   * @param object to be checked for equality.
   * @return boolean if the object is equal.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof PolynomialADTEmptyNode)) {
      return false;
    }
    PolynomialADTEmptyNode elementNode = (PolynomialADTEmptyNode) object;
    return Objects.equals(term, elementNode.term);
  }

  /**
   * Generates hashcode from the term objects.
   *
   * @return integer value of the hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(term);
  }
}
