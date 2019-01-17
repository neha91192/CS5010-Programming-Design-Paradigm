import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the @link{SimpleProjectile} class.
 */
public class SimpleProjectileTest {
  private Particle projectile1;
  private Particle projectile2;
  private Particle projectile3;
  private Particle projectile4;

  /**
   * Initiates the values for SimpleProjectile class. It has following instances:
   * <ul>
   * <li>projectile1: When particle is at initial position x=0.00, y=0.0,
   * x-velocity component = 0.00 and y-velocity component = 10.00 </li>
   * <li>projectile2: When particle is at initial position x=1.32224, y=4.2332,
   * x-velocity component = 2.120 and y-velocity component = 20.00 </li>
   * <li>projectile3: When particle is at initial position x=-1.32224, y=-4.2332,
   * x-velocity component = 2.120 and y-velocity component = 30.23745544 </li>
   * <li>projectile4: When particle is at initial position x=-1.32224, y=-4.2332,
   * x-velocity component = 2.120 and y-velocity component = -30.23745544 </li>
   * </ul>
   */
  @Before
  public void setUp() {
    projectile1 = new SimpleProjectile(0.00f, 0.00f, 0.00f, 10.00f);
    projectile2 = new SimpleProjectile(1.32224f, 4.2332f, 2.120f,
            20.00f);
    projectile3 = new SimpleProjectile(-1.32224f, -4.2332f, 2.120f,
            30.23745544f);
    projectile4 = new SimpleProjectile(-1.32224f, -4.2332f, 2.120f,
            -30.23745544f);
  }

  /**
   * This test case will pass if the output state has the same value as the desired state. It checks
   * for the condition where provided <strong>time = restTime</strong> (restTime is the time at
   * which particle will be at "rest" for the first time).
   */
  @Test
  public void testState1() {
    String state = "At time " + String.format("%.2f", 2.0387f) + ": position is ("
            + String.format("%.2f", 0.00f) + "," + String.format("%.2f", 0.00f) + ")";
    assertEquals(projectile1.getState(2.0387f), state);
  }


  /**
   * This test case will fail if the output state has the same value as the non-desired state. It
   * checks for the condition where <strong>time is greater than restTime</strong> (restTime is the
   * time at which particle will be at "rest" for the first time).
   */
  @Test
  public void testState2() {
    String state = "At time " + String.format("%.2f", 20.4387f) + ": position is ("
            + String.format("%.2f", 0.00f) + "," + String.format("%.2f", 0.00f) + ")";
    assertEquals(projectile1.getState(20.4387f), state);
  }

  /**
   * This test case will fail if the output state has the same value as the desired state. It checks
   * for the condition where <strong>time is less than restTime</strong> (restTime is the time at
   * which particle will be at "rest" for the first time)
   */
  @Test
  public void testState3() {
    String state = "At time " + String.format("%.2f", 1.123f) + ": position is ("
            + String.format("%.2f", 0.00f) + "," + String.format("%.2f", 5.04f) + ")";
    assertEquals(projectile1.getState(1.123f), state);
  }

  /**
   * This test case will fail if the output state has the same value as the non-desired state. It
   * checks for the condition where <strong>initial x, y positions and x, y velocity component has
   * some positive values. Also, time is greater than restTime</strong>.
   */
  @Test
  public void testState4() {
    String state = "At time " + String.format("%.2f", 4.128923327f) + ": position is ("
            + String.format("%.2f", 9.97f) + "," + String.format("%.2f", 4.23f) + ")";
    assertEquals(projectile2.getState(4.128923327f), state);
  }

  /**
   * This test case will fail if the output state has the same value as the non-desired state. It
   * checks for the condition where <strong>initial x, y positions and x, y velocity component has
   * some negative values. Also, time is less than restTime</strong>.
   */
  @Test
  public void testState5() {
    String state = "At time " + String.format("%.2f", 3.79612f) + ": position is ("
            + String.format("%.2f", 6.73f) + "," + String.format("%.2f", 39.87f) + ")";
    assertEquals(projectile3.getState(3.79612f), state);
  }

  /**
   * This test case will fail if the output state has the same value as the non-desired state. It
   * checks for the condition where <strong>initial x, y positions and x, y velocity component has
   * some negative values. Also, time is greater than restTime</strong>
   */
  @Test
  public void testState6() {
    String state = "At time " + String.format("%.2f", 7.79612f) + ": position is ("
            + String.format("%.2f", 11.75f) + "," + String.format("%.2f", -4.2332f) + ")";
    assertEquals(projectile3.getState(7.79612f), state);
  }

  /**
   * This test case will fail if the output state has the same value as the non-desired state. It
   * checks for the condition where time is negative. In that case, this method should return
   * initial x and y positions.
   */
  @Test
  public void testState7() {
    String state = "At time " + String.format("%.2f", -7.79612f) + ": position is ("
            + String.format("%.2f", -1.32224f) + "," + String.format("%.2f", -4.2332f) + ")";
    assertEquals(projectile3.getState(-7.79612f), state);
  }

  /**
   * This test case will fail if the output state has the same value as the non-desired state. It
   * checks for the condition where initial vertical velocity is negative. In that case, this method
   * should return initial x and y positions.
   */
  @Test
  public void testState8() {
    String state = "At time " + String.format("%.2f", 7.79612f) + ": position is ("
            + String.format("%.2f", -1.32224f) + "," + String.format("%.2f", -4.2332f) + ")";
    assertEquals(projectile4.getState(7.79612f), state);
  }


}
