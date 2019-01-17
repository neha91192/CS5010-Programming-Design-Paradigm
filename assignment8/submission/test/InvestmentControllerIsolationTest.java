import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import howtoinvestfordummies.controller.IInvestmentController;
import howtoinvestfordummies.controller.InvestmentController;
import howtoinvestfordummies.model.InvestmentStrategies;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link InvestmentController}. It checks if the Controller class is
 * working fine in isolation with the Mock Model.
 * <p>This test class checks for the correctness of Controller. It checks for each methods that
 * is being called through the controller to a model called "MockModel" See {@link
 * MockModel} for more details.</p>
 * <p>First, it checks for the validity of the deck that is being done by any {@link
 * InvestmentStrategies} implementation. If controller is correctly calling out the
 * model that is the implementation of {@link InvestmentStrategies} and transmitting
 * the log operations set by the model, then controller is working as expected in this test.</p>
 */
public class InvestmentControllerIsolationTest {
  /**
   * Log for mock model.
   */
  private StringBuilder log;
  /**
   * AppState is set to started, when app is already started. Model should set the same
   * string.
   */
  private String appState;
  /**
   * Error message for mock model.
   */
  private String errorMessage;
  /**
   * Mock model for testing.
   */
  private MockModel mock;
  /**
   * Instance of controller.
   */
  private IInvestmentController controller;
  /**
   * Output buffer from controller.
   */
  private StringBuffer out;
  private Date invalidDate;
  private Date validDate;
  private String portfolioName;
  private String stockSymbol;
  private SimpleDateFormat sdf1;

  /**
   * Initializes value for testing controller.
   */
  @Before
  public void setup() {
    log = new StringBuilder();
    sdf1 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    String invalidDateStr = "11-09-2018 08:30:00";
    invalidDate = new Date();
    String validDateStr = "11-09-2018 11:30:00";
    validDate = new Date();
    try {
      invalidDate = sdf1.parse(invalidDateStr);
      validDate = sdf1.parse(validDateStr);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    portfolioName = "College";
    stockSymbol = "GOOG";

    mock = new MockModel(invalidDate, portfolioName, stockSymbol);
    out = new StringBuffer();
  }

  /**
   * Invalid date to mock model in buy shares. The message returned by mock model will be
   * asserted here.
   */
  @Test
  public void testInvalidDate() {
    try {
      mock.buySharesOfStock(invalidDate, 1050.01, stockSymbol, portfolioName);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Trading is closed for this time. Please try again later", iae.getMessage());
    }
  }

  /**
   * Portfolio not present. The message returned by mock model will be asserted here.
   */
  @Test
  public void testInvalidPorfolio() {
    try {
      mock.buySharesOfStock(validDate, 1050.01, stockSymbol, portfolioName);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Portfolio Not found", iae.getMessage());
    }
  }

  /**
   * Create portfolio. The message returned by mock model will be asserted here.
   */
  @Test
  public void testCreatePorfolio() {
    try {
      mock.buySharesOfStock(validDate, 1050.01, stockSymbol, portfolioName);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Portfolio Not found", iae.getMessage());
    }
  }
}
