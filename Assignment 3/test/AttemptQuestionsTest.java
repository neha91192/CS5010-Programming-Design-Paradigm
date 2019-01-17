import org.junit.BeforeClass;
import org.junit.Test;

import questionaire.EvaluationResult;
import questionaire.IQuestion;
import questionaire.LikertQuestion;
import questionaire.MultipleAnswersQuestion;
import questionaire.MultipleChoiceQuestion;
import questionaire.SampleQuestions;
import questionaire.YesNoQuestion;

import static org.junit.Assert.assertEquals;

/**
 * Test class to attempt all types of Questions under {@link IQuestion}.
 */
public class AttemptQuestionsTest {
  /**
   * Different Questions to attempt.
   */
  private static IQuestion yesNoQuestion1;
  private static IQuestion yesNoQuestion2;
  private static IQuestion likertQuestion1;
  private static IQuestion likertQuestion2;
  private static IQuestion multipleChoiceQuestion1;
  private static IQuestion multipleChoiceQuestion2;
  private static IQuestion multipleAnswerQuestion1;
  private static IQuestion multipleAnswerQuestion2;
  private static IQuestion multipleAnswerQuestion3;


  /**
   * Instantiates different type of Question objects. Questions have been taken from {@link
   * SampleQuestions}.
   */
  @BeforeClass
  public static void setUp() {
    yesNoQuestion1 = new YesNoQuestion(SampleQuestions.QUESTION_1, "Yes");
    yesNoQuestion2 = new YesNoQuestion(SampleQuestions.QUESTION_2, "NO");
    likertQuestion1 = new LikertQuestion(SampleQuestions.QUESTION_3);
    likertQuestion2 = new LikertQuestion(SampleQuestions.QUESTION_4);
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    multipleChoiceQuestion1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_5,
            options, "2");
    multipleChoiceQuestion2 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_5,
            options, "2");
    multipleAnswerQuestion1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, "1");
    multipleAnswerQuestion2 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "1 2");
    multipleAnswerQuestion3 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_3,
            options, "1 2 3 4");
  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptYesNoQuestionCorrect1() {
    String userAnswer = "Yes";
    String result = yesNoQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptYesNoQuestionCorrect2() {
    String userAnswer = "YES";
    String result = yesNoQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptYesNoQuestionCorrect3() {
    String userAnswer = "yes";
    String result = yesNoQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptYesNoQuestionCorrect4() {
    String userAnswer = "No";
    String result = yesNoQuestion2.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptYesNoQuestionCorrect5() {
    String userAnswer = "NO";
    String result = yesNoQuestion2.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptYesNoQuestionCorrect6() {
    String userAnswer = "no";
    String result = yesNoQuestion2.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is incorrect.
   */
  @Test
  public void testAttemptYesNoQuestionIncorrect1() {
    String userAnswer = "ddf32";
    String result = yesNoQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is incorrect.
   */
  @Test
  public void testAttemptYesNoQuestionIncorrect2() {
    String userAnswer = "yeah";
    String result = yesNoQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the correct attempt of Likert Question.
   */
  @Test
  public void testAttemptLikertCorrect1() {
    String userAnswer = "1";
    String result = likertQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the correct attempt of Likert Question.
   */
  @Test
  public void testAttemptLikertCorrect2() {
    String userAnswer = "2";
    String result = likertQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the correct attempt of Likert Question.
   */
  @Test
  public void testAttemptLikertCorrect3() {
    String userAnswer = "3";
    String result = likertQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the correct attempt of Likert Question.
   */
  @Test
  public void testAttemptLikertCorrect4() {
    String userAnswer = "4";
    String result = likertQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the correct attempt of Likert Question.
   */
  @Test
  public void testAttemptLikertCorrect5() {
    String userAnswer = "5";
    String result = likertQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the incorrect attempt of Likert Question.
   */
  @Test
  public void testAttemptLikertIncorrect1() {
    String userAnswer = "Agree";
    String result = likertQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the incorrect attempt of Likert Question.
   */
  @Test
  public void testAttemptLikertIncorrect2() {
    String userAnswer = "6";
    String result = likertQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the case where question is different.
   */
  @Test
  public void testAttemptLikertIncorrect3() {
    String userAnswer = "0";
    String result = likertQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the case where option is valid number but out of range.
   */
  @Test
  public void testAttemptLikertIncorrect4() {
    String userAnswer = "10";
    String result = likertQuestion2.evaluate(userAnswer);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptMultipleChoiceCorrect1() {
    String userAnswer = "2";
    String result = multipleChoiceQuestion1.evaluate(userAnswer);
    assertEquals(EvaluationResult.CORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptMultipleChoiceCorrect2() {
    String result = multipleChoiceQuestion1.evaluate(" 2");
    assertEquals(EvaluationResult.CORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptMultipleChoiceCorrect3() {
    String result = multipleChoiceQuestion1.evaluate("2 ");
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptMultipleChoiceCorrect4() {
    String result = multipleChoiceQuestion1.evaluate(" 2 ");
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is incorrect.
   */
  @Test
  public void testAttemptMultipleChoiceIncorrect1() {
    String userAnswer = "dd";
    String result = multipleChoiceQuestion2.evaluate(userAnswer);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is incorrect.
   */
  @Test
  public void testAttemptMultipleChoiceIncorrect2() {
    String userAnswer = "6";
    String result = multipleChoiceQuestion2.evaluate(userAnswer);
    assertEquals(EvaluationResult.INCORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is incorrect.
   */
  @Test
  public void testAttemptMultipleChoiceIncorrect3() {
    String userAnswer = "-1";
    String result = multipleChoiceQuestion2.evaluate(userAnswer);
    assertEquals(EvaluationResult.INCORRECT.name(), result);
  }


  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerCorrect1() {
    String result = multipleAnswerQuestion1.evaluate("1");
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerCorrect2() {
    String result = multipleAnswerQuestion2.evaluate("1 2");
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerCorrect3() {
    String result = multipleAnswerQuestion3.evaluate("1 2 3 4");
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerCorrect4() {
    String result = multipleAnswerQuestion1.evaluate("1 ");
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerCorrect5() {
    String result = multipleAnswerQuestion2.evaluate("1   2");
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerCorrect6() {
    String result = multipleAnswerQuestion3.evaluate("1  2 3  4 ");
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerIncorrect1() {
    String result = multipleAnswerQuestion1.evaluate("1 2");
    assertEquals(EvaluationResult.INCORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerIncorrect2() {
    String result = multipleAnswerQuestion2.evaluate("1 2 3 4");
    assertEquals(EvaluationResult.INCORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerIncorrect3() {
    String result = multipleAnswerQuestion3.evaluate("1 2");
    assertEquals(EvaluationResult.INCORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerIncorrect4() {
    String result = multipleAnswerQuestion1.evaluate("1 7 9");
    assertEquals(EvaluationResult.INCORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerIncorrect5() {
    String result = multipleAnswerQuestion1.evaluate("-1 2");
    assertEquals(EvaluationResult.INCORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerIncorrect6() {
    String result = multipleAnswerQuestion1.evaluate("hello 1 21");
    assertEquals(EvaluationResult.INCORRECT.name(), result);
  }

  /**
   * Checks for the case where answer is correct.
   */
  @Test
  public void testAttemptMultipleAnswerIncorrect7() {
    String result = multipleAnswerQuestion1.evaluate("1 21 o");
    assertEquals(EvaluationResult.INCORRECT.name(), result);
  }

}
