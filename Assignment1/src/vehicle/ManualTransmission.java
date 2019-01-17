package vehicle;

/**
 * This is an Interface for vehicles that captures Manual Transmission - increase or decrease in
 * speed or gear to give back appropriate actions. The speed is distributed within the set of gears
 * and on the basis of what the speed of vehicle is, current gear can be/needs to be increased or
 * decreased.
 * <p>If the speed of the vehicle is attempted to increase beyond the current gear allowable
 * range, vehicle speed cannot be increased (in that case, gear needs to be increased first) and
 * will prompt the driver to increase the gear before increasing the speed of the vehicle. The
 * engine "revvs" in this case.</p>
 * <p>Similarly, if the vehicle speed is attempted to decrease lower than the current gear
 * allowable range, the engine will "Knock" at this situation.</p>
 * <p>ManualTransmission Interface contracts the following operations: </p>
 * <ul>
 * <li><strong>getStatus </strong></li>
 * <li><strong>getSpeed </strong></li>
 * <li><strong>getGear </strong></li>
 * <li><strong>increaseSpeed </strong></li>
 * <li><strong>decreaseSpeed </strong></li>
 * <li><strong>increaseGear </strong></li>
 * <li><strong>decreaseGear </strong></li>
 * </ul>
 */
public interface ManualTransmission {

  /**
   * Returns the current status of the vehicle as a String. Refer this {@link
   * TransmissionMessageConstants} class for more details about the statuses that could be
   * returned.
   *
   * @return current status of the vehicle as String.
   */
  String getStatus();

  /**
   * Returns current speed of the vehicle as a whole number.
   *
   * @return current speed of the vehicle as int.
   */
  int getSpeed();

  /**
   * Returns current value of the gear as a whole number.
   *
   * @return current gear of the vehicle as int.
   */
  int getGear();

  /**
   * This method increases the speed of the vehicle by a certain amount without changing gears. If
   * the speed can be increased, the status is updated in the transmission object along with the new
   * speed of the transmission. If the speed cannot be increased, then the speed remains the same
   * and the status is updated with the appropriate status and returned back as a response. Changing
   * of speed, i.e., amount by which the speed is increased or decreased on pressing gas pedal
   * depends upon the actual implementation of this interface.
   *
   * @return an object of type {@link ManualTransmission} containing current transmission details.
   */
  ManualTransmission increaseSpeed();

  /**
   * This method decreases the speed of the vehicle by a certain amount without changing gears. If
   * the speed can be decreased, the status is updated in the transmission object along with the new
   * speed of the transmission. If the speed cannot be decreased, then the speed remains the same
   * and the status is updated with the appropriate message and returned back as a response.
   * Changing of speed, i.e., amount by which the speed is increased or decreased on pressing gas
   * pedal depends upon the actual implementation of this interface.
   *
   * @return an object of type {@link ManualTransmission} containing current transmission details.
   */
  ManualTransmission decreaseSpeed();

  /**
   * This method increases the gear by one with no change in the current speed. Also, if the gear
   * cannot be increased, speed should remain the same and appropriate message should be updated in
   * the status field of transmission object which is sent as a response of this method.
   *
   * @return an object of type {@link ManualTransmission} containing current transmission details.
   */
  ManualTransmission increaseGear();

  /**
   * This method decreases the gear by one with no change in the current speed. Also, if the gear
   * cannot be decreased, speed should remain the same and appropriate message should be updated in
   * the status field of transmission object which is sent as a response of this method.
   *
   * @return an object of type {@link ManualTransmission} containing current transmission details.
   */
  ManualTransmission decreaseGear();
}
