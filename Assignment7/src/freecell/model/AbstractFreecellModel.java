package freecell.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Abstract base class for implementations of {@link FreecellOperations}. This class contains all
 * the method definitions that are common to the concrete implementations of the {@link
 * FreecellOperations} interface. A new implementation of the interface has the option of extending
 * this class, or directly implementing all the methods.
 */
public class AbstractFreecellModel implements FreecellOperations<Card> {
  /**
   * Represents standard size of deck of cards.
   */
  private static final int DECK_SIZE = 52;
  /**
   * Data structure to store piles of cards in Freecell. It is a Map which stores key as {@link
   * PileType} and values as List of Stacks of {@link Card}. Size of each List is determined by the
   * sizes provided by {@link FreecellOperationsBuilder}.
   */
  Map<PileType, List<Stack<Card>>> piles;
  /**
   * State of Freecell game. It can be Not Started, Started, or Game Over.
   */
  private FreecellState gameState;
  /**
   * Size of cascade pile in int.
   */
  protected int cascade;
  /**
   * Size of open pile in int.
   */
  protected int open;
  /**
   * Size of foundation pile in int.
   */
  private int foundation;

  /**
   * Private constructor to set the values fetched from {@link FreecellOperationsBuilder}. It sets
   * the user-defined configuration values which includes size of the cascade and open piles. It
   * also initializes piles required to play this game.
   *
   * @param cascade    size of the cascade pile to be created
   * @param open       size of the open pile to be created
   * @param foundation size of the foundation pile to be created
   */
  AbstractFreecellModel(int cascade, int open, int foundation) {
    this.gameState = FreecellState.NOT_STARTED;
    this.cascade = cascade;
    this.open = open;
    this.foundation = foundation;
    this.piles = new LinkedHashMap<>(3);
    initializePiles();
  }

  /**
   * Returns an instance of {@link FreecellOperationsBuilder} which is the Builder of this class.
   * This builder contains all the set of methods which could initialise data of this class and also
   * a build method which returns newly created instance of {@link FreecellModel}.
   */
  public static FreecellOperationsBuilder getBuilder() {
    return new AbstractFreecellModel.FreecellOperationsBuilderImpl();
  }

  /**
   * For the given values set in FreeCellModel it initializes Piles data structure required to play
   * this game. It calls {@link FreecellModel#addPile(PileType, int)} for each PileType with the
   * required size of pile.
   */
  private void initializePiles() {
    addPile(PileType.FOUNDATION, this.foundation);
    addPile(PileType.OPEN, this.open);
    addPile(PileType.CASCADE, this.cascade);
  }


  /**
   * Inserts one {@link PileType} at a time by creating piles of a given size. If any of the pile
   * value is too large, it handles {@link StackOverflowError} and immediately throws error with the
   * appropriate message.
   *
   * @param pileType which can be either Foundation, Cascade or Open.
   * @param size     default capacity of piles.
   */
  private void addPile(PileType pileType, int size) {
    Stack<Card> pileList;
    List<Stack<Card>> pile = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      try {
        pileList = new Stack<>();
      } catch (StackOverflowError e) {
        throw new IllegalStateException("System cannot process request");
      }
      pile.add(pileList);
    }
    piles.put(pileType, pile);
  }

  /**
   * Initializes deck for all the suits and values. A deck consist of 4 types of suits (Spade,
   * Clubs, Diamond, and Hearts) with value 2-10 and face cards of value A, J, Q, K. Refer {@link
   * Card} to know more about this in detail.
   *
   * @return deck containing all 52 unique cards including all types of suits and values.
   */
  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (CardSuit suit : CardSuit.values()) {
      for (CardValue value : CardValue.values()) {
        Card card = new CardImpl(suit, value);
        deck.add(card);
      }
    }
    return deck;
  }

  /**
   * It first checks if the deck is valid. To know about validity of the deck, please refer javadoc
   * of {@link FreecellModel#isDeckValid(List)} method.<br> If the deck is valid, it shuffles the
   * deck depending upon the boolean value of shuffle. It then initialises cascade pile with the
   * deck arranged in round-robin fashion. After the cascade is initialised properly, it updates the
   * state of game with {@link FreecellState#STARTED}.
   *
   * @param deck    the deck to be dealt.
   * @param shuffle if true, shuffle the deck else deal the deck as-is.
   * @throws IllegalArgumentException in case the deck is invalid.
   */
  @Override
  public void startGame(List<Card> deck, boolean shuffle) throws IllegalArgumentException {
    initializePiles();
    if (isDeckValid(deck)) {
      if (shuffle) {
        Collections.shuffle(deck);
      }
      List<Stack<Card>> cascadePile = piles.get(PileType.CASCADE);
      int j = 0;
      while (j < deck.size()) {
        for (Stack stack : cascadePile) {
          if (j >= deck.size()) {
            break;
          }
          stack.push(deck.get(j));
          j++;
        }
      }
      piles.put(PileType.CASCADE, cascadePile);
      this.gameState = FreecellState.STARTED;
    } else {
      throw new IllegalArgumentException("Invalid deck");
    }
  }

  /**
   * A deck is valid in case all type of suit is included with unique values starting from 2-10 and
   * A, J, Q, K. If the deck is not null, it creates a new set with the deck list. If both the size
   * of list and set is same, it means there are no duplicate values. This method returns true if
   * the deck is valid otherwise false.
   *
   * @param deck containing list of cards.
   * @return boolean containing validity of deck.
   */
  private boolean isDeckValid(List<Card> deck) {
    if (deck != null) {
      Set<Card> deckSet = new HashSet<>(deck);
      return (deck.size() == DECK_SIZE && deckSet.size() == DECK_SIZE);
    }
    return false;
  }

  /**
   * Checks the state of the game and checks if the move is valid. If the gameState is incorrect
   * throws IllegalStateException. If move is valid, Performs move operation on the card at index
   * cardIndex of pile pileNumber from source pile and moves it to the pile specified by destination
   * at location destPileNumber. else throws IllegalArgumentException exception
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalStateException    if the state of game is not Started
   * @throws IllegalArgumentException if the move is invalid
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    if (this.gameState != FreecellState.STARTED) {
      if (this.gameState == FreecellState.GAME_OVER) {
        throw new IllegalStateException("Game Over!");
      }
      throw new IllegalStateException("Game has not started yet");
    }

    if (isValidMove(source, pileNumber, cardIndex, destination, destPileNumber)) {
      int sourceSize = piles.get(source).get(pileNumber).size();
      Stack<Card> intermediateStack = new Stack<>();
      for (int i = 0; i < sourceSize - cardIndex; i++) {
        Card card = piles.get(source).get(pileNumber).pop();
        intermediateStack.push(card);
      }
      int intermediateStackSize = intermediateStack.size();
      for (int i = 0; i < intermediateStackSize; i++) {
        piles.get(destination).get(destPileNumber).push(intermediateStack.pop());
      }
      if (destination.equals(PileType.FOUNDATION)) {
        updateGameStateIfOver();
      }
    } else {
      throw new IllegalArgumentException("Not a valid move");
    }
  }

  /**
   * <p>Checks if the move is valid. The following are the cases considered (in order) while
   * validating a move:
   * </p>
   * <ul>
   * <li> If the source and destination pile is same and index is same, it immediately returns
   * true.</li>
   * <li> If the source type is foundation, it is not a valid move and therefore returns
   * false.</li>
   * <li>If the sourcePileNumber or destPileNumber or cardIndex is not valid which includes
   * negative number or any value greater than the supported size for pile, return false.</li>
   * <li>Considering the indexes are valid till this point and source pile can only be cascade or
   * open, it checks for all the type of destination cases.</li>
   * </ul>
   */
  private boolean isValidMove(PileType source, int sourcePileNumber, int cardIndex,
                              PileType destination, int destPileNumber)
          throws IllegalArgumentException {
    //if the source and destination index and pile are same
    if (source.equals(destination) && sourcePileNumber == destPileNumber) {
      return true;
    }
    //source pile type cannot be foundation
    if (source == PileType.FOUNDATION) {
      throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_001);
    }

    //if the indexes are valid which includes negative indexes
    if (isValidIndex(source, sourcePileNumber, cardIndex, destination, destPileNumber)) {
      if (destination == PileType.OPEN) {
        if (piles.get(PileType.OPEN).get(destPileNumber).size() > 0
                || cardIndex != piles.get(source).get(sourcePileNumber).size() - 1) {
          throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_005);
        } else {
          return true;
        }
      }
      //check for valid build
      if (isBuildMoveValid(source, sourcePileNumber, cardIndex)) {
        Card sourceCard = piles.get(source).get(sourcePileNumber).get(cardIndex);

        //check if foundation is empty and card face value is A only
        if (piles.get(destination).get(destPileNumber).isEmpty()) {
          if (destination == PileType.FOUNDATION
                  && !sourceCard.getFaceValue().equals(CardValue.ACE)) {
            throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_008);
          } else {
            return true;
          }
        }

        Card destinationCard = piles.get(destination).get(destPileNumber).peek();

        if (destination == PileType.FOUNDATION) {
          //if card has same suit and source card is bigger than destination card
          if (cardIndex == piles.get(source).get(sourcePileNumber).size() - 1
                  && sourceCard.getSuit() == destinationCard.getSuit()
                  && compareCards(sourceCard, destinationCard) == 1) {
            return true;
          } else {
            throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_010);
          }
        }
        if (destination == PileType.CASCADE) {
          //if card has different colored suit and destination card is bigger than source card
          if (!sourceCard.getSuit().getColor().equals(destinationCard.getSuit().getColor())
                  && compareCards(sourceCard, destinationCard) == -1) {
            return true;
          } else {
            throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_009);
          }
        }
      }
    }
    return false;
  }

  /**
   * Checks if the move is valid for the entire build. For basic implementation of Freecell model,
   * this is set true.
   *
   * @param pileType         the type of the source pile
   * @param sourcePileNumber the pile number of the given type, starting at 0
   * @param cardIndex        the index of the card to be moved from the source pile, starting at 0
   * @throws IllegalStateException    if the state of game is not Started
   * @throws IllegalArgumentException if the move is invalid
   */
  protected boolean isBuildMoveValid(PileType pileType, int sourcePileNumber, int cardIndex) {
    return true;
  }

  /**
   * Checks if indexes are within the range to support move operation.
   *
   * @param source           the type of the source pile (see @link{PileType})
   * @param sourcePileNumber the pile number of the given type, starting at 0
   * @param cardIndex        the index of the card to be moved from the source pile, starting at 0
   * @param destination      the pile number of the given type, starting at 0
   * @param destPileNumber   the type of the destination pile (see @link{PileType})
   * @throws IllegalArgumentException in case the index is not in the valid range.
   */
  protected boolean isValidIndex(PileType source, int sourcePileNumber, int cardIndex,
                                 PileType destination, int destPileNumber)
          throws IllegalArgumentException {
    if (sourcePileNumber >= piles.get(source).size() || sourcePileNumber < 0) {
      throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_002);
    }
    if (cardIndex != piles.get(source).get(sourcePileNumber).size() - 1) {
      throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_003);
    }
    if (destPileNumber >= piles.get(destination).size() || destPileNumber < 0) {
      throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_004);
    }
    return true;
  }

  /**
   * Checks for the comparison of the two cards to their assigned priority defined in {@link
   * CardComparator}. Returns value greater than 1 if the source card is bigger than destination
   * card, -1 if the source card is smaller than the destination card, or 0 otherwise.
   *
   * @param source      is the card to be moved
   * @param destination is the card on which the source card is to be placed
   */
  protected int compareCards(Card source, Card destination) {
    return CardComparator.getPriority(source.getFaceValue().getValue())
            - CardComparator.getPriority(destination.getFaceValue().getValue());
  }

  /**
   * Checks if all the piles in foundation pile is full. For each stack in foundation pile, it
   * checks if the size of the stack is 13.
   */
  protected void updateGameStateIfOver() {
    boolean isGameOver = true;
    List<Stack<Card>> values = piles.get(PileType.FOUNDATION);
    for (Stack<Card> stack : values) {
      isGameOver = isGameOver && stack.size() == DECK_SIZE / CardSuit.values().length;
    }
    if (isGameOver) {
      this.gameState = FreecellState.GAME_OVER;
    }
  }

  /**
   * Returns if the game is over. It checks the game state stored as {@link FreecellState} and
   * depending upon values of {@link FreecellState} it returns if the game is over. If the game
   * state is of type {@link FreecellState#GAME_OVER}, it returns true otherwise false.
   *
   * @return boolean as true if the game is over otherwise false.
   */
  @Override
  public boolean isGameOver() {
    return this.gameState == FreecellState.GAME_OVER;
  }

  /**
   * Iterates over all the pile types starting from Foundation, and then all the stacks of each pile
   * types to print pile types and face values on each card it contains. It assigns delimiters with
   * empty value(space and newline) at the start of each for loop and first step it does after
   * entering the loop is to append this empty delimiter to string builder. It again reassigns the
   * delimiter with the original values ("\n" and " " respectively) so that for the next iteration,
   * a delimiter is put between to pile types or cards. This solves the problem of not putting
   * newline after last piletype or space if there are no cards in any of the piles. Refer {@link
   * FreecellOperations#getGameState()} for the format of string to be returned.
   *
   * @return gamestate in String.
   */
  @Override
  public String getGameState() {
    StringBuilder sb = new StringBuilder();
    if (!this.gameState.equals(FreecellState.NOT_STARTED)) {
      String newPile = "";
      for (PileType pileType : piles.keySet()) {
        sb.append(newPile);
        newPile = "\n";
        List<Stack<Card>> value = piles.get(pileType);
        char pile = pileType.name().charAt(0);
        String newLine = "";
        for (int i = 0; i < value.size(); i++) {
          sb.append(newLine);
          newLine = "\n";
          sb.append(pile);
          sb.append(i + 1);
          sb.append(":");
          Stack<Card> stack = value.get(i);
          String delimiter = " ";
          for (Card card : stack) {
            sb.append(delimiter);
            delimiter = ", ";
            sb.append(card.toString());
          }
        }
      }
    }
    return sb.toString();
  }


  /**
   * Builder class which returns the instance of {@link FreecellModel} class.
   */
  public static class FreecellOperationsBuilderImpl implements FreecellOperationsBuilder {
    /**
     * Size of cascade piles set by this builder class.
     */
    protected int cascadeSize;
    /**
     * Size of open piles set by this builder class.
     */
    protected int openSize;
    /**
     * Size of foundation piles set by this builder class.
     */
    protected int foundationSize;

    /**
     * Default Minimum size of cascade piles of Freecell model.
     */
    protected static final int MIN_CASCADE_SIZE = 4;
    /**
     * Default Minimum size of open piles of Freecell model.
     */
    protected static final int MIN_OPEN_SIZE = 1;

    /**
     * Default constructor of this builder class. If no other method of this class is called except
     * build, the new instance of {@link FreecellModel} contains the values defined by this
     * constructor.
     */
    FreecellOperationsBuilderImpl() {
      this.cascadeSize = 8;
      this.openSize = 4;
      this.foundationSize = 4;
    }

    /**
     * Sets value for cascade piles.
     *
     * @param c value of size of cascade piles in int.
     * @return instance of FreecellOperationsBuilder.
     */
    @Override
    public FreecellOperationsBuilder cascades(int c) {
      if (c >= MIN_CASCADE_SIZE) {
        this.cascadeSize = c;
      } else {
        throw new IllegalArgumentException("Cascade size should be at least"
                + MIN_CASCADE_SIZE + " or above.");
      }
      return this;
    }

    /**
     * Sets value for open piles.
     *
     * @param o value of size of open piles in int.
     * @return instance of FreecellOperationsBuilder.
     */
    @Override
    public FreecellOperationsBuilder opens(int o) {
      if (o >= MIN_OPEN_SIZE) {
        this.openSize = o;
      } else {
        throw new IllegalArgumentException("Cascade size should be at least"
                + MIN_OPEN_SIZE + "or above");
      }
      return this;
    }

    /**
     * It will return the final instance of {@link FreecellOperations} object.
     *
     * @return FreecellOperations instance with the values set by this builder.
     */
    @Override
    public FreecellOperations<Card> build() {
      return new freecell.model.AbstractFreecellModel(this.cascadeSize, this.openSize,
              this.foundationSize);
    }
  }
}

