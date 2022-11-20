import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Revegetate {
  static StreamTokenizer st;
  static int N;
  static int M;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("revegetate.out"));
    N = nextInt();
    M = nextInt();
    
    // solve
    Grass[] grass = new Grass[N];
    for (int i = 0; i< N; i++) {
      grass[i] = new Grass(i);
    }

    for (int i = 0; i < M; i++) {
      boolean same = nextString().charAt(0) == 'S';
      Grass a = grass[nextInt()-1];
      Grass b = grass[nextInt()-1];

      if (same) {
        a.same.add(b);
        b.same.add(a);
      } else {
        a.different.add(b);
        b.different.add(a);
      }
    }

    boolean flag3 = false;
    int count = 0;
    for (int i = 0; i < N; i++) {
      if (grass[i].visited) continue;

      // new group
      count++;
      ArrayDeque<Grass> q = new ArrayDeque<>();
      q.add(grass[i]);
      grass[i].visited = true;
      grass[i].value = false;
      boolean flag2 = false;

      while (!q.isEmpty()) {
        Grass curGrass = q.poll();
        boolean flag = false;

        for (Grass s : curGrass.same) {
          if (s.visited) {
            if (s.value != curGrass.value) {
              // not good
              flag = true;
              break;
            }
          } else {
            q.add(s);
            s.visited = true;
            s.value = curGrass.value;
          }
        }

        if (flag) {
          flag2 = true;
          break;
        }

        for (Grass d : curGrass.different) {
          if (d.visited) {
            if (d.value == curGrass.value) {
              // not good
              flag = true;
              break;
            }
          } else {
            q.add(d);
            d.visited = true;
            d.value = !curGrass.value;
          }
        }

        if (flag) {
          flag2 = true;
          break;
        }
      }

      if (flag2) {
        flag3 = true;
        break;
      }
    }

    if (flag3) {
      pw.println(0);
    } else {
      StringBuilder sb = new StringBuilder();
      sb.append('1');
      for (int i = 0; i < count; i++) {
        sb.append('0');
      }

      pw.println(sb.toString());
    }

    br.close();
    pw.close();
  }

  private static class Grass {
    int id;
    Boolean value = null;
    boolean visited = false;
    List<Grass> same = new ArrayList<>();
    List<Grass> different = new ArrayList<>();

    public Grass(int id) {
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