import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Reduce {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("reduce.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    List<Coord> coordsX = new ArrayList<>();
    List<Coord> coordsY = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      Coord cur = new Coord(nextInt(), nextInt());
      coordsX.add(cur);
      coordsY.add(cur);
    }

    Collections.sort(coordsX, (Coord a, Coord b) -> a.x-b.x);
    Collections.sort(coordsY, (Coord a, Coord b) -> a.y-b.y);

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        for (int k = 0; k < 4; k++) {
          for (int l = 0; l < 4; l++) {
            int minX = coordsX.get(i).x;
            int maxX = coordsX.get(N-j-1).x;
            int minY = coordsY.get(k).y;
            int maxY = coordsY.get(N-l-1).y;
            if (minX > maxX || minY > maxY) continue;

            int count = 0;
            for (int m = 0; count <= 3 && m < N; m++) {
              if (coordsX.get(m).x < minX || coordsX.get(m).x > maxX || coordsX.get(m).y < minY || coordsX.get(m).y > maxY) {
                count++;
              }
            }

            if (count <= 3) {
              result = Math.min(result, (maxX-minX) * (maxY-minY));
            }
          }
        }
      }
    }

    pw.println(result);

    br.close();
    pw.close();
  }

  private static class Coord {
    int x;
    int y;
    public Coord(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return x + " " + y;
    }
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