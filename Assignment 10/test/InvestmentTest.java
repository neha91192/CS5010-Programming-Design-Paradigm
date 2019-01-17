import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import howtoinvestfordummies.model.IPortfolio;
import howtoinvestfordummies.model.InvestmentStrategies;
import howtoinvestfordummies.model.InvestmentStrategiesModel;
import howtoinvestfordummies.model.InvestmentStrategyType;
import howtoinvestfordummies.model.StockInvestmentStrategies;
import howtoinvestfordummies.model.StockInvestmentStrategiesModel;
import howtoinvestfordummies.model.Transaction;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertFalse;

public class InvestmentTest {

  private InvestmentStrategies investmentStrategies;
  private InvestmentStrategies investmentStrategiesInvalid;
  private StockInvestmentStrategies stockInvestment;
  private String portfolioCollege = "College";
  private String portfolioPFs = "ProvidentFunds";
  private String portfolioRetirement = "Retirement";
  private String portfolioMutualFunds = "MutualFunds";
  private SimpleDateFormat sdf1;
  private Date date = new Date(); // get back a Date object

  private InvestmentStrategies highLevelInvestment;
  private Transaction transaction;
  private List<String> stockSymbols = new ArrayList<>();
  private List<Double> stockWeights = new ArrayList<>();
  private String portfolioFANG = "FANG";
  private Date startDate = new Date();
  private Date endDate = new Date();
  private int frequencyInterval = 30;
  private double totalAmount = 1000.0;
  private Map<String, Double> commissionFees = new HashMap<>();

  /**
   * Sets the default object for InvestmentStrategies and StockInvestmentStrategies.
   */
  @Before
  public void setUp() {
    String dateInString = "11-09-2018 11:30:00";
    investmentStrategies = new InvestmentStrategiesModel();
    investmentStrategiesInvalid = new InvestmentStrategiesModel();
    stockInvestment = new StockInvestmentStrategiesModel();
    highLevelInvestment = new InvestmentStrategiesModel();

    try {
      investmentStrategies.createPortfolio(portfolioCollege);
      investmentStrategies.createPortfolio(portfolioRetirement);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    try {
      stockInvestment.createPortfolio(portfolioCollege);
      stockInvestment.createPortfolio(portfolioRetirement);
      stockInvestment.createPortfolio(portfolioMutualFunds);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    sdf1 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    try {
      date = sdf1.parse(dateInString);
    } catch (ParseException e) {
      fail(e.getMessage());
    }

    stockSymbols.add("FB");
    stockSymbols.add("GOOG");
    stockSymbols.add("AAPL");
    stockWeights.add(30.0);
    stockWeights.add(50.0);
    stockWeights.add(20.0);

    dateInString = "01-12-2018 00:00:00";
    try {
      startDate = sdf1.parse(dateInString);
    } catch (ParseException e) {
      fail(e.getMessage());
    }

    dateInString = "12-12-2018 00:00:00";
    try {
      endDate = sdf1.parse(dateInString);
    } catch (ParseException e) {
      fail(e.getMessage());
    }

    commissionFees.put("FB", 10.0);
    commissionFees.put("GOOG", 20.0);
    commissionFees.put("AAPL", 15.0);
  }

  // check for status of portfolio when portfolio is not created
  @Test(expected = IllegalArgumentException.class)
  public void testStatusOfPortfolioNotCreated() {
    investmentStrategiesInvalid.checkPortfolioStatus(new Date(), portfolioCollege);
  }

  // view a portfolio when portfolio is not created
  @Test(expected = IllegalArgumentException.class)
  public void testViewPortfolioNotCreated() {
    investmentStrategiesInvalid.viewPortfolio(portfolioCollege);
  }

  // create one or more portfolios, keep them empty and check.
  @Test
  public void testCreateEmptyPortfolios() {

    List<String> expectedPortfolioNames = new ArrayList<>();
    expectedPortfolioNames.add(portfolioCollege);
    expectedPortfolioNames.add(portfolioRetirement);

    List<IPortfolio> portfolioList = investmentStrategies.getPortfolios();
    List<String> actualPortfolioNames = new ArrayList<>();
    for (IPortfolio portfolio : portfolioList) {
      actualPortfolioNames.add(portfolio.getName());
      assertEquals(0.0, portfolio.getCostBasis(new Date()));
      assertEquals(0.0, portfolio.getValue(new Date()));
      assertTrue(portfolio.getInvestments().isEmpty());
    }

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 0.00 USD"
            + "\nTotal Portfolio Investment Value: 0.00 USD";
    assertEquals(expectedPortfolioStatus,
            investmentStrategies.checkPortfolioStatus(new Date(), portfolioCollege));
    assertEquals(expectedPortfolioStatus,
            investmentStrategies.checkPortfolioStatus(new Date(), portfolioRetirement));

    assertEquals(expectedPortfolioNames, actualPortfolioNames);
  }

  // create one or more portfolios, buy shares of stocks for particular portfolio
  // and check for cost basis and total value for the portfolio
  @Test
  public void testBuySharesForStockInPortfolio() {
    stockInvestment.buySharesOfStock(date, 1050.00, "GOOG", portfolioCollege);

    for (IPortfolio portfolio : stockInvestment.getPortfolios()) {
      if (portfolio.getName().equals(portfolioCollege)) {
        assertEquals(1050.0, portfolio.getCostBasis(date));
        assertEquals(1066.15, portfolio.getValue(date));
        assertFalse(portfolio.getInvestments().isEmpty());
      } else {
        assertEquals(0.0, portfolio.getCostBasis(date));
        assertEquals(0.0, portfolio.getValue(date));
        assertTrue(portfolio.getInvestments().isEmpty());
      }
    }

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 1050.00 USD"
            + "\nTotal Portfolio Investment Value: 1066.15 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(date, portfolioCollege));
  }

  // create one or more portfolios, buy shares of multiple stocks for multiple
  // portfolios and check for cost basis and total value for the portfolio
  // get total value of a portfolio on working day (9-4) for a certain date
  @Test
  public void testBuySharesForMultipleStocksInMultiplePortfolios() {

    String dateInString1 = "11-09-2018 11:30:00";
    String dateInString2 = "10-16-2018 14:33:00";
    String dateInString3 = "09-28-2018 09:05:00";
    String dateInString4 = "09-28-2015 12:05:00";

    Date date1 = new Date(); // get back a Date object
    Date date2 = new Date();
    Date date3 = new Date();
    Date date4 = new Date();

    try {
      date1 = sdf1.parse(dateInString1);
      date2 = sdf1.parse(dateInString2);
      date3 = sdf1.parse(dateInString3);
      date4 = sdf1.parse(dateInString4);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    stockInvestment.buySharesOfStock(date1, 1050.00, "GOOG", portfolioCollege);
    stockInvestment.buySharesOfStock(date2, 2110.05, "FB", portfolioCollege);
    stockInvestment.buySharesOfStock(date3, 2999.59, "SBUX", portfolioRetirement);
    stockInvestment.buySharesOfStock(date4, 599.99, "AAPL", portfolioMutualFunds);

    List<IPortfolio> portfolioList = stockInvestment.getPortfolios();
    for (IPortfolio portfolio : portfolioList) {
      if (portfolio.getName().equals(portfolioCollege)) {
        assertEquals(3160.05, portfolio.getCostBasis(date1));
        assertEquals(3039.0600000000004, portfolio.getValue(date1));
        assertFalse(portfolio.getInvestments().isEmpty());
      } else if (portfolio.getName().equals(portfolioRetirement)) {
        assertEquals(2999.59, portfolio.getCostBasis(date1));
        assertEquals(3624.14, portfolio.getValue(date1));
        assertFalse(portfolio.getInvestments().isEmpty());
      } else {
        assertEquals(599.99, portfolio.getCostBasis(date1));
        assertEquals(1091.87, portfolio.getValue(date1));
        assertFalse(portfolio.getInvestments().isEmpty());
      }
    }

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 3160.05 USD"
            + "\nTotal Portfolio Investment Value: 3039.06 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(date1, portfolioCollege));

    expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 2999.59 USD\n"
            + "Total Portfolio Investment Value: 3624.14 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(date1, portfolioRetirement));

    expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 599.99 USD\n"
            + "Total Portfolio Investment Value: 1091.87 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(date1, portfolioMutualFunds));
  }

  // buy shares for a portfolio that does not exist, invalid
  @Test(expected = IllegalArgumentException.class)
  public void testBuySharesForPortfolioNotCreatedInvalid() {
    stockInvestment.buySharesOfStock(date, 599.99, "AAPL", portfolioPFs);
  }

  // Valid amount in buy shares of a stock of a portfolio
  @Test
  public void testBuySharesForStockValidAmount() {
    stockInvestment.buySharesOfStock(date, 1050.00, "GOOG", portfolioCollege);

    List<IPortfolio> portfolioList = stockInvestment.getPortfolios();
    for (IPortfolio portfolio : portfolioList) {
      if (portfolio.getName().equals(portfolioCollege)) {
        assertEquals(1050.0, portfolio.getCostBasis(date));
        assertEquals(1066.15, portfolio.getValue(date));
        assertFalse(portfolio.getInvestments().isEmpty());
      }
    }

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 1050.00 USD\n"
            + "Total Portfolio Investment Value: 1066.15 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(date, portfolioCollege));
  }

  // Invalid amount in buy shares of a stock of a portfolio
  @Test(expected = IllegalArgumentException.class)
  public void testBuySharesForStockInvalidAmount() {
    stockInvestment.buySharesOfStock(date, -1050.00, "GOOG", portfolioCollege);
  }

  // Buy shares of a stock of a portfolio for a future date which is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testBuySharesForStockFutureDate() {
    String futureDate = "12-20-2018 13:15:00";
    Date fDate = new Date(); // get back a Date object
    try {
      fDate = sdf1.parse(futureDate);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    stockInvestment.buySharesOfStock(fDate, 1050.00, "GOOG", portfolioCollege);
  }

  // Buy shares of a stock of a portfolio after business hours, invalid
  @Test(expected = IllegalArgumentException.class)
  public void testBuySharesForStockAfterHours() {
    String afterHours = "10-18-2018 8:05:00";
    Date aHours = new Date(); // get back a Date object
    try {
      aHours = sdf1.parse(afterHours);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    stockInvestment.buySharesOfStock(aHours, 1050.00, "GOOG", portfolioCollege);
  }

  // Buy shares of a stock of a portfolio on weekend, invalid
  @Test(expected = IllegalArgumentException.class)
  public void testBuySharesForStockOnWeekend() {
    String weekendDate = "10-20-2018 13:05:00";
    Date wDate = new Date(); // get back a Date object
    try {
      wDate = sdf1.parse(weekendDate);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    stockInvestment.buySharesOfStock(wDate, 1050.00, "GOOG", portfolioCollege);
  }

  // Buy shares of a stock of a portfolio, negative commission
  @Test(expected = IllegalArgumentException.class)
  public void testBuySharesForStockNegativeCommission() {
    String sDate = "11-09-2018 11:05:00";
    Date date = new Date(); // get back a Date object
    try {
      date = sdf1.parse(sDate);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    stockInvestment.buySharesOfStock(date, 1050.00, "GOOG", portfolioCollege);
    stockSymbols = new ArrayList<>();
    stockSymbols.add("GOOG");
    commissionFees = new HashMap<>();
    commissionFees.put("GOOG", -5.0);
    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioCollege)
            .amount(1000.0)
            .transactionStartDate(date)
            .transactionEndDate(date)
            .commissionFees(commissionFees)
            .build();
    stockInvestment.buyStocksWithCommission(transaction);
  }

  // get total cost basis of a portfolio at a date before purchase
  @Test
  public void testCostBasisForDateBeforePurchase() {

    String dateInString1 = "11-09-2018 11:30:00";
    String dateInString2 = "10-16-2018 14:33:00";
    String dateInString3 = "09-28-2018 09:05:00";
    String dateBeforePurchase = "09-28-2015 12:05:00";

    Date date1 = new Date(); // get back a Date object
    Date date2 = new Date();
    Date date3 = new Date();
    Date dateBeforeP = new Date();

    try {
      date1 = sdf1.parse(dateInString1);
      date2 = sdf1.parse(dateInString2);
      date3 = sdf1.parse(dateInString3);
      dateBeforeP = sdf1.parse(dateBeforePurchase);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    stockInvestment.buySharesOfStock(date1, 1050.00, "GOOG", portfolioCollege);
    stockInvestment.buySharesOfStock(date2, 2110.05, "FB", portfolioCollege);
    stockInvestment.buySharesOfStock(date3, 2999.59, "SBUX", portfolioRetirement);

    List<IPortfolio> portfolioList = stockInvestment.getPortfolios();
    for (IPortfolio portfolio : portfolioList) {
      if (portfolio.getName().equals(portfolioCollege)) {
        assertEquals(0.0, portfolio.getCostBasis(dateBeforeP));
        assertEquals(0.0, portfolio.getValue(dateBeforeP));
        assertFalse(portfolio.getInvestments().isEmpty());
      } else if (portfolio.getName().equals(portfolioRetirement)) {
        assertEquals(2999.59, portfolio.getCostBasis(date2));
        assertEquals(3054.1, portfolio.getValue(date2));
        assertFalse(portfolio.getInvestments().isEmpty());
      }
    }

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 0.00 USD"
            + "\nTotal Portfolio Investment Value: 0.00 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(dateBeforeP, portfolioCollege));

    expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 2999.59 USD"
            + "\nTotal Portfolio Investment Value: 3054.10 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(date2, portfolioRetirement));
  }

  // get total value of a portfolio on working day after 4 (close value)

  @Test
  public void testTotalValueForDateAfterHours() {

    String dateInString1 = "11-09-2018 11:30:00";
    String dateInString2 = "10-16-2018 14:33:00";
    String dateInString3 = "09-28-2018 09:05:00";
    String dateAfterHours = "11-14-2018 20:05:00";

    Date date1 = new Date(); // get back a Date object
    Date date2 = new Date();
    Date date3 = new Date();
    Date dateAfterH = new Date();

    try {
      date1 = sdf1.parse(dateInString1);
      date2 = sdf1.parse(dateInString2);
      date3 = sdf1.parse(dateInString3);
      dateAfterH = sdf1.parse(dateAfterHours);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    stockInvestment.buySharesOfStock(date1, 1050.00, "GOOG", portfolioCollege);
    stockInvestment.buySharesOfStock(date2, 2110.05, "FB", portfolioCollege);
    stockInvestment.buySharesOfStock(date3, 2999.59, "SBUX", portfolioRetirement);

    List<IPortfolio> portfolioList = stockInvestment.getPortfolios();
    for (IPortfolio portfolio : portfolioList) {
      if (portfolio.getName().equals(portfolioCollege)) {
        assertEquals(3160.05, portfolio.getCostBasis(dateAfterH));
        assertEquals(3006.49, portfolio.getValue(dateAfterH));
        assertFalse(portfolio.getInvestments().isEmpty());
      } else if (portfolio.getName().equals(portfolioRetirement)) {
        assertEquals(2999.59, portfolio.getCostBasis(dateAfterH));
        assertEquals(3541.72, portfolio.getValue(dateAfterH));
        assertFalse(portfolio.getInvestments().isEmpty());
      }
    }

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 3160.05 USD"
            + "\nTotal Portfolio Investment Value: 3006.49 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(dateAfterH, portfolioCollege));

    expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 2999.59 USD"
            + "\nTotal Portfolio Investment Value: 3541.72 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(dateAfterH, portfolioRetirement));
  }

  //assignment 9

  //create multiportfolio
  @Test
  public void testCreateEmptyMultiPortfolio() {

    highLevelInvestment = new InvestmentStrategiesModel();
    try {
      highLevelInvestment.createPortfolio(portfolioFANG);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    List<String> expectedPortfolioNames = new ArrayList<>();
    expectedPortfolioNames.add(portfolioFANG);

    List<IPortfolio> portfolioList = highLevelInvestment.getPortfolios();
    List<String> actualPortfolioNames = new ArrayList<>();
    for (IPortfolio portfolio : portfolioList) {
      actualPortfolioNames.add(portfolio.getName());
      assertEquals(0.0, portfolio.getCostBasis(new Date()));
      assertEquals(0.0, portfolio.getValue(new Date()));
      assertTrue(portfolio.getInvestments().isEmpty());
    }

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 0.00 USD"
            + "\nTotal Portfolio Investment Value: 0.00 USD";
    assertEquals(expectedPortfolioStatus,
            highLevelInvestment.checkPortfolioStatus(new Date(), portfolioFANG));

    assertEquals(expectedPortfolioNames, actualPortfolioNames);
  }

  //check portfolio value for multiportfolio with end date
  @Test
  public void testCheckMultiPortfolioEndDate() {

    try {
      highLevelInvestment.createPortfolio(portfolioFANG);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    List<String> expectedPortfolioNames = new ArrayList<>();
    expectedPortfolioNames.add(portfolioFANG);

    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioFANG)
            .amount(totalAmount)
            .stockWeights(stockWeights)
            .transactionStartDate(startDate)
            .transactionEndDate(endDate)
            .frequencyValue(frequencyInterval)
            .commissionFees(commissionFees)
            .build();
    IPortfolio highPortfolio = highLevelInvestment.initiateHighLevelInvestment(transaction,
            InvestmentStrategyType.DOLLAR_COST_AVERAGING);

    assertEquals(11495.0, highPortfolio.getCostBasis(new Date()));
    assertEquals(39841.18, highPortfolio.getValue(new Date()));

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 11495.00 USD"
            + "\nTotal Portfolio Investment Value: 39841.18 USD";
    assertEquals(expectedPortfolioStatus,
            highLevelInvestment.checkPortfolioStatus(new Date(), portfolioFANG));

    expectedPortfolioStatus = "Portfolio Name: FANG\n"
            + "1. FB\n"
            + "Percentage Weight:30.0%\n"
            + "2. GOOG\n"
            + "Percentage Weight:50.0%\n"
            + "3. AAPL\n"
            + "Percentage Weight:20.0%\n"
            + "Investment Cost: 1045.0\n"
            + "Investment Start Date: Fri Jan 12 00:00:00 EST 2018\n"
            + "Investment End Date: Wed Dec 12 00:00:00 EST 2018\n"
            + "Investment Frequency in Days: 30";
    assertEquals(expectedPortfolioStatus, highPortfolio.toString());
  }

  //check portfolio value for multiportfolio without end date
  @Test
  public void testCheckMultiPortfolioWithoutEndDate() {

    try {
      highLevelInvestment.createPortfolio(portfolioFANG);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    List<String> expectedPortfolioNames = new ArrayList<>();
    expectedPortfolioNames.add(portfolioFANG);

    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioFANG)
            .amount(totalAmount)
            .stockWeights(stockWeights)
            .transactionStartDate(startDate)
            .transactionEndDate(null)
            .frequencyValue(frequencyInterval)
            .commissionFees(commissionFees)
            .build();
    IPortfolio highPortfolio = highLevelInvestment.initiateHighLevelInvestment(transaction,
            InvestmentStrategyType.DOLLAR_COST_AVERAGING);


    String dateInString1 = "08-09-2018 11:30:00";
    Date date1 = new Date(); // get back a Date object

    try {
      date1 = sdf1.parse(dateInString1);
    } catch (ParseException e) {
      fail(e.getMessage());
    }

    assertEquals(7315.0, highPortfolio.getCostBasis(date1));
    assertEquals(7715.27, highPortfolio.getValue(date1));

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 7315.00 USD"
            + "\nTotal Portfolio Investment Value: 7715.27 USD";
    assertEquals(expectedPortfolioStatus,
            highLevelInvestment.checkPortfolioStatus(date1, portfolioFANG));

    expectedPortfolioStatus = "Portfolio Name: FANG\n"
            + "1. FB\n"
            + "Percentage Weight:30.0%\n"
            + "2. GOOG\n"
            + "Percentage Weight:50.0%\n"
            + "3. AAPL\n"
            + "Percentage Weight:20.0%\n"
            + "Investment Cost: 1045.0\n"
            + "Investment Start Date: Fri Jan 12 00:00:00 EST 2018\n"
            + "Investment End Date: ONGOING\n"
            + "Investment Frequency in Days: 30";
    assertEquals(expectedPortfolioStatus, highPortfolio.toString());
  }

  //check portfolio value for multiportfolio for equal weights, multiple dates
  @Test
  public void testCheckMultiPortfolioPresetWeights() {

    try {
      highLevelInvestment.createPortfolio(portfolioFANG);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    List<String> expectedPortfolioNames = new ArrayList<>();
    expectedPortfolioNames.add(portfolioFANG);

    stockWeights = new ArrayList<>();

    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioFANG)
            .amount(totalAmount)
            .stockWeights(stockWeights)
            .transactionStartDate(startDate)
            .transactionEndDate(endDate)
            .frequencyValue(frequencyInterval)
            .commissionFees(commissionFees)
            .build();
    IPortfolio highPortfolio = highLevelInvestment.initiateHighLevelInvestment(transaction,
            InvestmentStrategyType.DOLLAR_COST_AVERAGING);

    String dateInString1 = "08-09-2018 11:30:00";
    Date date1 = new Date(); // get back a Date object
    String dateInString2 = "09-09-2018 11:30:00";
    Date date2 = new Date();
    String dateInString3 = "10-09-2018 11:30:00";
    Date date3 = new Date();

    try {
      date1 = sdf1.parse(dateInString1);
      date2 = sdf1.parse(dateInString2);
      date3 = sdf1.parse(dateInString3);
    } catch (ParseException e) {
      fail(e.getMessage());
    }

    assertEquals(7314.3, highPortfolio.getCostBasis(date1));
    assertEquals(0.0, highPortfolio.getValue(date1));
    assertEquals(9404.1, highPortfolio.getCostBasis(date2));
    assertEquals(0.0, highPortfolio.getValue(date2));
    assertEquals(10449.0, highPortfolio.getCostBasis(date3));
    assertEquals(0.0, highPortfolio.getValue(date3));

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 7314.30 USD"
            + "\nTotal Portfolio Investment Value: 0.0 USD";
    assertEquals(expectedPortfolioStatus,
            highLevelInvestment.checkPortfolioStatus(date1, portfolioFANG));
    expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 9404.1 USD"
            + "\nTotal Portfolio Investment Value: 0.0 USD";
    assertEquals(expectedPortfolioStatus,
            highLevelInvestment.checkPortfolioStatus(date1, portfolioFANG));
    expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 10449.0 USD"
            + "\nTotal Portfolio Investment Value: 0.0 USD";
    assertEquals(expectedPortfolioStatus,
            highLevelInvestment.checkPortfolioStatus(date1, portfolioFANG));

    expectedPortfolioStatus = "Portfolio Name: FANG\n"
            + "1. FB\n"
            + "Percentage Weight:33.33%\n"
            + "2. GOOG\n"
            + "Percentage Weight:33.33%\n"
            + "3. AAPL\n"
            + "Percentage Weight:33.33%\n"
            + "Investment Cost: 1044.9\n"
            + "Investment Start Date: Fri Jan 12 00:00:00 EST 2018\n"
            + "Investment End Date: Wed Dec 12 00:00:00 EST 2018\n"
            + "Investment Frequency in Days: 30";
    assertEquals(expectedPortfolioStatus, highPortfolio.toString());
  }

  //check portfolio value for multiportfolio for different weights
  @Test
  public void testCheckMultiPortfolioDiffWeights() {

    try {
      highLevelInvestment.createPortfolio(portfolioFANG);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    List<String> expectedPortfolioNames = new ArrayList<>();
    expectedPortfolioNames.add(portfolioFANG);

    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioFANG)
            .amount(totalAmount)
            .stockWeights(stockWeights)
            .transactionStartDate(startDate)
            .transactionEndDate(endDate)
            .frequencyValue(frequencyInterval)
            .commissionFees(commissionFees)
            .build();
    IPortfolio highPortfolio = highLevelInvestment.initiateHighLevelInvestment(transaction,
            InvestmentStrategyType.DOLLAR_COST_AVERAGING);

    String dateInString1 = "03-12-2018 11:30:00";
    Date date1 = new Date(); // get back a Date object

    try {
      date1 = sdf1.parse(dateInString1);
    } catch (ParseException e) {
      fail(e.getMessage());
    }

    assertEquals(2090.0, highPortfolio.getCostBasis(date1));
    assertEquals(2143.57, highPortfolio.getValue(date1));

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 2090.00 USD"
            + "\nTotal Portfolio Investment Value: 2143.57 USD";
    assertEquals(expectedPortfolioStatus,
            highLevelInvestment.checkPortfolioStatus(date1, portfolioFANG));

    expectedPortfolioStatus = "Portfolio Name: FANG\n"
            + "1. FB\n"
            + "Percentage Weight:30.0%\n"
            + "2. GOOG\n"
            + "Percentage Weight:50.0%\n"
            + "3. AAPL\n"
            + "Percentage Weight:20.0%\n"
            + "Investment Cost: 1045.0\n"
            + "Investment Start Date: Fri Jan 12 00:00:00 EST 2018\n"
            + "Investment End Date: Wed Dec 12 00:00:00 EST 2018\n"
            + "Investment Frequency in Days: 30";
    assertEquals(expectedPortfolioStatus, highPortfolio.toString());
  }

  //check portfolio value for multiportfolio where transaction may happen on holidays
  @Test
  public void testCheckMultiPortfolioHoliday() {

    try {
      highLevelInvestment.createPortfolio(portfolioFANG);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    List<String> expectedPortfolioNames = new ArrayList<>();
    expectedPortfolioNames.add(portfolioFANG);

    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioFANG)
            .amount(totalAmount)
            .stockWeights(stockWeights)
            .transactionStartDate(startDate)
            .transactionEndDate(endDate)
            .frequencyValue(frequencyInterval)
            .commissionFees(commissionFees)
            .build();
    IPortfolio highPortfolio = highLevelInvestment.initiateHighLevelInvestment(transaction,
            InvestmentStrategyType.DOLLAR_COST_AVERAGING);

    assertEquals(11495.0, highPortfolio.getCostBasis(new Date()));
    assertEquals(10010.119999999999, highPortfolio.getValue(new Date()));

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 11495.00 USD"
            + "\nTotal Portfolio Investment Value: 10010.12 USD";
    assertEquals(expectedPortfolioStatus,
            highLevelInvestment.checkPortfolioStatus(new Date(), portfolioFANG));

    expectedPortfolioStatus = "Portfolio Name: FANG\n"
            + "1. FB\n"
            + "Percentage Weight:30.0%\n"
            + "2. GOOG\n"
            + "Percentage Weight:50.0%\n"
            + "3. AAPL\n"
            + "Percentage Weight:20.0%\n"
            + "Investment Cost: 1045.0\n"
            + "Investment Start Date: Fri Jan 12 00:00:00 EST 2018\n"
            + "Investment End Date: Wed Dec 12 00:00:00 EST 2018\n"
            + "Investment Frequency in Days: 30";
    assertEquals(expectedPortfolioStatus, highPortfolio.toString());
  }

  //buy stock without commission fee
  @Test
  public void testCheckMultiPortfolioNoCommission() {

    try {
      highLevelInvestment.createPortfolio(portfolioFANG);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    List<String> expectedPortfolioNames = new ArrayList<>();
    expectedPortfolioNames.add(portfolioFANG);

    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioFANG)
            .amount(totalAmount)
            .stockWeights(stockWeights)
            .transactionStartDate(startDate)
            .transactionEndDate(endDate)
            .frequencyValue(frequencyInterval)
            .build();
    IPortfolio highPortfolio = highLevelInvestment.initiateHighLevelInvestment(transaction,
            InvestmentStrategyType.DOLLAR_COST_AVERAGING);

    assertEquals(11000.0, highPortfolio.getCostBasis(new Date()));
    assertEquals(10010.119999999999, highPortfolio.getValue(new Date()));

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 11000.00 USD"
            + "\nTotal Portfolio Investment Value: 10010.12 USD";
    assertEquals(expectedPortfolioStatus,
            highLevelInvestment.checkPortfolioStatus(new Date(), portfolioFANG));

    expectedPortfolioStatus = "Portfolio Name: FANG\n"
            + "1. FB\n"
            + "Percentage Weight:30.0%\n"
            + "2. GOOG\n"
            + "Percentage Weight:50.0%\n"
            + "3. AAPL\n"
            + "Percentage Weight:20.0%\n"
            + "Investment Cost: 1000.0\n"
            + "Investment Start Date: Fri Jan 12 00:00:00 EST 2018\n"
            + "Investment End Date: Wed Dec 12 00:00:00 EST 2018\n"
            + "Investment Frequency in Days: 30";
    assertEquals(expectedPortfolioStatus, highPortfolio.toString());
  }

  //create multiple portfolios with added commission
  @Test
  public void testMultiplePortfolios() {

    try {
      highLevelInvestment.createPortfolio(portfolioFANG);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioFANG)
            .amount(totalAmount)
            .stockWeights(stockWeights)
            .transactionStartDate(startDate)
            .transactionEndDate(endDate)
            .frequencyValue(frequencyInterval)
            .commissionFees(commissionFees)
            .build();
    IPortfolio highPortfolio = highLevelInvestment.initiateHighLevelInvestment(transaction,
            InvestmentStrategyType.DOLLAR_COST_AVERAGING);

    assertEquals(11495.0, highPortfolio.getCostBasis(new Date()));
    assertEquals(10010.119999999999, highPortfolio.getValue(new Date()));

    String dateInString1 = "11-09-2018 11:30:00";
    String dateInString2 = "10-16-2018 14:33:00";
    String dateInString3 = "09-28-2018 09:05:00";
    String dateAfterHours = "11-14-2018 20:05:00";

    Date date1 = new Date(); // get back a Date object
    Date date2 = new Date();
    Date date3 = new Date();
    Date dateAfterH = new Date();

    try {
      date1 = sdf1.parse(dateInString1);
      date2 = sdf1.parse(dateInString2);
      date3 = sdf1.parse(dateInString3);
      dateAfterH = sdf1.parse(dateAfterHours);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    stockInvestment.buySharesOfStock(date1, 1050.00, "GOOG", portfolioCollege);
    stockInvestment.buySharesOfStock(date2, 2110.05, "FB", portfolioCollege);
    stockInvestment.buySharesOfStock(date3, 2999.59, "SBUX", portfolioRetirement);
    stockSymbols = new ArrayList<>();
    stockSymbols.add("SBUX");
    commissionFees = new HashMap<>();
    commissionFees.put("SBUX", 10.0);
    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioRetirement)
            .amount(2999.59)
            .transactionStartDate(date1)
            .commissionFees(commissionFees)
            .build();
    stockInvestment.buyStocksWithCommission(transaction);

    List<IPortfolio> portfolioList = stockInvestment.getPortfolios();
    for (IPortfolio portfolio : portfolioList) {
      if (portfolio.getName().equals(portfolioCollege)) {
        assertEquals(3160.05, portfolio.getCostBasis(dateAfterH));
        assertEquals(3006.49, portfolio.getValue(dateAfterH));
        assertFalse(portfolio.getInvestments().isEmpty());
      } else if (portfolio.getName().equals(portfolioRetirement)) {
        assertEquals(5999.18, portfolio.getCostBasis(dateAfterH));
        assertEquals(6495.5, portfolio.getValue(dateAfterH));
        assertFalse(portfolio.getInvestments().isEmpty());
      }
    }

    String expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 3160.05 USD"
            + "\nTotal Portfolio Investment Value: 3006.49 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(dateAfterH, portfolioCollege));

    expectedPortfolioStatus = "\nTotal Portfolio Investment Cost: 5999.18 USD"
            + "\nTotal Portfolio Investment Value: 6495.50 USD";
    assertEquals(expectedPortfolioStatus,
            stockInvestment.checkPortfolioStatus(dateAfterH, portfolioRetirement));

    expectedPortfolioStatus = "Portfolio Name: FANG\n"
            + "1. FB\n"
            + "Percentage Weight:30.0%\n"
            + "2. GOOG\n"
            + "Percentage Weight:50.0%\n"
            + "3. AAPL\n"
            + "Percentage Weight:20.0%\n"
            + "Investment Cost: 1045.0\n"
            + "Investment Start Date: Fri Jan 12 00:00:00 EST 2018\n"
            + "Investment End Date: Wed Dec 12 00:00:00 EST 2018\n"
            + "Investment Frequency in Days: 30";
    assertEquals(expectedPortfolioStatus, highPortfolio.toString());
  }

  //high level portfolio with negative commission
  @Test(expected = IllegalArgumentException.class)
  public void testHighLevelPortfoliosNegativeCommission() {

    try {
      highLevelInvestment.createPortfolio(portfolioFANG);
    } catch (Exception e) {
      fail(e.getMessage());
    }

    List<String> expectedPortfolioNames = new ArrayList<>();
    expectedPortfolioNames.add(portfolioFANG);

    commissionFees = new HashMap<>();
    commissionFees.put("FB", 10.0);
    commissionFees.put("GOOG", 15.0);
    commissionFees.put("AAPL", -20.0);

    transaction = Transaction.getBuilder()
            .stocks(stockSymbols)
            .portfolioName(portfolioFANG)
            .amount(totalAmount)
            .stockWeights(stockWeights)
            .transactionStartDate(startDate)
            .transactionEndDate(endDate)
            .commissionFees(commissionFees)
            .frequencyValue(frequencyInterval)
            .build();
    highLevelInvestment.initiateHighLevelInvestment(transaction,
            InvestmentStrategyType.DOLLAR_COST_AVERAGING);
  }
}
