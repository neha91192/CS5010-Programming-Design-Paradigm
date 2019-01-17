package howtoinvestfordummies.view;

import java.awt.event.ActionListener;

/**
 * Represents a dedicated view interface with the basic responsibility of taking input from user and
 * transmitting output for different cases.
 */
public interface IInvestmentView {

  /**
   * Takes the result of any operation from the controller to display message to any type of view.
   *
   * @param message of type String.
   */
  void transmitMessage(String message);

  /**
   * Takes input from user and returns the value of input to the controller to process user
   * request.
   *
   * @return input of type String.
   */
  String read();

  /**
   * Checks if input is pending to be read. Returns true in that case otherwise false.
   *
   * @return answer in Boolean.
   */
  boolean hasNextInput();

  /**
   * Set the listener for any actions.
   *
   * @param listener of type ActionListener.
   */
  void setListener(ActionListener listener);
}
