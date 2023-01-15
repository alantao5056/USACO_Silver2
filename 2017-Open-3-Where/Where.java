import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Where {
  static StreamTokenizer st;
  static int N;
  static char[][] grid;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("where.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("where.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    grid = new char[N][N];
    for (int i = 0; i < N; i++) {
      char[] line = nextString().toCharArray();
      for (int j = 0; j < N; j++) {
        grid[i][j] = line[j];
      }
    }

    List<Integer> minI_ = new ArrayList<>();
    List<Integer> maxI_ = new ArrayList<>();
    List<Integer> minJ_ = new ArrayList<>();
    List<Integer> maxJ_ = new ArrayList<>();
    
    for (int minI = 0; minI < N; minI++) {
      for (int maxI = minI; maxI < N; maxI++) {
        for (int minJ = 0; minJ < N; minJ++) {
          for (int maxJ = minJ; maxJ < N; maxJ++) {
            boolean stop = false;
            for (int i = 0; i < minI_.size(); i++) {
              if (isInPlc(minI, maxI, minJ, maxJ, minI_.get(i), maxI_.get(i), minJ_.get(i), maxJ_.get(i))) {
                stop = true;
                break;
              }
            }

            if (stop) continue;

            // check if it is plc
            if (isPlc(minI, maxI, minJ, maxJ)) {
              List<Integer> newMinI_ = new ArrayList<>();
              List<Integer> newMaxI_ = new ArrayList<>();
              List<Integer> newMinJ_ = new ArrayList<>();
              List<Integer> newMaxJ_ = new ArrayList<>();
              for (int i = 0; i < minI_.size(); i++) {
                if (!isInPlc(minI_.get(i), maxI_.get(i), minJ_.get(i), maxJ_.get(i), minI, maxI, minJ, maxJ)) {
                  newMinI_.add(minI_.get(i));
                  newMaxI_.add(maxI_.get(i));
                  newMinJ_.add(minJ_.get(i));
                  newMaxJ_.add(maxJ_.get(i));
                }
              }
              newMinI_.add(minI);
              newMaxI_.add(maxI);
              newMinJ_.add(minJ);
              newMaxJ_.add(maxJ);
              minI_ = newMinI_;
              maxI_ = newMaxI_;
              minJ_ = newMinJ_;
              maxJ_ = newMaxJ_;
            }
          }
        }
      }
    }

    pw.println(minI_.size());

    br.close();
    pw.close();
  }

  private static boolean isPlc(int minI, int maxI, int minJ, int maxJ) {
    boolean[][] visited = new boolean[N][N];
    char color1 = '.';
    char color2 = '.';
    char repeat = '.';
    for (int i = minI; i <= maxI; i++) {
      for (int j = minJ; j <= maxJ; j++) {
        if (visited[i][j]) continue;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(i);
        q.add(j);
        visited[i][j] = true;
        char curColor = grid[i][j];
        if (color1 == '.') {
          color1 = curColor;
        } else if (color2 == '.') {
          if (curColor == color1) {
            repeat = color1;
          } else {
            color2 = curColor;
          }
        } else {
          if (repeat == '.') {
            if (curColor != color1 && curColor != color2) return false;
            repeat = curColor;
          } else {
            if (curColor != repeat) {
              return false;
            }
          }
        }

        while (!q.isEmpty()) {
          int i_ = q.poll();
          int j_ = q.poll();

          if (i_+1 <= maxI && grid[i_+1][j_] == curColor && !visited[i_+1][j_]) {
            q.add(i_+1);
            q.add(j_);
            visited[i_+1][j_] = true;
          }
          
          if (j_+1 <= maxJ && grid[i_][j_+1] == curColor && !visited[i_][j_+1]) {
            q.add(i_);
            q.add(j_+1);
            visited[i_][j_+1] = true;
          }
          if (i_-1 >= minI && grid[i_-1][j_] == curColor && !visited[i_-1][j_]) {
            q.add(i_-1);
            q.add(j_);
            visited[i_-1][j_] = true;
          }
          if (j_-1 >= minJ && grid[i_][j_-1] == curColor && !visited[i_][j_-1]) {
            q.add(i_);
            q.add(j_-1);
            visited[i_][j_-1] = true;
          }
        }
      }
    }
    return color1 != '.' && color2 != '.' && repeat != '.';
  }

  private static boolean isInPlc(int minI, int maxI, int minJ, int maxJ, int minI_, int maxI_, int minJ_, int maxJ_) {
    return minI >= minI_ && minI <= maxI_ && maxI >= minI_ && maxI <= maxI_ && minJ >= minJ_ && minJ <= maxJ_ && maxJ >= minJ_ && maxJ <= maxJ_;
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