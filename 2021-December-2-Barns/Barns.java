import java.util.*;

public class Barns {
  static int T;
  static int left;
  static int right;

  public static void main(String[] args) throws Exception {
    // read input
    // Scanner sc = new Scanner(new File("barns.in"));
    Scanner sc = new Scanner(System.in);
    T = sc.nextInt();
    
    // solve
    for (int i = 0; i < T; i++) {
      int N = sc.nextInt();
      int M = sc.nextInt();

      Barn[] barns = new Barn[N+1];
      for (int j = 1; j <= N; j++) {
        barns[j] = new Barn(j);
      }

      for (int j = 0; j < M; j++) {
        int a = sc.nextInt();
        int b = sc.nextInt();

        barns[a].nbs.add(barns[b]);
        barns[b].nbs.add(barns[a]);
      }

      System.out.println(getMinCost(N, M, barns));
    }

    sc.close();
  }

  private static long getMinCost(int N, int M, Barn[] barns) {
    // split into groups
    List<List<Barn>> groups = new ArrayList<>();

    for (int i = 1; i <= N; i++) {
      if (barns[i].visited) continue;

      List<Barn> curGroup = new ArrayList<>();
      curGroup.add(barns[i]);
      barns[i].visited = true;

      ArrayDeque<Barn> q = new ArrayDeque<>();
      q.add(barns[i]);

      while (!q.isEmpty()) {
        Barn curBarn = q.poll();
        for (Barn b : curBarn.nbs) {
          if (!b.visited) {
            q.add(b);
            b.visited = true;
            curGroup.add(b);
          }
        }
      }

      Collections.sort(curGroup, (a, b) -> {return a.id - b.id;});

      groups.add(curGroup);
    }

    // find startGroup and endGroup
    List<Barn> startGroup = new ArrayList<>();
    List<Barn> endGroup = new ArrayList<>();

    for (var g : groups) {
      if (g.get(0).id == 1) {
        startGroup = g;
      }
      if (g.get(g.size()-1).id == N) {
        endGroup = g;
      }
    }

    if (startGroup == endGroup) {
      // same group
      return 0;
    }

    // check direct connection
    long minCost = Long.MAX_VALUE;
    for (Barn s : startGroup) {
      binarySearch(endGroup, s.id);

      if (left > -1) {
        minCost = Math.min(minCost, getCost(endGroup.get(left), s));
      }
      if (right < endGroup.size()) {
        minCost = Math.min(minCost, getCost(endGroup.get(right), s));
      }
    }

    // check all other
    for (var g : groups) {
      if (g == startGroup || g == endGroup) continue;

      long minStartCost = Long.MAX_VALUE;
      long minEndCost = Long.MAX_VALUE;

      for (Barn b : g) {
        binarySearch(startGroup, b.id);
        long startCost = Math.min(left > -1 ? getCost(startGroup.get(left), b) : Long.MAX_VALUE,
                                  right < startGroup.size() ? getCost(startGroup.get(right), b) : Long.MAX_VALUE);

        binarySearch(endGroup, b.id);
        long endCost = Math.min(left > -1 ? getCost(endGroup.get(left), b) : Long.MAX_VALUE,
                                right < endGroup.size() ? getCost(endGroup.get(right), b) : Long.MAX_VALUE);

        minStartCost = Math.min(minStartCost, startCost);
        minEndCost = Math.min(minEndCost, endCost);
      }

      minCost = Math.min(minCost, minStartCost + minEndCost);
    }

    return minCost;
  }

  private static long getCost(Barn a, Barn b) {
    return (long) Math.pow(a.id - b.id, 2);
  }

  private static void binarySearch(List<Barn> group, int target) {
    int l = 0, r = group.size() - 1;
    while (l <= r) {
      int m = l + (r - l) / 2;
            
      if (group.get(m).id < target) {
        l = m + 1;
      } else if (group.get(m).id > target) {
        r = m - 1;
      } else {
        // equals
        left = m;
        right = m;
        return;
      }
    }

    right = l;
    left = r;
  }

  private static class Barn {
    int id;
    boolean visited = false;
    List<Barn> nbs = new ArrayList<>();

    public Barn(int id) {
      this.id = id;
    }

    @Override
    public String toString() {
      return Integer.toString(id);
    }
  }
}