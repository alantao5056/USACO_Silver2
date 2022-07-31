import java.util.*;
import java.io.PrintWriter;

public class Closing {
  static int N;
  static int M;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("closing.in"));
    PrintWriter pw = new PrintWriter("closing.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    
    Barn[] barns = new Barn[N+1];

    for (int i = 0; i <= N; i++) {
      barns[i] = new Barn(i);
    }

    for (int i = 0; i < M; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      barns[a].neighbors.add(barns[b]);
      barns[b].neighbors.add(barns[a]);
    }

    for (int i = 0; i < N; i++) {
      int close;
      if (i == 0) close = 0;
      else close = sc.nextInt();

      barns[close].closed = true;

      // traverse farm

      boolean result = true;
      for (int j = 1; j <= N; j++) {
        if (barns[j].closed) continue;
        
        boolean[] visited = new boolean[N+1];

        Stack<Barn> s = new Stack<>();
        s.add(barns[j]);

        while (!s.isEmpty()) {
          Barn curBarn = s.pop();
          if (visited[curBarn.id]) continue;

          visited[curBarn.id] = true;

          for (Barn n : curBarn.neighbors) {
            if (!n.closed && !visited[n.id]) {
              s.add(n);
            }
          }
        }

        // check if all visited
        for (int k = 1; k <= N; k++) {
          if (!barns[k].closed && !visited[k]) {
            result = false;
            break;
          }
        }

        break;
      }

      pw.println(result?"YES":"NO");
    }

    pw.close();
    sc.close();
  }

  public static class Barn {
    int id;
    boolean closed = false;
    List<Barn> neighbors = new ArrayList<>();

    public Barn (int id) {
      this.id = id;
    }
  }
}
