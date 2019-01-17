package vehicle;

/**
 * Constants class designated to keep set of RegularManualTransmission messages which will be
 * returned depending upon the transmission actions.
 */
public final class TransmissionMessageConstants {

  /**
   * Response message indicating if the speed or gear was changed successfully.
   */
  public static final String OK = "OK: everything is OK.";
  /**
   * Response message indicating if the speed was increased successfully but requires increasing the
   * gear.
   */
  public static final String OK_INCREASE_GEAR = "OK: you may increase the gear.";
  /**
   * Response message indicating if the speed was decreased successfully but requires decreasing the
   * gear.
   */
  public static final String OK_DECREASE_GEAR = "OK: you may decrease the gear.";
  /**
   * Response message indicating if there was an error in increasing the speed and requires
   * increasing the gear first.
   */
  public static final String ERROR_INCREASING_SPEED_INCREASE_GEAR =
          "Cannot increase speed, increase gear first.";
  /**
   * Response message indicating if there was an error in decreasing the speed and requires
   * decreasing the gear first.
   */
  public static final String ERROR_DECREASING_SPEED_DECREASE_GEAR =
          "Cannot decrease speed, decrease gear first.";
  /**
   * Response message indicating if there was an error in increasing the gear and requires
   * increasing the speed first.
   */
  public static final String ERROR_INCREASING_GEAR_INCREASE_SPEED =
          "Cannot increase gear, increase speed first.";
  /**
   * Response message indicating if there was an error in decreasing the gear and requires
   * decreasing the speed first.
   */
  public static final String ERROR_DECREASING_GEAR_DECREASE_SPEED =
          "Cannot decrease gear, decrease speed first.";
  /**
   * Response message indicating if there was an error in increasing the speed since the vehicle has
   * already attained the maximum speed limit.
   */
  public static final String ERROR_INCREASING_SPEED_MAX_SPEED =
          "Cannot increase speed. Reached maximum speed.";
  /**
   * Response message indicating if there was an error in decreasing the speed and the vehicle
   * cannot go below the minimum speed of the vehicle.
   */
  public static final String ERROR_DECREASING_SPEED_MIN_SPEED =
          "Cannot decrease speed. Reached minimum speed.";
  /**
   * Response message indicating an error in increasing gear since the vehicle has already attained
   * maximum gear limit.
   */
  public static final String ERROR_INCREASING_GEAR_MAX_GEAR =
          "Cannot increase gear. Reached maximum gear.";
  /**
   * Response message indicating an error in decreasing gear since the vehicle cannot go below the
   * minimum gear value.
   */
  public static final String ERROR_DECREASING_GEAR_MIN_GEAR =
          "Cannot decrease gear. Reached minimum gear.";


}
