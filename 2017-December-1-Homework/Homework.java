import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Homework {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("homework.in"));
    PrintWriter pw = new PrintWriter("homework.out");
    N = sc.nextInt();
    
    // solve
    int[] scores = new int[N];

    for (int i = 0; i < N; i++) {
      scores[i] = sc.nextInt();
    }

    int[] prefixSmallest = new int[N];
    int[] prefixSum = new int[N];
    int curSmallest = Integer.MAX_VALUE;
    int curSum = 0;
    for (int i = N-1; i >= 0; i--) {
      curSum += scores[i];
      prefixSum[i] = curSum;
      curSmallest = Math.min(curSmallest, scores[i]);
      prefixSmallest[i] = curSmallest;
    }

    List<Integer> result = new ArrayList<>();
    double maxScore = 0;
    for (int i = 1; i < N-1; i++) {
      double curScore = (double) (prefixSum[i] - prefixSmallest[i])/(N-i-1);
      if (Double.compare(maxScore, curScore) < 0) {
        result = new ArrayList<>();
        result.add(i);
        maxScore = curScore;
      } else if (Double.compare(maxScore, curScore) == 0) {
        result.add(i);
      }
    }

    for (int r : result) {
      pw.println(r);
    }

    sc.close();
    pw.close();
  }
}