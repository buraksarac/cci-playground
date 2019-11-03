package cci.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//https://hyperskill.org/learn/step/5719
public class RabinKarp {
  
  public static void main(String[] args) {
    System.out.println(search("this buraks is burak karub", "burak"));
  }

  public static long charToLong(char ch) {
    return (long) (ch - 'A' + 1);
  }

  public static List<Integer> search(String text, String pattern) {
    /* 2 */
    int a = 53;
    long m = 1_000_000_000 + 9;

    /* 3 */
    long patternHash = 0;
    long currSubstrHash = 0;
    long pow = 1;

    for (int i = 0; i < pattern.length(); i++) {
      patternHash += charToLong(pattern.charAt(i)) * pow;
      patternHash %= m;

      currSubstrHash += charToLong(text.charAt(text.length() - pattern.length() + i)) * pow;
      currSubstrHash %= m;

      if (i != pattern.length() - 1) {
        pow = pow * a % m;
      }
    }

    /* 4 */
    ArrayList<Integer> occurrences = new ArrayList<Integer>();

    for (int i = text.length(); i >= pattern.length(); i--) {
      if (patternHash == currSubstrHash) {
        boolean patternIsFound = true;

        for (int j = 0; j < pattern.length(); j++) {
          if (text.charAt(i - pattern.length() + j) != pattern.charAt(j)) {
            patternIsFound = false;
            break;
          }
        }

        if (patternIsFound) {
          occurrences.add(i - pattern.length());
        }
      }

      if (i > pattern.length()) {
        currSubstrHash = (currSubstrHash - charToLong(text.charAt(i - 1)) * pow % m + m) * a % m;
        currSubstrHash = (currSubstrHash + charToLong(text.charAt(i - pattern.length() - 1))) % m;
      }
    }

    Collections.sort(occurrences, Collections.reverseOrder());
    return occurrences;
  }
}
