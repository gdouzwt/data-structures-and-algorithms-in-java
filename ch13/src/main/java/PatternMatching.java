/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Map;
import java.util.HashMap;

/*
  Note: we use char[] rather than String for these methods, simply to allow
  better readability of algorithms with the more direct syntax
     pattern[k]
  rather than the more verbose
     pattern.charAt(k)
*/

public class PatternMatching {

  /** Returns the lowest index at which substring pattern begins in text (or else -1).*/
  public static int findBrute(char[] text, char[] pattern) {
    int n = text.length;
    int m = pattern.length;
    for (int i=0; i <= n - m; i++) {                 // try every starting index within text
      int k = 0;                                     // k is index into pattern
      while (k < m && text[i+k] == pattern[k])       // kth character of pattern matches
        k++;
      if (k == m)                                    // if we reach the end of the pattern,
        return i;                                    // substring text[i..i+m-1] is a match
    }
    return -1;                                       // search failed
  }

  /** Returns the lowest index at which substring pattern begins in text (or else -1).*/
  public static int findBoyerMoore(char[] text, char[] pattern) {
    int n = text.length;
    int m = pattern.length;
    if (m == 0) return 0;                            // trivial search for empty string
    Map<Character,Integer> last = new HashMap<>();   // the 'last' map
    for (int i=0; i < n; i++)
      last.put(text[i], -1);               // set -1 as default for all text characters
    for (int k=0; k < m; k++)
      last.put(pattern[k], k);             // rightmost occurrence in pattern is last
    // start with the end of the pattern aligned at index m-1 of the text
    int i = m-1;                                     // an index into the text
    int k = m-1;                                     // an index into the pattern
    while (i < n) {
      if (text[i] == pattern[k]) {                   // a matching character
        if (k == 0) return i;                        // entire pattern has been found
        i--;                                         // otherwise, examine previous
        k--;                                         // characters of text/pattern
      } else {
        i += m - Math.min(k, 1 + last.get(text[i])); // case analysis for jump step
        k = m - 1;                                   // restart at end of pattern
      }
    }
    return -1;                                       // pattern was never found
  }

  /** Returns the lowest index at which substring pattern begins in text (or else -1).*/
  public static int findKMP(char[] text, char[] pattern) {
    int n = text.length;
    int m = pattern.length;
    if (m == 0) return 0;                            // trivial search for empty string
    int[] fail = computeFailKMP(pattern);            // computed by private utility
    int j = 0;                                       // index into text
    int k = 0;                                       // index into pattern
    while (j < n) {
      if (text[j] == pattern[k]) {                   // pattern[0..k] matched thus far
        if (k == m - 1) return j - m + 1;            // match is complete
        j++;                                         // otherwise, try to extend match
        k++;
      } else if (k > 0)
        k = fail[k-1];                               // reuse suffix of P[0..k-1]
      else
        j++;
    }
    return -1;                                       // reached end without match
  }

  private static int[] computeFailKMP(char[] pattern) {
    int m = pattern.length;
    int[] fail = new int[m];                         // by default, all overlaps are zero
    int j = 1;
    int k = 0;
    while (j < m) {                                  // compute fail[j] during this pass, if nonzero
      if (pattern[j] == pattern[k]) {                // k + 1 characters match thus far
        fail[j] = k + 1;
        j++;
        k++;
      } else if (k > 0)                              // k follows a matching prefix
        k = fail[k-1];
      else                                           // no match found starting at j
        j++;
    }
    return fail;
  }
}
