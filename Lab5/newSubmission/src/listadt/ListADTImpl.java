package listadt;

import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements the listadt.ListADT
 * interface.
 */
public class ListADTImpl<T> implements ListADT<T> {
  private GenericListADTNode<T> head;

  public ListADTImpl() {
    head = new GenericEmptyNode();
  }

  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  @Override
  public int getSize() {
    return head.count();
  }

  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    } else throw new IllegalArgumentException("Invalid index");

  }

  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ListADTImpl(head.map(converter));
  }

  /**
   * Returns immutable version of this class. Current head of this list is mapped to a new head
   * object and this method returns the immutable list initialised with this head. Any changes in
   * the head of this class won't replicate in the returned immutable instance.
   *
   * @return an object of type ListADT containing immutable list instance.
   */
  @Override
  public <R> ListADT<R> getImmutableList() {
    GenericListADTNode<T> newNode = this.head.map(node -> node);
    return new ImmutableListADTImpl(newNode);
  }

  /**
   * Returns the mutable version of this class. Current head of this list is mapped to a new head
   * object and this method returns the mutable list initialised with this head. This method
   * therefore creates replica of this list which supports same operations that could be performed
   * by the calling object but the changes in the new object would not be reflected in the original
   * object.
   *
   * @return an object of type ListADT containing mutable list instance.
   */
  @Override
  public <R> ListADT<R> getMutableList() {
    GenericListADTNode<T> newNode = this.head.map(node -> node);
    return new ListADTImpl(newNode);
  }

  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}