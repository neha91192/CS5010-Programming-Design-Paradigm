import org.junit.BeforeClass;
import org.junit.Test;

import cs5010.register.CashRegister;
import cs5010.register.Denomination;
import cs5010.register.InsufficientCashException;
import cs5010.register.SimpleRegister;
import static org.junit.Assert.fail;

/**
 * Test class for {@link cs5010.register.CashRegister}
 */
public class CashRegisterTest {

  private static CashRegister cashRegister1;

  private static Integer totalCents1;

  private static CashRegister cashRegister2;

  private static CashRegister cashRegister3;

  private static Integer totalCents2;


  /**
   * Initialises cashRegister object instance.
   */
  @BeforeClass
  public static void setUp() {
    cashRegister1 = new SimpleRegister();
    cashRegister2 = new SimpleRegister();
    cashRegister3 = new SimpleRegister();

    cashRegister1.addPennies(8);
    cashRegister1.addNickels(10);
    cashRegister1.addDimes(19);
    cashRegister1.addQuarters(10);
    cashRegister1.addOnes(9);
    cashRegister1.addFives(22);
    cashRegister1.addTens(14);
    totalCents1 = calculateTotalCentsInRegister(cashRegister1);

    cashRegister3.addPennies(2);
    cashRegister3.addNickels(3);
    cashRegister3.addDimes(1);
    cashRegister3.addQuarters(0);
    cashRegister3.addOnes(2);
    cashRegister3.addFives(1);
    cashRegister3.addTens(1);
    totalCents2 = calculateTotalCentsInRegister(cashRegister1);


  }

  /**
   *
   * @param cashRegister1
   * @return
   */
  private static Integer calculateTotalCentsInRegister(CashRegister cashRegister1){
    int totalCents = 0;
    for(Integer denomination: cashRegister1.getContents().keySet()){
      totalCents = totalCents+(denomination*cashRegister1.getContents().get(denomination));
    }
    return totalCents;
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
  public void testWithdrawFailure1(){
    try {
      cashRegister2.withdraw(10, 50);
      fail("Error");
    } catch (InsufficientCashException e){
      System.out.println(cashRegister2.getAuditLog());
    }

  }

  @Test
  public void testWithdrawFailure2(){
    try {
      cashRegister3.withdraw(50000, 50);
      fail("Error");
    } catch (InsufficientCashException e){
      System.out.println(cashRegister3.getAuditLog());
    }

  }
  @Test(expected = IllegalArgumentException.class)
  public void testWithdrawNegativeNumber1(){
    try {
      cashRegister1.withdraw(-20, -50);
      fail("Error");
    } catch (InsufficientCashException e){
      System.out.println(cashRegister1.getAuditLog());
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testWithdrawNegativeNumber2(){
    try {
      cashRegister1.withdraw(-1, 90);
      fail("Error");
    } catch (InsufficientCashException e){
      System.out.println(cashRegister1.getAuditLog());
    }

  }

  @Test
  public void testGetAuditLog(){

      System.out.println(cashRegister1.getAuditLog());

  }

}
