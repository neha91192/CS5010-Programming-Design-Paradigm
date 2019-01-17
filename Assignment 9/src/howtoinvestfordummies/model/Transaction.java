package howtoinvestfordummies.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Transfer Object class to store information about any investment related transaction.
 */
public class Transaction {

  /**
   * List of investments made in this transaction.
   */
  private List<String> stocks;

  /**
   * Weights of respective stocks.
   */
  private List<Double> stockWeights;
  /**
   * Portfolio on which this transaction is done.
   */
  private String portfolioName;
  /**
   * Start Date of this transaction.
   */
  private Date transactionStartDate;
  /**
   * End Date of this transaction.
   */
  private Date transactionEndDate;
  /**
   * Value supported for the frequency of the transaction.
   */
  private int frequencyValue;
  /**
   * Amount of investment.
   */
  private double amount;
  /**
   * Commission fee applied to this transaction.
   */
  private Map<String, Double> commissionFees;

  private Transaction(List<String> stocks, List<Double> stockWeights, String
          portfolioName,
                      Date transactionStartDate, Date transactionEndDate, int
                              frequencyValue,
                      double amount, Map<String, Double> commissionFees) {
    this.stocks = stocks;
    this.stockWeights = stockWeights;
    this.portfolioName = portfolioName;
    this.transactionEndDate = transactionEndDate;
    this.transactionStartDate = transactionStartDate;
    this.frequencyValue = frequencyValue;
    this.amount = amount;
    this.commissionFees = commissionFees;
  }

  /**
   * Returns an instance of {@link TransactionBuilder} which is the Builder of this class. This
   * builder contains all the set of methods which could initialise data of this class and also a
   * build method which returns newly created instance of {@link Transaction}.
   *
   * @return builder instance of this class.
   */
  public static TransactionBuilder getBuilder() {
    return new TransactionBuilderImpl();
  }

  public List<String> getStocks() {
    return stocks;
  }

  public List<Double> getStockWeights() {
    return stockWeights;
  }

  public Date getTransactionStartDate() {
    return transactionStartDate;
  }

  public Date getTransactionEndDate() {
    return transactionEndDate;
  }

  public int getFrequencyValue() {
    return frequencyValue;
  }

  public double getAmount() {
    return amount;
  }

  public String getPortfolioName() {
    return portfolioName;
  }

  public Map<String, Double> getCommissionFees() {
    return commissionFees;
  }


  /**
   * Builder class which returns the instance of {@link Transaction} class.
   */
  public static class TransactionBuilderImpl implements TransactionBuilder {

    /**
     * List of investments set by this Builder class.
     */
    private List<String> stocks;

    /**
     * Weights of respective stocks.
     */
    private List<Double> stockWeights;

    /**
     * Portfolio on which transaction is done.
     */
    private String portfolioName;

    /**
     * Start Date set by this Builder class.
     */
    private Date transactionStartDate;

    /**
     * End Date set by this Builder class.
     */
    private Date transactionEndDate;

    /**
     * Value supported for the frequency of transaction set by this Builder class.
     */
    private int frequencyValue;

    /**
     * Amount of investment.
     */
    private double amount;


    /**
     * Commission fee applied to transaction set by this Builder class.
     */
    private Map<String, Double> commissionFees;

    /**
     * Default constructor of this builder class. If no other method of this class is called except
     * build, the new instance of {@link Transaction} contains the values defined by this
     * constructor.
     */
    public TransactionBuilderImpl() {
      this.stocks = new ArrayList<>();
      this.stockWeights = new ArrayList<>();
      this.stockWeights.add(100.0);
      this.transactionEndDate = new Date();
      this.commissionFees = new HashMap<>();
      this.frequencyValue = 0;
    }

    @Override
    public TransactionBuilder stocks(List<String> stocks) {
      this.stocks = stocks;
      return this;
    }

    @Override
    public TransactionBuilder stockWeights(List<Double> stockWeights) {
      double presetWeight = Math.round((100.0 / stocks.size()) * 100.0) / 100.0;
      for (String stock : stocks) {
        stockWeights.add(presetWeight);
      }
      this.stockWeights = stockWeights;
      return this;
    }

    @Override
    public TransactionBuilder portfolioName(String portfolioName) {
      this.portfolioName = portfolioName;
      return this;
    }

    @Override
    public TransactionBuilder transactionStartDate(Date transactionStartDate) {
      this.transactionStartDate = transactionStartDate;
      return this;
    }

    @Override
    public TransactionBuilder transactionEndDate(Date transactionEndDate) {
      this.transactionEndDate = transactionEndDate;
      return this;
    }

    @Override
    public TransactionBuilder frequencyValue(int frequencyValue) {
      this.frequencyValue = frequencyValue;
      return this;
    }

    @Override
    public TransactionBuilder amount(double amount) {
      this.amount = amount;
      return this;
    }

    @Override
    public TransactionBuilder commissionFees(Map<String, Double> commissionFees) {
      this.commissionFees = commissionFees;
      return this;
    }

    @Override
    public Transaction build() {
      return new Transaction(stocks, stockWeights, portfolioName, transactionStartDate,
              transactionEndDate, frequencyValue, amount, commissionFees);
    }
  }
}
