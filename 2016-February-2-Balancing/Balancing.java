import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Balancing {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("balancing.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    Coord[] coords = new Coord[N];
    TreeSet<Coord> left = new TreeSet<>(new Comp());
    TreeSet<Coord> right = new TreeSet<>(new Comp());
    for (int i = 0; i < N; i++) {
      coords[i] = new Coord(nextInt(), nextInt());
      right.add(coords[i]);
    }

    Arrays.sort(coords, (Coord a, Coord b) -> a.x - b.x);

    int lastX = -1;
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      if (lastX != coords[i].x) {
        for (int j = 0; j < N; j++) {
          int leftCount = left.headSet(coords[j]).size();
          int leftRemain = left.size() - leftCount;

          int rightCount = right.headSet(coords[j]).size();
          int rightRemain = right.size() - rightCount;

          result = Math.min(result, Math.max(leftCount, Math.max(leftRemain, Math.max(rightCount, rightRemain))));
        }
      }

      right.remove(coords[i]);
      left.add(coords[i]);
      lastX = coords[i].x;
    }

    for (int j = 0; j < N; j++) {
      int leftCount = left.headSet(coords[j]).size();
      int leftRemain = left.size() - leftCount;

      int rightCount = right.headSet(coords[j]).size();
      int rightRemain = right.size() - rightCount;

      result = Math.min(result, Math.max(leftCount, Math.max(leftRemain, Math.max(rightCount, rightRemain))));
    }

    pw.println(result);

    br.close();
    pw.close();
  }

  private static class Comp implements Comparator<Coord> {
    @Override public int compare(Coord e1, Coord e2){
      return e1.y-e2.y;
    }
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