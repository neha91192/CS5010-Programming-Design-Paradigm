import freecell.model.Card;
import freecell.model.FreecellModel;
import freecell.model.FreecellOperations;

/**
 * Tests for {@link FreecellModel}. All the test cases for basic Freecell model are Abstracted at
 * {@link AbstractFreecellModelTest} level.
 */
public class FreecellModelTest extends AbstractFreecellModelTest {
  /**
   * Returns instance of {@link FreecellModel} when being called.
   *
   * @param cascade size of cascade pile.
   * @param open    size of open pile.
   * @return instance of FreecellModel.
   */
  @Override
  protected FreecellOperations<Card> freecellModel(int cascade, int open) {
    return FreecellModel.getBuilder().cascades(cascade).opens(open).build();
  }

  /**
   * Returns instance of {@link FreecellModel} when being called.
   *
   * @param cascade size of cascade pile.
   * @param open    size of open pile.
   * @return instance of FreecellModel.
   */
  @Override
  protected FreecellOperations<Card> freecellMultimoveModel(int cascade, int open) {
    return FreecellModel.getBuilder().cascades(cascade).opens(open).build();
  }
}
