import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Range {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("range.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("range.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    List<Integer>[] ranges = new ArrayList[N];

    for (int i = 0; i < N; i++) {
      ranges[i] = new ArrayList<>();
      for (int j = 0; j < N-i; j++) {
        ranges[i].add(nextInt());
      }
    }

    int[] result = new int[N];
    result[N-1] = 0;
    for (int i = N-2; i >= 0; i--) {
      result[i] = result[i+1]+ranges[i].get(1);
      // check if it works
      boolean works = true;
      for (int r = 2; r < ranges[i].size(); r++) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int j = i; j <= i+r; j++) {
          max = Math.max(max, result[j]);
          min = Math.min(min, result[j]);
        }
        if (max-min != ranges[i].get(r)) {
          works = false;
        }
      }

      if (!works) {
        result[i] = result[i+1]-ranges[i].get(1);
      }
    }

    for (int i = 0; i < N-1; i++) {
      pw.print(result[i]);
      pw.print(" ");
    }

    pw.println(result[N-1]);

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