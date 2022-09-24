import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Herding {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("herding.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter("herding.out");
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    
    // solve
    int[] points = new int[N];
    for (int i = 0; i < N; i++) {
      points[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(points);

    // check isolated special case
    // all together
    int min = Integer.MAX_VALUE;
    int max = 0;

    if (points[N-1] - points[0] + 1 == N) {
      min = 0;
      max = 0;
    } else if (points[N-1] - points[1] + 1 == N-1) {
      // left isolated
      min = points[1] - points[0] == 2 ? 1 : 2;
      max = points[1] - points[0] - 1;
    } else if (points[N-2] - points[0] + 1 == N-1) {
      // right isolated
      min = points[N-1] - points[N-2] == 2 ? 1 : 2;
      max = points[N-1] - points[N-2] - 1;
    } else {
      for (int i = 0; i < N; i++) {
        // start a segment of length N at i
        int endPoint = points[i] + N-1;

        // binary search endPoint
        int l = 0, r = points.length - 1;
        while (l <= r) {
          int m = l + (r - l) / 2;
    
          if (points[m] == endPoint) {
            r = m;
            break;
          }
                
          if (points[m] < endPoint) {
            l = m + 1;
          } else {
            r = m - 1;
          }
        }

        min = Math.min(min, N-(r-i+1));
      }

      // calculate max
      max = points[N-1] - points[0] + 1 - N;
      max -= Math.min(points[1] - points[0] - 1, points[N-1] - points[N-2] - 1);
    }

    pw.println(min);
    pw.println(max);

    br.close();
    pw.close();
  }
}