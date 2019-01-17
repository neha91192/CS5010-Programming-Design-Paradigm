
package freecell.model;

/**
 * Represents invalid move messages thrown by {@link FreecellOperations}.
 */
public final class FreecellMoveMessages {
  /**
   * Message thrown when Source type provided is foundation.
   */
  public static final String INVALID_MOVE_001 = "Invalid Move. Try Again. "
          + "Source pile type cannot be foundation";
  /**
   * Message thrown when source pile number is out of range.
   */
  public static final String INVALID_MOVE_002 = "Invalid Move. Try Again. Source pile number "
          + "is out of range.";
  /**
   * Message thrown when card index provided is not valid.
   */
  public static final String INVALID_MOVE_003 = "Invalid Move. Try Again. Card Index provided is "
          + "invalid.";
  /**
   * Message thrown when destination pile number is out of range.
   */
  public static final String INVALID_MOVE_004 = "Invalid Move. Try Again. Destination pile number "
          + "is out of range.";
  /**
   * Message thrown when given number of source cards cannot be moved to open pile.
   */
  public static final String INVALID_MOVE_005 = "Invalid Move. Try Again. "
          + "Open Pile cannot accommodate these many source cards.";
  /**
   * Message thrown in FreecellMultiMoveModel when the maximum intermediate slots are not enough for
   * the move.
   */
  public static final String INVALID_MOVE_006 = "Invalid Move. Try Again. "
          + "intermediate slots are not enough for the move.";
  /**
   * Message thrown in FreecellMultiMoveModel when the build contains invalid order of cards or suit
   * color.
   */
  public static final String INVALID_MOVE_007 = "Invalid Move. Try Again. "
          + "Invalid order of cards or suit color in the source cards.";
  /**
   * Message thrown when Foundation is empty and source card is not Ace.
   */
  public static final String INVALID_MOVE_008 = "Invalid Move. Try Again. "
          + "When Foundation card is empty, only Ace can be placed.";
  /**
   * Message thrown when source is cascade and destination is also cascade and move is invalid.
   */
  public static final String INVALID_MOVE_009 = "Invalid Move. Try Again. "
          + "Cards cannot be of same suit or source card value should be 1 less"
          + " than destination card.";
  /**
   * Message thrown when source is cascade and destination is foundation and move is invalid.
   */
  public static final String INVALID_MOVE_010 = "Invalid Move. Try Again. "
          + "Cards should be of same suit and source card value should be 1 greater than "
          + "destination card";

}
