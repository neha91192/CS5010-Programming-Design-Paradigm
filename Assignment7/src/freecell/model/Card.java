package freecell.model;

/**
 * This is the interface of Card. It provides methods to get the suit of the card and facevalue of
 * the card.<br> A suit of the card depicts the suit of a card.<br> The facevalue of the card
 * depicts the value of the card.<br>
 */
public interface Card {
  /**
   * Returns the suit of the card. See {@link CardSuit} enumeration for more details about the card
   * suit.
   *
   * @return suit of the card.
   */
  CardSuit getSuit();

  /**
   * Represents value of the card. It returns the values described in {@link CardValue}
   * enumeration.
   *
   * @return value of the card.
   */
  CardValue getFaceValue();

}
