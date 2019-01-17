package howtoinvestfordummies.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import howtoinvestfordummies.model.StockInvestmentModel;
import howtoinvestfordummies.model.StockInvestmentStrategies;

/**
 * Represents controller implementation for {@link IInvestmentController}. The main function of this
 * class to take request from view known as commands and performs required operations. The status of
 * the operation is then transmitted back to the user from this class.
 */
public class InvestmentController implements IInvestmentController {
  /**
   * Object required to take input.
   */
  private Readable in;
  /**
   * Object required to transmit output.
   */
  private Appendable out;
  /**
   * Scanner object used to parse input data.
   */
  private Scanner scan;
  /**
   * Map containing known commands and line of action for those commands in the form of Function
   * object.
   */
  private Map<String, Function<Scanner, StockInvestmentCommand>> commands;

  /**
   * Represents constructor of this class. It initialises appendable and readable object, scanner
   * and loads command data into the map.
   *
   * @param in  of type Readable.
   * @param out of type Appendable.
   * @throws IllegalArgumentException in case the readable or appendable objects are null.
   */
  public InvestmentController(Readable in, Appendable out) throws
          IllegalArgumentException {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Please provide valid Readable or " +
              "Appendable object");
    }
    this.in = in;
    this.out = out;
    scan = new Scanner(in);

    commands = new HashMap<>();
    commands.put("1", s -> new CreatePortfolio(scan, out));
    commands.put("2", s -> new BuyStock(scan, out));
    commands.put("3", s -> new DetermineCostAndValue(scan, out));
    commands.put("4", s -> new ViewPortfolioComposition(scan, out));
  }

  /**
   * Starting point of application which is responsible for taking scanner inputs and delegating
   * work to the commands found the commands map. It indefinitely scans input and stops executing
   * when user enters "q" or "quit".
   *
   * @param model of type StockInvestmentStrategies.
   * @throws IllegalArgumentException in case model is invalid.
   */
  @Override
  public void start(StockInvestmentStrategies model) throws
          IllegalArgumentException, IllegalStateException {
    if (model == null) {
      throw new IllegalArgumentException("Please provide a valid Model object");
    }
    transmitMessage("Welcome to Invest for Dummies Application!");
    transmitMessage("Following are the options:");
    transmitMessage("Enter 1 to Create Portfolio");
    transmitMessage("Enter 2 to Buy Stock for a Portfolio at a given date");
    transmitMessage("Enter 3 to Find cost and value of a portfolio for a given date");
    transmitMessage("Enter 4 to View Portfolio Composition");
    transmitMessage("Enter '#' to return to the main menu");
    transmitMessage("Enter 'q' or 'quit' to exit");
    while (scan.hasNext()) {
      StockInvestmentCommand command;
      String in = scan.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit"))
        return;
      Function<Scanner, StockInvestmentCommand> cmd =
              commands.getOrDefault(in, null);
      if (cmd == null) {
        throw new IllegalArgumentException("Command not found.");
      } else {
        command = cmd.apply(scan);
        command.go(model);
        transmitMessage("You are in Menu. Select option number to continue or q to quit");
      }
    }
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
      out.append(message);
      out.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Exception encountered in transmitting " +
              "message");
    }

  }

  /**
   * Entry point of the application. This main method calls the {@link IInvestmentController} to
   * perform investment related activities.
   *
   * @param args input arguments in string.
   */
  public static void main(String[] args) {
    StockInvestmentStrategies model = new StockInvestmentModel();
    IInvestmentController controller = new
            InvestmentController(new InputStreamReader(System.in), System.out);
    controller.start(model);
  }

}
