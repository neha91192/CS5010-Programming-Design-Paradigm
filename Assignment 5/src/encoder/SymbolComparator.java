package encoder;

import java.util.Comparator;

/**
 * This comparator implementation sorts Symbol as per their frequency values. If the frequency of
 * first symbol is less than the frequency of second, it returns 1. If the frequency of first symbol
 * is greater than the frequency of second symbol
 */
public class SymbolComparator implements Comparator<Symbol> {

  /**
   * Compares two symbols objects on the basis of frequency of symbol. If two symbol has same
   * frequency, it considers lexicographic order of the value of symbol.
   */
  @Override
  public int compare(Symbol symbol1, Symbol symbol2) {
    if (symbol1.getFrequency() > symbol2.getFrequency()) {
      return 1;
    } else if (symbol1.getFrequency() < symbol2.getFrequency()) {
      return -1;
    }
    return symbol1.getValue().compareTo(symbol2.getValue());
  }
}
