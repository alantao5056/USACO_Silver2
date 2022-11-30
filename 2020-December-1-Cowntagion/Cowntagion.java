import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Cowntagion {
  static StreamTokenizer st;
  static int N;
  static int days = 0;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("cowntagion.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("cowntagion.out"));
    N = nextInt();
    
    // solve
    Barn[] barns = new Barn[N];
    for (int i = 0; i < N; i++) {
      barns[i] = new Barn(i);
    }

    for (int i = 0; i < N-1; i++) {
      int a = nextInt()-1;
      int b = nextInt()-1;
      barns[a].nbs.add(barns[b]);
      barns[b].nbs.add(barns[a]);
    }

    barns[0].spread = 1;
    go(null, barns[0]);

    System.out.println(days);

    br.close();
    // pw.close();
  }

  private static void go(Barn parent, Barn cur) {
    if (parent != null && cur.nbs.size() <= 1) {
      return;
    }

    int nbSize = parent == null ? cur.nbs.size() : cur.nbs.size()-1;
    while (cur.spread <= nbSize) {
      cur.spread *= 2;
      days++;
    }

    for (Barn n : cur.nbs) {
      if (n == parent) continue;
      n.spread = 1;
      days++;
      go(cur, n);
    }
  }

  private static class Barn {
    int id;
    int spread;
    List<Barn> nbs = new ArrayList<>();

    public Barn (int id) {
      this.id = id;
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