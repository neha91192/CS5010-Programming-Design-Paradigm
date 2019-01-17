package polynomial;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 */
public class PolynomialADTEmptyNode implements PolynomialADTNode {
  /**
   * Represents current term of this Polynomial node.
   */
  private Term term;
  /**
   * Represents rest of the terms after this polynomial node.
   */
  private PolynomialADTNode rest;

  PolynomialADTEmptyNode() {
    this.term = new Term(0, 0);
  }

  /**
   *
   * @param term
   * @throws IllegalArgumentException
   */
  @Override
  public PolynomialADTNode addTerm(Term term) throws IllegalArgumentException {
    return new PolynomialADTElementNode(term, new PolynomialADTEmptyNode());
  }

  /**
   *
   * @return
   */
  @Override
  public Integer getDegree() {
    return this.term.getPower();
  }

  /**
   *
   * @param power
   * @return
   */
  @Override
  public Integer getCoefficient(Integer power) {
    return 0;
  }

  /**
   *
   * @param value
   * @return
   */
  @Override
  public Double evaluate(Double value) {
    return this.term.getCoefficient() * value;
  }

  /**
   *
   * @param polynomial
   * @return
   */
  @Override
  public PolynomialADTNode addPolynomial(PolynomialADTNode polynomial) {
    return new PolynomialADTEmptyNode();
  }

  /**
   *
   * @return
   */
  @Override
  public PolynomialADTNode derivative() {
    return new PolynomialADTEmptyNode();
  }

  /**
   * Starting from this list of T, the resulting list of type R is an element that contains this
   * data converted to T, followed by the rest of the converted list
   */
  @Override
  public PolynomialADTNode map(Function<PolynomialADTNode, Term> converter) {
    return new PolynomialADTEmptyNode();
  }

  @Override
  public PolynomialADTNode mapToNode(Function<PolynomialADTNode, PolynomialADTNode> converter) {
    return converter.apply(this);
  }

  /**
   *
   * @param predicate
   * @return
   */
  @Override
  public PolynomialADTNode filter(Predicate<Term> predicate) {
    return new PolynomialADTEmptyNode();
  }

  @Override
  public Term getCurrentTerm() {
    return new Term(0,0);
  }

  @Override
  public Double reduce(Function<Term, Double> converter) {
    return 0.0;
  }


  /**
   *
   * @return
   */
  @Override
  public String toString() {
    return String.format("%d", this.term.getCoefficient());
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
    if (!(polynomialNode instanceof PolynomialADTEmptyNode)) {
      return false;
    }
    PolynomialADTEmptyNode elementNode = (PolynomialADTEmptyNode) polynomialNode;
    return Objects.equals(term, elementNode.term) &&
            Objects.equals(rest, elementNode.rest);
  }

  /**
   *
   * @return
   */
  @Override
  public int hashCode() {
    return Objects.hash(term, rest);
  }
}
