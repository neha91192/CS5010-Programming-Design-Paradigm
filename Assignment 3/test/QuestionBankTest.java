import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import questionaire.IQuestion;
import questionaire.LikertQuestion;
import questionaire.MultipleAnswersQuestion;
import questionaire.MultipleChoiceQuestion;
import questionaire.SampleQuestions;
import questionaire.YesNoQuestion;

import static org.junit.Assert.assertEquals;

/**
 * Test class to create question bank.
 */
public class QuestionBankTest {
  private static IQuestion yesNoQuestion1;
  private static IQuestion yesNoQuestion2;
  private static IQuestion yesNoQuestion3;

  private static IQuestion likertQuestion1;
  private static IQuestion likertQuestion2;
  private static IQuestion likertQuestion3;

  private static IQuestion multipleChoiceQuestion;
  private static IQuestion multipleChoiceQuestion2;

  private static IQuestion multipleAnswerQuestion;
  private static IQuestion multipleAnswerQuestion1;
  private static IQuestion multipleAnswerQuestion2;

  private static IQuestion[] questionaire1;
  private static IQuestion[] questionaire2;
  private static IQuestion[] questionaire3;
  private static IQuestion[] questionaire4;
  private static IQuestion[] questionaire5;

  /**
   * Instantiates different type of Questions and puts them up in Questionaire object. The
   * alphabetical order of Question 1 to Question 5 present in {@link SampleQuestions} class is - 1
   * 5 2 3 4.
   */
  @BeforeClass
  public static void setUp() {
    setUpYesNoQuestion();
    setUpLikertQuestion();
    setUpMultipleChoiceQuestion();
    setUpMultipleAnswersQuestion();
    setUpQuestionaire();
  }

  /**
   * Sets up Yes No type of Questions.
   */
  private static void setUpYesNoQuestion() {
    yesNoQuestion1 = new YesNoQuestion(SampleQuestions.QUESTION_1, "Yes");
    yesNoQuestion2 = new YesNoQuestion(SampleQuestions.QUESTION_2, "Yes");
    yesNoQuestion3 = new YesNoQuestion(SampleQuestions.QUESTION_1, "Yes");
  }

  /**
   * Sets up Likert Questions.
   */
  private static void setUpLikertQuestion() {
    likertQuestion1 = new LikertQuestion(SampleQuestions.QUESTION_2);
    likertQuestion2 = new LikertQuestion(SampleQuestions.QUESTION_5);
    likertQuestion3 = new LikertQuestion(SampleQuestions.QUESTION_4);
  }

  /**
   * Sets up Multiple Choice Questions.
   */
  private static void setUpMultipleChoiceQuestion() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    multipleChoiceQuestion = new MultipleChoiceQuestion(SampleQuestions.QUESTION_5,
            options, "2");
    multipleChoiceQuestion2 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
            options, "2");
  }

  /**
   * Sets up Multiple Answers Questions.
   */
  private static void setUpMultipleAnswersQuestion() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    multipleAnswerQuestion = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, "1 2");
    multipleAnswerQuestion1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_4,
            options, "1 2 4");
    multipleAnswerQuestion2 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_5,
            options, "1 2 3");
  }

  /**
   * Sets up Questionaire.
   */
  private static void setUpQuestionaire() {
    questionaire1 = new IQuestion[]{multipleAnswerQuestion, yesNoQuestion1, multipleChoiceQuestion,
                                    likertQuestion2, yesNoQuestion2};
    questionaire2 = new IQuestion[]{yesNoQuestion2, multipleAnswerQuestion, likertQuestion1,
                                    yesNoQuestion1, multipleChoiceQuestion, likertQuestion2};
    questionaire3 = new IQuestion[]{yesNoQuestion2, multipleAnswerQuestion2, yesNoQuestion3};
    questionaire4 = new IQuestion[]{multipleAnswerQuestion2, yesNoQuestion1,
                                    multipleAnswerQuestion1};
    questionaire5 = new IQuestion[]{multipleChoiceQuestion2, yesNoQuestion1,
                                    multipleAnswerQuestion1, multipleAnswerQuestion2,
                                    likertQuestion3};
  }

  /**
   * Checks for the correct question bank ordering.
   */
  @Test
  public void testCreateQuestionBank1() {
    Arrays.sort(questionaire1);

    assertEquals(yesNoQuestion1, questionaire1[0]);
    assertEquals(SampleQuestions.QUESTION_1, questionaire1[0].getQuestionText());

    assertEquals(yesNoQuestion2, questionaire1[1]);
    assertEquals(SampleQuestions.QUESTION_2, questionaire1[1].getQuestionText());

    assertEquals(likertQuestion2, questionaire1[2]);
    assertEquals(SampleQuestions.QUESTION_5, questionaire1[2].getQuestionText());

    assertEquals(multipleChoiceQuestion, questionaire1[3]);
    assertEquals(SampleQuestions.QUESTION_5, questionaire1[3].getQuestionText());

    assertEquals(multipleAnswerQuestion, questionaire1[4]);
    assertEquals(SampleQuestions.QUESTION_1, questionaire1[4].getQuestionText());

  }

  /**
   * Checks for the correct question bank ordering.
   */
  @Test
  public void testCreateQuestionBank2() {
    Arrays.sort(questionaire2);

    assertEquals(yesNoQuestion1, questionaire2[0]);
    assertEquals(SampleQuestions.QUESTION_1, questionaire2[0].getQuestionText());

    assertEquals(yesNoQuestion2, questionaire2[1]);
    assertEquals(SampleQuestions.QUESTION_2, questionaire2[1].getQuestionText());

    assertEquals(likertQuestion2, questionaire2[2]);
    assertEquals(SampleQuestions.QUESTION_5, questionaire2[2].getQuestionText());

    assertEquals(likertQuestion1, questionaire2[3]);
    assertEquals(SampleQuestions.QUESTION_2, questionaire2[3].getQuestionText());

    assertEquals(multipleChoiceQuestion, questionaire2[4]);
    assertEquals(SampleQuestions.QUESTION_5, questionaire2[4].getQuestionText());

    assertEquals(multipleAnswerQuestion, questionaire2[5]);
    assertEquals(SampleQuestions.QUESTION_1, questionaire2[5].getQuestionText());
  }

  /**
   * Checks for the correct question bank ordering.
   */
  @Test
  public void testCreateQuestionBank3() {
    Arrays.sort(questionaire3);

    assertEquals(yesNoQuestion3, questionaire3[0]);
    assertEquals(SampleQuestions.QUESTION_1, questionaire3[0].getQuestionText());

    assertEquals(yesNoQuestion2, questionaire3[1]);
    assertEquals(SampleQuestions.QUESTION_2, questionaire3[1].getQuestionText());

    assertEquals(multipleAnswerQuestion2, questionaire3[2]);
    assertEquals(SampleQuestions.QUESTION_5, questionaire3[2].getQuestionText());

  }

  /**
   * Checks for the correct question bank ordering.
   */
  @Test
  public void testCreateQuestionBank4() {
    Arrays.sort(questionaire4);

    assertEquals(yesNoQuestion1, questionaire4[0]);
    assertEquals(SampleQuestions.QUESTION_1, questionaire4[0].getQuestionText());

    assertEquals(multipleAnswerQuestion2, questionaire4[1]);
    assertEquals(SampleQuestions.QUESTION_5, questionaire4[1].getQuestionText());

    assertEquals(multipleAnswerQuestion1, questionaire4[2]);
    assertEquals(SampleQuestions.QUESTION_4, questionaire4[2].getQuestionText());
  }

  /**
   * Checks for the correct question bank ordering.
   */
  @Test
  public void testCreateQuestionBank5() {
    Arrays.sort(questionaire5);

    assertEquals(yesNoQuestion1, questionaire5[0]);
    assertEquals(SampleQuestions.QUESTION_1, questionaire5[0].getQuestionText());

    assertEquals(likertQuestion3, questionaire5[1]);
    assertEquals(SampleQuestions.QUESTION_4, questionaire5[1].getQuestionText());

    assertEquals(multipleChoiceQuestion2, questionaire5[2]);
    assertEquals(SampleQuestions.QUESTION_1, questionaire5[2].getQuestionText());

    assertEquals(multipleAnswerQuestion2, questionaire5[3]);
    assertEquals(SampleQuestions.QUESTION_5, questionaire5[3].getQuestionText());

    assertEquals(multipleAnswerQuestion1, questionaire5[4]);
    assertEquals(SampleQuestions.QUESTION_4, questionaire5[4].getQuestionText());
  }

  /**
   * Tests for Reflexivity of implemented equals method.
   */
  @Test
  public void testEqualsReflexivity() {
    assertEquals(true,
            yesNoQuestion1.equals(yesNoQuestion3));
  }

  /**
   * Tests for symmetry of implemented equals method.
   */
  @Test
  public void testEqualsSymmetry() {
    boolean result1 = yesNoQuestion1.equals(yesNoQuestion3);
    boolean result2 = yesNoQuestion3.equals(yesNoQuestion1);
    assertEquals(result1, result2);
  }

  /**
   * Tests for transitivity of implemented equals method.
   */
  @Test
  public void testEqualsTransitive() {
    boolean result1 = likertQuestion1.equals(likertQuestion2);
    boolean result2 = likertQuestion2.equals(likertQuestion3);
    boolean result3 = likertQuestion3.equals(likertQuestion1);
    assertEquals(result1, result2);
    assertEquals(result2, result3);
    assertEquals(result1, result3);
  }

}
