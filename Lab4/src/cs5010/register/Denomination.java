package cs5010.register;

/**
 * Represents denomination class. All types of denomination with name, value in cents and category
 * as "Dollar" or "Cents".
 */
public enum Denomination {
  TEN(1000, "Dollar"), FIVE(500, "Dollar"), ONE(100, "Dollar"), QUARTER(25, "Cents"),
  DIME(10, "Cents"), NICKLE(5, "Cents"), PENNY(1, "Cents");

  /**
   * Value of the denomination in cents.
   */
  private final Integer value;
  /**
   * Category of the denomination in String. This could be either Dollar or Cents.
   */
  private final String category;

  /**
   * Constructor which sets denomination value.
   *
   * @param value in cents form as Integer.
   * @param type  of denomination in String.
   */
  Denomination(Integer value, String type) {
    this.value = value;
    this.category = type;
  }

  /**
   * Returns value of the calling denomination.
   *
   * @return value of the denomination represented in cents in Integer.
   */
  public Integer getValue() {
    return this.value;
  }

  /**
   * Returns category of the calling denomination.
   *
   * @return category of the denomination represented in cents in String.
   */
  public String getCategory() {
    return this.category;
  }

}
