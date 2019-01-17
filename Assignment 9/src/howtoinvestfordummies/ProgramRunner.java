package howtoinvestfordummies;

import java.io.InputStreamReader;

import howtoinvestfordummies.controller.IInvestmentController;
import howtoinvestfordummies.controller.InvestmentController;
import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.model.StockInvestmentStrategiesModel;
import howtoinvestfordummies.view.IInvestmentView;
import howtoinvestfordummies.view.InvestmentTextView;

/**
 * An independent class which is responsible for running this application. It includes main method
 * which feeds View and Model object to the controller and lets controller take the charge of this
 * application.
 */
public class ProgramRunner {
  /**
   * Entry point of the application. This main method calls the {@link IInvestmentController} to
   * perform investment related activities.
   *
   * @param args input arguments in string.
   */
  public static void main(String[] args) {
    StockInvestmentStrategies model =
            new StockInvestmentStrategiesModel();
    IInvestmentView view = new InvestmentTextView(new InputStreamReader(System.in),
            System.out) {
    };
    IInvestmentController controller = new InvestmentController(view, model);
    controller.start();
  }
}
