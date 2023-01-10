import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Angry {
  static StreamTokenizer st;
  static int N;
  static int K;
  static int[] cows;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("angry.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("angry.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    K = nextInt();
    
    // solve
    cows = new int[N];
    for (int i = 0; i < N; i++) {
      cows[i] = nextInt();
    }

    Arrays.sort(cows);

    int low = 0;
    int high = 1000000000/2;
    
    while (low < high) { // notice we do not compare element at mid with our target
      int mid = low + (high - low) / 2;
      if (check(mid))
        high = mid;
      else
        low = mid + 1;
    }

    pw.println(low);

    br.close();
    pw.close();
  }

  private static boolean check(int r) {
    int idx = 0;
    int count = 0;
    while (count < K && idx < N) {
      int end = cows[idx]+r*2;
      while (idx < N && cows[idx] <= end) {
        idx++;
      }

      count++;
    }

    if (idx == N) return true;
    return false;
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