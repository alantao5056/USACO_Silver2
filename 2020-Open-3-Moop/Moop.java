import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Moop {
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

    TreeSet<Integer> t = new TreeSet<>();
    for (int i = 0; i < N; i++) {
      int y = particles[i][1];
      Object[] set = t.headSet(y, true).toArray();

      if (set.length == 0) {
        t.add(y);
      } else {
        for (int j = 1; j < set.length; j++) {
          t.remove(set[j]);
        }
      }
    }

    pw.println(t.size());

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