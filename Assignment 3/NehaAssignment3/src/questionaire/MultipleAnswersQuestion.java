package questionaire;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class MultipleAnswersQuestion extends AbstractQuestion {
  /**
   *
   */
  private String[] options;

  private String[] correctOptions;

  private static final Integer OPTIONS_MAX_LENGTH = 8;
  private static final Integer OPTIONS_MIN_LENGTH = 3;
  private static final String WHITESPACECHAR_REGEX = "\\s+";
  private static final String DELIMITER = " ";

  /**
   *
   * @param text
   * @param correctAnswer
   * @param options
   */
  public MultipleAnswersQuestion(String text, String[] options, String correctAnswer)
          throws IllegalArgumentException {
    super(text, correctAnswer);
    if (isAnswerValidOption(options.length)) {
      setOptions(options);
    }
  }

  /**
   *
   * @param answer
   * @return
   */
  @Override
  public boolean validateAnswerFormat(String answer) throws IllegalArgumentException {
    answer = answer.replaceAll(WHITESPACECHAR_REGEX, DELIMITER);
    String[] answers = answer.split(DELIMITER);
    for (String option : answers) {
      try {
        if (!option.equals("")) {
          Integer.parseInt(option);
        }
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_NOT_AN_OPTION);
      }
    }
    return true;
  }

  /**
   *
   * @param options
   * @throws IllegalArgumentException
   */
  private void setOptions(String[] options) throws IllegalArgumentException {
    if (options.length < OPTIONS_MIN_LENGTH || options.length > OPTIONS_MAX_LENGTH) {
      throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_OPTIONS_SIZE_MCQ);
    }
    this.options = options;
  }

  /**
   *
   * @return
   */
  private boolean isAnswerValidOption(Integer optionLength) throws IllegalArgumentException {
    String[] answers = correctAnswer.split(DELIMITER);
    for (String answer : answers) {
      if (Integer.parseInt(answer) < 1 || Integer.parseInt(answer) > optionLength) {
        throw new IllegalArgumentException(ExceptionMessageConstants.INVALID_ANSWER_NOT_AN_OPTION);
      }
    }
    return true;
  }

  /**
   * Implements something similar to equals method. Compares user attempted question object with
   * that of standard question calling object. Evaluation returns "Correct" if text and correctAnswer
   * fields are same. For this type of question, correctAnswer can be case insensitive and so "Yes", "YES"
   * or "yes" would be regarded as the same.
   *
   * @return String of type {@link EvaluationResult#getValue()}
   */
  @Override
  public String evaluate(String answer) {
    if (validateAnswerFormat(answer) && answersEqual(answer)) {
      return EvaluationResult.CORRECT.name();
    } else {
      return EvaluationResult.INCORRECT.name();
    }
  }

  public boolean answersEqual(String userAnswer){
    String correct = this.correctAnswer.replaceAll(WHITESPACECHAR_REGEX, DELIMITER);
    String[] correctOptions = correct.split(DELIMITER);
    String attempted = userAnswer.replaceAll(WHITESPACECHAR_REGEX, DELIMITER);
    String[] attemptedOptions = attempted.split(DELIMITER);
    Set<String> correctSet = new HashSet<>(Arrays.asList(correctOptions));
    Set<String> attemptedSet = new HashSet<>(Arrays.asList(attemptedOptions));
    return correctSet.equals(attemptedSet);
  }
}
