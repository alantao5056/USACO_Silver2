import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Soulmates {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("soulmates.in"));
    // Scanner sc = new Scanner(System.in);
    // PrintWriter pw = new PrintWriter("soulmates.out");
    N = sc.nextInt();
    
    // solve
    for (int i = 0; i < N; i++) {
      System.out.println(getMinSteps(sc.nextInt(), sc.nextInt()));
    }

    sc.close();
    // pw.close();
  }
  private static int getMinSteps(int num, int target) {
    int totalSteps = 0;
    while (num > target) {
      if (num %2!= 0) {
        num++;
        totalSteps++;
      }
      num /= 2;
      totalSteps++;
    }

    int minSteps = Integer.MAX_VALUE;
    int multiplyCount = 0;
    int temp = num;
    while (temp * 2 < target) {
      temp *= 2;
      multiplyCount++;
    }
    while (true) {
      int curTarget = target / ((int) Math.pow(2, multiplyCount));
      int steps;
      if (multiplyCount == 0) {
        steps = totalSteps + (curTarget - num);
      } else {
        steps = totalSteps + (curTarget - num) + multiplyCount + (target - (int) Math.pow(2, multiplyCount) * curTarget);
      }
      if (steps >= minSteps) {
        break;
      }
      minSteps = steps;
      if (num % 2 != 0) {
        num++;
        totalSteps++;
      }
      num /= 2;
      totalSteps++;
      multiplyCount++;
    }
    return minSteps;
  }
}