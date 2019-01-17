package vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements {@link ManualTransmission} Interface which provides general manual transmission
 * operations required for driving a car.
 */
public class RegularManualTransmission implements ManualTransmission {
  /**
   * Amount by which speed needs to be incremented. For this implementation, the speed factor is 1.
   */
  private static final int SPEED_FACTOR = 1;

  /**
   * All possible Gears for this transmission. It may take from the values of range 1-5 inclusive,
   * and will have the corresponding high and low speed values for the respective gear.
   */
  private List<Gear> gearList;
  /**
   * Current speed of the vehicle. Lowest speed of the vehicle is 0 and the highest speed of the
   * vehicles is the maximum speed of the last gear.
   */
  private int speed;
  /**
   * Current gear of the vehicle. It may take from the values of range 1-5 inclusive.
   */
  private int gear;
  /**
   * Current status of the vehicle. For all the status messages, refer {@link
   * TransmissionMessageConstants} class.
   */
  private String status;

  /**
   * Constructor for RegularManualTransmission class. It sets up the initial transmission object
   * with the speed of value 0, gear of value 1, and status of value - "OK. everything is OK.". It
   * also accepts speed ranges (l1,h1,l2,h2..,l5,h5) defined by low and high for the set of 5
   * gearList and initializes gearList for this implementation. If the argument is unacceptable, it
   * throws {@link IllegalArgumentException}.
   *
   * @param l1 lowest speed limit for gear 1.
   * @param h1 highest speed limit for gear 1.
   * @param l2 lowest speed limit for gear 2.
   * @param h2 highest speed limit for gear 2.
   * @param l3 lowest speed limit for gear 3.
   * @param h3 highest speed limit for gear 3.
   * @param l4 lowest speed limit for gear 4.
   * @param h4 highest speed limit for gear 4.
   * @param l5 lowest speed limit for gear 5.
   * @param h5 highest speed limit for gear 5.
   * @throws IllegalArgumentException if any Illegal argument exception is thrown by
   *                                  checkArgumentsConstraint method.
   */
  public RegularManualTransmission(int l1, int h1, int l2, int h2, int l3, int h3, int l4, int h4,
                                   int l5, int h5) throws IllegalArgumentException {
    checkArgumentsConstraint(l1, h1, l2, h2, l3, h3, l4, h4, l5, h5);
    Gear gear1 = new Gear(l1, h1, 1);
    Gear gear2 = new Gear(l2, h2, 2);
    Gear gear3 = new Gear(l3, h3, 3);
    Gear gear4 = new Gear(l4, h4, 4);
    Gear gear5 = new Gear(l5, h5, 5);
    gearList = new ArrayList<>();
    gearList.add(gear1);
    gearList.add(gear2);
    gearList.add(gear3);
    gearList.add(gear4);
    gearList.add(gear5);
    this.gear = 1;
    this.status = TransmissionMessageConstants.OK;
    this.speed = 0;
  }

  /**
   * Checks for the different constraints defined by RegularManualTransmission and if any of them
   * doesn't satisfy, it throws {@link IllegalArgumentException}. It throws exceptions in the
   * following cases:
   * <ul>
   * <li><strong>Negative speed error: </strong> If any of the speed in the input parameter (lx
   * or hx) is negative.</li>
   * <li><strong>Lowest speed error: </strong> If the lowest speed in the input parameter
   * (l1) is not equal to 0.</li>
   * <li><strong>Gear speed range error: </strong> When the lowest speed of the given gear is
   * greater than highest speed of the same gear. </li>
   * <li><strong>Lower speed range error: </strong> When the lower speed of one gear is
   * greater than the lower speed of the next gear. </li>
   * <li><strong>Overlapping not found error: </strong> When the lower speed of one gear is
   * greater than the higher speed of the previous gear. Essentially, when there is no overlapping
   * between the speeds. </li>
   * </ul>
   *
   * @throws IllegalArgumentException in case any of the above exception occurs.
   */
  private void checkArgumentsConstraint(int l1, int h1, int l2, int h2, int l3, int h3, int l4, int
          h4, int l5, int h5) throws IllegalArgumentException {
    if (l1 < 0 || h1 < 0 || l2 < 0 || h2 < 0 || l3 < 0 || h3 < 0 || l4 < 0 || h4 < 0 || l5 < 0
            || h5 < 0) {
      throw new IllegalArgumentException(ExceptionMessageConstants.NEGATIVE_SPEED_ERROR);
    }
    if (l1 != 0) {
      throw new IllegalArgumentException(ExceptionMessageConstants.LOWEST_SPEED_ERROR);
    }
    if (l1 > h1 || l2 > h2 || l3 > h3 || l4 > h4 || l5 > h5) {
      throw new IllegalArgumentException(ExceptionMessageConstants.GEAR_SPEED_RANGE_ERROR);
    }
    if (l1 >= l2 || l2 >= l3 || l3 >= l4 || l4 >= l5) {
      throw new IllegalArgumentException(ExceptionMessageConstants.LOWER_SPEED_RANGE_ERROR);
    }
    if (l2 > h1 || l3 > h2 || l4 > h3 || l5 > h4) {
      throw new IllegalArgumentException(ExceptionMessageConstants.OVERLAPPING_NOT_FOUND_ERROR);
    }
  }

  /**
   * Returns current transmission status of the vehicle.
   *
   * @return a response containing status message of type String.
   */
  @Override
  public String getStatus() {
    return this.status;
  }

  /**
   * Returns current speed of the vehicle.
   *
   * @return int consisting of current speed of the vehicle.
   */
  @Override
  public int getSpeed() {
    return this.speed;
  }

  /**
   * Returns current value of the gear.
   *
   * @return int which contains current level of the gear.
   */
  @Override
  public int getGear() {
    return this.gear;
  }

  /**
   * This method increases the speed of the vehicle if and only if the new speed is within the range
   * of maximum allowable speed for the current gear x(less than or equal to lx or greater than or
   * equal to hx). Depending upon the current speed and gear value and how much the driver presses
   * the gas pedal to increase the speed, there can be the following cases:
   * <ul>
   * <li><strong>Case 1: OK </strong> Speed is successfully increased with no change of gear.
   * Status message is updated as "OK. everything is OK."</li>
   * <li><strong>Case 2: OK_INCREASE_GEAR </strong> Speed is increased successfully but the gear
   * can also be increased. Status message is updated as "OK: you may increase the gear."</li>
   * <li><strong>Case 3: ERROR_INCREASING_SPEED_INCREASE_GEAR </strong> Speed cannot be increased
   * as it has already reached the highest value for that gear. Status message is updated as "Cannot
   * increase speed, increase gear first."</li>
   * <li><strong>Case 4: ERROR_INCREASING_SPEED_MAX_SPEED </strong> Speed cannot be increased
   * as it has already reached the highest value for the maximum gear. Status message is updated as
   * "Cannot increase speed. Reached maximum speed."</li>
   * </ul>
   *
   * @return transmission object of type ManualTransmission containing current transmission details.
   */
  @Override
  public ManualTransmission increaseSpeed() {
    int newSpeed = this.getSpeed() + SPEED_FACTOR;
    boolean isNotLastGear = (this.gear != this.gearList.size());
    if (canIncreaseSpeed(newSpeed)) {
      this.speed = newSpeed;
      if (isNotLastGear && newSpeed >= getNextGearLowestSpeed()) {
        this.status = TransmissionMessageConstants.OK_INCREASE_GEAR;
      } else {
        this.status = TransmissionMessageConstants.OK;
      }
    } else if (isNotLastGear) {
      this.status = TransmissionMessageConstants.ERROR_INCREASING_SPEED_INCREASE_GEAR;
    } else {
      this.status = TransmissionMessageConstants.ERROR_INCREASING_SPEED_MAX_SPEED;
    }
    return this;
  }

  /**
   * This method decreases the speed of the vehicle if and only if the new speed is within the range
   * of minimum allowable speed for the current gear x(less than or equal to lx or greater than or
   * equal to hx). Depending upon the current speed and gear value and how much the driver releases
   * the gas pedal to decrease the speed, there can be the following cases:
   * <ul>
   * <li><strong>Case 1: OK </strong> Speed is successfully decreased with no change of gear.
   * Status message is updated as "OK. everything is OK."</li>
   * <li><strong>Case 2: OK_DECREASE_GEAR </strong> Speed is decreased successfully but the gear
   * can also be decreased. Status message is updated as "OK: you may decrease the gear."</li>
   * <li><strong>Case 3: ERROR_DECREASING_SPEED_DECREASE_GEAR </strong> Speed cannot be decreased
   * as it has already reached the minimum value for that gear. Status message is updated as "Cannot
   * decrease speed, decrease gear first."</li>
   * <li><strong>Case 4: ERROR_DECREASING_SPEED_MIN_SPEED </strong> Speed cannot be decreased
   * further as it has already reached the minimum value for the first gear which is 0. Status
   * message is updated as "Cannot decrease speed. Reached minimum speed."</li>
   * </ul>
   *
   * @return transmission object of type ManualTransmission containing current transmission details.
   */
  public ManualTransmission decreaseSpeed() {
    int newSpeed = this.getSpeed() - SPEED_FACTOR;
    boolean isNotFirstGear = (this.gear != this.gearList.get(0).getLevel());
    if (canDecreaseSpeed(newSpeed)) {
      this.speed = newSpeed;
      if (isNotFirstGear && newSpeed <= getPreviousGearHighestSpeed()) {
        this.status = TransmissionMessageConstants.OK_DECREASE_GEAR;
      } else {
        this.status = TransmissionMessageConstants.OK;
      }
    } else if (isNotFirstGear) {
      this.status = TransmissionMessageConstants.ERROR_DECREASING_SPEED_DECREASE_GEAR;
    } else {
      this.status = TransmissionMessageConstants.ERROR_DECREASING_SPEED_MIN_SPEED;
    }
    return this;
  }

  /**
   * Increases gear when the gear value is less than the maximum gear and the current speed is
   * allowed to be in the next gear range.
   *
   * @return ManualTransmission object containing current value of gear.
   */
  @Override
  public ManualTransmission increaseGear() {
    if (this.getGear() == gearList.size()) {
      this.status = TransmissionMessageConstants.ERROR_INCREASING_GEAR_MAX_GEAR;
    } else if (canIncreaseGear()) {
      this.gear = getNextGearValue();
      this.status = TransmissionMessageConstants.OK;
    } else {
      this.status = TransmissionMessageConstants.ERROR_INCREASING_GEAR_INCREASE_SPEED;
    }
    return this;
  }

  /**
   * Decreases gear only when the value is greater than minimum gear value and the current speed is
   * allowed to be in the previous gear range.
   *
   * @return ManualTransmission object containing current value of gear.
   */
  @Override
  public ManualTransmission decreaseGear() {
    if (this.getGear() == this.gearList.get(0).getLevel()) {
      this.status = TransmissionMessageConstants.ERROR_DECREASING_GEAR_MIN_GEAR;
    } else if (canDecreaseGear()) {
      this.gear = getPreviousGearValue();
      this.status = TransmissionMessageConstants.OK;
    } else {
      this.status = TransmissionMessageConstants.ERROR_DECREASING_GEAR_DECREASE_SPEED;
    }
    return this;
  }

  /**
   * Checks if it is permissible to increase speed. It checks if the newSpeed is still less than or
   * equal to the maximum speed value for the current gear.
   *
   * @return boolean indicating if speed can be increased.
   */
  private boolean canIncreaseSpeed(int newSpeed) {
    return (newSpeed <= this.gearList.get(this.gear - 1).getHigh());
  }

  /**
   * Checks if it is permissible to decrease speed. It checks if the newSpeed is still greater than
   * or equal to the minimum speed value for the current gear.
   *
   * @return boolean indicating if speed can be increased.
   */
  private boolean canDecreaseSpeed(int newSpeed) {
    return (newSpeed >= this.gearList.get(this.gear - 1).getLow());
  }

  /**
   * Checks if it is allowed to increase gear. If the current gear is between 1-4 inclusive, gear
   * can be increased.
   *
   * @return boolean indicating if gear can be increased.
   */
  private boolean canIncreaseGear() {
    return (this.getGear() < this.gearList.size() && getNextGearLowestSpeed() <= this.getSpeed());
  }

  /**
   * Checks if it is allowed to decrease gear. If the current gear is between 2-5 inclusive, gear
   * can be decreased.
   */
  private boolean canDecreaseGear() {
    return (this.getGear() > this.gearList.get(0).getLevel() && getPreviousGearHighestSpeed()
            >= this.getSpeed());
  }

  /**
   * Fetches the lowest speed for the next gear. Helps in identifying if the next gear range has
   * started or not.
   *
   * @return int containing value of the lowest speed in next gear.
   */
  private int getNextGearLowestSpeed() {
    return this.gearList.get(this.getGear()).getLow();
  }

  /**
   * Fetches highest speed for the previous gear. Helps in identifying if the previous gear range
   * has started or not.
   *
   * @return int containing value of the highest speed in previous gear.
   */
  private int getPreviousGearHighestSpeed() {
    return this.gearList.get(this.getGear() - 2).getHigh();
  }

  /**
   * Fetches the next gear level.
   *
   * @return int containing value of the next gear.
   */
  private int getNextGearValue() {
    return this.gearList.get(this.getGear()).getLevel();
  }

  /**
   * Fetches previous gear level.
   *
   * @return int containing value of the previous gear.
   */
  private int getPreviousGearValue() {
    return this.gearList.get(this.getGear() - 2).getLevel();
  }
}
