package listadt;

import java.util.function.Function;

/**
 * This interface represents a generic list. It is a generalization of the
 * BookListADT interface.
 *
 * We represent the type of data that this will work with a generic parameter
 * T. This is a placeholder for the actual data type.
 */
public interface ImmutableListADT<T> extends ListADT<T>{


  /**
   * Return the number of objects currently in this list
   * @return the size of the list
   */
  int getSize();


  /**
   * Get the (index)th object in this list
   * @param index the index of the object to be returned
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  T get(int index) throws IllegalArgumentException;

  /**
   * A general purpose map higher order function on this list, that returns
   * the corresponding list of type R.
   * @param converter the function that converts T into R
   * @param <R> the type of data in the resulting list
   * @return the resulting list that is identical in structure to this list,
   * but has data of type R
   */
  <R> ListADT<R> map(Function<T,R> converter);

  /**
   * Should not modify the immutable version
   * @param <R>
   * @return
   */
  <R> ListADT<R> getMutable();

  <R> ListADT<R> getImmutable();
}