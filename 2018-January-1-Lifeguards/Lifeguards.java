import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Lifeguards {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("lifeguards.in"));
    PrintWriter pw = new PrintWriter("lifeguards.out");
    N = sc.nextInt();
    
    // solve
    Cow[] cows = new Cow[N];
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(sc.nextInt(), sc.nextInt());
    }

    Arrays.sort(cows, new Comparator<Cow>() {
      public int compare(Cow a, Cow b) {
        if (a.s > b.s) {
          return 1;
        } else if (a.s < b.s) {
          return -1;
        } else {
          return a.e - b.e;
        }
      }
    });

    // calculate initial time
    int totalTime = cows[0].e - cows[0].s;
    int maxEnd = cows[0].e;
    for (int i = 1; i < N; i++) {
      if (maxEnd <= cows[i].s) {
        // not connected
        maxEnd = cows[i].e;
        totalTime += maxEnd - cows[i].s;
      } else {
        // connected
        totalTime += cows[i].e > maxEnd ? cows[i].e - maxEnd : 0;
        maxEnd = Math.max(maxEnd, cows[i].e);
      }
    }

    // find out which is the least work
    maxEnd = cows[0].e;
    int leastWork = cows[0].e - cows[0].s - (cows[1].s < cows[0].e ? cows[0].e - cows[1].s : 0);
    for (int i = 1; i < N-1; i++) {
      if (leastWork == 0) {
        // exit
        pw.println(totalTime);
        sc.close();
        pw.close();
        return;
      }
      int left = 0;
      if (maxEnd > cows[i].s) {
        // touching
        left = maxEnd - cows[i].s;
      }

      int right = 0;
      if (cows[i+1].s < cows[i].e) {
        // touching
        right = cows[i].e - cows[i+1].s;
      }

      maxEnd = Math.max(maxEnd, cows[i].e);
      leastWork = Math.min(leastWork, (cows[i].e - cows[i].s) - left - right);
    }

    // do last
    if (cows[N-2].e > cows[N-1].s) {
      // touching
      leastWork = Math.min(leastWork, cows[N-1].e - cows[N-1].s - (cows[N-2].e - cows[N-1].s));
    }

    pw.println(totalTime - leastWork);

    sc.close();
    pw.close();
  }

  private static class Cow {
    int s;
    int e;
    public Cow(int s, int e) {
      this.s = s;
      this.e = e;
    }
  }
}