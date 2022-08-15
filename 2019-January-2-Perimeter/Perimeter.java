import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Perimeter {
  static int N;
  static boolean[][] grid;
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("perimeter.in"));
    PrintWriter pw = new PrintWriter("perimeter.out");
    N = sc.nextInt();
    grid = new boolean[N+2][N+2];
    visited = new boolean[N+2][N+2];

    for (int i = 1; i <= N; i++) {
      char[] line = sc.next().toCharArray();

      for (int j = 1; j <= N; j++) {
        grid[i][j] = line[j-1] == '#';
      }
    }

    // solve
    // flood fill
    int minPerimeter = Integer.MAX_VALUE;
    int maxArea = 0;

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (!grid[i][j] || visited[i][j]) continue;

        // flood fill
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();

        q1.add(i);
        q2.add(j);
        visited[i][j] = true;

        int perimeter = 0;
        int area = 1;

        while (!q1.isEmpty()) {
          int curI = q1.poll();
          int curJ = q2.poll();

          if (grid[curI+1][curJ] && !visited[curI+1][curJ]) {
            q1.add(curI+1);
            q2.add(curJ);
            visited[curI+1][curJ] = true;
            area++;
          } else if (!grid[curI+1][curJ]) {
            perimeter++;
          }

          if (grid[curI-1][curJ] && !visited[curI-1][curJ]) {
            q1.add(curI-1);
            q2.add(curJ);
            visited[curI-1][curJ] = true;
            area++;
          } else if (!grid[curI-1][curJ]) {
            perimeter++;
          }

          if (grid[curI][curJ+1] && !visited[curI][curJ+1]) {
            q1.add(curI);
            q2.add(curJ+1);
            visited[curI][curJ+1] = true;
            area++;
          } else if (!grid[curI][curJ+1]) {
            perimeter++;
          }

          if (grid[curI][curJ-1] && !visited[curI][curJ-1]) {
            q1.add(curI);
            q2.add(curJ-1);
            visited[curI][curJ-1] = true;
            area++;
          } else if (!grid[curI][curJ-1]) {
            perimeter++;
          }
        }

        // update
        if (area > maxArea) {
          minPerimeter = perimeter;
          maxArea = area;
        } else if (area == maxArea) {
          minPerimeter = Math.min(minPerimeter, perimeter);
        }
      }
    }

    pw.println(String.format("%d %d", maxArea, minPerimeter));

    sc.close();
    pw.close();
  }
}