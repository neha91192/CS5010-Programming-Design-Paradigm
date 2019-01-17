package howtoinvestfordummies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides an implementation for common activities related to all the type of investments. This
 * includes creating Portfolios, examining its content, getting all the portfolios, and checking
 * portfolio value and cost at certain period.
 */
public class InvestmentStrategiesModel implements InvestmentStrategies {
  /**
   * Stores portfolios created by the user by keeping them as key value pair where portfolio name is
   * the key and {@link IPortfolio} object is the value.
   */
  protected Map<String, IPortfolio> portfolios;

  /**
   * Default constructor which is responsible for initializing the data structure for storing
   * portfolios.
   */
  public InvestmentStrategiesModel() {
    portfolios = new HashMap<>();
  }


  /**
   * Creates a new portfolio for a given unique name. This portfolio objects contain zero
   * investments.If the portfolio is already present, it throws appropriate message with the
   * exception.
   *
   * @param portfolioName in String required for creating portfolio.
   * @return portfolio object which is a newly created portfolio object.
   * @throws Exception in case a portfolio already exists for the portfolio name provided.
   */
  @Override
  public IPortfolio createPortfolio(String portfolioName) throws Exception {
    if (isPortfolioPresent(portfolioName)) {
      throw new Exception("Portfolio with this name is already present");
    }
    IPortfolio portfolio = new Portfolio(portfolioName);
    portfolios.put(portfolioName, portfolio);
    return portfolio;
  }

  /**
   * Returns a list containing all the portfolios created by the user. This method ensures that the
   * original list is not modified. It finds all portfolios created by the user and returns the list
   * of type {@link IPortfolio}
   *
   * @return portfolioList containing details of each portfolio.
   */
  @Override
  public List<IPortfolio> getPortfolios() {
    List<IPortfolio> portfolioList = new ArrayList<>();
    for (String portfolioName : portfolios.keySet()) {
      IPortfolio portfolio = portfolios.get(portfolioName);
      if (portfolio.getPortfolioData().getType().equals(PortfolioType.SIMPLE)) {
        portfolioList.add(new Portfolio(portfolioName,
                portfolios.get(portfolioName).getInvestments()));
      } else if (portfolio.getPortfolioData().getType().equals(PortfolioType.RECURRING)) {
        PortfolioData data = portfolio.getPortfolioData();
        portfolioList.add(
                new RecurringPortfolio(data.getPortfolioName(), data.getFrequencyValue(),
                        data.getTransactionStartDate(), data.getTransactionEndDate(),
                        portfolios.get(portfolioName).getInvestments()));
      }
    }

    return portfolioList;
  }

  /**
   * Returns a new instance of the requested Portfolio object. If the portfolio is not found, it
   * throws appropriate message.
   *
   * @param portfolioName name of the portfolio for which user wants to check details.
   * @throws IllegalArgumentException in case requested portfolio was not found.
   */
  @Override
  public IPortfolio viewPortfolio(String portfolioName) {
    if (!isPortfolioPresent(portfolioName)) {
      throw new IllegalArgumentException("Portfolio not found");
    }
    IPortfolio existingPortfolio = portfolios.get(portfolioName);
    if (existingPortfolio.getPortfolioData().getType().equals(PortfolioType.SIMPLE)) {
      return new Portfolio(existingPortfolio.getName(), existingPortfolio.getInvestments());
    } else {
      return new RecurringPortfolio(existingPortfolio.getPortfolioData().getPortfolioName(),
              existingPortfolio.getPortfolioData().getFrequencyValue(),
              existingPortfolio.getPortfolioData().getTransactionStartDate(),
              existingPortfolio.getPortfolioData().getTransactionEndDate(),
              portfolios.get(portfolioName).getInvestments());
    }
  }

  /**
   * For any given date, it finds the investment value and cost of the portfolio. The date can be
   * back-dated or the one where no trading happened. For such dates, this method checks for the
   * closing value and returns total investment cost and value of the user for this particular
   * portfolio.
   *
   * @param date          for which portfolio needs to be checked.
   * @param portfolioName in String for which investment needs to be checked.
   * @throws IllegalArgumentException in case the requested portfolio was not found.
   */
  @Override
  public String checkPortfolioStatus(Date date, String portfolioName) throws
          IllegalArgumentException {
    if (!isPortfolioPresent(portfolioName)) {
      throw new IllegalArgumentException("Portfolio not found");
    }
    IPortfolio portfolio = portfolios.get(portfolioName);
    double cost = portfolio.getCostBasis(date);
    double value = portfolio.getValue(date);
    return String.format("\nTotal Portfolio Investment Cost: %.2f USD"
            + "\nTotal Portfolio Investment Value: %.2f USD", cost, value);
  }


  /**
   * This method first gets the implementation of the required strategy and then calls the
   * respective method to initiate high level investment. Then it updates the portfolio list of this
   * class and returns the configured portfolio for this type of investment.
   *
   * @param transaction containing details required for high level transaction.
   * @param type        in InvestmentStrategyType.
   * @return portfolio object created by this method.
   */
  @Override
  public IPortfolio initiateHighLevelInvestment(Transaction transaction,
                                                InvestmentStrategyType type)
          throws IllegalArgumentException {
    HighLevelInvestmentStrategy strategy = HighLevelInvestmentFactory
            .getHighLevelStrategy(type.name());
    IPortfolio multiPortfolio = strategy.initiate(transaction);
    portfolios.put(transaction.getPortfolioName(), multiPortfolio);
    return multiPortfolio;
  }

  /**
   * Returns list of High Level Investment strategy portfolios supported by this application.
   *
   * @return list containing investment strategies.
   */
  @JsonIgnore
  @Override
  public List<InvestmentStrategyType> getHighLevelInvestments() {
    return Arrays.asList(InvestmentStrategyType.values());
  }


  /**
   * Helper method to check if the portfolio for the given name exists or not.
   *
   * @param portfolioName in String.
   * @return true if portfolio of that name is present, otherwise false.
   */
  protected boolean isPortfolioPresent(String portfolioName) {
    return portfolios.containsKey(portfolioName);
  }

  /**
   * Takes name of the file where data needs to be saved and uses {@link FileStrategy} to save the
   * data.
   *
   * @param name of the file to be saved.
   */
  @Override
  public void save(String name) {
    IPersistenceStrategy strategy = PersistanceStrategyFactory.getPersistanceStrategy("FILE");
    strategy.save(this, name);
  }

  /**
   * Retrieves all the data from file and loads portfolios for this model.
   *
   * @param name of the file from which data needs to be retrieved.
   */
  @Override
  public void restore(String name) {
    IPersistenceStrategy strategy = PersistanceStrategyFactory.getPersistanceStrategy("FILE");
    this.portfolios = strategy.restore(name);
  }

  /**
   * Returns high level investment strategies saved in the data store.
   *
   * @param name of the data source.
   * @return list containing high level investment portfolios.
   */
  @Override
  public List<IPortfolio> retrieveInvestmentStrategies(String name) {
    IPersistenceStrategy strategy = PersistanceStrategyFactory.getPersistanceStrategy("FILE");
    List<IPortfolio> portfolios = new ArrayList<>();
    Map<String, IPortfolio> map = strategy.restore(name);
    for (IPortfolio portfolio : map.values()) {
      if (portfolio.getPortfolioData().getType().equals(PortfolioType.RECURRING)) {
        portfolios.add(portfolio);
      }
    }
    return portfolios;
  }
}
