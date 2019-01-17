import listadt.GenericElementNode;
import listadt.GenericEmptyNode;
import listadt.GenericListADTNode;
import listadt.ListADT;
import listadt.ListADTImpl;

public class ListTest {
  public static void main(String[] args) {
    ListADT listADT = new ListADTImpl();
    Integer a = new Integer(23);
    Integer b = new Integer(34);
    Integer c = new Integer(56);
    GenericListADTNode node = new GenericElementNode(a, new GenericEmptyNode());
    node.addBack(b);
    node.addBack(c);
    listADT.addFront(node);
    ListADT immutable = new ImmutableListADTImpl(listADT);
    ListADT mutable = ((ImmutableListADTImpl) immutable).getMutable();
    mutable.addFront(new Integer(89));
    immutable.get(2);
  }
}
