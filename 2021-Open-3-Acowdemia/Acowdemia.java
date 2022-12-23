import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Acowdemia {
  static StreamTokenizer st;
  static int N;
  static int K;
  static int L;
  static int[] cites;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("acowdemia.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("acowdemia.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    K = nextInt();
    L = nextInt();
    
    // solve
    cites = new int[N];
    for (int i = 0; i < N; i++) {
      cites[i] = nextInt();
    }

    Arrays.sort(cites);

    // binary search h-index
    int low = 0;
    int high = N;
    
    while (low < high) { // notice we do not compare element at mid with our target
      int mid = low + (high - low + 1) / 2;
      if (works(mid))
        low = mid;
      else
        high = mid - 1;
    }

    pw.println(low);

    br.close();
    pw.close();
  }

  private static boolean works(int h) {
    if (h-cites[N-h] > K) {
      return false;
    }

    long total = 0;
    for (int i = N-h; i < N; i++) {
      if (cites[i] >= h) {
        break;
      }

      total += h-cites[i];
    }

    return total <= (long) K*L;
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