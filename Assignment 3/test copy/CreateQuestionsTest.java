import org.junit.Test;

import questionaire.IQuestion;
import questionaire.LikertQuestion;
import questionaire.MultipleAnswersQuestion;
import questionaire.MultipleChoiceQuestion;
import questionaire.SampleQuestions;
import questionaire.YesNoQuestion;

import static org.junit.Assert.assertEquals;

/**
 * Test class to create all types of Questions.
 */
public class CreateQuestionsTest {


  /**
   * Checks for the correct answer format of YesNo Question. Program should be able to set this
   * question with all variants of Yes/No.
   */
  @Test
  public void testSetYesNoAnswerValidFormat() {
    IQuestion question1 = new YesNoQuestion(SampleQuestions.QUESTION_1, "Yes");
    assertEquals(SampleQuestions.QUESTION_1, question1.getQuestionText());

    IQuestion question2 = new YesNoQuestion(SampleQuestions.QUESTION_1, "No");
    assertEquals(SampleQuestions.QUESTION_1, question2.getQuestionText());

    IQuestion question3 = new YesNoQuestion(SampleQuestions.QUESTION_1, "yes");
    assertEquals(SampleQuestions.QUESTION_1, question3.getQuestionText());

    IQuestion question4 = new YesNoQuestion(SampleQuestions.QUESTION_1, "no");
    assertEquals(SampleQuestions.QUESTION_1, question4.getQuestionText());

    IQuestion question5 = new YesNoQuestion(SampleQuestions.QUESTION_1, "YES");
    assertEquals(SampleQuestions.QUESTION_1, question5.getQuestionText());

    IQuestion question6 = new YesNoQuestion(SampleQuestions.QUESTION_1, "NO");
    assertEquals(SampleQuestions.QUESTION_1, question6.getQuestionText());
  }

  /**
   * Checks for the incorrect answer format of YesNo Question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetYesNoAnswerInvalidFormat1() {
    IQuestion question1 = new YesNoQuestion(SampleQuestions.QUESTION_2, "Yas");
  }

  /**
   * Checks for the incorrect answer format of YesNo Question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetYesNoAnswerInvalidFormat2() {
    IQuestion question2 = new YesNoQuestion(SampleQuestions.QUESTION_2, "#221");
  }

  /**
   * Checks for the incorrect answer format of YesNo Question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetYesNoAnswerInvalidFormat3() {
    IQuestion question3 = new YesNoQuestion(SampleQuestions.QUESTION_2, "yeah");
  }

  /**
   * Checks for the creation of Likert Question type.
   */
  @Test
  public void testSetLikertAnswerValidFormat() {
    IQuestion question1 = new LikertQuestion(SampleQuestions.QUESTION_1);
    assertEquals(SampleQuestions.QUESTION_1, question1.getQuestionText());

    IQuestion question2 = new LikertQuestion(SampleQuestions.QUESTION_2);
    assertEquals(SampleQuestions.QUESTION_2, question2.getQuestionText());

    IQuestion question3 = new LikertQuestion(SampleQuestions.QUESTION_3);
    assertEquals(SampleQuestions.QUESTION_3, question3.getQuestionText());

    IQuestion question4 = new LikertQuestion(SampleQuestions.QUESTION_4);
    assertEquals(SampleQuestions.QUESTION_4, question4.getQuestionText());

    IQuestion question5 = new LikertQuestion(SampleQuestions.QUESTION_5);
    assertEquals(SampleQuestions.QUESTION_5, question5.getQuestionText());

  }


  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer cannot be other than
   * option number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetMultipleChoiceAnswerNANFormat() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1, options,
            "Yes");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer cannot be multiple
   * values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetMultipleChoiceAnswerMultipleAnswers() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
            options, "1 2");
  }


  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 1.
   */
  @Test
  public void testSetMultipleChoiceAnswerFormat1() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
            options, "1");
    assertEquals(SampleQuestions.QUESTION_1, question1.getQuestionText());
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 2.
   */
  @Test
  public void testSetMultipleChoiceAnswerFormat2() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
            options, "2");
    assertEquals(SampleQuestions.QUESTION_1, question1.getQuestionText());
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 3.
   */
  @Test
  public void testSetMultipleChoiceAnswerFormat3() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
            options, "3");
    assertEquals(SampleQuestions.QUESTION_1, question1.getQuestionText());
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 4.
   */
  @Test
  public void testSetMultipleChoiceAnswerFormat4() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
            options, "4");
    assertEquals(SampleQuestions.QUESTION_1, question1.getQuestionText());
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is greater
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueGreaterThanAllowed1() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
            options, "5");
    assertEquals(SampleQuestions.QUESTION_1, question1.getQuestionText());
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is less
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueLessThanAllowed1() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
            options, "0");
  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is less
   * than minimum option values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptions1() {
    String[] options = new String[]{"Yes", "No"};
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
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
    IQuestion question1 = new MultipleChoiceQuestion(SampleQuestions.QUESTION_1,
            options, "1");
  }

  /**
   * Checks for the incorrect answer format of Multiple Answers Question. Answer cannot be other
   * than option number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetMultipleAnswersNANFormat() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, "Yes");
  }

  /**
   * Checks for the correct answer format of MultipleAnswers Question.
   */
  @Test
  public void testSetMultipleAnswers() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1, options,
            "1");
    assertEquals(SampleQuestions.QUESTION_1, question1.getQuestionText());
    IQuestion question2 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, "1 2");
    assertEquals(SampleQuestions.QUESTION_1, question2.getQuestionText());
    IQuestion question3 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, "1 2 3");
    assertEquals(SampleQuestions.QUESTION_1, question3.getQuestionText());
    IQuestion question4 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, "1 2 3 4");
    assertEquals(SampleQuestions.QUESTION_1, question4.getQuestionText());

  }

  /**
   * Checks for the correct answer format of Multiple answers Question. Answer can have whitespace
   * character values.
   */
  @Test
  public void testSetMultipleAnswersWithWhitespace() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, " 1");
    assertEquals(SampleQuestions.QUESTION_1, question1.getQuestionText());
    IQuestion question2 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, " 1 ");
    assertEquals(SampleQuestions.QUESTION_1, question2.getQuestionText());
    IQuestion question3 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, "1  2");
    assertEquals(SampleQuestions.QUESTION_1, question3.getQuestionText());
    IQuestion question4 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, "1   2  3");
    assertEquals(SampleQuestions.QUESTION_1, question4.getQuestionText());
    IQuestion question5 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_1,
            options, " 1 2  3 4  ");
    assertEquals(SampleQuestions.QUESTION_1, question5.getQuestionText());

  }

  /**
   * Checks for the correct answer format of Multiple answers Question. Answer is option 1.
   */
  @Test
  public void testSetMultipleAnswerFormat1() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "1");
    assertEquals(SampleQuestions.QUESTION_2, question1.getQuestionText());
  }

  /**
   * Checks for the correct answer format of Multiple answer Question. Answer is option 2.
   */
  @Test
  public void testSetMultipleAnswerFormat2() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "2");
    assertEquals(SampleQuestions.QUESTION_2, question1.getQuestionText());
  }

  /**
   * Checks for the correct answer format of Multiple answer Question. Answer is option 3.
   */
  @Test
  public void testSetMultipleAnswerFormat3() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "3");
    assertEquals(SampleQuestions.QUESTION_2, question1.getQuestionText());
  }

  /**
   * Checks for the correct answer format of Multiple Choice Question. Answer is option 4.
   */
  @Test
  public void testSetMultipleAnswerFormat4() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "4");
    assertEquals(SampleQuestions.QUESTION_2, question1.getQuestionText());
  }

  /**
   * Checks for the incorrect answer format of Multiple answer Question. Answer provided is greater
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueGreaterThanAllowed2() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "1 2 5");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is greater
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueGreaterThanAllowed3() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "5");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is less
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueLessThanAllowed2() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "0 1");
  }

  /**
   * Checks for the incorrect answer format of Multiple Choice Question. Answer provided is less
   * than option length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptionsValueLessThanAllowed3() {
    String[] options = new String[]{"Yes", "No", "Maybe Later", "Definitely not"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "-1");
  }

  /**
   * Checks for the incorrect option format of Multiple Choice Question. Number of options is less
   * than minimum option values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOptions3() {
    String[] options = new String[]{"Yes", "No"};
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
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
    IQuestion question1 = new MultipleAnswersQuestion(SampleQuestions.QUESTION_2,
            options, "1");
  }


}
