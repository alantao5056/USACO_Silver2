import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Moop2 {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("moop.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("moop.out"));
    N = nextInt();
    
    // solve
    int[][] particles = new int[N][2];
    for (int i = 0; i < N; i++) {
      particles[i][0] = nextInt();
      particles[i][1] = nextInt();
    }

    Arrays.sort(particles, (int[] a, int[] b) -> a[0] < b[0] ? -1 : a[0] > b[0] ? 1 : a[1] - b[1]);

    int[] leftMin = new int[N];
    int curMin = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      curMin = Math.min(curMin, particles[i][1]);
      leftMin[i] = curMin;
    }

    int[] rightMax = new int[N];
    int curMax = Integer.MIN_VALUE;
    for (int i = N-1; i >= 0; i--) {
      rightMax[i] = curMax;
      curMax = Math.max(curMax, particles[i][1]);
    }

    int count = 0;
    for (int i = 0; i < N; i++) {
      if (leftMin[i] > rightMax[i]) {
        count++;
      }
    }

    pw.println(count);

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