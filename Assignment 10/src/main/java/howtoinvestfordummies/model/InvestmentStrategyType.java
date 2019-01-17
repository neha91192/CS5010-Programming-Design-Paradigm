package howtoinvestfordummies.model;

/**
 * Enumeration representing Investment Strategy type supported by this application.
 */
public enum InvestmentStrategyType {
  DOLLAR_COST_AVERAGING(1, "Dollar Cost Averaging");

  /**
   * Value for the InvestmentStrategyType.
   */
  int value;
  /**
   * Display value for the InvestmentStrategyType.
   */
  String displayValue;

  /**
   * Initializes this Enumeration by setting up value and display value.
   *
   * @param value        in int.
   * @param displayValue in String.
   */
  InvestmentStrategyType(int value, String displayValue) {
    this.displayValue = displayValue;
    this.value = value;
  }

  /**
   * Returns display value for the InvestmentStrategyType.
   *
   * @return display value in String.
   */
  public String getDisplayValue() {
    return this.displayValue;
  }
}
