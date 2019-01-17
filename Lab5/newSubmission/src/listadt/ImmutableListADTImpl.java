package listadt;

import java.util.function.Function;

/**
 * <p>The purpose of this implementation is to create a new immutable list from pre-populated head
 * node and supports no changes in the structure of new list whatsoever. This implementation extends
 * {@link ListADT} and implements all its required operations in an immutable fashion.
 */
public class ImmutableListADTImpl<T> implements ListADT<T> {
  /**
   * Generic node which maintains starting node for this immutable list.
   */
  private final GenericListADTNode head;

  /**
   * Initialises an immutable version of the list with the provided parameter head node.
   *
   * @param head of type GenericListNode.
   */
  ImmutableListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * Any call to this method would return {@link UnsupportedOperationException}. Since the class
   * supports operations allowed to keep immutability of the list, this operation is not allowed.
   *
   * @param b the object to be added to the front of this list.
   */
  @Override
  public final void addFront(T b) {
    throw new UnsupportedOperationException("Cannot add any element in Immutable list");
  }

  /**
   * Any call to this method would return {@link UnsupportedOperationException}. Since the class
   * supports operations allowed to keep immutability of the list, this operation is not allowed.
   *
   * @param b the object to be added to the front of this list.
   */
  @Override
  public final void addBack(T b) {
    throw new UnsupportedOperationException("Cannot add any element in Immutable list");
  }

  /**
   * Any call to this method would return {@link UnsupportedOperationException}. Since the class
   * supports operations allowed to keep immutability of the list, this operation is not allowed.
   *
   * @param b the object to be added to the front of this list.
   */
  @Override
  public final void add(int index, T b) {
    throw new UnsupportedOperationException("Cannot add any element in Immutable list");
  }

  /**
   * Returns the number of nodes of the list fetched from {@link GenericListADTNode}
   * implementation.
   *
   * @return size of the list in integer.
   */
  @Override
  public int getSize() {
    return this.head.count();
  }

  /**
   * Any call to this method would return {@link UnsupportedOperationException}. Since the class
   * supports operations allowed to keep immutability of the list, this operation is not allowed.
   *
   * @param b the object to be added to the front of this list.
   */
  @Override
  public final void remove(T b) {
    throw new UnsupportedOperationException("Cannot remove any element in Immutable list");
  }

  /**
   * Returns the element at the given index. Since the data object of the list returned can be
   * mutable, it is returned as it is.
   *
   * @param index the index of the object to be returned.
   * @throws IllegalArgumentException in case the index provided is incorrect.
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    return (T) this.head.get(index);
  }

  /**
   * Converts an immutable list of type T to R by mapping each node in the list.
   *
   * @param converter the function that converts T into R.
   */
  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ImmutableListADTImpl<R>(head.map(converter));
  }

  /**
   * Returns the immutable list version of itself with the same head node.
   */
  @Override
  public <R> ListADT<R> getImmutableList() {
    return new ImmutableListADTImpl<>(this.head);
  }

  /**
   * Returns the mutable list version of itself starting from the head node. It first maps all the
   * nodes of the list and transforms it into a new head. This new head is passed to the add method
   * {@link ListADTImpl} class which returns mutable instance.
   */
  @Override
  public <R> ListADT<R> getMutableList() {
    ListADT<R> listImpl = new ListADTImpl();
    GenericListADTNode<R> newHead = this.head.map(node -> node);
    for (int i = 0; i < this.getSize(); i++) {
      listImpl.add(i, newHead.get(i));
    }
    return listImpl;

  }

  /**
   * Returns String implementation of the immutable list.
   */
  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}
