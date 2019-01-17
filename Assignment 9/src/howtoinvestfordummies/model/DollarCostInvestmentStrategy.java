package howtoinvestfordummies.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class to support {@link DollarCostInvestmentStrategy}.Dollar Cost Investment
 * Strategy lets user passively make investment by letting user set up about new investment strategy
 * pattern and allow to system to make recurring changes as per the configuration. In future if user
 * wants to modify this configuration, an update method can be added to the {@link
 * HighLevelInvestmentStrategy} interface.
 */
public class DollarCostInvestmentStrategy extends AbstractHighLevelInvestmentStrategy {

  /**
   * This contains the logic of creating a multi-stock portfolio for HighLevelInvestment strategy
   * following Dollar Cost Averaging pattern. It takes details regarding transaction set by user and
   * configures portfolio responsible for making recurring investment. After creating the recurring
   * portfolio, it returns the object of type {@link IPortfolio}.
   */
  @Override
  public IPortfolio initiate(Transaction details) {
    List<String> stockSymbols = details.getStocks();
    List<IInvestment> recurringStocks = new ArrayList<>();
    for (int i = 0; i < stockSymbols.size(); i++) {
      double cost = details.getAmount() * details.getStockWeights().get(i) / 100;
      IInvestment investment = new Stock(stockSymbols.get(i),
              0, details.getTransactionStartDate(), cost);
      investment.setPercentageWeight(details.getStockWeights().get(i));
      investment.setCommissionFees(details.getCommissionFees()
              .getOrDefault(stockSymbols.get(i), 0.0));
      recurringStocks.add(investment);
    }
    IPortfolio multiportfolio = new RecurringPortfolio(details.getPortfolioName(),
            details.getFrequencyValue(), details.getTransactionStartDate(),
            details.getTransactionEndDate(), recurringStocks);
    for (IInvestment stock : recurringStocks) {
      multiportfolio.addInvestments(stock);
    }
    return multiportfolio;
  }
}
