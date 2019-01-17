import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import freecell.model.Card;
import freecell.model.CardImpl;
import freecell.model.CardSuit;
import freecell.model.CardValue;
import freecell.model.FreecellModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractFreecellModelTest {

  /**
   * Abstract method which returns new instance of {@link FreecellModel} object when called. This
   * method has to be implemented by {@link FreecellModelTest} so that abstraction can be achieved
   * while testing on the model object for all the cases under Freecell model.
   */
  protected abstract FreecellOperations<Card> freecellModel(int cascadeSize, int openSize);

  /**
   * Abstract method which returns new instance of {@link FreecellOperations} object when called.
   * This method has to be implemented by {@link freecell.model.FreecellMultiMoveModel} so that
   * abstraction can be achieved while testing on the model object for all the cases under Freecell
   * multimove model.
   */
  protected abstract FreecellOperations<Card> freecellMultimoveModel(int cascadeSize, int openSize);

  FreecellOperations<Card> game1;
  FreecellOperations<Card> game2;
  FreecellOperations<Card> game3;
  FreecellOperations<Card> game4;
  FreecellOperations<Card> game5;
  FreecellOperations<Card> game6;

  private List<String> deck;

  private String initialGameStateWithoutShuffle;

  List<Card> deckToPlay1;
  List<Card> deckToPlay2;


  /**
   * Sets the default freecell object for performing operations using FreecellModel.
   */
  @Before
  public void setup() {
    game1 = freecellModel(4, 1);
    game3 = freecellMultimoveModel(4, 1);
    game2 = freecellModel(5, 1);
    game4 = freecellMultimoveModel(5, 1);
    game5 = freecellMultimoveModel(8, 4);
    game6 = freecellMultimoveModel(10, 4);

    deck = new ArrayList<>();

    List<String> clubs = new ArrayList<>();
    List<String> diamonds = new ArrayList<>();
    List<String> hearts = new ArrayList<>();
    List<String> spades = new ArrayList<>();

    clubs.add("A♣");
    clubs.add("2♣");
    clubs.add("3♣");
    clubs.add("4♣");
    clubs.add("5♣");
    clubs.add("6♣");
    clubs.add("7♣");
    clubs.add("8♣");
    clubs.add("9♣");
    clubs.add("10♣");
    clubs.add("J♣");
    clubs.add("Q♣");
    clubs.add("K♣");

    diamonds.add("A♦");
    diamonds.add("2♦");
    diamonds.add("3♦");
    diamonds.add("4♦");
    diamonds.add("5♦");
    diamonds.add("6♦");
    diamonds.add("7♦");
    diamonds.add("8♦");
    diamonds.add("9♦");
    diamonds.add("10♦");
    diamonds.add("J♦");
    diamonds.add("Q♦");
    diamonds.add("K♦");

    hearts.add("A♥");
    hearts.add("2♥");
    hearts.add("3♥");
    hearts.add("4♥");
    hearts.add("5♥");
    hearts.add("6♥");
    hearts.add("7♥");
    hearts.add("8♥");
    hearts.add("9♥");
    hearts.add("10♥");
    hearts.add("J♥");
    hearts.add("Q♥");
    hearts.add("K♥");

    spades.add("A♠");
    spades.add("2♠");
    spades.add("3♠");
    spades.add("4♠");
    spades.add("5♠");
    spades.add("6♠");
    spades.add("7♠");
    spades.add("8♠");
    spades.add("9♠");
    spades.add("10♠");
    spades.add("J♠");
    spades.add("Q♠");
    spades.add("K♠");

    deck.addAll(clubs);
    deck.addAll(diamonds);
    deck.addAll(hearts);
    deck.addAll(spades);

    initialGameStateWithoutShuffle = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: A♣, 5♣, 9♣, K♣, 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠\n"
            + "C2: 2♣, 6♣, 10♣, A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠\n"
            + "C3: 3♣, 7♣, J♣, 2♦, 6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠\n"
            + "C4: 4♣, 8♣, Q♣, 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠";

    String cards1 = "9♠, 8♣, K♠, A♦, "
            + "4♣, 2♣, K♣, 3♣, "
            + "7♠, 9♥, 10♦, A♠, "
            + "A♣, K♥, 8♠, 7♥, "
            + "K♦, J♠, 8♥, J♣, "
            + "3♥, Q♠, 2♠, 2♥, "
            + "7♣, 10♥, J♦, Q♥, "
            + "3♠, 2♦, 7♦, 6♥, "
            + "9♦, Q♣, Q♦, 4♦, "
            + "4♠, 5♣, 5♥, 5♦, "
            + "3♦, 6♠, 8♦, 4♥, "
            + "6♣, 10♠, 10♣, 9♣, "
            + "6♦, 5♠, J♥, A♥";

    String cards2 = "9♠, 8♣, K♠, A♦, "
            + "4♣, 2♣, K♣, 3♣, "
            + "7♠, 9♥, 10♦, A♠, "
            + "A♣, K♥, 8♠, 7♥, "
            + "K♦, J♠, 8♥, J♣, "
            + "10♣, Q♠, 2♠, 6♦, "
            + "7♣, 10♥, J♦, Q♥, "
            + "3♠, 2♦, 3♦, 6♥, "
            + "9♦, Q♣, Q♦, 4♦, "
            + "4♠, 5♣, 5♥, 5♦, "
            + "7♦, 6♠, 8♦, J♥, "
            + "2♥, 10♠, 3♥, 9♣, "
            + "6♣, 5♠, 4♥, A♥";

    deckToPlay1 = createValidDeckToPlay(cards1);
    deckToPlay2 = createValidDeckToPlay(cards2);
  }

  private List<Card> createInvalidDeck(String type) {
    List<Card> cards = new ArrayList<>();

    //creates invalid deck of incomplete type
    if (type.equals("incomplete")) {
      Card card;
      for (int i = 0; i < 51; i++) {
        for (CardSuit suit : CardSuit.values()) {
          for (CardValue value : CardValue.values()) {
            card = new CardImpl(suit, value);
            cards.add(card);
          }
        }
      }
    }

    Card card = new CardImpl(CardSuit.CLUBS, CardValue.ACE);
    //creates invalid deck of more than deck size
    if (type.equals("extra")) {
      cards = game1.getDeck();

      cards.add(card);
    }

    //creates invalid deck of incomplete type
    if (type.equals("duplicate")) {
      for (int i = 0; i < 51; i++) {
        for (CardSuit suit : CardSuit.values()) {
          for (CardValue value : CardValue.values()) {
            card = new CardImpl(suit, value);
            cards.add(card);
          }
        }
      }
      cards.add(card);
    }
    return cards;
  }

  List<Card> createValidDeckToPlay(String cards) {
    List<Card> cardsToPlay = new ArrayList<>();
    for (String card : cards.split(", ")) {
      String suit = card.substring(card.length() - 1);
      String value = card.substring(0, card.length() - 1);
      CardSuit suit1 = CardSuit.fromString(suit);
      CardValue value1 = CardValue.fromString(value);
      Card card1 = new CardImpl(suit1, value1);
      cardsToPlay.add(card1);
    }
    return cardsToPlay;
  }


  /**
   * Test for checking valid deck.
   */
  @Test
  public void testValidDeck() {
    List<String> actualDeck = game1.getDeck().stream()
            .map(card -> card.toString()).sorted().collect(Collectors.toList());
    Collections.sort(deck);
    assertEquals(deck, actualDeck);
  }

  /**
   * Test for checking start a started game.
   */
  @Test
  public void testStartAStartedGame() {
    game2.startGame(deckToPlay1, false);
    String expected = game2.getGameState();
    game2.startGame(deckToPlay1, false);
    assertEquals(expected, game2.getGameState());
  }

  /**
   * Test for checking that deck shuffling logic is working.
   */
  @Test
  public void testShuffleDeck() {
    game1.startGame(deckToPlay1, false);
    String expected = game1.getGameState();
    game1.startGame(deckToPlay1, true);
    assertFalse(expected.equals(game1.getGameState()));

    game2.startGame(deckToPlay1, false);
    expected = game2.getGameState();
    game2.startGame(deckToPlay1, true);
    assertFalse(expected.equals(game2.getGameState()));
  }

  /**
   * Test for starting game, playing a move and then starting game again.
   */
  @Test
  public void testStartMoveStartGame() {
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣, A♥";
    game1.startGame(deckToPlay1, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 1);
    game1.startGame(deckToPlay1, false);
    assertEquals(expected, game1.getGameState());
  }

  /**
   * Test for negative size for cascade.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSizeCascade() {
    game3 = FreecellModel.getBuilder().cascades(-2).opens(1).build();
    game3.startGame(deckToPlay1, false);
  }

  /**
   * Test for negative size for open.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSizeOpen() {
    game3 = FreecellModel.getBuilder().cascades(7).opens(-2).build();
    game3.startGame(deckToPlay1, false);
  }

  /**
   * Test for max Int size for cascade.
   */
  public void testIntMaxSizeCascade() {
    game3 = FreecellModel.getBuilder().cascades(Integer.MAX_VALUE).opens(1).build();
    game3.startGame(deckToPlay1, false);
  }

  /**
   * Test for cascade size greater than deck size.
   */
  @Test
  public void testCascadeSizeGreaterThanDeckSize() {
    game3 = FreecellModel.getBuilder().cascades(60).opens(8).build();
    game3.startGame(deckToPlay1, false);
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "O5:\n"
            + "O6:\n"
            + "O7:\n"
            + "O8:\n"
            + "C1: 9♠\n"
            + "C2: 8♣\n"
            + "C3: K♠\n"
            + "C4: A♦\n"
            + "C5: 4♣\n"
            + "C6: 2♣\n"
            + "C7: K♣\n"
            + "C8: 3♣\n"
            + "C9: 7♠\n"
            + "C10: 9♥\n"
            + "C11: 10♦\n"
            + "C12: A♠\n"
            + "C13: A♣\n"
            + "C14: K♥\n"
            + "C15: 8♠\n"
            + "C16: 7♥\n"
            + "C17: K♦\n"
            + "C18: J♠\n"
            + "C19: 8♥\n"
            + "C20: J♣\n"
            + "C21: 3♥\n"
            + "C22: Q♠\n"
            + "C23: 2♠\n"
            + "C24: 2♥\n"
            + "C25: 7♣\n"
            + "C26: 10♥\n"
            + "C27: J♦\n"
            + "C28: Q♥\n"
            + "C29: 3♠\n"
            + "C30: 2♦\n"
            + "C31: 7♦\n"
            + "C32: 6♥\n"
            + "C33: 9♦\n"
            + "C34: Q♣\n"
            + "C35: Q♦\n"
            + "C36: 4♦\n"
            + "C37: 4♠\n"
            + "C38: 5♣\n"
            + "C39: 5♥\n"
            + "C40: 5♦\n"
            + "C41: 3♦\n"
            + "C42: 6♠\n"
            + "C43: 8♦\n"
            + "C44: 4♥\n"
            + "C45: 6♣\n"
            + "C46: 10♠\n"
            + "C47: 10♣\n"
            + "C48: 9♣\n"
            + "C49: 6♦\n"
            + "C50: 5♠\n"
            + "C51: J♥\n"
            + "C52: A♥\n"
            + "C53:\n"
            + "C54:\n"
            + "C55:\n"
            + "C56:\n"
            + "C57:\n"
            + "C58:\n"
            + "C59:\n"
            + "C60:";
    assertEquals(expected, game3.getGameState());
  }

  /**
   * Test for min Int size for cascade.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIntMinSizeCascade() {
    game3 = FreecellModel.getBuilder().cascades(Integer.MIN_VALUE).opens(-2).build();
    game3.startGame(deckToPlay1, false);
  }

  /**
   * Test for max Int size for open.
   */
  public void testIntMaxSizeOpen() {
    game3 = FreecellModel.getBuilder().cascades(6).opens(Integer.MAX_VALUE).build();
    game3.startGame(deckToPlay1, false);
  }

  /**
   * Test for min Int size for open.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIntMinSizeOpen() {
    game3 = FreecellModel.getBuilder().cascades(7).opens(Integer.MIN_VALUE).build();
    game3.startGame(deckToPlay1, false);
  }

  /**
   * Test for checking invalid deck with less than 52 cards, shuffle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidDeckShuffle1() {
    List<Card> invalidDeck = createInvalidDeck("incomplete");
    game1.startGame(invalidDeck, true);
  }

  /**
   * Test for checking invalid deck with less than 52 cards, no shuffle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidDeckNoShuffle1() {
    List<Card> invalidDeck = createInvalidDeck("incomplete");
    game1.startGame(invalidDeck, false);
  }

  /**
   * Test for checking invalid deck in case deck has duplicate cards, shuffle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidDeckShuffle2() {
    List<Card> invalidDeck = createInvalidDeck("duplicate");
    game1.startGame(invalidDeck, true);
  }

  /**
   * Test for checking invalid deck in case deck has duplicate cards, no shuffle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidDeckNoShuffle2() {
    List<Card> invalidDeck = createInvalidDeck("duplicate");
    game1.startGame(invalidDeck, false);
  }

  /**
   * Test for checking invalid deck with more than 52 cards, shuffle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidDeckShuffle3() {
    List<Card> invalidDeck = createInvalidDeck("extra");
    game1.startGame(invalidDeck, true);
  }

  /**
   * Test for checking invalid deck with more than 52 cards, no shuffle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidDeckNoShuffle3() {
    List<Card> invalidDeck = createInvalidDeck("extra");
    game1.startGame(invalidDeck, false);
  }

  /**
   * Test for checking invalid deck for null deck in startGame.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullDeckInStartGame() {
    game1.startGame(null, false);
  }

  /**
   * Test for checking invalid empty deck in startGame.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyDeckInStartGame() {
    game1.startGame(new ArrayList<>(), false);
  }

  /**
   * Test for checking initial game state for shuffle. Game State will keep varying as deck is being
   * shuffled.
   */
  @Test
  public void testStartGameShuffle() {
    game1.startGame(game1.getDeck(), true);
    assertNotEquals(initialGameStateWithoutShuffle, game1.getGameState());
  }

  /**
   * Test for checking if game has correctly started when shuffle is kept false.
   */
  @Test
  public void testStartGameNoShuffle() {
    game1.startGame(game1.getDeck(), false);
    assertEquals(initialGameStateWithoutShuffle, game1.getGameState());
  }

  /**
   * Test for checking game state when it has just started with no moves.
   */
  @Test
  public void testGameStateNoShuffle() {
    game1.startGame(game1.getDeck(), false);
    assertEquals(initialGameStateWithoutShuffle, game1.getGameState());
  }

  /**
   * Test for checking game state when it game has not started.
   */
  @Test
  public void testGameStateWhenGameNotStarted() {
    assertEquals("", game1.getGameState());
  }


  /**
   * Test for checking game state after startGame has thrown an exception.
   */
  @Test
  public void testGameStateWhenStartGameException() {
    try {
      List<Card> invalidDeck = createInvalidDeck("incomplete");
      game1.startGame(invalidDeck, true);
      Assert.fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals("", game1.getGameState());
    }
  }

  /**
   * Test for checking getDeck after game is started with shuffled deck.
   */
  @Test
  public void testGameStateShuffle() {
    List<Card> expectedDeck = deckToPlay1;
    game1.startGame(deckToPlay1, true);
    assertEquals(expectedDeck.size(), game1.getDeck().size());

    List<String> actualDeck = game1.getDeck().stream()
            .map(card -> card.toString()).sorted().collect(Collectors.toList());
    List<String> expectedDeckStr = deckToPlay1.stream()
            .map(card -> card.toString()).sorted().collect(Collectors.toList());
    Collections.sort(expectedDeckStr);
    assertEquals(expectedDeckStr, actualDeck);
  }

  /**
   * Test for invalid move from Foundation pile as source.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceFoundationToFoundation() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.FOUNDATION, 3, 12, PileType.FOUNDATION, 0);
  }

  /**
   * Test for invalid move from Foundation pile as source.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceFoundationToOpen() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.FOUNDATION, 3, 12, PileType.OPEN, 0);
  }

  /**
   * Test for invalid move from Foundation pile as source.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceFoundationToCascade() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.FOUNDATION, 3, 12, PileType.CASCADE, 0);
  }

  /**
   * Test for invalid move from Open pile as source to Cascade pile as destination. Invalid
   * pileNumber.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceOpenToCascade1() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.OPEN, 3, 0, PileType.CASCADE, 2);
  }

  /**
   * Test for invalid move from Open pile as source to Cascade pile as destination. Invalid
   * cardIndex.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceOpenToCascade2() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.OPEN, 0, 12, PileType.CASCADE, 3);
  }

  /**
   * Test for invalid move from Open pile as source to Cascade pile as destination. Invalid
   * destPileNumber.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceOpenToCascade3() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 9);
  }

  /**
   * Test for invalid move from Open pile as source to Foundation pile as destination. Invalid
   * pileNumber.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceOpenToFoundation1() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 2);
  }

  /**
   * Test for invalid move from Open pile as source to Foundation pile as destination. Invalid
   * cardIndex.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceOpenToFoundation2() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.OPEN, 0, 12, PileType.FOUNDATION, 3);
  }

  /**
   * Test for invalid move from Open pile as source to Foundation pile as destination. Invalid
   * destPileNumber.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceOpenToFoundation3() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 5);
  }

  /**
   * Test for invalid move from Cascade pile as source to Open pile as destination. Invalid
   * pileNumber.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceCascadeToOpen1() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.CASCADE, 5, 12, PileType.OPEN, 0);
  }

  /**
   * Test for invalid move from Cascade pile as source to Open pile as destination. Invalid
   * cardIndex.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceCascadeToOpen2() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.CASCADE, 2, 14, PileType.OPEN, 0);
  }

  /**
   * Test for invalid move from Cascade pile as source to Open pile as destination. Invalid
   * destPileNumber.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceCascadeToOpen3() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.CASCADE, 2, 12, PileType.OPEN, 1);
  }

  /**
   * Test for invalid move, pileNumber negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceCascadeToFoundation1() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.CASCADE, -1, 12, PileType.FOUNDATION, 0);
  }

  /**
   * Test for invalid move, cardIndex negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceCascadeToFoundation2() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.CASCADE, 2, -3, PileType.FOUNDATION, 0);
  }

  /**
   * Test for invalid move, destPileNumber negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceCascadeToFoundation3() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.CASCADE, 2, 12, PileType.FOUNDATION, -2);
  }

  /**
   * Test for invalid move from Cascade pile as source to Foundation pile as destination. Invalid
   * destPileNumber.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSourceCascadeToFoundation4() {
    game1.startGame(game1.getDeck(), true);
    game1.move(PileType.CASCADE, 2, 12, PileType.FOUNDATION, 5);
  }

  /**
   * When foundation is empty, source is cascade and card is A.
   */
  @Test
  public void testValidMoveCascadeToFoundationEmpty() {
    String expected =
            "F1:\n"
                    + "F2: A♥\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
                    + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
                    + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
                    + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣";
    game1.startGame(deckToPlay1, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 1);
    assertEquals(expected, game1.getGameState());


    /**
     * Valid move from cascade to cascade.
     */
    expected = "F1:\n"
            + "F2: A♥\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦, 5♠\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣";
    game1.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 0);
    assertEquals(expected, game1.getGameState());

    /**
     * Valid move from cascade to open.
     */
    expected = "F1:\n"
            + "F2: A♥\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 9♣\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦, 5♠\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥";
    game1.move(PileType.CASCADE, 3, 11, PileType.OPEN, 0);
    assertEquals(expected, game1.getGameState());
  }

  /**
   * Valid move from cascade to foundation. Valid move from cascade to open.
   */
  @Test
  public void testValidMove2() {
    String expected = "F1: A♥, 2♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 6♣\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 10♣, 7♣, 3♠, 9♦, 4♠, 7♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 3♦, Q♦, 5♥, 8♦, 3♥, 4♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 6♦, Q♥, 6♥, 4♦, 5♦, J♥, 9♣";
    game1.startGame(deckToPlay2, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
    game1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    game1.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    assertEquals(expected, game1.getGameState());

    /**
     * Valid move from open to cascade.
     */
    expected = "F1: A♥, 2♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 10♣, 7♣, 3♠, 9♦, 4♠, 7♦, 6♣\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 3♦, Q♦, 5♥, 8♦, 3♥, 4♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 6♦, Q♥, 6♥, 4♦, 5♦, J♥, 9♣";
    game1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
    assertEquals(expected, game1.getGameState());

    /**
     * Valid move from open to foundation.
     */
    expected = "F1: A♥, 2♥, 3♥, 4♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 10♣, 7♣, 3♠, 9♦, 4♠, 7♦, 6♣\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 3♦, Q♦, 5♥, 8♦\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 6♦, Q♥, 6♥, 4♦, 5♦, J♥, 9♣";
    game1.move(PileType.CASCADE, 2, 12, PileType.OPEN, 0);
    game1.move(PileType.CASCADE, 2, 11, PileType.FOUNDATION, 0);
    game1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    assertEquals(expected, game1.getGameState());
  }

  /**
   * When foundation is empty, source is cascade and card is not an Ace.
   */
  @Test
  public void testValidMoveCascadeToFoundationInvalidEmpty() {
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣, A♥";
    game1.startGame(deckToPlay1, false);
    try {
      game1.move(PileType.CASCADE, 2, 12, PileType.FOUNDATION, 1);
      Assert.fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved from cascade to open which is already having a card. Invalid move.
   */
  @Test
  public void testValidMoveCascadeToOpenInvalid() {
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♥\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣";
    game1.startGame(deckToPlay1, false);
    try {
      game1.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
      game1.move(PileType.CASCADE, 2, 12, PileType.OPEN, 0);
      Assert.fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved from cascade to open which is already having a card. Invalid move. Ace on
   * Nine.
   */
  @Test
  public void testValidMoveOpenToCascadeInvalid1() {
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♥\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣";
    game1.startGame(deckToPlay1, false);
    try {
      game1.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
      game1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);
      Assert.fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved from cascade to open which is already having a card. Invalid move. 5 of same
   * color on 6 of same color.
   */
  @Test
  public void testValidMoveOpenToCascadeInvalid2() {
    String expected = "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 5♠\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 10♣, 7♣, 3♠, 9♦, 4♠, 7♦, 2♥, 6♣\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 3♦, Q♦, 5♥, 8♦, 3♥, 4♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 6♦, Q♥, 6♥, 4♦, 5♦, J♥, 9♣";
    game1.startGame(deckToPlay2, false);
    try {
      game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
      game1.move(PileType.CASCADE, 1, 12, PileType.OPEN, 0);
      game1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
      Assert.fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved from open to same open pile which is already having a card. Valid move. Same
   * pile Ace on same pile Ace.
   */
  @Test
  public void testValidMoveOpenToOpenValid() {
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♥\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣";
    game1.startGame(deckToPlay1, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    game1.move(PileType.OPEN, 0, 0, PileType.OPEN, 0);
    assertEquals(expected, game1.getGameState());
  }

  /**
   * When card is moved from cascade to same cascade pile. Valid move. Same pile Joker on same pile
   * Joker.
   */
  @Test
  public void testValidMoveCascadeToSameCascadeValid() {
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♥\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣";
    game1.startGame(deckToPlay1, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    game1.move(PileType.CASCADE, 3, 11, PileType.CASCADE, 3);
    assertEquals(expected, game1.getGameState());
  }

  /**
   * When card is moved from Foundation to same Foundation pile which is already having a card.
   * Valid move. Same pile Ace on same pile Ace.
   */
  @Test
  public void testValidMoveFoundationToFoundationValid() {
    String expected = "F1:\n"
            + "F2:\n"
            + "F3: A♥\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣";
    game1.startGame(deckToPlay1, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    game1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 2);
    game1.move(PileType.FOUNDATION, 2, 0, PileType.FOUNDATION, 2);
    assertEquals(expected, game1.getGameState());
  }

  /**
   * When card is moved from open to foundation. Invalid move. Nine on Ace.
   */
  @Test
  public void testValidMoveOpenToFoundationInvalid1() {
    String expected = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4: A♥\n"
            + "O1: 9♣\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥";
    game1.startGame(deckToPlay1, false);
    try {
      game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 3);
      game1.move(PileType.CASCADE, 3, 11, PileType.OPEN, 0);
      game1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 3);
      Assert.fail("Error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved from open to foundation. Invalid move. Five of different suit on Four of
   * some suit.
   */
  @Test
  public void testValidMoveOpenToFoundationInvalid2() {
    String expected = "F1: A♥, 2♥, 3♥, 4♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 5♠\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 10♣, 7♣, 3♠, 9♦, 4♠, 7♦, 6♣\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 3♦, Q♦, 5♥, 8♦\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 6♦, Q♥, 6♥, 4♦, 5♦, J♥, 9♣";
    game1.startGame(deckToPlay2, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
    game1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    game1.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    game1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
    game1.move(PileType.CASCADE, 2, 12, PileType.OPEN, 0);
    game1.move(PileType.CASCADE, 2, 11, PileType.FOUNDATION, 0);
    game1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    game1.move(PileType.CASCADE, 1, 12, PileType.OPEN, 0);
    try {
      game1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
      fail("error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved from cascade to foundation. Invalid move. five of different suit on four of
   * some suit.
   */
  @Test
  public void testValidMoveCascadeToFoundationInvalid1() {
    String expected = "F1: A♥, 2♥, 3♥, 4♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 10♣, 7♣, 3♠, 9♦, 4♠, 7♦, 6♣\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 3♦, Q♦, 5♥, 8♦\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 6♦, Q♥, 6♥, 4♦, 5♦, J♥, 9♣";
    game1.startGame(deckToPlay2, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
    game1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    game1.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    game1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
    game1.move(PileType.CASCADE, 2, 12, PileType.OPEN, 0);
    game1.move(PileType.CASCADE, 2, 11, PileType.FOUNDATION, 0);
    game1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    try {
      game1.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 0);
      fail("error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved from cascade to foundation. Invalid move. Nine on four.
   */
  @Test
  public void testValidMoveCascadeToFoundationInvalid2() {
    String expected = "F1: A♥, 2♥, 3♥, 4♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 10♣, 7♣, 3♠, 9♦, 4♠, 7♦, 6♣\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 3♦, Q♦, 5♥, 8♦\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 6♦, Q♥, 6♥, 4♦, 5♦, J♥, 9♣";
    game1.startGame(deckToPlay2, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
    game1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    game1.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    game1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
    game1.move(PileType.CASCADE, 2, 12, PileType.OPEN, 0);
    game1.move(PileType.CASCADE, 2, 11, PileType.FOUNDATION, 0);
    game1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    try {
      game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
      fail("error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved from cascade to cascade. Invalid move. 9 on J.
   */
  @Test
  public void testValidMoveCascadeToCascadeInvalid1() {
    String expected = "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣, J♥\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣";
    game1.startGame(deckToPlay1, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
    try {
      game1.move(PileType.CASCADE, 3, 12, PileType.CASCADE, 2);
      fail("error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved from cascade to cascade. Invalid move. 9 on 10 same suit.
   */
  @Test
  public void testValidMoveCascadeToCascadeInvalid2() {
    String expected = "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♥\n"
            + "C1: 9♠, 4♣, 7♠, A♣, K♦, 3♥, 7♣, 3♠, 9♦, 4♠, 3♦, 6♣, 6♦\n"
            + "C2: 8♣, 2♣, 9♥, K♥, J♠, Q♠, 10♥, 2♦, Q♣, 5♣, 6♠, 10♠, 5♠\n"
            + "C3: K♠, K♣, 10♦, 8♠, 8♥, 2♠, J♦, 7♦, Q♦, 5♥, 8♦, 10♣\n"
            + "C4: A♦, 3♣, A♠, 7♥, J♣, 2♥, Q♥, 6♥, 4♦, 5♦, 4♥, 9♣";
    game1.startGame(deckToPlay1, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
    game1.move(PileType.CASCADE, 2, 12, PileType.OPEN, 0);
    try {
      game1.move(PileType.CASCADE, 3, 12, PileType.CASCADE, 2);
      fail("error");
    } catch (IllegalArgumentException iae) {
      assertEquals(expected, game1.getGameState());
    }
  }

  /**
   * When card is moved before starting the game. IllegalStateException.
   */
  @Test
  public void testMoveBeforeStartingGame() {
    try {
      game1.move(PileType.CASCADE, 3, 12, PileType.CASCADE, 2);
      fail("error");
    } catch (IllegalStateException ise) {
      assertEquals("", game1.getGameState());
    }
  }

  /**
   * Test for isGameOver when game is not started.
   */
  @Test
  public void testGameNotStartedIsGameOverFalse() {
    assertFalse(game1.isGameOver());
  }

  /**
   * Test for isGameOver when game is not over, but started.
   */
  @Test
  public void testGameStartedIsGameOverFalse() {
    game1.startGame(deckToPlay1, false);
    game1.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
    game1.move(PileType.CASCADE, 2, 12, PileType.OPEN, 0);
    assertFalse(game1.isGameOver());
  }

  /**
   * Test for isGameOver when game is over.
   */
  @Test
  public void testGameOver() {
    game3 = FreecellModel.getBuilder().cascades(52).opens(1).build();
    game3.startGame(deckToPlay1, false);

    game3.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 43, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 31, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 18, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 27, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 13, 0, PileType.FOUNDATION, 0);

    game3.move(PileType.CASCADE, 11, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 22, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 49, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 45, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 17, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 21, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);

    game3.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 35, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 39, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 2);

    game3.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 47, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 46, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 3);

    String expectedGameState = "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
            + "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
            + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
            + "O1:\n"
            + "C1:\n"
            + "C2:\n"
            + "C3:\n"
            + "C4:\n"
            + "C5:\n"
            + "C6:\n"
            + "C7:\n"
            + "C8:\n"
            + "C9:\n"
            + "C10:\n"
            + "C11:\n"
            + "C12:\n"
            + "C13:\n"
            + "C14:\n"
            + "C15:\n"
            + "C16:\n"
            + "C17:\n"
            + "C18:\n"
            + "C19:\n"
            + "C20:\n"
            + "C21:\n"
            + "C22:\n"
            + "C23:\n"
            + "C24:\n"
            + "C25:\n"
            + "C26:\n"
            + "C27:\n"
            + "C28:\n"
            + "C29:\n"
            + "C30:\n"
            + "C31:\n"
            + "C32:\n"
            + "C33:\n"
            + "C34:\n"
            + "C35:\n"
            + "C36:\n"
            + "C37:\n"
            + "C38:\n"
            + "C39:\n"
            + "C40:\n"
            + "C41:\n"
            + "C42:\n"
            + "C43:\n"
            + "C44:\n"
            + "C45:\n"
            + "C46:\n"
            + "C47:\n"
            + "C48:\n"
            + "C49:\n"
            + "C50:\n"
            + "C51:\n"
            + "C52:";

    assertEquals(expectedGameState, game3.getGameState());
    assertTrue(game3.isGameOver());
  }

  /**
   * Test for move after game is over.
   */
  @Test
  public void testMoveAfterGameOver() {
    game3 = FreecellModel.getBuilder().cascades(52).opens(1).build();
    game3.startGame(deckToPlay1, false);

    game3.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 43, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 31, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 18, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 27, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 13, 0, PileType.FOUNDATION, 0);

    game3.move(PileType.CASCADE, 11, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 22, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 49, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 45, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 17, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 21, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);

    game3.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 35, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 39, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 2);

    game3.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 47, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 46, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 3);

    /**
     * Test for move from cascade to foundation after game is over.
     */
    try {
      game3.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 3);
      fail("error");
    } catch (IllegalStateException ise) {
      assertTrue(game3.isGameOver());
    }

    /**
     * Test for move from Foundation to Foundation after game is over.
     */
    try {
      game3.move(PileType.FOUNDATION, 2, 12, PileType.FOUNDATION, 2);
      fail("error");
    } catch (IllegalStateException ise) {
      assertTrue(game3.isGameOver());
    }
  }


  /**
   * Test for startGame after game is over.
   */
  @Test
  public void testMoveFoundationToFoundationAfterGameOver() {
    game3 = FreecellModel.getBuilder().cascades(52).opens(1).build();
    game3.startGame(deckToPlay1, false);

    String expectedGameState = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: 9♠\n"
            + "C2: 8♣\n"
            + "C3: K♠\n"
            + "C4: A♦\n"
            + "C5: 4♣\n"
            + "C6: 2♣\n"
            + "C7: K♣\n"
            + "C8: 3♣\n"
            + "C9: 7♠\n"
            + "C10: 9♥\n"
            + "C11: 10♦\n"
            + "C12: A♠\n"
            + "C13: A♣\n"
            + "C14: K♥\n"
            + "C15: 8♠\n"
            + "C16: 7♥\n"
            + "C17: K♦\n"
            + "C18: J♠\n"
            + "C19: 8♥\n"
            + "C20: J♣\n"
            + "C21: 3♥\n"
            + "C22: Q♠\n"
            + "C23: 2♠\n"
            + "C24: 2♥\n"
            + "C25: 7♣\n"
            + "C26: 10♥\n"
            + "C27: J♦\n"
            + "C28: Q♥\n"
            + "C29: 3♠\n"
            + "C30: 2♦\n"
            + "C31: 7♦\n"
            + "C32: 6♥\n"
            + "C33: 9♦\n"
            + "C34: Q♣\n"
            + "C35: Q♦\n"
            + "C36: 4♦\n"
            + "C37: 4♠\n"
            + "C38: 5♣\n"
            + "C39: 5♥\n"
            + "C40: 5♦\n"
            + "C41: 3♦\n"
            + "C42: 6♠\n"
            + "C43: 8♦\n"
            + "C44: 4♥\n"
            + "C45: 6♣\n"
            + "C46: 10♠\n"
            + "C47: 10♣\n"
            + "C48: 9♣\n"
            + "C49: 6♦\n"
            + "C50: 5♠\n"
            + "C51: J♥\n"
            + "C52: A♥";
    assertEquals(expectedGameState, game3.getGameState());

    game3.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 43, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 31, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 18, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 27, 0, PileType.FOUNDATION, 0);
    game3.move(PileType.CASCADE, 13, 0, PileType.FOUNDATION, 0);

    game3.move(PileType.CASCADE, 11, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 22, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 49, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 45, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 17, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 21, 0, PileType.FOUNDATION, 1);
    game3.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);

    game3.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 35, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 39, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 2);
    game3.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 2);

    game3.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 47, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 46, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 3);
    game3.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 3);

    assertTrue(game3.isGameOver());

    game3.startGame(deckToPlay1, false);
    assertFalse(game3.isGameOver());

    assertEquals(expectedGameState, game3.getGameState());
  }

  /**
   * Test for move for default game.
   */
  @Test
  public void testDefaultGameOver() {
    game3 = FreecellModel.getBuilder().build();

    assertFalse(game3.isGameOver());
    game3.startGame(deckToPlay1, false);
    assertFalse(game3.isGameOver());

    game3.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);

    String expectedGameState = "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 9♦, 3♦, 6♦\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, 6♠, 5♠\n"
            + "C3: K♠, 10♦, 8♥, J♦, Q♦, 8♦, J♥\n"
            + "C4: A♦, A♠, J♣, Q♥, 4♦, 4♥\n"
            + "C5: 4♣, A♣, 3♥, 3♠, 4♠, 6♣\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣, 10♠\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 5♥, 10♣\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣";
    assertEquals(expectedGameState, game3.getGameState());

    game3.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 1);
    expectedGameState = "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 9♦, 3♦, 6♦\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, 6♠, 5♠, 4♥\n"
            + "C3: K♠, 10♦, 8♥, J♦, Q♦, 8♦, J♥\n"
            + "C4: A♦, A♠, J♣, Q♥, 4♦\n"
            + "C5: 4♣, A♣, 3♥, 3♠, 4♠, 6♣\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣, 10♠\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 5♥, 10♣\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣";
    assertEquals(expectedGameState, game3.getGameState());

    try {
      game3.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 0);
      fail("error");
    } catch (IllegalArgumentException ise) {
      assertEquals(expectedGameState, game3.getGameState());
      assertFalse(game3.isGameOver());
    }
  }
}
