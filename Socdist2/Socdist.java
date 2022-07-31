import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Socdist {
  static int N;
  static int M;
  static Grass[] grass;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("socdist.in"));
    PrintWriter pw = new PrintWriter("socdist.out");
    N = sc.nextInt();
    M = sc.nextInt();
    
    // solve
    grass = new Grass[M];

    long minPos = Long.MAX_VALUE;
    long maxPos = -1;

    for (int i = 0; i < M; i++) {
      long s = sc.nextLong();
      long e = sc.nextLong();

      minPos = Math.min(minPos, s);
      maxPos = Math.max(maxPos, e);

      grass[i] = new Grass(s, e);
    }

    Arrays.sort(grass, new Comparator<Grass>() {
      public int compare(Grass a, Grass b) {
        if (a.s > b.s) {
          return 1;
        } else if (b.s > a.s) {
          return -1;
        } else {
          return 0;
        }
      }
    });

    // binary search D
    long l = 0, r = maxPos - minPos+1;
    while (l <= r) {
      long m = l + (r - l) / 2;
            
      if (canFit(m)) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    
    if (canFit(l)) {
      pw.println(l);
    } else {
      pw.println(r);
    }

    sc.close();
    pw.close();
  }

  private static boolean canFit(long D) {
    long lastPos = grass[0].s;
    int placed = 1;
    int i = 0;
    while (i < M) {
      if (placed >= N) {
        // done
        return true;
      }
      long targetPos = lastPos+D;
      if (targetPos <= grass[i].s) {
        // place at start
        lastPos = grass[i].s;
        placed++;
      } else if (targetPos <= grass[i].e) {
        // place at targetPos
        lastPos = targetPos;
        placed++;
      } else {
        // can't place on this grass
        i++;
      }
    }
    return false;
  }

  private static class Grass {
    long s;
    long e;

    public Grass(long s, long e) {
      this.s = s;
      this.e = e;
    }

    @Override
    public String toString() {
      return String.format("%d %d", s, e);
    }
  }
}