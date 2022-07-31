import java.util.*;
import java.io.PrintWriter;

public class Wormsort2 {
  static int N;
  static int M;
  static int connectedSize;
  static List<To>[] connection;
  static int[] cows;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("wormsort.in"));
    PrintWriter pw = new PrintWriter("wormsort.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();

    cows = new int[N+1];
    boolean sorted = true;
    for (int i = 1; i <= N; i++) {
      cows[i] = sc.nextInt();
      if (cows[i] != i) {
        sorted = false;
      }
    }
    if (sorted) {
      pw.println(-1);
      pw.close();
      sc.close();
      return;
    }
    
    connection = new ArrayList[N+1];

    for (int i = 1; i <= M; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      int weight = sc.nextInt();

      if (connection[a] == null) {
        connection[a] = new ArrayList<>();
      }
      connection[a].add(new To(b, weight));

      if (connection[b] == null) {
        connection[b] = new ArrayList<>();
      }
      connection[b].add(new To(a, weight));
    }

    int l = 0, r = (int) Math.pow(10, 9)+1;
    while (l <= r) {
      int m = l + (r - l) / 2;

      if (isConnected(m)) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }

    if (isConnected(l)) {
      pw.println(l);
    } else {
      pw.println(r);
    }

    sc.close();
    pw.close();
  }

  private static boolean isConnected(int minWeight) {
    // split to groups
    int[] groups = new int[N+1];
    List<Set<Integer>> groups2 = new ArrayList<>();
    groups2.add(new HashSet<>());

    int curGroupNum = 1;
    for (int i = 1; i < N+1; i++) {
      if (groups[i] != 0) continue;

      groups[i] = curGroupNum;
      groups2.add(new HashSet<>());
      groups2.get(curGroupNum).add(i);

      if (connection[i] == null) {
        continue;
      }

      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.add(i);

      while (!q.isEmpty()) {
        int curIdx = q.poll();

        for (int j = 0; j < connection[curIdx].size(); j++) {
          int to = connection[curIdx].get(j).to;
          int weight = connection[curIdx].get(j).weight;
          if (groups[to] == 0 && weight >= minWeight) {
            groups[to] = curGroupNum;
            groups2.get(curGroupNum).add(to);
            q.add(to);
          }
        }
      }
      curGroupNum++;
    }

    for (int i = 1; i < N+1; i++) {
      if (!groups2.get(groups[i]).contains(cows[i])) {
        return false;
      }
    }

    return true;
  }

  public static class To {
    int to;
    int weight;

    public To (int to, int weight) {
      this.to = to;
      this.weight = weight;
    }

    @Override
    public String toString() {
      return Integer.toString(to);
    }
  }
}