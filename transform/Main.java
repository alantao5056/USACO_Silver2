import java.util.Scanner;

public class Main {
  private static int N;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("1.in"));

    N = sc.nextInt();

    boolean[][] grid = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      char[] line = sc.next().toCharArray();
      for (int j = 0; j < N; j++) {
        grid[i][j] = line[j] == '@';
      }
    }

    boolean[][] endGrid = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      char[] line = sc.next().toCharArray();
      for (int j = 0; j < N; j++) {
        endGrid[i][j] = line[j] == '@';
      }
    }

    System.out.println(getRotation(grid, endGrid));

    sc.close();
  }

  private static int getRotation(boolean[][] grid, boolean[][] endGrid) {
    // check if 90 degree rotation
    boolean degree90 = true;
    boolean degree180 = true;
    boolean degree270 = true;
    boolean reflection = true;
    boolean combination1 = true;
    boolean combination2 = true;
    boolean combination3 = true;
    boolean noChange = true;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (grid[i][j] != endGrid[j][N-i-1]) {
          // not 90 degree
          degree90 = false;
        }
        if (grid[i][j] != endGrid[j][i]) {
          // not 180 degree
          degree180 = false;
        }
        if (grid[i][j] != endGrid[N-j-1][i]) {
          // not 270 degree
          degree270 = false;
        }
        if (grid[i][j] != endGrid[i][N-j-1]) {
          // not reflection
          reflection = false;
        }
        if (grid[i][j] != endGrid[j][N-(N-i-1)-1]) {
          // not combination1
          combination1 = false;
        }
        if (grid[i][j] != endGrid[j][N-i-1]) {
          // not combination2
          combination2 = false;
        }
        if (grid[i][j] != endGrid[N-j-1][N-i-1]) {
          // not combination3
          combination3 = false;
        }
        if (grid[i][j] != endGrid[i][j]) {
          // not noChange
          noChange = false;
        }
      }
    }

    if (degree90) return 1;
    if (degree180) return 2;
    if (degree270) return 3;
    if (reflection) return 4;
    if (combination1) return 5;
    if (combination2) return 5;
    if (combination3) return 5;
    if (noChange) return 6;
    return 7;
  }
}