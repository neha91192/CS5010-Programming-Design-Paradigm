package decoder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 */
public class CodeSymbol extends GenericCode {
  /**
   * List of codes that are children of the current code. The possible number of children at each
   * level would be the total values defined in {@link CodeValues}.
   */
  private Map<Character, Code> children;
    CodeSymbol(String code) {
    super(code);
    children = new HashMap<>();
  }

  /**
   * Assign calling node as current object. iterate over all the characters in the code and traverse
   * till the point current reaches second last node. Append new character of type {@link
   * CharacterSymbol} to the end as leaf node.
   */
  @Override
  public void addCode(Code characterSymbol, String code) throws IllegalStateException {
      //if it is the last code to be added, add character type code.
      if(code.length()==1){
        this.addCharacterNode(code.charAt(0), characterSymbol);
      } else {
        //if it is the transition node that needs to be added
        if(!this.getChildrenNodes().containsKey(code.charAt(0))){
          this.addTransitionNode(code.charAt(0));
        }
        this.getChildrenNodes().get(code.charAt(0)).addCode(characterSymbol,code.substring(1));
      }
  }


  /**
   * Iterate over all the codes in the message. If the current node has reached child node, append
   * code in the string builder and start over again from the root node.
   */
  @Override
  public String decode(String message) throws IllegalStateException {
    StringBuilder sb = new StringBuilder("");
    CodeSymbol current = this;

    for (Character character : message.toCharArray()) {
      Code val = current.children.get(character);
      if (val.getCharacterSymbol() != null) {
        sb.append(val.getCharacterSymbol());
        current = this;
      } else {
        current = (CodeSymbol) val;
      }
    }
    return sb.toString();
  }

//  @Override
//  public String allCodes() {
//    StringBuilder sb = new StringBuilder("");
//    StringBuilder codesAsString = new StringBuilder("");
//    CodeSymbol current = this;
//    for (Character child : this.codingSymbols.toCharArray()) {
//      sb.append(child);
//      Code val = current.children.get(child);
//      //check if this is character symbol type
//      if (val.getCharacterSymbol() != null) {
//        sb.reverse();
//        sb.append(":");
//        sb.append(val.getCharacterSymbol());
//        sb.reverse();
//        sb.append("\n");
//        codesAsString.append(sb.toString());
//        sb = new StringBuilder();
//      } else {
//        current = (CodeSymbol) current.children.get(child);
//      }
//    }
//    return codesAsString.toString();
//  }

  //dfs traversal
  private String traverse(Code currentNode) {
    //visit current
    //visit left
    //visit right
    return null;
  }

  @Override
  public boolean isCodeComplete() {
    CodeSymbol current = this;


    return false;
  }

  /**
   *
   * @return
   */
//  private boolean validateCode(String code) {
//    char[] array1 = code.toCharArray();
//    char[] array2 = this.codingSymbols.toCharArray();
//    Arrays.sort(array1);
//    Arrays.sort(array2);
////    return Arrays.equals(array1, array2);
//    return true;
//  }

  @Override
  protected boolean isCharacterSymbol(){
    return false;
  }


  @Override
  protected boolean isCodeSymbol(){
    return true;
  }

  @Override
  protected void addCharacterNode(Character character, Code code) {
    this.children.put(character,code);
  }

  @Override
  protected void addTransitionNode(Character character) {
    this.children.put(character, new CodeSymbol(this.codingSymbols));
  }
  @Override
  protected Map<Character, Code> getChildrenNodes(){
    return this.children;
  }

  @Override
  public <R> Code map(Function<Code,R> transform) {
    //CodeSymbol newNode = new CodeSymbol(transform.apply(this.data));
    for (Code child:children.values()) {
      //newNode.children.add(child.map(transform));
    }
    return null;
  }

  @Override
  public List<String> toList() {
    List<String> result = new ArrayList<>();
    for (Code child: children.values()) {
      result.addAll(child.toList());
    }
    return result;
  }

}
