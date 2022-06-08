import java.io.PrintWriter;
import java.time.chrono.IsoChronology;
import java.util.*;

/**
 * WormholeSortBFS
 */
public class WormholeSortBFS {
  private static int N;
  private static int M;
  private static int[] positions;
  private static List<Path>[] nbs;
  private static int[] groups;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new java.io.File("wormsort.in"));
    N = sc.nextInt();
    M = sc.nextInt();
    positions = new int[N + 1];
    boolean sorted = true;
    for (int i = 1; i < N + 1; i++) {
      positions[i] = sc.nextInt();
      if (i != positions[i]) {
        sorted = false;
      }
    }
    nbs = new List[N + 1];
    int from, to, width;
    int minW = Integer.MAX_VALUE, maxW = 0;
    for (int i = 0; i < M; i++) {
      from = sc.nextInt();
      to = sc.nextInt();
      width = sc.nextInt();
      minW = Math.min(minW, width);
      maxW = Math.max(maxW, width);
      if (nbs[from] == null) nbs[from] = new ArrayList<Path>();
      if (nbs[to] == null) nbs[to] = new ArrayList<Path>();
      nbs[from].add(new Path(to, width));
      nbs[to].add(new Path(from, width));
    }
    sc.close();

    // solve
    PrintWriter pw = new PrintWriter("wormsort.out");
    if (sorted) {
      pw.println("-1");
      pw.close();
      return;
    }
    groups = new int[N + 1];

    // binary search
    int low = minW;
    int high = maxW;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (isConnected(mid)) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    if (isConnected(low)) {
      pw.println(low);
    } else {
      pw.println(high);
    }
    pw.close();
  }

  private static boolean isConnected(int width) {
    Arrays.fill(groups, 0);
    int groupNo = 0;
    for (int i = 1; i < N + 1; i++) {
      if (groups[i] > 0) {
        continue;
      }
      groupNo++;
      groups[i] = groupNo;

      if (nbs[i] == null) continue;

      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.add(i);

      while (!q.isEmpty()) {
        int curCow = q.poll();

        for (var n : nbs[curCow]) {
          if (groups[n.nb] == 0 && n.width >= width) {
            q.add(n.nb);
            groups[n.nb] = groupNo;
          }
        }
      }
    }

    for (int i = 1; i < N + 1; i++) {
      if (groups[i] != groups[positions[i]]) {
        return false;
      }
    }

    return true;
  }

  public static class Path {
    public int nb;
    public int width;

    public Path(int nb, int width) {
      this.nb = nb;
      this.width = width;
    }
  }
}