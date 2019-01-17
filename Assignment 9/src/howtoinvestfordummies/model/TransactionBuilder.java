package howtoinvestfordummies.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This is the interface for the TransactionBuilder. It is responsible for creating {@link
 * Transaction} instances. Depending upon default configuration of the implementation classes of
 * TransactionBuilder Interface, the Transaction will be initialized with appropriate values.
 */
public interface TransactionBuilder {
  /**
   * Sets List of stock symbols.
   *
   * @param stocks list of stock symbols in String
   * @return instance of builder class
   */
  TransactionBuilder stocks(List<String> stocks);

  /**
   * Sets List of stock weights.
   *
   * @param stockWeights list of stock weights in Integer
   * @return instance of builder class
   */
  TransactionBuilder stockWeights(List<Double> stockWeights);

  /**
   * Sets name of portfolio.
   *
   * @param portfolioName is the name of portfolio
   * @return instance of builder class
   */
  TransactionBuilder portfolioName(String portfolioName);

  /**
   * Sets start date of transaction.
   *
   * @param transactionStartDate start date of transaction
   * @return instance of builder class
   */
  TransactionBuilder transactionStartDate(Date transactionStartDate);

  /**
   * Sets end date of transaction.
   *
   * @param transactionEndDate end date of transaction
   * @return instance of builder class
   */
  TransactionBuilder transactionEndDate(Date transactionEndDate);

  /**
   * Sets frequency value for the transaction.
   *
   * @param frequencyValue value for recurring transaction
   * @return instance of builder class
   */
  TransactionBuilder frequencyValue(int frequencyValue);

  /**
   * Sets value of amount.
   *
   * @param amount to be invested
   * @return instance of builder class
   */
  TransactionBuilder amount(double amount);

  /**
   * Sets map of stock symbols and commission fees for respective stocks.
   *
   * @param commissionFees map of stocks symbols and commission fees
   * @return instance of builder class
   */
  TransactionBuilder commissionFees(Map<String, Double> commissionFees);

  /**
   * Build method finally returns the instance of required class. Depending upon what values were
   * given in the past got all variables, it initializes object for {@link Transaction}.
   *
   * @return transaction object of type Transaction.
   */
  Transaction build();
}
