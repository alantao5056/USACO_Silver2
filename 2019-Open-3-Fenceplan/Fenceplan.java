import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Fenceplan {
  static StreamTokenizer st;
  static int N;
  static int M;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("fenceplan.out"));
    N = nextInt();
    M = nextInt();
    
    // solve
    Cow[] cows = new Cow[N];
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(i, nextInt(), nextInt());
    }

    for (int i = 0; i < M; i++) {
      int a = nextInt()-1;
      int b = nextInt()-1;
      cows[a].nbs.add(cows[b]);
      cows[b].nbs.add(cows[a]);
    }

    int minPerimeter = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      if (cows[i].visited) continue;

      ArrayDeque<Cow> q = new ArrayDeque<>();
      q.add(cows[i]);
      cows[i].visited = true;
      int minX = Integer.MAX_VALUE;
      int maxX = 0;
      int minY = Integer.MAX_VALUE;
      int maxY = 0;
      
      while (!q.isEmpty()) {
        Cow curCow = q.poll();
        minX = Math.min(minX, curCow.x);
        maxX = Math.max(maxX, curCow.x);
        minY = Math.min(minY, curCow.y);
        maxY = Math.max(maxY, curCow.y);

        for (Cow c : curCow.nbs) {
          if (c.visited) continue;
          q.add(c);
          c.visited = true;
        }
      }

      minPerimeter = Math.min(minPerimeter, (maxX-minX+maxY-minY)*2);
    }

    pw.println(minPerimeter);

    br.close();
    pw.close();
  }

  private static class Cow {
    int x;
    int y;
    int id;
    boolean visited;
    List<Cow> nbs = new ArrayList<>();

    public Cow(int id, int x, int y) {
      this.id = id;
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