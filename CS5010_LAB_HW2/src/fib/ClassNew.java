package fib;

import java.util.ArrayList;
import java.util.List;

class Result {
  // Set to store all the subsequences
  static List<String> st = new ArrayList<>();

  /*
   * Complete the 'findSubstrings' function below.
   *
   * The function accepts STRING s as parameter.
   */
  public static void findSubstrings(String s) {
    // Write your code here
    substring(s);
    System.out.println(st.get(0));
    System.out.println(st.get(st.size() - 1));
  }

  static void substring(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (isVowel(s.charAt(i))) {
        for (int j = (s.length() - 1); j >= i; j--) {
          if (!isVowel(s.charAt((j)))) {
            String substr = s.substring(i, j + 1);
            st.add(substr);
            for (int k = 1; k < substr.length() - 1; k++) {
              StringBuffer sb = new StringBuffer(substr);
              sb.deleteCharAt(k);
              substring(sb.toString());
            }
          }
        }
      }
    }
  }

  private static boolean isVowel(char v) {
    return (v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u');
  }

}
