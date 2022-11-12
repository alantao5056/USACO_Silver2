import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Teleportation {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("teleport.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("teleport.out"));
    N = nextInt();
    
    // solve
    List<Point> points = new ArrayList<>();
    int[] starts = new int[N];
    int[] ends = new int[N];
    long noTeleport = 0;
    for (int i = 0; i < N; i++) {
      int a = nextInt();
      int b = nextInt();
      starts[i] = a;
      ends[i] = b;
      noTeleport += Math.abs(a-b);

      // check if cannot contribute
      if (Math.abs(a) >= Math.abs(b-a)) continue;
      points.add(new Point(b, -2));
      if ((a < b && a < 0) || (a >= b && a >= 0)) {
        points.add(new Point(0, 1));
        points.add(new Point(2*b, 1));
      } else if ((a < b && a >= 0) || (a >= b && a < 0)){
        points.add(new Point(2*b-2*a, 1));
        points.add(new Point(2*a, 1));
      }
    }

    Collections.sort(points, (a, b) -> {return a.x - b.x;});

    int curAdd = points.get(0).add;
    int curPos = points.get(0).x;
    long maxValue = 0;
    long curValue = 0;
    for (int i = 1; i < points.size(); i++) {
      long deltaY = (points.get(i).x-curPos) * ((long) curAdd);
      curValue += deltaY;
      if (curValue > maxValue) {
        maxValue = curValue;
      }
      curPos = points.get(i).x;
      curAdd += points.get(i).add;
    }

    // long total = 0;
    // for (int i = 0; i < N; i++) {
    //   int a = starts[i];
    //   int b = ends[i];
    //   total += Math.min(Math.abs(a-b), Math.abs(a) + Math.abs(bestIdx-b));
    // }

    pw.println(noTeleport - maxValue);
    // pw.println(total);

    br.close();
    pw.close();
  }

  private static class Point {
    int x;
    int add;
    public Point(int x, int add) {
      this.x = x;
      this.add = add;
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