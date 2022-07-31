import java.io.PrintWriter;
import java.util.*;

/**
 * WormholeSortDisjoint
 */
public class WormholeSortDisjoint {
  private static int N;
  private static int M;
  private static int[] positions;
  private static Path[] paths;
  private static int[] connected;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new java.io.File("wormsort.in"));
    N = sc.nextInt();
    M = sc.nextInt();
    positions = new int[N + 1];
    connected = new int[N+1];
    boolean sorted = true;
    for (int i = 1; i < N + 1; i++) {
      positions[i] = sc.nextInt();
      if (i != positions[i]) {
        sorted = false;
      }
    }
    int from, to, width;
    int minW = Integer.MAX_VALUE, maxW = 0;
    paths = new Path[M];
    for (int i = 0; i < M; i++) {
      from = sc.nextInt();
      to = sc.nextInt();
      width = sc.nextInt();
      minW = Math.min(minW, width);
      maxW = Math.max(maxW, width);
      paths[i] = new Path(from, to, width);
    }

    // sort 
    Arrays.sort(paths, new Comparator<Path>() {
      public int compare(Path a, Path b) {
        return a.width - b.width;
      }
    });

    sc.close();

    // solve
    PrintWriter pw = new PrintWriter("wormsort.out");
    if (sorted) {
      pw.println("-1");
      pw.close();
      return;
    }

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
    Arrays.fill(connected, -1);
    for (int i = M-1; i >= 0; i--) {
      if (paths[i].width < width) break;
      int rootA = getRoot(paths[i].a);
      int rootB = getRoot(paths[i].b);

      if (rootA == rootB) continue;

      if (connected[rootA] > connected[rootB]) {
        // a merge to b
        connected[rootB] += connected[rootA];
        connected[rootA] = rootB;
      } else {
        connected[rootA] += connected[rootB];
        connected[rootB] = rootA;
      }
    }

    // check if all connected
    for (int i = 1; i < N+1; i++) {
      int rootI = getRoot(i);
      int rootTo = getRoot(positions[i]);

      if (rootI != rootTo) return false;
    }
    return true;
  }

  private static int getRoot(int i) {
    while (connected[i] > 0) {
      i = connected[i];
    }
    return i;
  }

  public static class Path {
    public int a;
    public int b;
    public int width;

    public Path(int a, int b, int width) {
      this.a = a;
      this.b = b;
      this.width = width;
    }
  }
}