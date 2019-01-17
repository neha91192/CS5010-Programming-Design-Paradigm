package freecell.model;

/**
 * Suits for the four types of cards in the game of Freecell. <br> Each type of card is given two
 * String values, a suit and a color.<br> Clubs: This card has a unique suit "♣" and is black in
 * color. <br> Diamonds: This card has a unique suit "♦" and is red in color.<br> Hearts: This card
 * has a unique suit "♥" and is red in color. <br> Spades: This card has a unique suit "♠" and is
 * black in color.<br>
 */
public enum CardSuit {
  CLUBS("♣", "BLACK"), DIAMONDS("♦", "RED"), HEARTS("♥", "RED"), SPADES("♠", "BLACK");
  private final String suit;
  private final String color;

  /**
   * A constructor that takes the suit and color of a card as String.
   *
   * @param suit  is the suit of the card as String
   * @param color is the color of the card as String
   */
  CardSuit(String suit, String color) {
    this.suit = suit;
    this.color = color;
  }

  /**
   * Takes in a string and compares this string with all the string suits of the types of card. If
   * the string value is equal to one of the suits, it returns that card type in CardSuit
   * format.<br> If there are no suits matching to the value, we do not have such a suit for any of
   * our card and hence returns null.
   *
   * @param value is the suit of the card as String
   * @return the suit of the card from CardSuit enum if value is equal to one of the suit value for
   * any type of card, null otherwise.
   */
  public static CardSuit fromString(String value) {
    for (CardSuit suit : CardSuit.values()) {
      if (suit.getSuit().equals(value)) {
        return suit;
      }
    }
    return null;
  }

  /**
   * The suit of the card as String is returned, the suits of cards acceptable for our deck of
   * Freecell game are "♣", "♦", "♥" and "♠".
   *
   * @return the suit of the card as String
   */
  String getSuit() {
    return suit;
  }

  /**
   * The color of the card as String is returned, the colors of cards acceptable for our deck of
   * Freecell game are "Red" and "Black".
   *
   * @return the color of the card as String
   */
  String getColor() {
    return color;
  }
}
