import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Circular2 {
  static StreamTokenizer st;
  static int T;
  static boolean[] isPrime;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("circular.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("circular.out"));
    PrintWriter pw = new PrintWriter(System.out);
    T = nextInt();

    isPrime = new boolean[5000001];
    Arrays.fill(isPrime, true);

    int ub = (int)Math.sqrt(5000000);
    for(int j=2; j<=ub; j++) {
      if(!isPrime[j]) continue;
      
      //loop through all multiples k*j, where k>=j
      for(int x=j*j; x<=5000000; x+=j) {
        isPrime[x] = false;
      }
    }
    
    // solve
    for (int i = 0; i < T; i++) {
      int N = nextInt();

      int minLose = Integer.MAX_VALUE;
      int minLoseIdx = 0;
      int minWin = Integer.MAX_VALUE;
      int minWinIdx = 0;

      boolean found = false;
      for (int j = 0; j < N; j++) {
        int curRoom = nextInt();
        if (isPrime[curRoom]) {
          found = true;
        }

        if (found) continue;

        if (curRoom % 4 == 0) {
          int rounds = rounds(curRoom);
          if (rounds < minLose) {
            minLoseIdx = j;
            minLose = rounds;
          } else if (rounds == minLose) {
            minLoseIdx = Math.min(minLoseIdx, j);
          }
        } else {
          int rounds = rounds(curRoom);
          if (rounds < minWin) {
            minWinIdx = j;
            minWin = rounds;
          } else if (rounds == minWin) {
            minWinIdx = Math.min(minWinIdx, j);
          }
        }
      }

      if (found) {
        pw.println("Farmer John");
        continue;
      }

      if (minLose < minWin) {
        pw.println("Farmer Nhoj");
      } else if (minLose > minWin) {
        pw.println("Farmer John");
      } else {
        if (minLoseIdx < minWinIdx) {
          pw.println("Farmer Nhoj");
        } else {
          pw.println("Farmer John");
        }
      }
    }

    br.close();
    pw.close();
  }

  private static int rounds(int k) {
    if (k%4==0) {
      // lose
      return k/2;
    }

    for (int i = 4; i < k; i+=4) {
      if (isPrime[k-i]) {
        return i/2+1;
      }
    }
    return -1;
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