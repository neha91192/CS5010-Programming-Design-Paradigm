package freecell.model;

import java.util.Stack;

/**
 * Using this version of Freecell, the player can move several cards at once from one cascade pile
 * to another. This class supports multi-move to Cascade type piles only.
 */
public class FreecellMultiMoveModel extends AbstractFreecellModel {

  /**
   * Private constructor to set the values fetched from {@link FreecellOperationsBuilder}. It sets
   * the user-defined configuration values which includes size of the cascade and open piles. It
   * also initializes piles required to play this game.
   *
   * @param cascade    size of the cascade pile to be created
   * @param open       size of the open pile to be created
   * @param foundation size of the foundation pile to be created
   */
  private FreecellMultiMoveModel(int cascade, int open, int foundation) {
    super(cascade, open, foundation);
  }

  /**
   * Checks if the conditions to move multi-cards is satisfied for the given move attributes. It
   * first checks if the source is Cascade since this type validation is for cascade piles only. It
   * then calls {@link FreecellMultiMoveModel#isMulticardMoveFeasible(int)} to check for the
   * feasible move condition. If that is true, it checks if all the cards in the build are placed in
   * the desired order. At any moment if the build is invalid, it throws {@link
   * IllegalArgumentException} with appropriate messages.
   *
   * @param pileType         the type of the source pile see @link{PileType}
   * @param sourcePileNumber the pile number of the given type, starting at 0
   * @param cardIndex        the index of the card to be moved from the source pile, starting at 0
   * @throws IllegalArgumentException if the build move is invalid.
   */
  protected boolean isBuildMoveValid(PileType pileType, int sourcePileNumber, int cardIndex)
          throws IllegalArgumentException {
    if (pileType.equals(PileType.CASCADE)) {
      Stack<Card> cards = piles.get(PileType.CASCADE).get(sourcePileNumber);
      if (isMulticardMoveFeasible(cards.size() - cardIndex)) {
        for (int i = cardIndex; i < cards.size() - 1; i++) {
          Card card1 = cards.get(i);
          Card card2 = cards.get(i + 1);
          if (compareCards(card1, card2) != 1
                  || card1.getSuit().getColor().equals(card2.getSuit().getColor())) {
            throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_007);
          }
        }
      }
    }
    return true;
  }

  /**
   * Checks if the multi card build is feasible to move or not. It first counts the number of empty
   * piles of type of Open(n) and Cascade(k). If for the given builds size, the value of (n+1)*2^k
   * is greater, then it can accommodate all the cards in the empty slots, otherwise not.
   *
   * @param buildSize is the number of card in the build that needs to be moved.
   * @return true if the build is feasible to move otherwise false.
   * @throws IllegalArgumentException if the build is not feasible to move.
   */
  private boolean isMulticardMoveFeasible(int buildSize) throws IllegalArgumentException {
    int n = 0;
    int k = 0;
    for (Stack<Card> openPile : piles.get(PileType.OPEN)) {
      if (openPile.isEmpty()) {
        n++;
      }
    }
    for (Stack<Card> cascadePile : piles.get(PileType.CASCADE)) {
      if (cascadePile.isEmpty()) {
        k++;
      }
    }
    if (buildSize > (n + 1) * Math.pow(2, k)) {
      throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_006);
    }
    return true;
  }

  /**
   * Checks if indexes are within the range to support move operation.
   *
   * @param source           the type of the source pile (see @link{PileType})
   * @param sourcePileNumber the pile number of the given type, starting at 0
   * @param cardIndex        the index of the card to be moved from the source pile, starting at 0
   * @param destination      the type of the destination pile (see @link{PileType})
   * @param destPileNumber   the pile number of the given type, starting at 0
   * @return true if the the indexes are valid
   * @throws IllegalArgumentException if the move is invalid
   */
  protected boolean isValidIndex(PileType source, int sourcePileNumber, int cardIndex,
                                 PileType destination, int destPileNumber) {
    if (sourcePileNumber >= piles.get(source).size() || sourcePileNumber < 0) {
      throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_002);
    }
    if (cardIndex >= piles.get(source).get(sourcePileNumber).size() || cardIndex < 0) {
      throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_003);
    }
    if (destPileNumber >= piles.get(destination).size() || destPileNumber < 0) {
      throw new IllegalArgumentException(FreecellMoveMessages.INVALID_MOVE_004);
    }
    return true;
  }

  /**
   * Builder class to return instance of {@link FreecellOperations}.
   */
  public static class FreecellOperationsBuilderImpl
          extends AbstractFreecellModel.FreecellOperationsBuilderImpl {
    /**
     * It will return the final instance of {@link FreecellOperations} object.
     *
     * @return FreecellOperations instance with the values set by this builder.
     */
    @Override
    public FreecellOperations<Card> build() {
      return new freecell.model.FreecellMultiMoveModel(cascadeSize, openSize, foundationSize);
    }
  }

  /**
   * This builder contains all the set of methods which could initialise data of this class and also
   * a build method which returns newly created instance of {@link FreecellModel}.
   *
   * @return an instance of {@link FreecellOperationsBuilder} which is the Builder of this class.
   */
  public static FreecellOperationsBuilder getBuilder() {
    return new FreecellMultiMoveModel.FreecellOperationsBuilderImpl();
  }

}
