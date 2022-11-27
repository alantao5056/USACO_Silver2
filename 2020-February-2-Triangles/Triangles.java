import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Triangles {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("triangles.out"));
    N = nextInt();
    
    // solve
    Coord[] coords = new Coord[N];
    for (int i = 0; i < N; i++) {
      coords[i] = new Coord(nextInt(), nextInt());
    }

    // sort by x
    Arrays.sort(coords, (Coord a, Coord b) -> a.x > b.x ? 1 : a.x < b.x ? -1 : a.y-b.y);

    Coord lastCoord = coords[0];
    long curTotal = 0;
    List<Coord> curY = new ArrayList<>();
    for (int i = 1; i <= N; i++) {
      if (i != N && coords[i].x == lastCoord.x) {
        // same
        curY.add(coords[i]);
        curTotal += coords[i].y-lastCoord.y;
      } else {
        // compute
        int add = 0;
        int sub = curY.size()-1;
        int lastY = lastCoord.y;
        lastCoord.yTotal = curTotal;
        for (Coord y : curY) {
          curTotal += add * (y.y-lastY);
          curTotal -= sub * (y.y-lastY);
          lastY = y.y;
          add++;
          sub--;
          y.yTotal = curTotal;
        }

        if (i < N) {
          lastCoord = coords[i];
          curTotal = 0;
          curY = new ArrayList<>();
        }
      }
    }

    // sort by y
    Arrays.sort(coords, (Coord a, Coord b) -> a.y > b.y ? 1 : a.y < b.y ? -1 : a.x-b.x);

    lastCoord = coords[0];
    curTotal = 0;
    List<Coord> curX = new ArrayList<>();
    for (int i = 1; i <= N; i++) {
      if (i != N && coords[i].y == lastCoord.y) {
        // same
        curX.add(coords[i]);
        curTotal += coords[i].x-lastCoord.x;
      } else {
        // compute
        int add = 0;
        int sub = curX.size()-1;
        int lastX = lastCoord.x;
        lastCoord.xTotal = curTotal;
        for (Coord x : curX) {
          curTotal += add * (x.x-lastX);
          curTotal -= sub * (x.x-lastX);
          lastX = x.x;
          add++;
          sub--;
          x.xTotal = curTotal;
        }

        if (i < N) {
          lastCoord = coords[i];
          curTotal = 0;
          curX = new ArrayList<>();
        }
      }
    }

    long total = 0;
    for (int i = 0; i < N; i++) {
      total += (coords[i].xTotal * coords[i].yTotal) % 1000000007;
      total %= 1000000007;
    }

    pw.println(total);

    br.close();
    pw.close();
  }

  private static class Coord {
    int x;
    int y;
    long yTotal;
    long xTotal;

    public Coord(int x, int y) {
      this.x = x;
      this.y = y;
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