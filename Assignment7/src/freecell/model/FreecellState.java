package freecell.model;

/**
 * States for a particular Freecell game. <br> Each game is in a state at any point of time. The
 * state is given a String value.<br> Not started: The game has not yet started and has the value
 * Not Started. <br> Started: The game has started and has the value Started. <br> Game over: The
 * game is over and has the value Game Over. <br>
 */
public enum FreecellState {
  NOT_STARTED("Not Started"), STARTED("Started"), GAME_OVER("Game Over");
  private final String value;

  /**
   * A constructor that takes the value of a game's state as String.
   *
   * @param value is the value of the game's state
   */
  FreecellState(String value) {
    this.value = value;
  }

  /**
   * The value of the state of a game is returned, the values of game state acceptable for our deck
   * of Freecell game are "Not Started", "Started" and "Game Over".
   *
   * @return the value of state of game as String
   */
  public String getValue() {
    return this.value;
  }
}
