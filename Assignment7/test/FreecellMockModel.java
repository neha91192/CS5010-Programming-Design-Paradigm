import java.util.ArrayList;
import java.util.List;

import freecell.model.FreecellOperations;
import freecell.model.PileType;

/**
 * Mock model of type {@link FreecellOperations} used for testing {@link
 * freecell.controller.IFreecellController}.
 */
public class FreecellMockModel<K> implements FreecellOperations<K> {
  private StringBuilder log;
  private List<K> deck;
  private boolean gameOver;
  private String gameState;
  private K invalidDeckObject;
  private int invalidCardIndex;
  private int makeGameOverIndex;
  private String errorMessage;

  /**
   * Constructor to initialize desired values for model. deck contains objects of type K.
   * invalidDeckObject is any object in the deck which is invalid, i.e if that value is encountered,
   * the deck becomes invalid invalidCardIndex is any value for card index which is out of the range
   * for the pile size. makeGameIndex is a value which ends the game. errorMessage is a specific
   * String which will be expected when a particular error occurs.
   *
   * @param log               is to keep log of the model
   * @param deck              is the deck with K type object provided to mock model
   * @param gameOver          is boolean value, true if game is over, false otherwise
   * @param gameState         state of the game for the model
   * @param invalidDeckObject is an object in the deck which is invalid for the model
   * @param invalidCardIndex  is an index of a card in the deck which is invalid for the model
   * @param makeGameOverIndex is an index, which if present in the model will end the game.
   * @param errorMessage      is the message recieved for every exception thrown by the model.
   */
  public FreecellMockModel(StringBuilder log, List<K> deck, boolean gameOver, String gameState,
                           K invalidDeckObject, int invalidCardIndex, int makeGameOverIndex,
                           String errorMessage) {
    this.log = log;
    this.deck = deck;
    this.gameOver = gameOver;
    this.invalidDeckObject = invalidDeckObject;
    this.invalidCardIndex = invalidCardIndex;
    this.makeGameOverIndex = makeGameOverIndex;
    this.errorMessage = errorMessage;
    this.gameState = "";
  }

  /**
   * Creates a copy of deck and returns the values.
   *
   * @return list of cards as deck.
   */
  @Override
  public List<K> getDeck() {
    List<K> deckCopy = new ArrayList<>();
    for (K card : deck) {
      deckCopy.add(card);
    }
    return deckCopy;
  }

  /**
   * Logs if the game has been started and verifies the same while testing at controller level.
   *
   * @param deck    the deck to be dealt
   * @param shuffle if true, shuffle the deck else deal the deck as-is
   */
  @Override
  public void startGame(List<K> deck, boolean shuffle) throws IllegalArgumentException {
    log.append("Start game with deck:\n");
    for (K card : deck) {
      log.append(card.toString() + "\n");
    }
    log.append("Shuffle: " + shuffle + "\n");
    if (deck.contains(invalidDeckObject + "")) {
      throw new IllegalArgumentException("Invalid deck.");
    }
    if (deck.contains(invalidCardIndex + "")) {
      throw new IllegalArgumentException(errorMessage);
    }
    this.gameState = "Started";
  }

  /**
   * Updates the log for the move which includes move parameters and tests if the invalid values are
   * logged correctly for the same case.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if cardIndex is invalid
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    log.append("Move for: " + source + " " + pileNumber + " " + cardIndex + " "
            + destination + " " + destPileNumber + "\n");
    if (cardIndex == this.invalidCardIndex) {
      throw new IllegalArgumentException(this.errorMessage);
    }
    if (cardIndex == this.makeGameOverIndex) {
      this.gameOver = true;
    }
    if (this.gameState.equals("")) {
      throw new IllegalStateException(this.errorMessage);
    }
  }

  /**
   * Returns if the game is over.
   *
   * @return true if the game is over otherwise false.
   */
  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Returns the Game state as it is.
   *
   * @return gameState in String.
   */
  @Override
  public String getGameState() {
    return gameState;
  }
}
