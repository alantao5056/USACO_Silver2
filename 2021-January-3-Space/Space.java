import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Space {
  static int N;
  static int[][] grid;

  public static void main(String[] args) throws Exception {
    // read input
    // Scanner sc = new Scanner(new File("space.in"));
    Scanner sc = new Scanner(System.in);
    // PrintWriter pw = new PrintWriter("space.out");
    N = sc.nextInt();
    grid = new int[N][N];

    // solve
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        grid[i][j] = sc.nextInt();
      }
    }

    // vertical
    int totalVertical = 0;
    for (int i = 0; i < N; i++) {
      int points1 = 0;
      for (int j = 0; j < N; j+=2) {
        points1 += grid[i][j];
      }

      int points2 = 0;
      for (int j = 1; j < N; j+=2) {
        points2 += grid[i][j];
      }

      totalVertical += Math.max(points1, points2);
    }
  
    // vertical
    int totalHorizontal = 0;
    for (int j = 0; j < N; j++) {
      int points1 = 0;
      for (int i = 0; i < N; i+=2) {
        points1 += grid[i][j];
      }

      int points2 = 0;
      for (int i = 1; i < N; i+=2) {
        points2 += grid[i][j];
      }

      totalHorizontal += Math.max(points1, points2);
    }

    System.out.println(Math.max(totalVertical, totalHorizontal));

    sc.close();
    // pw.close();
  }
}