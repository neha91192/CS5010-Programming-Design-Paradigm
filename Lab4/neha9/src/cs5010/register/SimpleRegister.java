package cs5010.register;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class SimpleRegister implements CashRegister {

  private Map<Integer, Integer> cashRegister;
  private StringBuilder transactionLog;
  private static final String DEPOSIT = "Deposit";
  private static final String WITHDRAW = "Withdraw";
  private Integer balanceInCents;


  public SimpleRegister() {
    cashRegister = new TreeMap<>(Collections.reverseOrder());
    for(Denomination value : Denomination.values()){
      cashRegister.put(value.getValue(), 0);
    }
    balanceInCents = 0;
    transactionLog = new StringBuilder();
  }

  /**
   * Adds pennies to the register.
   *
   * @param num number of pennies to be added
   */
  @Override
  public void addPennies(int num) {
   addValue(Denomination.PENNY, num);
  }

  @Override
  public void addNickels(int num) {
    addValue(Denomination.NICKLE, num);
  }

  @Override
  public void addDimes(int num) {
    addValue(Denomination.DIME, num);
  }

  @Override
  public void addQuarters(int num) {
    addValue(Denomination.QUARTER, num);
  }

  @Override
  public void addOnes(int num) {
    addValue(Denomination.ONE, num);
  }

  @Override
  public void addFives(int num) {
    addValue(Denomination.FIVE, num);
  }

  @Override
  public void addTens(int num) {
    addValue(Denomination.TEN, num);
  }

  /**
   * @param dollars the dollar amount to be withdrawn
   * @param cents   the cent amount to be withdrawn
   */
  @Override
  public Map<Integer, Integer> withdraw(int dollars, int cents) throws InsufficientCashException {
    Map<Integer, Integer> register = new TreeMap<>(cashRegister);
    if(dollars<0 || cents < 0){
      throw new IllegalArgumentException("Values cannot be negative");
    }
    int withdrawalValue = dollars * 100 + cents;
    if(withdrawalValue> balanceInCents){
      throw new InsufficientCashException("Cannot withdraw due to insufficient balance");
    }

    for (Integer denomination : cashRegister.keySet()) {
      int noOfCoins = register.get(denomination);
      while(withdrawalValue >= denomination && noOfCoins>=0) {
        withdrawalValue =  withdrawalValue-denomination;
        noOfCoins--;
      }
      register.put(denomination, noOfCoins);
      if(withdrawalValue == 0){
        break;
      }
    }
    String log = WITHDRAW+": "+convertCentsToDollarFormat(dollars * 100 + cents);
    writeAuditLog(log);
    cashRegister = register;
    return register;
  }

  /**
   *
   * @return
   */
  @Override
  public Map<Integer, Integer> getContents() {
    return new TreeMap<>(cashRegister);
  }

  /**
   *
   * @param log
   */
  private void writeAuditLog(String log) {
    if (transactionLog.toString().equals("")) {
      transactionLog.append(log);
    } else {
      transactionLog.append('\n');
      transactionLog.append(log);
    }

  }

  /**
   *
   * @param cents
   * @return
   */
  private String convertCentsToDollarFormat(int cents) {
    Integer dollarValue = cents/100;
    Integer centValue = cents%100;
    return dollarValue.toString()+"."+String.format("%02d", centValue);
  }


  /**
   *
   * @param denomination
   * @param num
   */
  private void addValue(Denomination denomination, int num){
    if(num<0){
      throw new IllegalArgumentException("Values cannot be negative");
    }
    int count = cashRegister.get(denomination.getValue());
    cashRegister.put(denomination.getValue(), count + num);
    balanceInCents = balanceInCents+ denomination.getValue()*num;
    String log = DEPOSIT+": "+convertCentsToDollarFormat(num*denomination.getValue());
    writeAuditLog(log);

  }
  /**
   *
   * @return
   */
  @Override
  public String getAuditLog() {
    return this.transactionLog.toString();
  }
}
