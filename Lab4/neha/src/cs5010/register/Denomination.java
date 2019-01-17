package cs5010.register;

/**
 * Represents coin denomination class.
 */
public enum Denomination {
  TEN(1000), FIVE(500), ONE(100), QUARTER(25), DIME(10), NICKLE(5), PENNY(1);

  /**
   * Value of the denomination in cents.
   */
  private final Integer value;

  /**
   * Constructor which sets denomination value.
   */
  Denomination(Integer value) {
    this.value = value;
  }

  /**
   * Returns value of the calling denomination.
   *
   * @return value of the denomination represented in cents in Integer.
   */
  public Integer getValue(){
    return this.value;
  }

}
