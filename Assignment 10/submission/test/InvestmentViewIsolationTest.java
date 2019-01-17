import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import howtoinvestfordummies.controller.IInvestmentController;
import howtoinvestfordummies.controller.InvestmentController;
import howtoinvestfordummies.view.InvestmentTextView;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for {@link InvestmentTextView}. It checks if the View class is working fine in
 * isolation
 * with the Mock View.
 * <p>This test class checks for the correctness of View. It checks for each methods that
 * is being called through the view to a controller called "InvestmentController" See
 * {@link
 * MockView} for more details.</p>
 * <p>First, it checks for the validity of the portfolio that is being done by any {@link
 * howtoinvestfordummies.model.InvestmentStrategies} implementation. If view is
 * correctly passing
 * values to the controller that is the implementation of {@link InvestmentController} and
 * transmitting the log operations set by the controller, then view is working as
 * expected in this
 * test.</p>
 */
public class InvestmentViewIsolationTest {
  /**
   * AppState is set to started, when app is already started. View should set the same
   * string.
   */
  private String appState;

  /**
   * Error message for mock view.
   */
  private String errorMessage;
  /**
   * Mock view for testing.
   */
  private MockView view;
  /**
   * Instance of controller.
   */
  private IInvestmentController controller;
  private Readable in;
  private Appendable out;

  /**
   * Initializes value for testing view.
   */
  @Before
  public void setup() {
    view = new MockView(in, out);
    Scanner scan = new Scanner(in);
  }

  @Test
  public void testTransmitMessage() {
    try {
      view.transmitMessage("Transmit message working");
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Exception encountered in transmitting message", iae.getMessage());
    }
  }

  @Test
  public void testRead() {
    try {
      String input = view.read();
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Exception encountered in read", iae.getMessage());
    }
  }

  @Test
  public void testHasNextInput() {
    try {
      boolean flag = view.hasNextInput();
      assertFalse(flag);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Exception encountered in has next", iae.getMessage());
    }
  }
}
