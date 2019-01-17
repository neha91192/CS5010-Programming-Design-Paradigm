package vehicle;

/**
 * Constants class designated to keep set of Exceptions messages which will be returned depending
 * upon the transmission actions.
 */
public final class ExceptionMessageConstants {
  /**
   * Error message indicating if speed provided is negative.
   */
  public static final String NEGATIVE_SPEED_ERROR = "Speeds cannot be negative. Please provide a "
          + "positive number";
  /**
   * Error message indicating if the lowest speed is not 0. gear.
   */
  public static final String LOWEST_SPEED_ERROR = "The lowest speed of the vehicle should be 0";
  /**
   * Error message indicating if the lower speed value of a given gear is greater than higher speed
   * value of that gear.
   */
  public static final String GEAR_SPEED_RANGE_ERROR = "Lower speed cannot be greater than the "
          + "higher speed for a given gear";
  /**
   * Error message indicating if the lower speed value of a given gear is greater than lower speed
   * value of the next gears.
   */
  public static final String LOWER_SPEED_RANGE_ERROR = "Lower speed of a given gear cannot be "
          + "greater than lower speed of next gears.";
  /**
   * Error message indicating if the lower speed value of the next gear is not less than or equal to
   * highest speed of the previous gear.
   */
  public static final String OVERLAPPING_NOT_FOUND_ERROR = "Lowest speed of next gear cannot be "
          + "greater than highest speed of the previous gear. There should be some overlapping "
          + "between two adjacent gears.";

}
