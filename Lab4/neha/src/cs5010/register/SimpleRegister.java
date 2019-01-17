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
  private static final String CURRENCY = "$";
  private static final String DEPOSIT = "Deposit";
  private static final String WITHDRAW = "Withdraw";

  public SimpleRegister() {
    cashRegister = new TreeMap<>(Collections.reverseOrder());
    for(Denomination value : Denomination.values()){
      cashRegister.put(value.getValue(), 0);
    }
    transactionLog = new StringBuilder();
  }

  /**
   * Adds pennies to the register.
   *
   * @param num number of pennies to be added
   */
  @Override
  public void addPennies(int num) {
    int count = cashRegister.get(Denomination.PENNY.getValue());
    cashRegister.put(Denomination.PENNY.getValue(), count + num);
    String log = DEPOSIT+": "+CURRENCY+convertCentsToDollarFormat(num*Denomination.PENNY.getValue());
    writeAuditLog(log);

  }

  @Override
  public void addNickels(int num) {
    int count = cashRegister.get(Denomination.NICKLE.getValue());
    cashRegister.put(Denomination.NICKLE.getValue(), count + num);
    String log = DEPOSIT+": "+CURRENCY+convertCentsToDollarFormat(num*Denomination.NICKLE.getValue());
    writeAuditLog(log);
  }

  @Override
  public void addDimes(int num) {
    int count = cashRegister.get(Denomination.DIME.getValue());
    cashRegister.put(Denomination.DIME.getValue(), count + num);
    String log = DEPOSIT+": "+CURRENCY+convertCentsToDollarFormat(num*Denomination.DIME.getValue());
    writeAuditLog(log);
  }

  @Override
  public void addQuarters(int num) {
    int count = cashRegister.get(Denomination.QUARTER.getValue());
    cashRegister.put(Denomination.QUARTER.getValue(), count + num);
    String log = DEPOSIT+": "+CURRENCY+convertCentsToDollarFormat(num*Denomination.QUARTER.getValue());
    writeAuditLog(log);
  }

  @Override
  public void addOnes(int num) {
    int count = cashRegister.get(Denomination.ONE.getValue());
    cashRegister.put(Denomination.ONE.getValue(), count + num);
    String log = DEPOSIT+": "+CURRENCY+convertCentsToDollarFormat(num*Denomination.ONE.getValue());
    writeAuditLog(log);
  }

  @Override
  public void addFives(int num) {
    int count = cashRegister.get(Denomination.FIVE.getValue());
    cashRegister.put(Denomination.FIVE.getValue(), count + num);
    String log = DEPOSIT+": "+CURRENCY+convertCentsToDollarFormat(num*Denomination.FIVE.getValue());
    writeAuditLog(log);
  }

  @Override
  public void addTens(int num) {
    int count = cashRegister.get(Denomination.TEN.getValue());
    cashRegister.put(Denomination.TEN.getValue(), count + num);
    String log = DEPOSIT+": "+CURRENCY+convertCentsToDollarFormat(num*Denomination.TEN.getValue());
    writeAuditLog(log);
  }

  /**
   * @param dollars the dollar amount to be withdrawn
   * @param cents   the cent amount to be withdrawn
   */
  @Override
  public Map<Integer, Integer> withdraw(int dollars, int cents) throws InsufficientCashException {
    int withdrawalValue = dollars * 100 + cents;

    for (Integer denomination : cashRegister.keySet()) {
      int noOfCoins = cashRegister.get(denomination);
      while(withdrawalValue >= denomination && noOfCoins>=0) {
        withdrawalValue =  withdrawalValue-denomination;
        noOfCoins--;
      }
      cashRegister.put(denomination, noOfCoins);
      if(withdrawalValue == 0){
        break;
      }
    }
    if(withdrawalValue > 0){
      throw new InsufficientCashException("Cannot withdraw");
    }
    String log = WITHDRAW+": "+CURRENCY+convertCentsToDollarFormat(dollars * 100 + cents);
    writeAuditLog(log);
    return cashRegister;
  }

  /**
   *
   * @return
   */
  @Override
  public Map<Integer, Integer> getContents() {

    return this.cashRegister;
  }

  /**
   *
   * @param log
   */
  private void writeAuditLog(String log) {
    transactionLog.append(log);
    transactionLog.append('\n');
  }

  private String convertCentsToDollarFormat(int cents) {
    Integer dollarValue = cents/100;
    Integer centValue = cents%100;
    return dollarValue.toString()+"."+centValue.toString();
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
