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
 * Test class for investment controller.
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
    in = new StringReader("2 c College GOOG 1000 11-09-2018 11:00 # q");
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
            + "You are in Stock buying menu. At any time you want to return to the main"
            + " menu, press #\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "Please enter Company symbol to buy shares (GOOG, FB, MSFT, SBUX, AAPL):\n"
            + "Please enter amount in USD to invest in shares\n"
            + "Please provide date of investment in (MM-dd-YYYY HH:mm 24-Hrs format)\n"
            + "Please wait while we buy shares for you at the lowest price..\n"
            + "Portfolio Not found\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Empty portfolio and viewing value(option 1).
   */
  @Test
  public void testEmptyPortfolioViewStock() {
    out = new StringBuffer();
    in = new StringReader("4 College # q");
    IInvestmentController controller = new InvestmentController(in, out);
    controller.start(model);
    String expected = "Welcome to Invest for Dummies Application!\n"
            + "Following are the options:\n"
            + "Enter 1 to Create Portfolio\n"
            + "Enter 2 to Buy Stock for a Portfolio at a given date\n"
            + "Enter 3 to Find cost and value of a portfolio for a given date\n"
            + "Enter 4 to View Portfolio Composition\n"
            + "Enter 'q' or 'quit' to exit\n"
            + "Enter Portfolio name to view or press # to return to the menu.\n"
            + "Portfolio not found\n"
            + "Enter Portfolio name to view or press # to return to the menu.\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Portfolio name already present while creating a new portfolio.
   */
  @Test
  public void testCreateExistingPortfolio() {
    out = new StringBuffer();
    in = new StringReader("1 College # 1 College # q");
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
            + "You are in Menu.\n"
            + "Enter Portfolio name to create or press # to return to the menu.\n"
            + "Portfolio with this name is already present\n"
            + "Enter Portfolio name to create or press # to return to the menu.\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Portfolio created successfully.
   */
  @Test
  public void testCreatePortfolioSuccessful() {
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
   * Buy stock, take invalid portfolio name.
   */
  @Test
  public void testBuyStockInvalidPortfolio() {
    out = new StringBuffer();
    in = new StringReader("1 College # 2 c Retirement GOOG 1000 11-09-2018 11:00 # q");
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
            + "You are in Menu.\n"
            + "Enter 'l' to list existing portfolio for which you want to buy stock or "
            + "'c' to continue if you know portfolio name\n"
            + "You are in Stock buying menu. At any time you want to return to the main"
            + " menu, press #\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "Please enter Company symbol to buy shares (GOOG, FB, MSFT, SBUX, AAPL):\n"
            + "Please enter amount in USD to invest in shares\n"
            + "Please provide date of investment in (MM-dd-YYYY HH:mm 24-Hrs format)\n"
            + "Please wait while we buy shares for you at the lowest price..\n"
            + "Portfolio Not found\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Buy stock, take invalid amount.
   */
  @Test
  public void testBuyStockInvalidAmount() {
    out = new StringBuffer();
    in = new StringReader("1 College # 2 c College GOOG -1000 11-09-2018 11:00 # q");
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
            + "You are in Menu.\n"
            + "Enter 'l' to list existing portfolio for which you want to buy stock or "
            + "'c' to continue if you know portfolio name\n"
            + "You are in Stock buying menu. At any time you want to return to the main"
            + " menu, press #\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "Please enter Company symbol to buy shares (GOOG, FB, MSFT, SBUX, AAPL):\n"
            + "Please enter amount in USD to invest in shares\n"
            + "Please provide date of investment in (MM-dd-YYYY HH:mm 24-Hrs format)\n"
            + "Please wait while we buy shares for you at the lowest price..\n"
            + "Please enter a valid amount.\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Buy stock, take invalid company symbol.
   */
  @Test
  public void testBuyStockInvalidCompanySymbol() {
    out = new StringBuffer();
    in = new StringReader("1 College # 2 c College TCS 1000 11-09-2018 11:00 # q");
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
            + "You are in Menu.\n"
            + "Enter 'l' to list existing portfolio for which you want to buy stock or "
            + "'c' to continue if you know portfolio name\n"
            + "You are in Stock buying menu. At any time you want to return to the main"
            + " menu, press #\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "Please enter Company symbol to buy shares (GOOG, FB, MSFT, SBUX, AAPL):\n"
            + "Please enter amount in USD to invest in shares\n"
            + "Please provide date of investment in (MM-dd-YYYY HH:mm 24-Hrs format)\n"
            + "Please wait while we buy shares for you at the lowest price..\n"
            + "Stock data not found for this symbol\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Buy stock, take invalid date format.
   */
  @Test
  public void testBuyStockInvalidDateFormat() {
    out = new StringBuffer();
    in = new StringReader("1 College # 2 c College GOOG 1000 2018-11-09 11:00 # q");
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
            + "You are in Menu.\n"
            + "Enter 'l' to list existing portfolio for which you want to buy stock or "
            + "'c' to continue if you know portfolio name\n"
            + "You are in Stock buying menu. At any time you want to return to the main"
            + " menu, press #\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "Please enter Company symbol to buy shares (GOOG, FB, MSFT, SBUX, AAPL):\n"
            + "Please enter amount in USD to invest in shares\n"
            + "Please provide date of investment in (MM-dd-YYYY HH:mm 24-Hrs format)\n"
            + "Please wait while we buy shares for you at the lowest price..\n"
            + "Stock data not found for this date\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Buy stock, date not in database.
   */
  @Test
  public void testBuyStockWeekendDate() {
    out = new StringBuffer();
    in = new StringReader("1 College # 2 c College GOOG 1000 11-10-2018 11:00 # q");
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
            + "You are in Menu.\n"
            + "Enter 'l' to list existing portfolio for which you want to buy stock or "
            + "'c' to continue if you know portfolio name\n"
            + "You are in Stock buying menu. At any time you want to return to the main"
            + " menu, press #\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "Please enter Company symbol to buy shares (GOOG, FB, MSFT, SBUX, AAPL):\n"
            + "Please enter amount in USD to invest in shares\n"
            + "Please provide date of investment in (MM-dd-YYYY HH:mm 24-Hrs format)\n"
            + "Please wait while we buy shares for you at the lowest price..\n"
            + "Trading is closed for this time. Please try again later\n"
            + "Enter name of the portfolio to buy shares:\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }


  /**
   * View cost and value for portfolio not present or invalid.
   */
  @Test
  public void testCostValueForPortfolioNotPresent() {
    out = new StringBuffer();
    in = new StringReader("1 College # 3 Retirement # q");
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
            + "You are in Menu.\n"
            + "Enter 'l' to list existing portfolio for which you want to find cost and"
            + " value or 'c' to continue if you know portfolio name or # to go back\n"
            + "Incorrect option selected\n"
            + "Press # to go back or press 'c' to check value for other portfolio\n"
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit after all operations.
   */
  @Test
  public void testQuitAll() {
    out = new StringBuffer();
    in = new StringReader("Quit");
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
   * Quit only at menu.
   */
  @Test
  public void testQuitMenu() {
    out = new StringBuffer();
    in = new StringReader("1 # Quit");
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
            + "You are in Menu.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit inside all options.
   */
  @Test
  public void testQuitInsideOptions() {
    out = new StringBuffer();
    in = new StringReader("2 # q");
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

}