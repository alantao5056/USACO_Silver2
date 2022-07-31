import java.util.*;
import java.io.PrintWriter;

public class Wormsort {
  static int N;
  static int M;
  static Cow[] cows;
  static int connectedSize;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("1.in"));
    // PrintWriter pw = new PrintWriter("1.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    
    cows = new Cow[N];
    connectedSize = 0;

    for (int i = 0; i < N; i++) {
      int to = sc.nextInt()-1;
      if (sc.nextInt()-1 == i) {
        cows[i] = new Cow(true, i, to);
      } else {
        connectedSize++;
        cows[i] = new Cow(true, i, to);
      }
    }

    for (int i = 0; i < M; i++) {
      int a = sc.nextInt()-1;
      int b = sc.nextInt()-1;
      int weight = sc.nextInt();

      cows[a].nbs.add(cows[b]);
      cows[a].nbsWeight.add(weight);

      cows[b].nbs.add(cows[a]);
      cows[b].nbsWeight.add(weight);
    }

    

    // pw.close();
    sc.close();
  }

  private static boolean isConnected(int minWeight) {
    boolean[] visited = new boolean[N];
    int[] groups = new int[N];

    int curGroupNum = 1;
    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;
      ArrayDeque<Cow> q = new ArrayDeque<>();
      q.add(cows[i]);
      visited[i] = true;
      groups[i] = curGroupNum;

      while (!q.isEmpty()) {
        Cow curCow = q.poll();

        for (int j = 0; j < curCow.nbs.size(); j++) {
          Cow n = curCow.nbs.get(j);
          if (!visited[n.idx] && n.nbsWeight.get(j) >= minWeight) {
            q.add(n);
            visited[n.idx] = true;
          }
        }
      }
    }
    return false;
  }

  public static class Cow {
    int idx;
    int to;
    boolean inPosition;
    List<Cow> nbs = new ArrayList<>();
    List<Integer> nbsWeight = new ArrayList<>();

    public Cow (boolean inPosition, int idx, int to) {
      this.inPosition = inPosition;
      this.idx = idx;
      this.to = to;
    }

    @Override
    public String toString() {
      return Boolean.toString(inPosition);
    }
  }
}
