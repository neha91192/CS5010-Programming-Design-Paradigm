import org.junit.BeforeClass;
import org.junit.Test;

import cs5010.register.CashRegister;
import cs5010.register.InsufficientCashException;
import cs5010.register.SimpleRegister;
import static org.junit.Assert.fail;

/**
 * Test class for {@link cs5010.register.CashRegister}
 */
public class CashRegisterTest {

  private static CashRegister cashRegister1;

  private static CashRegister cashRegister2;

  /**
   * Initialises cashRegister object instance.
   */
  @BeforeClass
  public static void setUp() {
    cashRegister1 = new SimpleRegister();
    cashRegister2 = new SimpleRegister();
    cashRegister1.addPennies(8);
    cashRegister1.addNickels(10);
    cashRegister1.addDimes(19);
    cashRegister1.addQuarters(10);
    cashRegister1.addOnes(9);
    cashRegister1.addFives(22);
    cashRegister1.addTens(14);

  }

//  /**
//   * Test to add penny.
//   */
//  @Test
//  public void testAddPennies1() {
//    cashRegister1.addPennies(2);
//    cashRegister1.addPennies(6);
//
//
//  }
//
//  /**
//   * Test to add penny.
//   */
//  @Test
//  public void testAddPennies2() {
//
//  }
//
//  /**
//   * Test to add penny.
//   */
//  @Test
//  public void testAddNickels1() {
//    cashRegister1.addNickels(10);
//    cashRegister1.addNickels(6);
//  }
//
//  /**
//   * Test to add penny.
//   */
//  @Test
//  public void testAddNickels2() {
//
//  }
//
//  /**
//   * Test to add penny.
//   */
//  @Test
//  public void testAddDimes1() {
//    cashRegister1.addDimes(19);
//  }
//
//  /**
//   * Test to add penny.
//   */
//  @Test
//  public void testAddDimes2() {
//
//  }
//
//
//  @Test
//  public void testAddQuarters1() {
//    cashRegister1.addQuarters(10);
//  }
//
//  @Test
//  public void testAddQuarters2() {
//
//  }
//
//  @Test
//  public void testAddOnes1() {
//    cashRegister1.addOnes(1);
//  }
//  @Test
//  public void testAddOnes2() {
//    cashRegister1.addOnes(9);
//  }
//  @Test
//  public void testAddFives1() {
//    cashRegister1.addFives(22);
//  }
//  @Test
//  public void testAddFives2() {
//
//  }
//  @Test
//  public void testAddTens1() {
//    cashRegister1.addTens(14);
//  }
//  @Test
//  public void testAddTens2() {
//    cashRegister1.addTens(2);
//  }
//

  @Test
  public void testWithdrawPositive(){
    try {
      cashRegister1.withdraw(10, 50);
      System.out.println(cashRegister1.getAuditLog());
    } catch (InsufficientCashException e){
      fail("Error");
    }

  }

  @Test
  public void testWithdrawNegative(){
    try {
      cashRegister2.withdraw(10, 50);
      fail("Error");
    } catch (InsufficientCashException e){
      System.out.println(cashRegister2.getAuditLog());
    }

  }

  @Test
  public void testGetAuditLog(){

      System.out.println(cashRegister1.getAuditLog());

  }

}
