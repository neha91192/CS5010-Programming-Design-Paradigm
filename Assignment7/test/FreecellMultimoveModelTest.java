import org.junit.Test;

import freecell.model.Card;
import freecell.model.FreecellMultiMoveModel;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for {@link FreecellMultiMoveModel}. This class tests for special cases of multi-moves
 * supported by {@link FreecellOperations}.
 */
public class FreecellMultimoveModelTest extends AbstractFreecellModelTest {
  /**
   * Returns instance of {@link FreecellMultiMoveModel} when being called.
   *
   * @param cascade size of cascade pile.
   * @param open    size of open pile.
   * @return instance of FreecellMultiMoveModel.
   */
  @Override
  protected FreecellOperations<Card> freecellModel(int cascade, int open) {
    return FreecellMultiMoveModel.getBuilder().cascades(cascade).opens(open).build();
  }

  /**
   * Returns instance of {@link FreecellMultiMoveModel} when being called.
   *
   * @param cascade size of cascade pile.
   * @param open    size of open pile.
   * @return instance of FreecellMultiMoveModel.
   */
  @Override
  protected FreecellOperations<Card> freecellMultimoveModel(int cascade, int open) {
    return FreecellMultiMoveModel.getBuilder().cascades(cascade).opens(open).build();
  }

  /**
   * Test for move for multiple cards. First it performs valid multiple moves and then check for 3
   * different cases where multiple moves should fail. Other cases have been covered in {@link
   * AbstractFreecellModelTest}.
   */
  @Test
  public void testMultipleMoves() {

    assertFalse(game5.isGameOver());
    game5.startGame(deckToPlay1, false);
    assertFalse(game5.isGameOver());

    game5.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    game5.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 0);
    game5.move(PileType.CASCADE, 1, 5, PileType.OPEN, 0);
    game5.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 2);
    game5.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 1);

    String expectedGameState = "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 6♠\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 9♦, 3♦, 6♦, 5♠\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, J♥, 10♠\n"
            + "C3: K♠, 10♦, 8♥, J♦, Q♦, 8♦\n"
            + "C4: A♦, A♠, J♣, Q♥, 4♦, 4♥\n"
            + "C5: 4♣, A♣, 3♥, 3♠, 4♠, 6♣\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 5♥, 10♣\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣";
    assertEquals(expectedGameState, game5.getGameState());

    game5.move(PileType.CASCADE, 0, 7, PileType.OPEN, 1);
    game5.move(PileType.CASCADE, 0, 6, PileType.OPEN, 2);
    game5.move(PileType.CASCADE, 0, 5, PileType.OPEN, 3);
    game5.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 1);

    game5.move(PileType.OPEN, 2, 0, PileType.CASCADE, 0);
    game5.move(PileType.OPEN, 1, 0, PileType.CASCADE, 0);
    game5.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 0);

    expectedGameState = "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 6♠\n"
            + "O2:\n"
            + "O3:\n"
            + "O4: 3♦\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 6♦, 5♠, 4♥\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, J♥, 10♠, 9♦\n"
            + "C3: K♠, 10♦, 8♥, J♦, Q♦, 8♦\n"
            + "C4: A♦, A♠, J♣, Q♥, 4♦\n"
            + "C5: 4♣, A♣, 3♥, 3♠, 4♠, 6♣\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 5♥, 10♣\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣";
    assertEquals(expectedGameState, game5.getGameState());

    // When move is not feasible due to build size -- (N+1)* 2^k
    try {
      game5.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 2);
      fail("error");
    } catch (IllegalArgumentException ise) {
      assertEquals(expectedGameState, game5.getGameState());
      assertFalse(game5.isGameOver());
    }

    // invalid build, but valid source and destination card
    try {
      game5.move(PileType.CASCADE, 4, 3, PileType.CASCADE, 3);
      fail("error");
    } catch (IllegalArgumentException ise) {
      assertEquals(expectedGameState, game5.getGameState());
      assertFalse(game5.isGameOver());
    }

    game5.move(PileType.CASCADE, 2, 5, PileType.CASCADE, 7);
    game5.move(PileType.CASCADE, 4, 5, PileType.OPEN, 1);
    game5.move(PileType.CASCADE, 3, 4, PileType.CASCADE, 5);

    expectedGameState = "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 6♠\n"
            + "O2: 6♣\n"
            + "O3:\n"
            + "O4: 3♦\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 6♦, 5♠, 4♥\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, J♥, 10♠, 9♦\n"
            + "C3: K♠, 10♦, 8♥, J♦, Q♦\n"
            + "C4: A♦, A♠, J♣, Q♥\n"
            + "C5: 4♣, A♣, 3♥, 3♠, 4♠\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣, 4♦\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 5♥, 10♣\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣, 8♦";

    // invalid cardIndex in move from open to cascade
    try {
      game5.move(PileType.OPEN, 3, 1, PileType.CASCADE, 4);
      fail("error");
    } catch (IllegalArgumentException ise) {
      assertEquals(expectedGameState, game5.getGameState());
      assertFalse(game5.isGameOver());
    }

    // valid cardIndex in move from open to cascade. Empties Cascade pile 3.
    game5.move(PileType.OPEN, 3, 0, PileType.CASCADE, 4);

    expectedGameState = "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 6♠\n"
            + "O2: 6♣\n"
            + "O3: Q♥\n"
            + "O4: J♣\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 6♦, 5♠, 4♥\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, J♥, 10♠, 9♦\n"
            + "C3: K♠, 10♦, 8♥, J♦, Q♦\n"
            + "C4: A♦, A♠\n"
            + "C5: 4♣, A♣, 3♥, 3♠, 4♠, 3♦\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣, 4♦\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 5♥, 10♣\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣, 8♦";


    game5.move(PileType.CASCADE, 3, 3, PileType.OPEN, 2);
    game5.move(PileType.CASCADE, 3, 2, PileType.OPEN, 3);
    assertEquals(expectedGameState, game5.getGameState());
    game5.move(PileType.CASCADE, 3, 1, PileType.FOUNDATION, 1);
    game5.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);

    expectedGameState = "F1: A♥\n"
            + "F2: A♠\n"
            + "F3: A♦\n"
            + "F4:\n"
            + "O1: 6♠\n"
            + "O2: 6♣\n"
            + "O3: Q♥\n"
            + "O4: J♣\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 6♦, 5♠, 4♥\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, J♥, 10♠, 9♦\n"
            + "C3: K♠, 10♦, 8♥, J♦, Q♦\n"
            + "C4:\n"
            + "C5: 4♣, A♣, 3♥, 3♠, 4♠, 3♦\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣, 4♦\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 5♥, 10♣\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣, 8♦";

    assertEquals(expectedGameState, game5.getGameState());

    // valid move from open pile to empty cascade pile
    game5.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);

    expectedGameState = "F1: A♥\n"
            + "F2: A♠\n"
            + "F3: A♦\n"
            + "F4:\n"
            + "O1:\n"
            + "O2: 6♣\n"
            + "O3: Q♥\n"
            + "O4: J♣\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 6♦, 5♠, 4♥\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, J♥, 10♠, 9♦\n"
            + "C3: K♠, 10♦, 8♥, J♦, Q♦\n"
            + "C4: 6♠\n"
            + "C5: 4♣, A♣, 3♥, 3♠, 4♠, 3♦\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣, 4♦\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 5♥, 10♣\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣, 8♦";
    assertEquals(expectedGameState, game5.getGameState());

    game5.move(PileType.CASCADE, 3, 0, PileType.OPEN, 0);
    game5.move(PileType.OPEN, 3, 0, PileType.CASCADE, 2);

    // move a valid build of cards from cascade pile to an empty cascade pile
    game5.move(PileType.CASCADE, 2, 4, PileType.CASCADE, 3);

    game5.move(PileType.CASCADE, 6, 5, PileType.CASCADE, 2);
    game5.move(PileType.CASCADE, 6, 4, PileType.OPEN, 3);
    game5.move(PileType.OPEN, 0, 0, PileType.CASCADE, 6);

    expectedGameState = "F1: A♥\n"
            + "F2: A♠\n"
            + "F3: A♦\n"
            + "F4:\n"
            + "O1:\n"
            + "O2: 6♣\n"
            + "O3: Q♥\n"
            + "O4: 5♥\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 6♦, 5♠, 4♥\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, J♥, 10♠, 9♦\n"
            + "C3: K♠, 10♦, 8♥, J♦, 10♣\n"
            + "C4: Q♦, J♣\n"
            + "C5: 4♣, A♣, 3♥, 3♠, 4♠, 3♦\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣, 4♦\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 6♠\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣, 8♦";

    // valid build tried to move on correct valued card but same color card.
    // Will fail as color is same.
    try {
      game5.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 6);
      fail("error");
    } catch (IllegalArgumentException ise) {
      assertEquals(expectedGameState, game5.getGameState());
      assertFalse(game5.isGameOver());
    }

    game5.move(PileType.OPEN, 3, 0, PileType.CASCADE, 6);
    game5.move(PileType.CASCADE, 4, 4, PileType.CASCADE, 6);
    game5.move(PileType.CASCADE, 4, 3, PileType.CASCADE, 5);
    game5.move(PileType.CASCADE, 4, 2, PileType.OPEN, 3);
    game5.move(PileType.CASCADE, 4, 1, PileType.FOUNDATION, 3);
    game5.move(PileType.CASCADE, 4, 0, PileType.OPEN, 0);

    expectedGameState = "F1: A♥\n"
            + "F2: A♠\n"
            + "F3: A♦\n"
            + "F4: A♣\n"
            + "O1: 4♣\n"
            + "O2: 6♣\n"
            + "O3: Q♥\n"
            + "O4: 3♥\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 6♦, 5♠, 4♥\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, J♥, 10♠, 9♦\n"
            + "C3: K♠, 10♦, 8♥, J♦, 10♣\n"
            + "C4: Q♦, J♣\n"
            + "C5:\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣, 4♦, 3♠\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 6♠, 5♥, 4♠, 3♦\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣, 8♦";

    // valid build with 3 cards tried to move on empty cascade pile when no open piles are empty.
    // so (0 + 1) * (2 ^ 1) = 2 and build size is 3. so cannot move and will fail.
    try {
      game5.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 4);
      fail("error");
    } catch (IllegalArgumentException ise) {
      assertEquals(expectedGameState, game5.getGameState());
      assertFalse(game5.isGameOver());
    }

    // trying to move a build of multiple cards to foundation pile
    try {
      game5.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION, 1);
      fail("error");
    } catch (IllegalArgumentException ise) {
      assertEquals(expectedGameState, game5.getGameState());
      assertFalse(game5.isGameOver());
    }

    expectedGameState = "F1: A♥\n"
            + "F2: A♠\n"
            + "F3: A♦\n"
            + "F4: A♣\n"
            + "O1:\n"
            + "O2: 6♣\n"
            + "O3: Q♥\n"
            + "O4:\n"
            + "C1: 9♠, 7♠, K♦, 7♣, 6♦, 5♠, 4♥\n"
            + "C2: 8♣, 9♥, J♠, 10♥, Q♣, J♥, 10♠, 9♦\n"
            + "C3: K♠, 10♦, 8♥, J♦, 10♣\n"
            + "C4: Q♦, J♣\n"
            + "C5: 4♣, 3♥\n"
            + "C6: 2♣, K♥, Q♠, 2♦, 5♣, 4♦, 3♠\n"
            + "C7: K♣, 8♠, 2♠, 7♦, 6♠, 5♥, 4♠, 3♦\n"
            + "C8: 3♣, 7♥, 2♥, 6♥, 5♦, 9♣, 8♦";
    game5.move(PileType.OPEN, 0, 0, PileType.CASCADE, 4);
    game5.move(PileType.OPEN, 3, 0, PileType.CASCADE, 4);

    assertEquals(expectedGameState, game5.getGameState());
  }

  /**
   * Test for move for wrong build. When card source and destination combination is valid but
   * internally build has same deck colour with correct difference of values of 1.
   */
  @Test
  public void testWrongBuildMove() {

    assertFalse(game6.isGameOver());
    game6.startGame(deckToPlay1, false);
    assertFalse(game6.isGameOver());

    String expectedGameState = "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 9♠, 10♦, 3♥, 7♦, 3♦, J♥\n"
            + "C2: 8♣, A♠, Q♠, 6♥, 6♠, A♥\n"
            + "C3: K♠, A♣, 2♠, 9♦, 8♦\n"
            + "C4: A♦, K♥, 2♥, Q♣, 4♥\n"
            + "C5: 4♣, 8♠, 7♣, Q♦, 6♣\n"
            + "C6: 2♣, 7♥, 10♥, 4♦, 10♠\n"
            + "C7: K♣, K♦, J♦, 4♠, 10♣\n"
            + "C8: 3♣, J♠, Q♥, 5♣, 9♣\n"
            + "C9: 7♠, 8♥, 3♠, 5♥, 6♦\n"
            + "C10: 9♥, J♣, 2♦, 5♦, 5♠";

    // invalid build, but valid source and destination card
    try {
      game6.move(PileType.CASCADE, 2, 3, PileType.CASCADE, 5);
      fail("error");
    } catch (IllegalArgumentException ise) {
      assertEquals(expectedGameState, game6.getGameState());
      assertFalse(game6.isGameOver());
    }
  }


}