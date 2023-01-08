import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Diamond {
  static StreamTokenizer st;
  static int N;
  static int K;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("diamond.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    K = nextInt();
    
    // solve
    int[] diamonds = new int[N];
    for (int i = 0; i < N; i++) {
      diamonds[i] = nextInt();
    }

    Arrays.sort(diamonds);

    int[] ends = new int[N];
    for (int i = 0; i < N; i++) {
      // binary search diamond[i]+K
      int low = 0;
      int high = N-1;
      
      while (low < high) { // notice we do not compare element at mid with our target
        int mid = low + (high - low + 1) / 2;
        if (diamonds[mid] <= diamonds[i]+K)
          low = mid;
        else
          high = mid - 1;
      }

      ends[i] = low-i+1;
    }

    int[] prefix = new int[N];
    int curMax = 0;
    for (int i = N-1; i >= 0; i--) {
      curMax = Math.max(curMax, ends[i]);
      prefix[i] = curMax;
    }

    int max = 0;
    for (int i = 0; i < N; i++) {
      if (i+ends[i]-1 == N-1) {
        max = Math.max(max, ends[i]);
      } else {
        max = Math.max(max, ends[i] + prefix[i+ends[i]]);
      }
    }

    pw.println(max);

    br.close();
    pw.close();
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