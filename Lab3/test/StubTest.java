import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grades.Gradebook;
import grades.StudentRecord;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Gradebook functions. Testing on 30 students with different range of percentage points
 * as input values.
 */
public class StubTest {

  private Gradebook records;
  private List<Double> weights1;
  private List<Double> weights2;
  private List<Double> finalScores;
  private List<String> letterGrades;
  private List<String> firstNames, lastNames;
  private final int NumAssignments = 4;
  private List<String> letters;
  private List<Double> thresholds;

  private List<Integer> letterGradeCount;

  /**
   * Initializes values for testing gradebook class. Values are retrieved from input array and set
   * in individual lists for testing purpose.
   */
  @Before
  public void setup() {

    letters = Arrays.asList(new String[]{"F", "D-", "D", "D+", "C-", "C", "C+",
            "B-", "B", "B+", "A-", "A"});
    thresholds = Arrays.asList(new Double[]{60.0, 63.0, 66.0, 70.0, 73.0, 76.0,
            80.0, 83.0, 86.0, 90.0, 93.0, 100.0});
    records = new Gradebook(letters, thresholds);
    finalScores = new ArrayList<Double>();
    letterGrades = new ArrayList<String>();
    firstNames = new ArrayList<String>();
    lastNames = new ArrayList<String>();
    int i = 0;
    while (i < input.length) {
      String fName = input[i];
      String lName = input[i + 1];
      double[] points = new double[NumAssignments];
      for (int j = 0; j < NumAssignments; j++) {
        points[j] = 100 * Double.parseDouble(input[i + 2 + j]);
      }

      finalScores.add(Double.parseDouble(input[i + 2 + NumAssignments]));
      letterGrades.add(input[i + 2 + NumAssignments + 1]);
      firstNames.add(fName);
      lastNames.add(lName);

      i = i + 4 + NumAssignments;
      records.addStudent(new StudentRecord(fName, lName, points));
    }

    weights1 = new ArrayList<Double>();
    weights1.add(20.0);
    weights1.add(30.0);
    weights1.add(40.0);
    weights1.add(10.0);

    //stores the count of number of students letter for all the letter grades.
    letterGradeCount = new ArrayList<>();
    letterGradeCount.add(4);
    letterGradeCount.add(2);
    letterGradeCount.add(1);
    letterGradeCount.add(3);
    letterGradeCount.add(1);
    letterGradeCount.add(1);
    letterGradeCount.add(1);
    letterGradeCount.add(2);
    letterGradeCount.add(3);
    letterGradeCount.add(1);
    letterGradeCount.add(4);
    letterGradeCount.add(7);

  }

  /**
   * Tests the final score value returned from the Gradebook class. (Professor's Test case).
   */
  @Test
  public void testIndividualGrades() {
    List<Double> finals = records.getFinalScores(weights1);
    for (int i = 0; i < finalScores.size(); i++) {
      assertEquals(finalScores.get(i), finals.get(i), 0.001);
    }
  }


  /**
   * Tests the output which is returned after counting final scores for all the students.
   */
  @Test
  public void testGetFinalScores() {
    List<Double> finalScoresOutput = records.getFinalScores(weights1);
    for (int i = 0; i < finalScores.size(); i++) {
      assertEquals(finalScores.get(i), finalScoresOutput.get(i), 0.001);
    }
  }

  /**
   * Tests for Counting number of people for all the letter grades. Count for all the letter grades
   * has been initialized in letterGradeCount List.
   */
  @Test
  public void testCountLetterGrade() {
    for (int i = 0; i < letters.size(); i++) {
      Integer count = records.countLetterGrade(letters.get(i), weights1);
      assertEquals(count, letterGradeCount.get(i));
    }

  }


  /**
   * Tests for the case where firstname and lastname of students need to be returned.
   */
  @Test
  public void testGetStudentNames() {
    List<String> studentNames = records.getStudentNames();
    for (int i = 0; i < firstNames.size(); i++) {
      assertEquals(studentNames.get(i),
              firstNames.get(i) + " " + lastNames.get(i));
    }
  }

  /**
   * Tests for the average score for the name that does not exist in the gradebook.
   */
  @Test
  public void testAverageScoreForName1() {
    Double averageScoreForName = records.averageScoreForName("Rafael", weights1);
    assertEquals(0.0, averageScoreForName, 0.001);
  }

  /**
   * Tests for the average score of name that is repeated in the list.
   */
  @Test
  public void testAverageScoreForName2() {
    Double averageScoreForName = records.averageScoreForName("Amit", weights1);
    assertEquals(69.51292, averageScoreForName, 0.001);
  }

  /**
   * Tests for the average score of the name that is repeated in the list.
   */
  @Test
  public void testAverageScoreForName3() {
    Double averageScoreForName = records.averageScoreForName("Neha", weights1);
    assertEquals(19.4084, averageScoreForName, 0.001);
  }

  /**
   * Tests for the average score of the name that is not repeated in the list.
   */
  @Test
  public void testAverageScoreForName4() {
    Double averageScoreForName = records.averageScoreForName("Clark", weights1);
    assertEquals(92.5368, averageScoreForName, 0.001);
  }

  /**
   * Tests for the case where firstname and lastname of students need to be returned.
   */
  @Test
  public void testCountAboveAverage() {
    long countAboveAverage = records.countAboveAverage(weights1);
    assertEquals(20, countAboveAverage);
  }


  // Data from the Excel file, to be used for testing
  String[] input = {"Amit"
          , "Shesh"
          , "0.920833333"
          , "0.8"
          , "0.656410256"
          , "0.218181818"
          , "70.8548951"
          , "C-"
          , "Clark"
          , "Freifeld"
          , "1"
          , "0.888888889"
          , "0.9"
          , "0.987012987"
          , "92.53679654"
          , "A-"
          , "Aniruddha"
          , "Tapas"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Aditya"
          , "Sathyanarayan"
          , "0.783333333"
          , "0.8"
          , "0.333333333"
          , "0"
          , "53"
          , "F"
          , "Ritika"
          , "Nair"
          , "1"
          , "0.911111111"
          , "0.955555556"
          , "0.92987013"
          , "94.85425685"
          , "A"
          , "Alice"
          , "Wonderland"
          , "0.89"
          , "0.65"
          , "0.98"
          , "0.93"
          , "85.8"
          , "B"
          , "Bob"
          , "Marley"
          , "0.82"
          , "0.69"
          , "0.99"
          , "0.8799"
          , "85.499"
          , "B"
          , "Charlie"
          , "Brown"
          , "0.844323"
          , "1"
          , "0.933221"
          , "0.97232"
          , "93.9385"
          , "A"
          , "Dan"
          , "Walter"
          , "0.89923"
          , "0.897823"
          , "0.969777778"
          , "0.919334545"
          , "92.90374656"
          , "A-"
          , "Jane"
          , "Morse"
          , "0.933333333"
          , "1"
          , "0.977777778"
          , "0.745454545"
          , "95.23232323"
          , "A"
          , "Neil"
          , "Backman"
          , "0"
          , "0"
          , "0"
          , "0"
          , "0"
          , "F"
          , "Nore"
          , "White"
          , "1"
          , "1"
          , "1"
          , "1"
          , "100"
          , "A"
          , "Serena"
          , "Williams"
          , "0.7889"
          , "1"
          , "1"
          , "1"
          , "95.778"
          , "A"
          , "Arya"
          , "Stark"
          , "1"
          , "0.899"
          , "0.8799"
          , "0.7999"
          , "90.165"
          , "A-"
          , "Roger"
          , "Brown"
          , "0.890567"
          , "0.7458"
          , "0.8892"
          , "0.2339"
          , "78.09234"
          , "C+"
          , "Neha"
          , "Shukla"
          , "-1"
          , "-1"
          , "-1"
          , "-1"
          , "-100"
          , "#N/A"
          , "Amitabh"
          , "Bacchan"
          , "0.54322"
          , "0.8"
          , "0.687712"
          , "0.211433"
          , "64.48721"
          , "D"
          , "Amit"
          , "Sinha"
          , "0.122121"
          , "0.5634"
          , "0.98823"
          , "0.987012987"
          , "68.74374987"
          , "D+"
          , "Amit"
          , "Kumar"
          , "0.891666667"
          , "0.566666667"
          , "0.711111111"
          , "0.566233766"
          , "68.94011544"
          , "D+"
          , "Neha"
          , "Shah"
          , "0.7888223"
          , "0.55334"
          , "0.5432323"
          , "0.62332"
          , "60.339138"
          , "D-"
          , "Neha"
          , "Nair"
          , "1"
          , "0.9888"
          , "0.955555556"
          , "1"
          , "97.88622222"
          , "A"
          , "John"
          , "Doe"
          , "0.89"
          , "0.65"
          , "0.88"
          , "0.83"
          , "80.8"
          , "B-"
          , "Jonny"
          , "Marley"
          , "0.82"
          , "0.69"
          , "0.99"
          , "0.8799"
          , "85.499"
          , "B"
          , "Charles"
          , "Brown"
          , "0.844323"
          , "1"
          , "0.633221"
          , "0.7873232"
          , "80.088532"
          , "B-"
          , "Dany"
          , "Don"
          , "0.89923"
          , "0.827823"
          , "0.969777778"
          , "0.919334545"
          , "90.80374656"
          , "A-"
          , "Dany"
          , "Morse"
          , "0.933333333"
          , "0.742"
          , "0.977777778"
          , "0.745454545"
          , "87.49232323"
          , "B+"
          , "Neil"
          , "Backman"
          , "0"
          , "0"
          , "0"
          , "0"
          , "0"
          , "F"
          , "Aryan"
          , "Veer"
          , "0.56532"
          , "0.4273"
          , "1"
          , "1"
          , "74.1254"
          , "C"
          , "White"
          , "Walker"
          , "1"
          , "1"
          , "1"
          , "1"
          , "100"
          , "A"
          , "Maria"
          , "Charles"
          , "1"
          , "1"
          , "0.221"
          , "0.2112"
          , "60.952"
          , "D-"
  };


}