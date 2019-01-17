import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import freecell.controller.FreecellController;
import freecell.controller.IFreecellController;
import freecell.model.Card;
import freecell.model.FreecellMultiMoveModel;
import freecell.model.FreecellOperations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Checks for all the cases that should be handled at Controller level. This includes testing for
 * input validation. Messages transmitted correctly to the view.
 */
public class ViewToFreecellControllerTest {
  private IFreecellController controller;
  private StringBuffer out;
  private Reader in;
  private FreecellOperations model;

  @Before
  public void setUp() {
    model = FreecellMultiMoveModel.getBuilder().build();

  }

  /**
   * Check for readable null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReadableInvalid() {
    out = new StringBuffer();
    controller = new FreecellController(null, out);
  }

  /**
   * Check for appendable null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAppendableInvalid() {
    in = new StringReader("C1 7 F1 q");
    controller = new FreecellController(in, out);
  }

  /**
   * Check for null deck.
   */
  @Test
  public void testNullDeck() {
    out = new StringBuffer();
    in = new StringReader("C1 7 F1 q");
    IFreecellController controller = new FreecellController(in, out);
    try {
      controller.playGame(null, model, false);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid deck or model!", iae.getMessage());
    }
  }

  /**
   * Check for null model.
   */
  @Test
  public void testNullModel() {
    out = new StringBuffer();
    in = new StringReader("C1 7 F1 q");
    IFreecellController controller = new FreecellController(in, out);
    try {
      List<Card> deck = new ArrayList<>();
      controller.playGame(deck, null, false);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid deck or model!", iae.getMessage());
    }
  }

  /**
   * Check for invalid deck.
   */
  @Test
  public void testInvalidDeck() {
    out = new StringBuffer();
    in = new StringReader("C1 7 F1 q");
    IFreecellController controller = new FreecellController(in, out);
    try {
      List<String> deck = new ArrayList<>();
      deck.add("A*");
      deck.add("P@");
      controller.playGame(deck, model, false);
      fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid deck.", iae.getMessage());
    }
  }

  /**
   * Check for shuffle deck true.
   */
  @Test
  public void testShuffleDeck1() {
    out = new StringBuffer();
    in = new StringReader("q");
    IFreecellController controller = new FreecellController(in, out);
    String unexpected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.";
    try {
      List<Card> deck = model.getDeck();
      controller.playGame(deck, model, true);
      assertNotEquals(unexpected, out.toString());
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid deck.", iae.getMessage());
    }
  }

  /**
   * Check for shuffle deck false.
   */
  @Test
  public void testShuffleDeck2() {
    out = new StringBuffer();
    in = new StringReader("q");
    IFreecellController controller = new FreecellController(in, out);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    try {
      List<Card> deck = model.getDeck();
      controller.playGame(deck, model, false);
      assertEquals(expected, out.toString());
    } catch (IllegalArgumentException iae) {
      assertEquals("Invalid deck.", iae.getMessage());
    }
  }

  /**
   * Check for source pile name valid.
   */
  @Test
  public void testSourceNameValid() {
    out = new StringBuffer();
    in = new StringReader("C1 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Check for source pile name invalid.
   */
  @Test
  public void testSourceNameInvalid() {
    out = new StringBuffer();
    in = new StringReader("A1 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Check for source pile number valid.
   */
  @Test
  public void testSourcePileNumberValid() {
    out = new StringBuffer();
    in = new StringReader("C2 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Check for source pile number invalid.
   */
  @Test
  public void testSourcePileNumberInvalid() {
    out = new StringBuffer();
    in = new StringReader("C* q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Check for card index valid.
   */
  @Test
  public void testCardIndexValid() {
    out = new StringBuffer();
    in = new StringReader("C1 2 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Check for card index invalid.
   */
  @Test
  public void testCardIndexInvalid() {
    out = new StringBuffer();
    in = new StringReader("C1 z q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Check for destination pile name valid.
   */
  @Test
  public void testDestinationNameValid() {
    out = new StringBuffer();
    in = new StringReader("C2 7 O1 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♠\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Check for destination pile name invalid.
   */
  @Test
  public void testDestinationNameInvalid() {
    out = new StringBuffer();
    in = new StringReader("C2 7 P1 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Destination Pile Name. Please enter Destination Pile Name again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }


  /**
   * Check for Destination pile number valid.
   */
  @Test
  public void testDestinationPileNumberValid() {
    out = new StringBuffer();
    in = new StringReader("C2 2 O1 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Move. Try Again. Open Pile cannot accommodate these many source cards.\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Check for Destination pile number invalid.
   */
  @Test
  public void testDestinationPileNumberInvalid() {
    out = new StringBuffer();
    in = new StringReader("C2 2 O$ q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Destination Pile Number. Please enter Destination Pile Number again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * If valid source is not resolved, it doesn't set next values for move.
   */
  @Test
  public void testInputsProcessedOneAtaTime1() {
    out = new StringBuffer();
    in = new StringReader("U# w E C # q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * If valid source is resolved, it goes to process valid card index input.
   */
  @Test
  public void testInputsProcessedOneAtaTime2() {
    out = new StringBuffer();
    in = new StringReader("U# w E C # 2 3 O1 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Invalid Move. Try Again. Open Pile cannot accommodate these many source cards.\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * If valid card index is not resolved, it doesn't set next values for move.
   */
  @Test
  public void testInputsProcessedOneAtaTime3() {
    out = new StringBuffer();
    in = new StringReader("C2 i * # q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * If valid card index is not resolved, it doesn't set next values for move.
   */
  @Test
  public void testInputsProcessedOneAtaTime4() {
    out = new StringBuffer();
    in = new StringReader("C2 i * # 7 O1 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♠\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * If valid destination is not resolved, it doesn't call move.
   */
  @Test
  public void testInputsProcessedOneAtaTime5() {
    out = new StringBuffer();
    in = new StringReader("C2 * 1 P* O q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Destination Pile Name. Please enter Destination Pile Name again.\n"
            + "Invalid Destination Pile Number. Please enter Destination Pile Number again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * If valid destination is resolved, it calls move.
   */
  @Test
  public void testInputsProcessedOneAtaTime6() {
    out = new StringBuffer();
    in = new StringReader("C2 * 1 P# O 2 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Destination Pile Name. Please enter Destination Pile Name again.\n"
            + "Invalid Destination Pile Number. Please enter Destination Pile Number again.\n"
            + "Invalid Move. Try Again. Open Pile cannot accommodate these many source cards.\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit conditions for q.
   */
  @Test
  public void testQuitCondition1() {
    out = new StringBuffer();
    in = new StringReader("q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit conditions for Q.
   */
  @Test
  public void testQuitCondition2() {
    out = new StringBuffer();
    in = new StringReader("A2 Q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit conditions for Q.
   */
  @Test
  public void testQuitCondition3() {
    out = new StringBuffer();
    in = new StringReader("A# C Q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit conditions for Q.
   */
  @Test
  public void testQuitCondition4() {
    out = new StringBuffer();
    in = new StringReader("A# C 2 Q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit conditions for Q.
   */
  @Test
  public void testQuitCondition5() {
    out = new StringBuffer();
    in = new StringReader("A# C 2 $ q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit conditions for Q.
   */
  @Test
  public void testQuitCondition6() {
    out = new StringBuffer();
    in = new StringReader("A# C 2 $ 2 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit conditions for Q.
   */
  @Test
  public void testQuitCondition7() {
    out = new StringBuffer();
    in = new StringReader("A# C 2 $ 2 P@ C q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Destination Pile Name. Please enter Destination Pile Name again.\n"
            + "Invalid Destination Pile Number. Please enter Destination Pile Number again.\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit conditions for Q.
   */
  @Test
  public void testQuitCondition8() {
    out = new StringBuffer();
    in = new StringReader("A# C 2 $ 2 P@ C 1 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Destination Pile Name. Please enter Destination Pile Name again.\n"
            + "Invalid Destination Pile Number. Please enter Destination Pile Number again.\n"
            + "Invalid Move. Try Again. intermediate slots are not enough for the move.\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }

  /**
   * Quit conditions for Q.
   */
  @Test
  public void testQuitCondition9() {
    out = new StringBuffer();
    in = new StringReader("A# C 2 $ 2 P@ C 2 q");
    controller = new FreecellController(in, out);
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Invalid Source Pile Name. Please enter Source Pile Name again.\n"
            + "Invalid Source Pile Number. Please enter Source Pile Number again.\n"
            + "Invalid Card Index. Please enter Card Index again.\n"
            + "Invalid Destination Pile Name. Please enter Destination Pile Name again.\n"
            + "Invalid Destination Pile Number. Please enter Destination Pile Number again.\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n"
            + "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n"
            + "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n"
            + "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n"
            + "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n"
            + "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n"
            + "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n"
            + "Please enter Source Pile, Card Index, Destination Pile one on each line\n"
            + "Game quit prematurely.\n";
    assertEquals(expected, out.toString());
  }
}
