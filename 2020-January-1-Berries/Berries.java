import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Berries {
  static StreamTokenizer st;
  static int N;
  static int K;
  static int[] trees;
  static Integer[] trees2;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("berries.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("berries.out"));
    N = nextInt();
    K = nextInt();
    
    // solve
    trees = new int[N];
    trees2 = new Integer[N];
    int sum = 0;
    for (int i = 0; i < N; i++) {
      trees[i] = nextInt();
      sum += trees[i];
    }


    int max = 0;
    
    for (int i = 1; i < 1001; i++) {
      int count = 0;
      for (int j = 0; j < N; j++) {
        count += trees[j] / i;
        trees2[j] = trees[j] % i;
      }

      if (count >= K) {
        // done
        max = Math.max(max, i * K/2);
        continue;
      }

      if (count < K/2) {
        // doesn't work
        break;
      }

      Arrays.sort(trees2, Collections.reverseOrder());

      int cur = (count - K/2) * i;
      for (int j = 0; j < N && j < K-count; j++) {
        cur += trees2[j];
      }

      max = Math.max(max, cur);
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