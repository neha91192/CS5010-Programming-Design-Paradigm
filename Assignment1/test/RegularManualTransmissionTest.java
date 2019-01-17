import org.junit.Before;
import org.junit.Test;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;
import vehicle.TransmissionMessageConstants;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the @link{RegularManualTransmission} class.
 */
public class RegularManualTransmissionTest {
  /**
   * Transmission object which holds Gears, speed, status related details.
   */
  private ManualTransmission transmission1;

  /**
   * Initiates the values for RegularManualTransmission class.
   */
  @Before
  public void setUp() throws IllegalArgumentException {
    transmission1 = new RegularManualTransmission(0, 4, 3, 18,
            16, 25, 20, 31, 26, 40);

  }

  /**
   * Increases speed by value of n.
   *
   * @param n            of type int which contains value by which speed needs to be incremented.
   * @param transmission object which needs to be updated.
   */
  private ManualTransmission increaseSpeedBy(int n, ManualTransmission transmission) {
    for (int i = 0; i < n; i++) {
      transmission.increaseSpeed();
    }
    return transmission;
  }

  /**
   * Decreases speed by value of n.
   *
   * @param n            of type int which contains value by which speed needs to be incremented.
   * @param transmission object which needs to be updated.
   */
  private ManualTransmission decreaseSpeedBy(int n, ManualTransmission transmission) {
    for (int i = 0; i < n; i++) {
      transmission.decreaseSpeed();
    }
    return transmission;
  }

  /**
   * Case 1: Tests for the case where negative speeds cannot be given as input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeInputs() {
    new RegularManualTransmission(-20, -11, -12, -7,
            -8, -4, -6, -2, -3, 0);
  }


  /**
   * Case 2: Tests for the case where the lowest speed provided is 0. If it is not, it throws {@link
   * IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowestSpeed() {
    new RegularManualTransmission(2, 4, 3, 18,
            16, 25, 20, 31, 26, 40);
  }


  /**
   * Case 3: Tests for the case where l1 should be less than or equal to h1, otherwise it throws
   * {@link IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerSpeedLessThanEqualsHigher1() {
    new RegularManualTransmission(4, 3, 2, 20,
            20, 25, 25, 30, 29, 40);
  }

  /**
   * Case 4: Tests for the case where l2 should be less than or equal to h2, otherwise it throws
   * {@link IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerSpeedLessThanEqualsHigher2() {
    new RegularManualTransmission(0, 4, 4, 3,
            3, 25, 25, 30, 29, 40);
  }

  /**
   * Case 5: Tests for the case where l3 should be less than or equal to h3, otherwise it throws
   * {@link IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerSpeedLessThanEqualsHigher3() {
    new RegularManualTransmission(0, 4, 3, 20,
            20, 18, 18, 30, 29, 40);
  }

  /**
   * Case 6: Tests for the case where l4 should be less than or equal to h4, otherwise it throws
   * {@link IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerSpeedLessThanEqualsHigher4() {
    new RegularManualTransmission(0, 4, 3, 20,
            20, 25, 25, 23, 22, 40);
  }

  /**
   * Case 7: Tests for the case where l5 should be less than or equal to h5, otherwise it throws
   * {@link IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerSpeedLessThanEqualsHigher5() {
    new RegularManualTransmission(0, 4, 3, 20,
            20, 25, 25, 27, 22, 21);
  }

  /**
   * Case 8: Tests for the case where l1 should be less than l2, otherwise it throws {@link
   * IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerGearSpeedLessThanHigherGearSpeed1() {
    new RegularManualTransmission(0, 4, 0, 18,
            16, 16, 16, 31, 26, 40);
  }

  /**
   * Case 9: Tests for the case where l2 should be less than l3, otherwise it throws {@link
   * IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerGearSpeedLessThanHigherGearSpeed2() {
    new RegularManualTransmission(0, 16, 16, 19,
            16, 25, 17, 31, 26, 40);
  }

  /**
   * Case 10: Tests for the case where l3 should be less than l4, otherwise it throws {@link
   * IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerGearSpeedLessThanHigherGearSpeed3() {
    new RegularManualTransmission(0, 16, 15, 19,
            19, 25, 16, 31, 26, 40);
  }

  /**
   * Case 11: Tests for the case where l4 should be less than l5, otherwise it throws {@link
   * IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerGearSpeedLessThanHigherGearSpeed4() {
    new RegularManualTransmission(0, 16, 15, 19,
            18, 25, 24, 30, 23, 33);
  }

  /**
   * Case 12: Tests for the case where gears should be overlapping, otherwise it throws {@link
   * IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingGears1() {
    new RegularManualTransmission(0, 7, 8, 15,
            15, 20, 19, 25, 23, 30);
  }

  /**
   * Case 13: Tests for the case where gears should be overlapping, otherwise it throws {@link
   * IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingGears2() {
    new RegularManualTransmission(0, 7, 6, 15,
            16, 20, 18, 25, 24, 40);
  }

  /**
   * Case 14: Tests for the case where gears should be overlapping, otherwise it throws {@link
   * IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingGears3() {
    new RegularManualTransmission(0, 7, 7, 15,
            13, 18, 20, 25, 25, 40);
  }

  /**
   * Case 15: Tests for the case where gears should be overlapping, otherwise it throws {@link
   * IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingGears4() {
    new RegularManualTransmission(0, 7, 7, 15,
            13, 18, 17, 25, 27, 40);
  }


  /**
   * Case 16: Tests for the case where transmission object was created successfully with speed as 0,
   * gear as 1, and messages as "OK: everything is OK.".
   */
  @Test
  public void testTransmissionInitialization() {
    assertEquals(0, this.transmission1.getSpeed());
    assertEquals(1, this.transmission1.getGear());
    assertEquals(TransmissionMessageConstants.OK, this.transmission1.getStatus());
  }

  /**
   * Case 17: Tests for the case where gear values are always between 1 to 5.
   */
  @Test
  public void testGearValuesFrom1To5() {
    assertEquals(TransmissionMessageConstants.ERROR_DECREASING_GEAR_MIN_GEAR,
            this.transmission1.decreaseGear().getStatus());
    assertEquals(1, this.transmission1.getGear());
    this.increaseSpeedBy(3, this.transmission1).increaseGear();
    assertEquals(2, this.transmission1.getGear());
    this.increaseSpeedBy(13, this.transmission1).increaseGear();
    assertEquals(3, this.transmission1.getGear());
    this.increaseSpeedBy(4, this.transmission1).increaseGear();
    assertEquals(4, this.transmission1.getGear());
    this.increaseSpeedBy(6, this.transmission1).increaseGear();
    assertEquals(5, this.transmission1.getGear());
    assertEquals(TransmissionMessageConstants.ERROR_INCREASING_GEAR_MAX_GEAR,
            this.transmission1.increaseGear().getStatus());

  }


  /**
   * Case 18: Tests for the case where speed is incremented by 1.
   */
  @Test
  public void testIncrementSpeedBy1() {
    this.transmission1.increaseSpeed();
    assertEquals(1, this.transmission1.getSpeed());
    assertEquals(TransmissionMessageConstants.OK, this.transmission1.getStatus());
    assertEquals(1, this.transmission1.getGear());
  }

  /**
   * Case 19: Checks for the case where speed is allowed to be increased with no need of increase in
   * gear.
   */
  @Test
  public void testIncreaseSpeedOk() {
    this.increaseSpeedBy(2, this.transmission1);
    assertEquals(2, transmission1.getSpeed());
    assertEquals(TransmissionMessageConstants.OK, transmission1.getStatus());
    assertEquals(1, this.transmission1.getGear());
  }

  /**
   * Case 20: Checks for the case where speed is allowed to be increased but driver may increase the
   * gear.
   */
  @Test
  public void testIncreaseSpeedIncreaseGear() {
    this.increaseSpeedBy(3, this.transmission1);
    assertEquals(3, transmission1.getSpeed());
    assertEquals(1, this.transmission1.getGear());
    assertEquals(TransmissionMessageConstants.OK_INCREASE_GEAR, transmission1.getStatus());
  }

  /**
   * Case 21: Checks for the case where speed cannot be increased and gear must be increased.
   */
  @Test
  public void testIncreaseSpeedError() {
    this.increaseSpeedBy(5, this.transmission1);
    assertEquals(4, transmission1.getSpeed());
    assertEquals(1, this.transmission1.getGear());
    assertEquals(TransmissionMessageConstants.ERROR_INCREASING_SPEED_INCREASE_GEAR,
            transmission1.getStatus());
  }

  /**
   * Case 22: Checks for the case where speed cannot be increased as it has already reached maximum
   * speed limit of the vehicle.
   */
  @Test
  public void testIncreaseSpeedErrorMaxSpeed() {
    this.increaseSpeedBy(3, this.transmission1).increaseGear();
    this.increaseSpeedBy(13, this.transmission1).increaseGear();
    this.increaseSpeedBy(4, this.transmission1).increaseGear();
    this.increaseSpeedBy(6, this.transmission1).increaseGear();
    this.increaseSpeedBy(20, this.transmission1);
    assertEquals(40, transmission1.getSpeed());
    assertEquals(5, this.transmission1.getGear());
    assertEquals(TransmissionMessageConstants.ERROR_INCREASING_SPEED_MAX_SPEED,
            this.transmission1.getStatus());
  }


  /**
   * Case 23: Checks for the case where speed is decreased by 1.
   */
  @Test
  public void testDecreaseSpeedBy1() {
    this.transmission1.increaseSpeed();
    assertEquals(0, this.transmission1.decreaseSpeed().getSpeed());
    assertEquals(TransmissionMessageConstants.OK, transmission1.getStatus());
    assertEquals(1, this.transmission1.getGear());
  }

  /**
   * Case 24: Checks for the case where speed is allowed to be decreased with no need of decrease in
   * gear.
   */
  @Test
  public void testDecreaseSpeedOk() {
    this.increaseSpeedBy(3, transmission1);
    this.decreaseSpeedBy(2, transmission1);
    assertEquals(TransmissionMessageConstants.OK, transmission1.getStatus());
    assertEquals(1, this.transmission1.getSpeed());
    assertEquals(1, this.transmission1.getGear());
  }

  /**
   * Case 25: Checks for the case where speed is allowed to be decreased but driver may decrease the
   * gear.
   */
  @Test
  public void testDecreaseSpeedDecreaseGear() {
    this.increaseSpeedBy(3, this.transmission1).increaseGear();
    this.increaseSpeedBy(14, this.transmission1).increaseGear();
    this.transmission1.decreaseSpeed();
    assertEquals(TransmissionMessageConstants.OK_DECREASE_GEAR, transmission1.getStatus());
    assertEquals(16, this.transmission1.getSpeed());
    assertEquals(3, this.transmission1.getGear());
  }

  /**
   * Case 26: Checks for the case where speed cannot be decreased and gear must be decreased.
   */
  @Test
  public void testDecreaseSpeedError() {
    this.increaseSpeedBy(4, this.transmission1).increaseGear();
    this.increaseSpeedBy(12, this.transmission1).increaseGear();
    this.increaseSpeedBy(6, this.transmission1);
    this.decreaseSpeedBy(8, this.transmission1);
    assertEquals(TransmissionMessageConstants.ERROR_DECREASING_SPEED_DECREASE_GEAR,
            this.transmission1.getStatus());
    assertEquals(16, this.transmission1.getSpeed());
    assertEquals(3, this.transmission1.getGear());
  }


  /**
   * Case 27: Checks for the case where speed is allowed to be decreased but driver may decrease the
   * gear.
   */
  @Test
  public void testDecreaseSpeedMinSpeed() {
    this.increaseSpeedBy(2, this.transmission1);
    this.decreaseSpeedBy(3, this.transmission1);
    assertEquals(TransmissionMessageConstants.ERROR_DECREASING_SPEED_MIN_SPEED,
            this.transmission1.getStatus());
    assertEquals(0, this.transmission1.getSpeed());
    assertEquals(1, this.transmission1.getGear());
  }

  /**
   * Case 28: Tests for the case where speed is increased to max and again decreased to min.
   */
  @Test
  public void testIncreaseSpeedMaxDecreaseSpeedMin() {
    assertEquals(TransmissionMessageConstants.ERROR_DECREASING_SPEED_MIN_SPEED,
            this.transmission1.decreaseSpeed().getStatus());
    assertEquals(0, this.transmission1.getSpeed());
    this.increaseSpeedBy(3, this.transmission1).increaseGear();
    assertEquals(2, this.transmission1.getGear());
    this.increaseSpeedBy(13, this.transmission1).increaseGear();
    assertEquals(3, this.transmission1.getGear());
    this.increaseSpeedBy(4, this.transmission1).increaseGear();
    assertEquals(4, this.transmission1.getGear());
    this.increaseSpeedBy(6, this.transmission1).increaseGear();
    assertEquals(40, this.increaseSpeedBy(14, this.transmission1).getSpeed());
    assertEquals(5, this.transmission1.getGear());
    this.increaseSpeedBy(20, this.transmission1);
    assertEquals(TransmissionMessageConstants.ERROR_INCREASING_SPEED_MAX_SPEED,
            this.transmission1.getStatus());
    assertEquals(30, this.decreaseSpeedBy(10, this.transmission1).getSpeed());
    assertEquals(4, this.transmission1.decreaseGear().getGear());
    assertEquals(22, this.decreaseSpeedBy(8, this.transmission1).getSpeed());
    assertEquals(3, this.transmission1.decreaseGear().getGear());
    assertEquals(17, this.decreaseSpeedBy(5, this.transmission1).getSpeed());
    assertEquals(2, this.transmission1.decreaseGear().getGear());
    assertEquals(3, this.decreaseSpeedBy(14, this.transmission1).getSpeed());
    assertEquals(1, this.transmission1.decreaseGear().getGear());
    assertEquals(0, this.decreaseSpeedBy(4, this.transmission1).getSpeed());
    assertEquals(TransmissionMessageConstants.ERROR_DECREASING_SPEED_MIN_SPEED,
            this.transmission1.getStatus());
  }

  /**
   * Case 29: Checks for the case where gear is allowed to be increased with no need of increasing
   * speed.
   */
  @Test
  public void testIncreaseGearOk() {
    this.increaseSpeedBy(4, transmission1);
    this.transmission1.increaseGear();
    assertEquals(TransmissionMessageConstants.OK, this.transmission1.getStatus());
    assertEquals(4, this.transmission1.getSpeed());
    assertEquals(2, this.transmission1.getGear());
  }

  /**
   * Case 30: Checks for the case where gear is not allowed to be increased as speed needs to be
   * increased first.
   */
  @Test
  public void testIncreaseGearError() {
    this.increaseSpeedBy(2, transmission1);
    this.transmission1.increaseGear();
    assertEquals(TransmissionMessageConstants.ERROR_INCREASING_GEAR_INCREASE_SPEED,
            this.transmission1.getStatus());
    assertEquals(2, this.transmission1.getSpeed());
    assertEquals(1, this.transmission1.getGear());
  }

  /**
   * Case 31: Checks for the case where gear is not allowed to be increased as it has reached
   * maximum gear.
   */
  @Test
  public void testIncreaseGearMaxGear() {
    this.increaseSpeedBy(3, this.transmission1).increaseGear();
    this.increaseSpeedBy(13, this.transmission1).increaseGear();
    this.increaseSpeedBy(4, this.transmission1).increaseGear();
    this.increaseSpeedBy(6, this.transmission1).increaseGear();
    this.increaseSpeedBy(20, this.transmission1);
    this.transmission1.increaseGear();
    assertEquals(TransmissionMessageConstants.ERROR_INCREASING_GEAR_MAX_GEAR,
            this.transmission1.getStatus());
    assertEquals(40, this.transmission1.getSpeed());
    assertEquals(5, this.transmission1.getGear());
  }

  /**
   * Case 32: Checks for the case where gear is allowed to be decreased with no need of decreasing
   * speed.
   */
  @Test
  public void testDecreaseGearOk() {
    this.increaseSpeedBy(4, this.transmission1).increaseGear();
    this.transmission1.decreaseGear();
    assertEquals(TransmissionMessageConstants.OK, this.transmission1.getStatus());
    assertEquals(4, this.transmission1.getSpeed());
    assertEquals(1, this.transmission1.getGear());
  }

  /**
   * Case 33: Checks for the case where gear is not allowed to be decreased as speed needs to be
   * decreased first.
   */
  @Test
  public void testDecreaseGearError() {
    this.increaseSpeedBy(3, this.transmission1).increaseGear();
    this.increaseSpeedBy(12, this.transmission1);
    this.transmission1.decreaseGear();
    assertEquals(TransmissionMessageConstants.ERROR_DECREASING_GEAR_DECREASE_SPEED,
            this.transmission1.getStatus());
    assertEquals(15, this.transmission1.getSpeed());
    assertEquals(2, this.transmission1.getGear());
  }

  /**
   * Case 34: Checks for the case where gear is not allowed to be decreased as it has reached
   * minimum gear.
   */
  @Test
  public void testDecreaseGearMinGear() {
    this.transmission1.decreaseGear();
    assertEquals(TransmissionMessageConstants.ERROR_DECREASING_GEAR_MIN_GEAR,
            this.transmission1.getStatus());
    assertEquals(0, this.transmission1.getSpeed());
    assertEquals(1, this.transmission1.getGear());
  }

  /**
   * Case 35: Tests for the case where gear is increased to max and again decreased to min.
   */
  @Test
  public void testIncreaseGearMaxDecreaseGearMin() {
    assertEquals(TransmissionMessageConstants.ERROR_DECREASING_GEAR_MIN_GEAR,
            this.transmission1.decreaseGear().getStatus());
    assertEquals(1, this.transmission1.getGear());
    this.increaseSpeedBy(3, this.transmission1).increaseGear();
    assertEquals(2, this.transmission1.getGear());
    this.increaseSpeedBy(13, this.transmission1).increaseGear();
    assertEquals(3, this.transmission1.getGear());
    this.increaseSpeedBy(4, this.transmission1).increaseGear();
    assertEquals(4, this.transmission1.getGear());
    this.increaseSpeedBy(6, this.transmission1).increaseGear();
    assertEquals(5, this.transmission1.getGear());
    assertEquals(TransmissionMessageConstants.ERROR_INCREASING_GEAR_MAX_GEAR,
            this.transmission1.increaseGear().getStatus());
    assertEquals(4, this.transmission1.decreaseGear().getGear());
    this.decreaseSpeedBy(5, this.transmission1);
    assertEquals(3, this.transmission1.decreaseGear().getGear());
    this.decreaseSpeedBy(5, this.transmission1);
    assertEquals(2, this.transmission1.decreaseGear().getGear());
    this.decreaseSpeedBy(15, this.transmission1);
    assertEquals(1, this.transmission1.decreaseGear().getGear());
    assertEquals(TransmissionMessageConstants.ERROR_DECREASING_GEAR_MIN_GEAR,
            this.transmission1.decreaseGear().getStatus());

  }


}
