import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import questionaire.AbstractQuestion;
import questionaire.EvaluationResult;
import questionaire.IQuestion;
import questionaire.LikertQuestion;
import questionaire.MultipleAnswersQuestion;
import questionaire.MultipleChoiceQuestion;
import questionaire.YesNoQuestion;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class QuestionTest {
  private static IQuestion yesNoQuestion1;
  private static IQuestion yesNoQuestion2;
  private static IQuestion likertQuestion;
  private static IQuestion likertQuestion2;
  private static IQuestion multipleAnswerQuestion;
  private static IQuestion multipleChoiceQuestion;

  @BeforeClass
  public static void setUp() {
    String questionText = "Are you in Final Year?";
    yesNoQuestion1 = new YesNoQuestion(questionText, "Yes");
    yesNoQuestion2 = new YesNoQuestion("Is Sky Blue?", "Yes");
    likertQuestion = new LikertQuestion("Do you think if this course met requirements?",
            "");
    likertQuestion2 = new LikertQuestion("Do you think if this session was helpful?",
            "");
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    multipleChoiceQuestion = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "2");
    multipleAnswerQuestion = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "1 2");
  }

  /**
   * Checks for the correct answer format of YesNo Question
   */
  @Test
  public void testSetYesNoAnswerValidFormat() {
    String questionText = "Are you in Final Year?";
    IQuestion question2 = new YesNoQuestion(questionText, "No");
    IQuestion question3 = new YesNoQuestion(questionText, "yes");
    IQuestion question4 = new YesNoQuestion(questionText, "no");
    IQuestion question5 = new YesNoQuestion(questionText, "YES");
    IQuestion question6 = new YesNoQuestion(questionText, "NO");
  }

  /**
   * Checks for the incorrect answer format of YesNo Question
   */
  @Test(expected = IllegalArgumentException.class)
  //todo break into separate methods to check if all the cases are working
  public void testSetYesNoAnswerInvalidFormat() {
    IQuestion question1 = new YesNoQuestion("Are you in Final Year?", "Yas");
    IQuestion question2 = new YesNoQuestion("Are you in Married Year?", "Nep");
    IQuestion question3 = new YesNoQuestion("Are you in Final Year?", "1122");
    IQuestion question4 = new YesNoQuestion("Are you in Married Year?", "#221");
    IQuestion question5 = new YesNoQuestion("Are you in Final Year?", "yeah");
    IQuestion question6 = new YesNoQuestion("Are you in Married Year?", "yo");
  }


  /**
   * Checks for the correct answer format of Likert Question
   */
  @Test
  public void testSetLikertAnswerValidFormat() {

    IQuestion question2 = new LikertQuestion("Do you think you are fit for this position?",
            "");
  }


  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer cannot be other than
   * option number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetMultipleChoiceAnswerNANFormat() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "Yes");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer cannot be multiple
   * values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetMultipleChoiceAnswerMultipleAnswers() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "1 2");
  }


  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 1.
   */
  @Test
  public void testSetMultipleChoiceAnswerFormat1() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "1");
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 2.
   */
  @Test
  public void testSetMultipleChoiceAnswerFormat2() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "2");
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 3.
   */
  @Test
  public void testSetMultipleChoiceAnswerFormat3() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "3");
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 4.
   */
  @Test
  public void testSetMultipleChoiceAnswerFormat4() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "4");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is greater
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueGreaterThanAllowed1() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "5");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is less
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueLessThanAllowed1() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "0");
  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is less
   * than minimum option values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptions1() {
    String[] options = new String[]{"Yes", "No"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "1");
  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is
   * greater than maximum option values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptions2() {
    String[] options = new String[]{"Yes", "No", "MayBe", "Later", "Sooner", "Definitely", "Hardly"
            , "Not Sure", "Definitely No"};
    IQuestion question1 = new MultipleChoiceQuestion("Are you working in Summer?",
            options, "1");
  }

  /**
   * Checks for the incorrect answer format of Multiple Answers Question. Answer cannot be other
   * than option number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetMultipleAnswersNANFormat() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "Yes");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer cannot be multiple
   * values.
   */
  @Test
  public void testSetMultipleAnswers() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?", options,
            "1");
    IQuestion question2 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "1 2");
    IQuestion question3 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "1 2 3");
    IQuestion question4 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "1 2 3 4");

  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer cannot be multiple
   * values.
   */
  @Test
  public void testSetMultipleAnswersWithWhitespace() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, " 1");
//    IQuestion question2 = new MultipleAnswersQuestion("Are you working in Summer?",
//            " 1 ", options);
//    IQuestion question3 = new MultipleAnswersQuestion("Are you working in Summer?",
//            "1  2", options);
//    IQuestion question4 = new MultipleAnswersQuestion("Are you working in Summer?",
//            "1   2  3", options);
//    IQuestion question5 = new MultipleAnswersQuestion("Are you working in Summer?",
//            " 1 2  3 4  ", options);

  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 1.
   */
  @Test
  public void testSetMultipleAnswerFormat1() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "1");
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 2.
   */
  @Test
  public void testSetMultipleAnswerFormat2() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "2");
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 3.
   */
  @Test
  public void testSetMultipleAnswerFormat3() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "3");
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 4.
   */
  @Test
  public void testSetMultipleAnswerFormat4() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "4");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is greater
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueGreaterThanAllowed2() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "1 2 5");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is greater
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueGreaterThanAllowed3() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "5");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is less
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueLessThanAllowed2() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "0 1");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is less
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueLessThanAllowed3() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "-1");
  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is less
   * than minimum option values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptions3() {
    String[] options = new String[]{"Yes", "No"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "1");
  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is
   * greater than maximum option values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptions4() {
    String[] options = new String[]{"Yes", "No", "MayBe", "Later", "Sooner", "Definitely", "Hardly"
            , "Not Sure", "Definitely No"};
    IQuestion question1 = new MultipleAnswersQuestion("Are you working in Summer?",
            options, "1");
  }


  /**
   * Checks for the case where answer is right.
   */
  @Test
  public void testAttemptYesNoQuestionCorrect() {
    String userAnswer = "Yes";
    IQuestion userQuestion = yesNoQuestion1.setUserAnswer(userAnswer);
    String result = yesNoQuestion1.evaluate(userQuestion);
    assertEquals(EvaluationResult.CORRECT.name(), result);

  }

  /**
   * Checks for the case where answer is wrong.
   */
  @Test
  public void testAttemptYesNoQuestionIncorrect1() {
    String userAnswer = "No";
    IQuestion userQuestion = yesNoQuestion1.setUserAnswer(userAnswer);
    String result = yesNoQuestion1.evaluate(userQuestion);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the case where input format is invalid.
   */
  @Test
  public void testAttemptYesNoQuestionIncorrect2() {
    String userAnswer = "ddf32";
    IQuestion userQuestion = yesNoQuestion1.setUserAnswer(userAnswer);
    String result = yesNoQuestion1.evaluate(userQuestion);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Different questions are compared/evaluated for same answer
   */
  @Test
  public void testAttemptYesNoQuestionIncorrect3() {
    String userAnswer = "Yes";
    IQuestion userQuestion = yesNoQuestion1.setUserAnswer(userAnswer);
    String result = yesNoQuestion2.evaluate(userQuestion);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the case where instance to call evaluate method could be different than that of
   * attemptedQuestion instance.
   */
  @Test
  public void testAttemptYesNoQuestionIncorrectInstance() {

  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is
   * greater than maximum option values.
   */
  @Test
  public void testAttemptLikertCorrect() {
    IQuestion userQuestion = likertQuestion.setUserAnswer("2");
    String result = likertQuestion.evaluate(userQuestion);
    assertEquals(EvaluationResult.CORRECT.name(), result);
  }

  /**
   * Checks for the case where answer format is wrong.
   */
  @Test
  public void testAttemptLikertIncorrect1() {
    String userAnswer = "Agree";
    IQuestion userQuestion = likertQuestion.setUserAnswer(userAnswer);
    String result = likertQuestion.evaluate(userQuestion);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the case where question is different.
   */
  @Test
  public void testAttemptLikertIncorrect2() {
    String userAnswer = "2";
    IQuestion userQuestion = likertQuestion.setUserAnswer(userAnswer);
    String result = likertQuestion2.evaluate(userQuestion);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the case where option is valid number but out of range.
   */
  @Test
  public void testAttemptLikertIncorrect3() {
    String userAnswer = "6";
    IQuestion userQuestion = likertQuestion.setUserAnswer(userAnswer);
    String result = likertQuestion.evaluate(userQuestion);
    assertEquals(EvaluationResult.INCORRECT.name(), result);

  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is
   * greater than maximum option values.
   */
  @Test
  public void testSetMultipleAnswerUserAnswer() {
    multipleAnswerQuestion.setUserAnswer("1 2");
  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is
   * greater than maximum option values.
   */
  @Test
  public void testSetMultipleChoiceUserAnswer() {
    multipleChoiceQuestion.setUserAnswer("1");
  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is
   * greater than maximum option values.
   */
  @Test
  public void testQuestionBankOrdering() {
    IQuestion[] questionaire = {yesNoQuestion2,likertQuestion,yesNoQuestion1,
            multipleChoiceQuestion,multipleAnswerQuestion, likertQuestion2};
    Arrays.sort(questionaire);
    assertEquals(QuestionType.YES_NO, questionaire[0].getQuestionType());
    assertEquals("Are you in Final Year?", questionaire[0].getQuestionText());
    assertEquals(QuestionType.YES_NO, questionaire[1].getQuestionType());
    assertEquals("Is Sky Blue?", questionaire[1].getQuestionText());
    assertEquals(QuestionType.LIKERT, questionaire[2].getQuestionType());
    assertEquals("Do you think if this course met requirements?",
            questionaire[2].getQuestionText());
  }

}
