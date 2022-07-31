import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Planting {
  static int N;
  static List<Integer>[] connections;
  static Set<Integer>[] cannotBe;
  static boolean[] visited;


  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("planting.in"));
    PrintWriter pw = new PrintWriter("planting.out");
    N = sc.nextInt();
    
    // solve
    connections = new ArrayList[N+1];
    for (int i = 1; i < N; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
    
      if (connections[a] == null) {
        connections[a] = new ArrayList<>();
      }
      connections[a].add(b);
    
      if (connections[b] == null) {
        connections[b] = new ArrayList<>();
      }
      connections[b].add(a);
    }

    // bfs
    cannotBe = new HashSet[N+1];
    for (int i = 1; i <= N; i++) {
      cannotBe[i] = new HashSet<>();
    }

    ArrayDeque<Integer> q = new ArrayDeque<>();
    visited = new boolean[N+1];
    q.add(1);
    visited[1] = true;
    int maxColor = 0;

    while (!q.isEmpty()) {
      int cur = q.poll();
      // find avaliable color
      int curColor = findAvaliableColor(cur);
      maxColor = Math.max(maxColor, curColor);

      if (connections[cur] == null) continue;
      for (int nb : connections[cur]) {
        cannotBe[nb].add(curColor);
        for (int nb2 : connections[nb]) {
          cannotBe[nb2].add(curColor);
        }

        if (!visited[nb]) {
          q.add(nb);
          visited[nb] = true;
        }
      }
    }

    pw.println(maxColor);

    sc.close();
    pw.close();
  }

  private static int findAvaliableColor(int cur) {
    for (int i = 1; i <= N; i++) {
      if (!cannotBe[cur].contains(i)) {
        // found
        return i;
      }
    }
    return -1;
  }
}