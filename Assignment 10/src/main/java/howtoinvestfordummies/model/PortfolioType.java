package howtoinvestfordummies.model;

/**
 * Represents the type of portfolio. High Level Investment Strategies Portfolio are recurring in
 * nature and simple portfolio is suitable for active buying of stocks.
 */
public enum PortfolioType {
  SIMPLE("SIMPLE"), RECURRING("RECURRING");
  String value;

  /**
   * Sets the value of enum.
   *
   * @param value in String.
   */
  PortfolioType(String value) {
    this.value = value;
  }
}
