package howtoinvestfordummies.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.view.IInvestmentView;

/**
 * Class to operate creation of portfolio in the model.
 */
public class CreatePortfolio extends StockInvestmentCommandImpl implements
        ActionListener {

  /**
   * Constructs object of this class.
   *
   * @param view of type IInvestmentView.
   */
  CreatePortfolio(IInvestmentView view) {
    super(view);
    this.view.setListener(this);
  }

  /**
   * This method takes model object to create portfolio with a required name. If the
   * portfolio name
   * is unique, it will allow user to create portfolio.
   *
   * @param model of type StockInvestmentStrategies interface.
   */
  @Override
  public void execute(StockInvestmentStrategies model) {
    while (true) {
      try {
        view.transmitMessage("Enter Portfolio name to create or press # to return to "
                + "the menu.");
        String portfolioName = view.read();
        if (portfolioName.equalsIgnoreCase("#")) {
          break;
        }
        model.createPortfolio(portfolioName);
        view.transmitMessage("Portfolio " + portfolioName + " created successfully!");
      } catch (Exception e) {
        view.transmitMessage(e.getMessage());
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    view.transmitMessage(e.getActionCommand());
  }
}
