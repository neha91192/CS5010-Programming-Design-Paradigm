import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import howtoinvestfordummies.controller.IInvestmentController;
import howtoinvestfordummies.controller.InvestmentController;
import howtoinvestfordummies.model.StockInvestmentModel;
import howtoinvestfordummies.model.StockInvestmentStrategies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 */
public class InvestmentControllerTest {
  private IInvestmentController controller;
  private StringBuffer out;
  private Reader in;
  private StockInvestmentStrategies model;

  @Before
  public void setUp() {
    model = new StockInvestmentModel();
  }

  /**
   * Check for readable null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReadableInvalid() {
    out = new StringBuffer();
    controller = new InvestmentController(null, out);
  }

  /**
   * Check for appendable null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAppendableInvalid() {
    in = new StringReader("1");
    controller = new InvestmentController(in, out);
  }

  /**
   * Check for null model.
   */
  @Test
  public void testNullModel() {
    out = new StringBuffer();
    in = new StringReader("1 College");
    IInvestmentController controller = new InvestmentController(in, out);
    try {
      controller.start(null);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Please provide a valid Model object", iae.getMessage());
    }
  }

  /**
   * Invalid null input command.
   */
  @Test
  public void testNullInput() {
    out = new StringBuffer();
    in = new StringReader("null");
    IInvestmentController controller = new InvestmentController(in, out);
    try {
      controller.start(model);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
      assertEquals("Command not found.", iae.getMessage());
    }
  }

  /**
   * Invalid input command.
   */
  @Test
  public void testInvalidInput() {
    out = new StringBuffer();
    in = new StringReader("256");
    IInvestmentController controller = new InvestmentController(in, out);
    try {
      controller.start(model);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
      assertEquals("Command not found.", iae.getMessage());
    }
  }

  /**
   * quit at start.
   */
  @Test
  public void testQuit() {
    out = new StringBuffer();
    in = new StringReader("quit");
    IInvestmentController controller = new InvestmentController(in, out);
    controller.start(model);
    String expected = "Welcome to Invest for Dummies Application!\n"
            + "Following are the options:\n"
            + "Enter 1 to Create Portfolio\n"
            + "Enter 2 to Buy Stock for a Portfolio at a given date\n"
            + "Enter 3 to Find cost and value of a portfolio for a given date\n"
            + "Enter 4 to View Portfolio Composition\n"
            + "Enter 'q' or 'quit' to exit\n";
    assertEquals(expected, out.toString());
  }

  /**
   * quit after creating portfolio.
   */
  @Test
  public void testQuitAfterCreatingPortfolio() {
    out = new StringBuffer();
    in = new StringReader("1 College # q");
    IInvestmentController controller = new InvestmentController(in, out);
    controller.start(model);
    String expected = "Welcome to Invest for Dummies Application!\n"
            + "Following are the options:\n"
            + "Enter 1 to Create Portfolio\n"
            + "Enter 2 to Buy Stock for a Portfolio at a given date\n"
            + "Enter 3 to Find cost and value of a portfolio for a given date\n"
            + "Enter 4 to View Portfolio Composition\n"
            + "Enter 'q' or 'quit' to exit\n"
            + "Enter Portfolio name to create or press # to return to the menu.\n"
            + "Portfolio College created successfully!\n"
            + "Enter Portfolio name to create or press # to return to the menu.\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Empty portfolio and buying stock(option 2).
   */
  @Test
  public void testEmptyPortfolioBuyStock() {
    out = new StringBuffer();
    in = new StringReader("2 c q");
    IInvestmentController controller = new InvestmentController(in, out);
    controller.start(model);
    String expected = "Welcome to Invest for Dummies Application!\n"
            + "Following are the options:\n"
            + "Enter 1 to Create Portfolio\n"
            + "Enter 2 to Buy Stock for a Portfolio at a given date\n"
            + "Enter 3 to Find cost and value of a portfolio for a given date\n"
            + "Enter 4 to View Portfolio Composition\n"
            + "Enter 'q' or 'quit' to exit\n"
            + "Enter 'l' to list existing portfolio for which you want to buy stock or "
            + "'c' to continue if you know portfolio name\n"
            + "Incorrect option selected.\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Empty portfolio and viewing value(option 1).
   */
  @Test
  public void testEmptyPortfolioViewStock() {
    out = new StringBuffer();
    in = new StringReader("2 1 q");
    IInvestmentController controller = new InvestmentController(in, out);
    controller.start(model);
    String expected = "Welcome to Invest for Dummies Application!\n"
            + "Following are the options:\n"
            + "Enter 1 to Create Portfolio\n"
            + "Enter 2 to Buy Stock for a Portfolio at a given date\n"
            + "Enter 3 to Find cost and value of a portfolio for a given date\n"
            + "Enter 4 to View Portfolio Composition\n"
            + "Enter 'q' or 'quit' to exit\n"
            + "Enter 'l' to list existing portfolio for which you want to buy stock or "
            + "'c' to continue if you know portfolio name\n"
            + "Incorrect option selected.\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Portfolio name already present while creating a new portfolio.
   */
  /**
   * Portfolio created successfully.
   */
  /**
   * Buy stock, take invalid portfolio name.
   */
  /**
   * Buy stock, take invalid amount.
   */
  /**
   * Buy stock, take invalid company symbol.
   */
  /**
   * Buy stock, take invalid date format.
   */
  /**
   * Buy stock, date not in database.
   */
  /**
   * Buy stock successfully.
   */
  /**
   * View cost and value for portfolio not present or invalid.
   */
  /**
   * View cost and value for invalid date format.
   */
  /**
   * View cost and value for past date working day.
   */
  /**
   * View cost and value for past date non working day or hours.
   */
  /**
   * View cost and value for past date current date.
   */
  /**
   * View cost and value for past date future date.
   */
  /**
   * View cost and value for past date not in database.
   */
  /**
   * Quit after all operations.
   */
  /**
   * Quit only at menu
   */
  /**
   * Quit inside all options
   */
  /**
   * Back in between option 1.
   */
  /**
   * Back in between option 2.
   */
  /**
   * Back in between option 3.
   */
  /**
   * Back in between option 4.
   *
   */
  /**
   * View when no stocks have been bought
   */
  /**
   * View for invalid portfolio name
   */
  /**
   * View for multiple stocks have been bought
   */
}
