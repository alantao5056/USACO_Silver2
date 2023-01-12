import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Div7 {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("div7.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("div7.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    int[] prefix = new int[N+1];
    for (int i = 1; i <= N; i++) {
      int cur = nextInt() % 7;
      prefix[i] = (prefix[i-1] + cur) % 7;
    }

    int max = 0;
    for (int i = 0; i < 7; i++) {
      int start = -1;
      for (int j = 0; j <= N; j++) {
        if (prefix[j] == i) {
          // found
          start = j;
          break;
        }
      }

      if (start == -1) continue;

      int end = -1;
      for (int j = N; j >= 0; j--) {
        if (prefix[j] == i) {
          // found
          end = j;
          break;
        }
      }
      max = Math.max(max, end - start);
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