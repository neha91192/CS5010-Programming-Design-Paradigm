package freecell.model;

/**
 * This is the interface for the FreecellOperationsBuilder. It is responsible for creating {@link
 * FreecellOperations} instances. Depending upon default configuration of the implementation classes
 * of FreecellOperationsBuilder Interface, the Freecell model will be initialized with appropriate
 * values.
 */
public interface FreecellOperationsBuilder {
  /**
   * Sets the value of cascade piles.
   *
   * @param c size of cascade pile in int.
   * @return instance of the builder class.
   */
  FreecellOperationsBuilder cascades(int c);

  /**
   * Sets the value of open piles.
   *
   * @param o size of open pile in int.
   * @return instance of the builder class.
   */
  FreecellOperationsBuilder opens(int o);

  /**
   * Build method finally returns the instance of required class. Depending upon what values were
   * given in the past for cascade or open, it initializes object for {@link FreecellOperations}.
   */
  <K> FreecellOperations<K> build();
}
