import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Convention {
  static StreamTokenizer st;
  static int N;
  static int M;
  static int C;
  static int[] cows;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("convention.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("convention.out"));
    N = nextInt();
    M = nextInt();
    C = nextInt();
    
    // solve
    cows = new int[N];
    for (int i = 0; i < N; i++) {
      cows[i] = nextInt();
    }

    Arrays.sort(cows);

    int minWaiting = Integer.MAX_VALUE;
    for (int i = 0; i < N-1; i++) {
      minWaiting = Math.min(minWaiting, cows[i+1] - cows[i]);
    }

    int low = minWaiting;
    int high = cows[N-1]-cows[0];
    
    while (low < high) { // notice we do not compare element at mid with our target
      int mid = low + (high - low) / 2;
      if (works(mid))
        high = mid;
      else
        low = mid + 1;
    }

    pw.println(low);

    br.close();
    pw.close();
  }

  private static boolean works(int maxWaitTime) {
    int curM = 0;
    int curC = 0;
    int firstCow = 0;

    for (int i = 0; i < N; i++) {
      if (curM == M) { // done
        return false;
      }

      if (curC == C) { // next bus
        curM++;
        curC = 0;
        i--;
        continue;
      }

      if (curC == 0) { // first
        firstCow = cows[i];
      }

      if (cows[i] - firstCow <= maxWaitTime) {
        curC++;
      } else {
        // next bus
        curM++;
        curC = 0;
        i--;
      }
    }
    return true;
  }

  private static int nextInt() throws Exception {
    st.nextToken();
    return (int) st.nval;
  }

  private static String nextString() throws Exception {
    st.nextToken();
    return st.sval;
  }
}