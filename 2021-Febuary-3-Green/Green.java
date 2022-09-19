import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Green {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("green.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // PrintWriter pw = new PrintWriter("green.out");
    StringTokenizer line = new StringTokenizer(br.readLine());
    N = Integer.parseInt(line.nextToken());
    
    // solve
    int[][] grid = new int[N+1][N+1];
    for (int i = 1; i <= N; i++) {
      line = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        int cur = Integer.parseInt(line.nextToken());
        grid[i][j] = cur == 100 ? 1 : cur > 100 ? 0 : -501;
      }
    }

    int[][] prefix = new int[N+1][N+1];
    for (int i = 1; i <= N; i++) {
      int cur = 0;
      for (int j = 1; j <= N; j++) {
        cur += grid[i][j];
        prefix[i][j] = cur;
      }
    }

    // try all i j combinations
    long count = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = i; j <= N; j++) {
        for (int m = 1; m <= N;) {
          int start = -1;
          int middle = -1;
          int end = -1;
          int k = m;
          for (; k <= N; k++) {
            int curLine = prefix[k][j] - prefix[k][i-1];
            if (curLine >= 0 && start == -1) {
              start = k;
            }
            if (curLine > 0 && middle == -1) {
              middle = k;
            }
            if (k == N && middle != -1 && curLine >= 0) {
              // can set end
              end = N;
              break;
            }
            if (curLine < 0) {
              if (start != -1) {
                if (middle != -1) {
                  // can set end
                  end = k-1;
                }
                // cannot set end
                break;
              }
            }
          }

          if (end != -1) {
            // got results
            count += (end - middle + 1) * (middle - start+1);
          }
          if (middle != -1) {
            m=middle+1;
          } else {
            m=k+1;
          }
        }
      }
    }

    System.out.println(count);

    br.close();
    // pw.close();
  }
}