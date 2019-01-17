package questionaire;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class QuestionBankComparator implements Comparator<IQuestion> {

  Map<Class, Integer> orderList;


  private static final Integer YES_NO_ORDER = 1;
  private static final Integer LIKERT_ORDER = 2;
  private static final Integer MULTIPLE_CHOICE_ORDER = 3;
  private static final Integer MULTIPLE_ANSWER_ORDER = 4;

  public QuestionBankComparator() {
    orderList = new HashMap<>();
    orderList.put(YesNoQuestion.class, YES_NO_ORDER);
    orderList.put(LikertQuestion.class, LIKERT_ORDER);
    orderList.put(MultipleChoiceQuestion.class, MULTIPLE_CHOICE_ORDER);
    orderList.put(MultipleAnswersQuestion.class, MULTIPLE_ANSWER_ORDER);
  }

  @Override
  public int compare(IQuestion question1, IQuestion question2) {
    //what if question type is not present
    if (orderList.get(question1.getClass()) < orderList.get(question2.getClass())) {
      return -1;
    }
    if (orderList.get(question1.getClass()) > orderList.get(question2.getClass())) {
      return 1;
    }
    return question1.getQuestionText().compareTo(question2.getQuestionText());
  }
}
