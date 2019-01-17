package freecell.model;

/**
 * Implementation model class for {@link FreecellOperations}. It performs various operations to play
 * FreeCell game.
 */
public class FreecellModel extends AbstractFreecellModel {

  /**
   * Private constructor to set the values fetched from {@link FreecellOperationsBuilder}. It sets
   * the user-defined configuration values which includes size of the cascade and open piles. It
   * also initializes piles required to play this game.
   *
   * @param cascade    size of the cascade pile to be created
   * @param open       size of the open pile to be created
   * @param foundation size of the foundation pile to be created
   */
  private FreecellModel(int cascade, int open, int foundation) {
    super(cascade, open, foundation);
  }

  public static class FreecellOperationsBuilderImpl
          extends AbstractFreecellModel.FreecellOperationsBuilderImpl {
    /**
     * It will return the final instance of {@link FreecellOperations} object.
     *
     * @return FreecellOperations instance with the values set by this builder.
     */
    @Override
    public FreecellOperations<Card> build() {
      return new freecell.model.FreecellModel(cascadeSize, openSize, foundationSize);
    }
  }

  /**
   * This builder contains all the set of methods which could initialise data of this class and also
   * a build method which returns newly created instance of {@link FreecellModel}.
   *
   * @return an instance of {@link FreecellOperationsBuilder} which is the Builder of this class.
   */
  public static FreecellOperationsBuilder getBuilder() {
    return new FreecellModel.FreecellOperationsBuilderImpl();
  }

}
