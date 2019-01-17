import java.io.IOException;
import java.util.Scanner;

import howtoinvestfordummies.view.IInvestmentView;

public class MockView implements IInvestmentView {

  /**
   * Object required to transmit output.
   */
  private Appendable out;

  /**
   * Scanner object used to parse input data.
   */
  private Scanner scan;

  /**
   * Constructor for mock model.
   */
  public MockView(Readable in, Appendable out) {
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void transmitMessage(String message) {
    try {
      out.append(message);
      out.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Exception encountered in transmitting message");
    }
  }

  @Override
  public String read() {
    return scan.next();
  }

  @Override
  public boolean hasNextInput() {
    return false;
  }
}
