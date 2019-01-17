package grades;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stores values related to students which includes firstName, lastName, and percentagePoints scored
 * in each assignments/tests.
 */
public class StudentRecord {
  private final String firstName;
  private final String lastName;
  private final List<Double> percentagePoints;

  /**
   * Initialises values for students. Creates a student record entry.
   *
   * @param fName  firstName of student.
   * @param lName  lastName of student.
   * @param points percentage points for student.
   */
  public StudentRecord(String fName, String lName, double[] points) {
    this.firstName = fName;
    this.lastName = lName;
    this.percentagePoints =
            Arrays.stream(points).boxed().collect(Collectors.toList());
  }

  /**
   * Returns first name of student.
   *
   * @return firstName of type String.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns last name of student.
   *
   * @return lastName of type String.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Calculates grades of each assignment in percentage points by multiplying it with the weights
   * for the same. The final score returned is out of 100.
   *
   * @param weights containing weightage of the particular assignment.
   */
  public double getGrade(List<Double> weights) throws IllegalArgumentException {
    if (weights.size() != percentagePoints.size()) {
      throw new IllegalArgumentException("Number of weights is not equal to "
              + "number of assignments");
    }
    double score = 0;
    for (int i = 0; i < weights.size(); i++) {
      score += weights.get(i) * percentagePoints.get(i);
    }
    return score / 100;
  }
}
