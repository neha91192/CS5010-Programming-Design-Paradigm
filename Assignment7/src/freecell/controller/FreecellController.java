package freecell.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import freecell.model.FreecellOperations;
import freecell.model.PileType;

/**
 * Implements the {@link IFreecellController}. The main purpose of this controller is to take inputs
 * from user and give outputs fetched from model. It is responsible for performing basic input
 * validation and transmitting message of the outcomes of the validations.
 */
public class FreecellController<K> implements IFreecellController<K> {
  /**
   * Responsible for taking inputs from the user.
   */
  private final Readable rd;
  /**
   * Responsible for giving outputs to the user.
   */
  private final Appendable ap;
  /**
   * Scans the inputs taken from the user one at a time.
   */
  private Scanner scan;
  /**
   * Stores map of Character for the given pile with respect to its implementation in model.
   */
  private Map<Character, PileType> validPiles;
  /**
   * Value of the source pile to be sent to the model to make any move.
   */
  private PileType sourcePile;
  /**
   * Value of the destination pile to be sent to the model to make any move.
   */
  private PileType destinationPile;
  /**
   * Value of the source pile number to be sent to the model to make any move.
   */
  private int sourcePileNumber;
  /**
   * Value of the destination pile number to be sent to the model to make any move.
   */
  private int destPileNumber;
  /**
   * Value of the source card index number to be sent to the model to make any move.
   */
  private int sourceCardIndex;

  /**
   * The Readable object is used to take user input and the Appendable object is used to transmit
   * output. Readable and Appendable are two existing interfaces in Java that abstract input and
   * output respectively.
   *
   * @param rd is the Readable object used to take user input
   * @param ap is the Appendable object used to transmit output
   * @throws IllegalArgumentException if and only if the readable or appendable objects are null
   */
  public FreecellController(Readable rd, Appendable ap) {
    validPiles = new HashMap<>();
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Invalid readable or appendable!");
    }
    this.rd = rd;
    this.ap = ap;
    validPiles.put('C', PileType.CASCADE);
    validPiles.put('F', PileType.FOUNDATION);
    validPiles.put('O', PileType.OPEN);

  }

  /**
   * <p>This method represents controller method to play the Freecell game. It is responsible for
   * providing an interactive game session to the user when being called. This method performs basic
   * input validation while requesting for the inputs one at a time for any freecell move.</p>
   * <p>A valid sequence of input can be defined as follows:</p>
   * <ol>
   * <li>The source pile (e.g., "C1", as a single word). The pile number begins at 1, so that it is
   * more human-friendly.</li>
   * <li>The card index, again with the index beginning at 1.</li>
   * <li>The destination pile (e.g., "F2", as a single word). The pile number is again counted from
   * 1.</li>
   * </ol>
   * <p>If the input for the move is invalid, for example, invalid pile name or index value, it
   * transmits message to the user to ask for the different input.  It indefinitely asks for the
   * valid input for a given pile or card assignment.If the move is valid, the game state is
   * propagated to the user after each valid move. At any moment if user presses "q" or "Q" that
   * calls for Quit, the game session is ended without being over. If the game is over, the final
   * state of the game is displayed, along with the message.</p>
   *
   * @param deck    containing list of cards to play with.
   * @param model   of type FreecellOperations.
   * @param shuffle true if deck needs to be shuffled otherwise false.
   * @throws IllegalArgumentException in case any invalid input parameters are provided.
   * @throws IllegalStateException    in case any error occurred while reading or writing message.
   */
  @Override
  public void playGame(List<K> deck, FreecellOperations<K> model, boolean shuffle)
          throws IllegalArgumentException, IllegalStateException {

    if (deck == null || model == null) {
      throw new IllegalArgumentException("Invalid deck or model!");
    }

    if (shuffle) {
      Collections.shuffle(deck);
    }
    try {
      model.startGame(deck, shuffle);
    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException("Invalid deck.");
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid model.");
    }
    scan = new Scanner(this.rd);

    transmitMessage(model.getGameState());
    while (true) {
      transmitMessage("Please enter Source Pile, Card Index, Destination Pile one on each line");
      String input = scan.next();
      if (isInputQuit(input)) {
        break;
      }
      if (processSource(input) == 1) {
        break;
      }
      input = scan.next();
      if (isInputQuit(input)) {
        break;
      }
      if (processCardIndex(input) == 1) {
        break;
      }
      input = scan.next();
      if (isInputQuit(input)) {
        break;
      }
      if (processDestination(input) == 1) {
        break;
      }
      try {
        model.move(sourcePile, sourcePileNumber - 1, sourceCardIndex - 1,
                destinationPile, destPileNumber - 1);
        transmitMessage(model.getGameState());
        if (model.isGameOver()) {
          transmitMessage("Game over.");
          break;
        }
      } catch (IllegalStateException | IllegalArgumentException e) {
        transmitMessage(e.getMessage());
      }

    }
  }

  /**
   * Parses source pile input and checks for validity. In case only pile name is incorrect, it
   * expects user to input only pile name and same for pile number. Once both the values are
   * acceptable, it sets the sourcePile and sourcePileNumber values.
   *
   * @param input as string that needs to be parsed.
   * @return 0 in case operation was successful otherwise 1 in case user voluntarily quits.
   */
  private int processSource(String input) {
    char sPile = input.charAt(0);
    String sNumber = input.substring(1);
    while (true) {
      if (!isValidPile(sPile)) {
        transmitMessage("Invalid Source Pile Name. Please enter Source Pile Name again.");
        input = scan.next();
        if (isInputQuit(input)) {
          return 1;
        }
        sPile = input.charAt(0);
      } else if (!isValidPileIndex(sNumber)) {
        transmitMessage("Invalid Source Pile Number. Please enter Source Pile Number again.");
        input = scan.next();
        if (isInputQuit(input)) {
          return 1;
        }
        sNumber = input;
      } else {
        sourcePile = validPiles.get(sPile);
        sourcePileNumber = Integer.parseInt(sNumber);
        break;
      }
    }
    return 0;
  }

  /**
   * Parses card index input and checks for validity. In case index is not an acceptable integer, it
   * asks for the valid input again. Once the value is acceptable, it sets the Card Index value.
   *
   * @param input as string that needs to be parsed.
   * @return 0 in case operation was successful otherwise 1 in case user voluntarily quits.
   */
  private int processCardIndex(String input) {
    String cardIndex = input;
    while (true) {
      if (!isValidCardIndex(cardIndex)) {
        transmitMessage("Invalid Card Index. Please enter Card Index again.");
        input = scan.next();
        if (isInputQuit(input)) {
          return 1;
        }
        cardIndex = input;
      } else {
        sourceCardIndex = Integer.parseInt(cardIndex);
        break;
      }
    }
    return 0;
  }

  /**
   * Parses destination pile input and checks for validity. In case only pile name is incorrect, it
   * expects user to input only pile name and same for pile number. Once both the values are
   * acceptable, it sets the destinationPile and destinationPileNumber values.
   *
   * @param input as string that needs to be parsed.
   * @return 0 in case operation was successful otherwise 1 in case user voluntarily quits.
   */
  private int processDestination(String input) {
    char destPile = input.charAt(0);
    String destNumber = input.substring(1);
    while (true) {
      if (!isValidPile(destPile)) {
        transmitMessage("Invalid Destination Pile Name. Please enter Destination Pile Name again.");
        input = scan.next();
        if (isInputQuit(input)) {
          return 1;
        }
        destPile = input.charAt(0);
      } else if (!isValidPileIndex(destNumber)) {
        transmitMessage("Invalid Destination Pile Number. "
                + "Please enter Destination Pile Number again.");
        input = scan.next();
        if (isInputQuit(input)) {
          return 1;
        }
        destNumber = input;
      } else {
        destinationPile = validPiles.get(destPile);
        destPileNumber = Integer.parseInt(destNumber);
        break;
      }
    }
    return 0;
  }

  /**
   * Checks if the Pile character is acceptable. It refers validPiles map to look for the key values
   * described for each type of pile.
   *
   * @param c as character for storing valid pile name.
   * @return true in case pile name is valid otherwise false.
   */
  private boolean isValidPile(Character c) {
    return validPiles.containsKey(c);
  }

  /**
   * Determines if the string input can be a valid pile index number. It accepts any string input as
   * candidate valid pile if it can be represented as an integer.
   *
   * @param index of type string.
   * @return true in case pile index is valid, otherwise false.
   */
  private boolean isValidPileIndex(String index) {
    int val;
    try {
      val = Integer.parseInt(index);
    } catch (NumberFormatException e) {
      return false;
    }
    return val > 0;
  }

  /**
   * Determines if the string input can be a valid card index number. It accepts any string input as
   * candidate valid pile if it can be represented as an integer. Since the card index range can
   * only be between 1 to 52, it checks for that range to assert validity.
   *
   * @param index of type string.
   * @return true in case pile index is valid, otherwise false.
   */
  private boolean isValidCardIndex(String index) {
    int val;
    try {
      val = Integer.parseInt(index);
    } catch (NumberFormatException e) {
      return false;
    }
    return (val > 0 && val < 53);
  }

  /**
   * Returns message to the user by appending appropriate signals to Appendable object. In case
   * there is any error reading message, it throws {@link IllegalStateException}.
   *
   * @param message in string.
   * @throws IllegalStateException in case there is any error transmitting message.
   */
  private void transmitMessage(String message) {
    try {
      this.ap.append(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Error transmitting output.");
    }
  }

  /**
   * Checks if the input provided is for Quit operation and updates the transmission object.
   *
   * @param input in String.
   * @return true if the input provided is for quit, otherwise false.
   */
  private boolean isInputQuit(String input) {
    if (input.equalsIgnoreCase("q")) {
      transmitMessage("Game quit prematurely.");
      return true;
    }
    return false;
  }
}

