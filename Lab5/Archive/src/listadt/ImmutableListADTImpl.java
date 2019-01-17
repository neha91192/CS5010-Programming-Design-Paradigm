package listadt;

import java.util.function.Function;

public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {
  private GenericListADTNode head;

  private ImmutableListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * Creates an instance of immutable list with the given mutable list object.
   */
  public ImmutableListADTImpl(ListADT<T> listADT) {
    this.head = (GenericListADTNode) listADT.get(0);
  }

  @Override
  public final void addFront(T b) {
    throw new UnsupportedOperationException("Cannot add any element in Immutable list");
  }

  @Override
  public final void addBack(T b) {
    throw new UnsupportedOperationException("Cannot add any element in Immutable list");
  }

  @Override
  public final void add(int index, T b) {
    throw new UnsupportedOperationException("Cannot add any element in Immutable list");
  }

  @Override
  public int getSize() {
    return this.head.count();
  }

  @Override
  public final void remove(T b) {
    throw new UnsupportedOperationException("Cannot remove any element in Immutable list");
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    return (T) this.head.get(index);
  }

  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ImmutableListADTImpl<R>(head.map(converter));
  }

  @Override
  public <R> ListADT<R> getImmutable() {
    return new ImmutableListADTImpl<>(this.head);
  }

  /**
   * Check if the list returned
   */
  @Override
  public <R> ListADT<R> getMutable() {
    ListADT<R> listImpl = new ListADTImpl<>();
    GenericListADTNode<T> newNode = this.head.map(node -> node);
    listImpl.add(0, (R) newNode);
    return listImpl;

  }
}
