import org.junit.Before;
import org.junit.Test;

import listadt.ListADT;
import listadt.ListADTImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 */
public class ListTest {
  ListADT<Integer> mutable;
  ListADT<Integer> immutable;

  /**
   * Sets the default object.
   */
  @Before
  public void setup() {
    mutable = new ListADTImpl<>();
    mutable.addFront(20);
    mutable.addFront(15);
    mutable.addFront(10);
    mutable.addFront(5);
  }

  /**
   * Checks if the immutable version contains the same content as that of mutable.
   */
  @Test
  public void testGetImmutableSameContent() {
    immutable = mutable.getImmutableList();
    for (int i = 0; i < mutable.getSize(); i++) {
      assertEquals(immutable.get(i), mutable.get(i));
    }
  }

  /**
   * Checks if the immutable version cannot be modified through addFront operation.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testGetImmutableAddFront() {
    immutable = mutable.getImmutableList();
    immutable.addFront(1);
  }

  /**
   * Checks if the immutable version cannot be modified through addBack operation.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testGetImmutableAddBack() {
    immutable = mutable.getImmutableList();
    immutable.addBack(1);
  }

  /**
   * Checks if the immutable version cannot be modified through add operation.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testGetImmutableAdd() {
    immutable = mutable.getImmutableList();
    immutable.add(1, 23);
  }

  /**
   * Checks if the immutable version cannot be modified through remove operation.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testGetImmutableRemove() {
    immutable = mutable.getImmutableList();
    immutable.remove(5);
  }

  /**
   * Checks if the immutable version returns correct size.
   */
  @Test
  public void testImmutableGetSize() {
    immutable = mutable.getImmutableList();
    assertEquals(4, immutable.getSize());
  }

  /**
   * Checks if the immutable version returns value for get method.
   */
  @Test
  public void testImmutableGet() {
    immutable = mutable.getImmutableList();
    assertEquals(new Integer(10), immutable.get(1));
  }

  /**
   * Checks if the immutable version works correctly for map method. The original map of integer has
   * been converted to string and been asserted for each value of list in Integer to value in
   * String.
   */
  @Test
  public void testImmutableMap() {
    immutable = mutable.getImmutableList();
    ListADT<String> stringMap = immutable.map(num -> Integer.toString(num));
    for (int i = 0; i < stringMap.getSize(); i++) {
      assertEquals(stringMap.get(i), Integer.toString(immutable.get(i)));
    }
  }

  /**
   * Checks if the immutable list implementation returns mutable version of itself. If mutable
   * operations are successfully made and no values in immutable list gets changed in that process.
   */
  @Test
  public void testGetMutable() {
    immutable = mutable.getImmutableList();
    ListADT newMutable = immutable.getMutableList();
    for (int i = 0; i < mutable.getSize(); i++) {
      assertEquals(immutable.get(i), newMutable.get(i));
    }
    newMutable.remove(20);
    assertNotEquals(immutable.toString(), newMutable.toString());
    newMutable.add(3, 30);
    assertNotEquals(immutable.toString(), newMutable.toString());
    newMutable.add(4, 20);
    assertNotEquals(immutable.toString(), newMutable.toString());
  }

  /**
   * Demonstrate how your classes should be used.
   */

}
