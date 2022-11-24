import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Leftout {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("leftout.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("leftout.out"));
    N = Integer.parseInt(br.readLine());
    boolean[][] grid = new boolean[N][N];
    
    // solve
    for (int i = 0; i < N; i++) {
      char[] line = br.readLine().toCharArray();

      for (int j = 0; j < N; j++) {
        grid[i][j] = line[j] == 'R';
      }
    }

    

    boolean allFalse = true;
    int oneCount = 0;
    for (int i = 1; i < N; i++) {
      for (int j = 1; j < N; j++) {
        grid[i][j] ^= grid[i][0] ^ grid[0][j];
        oneCount += grid[i][j] ? 0 : 1;
        allFalse &= !grid[i][j];
      }
    }
    
    if (allFalse) {
      pw.println("1 1");
      br.close();
      pw.close();
      return;
    }

    for (int i = 1; i < N; i++) {
      boolean fullColumn = true;
      for (int j = 1; j < N; j++) {
        if (grid[j][i]) {
          fullColumn = false;
          break;
        }
      }

      if (fullColumn) {
        pw.println("1 " + Integer.toString(i+1));
        br.close();
        pw.close();
        return;
      }
    }

    for (int i = 1; i < N; i++) {
      boolean fullRow = true;
      for (int j = 1; j < N; j++) {
        if (grid[i][j]) {
          fullRow = false;
          break;
        }
      }

      if (fullRow) {
        pw.println(Integer.toString(i+1) + " 1");
        br.close();
        pw.close();
        return;
      }
    }

    if (oneCount != 1) {
      pw.println(String.format("-1"));
      br.close();
      pw.close();
      return;
    }

    for (int i = 1; i < N; i++) {
      for (int j = 1; j < N; j++) {
        if (!grid[i][j]) {
          pw.println(String.format("%d %d", i+1, j+1));
          br.close();
          pw.close();
          return;
        }
      }
    }

    br.close();
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