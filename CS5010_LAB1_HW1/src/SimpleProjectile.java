
/**
 * Implementation class representing Newtonian particle. It contains initial 2D positions and
 * velocities and calculates position value of any Newtonian particle for a given time t.
 */
public class SimpleProjectile implements Particle {
  private float initialX;
  private float initialY;
  private float initialVx;
  private float initialVy;
  private float acceleration;

  /**
   * Construct a SimpleProjectile object that has the provided initial x-coordinate position,
   * y-coordinate position, initial velocity Vx and Vy.
   *
   * @param initialX  the x-coordinate value for initial position of the particle.
   * @param initialY  the y-coordinate value for initial position of the particle.
   * @param initialVx the initial velocity Vx of the particle.
   * @param initialVy the initial velocity Vy of the particle.
   */
  public SimpleProjectile(float initialX, float initialY, float initialVx, float initialVy) {
    this.initialX = initialX;
    this.initialY = initialY;
    this.initialVx = initialVx;
    this.initialVy = initialVy;
    this.acceleration = 9.81f;
  }

  /**
   * Return the state of this particle as a formatted string. The format of the string is as
   * follows:
   * <code>At time [t]: position is ([x],[y])</code> where,
   * <ul>
   * <li>[t] is the time passed to this method, rounded to two decimal
   * places.</li>
   * <li>[x] is the x-coordinate of the position of this particle at this
   * time, rounded to two decimal places. </li>
   * <li>[y] is the y-coordinate of the position of this particle at this
   * time, rounded to two decimal places.
   * </li> </ul>
   * At any time t, the state is calculated using the following formula:
   * <p>s=ut+1/2at^2</p>
   * <p>Since x component will have no effect of gravity, we take a=0 for calculating x component
   * of displacement.</p>
   *
   * @param time the time at which the state must be obtained.
   * @return the state of the particle as a string formatted as above.
   */
  @Override
  public String getState(float time) {
    float x;
    float y;
    if (time < 0.00f || initialVy < 0.00f) {
      x = this.initialX;
      y = this.initialY;
    } else {
      float restTime = calculateRestTime();
      x = (time > restTime) ? calculateX(restTime) : calculateX(time);
      y = calculateY(time);
    }
    String state = "At time " + String.format("%.2f", time) + ": position is ("
            + String.format("%.2f", x) + "," + String.format("%.2f", y) + ")";
    return state;
  }

  /**
   * Calculates final x component value.
   *
   * @param time in float used to calculate final y position.
   * @return float containing final y position.
   */
  private float calculateX(float time) {
    float s = this.initialVx * time;
    return s + this.initialX;
  }

  /**
   * Calculates final y component value.
   *
   * @param time in float used to calculate final y position.
   * @return float containing final y position.
   */
  private float calculateY(float time) {
    float s = (float) this.initialVy * time - (float) ((0.50f) * (this.acceleration) * time * time);
    if (s <= 0.00f) {
      s = 0.00f;
    }
    return s + initialY;
  }

  /**
   * Calculates time at which particle will come to rest.
   *
   * @return float containing time at which particle will come to rest.
   */
  private float calculateRestTime() {
    float timeForX = (float) ((2.00f * initialVy) / this.acceleration);
    return timeForX;
  }
}
