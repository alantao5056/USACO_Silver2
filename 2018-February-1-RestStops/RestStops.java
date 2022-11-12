import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class RestStops {
  static StreamTokenizer st;
  static int L;
  static int N;
  static int RF;
  static int RB;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("reststops.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("reststops.out"));
    L = nextInt();
    N = nextInt();
    RF = nextInt();
    RB = nextInt();
    
    // solve
    int[][] rests = new int[N][2];
    for (int i = 0; i < N; i++) {
      rests[i][0] = nextInt();
      rests[i][1] = nextInt();
    }

    Arrays.sort(rests, (int[] a, int[] b) -> a[0]-b[0]);

    int curPos = 0;
    long result = 0;

    for (int i = 0; i < N; i++) {
      if (i==N-1 || rests[i][1] > rests[i+1][1]) {
        // do this one
        int d = rests[i][0] - curPos;
        result += (d*RF - d*RB) * rests[i][1];
        curPos = rests[i][0];
      }
    }

    pw.println(result);

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