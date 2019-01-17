import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import freecell.controller.FreecellController;
import freecell.controller.IFreecellController;
import freecell.model.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for {@link FreecellController}. It checks if the Controller class is working fine in
 * isolation with the Mock Model.
 * <p>This test class checks for the correctness of Controller. It checks for each methods that
 * is being called through the controller to a model called "MockModel" See {@link
 * FreecellMockModel} for more details.</p>
 * <p>First, it checks for the validity of the deck that is being done by any {@link
 * freecell.model.FreecellOperations} implementation. If controller is correctly calling out the
 * model that is the implementation of {@link freecell.model.FreecellOperations} and transmitting
 * the log operations set by the model, then controller is working as expected in this test.</p>
 */
public class FreecellControllerTest {
  /**
   * Log for mock model.
   */
  private StringBuilder log;
  /**
   * Some deck randomly generated for mock model.
   */
  private List<String> mockDeck;
  /**
   * GameState is set to started, when game is already started. Model should set the same string.
   */
  private String gameState;
  /**
   * Invalid deck object for mock model.
   */
  private String invalidDeckObject;
  /**
   * Invalid card index set to a specific value for mock model.
   */
  private int invalidCardIndex;
  /**
   * Game over index set to a specific value for mock model.
   */
  private int makeGameOverIndex;
  /**
   * Error message for mock model.
   */
  private String errorMessage;
  /**
   * Some deck randomly generated for controller input.
   */
  private List<String> controllerDeck;
  /**
   * Boolean value for shuffle is generated randomly for controller.
   */
  private boolean cShuffle;
  /**
   * Object for Random input.
   */
  private Random r;
  /**
   * Mock model for testing.
   */
  private FreecellMockModel mock;
  /**
   * Instance of controller.
   */
  private IFreecellController controller;
  /**
   * Output buffer from controller.
   */
  private StringBuffer out;

  /**
   * Input buffer from controller.
   */
  private Reader in;

  private String cDeck1;
  private String cDeck2;
  private String cDeck3;
  private String cDeck4;

  /**
   * Initializes value for testing controller.
   */
  @Before
  public void setup() {
    log = new StringBuilder();

    mockDeck = new ArrayList<>();
    r = new Random();
    String mDeck1 = r.nextInt() + "";
    String mDeck2 = r.nextInt() + "";
    String mDeck3 = r.nextInt() + "";
    String mDeck4 = r.nextInt() + "";
    mockDeck.add(mDeck1);
    mockDeck.add(mDeck2);
    mockDeck.add(mDeck3);
    mockDeck.add(mDeck4);

    invalidDeckObject = r.nextInt() + " " + r.nextInt();
    boolean gameOver = false;
    gameState = "Started";
    invalidCardIndex = 999;
    makeGameOverIndex = 1111;
    errorMessage = r.nextInt() + " ";

    controllerDeck = new ArrayList<>();
    cDeck1 = r.nextInt() + "";
    cDeck2 = r.nextInt() + "";
    cDeck3 = r.nextInt() + "";
    cDeck4 = r.nextInt() + "";
    controllerDeck.add(cDeck1);
    controllerDeck.add(cDeck2);
    controllerDeck.add(cDeck3);
    controllerDeck.add(cDeck4);
    cShuffle = r.nextBoolean();

    mock = new FreecellMockModel(log, mockDeck, gameOver, gameState,
            invalidDeckObject, invalidCardIndex, makeGameOverIndex, errorMessage);

    out = new StringBuffer();
  }

  /**
   * Invalid deck to mock model. The message returned by mock model will be asserted here.
   */
  @Test
  public void testInvalidDeck() {
    try {
      mockDeck.add(invalidCardIndex + "");
      mock.startGame(mockDeck, false);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals(errorMessage, iae.getMessage());
    }
  }

  /**
   * Invalid card object to mock model. The message returned by mock model will be asserted here.
   */
  @Test
  public void testInvalidCard() {
    try {
      mockDeck.add(invalidDeckObject + "");
      mock.startGame(mockDeck, false);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid deck.", iae.getMessage());
    }
  }

  /**
   * Invalid card index to move method of mock model. Model will throw exception and message will be
   * asserted after catch.
   */
  @Test
  public void testInvalidCardIndex() {
    try {
      mockDeck.add(invalidCardIndex + "");
      mock.move(PileType.CASCADE, r.nextInt(), invalidCardIndex, PileType.CASCADE, r.nextInt());
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals(errorMessage, iae.getMessage());
    }
  }

  /**
   * Game over input given to mock model. This index will indicate model to end the game.
   */
  @Test
  public void testGameOverInput1() {
    mockDeck.add(invalidCardIndex + "");
    mock.startGame(mockDeck, false);
    mock.move(PileType.CASCADE, r.nextInt(), makeGameOverIndex, PileType.CASCADE, r.nextInt());
    assertEquals(true, mock.isGameOver());
  }

  /**
   * Game over input given to mock model. This index will indicate model to end the game.
   */
  @Test
  public void testGameOverInput2() {
    mock.startGame(mockDeck, false);
    mock.move(PileType.CASCADE, r.nextInt(), 2, PileType.CASCADE, r.nextInt());
    assertEquals(false, mock.isGameOver());
  }


  /**
   * Checks if invalid moves are validated from model.
   */
  @Test
  public void testInvalidMovesFromModel() {
    in = new StringReader("C4 -8 F1\nq");
    controller = new FreecellController(in, out);
    controller.playGame(controllerDeck, mock, false);
    assertEquals("Start game with deck:\n"
            + cDeck1 + "\n"
            + cDeck2 + "\n"
            + cDeck3 + "\n"
            + cDeck4 + "\n"
            + "Shuffle: " + false + "\n", log.toString());
    String expected = gameState + "\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Checks if invalid moves are validated from model.
   */
  @Test
  public void testValidMovesFromModel() {
    in = new StringReader("C4 7 F1 q\n");
    controller = new FreecellController(in, out);
    controller.playGame(controllerDeck, mock, cShuffle);
    assertEquals("Start game with deck:\n"
            + cDeck1 + "\n"
            + cDeck2 + "\n"
            + cDeck3 + "\n"
            + cDeck4 + "\n"
            + "Shuffle: " + cShuffle + "\n"
            + "Move for: CASCADE 3 6 FOUNDATION 0\n", log.toString());
    String expected = gameState + "\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + gameState + "\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

}
