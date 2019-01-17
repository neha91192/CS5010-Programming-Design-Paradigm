package polynomial;


import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
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
   *
   * @param term
   * @throws IllegalArgumentException
   */
  @Override
  public PolynomialADTNode addTerm(Term term) throws IllegalArgumentException {
    if (this.term.getPower().equals(term.getPower())) {
      //merge two terms with same degree.
      int sum = this.term.getCoefficient() + term.getCoefficient();
      if (sum == 0) {
        return this.rest;
      }
      this.term.setCoefficient(sum);
    } else if (this.term.getPower() > term.getPower()) {
      //search for lower degrees, if new term reaches empty node, add element at the end of the list.
      this.rest = this.rest.addTerm(term);
    } else {
      //add at front
      return new PolynomialADTElementNode(term, this);
    }
    return this;
  }


  /**
   * Finds maximum degree in the element node.
   */
  @Override
  public Integer getDegree() {
    return Math.max(this.term.getPower(), this.rest.getDegree());
  }

  /**
   *
   * @param power
   * @return
   */
  @Override
  public Integer getCoefficient(Integer power) {
    return this.filter(term -> term.getPower().equals(power)).getCurrentTerm().getCoefficient();
  }

  /**
   *
   * @param value
   * @return
   */
  @Override
  public Double evaluate(Double value) {
    return this.reduce(term -> term.evaluate(value));
  }

  /**
   *
   * @param polynomial
   * @return
   */
  @Override
  public PolynomialADTNode addPolynomial(PolynomialADTNode polynomial) {
    PolynomialADTNode newNode = this.map(node ->
            polynomial.addTerm(new Term(node.getCurrentTerm().getCoefficient(),
                    node.getCurrentTerm().getPower())).getCurrentTerm());
    System.out.println(newNode.toString());
    return newNode;

  }

  /**
   *
   * @return
   */
  @Override
  public PolynomialADTNode derivative() {
    return this.map(polynomialADTNode -> polynomialADTNode.getCurrentTerm().findDerivative());
  }


  /**
   * Starting from this list of T, the resulting list of type R is an element that contains this
   * data converted to T, followed by the rest of the converted list
   */
  @Override
  public PolynomialADTNode map(Function<PolynomialADTNode, Term> converter) {
    return new PolynomialADTElementNode(converter.apply(this), this.rest.map(converter));
  }


  @Override
  public PolynomialADTNode mapToNode(Function<PolynomialADTNode, PolynomialADTNode> converter) {
  return new PolynomialADTElementNode(this.term, this.rest.mapToNode(converter));
  }


  /**
   * Starting from this list of T, the resulting list of type R is an element that contains this
   * data converted to T, followed by the rest of the converted list
   */
  @Override
  public PolynomialADTNode filter(Predicate<Term> predicate) {
    if (predicate.test(term)) {
      return new PolynomialADTElementNode(this.term, this.rest.filter(predicate));
    }
    return this.rest.filter(predicate);
  }

  /**
   *
   * @param
   * @return
   */
  @Override
  public Double reduce(Function<Term, Double> converter) {
    return converter.apply(this.term) + this.rest.reduce(converter);
  }


  @Override
  public Term getCurrentTerm() {
    return this.term;
  }

  /**
   *
   * @return
   */
  @Override
  public String toString() {
    if (this.rest instanceof PolynomialADTEmptyNode) {
      return this.term.toString();
    }
    return this.term.toString() + "" + this.rest.toString();
  }

  /**
   *
   * @param polynomialNode
   * @return
   */
  @Override
  public boolean equals(Object polynomialNode) {
    if (this == polynomialNode) {
      return true;
    }
    if (!(polynomialNode instanceof PolynomialADTElementNode)) {
      return false;
    }
    PolynomialADTElementNode elementNode = (PolynomialADTElementNode) polynomialNode;
    return Objects.equals(term, elementNode.term) &&
            Objects.equals(rest, elementNode.rest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(term, rest);
  }
}
