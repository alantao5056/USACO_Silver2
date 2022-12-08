import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Closest {
  static StreamTokenizer st;
  static int K;
  static int M;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("closest.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("closest.out"));
    PrintWriter pw = new PrintWriter(System.out);
    K = nextInt();
    M = nextInt();
    N = nextInt();
    
    // solve
    int[][] grass = new int[K][2];
    for (int i = 0; i < K; i++) {
      grass[i][0] = nextInt();
      grass[i][1] = nextInt();
    }

    Arrays.sort(grass, (int[] a, int[] b) -> a[0] - b[0]);

    int[] fn = new int[M];

    for (int i = 0; i < M; i++) {
      fn[i] = nextInt();
    }
    
    Arrays.sort(fn);
    br.close();

    
    List<Long> offers = new ArrayList<>();

    // do first
    int curIdx = 0;
    long total = 0;
    while (curIdx < K && grass[curIdx][0] < fn[0]) {
      total += grass[curIdx++][1];
    }
    
    offers.add(total);
    int start = curIdx;
    int end = curIdx;

    // do last
    curIdx = K-1;
    total = 0;
    while (curIdx > -1 && grass[curIdx][0] > fn[M-1]) {
      total += grass[curIdx--][1];
    }

    offers.add(total);

    // do rest
    for (int i = 0; i < M-1; i++) {
      int d = (fn[i+1]-fn[i])/2;
      long maxTotal = 0;
      long curTotal = 0;
      total = 0;
      if (grass[start][0] == fn[i]) {
        start++;
        end++;
      }
      
      while (start < K && grass[start][0]+d < fn[i+1]) {
        // update end
        while (end < K && grass[end][0] < grass[start][0]+d) {
          curTotal += grass[end][1];
          total += grass[end][1];
          end++;
        }

        maxTotal = Math.max(curTotal, maxTotal);

        if (end == K) break;

        curTotal -= grass[start][1];
        start++;
      }

      while (end < K && grass[end][0] < fn[i+1]) {
        curTotal += grass[end][1];
        total += grass[end][1];
        end++;
      }

      maxTotal = Math.max(curTotal, maxTotal);

      start = end;

      offers.add(maxTotal);
      offers.add(total-maxTotal);
    }

    Collections.sort(offers, Collections.reverseOrder());

    total = 0;
    for (int i = 0; i < N; i++) {
      total += offers.get(i);
    }

    pw.println(total);

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