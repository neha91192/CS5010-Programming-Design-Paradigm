package freecell.model;

/**
 * This class implements the interface Card and provides the methods specific to a card.<br> A card
 * should have a suit and value.<br> It has a constructor that takes a card's suit as CardSuit which
 * is an enum, and card's value as ICardValue which is another enum and creates a card.<br> A card
 * with only the values in enums can be created. Hence, It is not possible to create an invalid
 * card. The class also provides a toString method which returns the value and suit of the card as a
 * string.
 */
public class CardImpl implements Card {
  private final CardSuit suit;
  private final CardValue value;

  /**
   * A constructor that takes a suit from CardSuit enum and value from ICardValue enum.
   *
   * @param suit  is the suit of the card of CardSuit enum type
   * @param value is the value of the card from ICardValue enum
   */
  public CardImpl(CardSuit suit, CardValue value) {
    this.suit = suit;
    this.value = value;
  }

  /**
   * The suit of the card in CardSuit format is returned, where CardSuit is an enum declaring all
   * the the suit of cards acceptable for our deck of Freecell game.
   *
   * @return the suit of the card as CardSuit
   */
  public CardSuit getSuit() {
    return suit;
  }


  /**
   * A card can have value from A, 2 upto K. This method will return the value fo the card.
   *
   * @return the value of the card as ICardValue
   */
  public CardValue getFaceValue() {
    return value;
  }

  /**
   * toString method of the Comparable interface returns the text of the card in String format.
   *
   * @return the value and suit of the card in String format
   */
  @Override
  public String toString() {
    return this.value.getValue() + this.suit.getSuit();
  }


}
