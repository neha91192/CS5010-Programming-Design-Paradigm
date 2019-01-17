package vehicle;

/**
 * This class entails Gear related attributes.
 */
public class Gear {
  /**
   * Lowest speed this gear level can have.
   */
  private int low;
  /**
   * Highest speed this gear level can have.
   */
  private int high;
  /**
   * Level of this Gear. It can take from 1 to 5 values all inclusive.
   */
  private int level;

  /**
   * Constructor for Gear class.
   *
   * @param low   lowest speed for the gear.
   * @param high  highest speed for the gear.
   * @param level level of the gear.
   */
  public Gear(int low, int high, int level) {
    setLow(low);
    setHigh(high);
    setLevel(level);
  }

  /**
   * Returns lowest speed value this Gear instance can have.
   *
   * @return int containing lowest speed value of this gear.
   */
  public int getLow() {
    return low;
  }

  /**
   * Sets lowest speed value this Gear instance can have.
   *
   * @param low int containing lowest speed value of this gear.
   */
  public void setLow(int low) {
    this.low = low;
  }

  /**
   * Returns highest speed value this Gear instance can have.
   *
   * @return int containing highest value of this gear.
   */
  public int getHigh() {
    return high;
  }

  /**
   * Sets highest speed value this Gear instance can have.
   *
   * @param high of type int containing highest value of this gear.
   */
  public void setHigh(int high) {
    this.high = high;
  }

  /**
   * Returns level of this gear. It can take from 1 to 5 values all inclusive.
   *
   * @return level of type int containing the current gear level.
   */
  public int getLevel() {
    return level;
  }

  /**
   * Sets the level for this gear. It can take from 1 to 5 values all inclusive.
   *
   * @param level of type int containing the current gear level.
   */
  public void setLevel(int level) {
    this.level = level;
  }

}
