import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Comfortable {
  static int N;
  static int[][] grid;
  static int addedCowsNum;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("comfortable.in"));
    // Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    
    // solve
    grid = new int[2003][2003];

    for (int i = 0; i < 2003; i++) {
      for (int j = 0; j < 2003; j++) {
        grid[i][j] = -1;
      }
    }

    addedCowsNum = 0;
    for (int i = 0; i < N; i++) {
      int curI = sc.nextInt()+500;
      int curJ = sc.nextInt()+500;

      addCow(curI, curJ);

      System.out.println(addedCowsNum);
    }

    sc.close();
  }

  private static void addCow(int i, int j) {
    if (grid[i][j] != -1) {
      addedCowsNum--;
      return;
    }

    int cowCount = 0;
    if (grid[i][j+1] != -1) {
      cowCount++;
      grid[i][j+1]++;
    }
    if (grid[i+1][j] != -1) {
      cowCount++;
      grid[i+1][j]++;
    }
    if (grid[i][j-1] != -1) {
      cowCount++;
      grid[i][j-1]++;
    }
    if (grid[i-1][j] != -1) {
      cowCount++;
      grid[i-1][j]++;
    }

    grid[i][j] = cowCount;

    if (cowCount == 3) {
      addedCowsNum++;
      if (grid[i+1][j] == -1) {
        addCow(i+1, j);
      } else if (grid[i][j+1] == -1) {
        addCow(i, j+1);
      } else if (grid[i-1][j] == -1) { // grid[i-1][j+1]
        addCow(i-1, j);
      } else {
        addCow(i, j-1);
      }
    }

    if (grid[i][j+1] == 3) {
      addedCowsNum++;
      if (grid[i+1][j+1] == -1) {
        addCow(i+1, j+1);
      } else if (grid[i][j+2] == -1) {
        addCow(i, j+2);
      } else { // grid[i-1][j+1]
        addCow(i-1, j+1);
      }
    }
    if (grid[i+1][j] == 3) {
      addedCowsNum++;
      if (grid[i+2][j] == -1) {
        addCow(i+2, j);
      } else if (grid[i+1][j+1] == -1) {
        addCow(i+1, j+1);
      } else { // grid[i+1][j-1]
        addCow(i+1, j-1);
      }
    }
    if (grid[i][j-1] == 3) {
      addedCowsNum++;
      if (grid[i+1][j-1] == -1) {
        addCow(i+1, j-1);
      } else if (grid[i-1][j-1] == -1) {
        addCow(i-1, j-1);
      } else { // grid[i][j-2]
        addCow(i, j-2);
      }
    }
    if (grid[i-1][j] == 3) {
      addedCowsNum++;
      if (grid[i-1][j+1] == -1) {
        addCow(i-1, j+1);
      } else if (grid[i-2][j] == -1) {
        addCow(i-2, j);
      } else { // grid[i-1][j-1]
        addCow(i-1, j-1);
      }
    }
  }
}