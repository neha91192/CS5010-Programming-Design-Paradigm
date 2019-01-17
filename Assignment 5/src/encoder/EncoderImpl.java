package encoder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Implements Huffman Encoding algorithm to determine shortest encoding for any given plaintext.
 */
public class EncoderImpl implements Encoder {

  /**
   * Contains frequency of each character as values.
   */
  private TreeMap<Character, Integer> frequencyTable;
  /**
   * Contains coding symbols corresponding to each character.
   */
  private Map<Character, String> codingTable;
  /**
   * Contains list of supported coding symbols.
   */
  private List<Character> codingSymbols;

  /**
   * Queue used to generate encoding symbols as per Huffman encoding algorithm.
   */
  private PriorityQueue<Symbol> queue;

  /**
   * Initialises several values required for computing Huffman encoding.
   *
   * @param supportedSymbols Takes coding values supported for Huffman encoder.
   */
  public EncoderImpl(String supportedSymbols) {
    frequencyTable = new TreeMap<>();
    codingTable = new HashMap<>();
    codingSymbols = new ArrayList<>();
    for (Character c : supportedSymbols.toCharArray()) {
      if (!codingSymbols.contains(c)) {
        codingSymbols.add(c);
      }
    }
  }

  /**
   * Reads txt file from system and produces its content as String.
   *
   * @param fileName of type String.
   */
  private String readSource(String fileName) throws IOException {
    BufferedReader bufferedReader = null;
    StringBuilder sb = new StringBuilder();
    try {
      InputStream inputStream = new FileInputStream(new File(
              "res/" + fileName));
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      throw new IOException("File cannot be read");
    }
    return sb.toString();
  }

  /**
   * Sets up the frequency table by parsing each character of the file content.
   *
   * @param fileContent in String
   */
  private void setUpFrequencyTable(String fileContent) {
    int frequency;
    for (int i = 0; i < fileContent.length(); i++) {
      frequency = 0;
      Character symbol = fileContent.charAt(i);
      if (frequencyTable.containsKey(symbol)) {
        frequency = frequencyTable.get(symbol);
      }
      frequencyTable.put(symbol, ++frequency);
    }
  }


  /**
   * Builds up the table which stores character and the encoded symbol values of that character.
   */
  private void setUpCodingTable() {
    for (Character key : frequencyTable.keySet()) {
      codingTable.put(key, "");
    }
  }

  /**
   * Pre-populates queue required for the generating huffman codes.
   */
  private void initializeQueue() {
    queue = new PriorityQueue<>(frequencyTable.size(), new SymbolComparator());
    for (Character key : frequencyTable.keySet()) {
      Symbol symbol = new Symbol(Character.toString(key), frequencyTable.get(key));
      queue.add(symbol);
    }
  }

  /**
   * For each nth element in coding symbol, it pops n items from the queue and then add prefix of
   * the coding symbol to the encoding values. Updates codingTable which maintains record of each
   * character and encoding values. At the end, it concatenates all the symbol string encountered
   * with their combined frequency, and put them back in to the queue. Since the queue maintains its
   * ordering, we don't have to handle any ordering if a new element has been added to the queue.
   */
  private void processQueue() {
    while (queue.size() > 1) {
      int frequency = 0;
      StringBuilder sb = new StringBuilder();
      //poll for all coding symbols, for binary 0 and 1.
      for (Character code : codingSymbols) {
        Symbol symbol = queue.poll();
        if (symbol != null) {
          sb.append(symbol.getValue());
          frequency = frequency + symbol.getFrequency();
          //append for all characters in string
          for (Character c : symbol.getValue().toCharArray()) {
            String value = codingTable.get(c);
            value = code + value;
            codingTable.put(c, value);
          }
        }
      }
      Symbol newSymbol = new Symbol(sb.toString(), frequency);
      queue.add(newSymbol);
    }
  }

  /**
   * Parses each character in the file content and returns the encoded value in String.
   *
   * @return encoded value in String.
   */
  private String generateCode(String fileContent) {
    StringBuilder sb = new StringBuilder();
    for (Character c : fileContent.toCharArray()) {
      if (codingTable.containsKey(c)) {
        sb.append(codingTable.get(c));
      }
    }
    writeFile(sb.toString());
    return sb.toString();
  }

  /**
   * Prints the file in case it is present.
   */
  private void writeFile(String fileContent) {
    String encodedFileName = "res/encodings.txt";
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(encodedFileName, true));
      writer.newLine();
      writer.append(fileContent);
      writer.newLine();
      writer.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }


  }

  /**
   * Takes file name as input parameter and processes each character in the file to perform Huffman
   * Encoding.
   */
  @Override
  public String encode(String fileName) throws IOException {
    String fileContent = readSource(fileName);
    setUpFrequencyTable(fileContent);
    setUpCodingTable();
    initializeQueue();
    processQueue();
    return generateCode(fileContent);
  }

  /**
   * Returns codingTable which contains each character and corresponding encoding symbols for the
   * same.
   *
   * @return codingTable for this encoder.
   */
  @Override
  public Map<Character, String> getCodingTable() {
    return this.codingTable;
  }
}
