package howtoinvestfordummies.view;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a class with Text based view implementation. The main objective of this
 * class is to
 * handle text based request and response from user and controller respectively.
 */
public class InvestmentTextView implements IInvestmentView {
  /**
   * Object required to transmit output.
   */
  private Appendable out;

  /**
   * Scanner object used to parse input data.
   */
  private Scanner scan;

  /**
   * Constructor of this class. Takes input as {@link Readable} and returns output as
   * {@link
   * Appendable} object.
   *
   * @param in  of type Readable.
   * @param out of type Appendable.
   */
  public InvestmentTextView(Readable in, Appendable out) {
    this.out = out;
    scan = new Scanner(in);
  }

  /**
   * Returns message to the user by appending appropriate signals to Appendable object.
   * In case
   * there is any error reading message, it throws {@link IllegalStateException}.
   *
   * @param message in string.
   * @throws IllegalStateException in case there is any error transmitting message.
   */
  @Override
  public void transmitMessage(String message) {
    try {
      out.append(message);
      out.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Exception encountered in transmitting message");
    }
  }

  /**
   * Scans the current input of the user and returns the value in String.
   *
   * @return input value in String.
   */
  @Override
  public String read() {
    return scan.next();
  }

  /**
   * Checks if there is any input left in the scanner object.
   *
   * @return true in case there is any input left to be read otherwise false.
   */
  @Override
  public boolean hasNextInput() {
    return scan.hasNext();
  }

  @Override
  public void setListener(ActionListener listener) {
    System.out.println("");
  }

}
