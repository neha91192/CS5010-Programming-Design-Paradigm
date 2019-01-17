package decoder;


import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GenericCode implements Code {
  String codingSymbols;

  GenericCode(String codingSymbols){
    this.codingSymbols = codingSymbols;
  }
  /**
   * Assign calling node as current object. iterate over all the characters in the code and traverse
   * till the point current reaches second last node. Append new character of type {@link
   * CharacterSymbol} to the end as leaf node.
   */
  @Override
  public void addCode(Code characterSymbol, String code) throws IllegalStateException {

  }


  /**
   *
   * @param message
   * @return
   * @throws IllegalStateException
   */
  @Override
  public String decode(String message) throws IllegalStateException {
    if(message==null || message.equals("")){
      return "";
    }
    StringBuilder sb = new StringBuilder("");
    if(this.isCodeSymbol()){
      CodeSymbol codeSymbol = (CodeSymbol)this;
      if(!codeSymbol.getChildrenNodes().containsKey(message.charAt(0))){
        //decoded string not found
        throw new IllegalStateException("Cannot be decoded");
      }
      GenericCode value = (GenericCode)codeSymbol.getChildrenNodes().get(message.charAt(0));
      if(value.isCharacterSymbol()){
        sb.append(value.getCharacterSymbol());
      } else if(value.isCodeSymbol()){
        sb.append(value.decode(message.substring(1)));
      }

    }
    return sb.toString();
  }

  @Override
  public boolean isCodeComplete() {
    return false;
  }

  /**
   *
   * @return
   * If the Code is of type
   * Code Symbol then iterate through its Childrens by calling the same method
   * Character symbol then print the Character symbol and the string passed and return.
   */
  @Override
  public String allCodes() {
    StringBuilder sb = new StringBuilder("");
    if(this.isCharacterSymbol()){
      sb.append(":");
      sb.append(this.getCharacterSymbol());
      return sb.toString();
    }
    for(Character code: this.getChildrenNodes().keySet()){
      sb.append(code);
      sb.append(this.getChildrenNodes().get(code).allCodes());
    }
    return sb.toString();
  }

//  private void allCode(String ip, GenericCode c){
//    if(this.isCharacterSymbol()){
//      System.out.println(this.getCharacterSymbol() + ip);
//      return;
//    }
//    for (Character code : this.getChildrenNodes().keySet()){
//      ip = ip + code;
//      allCode(ip, code);
//    }
//  }

//  @Override
//  public String allCodes() {
//    StringBuilder sb = new StringBuilder("");
//    if(this.isCharacterSymbol()){
//     return this.getCharacterSymbol();
//    }
//    for(Character code: this.getChildrenNodes().keySet()){
//      sb.append(this.getChildrenNodes().get(code).allCodes());
//      sb.append(this.getCharacterSymbol());
//      sb.append(code);
//    }
//    return sb.toString();
//  }

  @Override
  public String getCharacterSymbol() {
    return null;
  }

  @Override
  public <String> Code map(Function<Code, String> transform) {
    return null;
  }

  @Override
  public List<String> toList() {
    return null;
  }

  protected boolean isCharacterSymbol(){
    return false;
  }
  protected boolean isCodeSymbol(){
    return false;
  }

  protected void addCharacterNode(Character character, Code code) {

  }

  protected void addTransitionNode(Character character) {

  }

  protected Map<Character, Code> getChildrenNodes(){
    return null;
  }

}
