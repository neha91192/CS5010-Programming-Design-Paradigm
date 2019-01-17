package howtoinvestfordummies.model;


import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

/**
 * Represents implementation for {@link IPortfolio} interface. This class provides features related
 * to operations at portfolio level which includes finding cost and value of all the investments in
 * the portfolio, list all the investments and add investment to the portfolio.
 */
public class Portfolio extends AbstractPortfolio {

  /**
   * Constructor to create portfolio object with the name and list of investments of the portfolio.
   *
   * @param portfolioName name of the portfolio in String.
   * @param investments   list containing object of type IInvestment.
   */
  public Portfolio(String portfolioName, List<IInvestment> investments) {
    super(investments);
    this.portfolioData = new PortfolioData(portfolioName, PortfolioType.SIMPLE,
            0, null, null);
  }

  @JsonGetter("data")
  @Override
  public PortfolioData getPortfolioData() {
    return getPortfolioCopy(this.portfolioData);
  }

  /**
   * Constructor to create portfolio object with the name and list of investments of the portfolio.
   *
   * @param portfolioName name of the portfolio in String.
   */
  public Portfolio(String portfolioName) {
    super(portfolioName);

  }

}
