package howtoinvestfordummies.model;

/**
 * This interface is responsible for providing features to persist the data.
 */
public interface Persistable {
  /**
   * This method provides save feature to the implementation class. Whenever an implementor wants to
   * use this interface, this method can be overridden to provide save logic as need.
   *
   * @param name of the file to be saved.
   */
  void save(String name);

  /**
   * This method provides restore feature to the implementation class. Whenever an implementor wants
   * to use this interface, this method can be overridden to provide retrieval logic from a file.
   *
   * @param name of the file from which data needs to be retrieved.
   */
  void restore(String name);
}
