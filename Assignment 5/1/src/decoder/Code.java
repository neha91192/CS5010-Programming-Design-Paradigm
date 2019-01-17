package decoder;

import java.util.List;
import java.util.function.Function;

public interface Code {
  /**
   *
   * @param characterCode
   * @param code
   * @throws IllegalStateException
   */
  void addCode(Code characterCode, String code) throws IllegalStateException;

  /**
   *
   * @param message
   * @return
   * @throws IllegalStateException
   */
  String decode(String message) throws IllegalStateException;

  boolean isCodeComplete();

  String allCodes();

  String getCharacterSymbol();

  <R> Code map(Function<Code,R> transform);

  /**
   * Convert the tree into a list.
   * @return the resulting list
   */
  List<String> toList();
}
