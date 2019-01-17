package freecell.model;

import java.util.Comparator;

/**
 * Checks for the priority of the two card. In this comparator, Ace is given lower priority than
 * other cards. If A has to be given higher priority for any other card game, a new comparator can
 * be implemented.
 */
public class CardComparator implements Comparator<Card> {
  /**
   * Finds the priority of two cards as per the face value and returns the appropriate value.
   *
   * @param card1 to be compared.
   * @param card2 to be compared.
   * @return -1 if card1 is smaller than card2, 1 if card1 is sma
   */
  @Override
  public int compare(Card card1, Card card2) {
    int value1 = getPriority(card1.getFaceValue().getValue());
    int value2 = getPriority(card2.getFaceValue().getValue());
    return value1 - value2;
  }

  /**
   * Returns ordering of card values. Since A has been given lowest value in the priority, sorting
   * will result placing A in the front, i.e., ascending order.
   *
   * @param card containing face value in String.
   * @return priority of card in integer.
   */
  static int getPriority(String card) {
    int priority = 0;
    switch (card) {
      case "A":
        priority = 1;
        break;
      case "2":
        priority = 2;
        break;
      case "3":
        priority = 3;
        break;
      case "4":
        priority = 4;
        break;
      case "5":
        priority = 5;
        break;
      case "6":
        priority = 6;
        break;
      case "7":
        priority = 7;
        break;
      case "8":
        priority = 8;
        break;
      case "9":
        priority = 9;
        break;
      case "10":
        priority = 10;
        break;
      case "J":
        priority = 11;
        break;
      case "Q":
        priority = 12;
        break;
      case "K":
        priority = 13;
        break;
      default:
        break;
    }
    return priority;
  }

}
