package howtoinvestfordummies.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.view.IInvestmentView;

/**
 * Represents controller implementation for {@link IInvestmentController}. The main
 * function of this
 * class to take request from view known as commands and performs required operations.
 * The status of
 * the operation is then transmitted back to the user from this class.
 * <strong>Additions in this class:</strong>
 * <ul>
 * <li>option 5: For configuring high level portfolio as per the strategy required.</li>
 * </ul>
 */
public class InvestmentController implements IInvestmentController {
  /**
   * Map containing known commands and line of action for those commands in the form of
   * Function
   * object.
   */
  private Map<String, Function<String, StockInvestmentCommand>> commands;
  private IInvestmentView view;
  private StockInvestmentStrategies model;
  private boolean restore = true;

  /**
   * Represents constructor of this class. It initialises appendable and readable
   * object, scanner
   * and loads command data into the map.
   *
   * @param view  of type IInvestmentView.
   * @param model of type StockInvestmentStrategies.
   * @throws IllegalArgumentException in case the readable or appendable objects are null.
   */
  public InvestmentController(IInvestmentView view, StockInvestmentStrategies model) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("Please provide valid View or "
              + "Model object");
    }
    this.view = view;
    this.model = model;
    commands = new HashMap<>();
    commands.put("1", s -> new CreatePortfolio(view));
    commands.put("2", s -> new BuyStock(view));
    commands.put("3", s -> new DetermineCostAndValue(view));
    commands.put("4", s -> new ViewPortfolioComposition(view));
    commands.put("5", s -> new ConfigureInvestmentStrategies(view));
    if (restore) {
      model.restore("user");
    }

  }

  /**
   * Starting point of application which is responsible for taking scanner inputs and
   * delegating
   * work to the commands found the commands map. It indefinitely scans input and stops
   * executing
   * when user enters "q" or "quit".
   *
   * @throws IllegalArgumentException in case model is invalid.
   */
  @Override
  public void start() {
    if (model == null) {
      throw new IllegalArgumentException("Please provide a valid Model object");
    }
    view.transmitMessage("Welcome to Invest for Dummies Application!");
    view.transmitMessage("Following are the options:");
    view.transmitMessage("Enter 1 to Create Portfolio");
    view.transmitMessage("Enter 2 to Buy Stock for a Portfolio at a given date");
    view.transmitMessage("Enter 3 to Find cost and value of a portfolio for a given date");
    view.transmitMessage("Enter 4 to View Portfolio Composition");
    view.transmitMessage("Enter 5 to Try out different investment strategies");
    view.transmitMessage("Enter '#' to return to the main menu");
    view.transmitMessage("Enter 'q' or 'quit' to exit");
    StockInvestmentCommand command;
    while (view.hasNextInput()) {
      String in = view.read();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        view.transmitMessage("Do you want to save? Y/N");
        in = view.read();
        if (in.equalsIgnoreCase("Y")) {
          model.save("user");
        }
        return;
      }
      Function<String, StockInvestmentCommand> cmd =
              commands.getOrDefault(in, null);
      if (cmd != null) {
        command = cmd.apply(in);
        command.execute(model);
        view.transmitMessage("You are in Main Menu. Select option number to continue or"
                + " q to quit");
      } else {
        view.transmitMessage("Please enter correct option");
      }
    }
  }
}
