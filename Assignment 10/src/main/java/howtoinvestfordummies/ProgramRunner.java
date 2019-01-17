package howtoinvestfordummies;

import howtoinvestfordummies.controller.IInvestmentController;
import howtoinvestfordummies.controller.InvestmentGUIController;
import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.model.StockInvestmentStrategiesModel;
import howtoinvestfordummies.view.IInvestmentGUIView;
import howtoinvestfordummies.view.InvestmentGUIView;

/**
 * An independent class which is responsible for running this application. It includes main method
 * which feeds View and Model object to the controller and lets controller take the charge of this
 * application.
 */
public class ProgramRunner {
  /**
   * Entry point of the application. This main method calls the {@link IInvestmentController} to
   * perform investment related activities from Graphical User Interface.
   *
   * @param args input arguments in string.
   */
//  public static void main(String[] args) {
//    if (args[0].equals("-view")) {
//      String input = args[1];
//      switch (input) {
//        case "console":
//          startConsole();
//          break;
//        case "gui":
//          startGUI();
//          break;
//        default:
//          System.out.println("Incorrect input");
//          break;
//      }
//    } else {
//      System.out.println("Exit");
//    }
//  }
//
//  /**
//   * This main method calls the {@link IInvestmentController} to perform investment related
//   * activities from the Command Line Interface.
//   */
//  private static void startConsole() {
//    StockInvestmentStrategies model =
//            new StockInvestmentStrategiesModel();
//    IInvestmentView view = new InvestmentTextView(new InputStreamReader(System.in), System.out);
//    IInvestmentController controller = new InvestmentController(view, model);
//    controller.start();
//  }
//
//  /**
//   * This main method calls the {@link howtoinvestfordummies.controller.InvestmentGUIController} to
//   * perform investment related activities from the GUI view.
//   */
//  private static void startGUI() {
//    StockInvestmentStrategies model = new StockInvestmentStrategiesModel();
//    IInvestmentGUIView view = new InvestmentGUIView("Stock Investment Application");
//    IInvestmentController controller = new InvestmentGUIController(view, model);
//    controller.start();
//  }
  public static void main(String[] args) {
    StockInvestmentStrategies model = new StockInvestmentStrategiesModel();
    IInvestmentGUIView view = new InvestmentGUIView("Stock Investment Application");
    IInvestmentController controller = new InvestmentGUIController(view, model);
    controller.start();
  }

}
