import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class Socdist {
  static int N;
  static int M;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("socdist.in"));
    PrintWriter pw = new PrintWriter("socdist.out");

    N = sc.nextInt();
    M = sc.nextInt();

    Grass[] grass = new Grass[M];

    for (int i = 0; i < M; i++) {
      grass[i] = new Grass(sc.nextLong(), sc.nextLong());
    }

    Arrays.sort(grass, new Comparator<Grass>() {
      public int compare(Grass a, Grass b) {
        return b.start > a.start ? -1 : b.start == a.start ? 0 : 1;
      }
    });

    // binary search d
    long low = 0;
    // long high = (long) Math.pow(10, 18);
    long high = (long) Math.pow(10, 18);

    while (low + 1 < high) {
      long D = (low + high)/2;
      if (canFit(grass, D)) {
        low = D;
      } else {
        high = D;
      }
    }

    pw.println(canFit(grass, high) ? high : low);

    pw.close();
    sc.close();
  }

  private static boolean canFit(Grass[] grass, long D) {
    int grassIdx = 0;
    long target = 0;
    int placed = 0;

    while (grassIdx < M && placed<N) {
      if (grass[grassIdx].end < target) {
        grassIdx++;
      } else {
        if (target < grass[grassIdx].start) {
          target = grass[grassIdx].start + D;
        } else {
          target += D;
        }
        placed++;
      }
    }
    return placed == N;
  }

  public static class Grass {
    long start;
    long end;
    public Grass(long start, long end) {
      this.start = start;
      this.end = end;
    }

    
  }
}