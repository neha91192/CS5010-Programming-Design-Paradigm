package howtoinvestfordummies.view;

import java.util.Date;
import java.util.List;

/**
 * Represents a dedicated view interface with the basic responsibility of taking input
 * from user and
 * transmitting output for different cases using Graphical User Interface.
 */
public interface IInvestmentGUIView extends IInvestmentView {

  /**
   * Make the view visible. This is usually called after the view is constructed.
   */
  void makeVisible();

  /**
   * Sets the display for create portfolio panel on the GUI.
   *
   * @param s is the name of the portfolio entered by the user in the view
   */
  void setCreatePortfolioDisplay(String s);

  /**
   * Gets the input fields of create portfolio panel on the GUI.
   *
   * @return string output after creating the portfolio
   */
  String getCreatePortfolioInputString();

  /**
   * Clear method to clear the input fields for create portfolio panel on the GUI.
   */
  void clearCreatePortfolioInputString();

  /**
   * Sets the display for buy stock panel on the GUI.
   *
   * @param s is string to be displayed on the view
   */
  void setBuyStockDisplay(String s);

  /**
   * Gets the input fields of buy stock panel on the GUI.
   *
   * @return List of strings containing the stock details entered in the text fields.
   */
  List<String> getBuyStockInputString();

  /**
   * Gets the date for buying stock in a portfolio as entered by the user on the input
   * field on GUI.
   *
   * @return date for purchasing the stock as entered by user
   */
  Date getBuyDate();

  /**
   * Clears the input fields for buy stock panel on the GUI.
   */
  void clearBuyStockInputString();

  /**
   * Sets the display for determine cost basis and value panel on the GUI.
   *
   * @param s is string to be displayed on the view
   */
  void setDetermineCostBasisDisplay(String s);

  /**
   * Gets the input fields of determine cost basis and value panel on the GUI.
   *
   * @return string containing the portfolio details entered in the text fields.
   */
  String getDetermineCostBasisPortfolio();

  /**
   * Gets the date for which the cost basis and value needs to be determined from the
   * input fields on the GUI.
   *
   * @return date for determining the cost basis and value as entered by user
   */
  Date getDetermineDate();

  /**
   * Clears the input fields for determine cost basis and value panel on the GUI.
   */
  void clearDetermineCostBasisInputString();

  /**
   * Sets the display for view portfolio panel on the GUI.
   *
   * @param s is string to be displayed on the view
   */
  void setViewDisplay(String s);

  /**
   * Gets the input fields of view portfolio panel on the GUI.
   *
   * @return string containing the portfolio details entered in the text fields.
   */
  String getViewInputString();

  /**
   * Clears the input fields for view portfolio panel on the GUI.
   */
  void clearViewInputString();

  /**
   * Sets the display for creating high level strategy panel on the GUI.
   *
   * @param s is string to be displayed on the view
   */
  void setCreateStrategyDisplay(String s);

  /**
   * Gets the input fields of creating high level strategy panel on the GUI.
   *
   * @return List of strings containing the portfolio details entered in the text fields.
   */
  List<String> getCreateStrategyInputString();

  /**
   * Gets the start date for the recurring investment in a portfolio as entered by the
   * user on the input field on GUI.
   *
   * @return start date for investing in the portfolio as entered by user
   */
  Date getStartDate();

  /**
   * Gets the end date for the recurring investment in a portfolio as entered by the
   * user on the input field on GUI.
   *
   * @return end date to stop investing in the portfolio as entered by user
   */
  Date getEndDate();

  /**
   * Clears the input fields for creating high level strategy panel on the GUI.
   */
  void clearCreateStrategyInputString();

  void setSaveFileName();
}
