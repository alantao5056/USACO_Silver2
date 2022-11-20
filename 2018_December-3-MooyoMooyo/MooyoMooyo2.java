import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class MooyoMooyo2 {
  static StreamTokenizer st;
  static int N;
  static int K;
  static int[][] grid;
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("mooyomooyo.out"));
    String[] line = br.readLine().split(" ");
    N = Integer.parseInt(line[0]);
    K = Integer.parseInt(line[1]);
    grid = new int[N+2][12];
    visited = new boolean[N+2][12];
    
    for (int i = 1; i <= N; i++) {
      char[] l = br.readLine().toCharArray();

      for (int j = 1; j <= 10; j++) {
        grid[i][j] = l[j-1] - '0';
      }
    }

    // solve
    while (true) {
      // printGrid();
      // find groups
      boolean found = false;
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= 10; j++) {
          if (grid[i][j] == 0 || visited[i][j]) continue;
          ArrayDeque<Integer> q = new ArrayDeque<>();
          List<Integer> path = new ArrayList<>();

          q.add(i);
          q.add(j);
          visited[i][j] = true;
          int size = 0;

          while (!q.isEmpty()) {
            size++;
            int curI = q.poll();
            int curJ = q.poll();
            path.add(curI);
            path.add(curJ);
            int curColor = grid[curI][curJ];

            if (grid[curI+1][curJ] == curColor && !visited[curI+1][curJ]) {
              q.add(curI+1);
              q.add(curJ);
              visited[curI+1][curJ] = true;
            }
            if (grid[curI-1][curJ] == curColor && !visited[curI-1][curJ]) {
              q.add(curI-1);
              q.add(curJ);
              visited[curI-1][curJ] = true;
            }
            if (grid[curI][curJ+1] == curColor && !visited[curI][curJ+1]) {
              q.add(curI);
              q.add(curJ+1);
              visited[curI][curJ+1] = true;
            }
            if (grid[curI][curJ-1] == curColor && !visited[curI][curJ-1]) {
              q.add(curI);
              q.add(curJ-1);
              visited[curI][curJ-1] = true;
            }
          }

          if (size >= K) {
            found = true;
            // do it again but changing to zero
            for (int k = 0; k < size*2; k+=2) {
              grid[path.get(k)][path.get(k+1)] = 0;
            }
          }
        }
      }

      if (!found) break;

      // let tiles fall
      for (int j = 1; j <= 10; j++) {
        int nextAvaliable = N;

        for (int i = N; i > 0; i--) {
          if (grid[i][j] != 0) {
            // put to next avaliable
            grid[nextAvaliable][j] = grid[i][j];
            if (i != nextAvaliable) {
              grid[i][j] = 0;
            }
            nextAvaliable--;
          }
          visited[i][j] = false;
        }
      }
    }

    // printGrid();
    for (int i = 1; i <= N; i++) {
      StringBuilder sb = new StringBuilder();
      for (int j = 1; j <= 10; j++) {
        sb.append(grid[i][j]);
      }

      pw.println(sb.toString());
    }

    br.close();
    pw.close();
  }

  // private static void printGrid() {
  //   for (int i = 1; i <= N; i++) {
  //     StringBuilder sb = new StringBuilder();
  //     for (int j = 1; j <= 10; j++) {
  //       sb.append(grid[i][j]);
  //     }

  //     System.out.println(sb.toString());
  //   }
  //   System.out.println();
  // }

  private static int nextInt() throws Exception {
    st.nextToken();
    return (int) st.nval;
  }

  private static String nextString() throws Exception {
    st.nextToken();
    return st.sval;
  }
}