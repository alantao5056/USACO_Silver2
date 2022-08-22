import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Cowdance {
  static int N;
  static int TMax;
  static int[] cows;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("cowdance.in"));
    PrintWriter pw = new PrintWriter("cowdance.out");
    N = sc.nextInt();
    TMax = sc.nextInt();
    
    // solve
    cows = new int[N];

    for (int i = 0; i < N; i++) {
      cows[i] = sc.nextInt();
    }

    // binary search K
    int l = 0, r = N;
    while (l <= r) {
      int K = l + (r - l) / 2;

      if (!canDance(K)) {
        l = K + 1;
      } else {
        r = K - 1;
      }
    }

    if (canDance(r)) {
      pw.println(r);
    } else {
      pw.println(l);
    }

    sc.close();
    pw.close();
  }

  private static boolean canDance(int K) {
    int[] stage = new int[K];

    for (int i = 0; i < K; i++) {
      stage[i] = cows[i];
    }

    int nextCowIdx = K;
    long time = 0;

    while (nextCowIdx < N) {
      // find minimum
      int minTime = Integer.MAX_VALUE;
      for (int i = 0; i < K; i++) {
        minTime = Math.min(minTime, stage[i]);
      }

      for (int i = 0; i < K; i++) {
        stage[i] -= minTime;
        if (stage[i] == 0) {
          if (nextCowIdx == N) {
            break;
          }
          stage[i] = cows[nextCowIdx];
          nextCowIdx++;
        }
      }

      time += minTime;
    }

    int maxTime = 0;
    for (int i = 0; i < K; i++) {
      maxTime = Math.max(maxTime, stage[i]);
    }

    time += maxTime;
    return time <= TMax;
  }
}