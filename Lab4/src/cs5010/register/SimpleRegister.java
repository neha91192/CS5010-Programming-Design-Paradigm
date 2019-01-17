package cs5010.register;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents the implementation class for {@link CashRegister}.
 */
public class SimpleRegister implements CashRegister {

  /**
   * Represents data structure to store denomination values (in cents) as keys and corresponding
   * number of coins that are available for the dollar denomination.
   */
  private Map<Denomination, Integer> dollarRegister;
  /**
   * Represents data structure to store denomination values (in cents) as keys and corresponding
   * number of coins that are available for the cents denomination.
   */
  private Map<Denomination, Integer> centsRegister;
  /**
   * Instance to store complete log.
   */
  private StringBuilder transactionLog;
  /**
   * Constant for Deposit transaction type.
   */
  private static final String DEPOSIT = "Deposit";
  /**
   * Constant for Withdraw transaction type.
   */
  private static final String WITHDRAW = "Withdraw";

  /**
   * Represents constructor of this class responsible for creating dollarRegister and  centsRegister
   * instances. It initialises both the registers as TreeMap which essentially store its value in
   * the natural ordering of keys. To allow storage of key value pairs in decreasing order, TreeMap
   * is initialized with the Collections.reverseOrder() parameter to set is ordering to decreasing
   * order. It then inserts all types of denomination with the default value of 0 and creates an
   * instance of transactionLog.
   */
  public SimpleRegister() {
    dollarRegister = new TreeMap<>(Collections.reverseOrder());
    centsRegister = new TreeMap<>(Collections.reverseOrder());
    for (Denomination denomination : Denomination.values()) {
      if (denomination.getCategory().equals("Dollar")) {
        dollarRegister.put(denomination, 0);
      } else {
        centsRegister.put(denomination, 0);
      }
    }
    transactionLog = new StringBuilder();
  }

  /**
   * Adds pennies to the register.
   *
   * @param num number of pennies to be added.
   */
  @Override
  public void addPennies(int num) {
    addValue(Denomination.PENNY, num);
  }

  /**
   * Adds nickels to the register.
   *
   * @param num number of nickels to be added.
   */
  @Override
  public void addNickels(int num) {
    addValue(Denomination.NICKLE, num);
  }

  /**
   * Adds dimes to the register.
   *
   * @param num number of dimes to be added.
   */
  @Override
  public void addDimes(int num) {
    addValue(Denomination.DIME, num);
  }

  /**
   * Adds quarters to the register.
   *
   * @param num number of quarters to be added.
   */
  @Override
  public void addQuarters(int num) {
    addValue(Denomination.QUARTER, num);
  }

  /**
   * Adds ones to the register.
   *
   * @param num number of ones to be added.
   */
  @Override
  public void addOnes(int num) {
    addValue(Denomination.ONE, num);
  }

  /**
   * Adds fives to the register.
   *
   * @param num fives of ones to be added.
   */
  @Override
  public void addFives(int num) {
    addValue(Denomination.FIVE, num);
  }

  /**
   * Adds tens to the register.
   *
   * @param num tens of ones to be added.
   */
  @Override
  public void addTens(int num) {
    addValue(Denomination.TEN, num);
  }

  /**
   * <p>Withdraws amount given in dollar and cents. It first creates a new register instances for
   * Dollars and Cents on which operation needs to be performed. It then calls withdrawFromRegister
   * for both, dollar and cent values internally to check if the value can be withdrawn. If these
   * methods are successful, the original registers are updated and transaction is logged. If there
   * is any exception in withdrawing dollars or cents, the method immediately fails and returns
   * {@link InsufficientCashException}. It accepts dollar or cent value as maximum range of integer
   * and any value above that is considered as negative. So in case of such negative or Max range
   * inputs, it throws {@link IllegalArgumentException}.
   * </p>
   *
   * @param dollars the dollar amount to be withdrawn.
   * @param cents   the cent amount to be withdrawn.
   * @return map containing denominations in cents and total number of coins returned.
   * @throws InsufficientCashException if there is no sufficient denomination or balance.
   */
  @Override
  public Map<Integer, Integer> withdraw(int dollars, int cents) throws InsufficientCashException {
    if (dollars < 0 || cents < 0) {
      throw new IllegalArgumentException("Values cannot be negative");
    }
    Map<Integer, Integer> withdrawalAmount = new HashMap<>();
    Map<Denomination, Integer> tempRegisterForDollar = new TreeMap<>(dollarRegister);
    Map<Denomination, Integer> tempRegisterForCents = new TreeMap<>(centsRegister);

    withdrawFromRegister(tempRegisterForDollar, dollars * 100, withdrawalAmount);
    withdrawFromRegister(tempRegisterForCents, cents, withdrawalAmount);

    if (dollars > 0 || cents > 0) {
      dollarRegister = tempRegisterForDollar;
      centsRegister = tempRegisterForCents;
      writeAuditLog(WITHDRAW, dollars * 100 + cents);
    }
    return withdrawalAmount;
  }

  /**
   * <p>Takes current register from which amount needs to be dispensed and the return container map
   * called withdrawalAmount which has how many notes or coins are dispensed for a given
   * denomination. For all the denominations in register, it starts with the highest denomination,
   * and subtracts maximum possible value from each denomination for each coins of that denomination
   * unless the total required amount is not over.</p>
   * <p> If the required amount is left, that is greater than 0, it can be inferred that either
   * there is no possible denomination combination left, or the balance is insufficient and in that
   * case, it returns {@link InsufficientCashException}.</p>
   *
   * @param register representing cash storage of this register.
   * @param amount   in cents that needs to be withdrawn.
   * @throws InsufficientCashException in case amount cannot be withdrawn.
   */
  private void withdrawFromRegister(Map<Denomination, Integer> register, int amount, Map<Integer,
          Integer> withdrawalAmount) throws InsufficientCashException {

    int coinsTaken = 0;
    for (Denomination denomination : register.keySet()) {
      int noOfCoins = register.get(denomination);
      while (amount >= denomination.getValue() && noOfCoins > 0) {
        amount = amount - denomination.getValue();
        noOfCoins--;
        coinsTaken++;
      }
      register.put(denomination, noOfCoins);
      if (coinsTaken > 0) {
        withdrawalAmount.put(denomination.getValue(), coinsTaken);
        coinsTaken = 0;
      }
    }
    if (amount > 0) {
      throw new InsufficientCashException("Cannot withdraw cash as there is insufficient "
              + "denomination in the register");
    }
  }

  /**
   * Fetches the combined contents of dollarRegister and centsRegister. It returns a new instance of
   * the register to disallow any unauthorized deposit/withdraw operation on actual cash register.
   *
   * @return cash register containing keys as denominations in cents and values as number of coins.
   */
  @Override
  public Map<Integer, Integer> getContents() {
    Map<Integer, Integer> contents = new HashMap<>();
    for (Denomination denomination : dollarRegister.keySet()) {
      contents.put(denomination.getValue(), dollarRegister.get(denomination));
    }
    for (Denomination denomination : centsRegister.keySet()) {
      contents.put(denomination.getValue(), centsRegister.get(denomination));
    }
    return contents;
  }


  /**
   * Creates transaction log for a given transaction type and amount and appends it to the common
   * transaction log.
   *
   * @param transaction describing whether transaction is Deposit or Withdraw in String.
   * @param amount      of transaction represented in int.
   */
  private void writeAuditLog(String transaction, int amount) {
    String log = transaction + ": " + convertCentsToDollarFormat(amount);
    if (transactionLog.toString().equals("")) {
      transactionLog.append(log);
    } else {
      transactionLog.append('\n');
      transactionLog.append(log);
    }

  }

  /**
   * Formats the value in cents to a proper String format containing dollar and cents value (up to 2
   * decimals).
   *
   * @param cents total amount of value in int that needs to be formatted.
   * @return formatted representation of string.
   */
  private String convertCentsToDollarFormat(int cents) {
    Integer dollarValue = cents / 100;
    Integer centValue = cents % 100;
    return dollarValue.toString() + "." + String.format("%02d", centValue);
  }


  /**
   * Inserts entry in registers as per given denomination type and number of coins need to be
   * added.
   *
   * @param denomination of type Denomination enumeration.
   * @param num          represents number of coins need to be added in int for a given
   *                     denomination.
   */
  private void addValue(Denomination denomination, int num) {
    if (num < 0) {
      throw new IllegalArgumentException("Values cannot be negative");
    }
    if (denomination.getCategory().equals("Dollar")) {
      int count = dollarRegister.get(denomination);
      dollarRegister.put(denomination, count + num);
    } else {
      int count = centsRegister.get(denomination);
      centsRegister.put(denomination, count + num);
    }
    writeAuditLog(DEPOSIT, num * denomination.getValue());

  }

  /**
   * Returns all the transaction log in String. A class level StringBuffer is maintained to keep
   * track of all the withdrawal and delete related logs. Refer {@link CashRegister#getAuditLog()}
   * for more details.
   */
  @Override
  public String getAuditLog() {
    return this.transactionLog.toString();
  }
}
