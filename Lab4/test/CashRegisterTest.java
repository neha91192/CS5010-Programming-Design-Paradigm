import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import cs5010.register.CashRegister;
import cs5010.register.Denomination;
import cs5010.register.InsufficientCashException;
import cs5010.register.SimpleRegister;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for {@link cs5010.register.CashRegister}.
 */
public class CashRegisterTest {

  /**
   * Register to hold some initial values.
   */
  private CashRegister cashRegister1;
  /**
   * Contains log after each immediate operation.
   */
  private StringBuilder log;

  /**
   * Register to hold empty values.
   */
  private CashRegister cashRegister2;

  /**
   * Register to hold empty values.
   */
  private CashRegister cashRegister3;

  /**
   * Initialises cashRegister object instance.
   */
  @Before
  public void setUp() {
    cashRegister1 = new SimpleRegister();
    cashRegister2 = new SimpleRegister();
    cashRegister3 = new SimpleRegister();
    log = new StringBuilder();

  }

  /**
   * Test to add penny success.
   */
  @Test
  public void testAddPenniesSuccess() {
    cashRegister1.addPennies(0);
    int expected = cashRegister1.getContents().get(Denomination.PENNY.getValue());
    assertEquals(expected, 0);

    cashRegister1.addPennies(2);
    expected = cashRegister1.getContents().get(Denomination.PENNY.getValue());
    assertEquals(expected, 2);

    cashRegister1.addPennies(6);
    expected = cashRegister1.getContents().get(Denomination.PENNY.getValue());
    assertEquals(expected, 8);

    cashRegister1.addPennies(20);
    expected = cashRegister1.getContents().get(Denomination.PENNY.getValue());
    assertEquals(expected, 28);

    cashRegister1.addPennies(0);
    expected = cashRegister1.getContents().get(Denomination.PENNY.getValue());
    assertEquals(expected, 28);

  }

  /**
   * Test to add penny failure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddPenniesFailure() {
    cashRegister1.addPennies(-2);
  }

  /**
   * Test to add nickel success.
   */
  @Test
  public void testAddNickelsSuccess() {
    cashRegister1.addNickels(0);
    int expected = cashRegister1.getContents().get(Denomination.NICKLE.getValue());
    assertEquals(expected, 0);

    cashRegister1.addNickels(2);
    expected = cashRegister1.getContents().get(Denomination.NICKLE.getValue());
    assertEquals(expected, 2);

    cashRegister1.addNickels(6);
    expected = cashRegister1.getContents().get(Denomination.NICKLE.getValue());
    assertEquals(expected, 8);

    cashRegister1.addNickels(20);
    expected = cashRegister1.getContents().get(Denomination.NICKLE.getValue());
    assertEquals(expected, 28);

    cashRegister1.addNickels(0);
    expected = cashRegister1.getContents().get(Denomination.NICKLE.getValue());
    assertEquals(expected, 28);
  }

  /**
   * Test to add Nickels.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNickelsFailure() {
    cashRegister1.addNickels(-13323);
  }

  /**
   * Test to add dime success.
   */
  @Test
  public void testAddDimesSuccess() {
    cashRegister1.addDimes(0);
    int expected = cashRegister1.getContents().get(Denomination.DIME.getValue());
    assertEquals(expected, 0);

    cashRegister1.addDimes(2);
    expected = cashRegister1.getContents().get(Denomination.DIME.getValue());
    assertEquals(expected, 2);

    cashRegister1.addDimes(6);
    expected = cashRegister1.getContents().get(Denomination.DIME.getValue());
    assertEquals(expected, 8);

    cashRegister1.addDimes(20);
    expected = cashRegister1.getContents().get(Denomination.DIME.getValue());
    assertEquals(expected, 28);

    cashRegister1.addDimes(0);
    expected = cashRegister1.getContents().get(Denomination.DIME.getValue());
    assertEquals(expected, 28);
  }

  /**
   * Test to add dime failure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddDimesFailure() {
    cashRegister1.addDimes(-112);
  }

  /**
   * Test to add quarter success.
   */
  @Test
  public void testAddQuartersSuccess() {
    cashRegister1.addQuarters(0);
    int expected = cashRegister1.getContents().get(Denomination.QUARTER.getValue());
    assertEquals(expected, 0);

    cashRegister1.addQuarters(2);
    expected = cashRegister1.getContents().get(Denomination.QUARTER.getValue());
    assertEquals(expected, 2);

    cashRegister1.addQuarters(6);
    expected = cashRegister1.getContents().get(Denomination.QUARTER.getValue());
    assertEquals(expected, 8);

    cashRegister1.addQuarters(20);
    expected = cashRegister1.getContents().get(Denomination.QUARTER.getValue());
    assertEquals(expected, 28);

    cashRegister1.addQuarters(0);
    expected = cashRegister1.getContents().get(Denomination.QUARTER.getValue());
    assertEquals(expected, 28);
  }

  /**
   * Test to add Quarter failure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddQuartersFailure() {
    cashRegister1.addQuarters(-12);
  }

  /**
   * Test to add ones success.
   */
  @Test
  public void testAddOnesSuccess() {
    cashRegister1.addOnes(0);
    int expected = cashRegister1.getContents().get(Denomination.ONE.getValue());
    assertEquals(expected, 0);

    cashRegister1.addOnes(2);
    expected = cashRegister1.getContents().get(Denomination.ONE.getValue());
    assertEquals(expected, 2);

    cashRegister1.addOnes(6);
    expected = cashRegister1.getContents().get(Denomination.ONE.getValue());
    assertEquals(expected, 8);

    cashRegister1.addOnes(20);
    expected = cashRegister1.getContents().get(Denomination.ONE.getValue());
    assertEquals(expected, 28);

    cashRegister1.addOnes(0);
    expected = cashRegister1.getContents().get(Denomination.ONE.getValue());
    assertEquals(expected, 28);
  }

  /**
   * Test to add ones failure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddOnesFailure() {
    cashRegister1.addOnes(-1);
  }

  /**
   * Test to add fives success.
   */
  @Test
  public void testAddFivesSuccess() {
    cashRegister1.addFives(0);
    int expected = cashRegister1.getContents().get(Denomination.FIVE.getValue());
    assertEquals(expected, 0);

    cashRegister1.addFives(2);
    expected = cashRegister1.getContents().get(Denomination.FIVE.getValue());
    assertEquals(expected, 2);

    cashRegister1.addFives(6);
    expected = cashRegister1.getContents().get(Denomination.FIVE.getValue());
    assertEquals(expected, 8);

    cashRegister1.addFives(20);
    expected = cashRegister1.getContents().get(Denomination.FIVE.getValue());
    assertEquals(expected, 28);

    cashRegister1.addFives(0);
    expected = cashRegister1.getContents().get(Denomination.FIVE.getValue());
    assertEquals(expected, 28);
  }

  /**
   * Test to add five failure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddFivesFailure() {
    cashRegister1.addFives(-7);
  }

  /**
   * Test to add tens success.
   */
  @Test
  public void testAddTensSuccess() {
    cashRegister1.addTens(0);
    int expected = cashRegister1.getContents().get(Denomination.TEN.getValue());
    assertEquals(expected, 0);

    cashRegister1.addTens(2);
    expected = cashRegister1.getContents().get(Denomination.TEN.getValue());
    assertEquals(expected, 2);

    cashRegister1.addTens(6);
    expected = cashRegister1.getContents().get(Denomination.TEN.getValue());
    assertEquals(expected, 8);

    cashRegister1.addTens(20);
    expected = cashRegister1.getContents().get(Denomination.TEN.getValue());
    assertEquals(expected, 28);

    cashRegister1.addTens(0);
    expected = cashRegister1.getContents().get(Denomination.TEN.getValue());
    assertEquals(expected, 28);
  }

  /**
   * Test to add tens failure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTensFailure() {
    cashRegister1.addTens(-2);
  }

  /**
   * Test to withdraw success when nothing is added.
   */
  @Test
  public void testWithdrawSuccess1() {
    try {
      Map<Integer, Integer> amount = cashRegister2.withdraw(0, 0);
      assertEquals(log.toString(), cashRegister2.getAuditLog());
      assertEquals(false, amount.containsKey(Denomination.TEN.getValue()));
      assertEquals(false, amount.containsKey(Denomination.FIVE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.ONE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.QUARTER.getValue()));
      assertEquals(false, amount.containsKey(Denomination.DIME.getValue()));
      assertEquals(false, amount.containsKey(Denomination.NICKLE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.PENNY.getValue()));
    } catch (InsufficientCashException e) {
      fail("Error");
    }
  }

  /**
   * Test to withdraw success when nothing is added.
   */
  @Test
  public void testWithdrawSuccess2() {
    try {
      cashRegister2.addOnes(1);
      log.append("Deposit: 1.00\n");
      cashRegister2.addPennies(1);
      log.append("Deposit: 0.01\n");
      Map<Integer, Integer> amount = cashRegister2.withdraw(1, 1);
      log.append("Withdraw: 1.01");
      int actual = amount.get(Denomination.ONE.getValue());
      assertEquals(actual, 1);
      actual = amount.get(Denomination.PENNY.getValue());
      assertEquals(actual, 1);
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    } catch (InsufficientCashException e) {
      fail("Error");
    }
  }

  /**
   * Test to withdraw success when amount is multiple of first denomination.
   */
  @Test
  public void testWithdrawSuccess3() {
    try {
      cashRegister2.addTens(2);
      log.append("Deposit: 20.00\n");
      cashRegister2.addFives(2);
      log.append("Deposit: 10.00\n");
      cashRegister2.addOnes(2);
      log.append("Deposit: 2.00\n");
      Map<Integer, Integer> amount = cashRegister2.withdraw(20, 0);
      log.append("Withdraw: 20.00");

      int actual = amount.get(Denomination.TEN.getValue());
      assertEquals(2, actual);
      assertEquals(false, amount.containsKey(Denomination.FIVE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.ONE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.QUARTER.getValue()));
      assertEquals(false, amount.containsKey(Denomination.DIME.getValue()));
      assertEquals(false, amount.containsKey(Denomination.NICKLE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.PENNY.getValue()));
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    } catch (InsufficientCashException e) {
      fail("Error");
    }
  }

  /**
   * Test to withdraw success when amount is multiple of first denomination.
   */
  @Test
  public void testWithdrawSuccess4() {
    try {
      cashRegister2.addTens(4);
      log.append("Deposit: 40.00\n");
      cashRegister2.addFives(2);
      log.append("Deposit: 10.00\n");
      cashRegister2.addOnes(2);
      log.append("Deposit: 2.00\n");
      Map<Integer, Integer> amount = cashRegister2.withdraw(41, 0);
      log.append("Withdraw: 41.00");

      int actual = amount.get(Denomination.TEN.getValue());
      assertEquals(4, actual);
      assertEquals(false, amount.containsKey(Denomination.FIVE.getValue()));
      actual = amount.get(Denomination.ONE.getValue());
      assertEquals(1, actual);
      assertEquals(false, amount.containsKey(Denomination.QUARTER.getValue()));
      assertEquals(false, amount.containsKey(Denomination.DIME.getValue()));
      assertEquals(false, amount.containsKey(Denomination.NICKLE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.PENNY.getValue()));
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    } catch (InsufficientCashException e) {
      fail("Error");
    }
  }

  /**
   * Test to withdraw success when dollar amount is used up entirely.
   */
  @Test
  public void testWithdrawSuccess5() {
    try {
      cashRegister2.addTens(4);
      log.append("Deposit: 40.00\n");
      cashRegister2.addFives(2);
      log.append("Deposit: 10.00\n");
      cashRegister2.addOnes(2);
      log.append("Deposit: 2.00\n");
      cashRegister2.addPennies(10);
      log.append("Deposit: 0.10\n");
      cashRegister2.addQuarters(10);
      log.append("Deposit: 2.50\n");
      cashRegister2.addDimes(10);
      log.append("Deposit: 1.00\n");
      cashRegister2.addNickels(10);
      log.append("Deposit: 0.50\n");
      Map<Integer, Integer> amount = cashRegister2.withdraw(52, 410);
      log.append("Withdraw: 56.10");

      int actual = amount.get(Denomination.TEN.getValue());
      assertEquals(4, actual);
      actual = amount.get(Denomination.FIVE.getValue());
      assertEquals(2, actual);
      actual = amount.get(Denomination.ONE.getValue());
      assertEquals(2, actual);
      actual = amount.get(Denomination.QUARTER.getValue());
      assertEquals(10, actual);
      actual = amount.get(Denomination.DIME.getValue());
      assertEquals(10, actual);
      actual = amount.get(Denomination.NICKLE.getValue());
      assertEquals(10, actual);
      actual = amount.get(Denomination.PENNY.getValue());
      assertEquals(10, actual);
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    } catch (InsufficientCashException e) {
      fail("Error");
    }
  }

  /**
   * Test to withdraw success when amount is less than first highest denominator for dollar.
   */
  @Test
  public void testWithdrawSuccess6() {
    try {
      cashRegister2.addTens(4);
      log.append("Deposit: 40.00\n");
      cashRegister2.addFives(2);
      log.append("Deposit: 10.00\n");
      cashRegister2.addOnes(3);
      log.append("Deposit: 3.00\n");
      Map<Integer, Integer> amount = cashRegister2.withdraw(8, 0);
      log.append("Withdraw: 8.00");
      assertEquals(false, amount.containsKey(Denomination.TEN.getValue()));
      int actual = amount.get(Denomination.FIVE.getValue());
      assertEquals(1, actual);
      actual = amount.get(Denomination.ONE.getValue());
      assertEquals(3, actual);
      assertEquals(false, amount.containsKey(Denomination.QUARTER.getValue()));
      assertEquals(false, amount.containsKey(Denomination.DIME.getValue()));
      assertEquals(false, amount.containsKey(Denomination.NICKLE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.PENNY.getValue()));
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    } catch (InsufficientCashException e) {
      fail("Error");
    }
  }

  /**
   * Test to withdraw success when all the denominations in dollars are used.
   */
  @Test
  public void testWithdrawSuccess7() {
    try {
      cashRegister2.addTens(4);
      log.append("Deposit: 40.00\n");
      cashRegister2.addFives(2);
      log.append("Deposit: 10.00\n");
      cashRegister2.addOnes(3);
      log.append("Deposit: 3.00\n");
      Map<Integer, Integer> amount = cashRegister2.withdraw(16, 0);
      log.append("Withdraw: 16.00");
      int actual = amount.get(Denomination.TEN.getValue());
      assertEquals(1, actual);
      actual = amount.get(Denomination.FIVE.getValue());
      assertEquals(1, actual);
      actual = amount.get(Denomination.ONE.getValue());
      assertEquals(1, actual);
      assertEquals(false, amount.containsKey(Denomination.QUARTER.getValue()));
      assertEquals(false, amount.containsKey(Denomination.DIME.getValue()));
      assertEquals(false, amount.containsKey(Denomination.NICKLE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.PENNY.getValue()));
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    } catch (InsufficientCashException e) {
      fail("Error");
    }
  }

  /**
   * Cases related to both dollars and cents.
   */
  @Test
  public void testWithdrawPositive8() {
    try {
      cashRegister2.addTens(4);
      log.append("Deposit: 40.00\n");
      cashRegister2.addFives(2);
      log.append("Deposit: 10.00\n");
      cashRegister2.addOnes(3);
      log.append("Deposit: 3.00\n");
      cashRegister2.addQuarters(8);
      log.append("Deposit: 2.00\n");
      cashRegister2.addDimes(20);
      log.append("Deposit: 2.00\n");
      cashRegister2.addNickels(40);
      log.append("Deposit: 2.00\n");
      cashRegister2.addPennies(400);
      log.append("Deposit: 4.00\n");

      Map<Integer, Integer> amount = cashRegister2.withdraw(52, 400);
      log.append("Withdraw: 56.00");
      int actual = amount.get(Denomination.TEN.getValue());
      assertEquals(4, actual);
      actual = amount.get(Denomination.FIVE.getValue());
      assertEquals(2, actual);
      actual = amount.get(Denomination.ONE.getValue());
      assertEquals(2, actual);
      actual = amount.get(Denomination.QUARTER.getValue());
      assertEquals(8, actual);
      actual = amount.get(Denomination.DIME.getValue());
      assertEquals(20, actual);
      assertEquals(false, amount.containsKey(Denomination.NICKLE.getValue()));
      assertEquals(false, amount.containsKey(Denomination.PENNY.getValue()));
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    } catch (InsufficientCashException e) {
      fail("Error");
    }
  }

  /**
   * Cases when there is withdrawal failure. Nothing has been added and trying to withdraw.
   */
  @Test
  public void testWithdrawFailure1() {
    try {
      cashRegister2.withdraw(10, 50);
      fail("Error");
    } catch (InsufficientCashException e) {
      int actual = cashRegister2.getContents().get(Denomination.TEN.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.FIVE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.ONE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.QUARTER.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.DIME.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.NICKLE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.PENNY.getValue());
      assertEquals(0, actual);
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }

  /**
   * Cases when there is withdrawal failure. Dollar required doesn't have required denomination but
   * sufficient balance.
   */
  @Test
  public void testWithdrawFailure2() {
    try {
      cashRegister2.addTens(5);
      log.append("Deposit: 50.00");
      cashRegister2.withdraw(9, 0);
      fail("Error");
    } catch (InsufficientCashException e) {
      int actual = cashRegister2.getContents().get(Denomination.TEN.getValue());
      assertEquals(5, actual);
      actual = cashRegister2.getContents().get(Denomination.FIVE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.ONE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.QUARTER.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.DIME.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.NICKLE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.PENNY.getValue());
      assertEquals(0, actual);
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }

  /**
   * Cases when there is withdrawal failure. Dollar required doesn't have enough denomination and
   * also insufficient balance.
   */
  @Test
  public void testWithdrawFailure3() {
    try {
      cashRegister2.addTens(5);
      log.append("Deposit: 50.00\n");
      cashRegister2.addOnes(9);
      log.append("Deposit: 9.00\n");
      cashRegister2.addPennies(13);
      log.append("Deposit: 0.13");
      cashRegister2.withdraw(60, 12);
      fail("Error");
    } catch (InsufficientCashException e) {
      int actual = cashRegister2.getContents().get(Denomination.TEN.getValue());
      assertEquals(5, actual);
      actual = cashRegister2.getContents().get(Denomination.FIVE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.ONE.getValue());
      assertEquals(9, actual);
      actual = cashRegister2.getContents().get(Denomination.QUARTER.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.DIME.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.NICKLE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.PENNY.getValue());
      assertEquals(13, actual);
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }

  /**
   * Cases when there is withdrawal failure. Cents required doesn't have required denomination but
   * sufficient balance.
   */
  @Test
  public void testWithdrawFailure4() {
    try {
      cashRegister2.addQuarters(4);
      log.append("Deposit: 1.00");
      cashRegister3.withdraw(0, 24);
      fail("Error");
    } catch (InsufficientCashException e) {
      int actual = cashRegister2.getContents().get(Denomination.TEN.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.FIVE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.ONE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.QUARTER.getValue());
      assertEquals(4, actual);
      actual = cashRegister2.getContents().get(Denomination.DIME.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.NICKLE.getValue());
      assertEquals(0, actual);
      actual = cashRegister2.getContents().get(Denomination.PENNY.getValue());
      assertEquals(0, actual);
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }

  /**
   * Test to withdraw failure when there is no sufficient cents left.
   */
  @Test
  public void testWithdrawFailure5() {
    try {
      cashRegister2.addTens(2);
      log.append("Deposit: 20.00\n");
      cashRegister2.addFives(2);
      log.append("Deposit: 10.00\n");
      cashRegister2.addOnes(2);
      log.append("Deposit: 2.00");
      cashRegister2.withdraw(23, 0);
      fail("Error");
    } catch (InsufficientCashException e) {
      int expected = cashRegister2.getContents().get(Denomination.TEN.getValue());
      assertEquals(expected, 2);
      expected = cashRegister2.getContents().get(Denomination.FIVE.getValue());
      assertEquals(expected, 2);
      expected = cashRegister2.getContents().get(Denomination.ONE.getValue());
      assertEquals(expected, 2);
      expected = cashRegister2.getContents().get(Denomination.QUARTER.getValue());
      assertEquals(expected, 0);
      expected = cashRegister2.getContents().get(Denomination.DIME.getValue());
      assertEquals(expected, 0);
      expected = cashRegister2.getContents().get(Denomination.NICKLE.getValue());
      assertEquals(expected, 0);
      expected = cashRegister2.getContents().get(Denomination.PENNY.getValue());
      assertEquals(expected, 0);
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }
  }

  /**
   * Test to withdraw failure when there is no sufficient dollars and cents left.
   */
  @Test
  public void testWithdrawFailure6() {
    try {
      cashRegister2.addTens(5);
      log.append("Deposit: 50.00\n");
      cashRegister2.addFives(5);
      log.append("Deposit: 25.00\n");
      cashRegister2.addOnes(5);
      log.append("Deposit: 5.00\n");
      cashRegister2.addQuarters(20);
      log.append("Deposit: 5.00\n");
      cashRegister2.addDimes(5000);
      log.append("Deposit: 500.00\n");
      cashRegister2.addNickels(1000);
      log.append("Deposit: 50.00\n");
      cashRegister2.addPennies(650);
      log.append("Deposit: 6.50");
      cashRegister2.withdraw(100, 700000);
      fail("Error");
    } catch (InsufficientCashException e) {
      int expected = cashRegister2.getContents().get(Denomination.TEN.getValue());
      assertEquals(expected, 5);
      expected = cashRegister2.getContents().get(Denomination.FIVE.getValue());
      assertEquals(expected, 5);
      expected = cashRegister2.getContents().get(Denomination.ONE.getValue());
      assertEquals(expected, 5);
      expected = cashRegister2.getContents().get(Denomination.QUARTER.getValue());
      assertEquals(expected, 20);
      expected = cashRegister2.getContents().get(Denomination.DIME.getValue());
      assertEquals(expected, 5000);
      expected = cashRegister2.getContents().get(Denomination.NICKLE.getValue());
      assertEquals(expected, 1000);
      expected = cashRegister2.getContents().get(Denomination.PENNY.getValue());
      assertEquals(expected, 650);
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }
  }

  /**
   * Cases when there is Illegal argument provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWithdrawNegativeNumber1() {
    try {
      cashRegister1.withdraw(-20, -50);
    } catch (InsufficientCashException e) {
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }

  /**
   * Cases when there is Illegal argument provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWithdrawNegativeNumber2() {
    try {
      cashRegister1.withdraw(-1, 90);
    } catch (InsufficientCashException e) {
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }


  /**
   * Cases when there is Illegal argument provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWithdrawNegativeNumber3() {
    try {
      cashRegister1.withdraw(1, -90);
    } catch (InsufficientCashException e) {
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }

  /**
   * Cases when there is Illegal argument provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWithdrawNegativeNumber4() {
    try {
      cashRegister1.withdraw(Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1);
    } catch (InsufficientCashException e) {
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }

  /**
   * Cases when there is Illegal argument provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWithdrawNegativeNumber5() {
    try {
      cashRegister1.withdraw(Integer.MIN_VALUE, Integer.MIN_VALUE);
    } catch (InsufficientCashException e) {
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }

  /**
   * Cases when there is Illegal argument provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWithdrawNegativeNumber6() {
    try {
      cashRegister1.withdraw(1, -90);
    } catch (InsufficientCashException e) {
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

  }

  /**
   * Case to verify if log is correct.
   */
  @Test
  public void testGetAuditLog() {
    cashRegister2.addTens(5);
    log.append("Deposit: 50.00\n");
    cashRegister2.addFives(5);
    log.append("Deposit: 25.00\n");
    cashRegister2.addOnes(5);
    log.append("Deposit: 5.00\n");
    cashRegister2.addQuarters(20);
    log.append("Deposit: 5.00\n");
    cashRegister2.addDimes(5000);
    log.append("Deposit: 500.00\n");
    cashRegister2.addNickels(1000);
    log.append("Deposit: 50.00\n");
    cashRegister2.addPennies(650);
    log.append("Deposit: 6.50\n");
    try {
      cashRegister2.withdraw(30, 3322);
      log.append("Withdraw: 63.22\n");
      cashRegister2.withdraw(15, 669);
      log.append("Withdraw: 21.69\n");
      cashRegister2.withdraw(0, 788);
      log.append("Withdraw: 7.88");
    } catch (InsufficientCashException e) {
      assertEquals(log.toString(), cashRegister2.getAuditLog());
    }

    assertEquals(log.toString(), cashRegister2.getAuditLog());
  }

  /**
   * Case to print content when the content is empty.
   */
  @Test
  public void testGetContentEmpty() {
    int actual = cashRegister3.getContents().get(Denomination.TEN.getValue());
    assertEquals(0, actual);
    actual = cashRegister3.getContents().get(Denomination.FIVE.getValue());
    assertEquals(0, actual);
    actual = cashRegister3.getContents().get(Denomination.ONE.getValue());
    assertEquals(0, actual);
    actual = cashRegister3.getContents().get(Denomination.QUARTER.getValue());
    assertEquals(0, actual);
    actual = cashRegister3.getContents().get(Denomination.DIME.getValue());
    assertEquals(0, actual);
    actual = cashRegister3.getContents().get(Denomination.NICKLE.getValue());
    assertEquals(0, actual);
    actual = cashRegister3.getContents().get(Denomination.PENNY.getValue());
    assertEquals(0, actual);
    assertEquals(log.toString(), cashRegister3.getAuditLog());
  }

  /**
   * Case to print content when all denominations are present.
   */
  @Test
  public void testGetContentAllPresent() {
    cashRegister2.addTens(5);
    log.append("Deposit: 50.00\n");
    cashRegister2.addFives(5);
    log.append("Deposit: 25.00\n");
    cashRegister2.addOnes(5);
    log.append("Deposit: 5.00\n");
    cashRegister2.addQuarters(20);
    log.append("Deposit: 5.00\n");
    cashRegister2.addDimes(5000);
    log.append("Deposit: 500.00\n");
    cashRegister2.addNickels(1000);
    log.append("Deposit: 50.00\n");
    cashRegister2.addPennies(650);
    log.append("Deposit: 6.50");
    int expected = cashRegister2.getContents().get(Denomination.TEN.getValue());
    assertEquals(expected, 5);
    expected = cashRegister2.getContents().get(Denomination.FIVE.getValue());
    assertEquals(expected, 5);
    expected = cashRegister2.getContents().get(Denomination.ONE.getValue());
    assertEquals(expected, 5);
    expected = cashRegister2.getContents().get(Denomination.QUARTER.getValue());
    assertEquals(expected, 20);
    expected = cashRegister2.getContents().get(Denomination.DIME.getValue());
    assertEquals(expected, 5000);
    expected = cashRegister2.getContents().get(Denomination.NICKLE.getValue());
    assertEquals(expected, 1000);
    expected = cashRegister2.getContents().get(Denomination.PENNY.getValue());
    assertEquals(expected, 650);
    assertEquals(log.toString(), cashRegister2.getAuditLog());
  }

}
