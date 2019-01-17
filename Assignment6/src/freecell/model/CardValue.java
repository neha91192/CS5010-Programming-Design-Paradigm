package freecell.model;

/**
 * Value for the 13 types of values of cards in the game of Freecell. <br> Each value of card is
 * given a String value, and an integer number depicting the priority of the card.<br> Ace: This
 * card has a unique value "A" and has lowest priority 1.<br> Two: This card has a unique value "2"
 * and has priority 2.<br> Three: This card has a unique value "3" and has priority 3.<br> Four:
 * This card has a unique value "4" and has priority 4.<br> Five: This card has a unique value "5"
 * and has priority 5.<br> Six: This card has a unique value "6" and has priority 6.<br> Seven: This
 * card has a unique value "7" and has priority 7.<br> Eight: This card has a unique value "8" and
 * has priority 8.<br> Nine: This card has a unique value "9" and has priority 9.<br> Ten: This card
 * has a unique value "10" and has priority 10.<br> Joker: This card has a unique value "J" and has
 * priority 11.<br> Queen: This card has a unique value "Q" and has priority 12.<br> King: This card
 * has a unique value "K" and has priority 13.<br>
 */
public enum CardValue {
  ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
  EIGHT("8"), NINE("9"), TEN("10"), JOKER("J"), QUEEN("Q"), KING("K");

  private final String value;

  /**
   * A constructor that takes the value and priority of a card as String and integer respectively.
   *
   * @param value is the value of the card as String
   */
  CardValue(String value) {
    this.value = value;
  }

  /**
   * Takes in a string and compares this string with all the string values of the card. If the
   * string value is equal to one of the values, it returns that card value in CardValue format.<br>
   * If there are no card values matching to the value, we do not have such a value for any card and
   * hence returns null.
   *
   * @param value is the value of the card as String
   * @return the value of the card from CardValue enum if value is equal to one of the value of any
   * type of card, null otherwise.
   */
  public static CardValue fromString(String value) {
    for (CardValue val : CardValue.values()) {
      if (val.getValue().equals(value)) {
        return val;
      }
    }
    return null;
  }

  /**
   * The value of the card as String is returned, the values of cards acceptable for our deck of
   * Freecell game are "A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q" and "K".
   *
   * @return the value of the card as String
   */
  public String getValue() {
    return value;
  }

}
