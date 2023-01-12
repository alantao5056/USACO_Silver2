import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Gates {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("gates.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("gates.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    Cell[][] grid = new Cell[2020][2020];

    for (int i = 0; i < 2020; i++) {
      for (int j = 0; j < 2020; j++) {
        grid[i][j] = new Cell();
        if (i == 0) {
          grid[i][j].up = true;
        }
        if (i == 2019) {
          grid[i][j].down = true;
        }
        if (j == 0) {
          grid[i][j].left = true;
        }
        if (j == 2019) {
          grid[i][j].right = true;
        }
      }
    }

    int curI = 1010;
    int curJ = 1010;

    char[] instructions = nextString().toCharArray();
    for (int i = 0; i < N; i++) {
      if (instructions[i] == 'N') {
        grid[curI][curJ].right = true;
        grid[curI][curJ+1].left = true;
        curI--;
      } else if (instructions[i] == 'E') {
        curJ++;
        grid[curI][curJ].down = true;
        grid[curI+1][curJ].up = true;
      } else if (instructions[i] == 'S') {
        curI++;
        grid[curI][curJ].right = true;
        grid[curI][curJ+1].left = true;
      } else {
        grid[curI][curJ].down = true;
        grid[curI+1][curJ].up = true;
        curJ--;
      }
    }

    // flood fill
    int count = 0;
    for (int i = 0; i < 2020; i++) {
      for (int j = 0; j < 2020; j++) {
        if (grid[i][j].visited) continue;
        count++;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(i);
        q.add(j);
        grid[i][j].visited = true;
        while (!q.isEmpty()) {
          curI = q.poll();
          curJ = q.poll();

          if (!grid[curI][curJ].up && !grid[curI-1][curJ].visited) {
            q.add(curI-1);
            q.add(curJ);
            grid[curI-1][curJ].visited = true;
          }
          if (!grid[curI][curJ].down && !grid[curI+1][curJ].visited) {
            q.add(curI+1);
            q.add(curJ);
            grid[curI+1][curJ].visited = true;
          }
          if (!grid[curI][curJ].left && !grid[curI][curJ-1].visited) {
            q.add(curI);
            q.add(curJ-1);
            grid[curI][curJ-1].visited = true;
          }
          if (!grid[curI][curJ].right && !grid[curI][curJ+1].visited) {
            q.add(curI);
            q.add(curJ+1);
            grid[curI][curJ+1].visited = true;
          }
        }
      }
    }
    
    pw.println(count-1);

    br.close();
    pw.close();
  }

  private static class Cell {
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    boolean visited;
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