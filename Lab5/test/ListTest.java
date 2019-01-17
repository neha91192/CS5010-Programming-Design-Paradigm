import org.junit.Before;
import org.junit.Test;

import listadt.ListADT;
import listadt.ListADTImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the generic list representation {@link ListADT} interface. Assuming the ListImpl works
 * correctly, it checks for the enhancement made for Immutable list.
 */
public class ListTest {
  private ListADT<Integer> mutable;
  private ListADT<Integer> immutable;


  /**
   * Sets the default mutable object.
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
   * Immutable instance can be derived only from Mutable instance. To get an immutable version,
   * please create a mutable list first. Once Immutable version is fetched from Mutable version, a
   * new mutable version can be derived with the same immutable object.
   */
  @Test
  public void testDemo() {
    //Create mutable list.
    ListADT<Integer> listADTMutable = new ListADTImpl<>();
    listADTMutable.addBack(50);
    listADTMutable.addFront(10);
    listADTMutable.addFront(15);
    listADTMutable.addBack(20);
    //Get immutable version of list.
    ListADT<Integer> listADTImmutable = listADTMutable.getImmutableList();
    //Get mutable version of the list from immutable version.
    ListADT<Integer> mutableFromImmutable = listADTImmutable.getMutableList();
    //checks if the data in the previous mutable list is same as the new mutable list.
    for (int i = 0; i < listADTMutable.getSize(); i++) {
      assertEquals(listADTImmutable.get(i), mutableFromImmutable.get(i));
    }
  }

  /**
   * Checks if the immutable version contains the same content as that of mutable.
   */
  @Test
  public void testGetImmutableSameContent() {
    immutable = mutable.getImmutableList();
    assertNotEquals(immutable, mutable);
    assertEquals(immutable.toString(), mutable.toString());
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
    assertNotEquals(immutable, mutable);
    assertEquals(immutable.toString(), mutable.toString());
    immutable.addFront(1);
  }

  /**
   * Checks if the immutable version cannot be modified through addBack operation.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testGetImmutableAddBack() {
    immutable = mutable.getImmutableList();
    assertEquals(immutable.toString(), mutable.toString());
    assertNotEquals(immutable, mutable);
    immutable.addBack(1);
  }

  /**
   * Checks if the immutable version cannot be modified through add operation.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testGetImmutableAdd() {
    immutable = mutable.getImmutableList();
    assertEquals(immutable.toString(), mutable.toString());
    assertNotEquals(immutable, mutable);
    immutable.add(1, 23);
  }

  /**
   * Checks if the immutable version cannot be modified through remove operation.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testGetImmutableRemove() {
    immutable = mutable.getImmutableList();
    assertEquals(immutable.toString(), mutable.toString());
    assertNotEquals(immutable, mutable);
    immutable.remove(5);
  }

  /**
   * Checks if the immutable version returns correct size.
   */
  @Test
  public void testImmutableGetSize() {
    immutable = mutable.getImmutableList();
    assertEquals(immutable.toString(), mutable.toString());
    assertNotEquals(immutable, mutable);
    assertEquals(4, immutable.getSize());
  }

  /**
   * Checks if the immutable version returns value for get method.
   */
  @Test
  public void testImmutableGet() {
    immutable = mutable.getImmutableList();
    assertEquals(immutable.toString(), mutable.toString());
    assertNotEquals(immutable, mutable);
    assertEquals(new Integer(10), immutable.get(1));
  }

  /**
   * Checks if the immutable version returns exception for invalid index in get method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImmutableGetInvalid1() {
    immutable = mutable.getImmutableList();
    assertEquals(immutable.toString(), mutable.toString());
    assertNotEquals(immutable, mutable);
    immutable.get(10);
  }

  /**
   * Checks if the immutable version returns exception for invalid index in get method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testImmutableGetInvalid2() {
    immutable = mutable.getImmutableList();
    assertEquals(immutable.toString(), mutable.toString());
    assertNotEquals(immutable, mutable);
    immutable.get(-10);
  }

  /**
   * Checks if the immutable version works correctly for map method. The original map of integer has
   * been converted to string and been asserted for each value of list in Integer to value in
   * String. String immutable version is also checked for immutability.
   */
  @Test
  public void testImmutableMap() {
    immutable = mutable.getImmutableList();
    assertNotEquals(immutable, mutable);
    assertEquals(immutable.toString(), mutable.toString());
    ListADT<String> immutableStringMap = immutable.map(num -> Integer.toString(num));
    for (int i = 0; i < immutableStringMap.getSize(); i++) {
      assertEquals(immutableStringMap.get(i), Integer.toString(immutable.get(i)));
    }

    //checks if the returned new immutable string version is not allowing add operation.
    try {
      immutableStringMap.addFront("30");
    } catch (UnsupportedOperationException e) {
      assertEquals(immutableStringMap.toString(), immutable.toString());
    }

    //checks if the returned new immutable string version is not allowing add operation.
    try {
      immutableStringMap.addBack("70");
    } catch (UnsupportedOperationException e) {
      assertEquals(immutableStringMap.toString(), immutable.toString());
    }
    //checks if the returned new immutable string version is not allowing add operation.
    try {
      immutableStringMap.add(immutableStringMap.getSize() - 1, "13");
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), immutableStringMap.toString());
    }

    //checks if the returned new immutable string version is not allowing remove operation.
    try {
      immutableStringMap.remove("10");
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), immutableStringMap.toString());
    }

  }

  /**
   * Checks if the immutable list implementation returns mutable version of itself. If mutable
   * operations are successfully made and no values in immutable list gets changed in that process.
   */
  @Test
  public void testGetMutable() {
    immutable = mutable.getImmutableList();
    ListADT<Integer> newMutable = immutable.getMutableList();
    assertNotEquals(newMutable, immutable);
    assertEquals(immutable.toString(), newMutable.toString());
    for (int i = 0; i < newMutable.getSize(); i++) {
      assertEquals(immutable.get(i), newMutable.get(i));
    }
    //checks if mutation is working correctly
    newMutable.remove(20);
    assertNotEquals(immutable.toString(), newMutable.toString());
    assertNotEquals(immutable.getSize(), newMutable.getSize());
    newMutable.add(3, 30);
    assertNotEquals(immutable.toString(), newMutable.toString());
    newMutable.addFront(20);
    assertNotEquals(immutable.toString(), newMutable.toString());
    assertNotEquals(immutable.getSize(), newMutable.getSize());
    newMutable.addBack(100);
    assertNotEquals(immutable.toString(), newMutable.toString());
    assertNotEquals(immutable.getSize(), newMutable.getSize());

    //fetches immutable version from the newMutable object
    ListADT<Integer> newImmutable = newMutable.getImmutableList();
    assertNotEquals(newImmutable, newMutable);
    assertEquals(immutable.toString(), newMutable.toString());
    for (int i = 0; i < newMutable.getSize(); i++) {
      assertEquals(newImmutable.get(i), newMutable.get(i));
    }

    //checks if the returned new immutable version is not allowing add operation.
    try {
      newImmutable.addFront(70);
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), newMutable.toString());
    }

    //checks if the returned new immutable version is not allowing add operation.
    try {
      newImmutable.addBack(70);
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), newMutable.toString());
    }
    //checks if the returned new immutable version is not allowing add operation.
    try {
      newImmutable.add(newImmutable.getSize() - 1, 16);
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), newMutable.toString());
    }

    //checks if the returned new immutable version is not allowing remove operation.
    try {
      newImmutable.remove(10);
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), newMutable.toString());
    }

  }

  /**
   * Test for toString implementation of Immutable list.
   */
  @Test
  public void testToString() {
    immutable = mutable.getImmutableList();
    assertEquals(immutable.toString(), mutable.toString());
    assertEquals("(5 10 15 20)", immutable.toString());
    assertEquals(immutable.getSize(), immutable.getSize());
  }

  /**
   * Checks if the getImmutableList works as expected.
   */
  @Test
  public void testGetImmutableFromImmutableList() {
    immutable = mutable.getImmutableList();
    assertEquals(immutable.toString(), mutable.toString());
    ListADT<Integer> newImmutable = immutable.getImmutableList();
    assertEquals(immutable.toString(), newImmutable.toString());
    //checks if the returned new immutable version is not allowing add operation.
    try {
      newImmutable.addFront(70);
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), newImmutable.toString());
    }

    //checks if the returned new immutable version is not allowing add operation.
    try {
      newImmutable.addBack(70);
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), newImmutable.toString());
    }
    //checks if the returned new immutable version is not allowing add operation.
    try {
      newImmutable.add(newImmutable.getSize() - 1, 16);
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), newImmutable.toString());
    }

    //checks if the returned new immutable version is not allowing add operation.
    try {
      newImmutable.remove(10);
    } catch (UnsupportedOperationException e) {
      assertEquals(immutable.toString(), newImmutable.toString());
    }

  }

  /**
   * Checks if the getMutableList works as expected.
   */
  @Test
  public void testGetMutableFromMutableList() {
    ListADT<Integer> newMutableList = mutable.getMutableList();
    assertEquals(newMutableList.toString(), mutable.toString());
    newMutableList.addFront(70);
    assertNotEquals(mutable.toString(), newMutableList.toString());
    newMutableList.addBack(70);
    assertNotEquals(mutable.toString(), newMutableList.toString());
    newMutableList.add(newMutableList.getSize() - 1, 16);
    assertNotEquals(mutable.toString(), newMutableList.toString());
    newMutableList.remove(10);
    assertNotEquals(immutable.toString(), newMutableList.toString());

  }


}
