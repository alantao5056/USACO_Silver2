import java.util.*;
import java.io.PrintWriter;

public class Moocast {
  static int N;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("moocast.in"));
    PrintWriter pw = new PrintWriter("moocast.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    
    Cow[] cows = new Cow[N];

    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(sc.nextInt(), sc.nextInt(), i, sc.nextInt());
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i == j) continue;
        if (cows[i].canTransmit(cows[j])) {
          cows[i].neighbors.add(cows[j]);
        }
      }
    }

    int maxCount = 0;
    for (int i = 0; i < N; i++) {
      // bfs
      Queue<Cow> q = new LinkedList<>();
      boolean[] visited = new boolean[N];
      q.add(cows[i]);
      visited[i] = true;
      int count = 0;

      while (!q.isEmpty()) {
        Cow curCow = q.poll();
        count++;

        for (Cow c : curCow.neighbors) {
          if (!visited[c.index]) {
            q.add(c);
            visited[c.index] = true;
          }
        }
      }

      maxCount = Math.max(maxCount, count);
    }

    pw.println(maxCount);

    pw.close();
    sc.close();
  }

  public static class Cow {
    int x;
    int y;
    int index;
    int radius;
    List<Cow> neighbors = new ArrayList<>();

    public Cow(int x, int y, int index, int radius) {
      this.x = x;
      this.y = y;
      this.index = index;
      this.radius = radius;
    }

    public boolean canTransmit(Cow c) {
      return Math.sqrt(Math.pow(c.x-x, 2) + Math.pow(c.y-y, 2)) < radius;
    }
  }
}
