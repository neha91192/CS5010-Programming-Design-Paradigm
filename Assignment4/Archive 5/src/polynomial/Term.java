package polynomial;

import java.util.Objects;

/**
 * Data transfer object containing set of related details and operations for a given term.
 */
public class Term {

  /**
   * Represents coefficient of this term.
   */
  private Integer coefficient;
  /**
   * Represents non-negative power of this term.
   */
  private Integer power;
  /**
   * Initialises Term using coefficient and power values.
   *
   * @param coefficient of type Integer.
   * @param power       of type Integer.
   */
  Term(Integer coefficient, Integer power) {
    this.coefficient = coefficient;
    this.power = power;
  }

  /**
   * Returns coefficient value for the term.
   *
   * @return coefficient of type Integer.
   */
  Integer getCoefficient() {
    return coefficient;
  }

  /**
   * Sets coefficient value for this term.
   *
   * @param coefficient of type Integer.
   */
  void setCoefficient(Integer coefficient) {
    this.coefficient = coefficient;
  }

  /**
   * Returns power of this term.
   *
   * @return power of type Integer.
   */
  Integer getPower() {
    return power;
  }

  /**
   * Initialises power value for this term.
   *
   * @param power of type Integer.
   */
  public void setPower(Integer power) {
    this.power = power;
  }

  /**
   * Converts this term to String.
   *
   * @return String value of this term.
   */
  public String toString() {
    String sign = "";
    if (this.coefficient > 0) {
      sign = "+";
    }
    if (this.coefficient.equals(0)) {
      return "";
    }
    if (this.power.equals(0)) {
      return String.format("%s%d", sign, this.coefficient);
    }
    return String.format("%s%dx^%d", sign, this.coefficient, this.power);

  }

  /**
   * While comparing if two terms are equal, positive sign is ignored. So if +4 is compared with 4,
   * both the terms will be equal and return true.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Term)) {
      return false;
    }
    Term term = (Term) object;
    return Objects.equals(coefficient, term.coefficient) &&
            Objects.equals(power, term.power);
  }

  /**
   *
   * @return
   */
  @Override
  public int hashCode() {
    return Objects.hash(coefficient, power);
  }


  /**
   *
   * @param value
   * @return
   */
  Double evaluate(Double value) {
    return this.getCoefficient() * Math.pow(value, this.getPower());
  }

  /**
   *
   * @return
   */
  Term findDerivative() {
    return new Term(this.getCoefficient() * this.getPower(),
            this.getPower() - 1);
  }
}
