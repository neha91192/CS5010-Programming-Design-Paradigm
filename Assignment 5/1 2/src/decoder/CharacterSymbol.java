package decoder;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CharacterSymbol extends GenericCode {
  /**
   * String character which is the character symbol derived from list of unique codes.
   */
  private String symbol;

  public CharacterSymbol(String symbol){
    super(symbol);
    this.symbol = symbol;
  }

  @Override
  public String getCharacterSymbol() {
    return this.symbol;
  }

  @Override
  protected boolean isCharacterSymbol(){
    return true;
  }

  @Override
  protected boolean isCodeSymbol(){
    return false;
  }

  //In LeafNode:
//  @Override
//  public <R> Code map(Function<Code,R> transform) {
//    return new CharacterSymbol(transform.apply(this));
////    return null;
//  }

  @Override
  public List<String> toList() {
    List<String> result = new ArrayList<>();
    result.add(this.getCharacterSymbol());
    return result;
  }
}
