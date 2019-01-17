package polynomial;


import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This class represents Polynomial nodes which contains some term. It implements {@link
 * PolynomialADTNode} which has defined certain node related features in the specification.
 */
public class PolynomialADTElementNode implements PolynomialADTNode {

  /**
   * Represents current term of this Polynomial node.
   */
  private Term term;
  /**
   * Represents rest of the terms after this polynomial node.
   */
  private PolynomialADTNode rest;

  /**
   * Constructor for initialising Polynomial Element node.
   *
   * @param term current term of this element node.
   * @param rest list of terms next to this element node.
   */
  PolynomialADTElementNode(Term term, PolynomialADTNode rest) {
    this.term = term;
    this.rest = rest;
  }

  /**
   * Adds a term in the {@link PolynomialADTElementNode}. It first checks if the power of input term
   * is same as the current head of this element node. In that case, it merges if the resulting
   * coefficient sum is not 0. If the current term has higher power, it searches recursively for the
   * lower power in the current node otherwise add the input term at the front.
   *
   * @param term containing coefficient and power.
   * @return PolynomialADTNode containing updated term.
   */
  @Override
  public PolynomialADTNode addTerm(Term term) {
    if (this.term.getPower().equals(term.getPower())) {
      //merge two terms with same degree.
      int sum = this.term.getCoefficient() + term.getCoefficient();
      if (sum == 0) {
        return this.rest;
      }
      this.term.setCoefficient(sum);
    } else if (this.term.getPower() > term.getPower()) {
      //search for lower degrees, if new term reaches empty node, add element at the end.
      this.rest = this.rest.addTerm(term);
    } else {
      //add at front
      return new PolynomialADTElementNode(term, this);
    }
    return this;
  }


  /**
   * Finds maximum degree in the element node. The maximum degree can be described as maximum of
   * current power vs maximum power found in the rest of the node.
   *
   * @return Integer value of degree.
   */
  @Override
  public Integer getDegree() {
    return Math.max(this.term.getPower(), this.rest.getDegree());
  }

  /**
   * /** Takes power in Integer and returns corresponding coefficient of that term.
   *
   * @param power of type Integer.
   * @return coefficient of type Integer.
   */
  @Override
  public Integer getCoefficient(Integer power) {
    return this.filter(term -> term.getPower().equals(power)).getCurrentTerm().getCoefficient();
  }

  /**
   * For the corresponding value, it evaluates value of the polynomial.
   *
   * @param value of type Double.
   * @return result of evaluation of type Double.
   */
  @Override
  public Double evaluate(Double value) {
    return this.reduce(term -> term.evaluate(value));
  }


  /**
   * Finds derivative of the existing polynomial and returns new derived polynomial.
   *
   * @return derived polynomial of type PolynomialADTNode.
   */
  @Override
  public PolynomialADTNode derivative() {
    return this.map(polynomialADTNode -> polynomialADTNode.getCurrentTerm().findDerivative());
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
    return new PolynomialADTElementNode(converter.apply(this), this.rest.map(converter));
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
    if (predicate.test(term)) {
      return new PolynomialADTElementNode(this.term, this.rest.filter(predicate));
    }
    return this.rest.filter(predicate);
  }

  /**
   * This reduce function takes {@link Term} as an input to return a value in Double.
   *
   * @param converter applies conversion of Term to Double object.
   * @return Double containing reduced value.
   */
  @Override
  public Double reduce(Function<Term, Double> converter) {
    return converter.apply(this.term) + this.rest.reduce(converter);
  }



  /**
   * Returns the current term of the node.
   *
   * @return term which represents current Term object in PolynomialADTNode.
   */
  @Override
  public Term getCurrentTerm() {
    return this.term;
  }

  /**
   * Returns String interpretation of the PolynomialADTNode. It is represented as String
   * representation of current term plus String representation of the rest of the node.
   *
   * @return String representation of this node.
   */
  @Override
  public String toString() {
    if (this.rest instanceof PolynomialADTEmptyNode) {
      return this.term.toString();
    }
    return this.term.toString() + "" + this.rest.toString();
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
    if (!(object instanceof PolynomialADTElementNode)) {
      return false;
    }
    PolynomialADTElementNode elementNode = (PolynomialADTElementNode) object;
    return Objects.equals(term, elementNode.term) &&
            Objects.equals(rest, elementNode.rest);
  }

  /**
   * Generates hashcode from the objects term and rest.
   *
   * @return integer value of the hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(term, rest);
  }
}
