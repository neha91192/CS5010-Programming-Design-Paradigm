package decoder;

public class DecoderImpl implements Decoder {

  private Code root;

  public DecoderImpl(String code){
    root = new CodeSymbol(code);
  }

  @Override
  public void addCode(Character symbol, String code) throws IllegalStateException {
    Code character = new CharacterSymbol(Character.toString(symbol));
    root.addCode(character, code);

  }

  @Override
  public String decode(String message) throws IllegalStateException {
    return root.decode(message);
  }

  @Override
  public String allCodes() {
    return root.allCodes();
  }



  @Override
  public boolean isCodeComplete() {
    return false;
  }


}
