package encoder;

/**
 * Data class which represents elements in priority queue used to generate Huffman coding. It
 * contains value of the symbol and frequency of the symbol. A symbol value could contain one or
 * more characters.
 */
public class Symbol {
  /**
   * Initialises value for Symbol data.
   *
   * @param value     of type String.
   * @param frequency of type Integer.
   */
  Symbol(String value, Integer frequency) {
    this.setFrequency(frequency);
    this.setValue(value);
  }

  /**
   * Value of the symbol present in the priority queue.
   */
  private String value;

  /**
   * Returns value of the symbol present in the priority queue.
   *
   * @return value as String.
   */
  public String getValue() {
    return value;
  }

  /**
   * Sets value of the symbol present in the queue.
   *
   * @param value as String.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Represents frequency of the symbol.
   */
  private Integer frequency;

  /**
   * Returns frequency of the symbol.
   *
   * @return frequency as Integer.
   */
  public Integer getFrequency() {
    return frequency;
  }

  /**
   * Sets frequency of the symbol.
   *
   * @param frequency as Integer.
   */
  public void setFrequency(Integer frequency) {
    this.frequency = frequency;
  }


}
