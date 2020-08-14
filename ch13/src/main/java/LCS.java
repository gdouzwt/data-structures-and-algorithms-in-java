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

public class LCS {

  /** Returns table such that L[j][k] is length of LCS for X[0..j-1] and Y[0..k-1]. */
  public static int[][] LCS(char[] X, char[] Y) {
    int n = X.length;
    int m = Y.length;
    int[][] L = new int[n+1][m+1];
    for (int j=0; j < n; j++)
      for (int k=0; k < m; k++)
        if (X[j] == Y[k])                // align this match
          L[j+1][k+1] = L[j][k] + 1;
        else                             // choose to ignore one character
          L[j+1][k+1] = Math.max(L[j][k+1], L[j+1][k]);
    return L;
  }

  /** Returns the longest common substring of X and Y, given LCS table L. */
  public static char[] reconstructLCS(char[] X, char[] Y, int[][] L) {
    StringBuilder solution = new StringBuilder();
    int j = X.length;
    int k = Y.length;
    while (L[j][k] > 0)             // common characters remain
      if (X[j-1] == Y[k-1]) {
        solution.append(X[j-1]);
        j--;
        k--;
      } else if (L[j-1][k] >= L[j][k-1])
        j--;
      else
        k--;
    // return left-to-right version, as char array
    return solution.reverse().toString().toCharArray();
  }

}
