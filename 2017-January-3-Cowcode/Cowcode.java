import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Cowcode {
  static Integer[] s;
  static int N;
  static long target;
  static int getCount = 0;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("cowcode.in"));
    PrintWriter pw = new PrintWriter("cowcode.out");
    char[] input = sc.next().toCharArray();
    N = input.length;
    s = new Integer[N];
    
    for (int i = 0; i < N; i++) {
      s[i] = input[i] - 'A' + 1;
    }

    target = sc.nextLong()-1;
    
    // solve
    int resultNum = getChar(target, findScopeAndCount(target), getCount);

    pw.println(String.valueOf((char)(resultNum + 64)));

    sc.close();
    pw.close();
  }

  private static int getChar(long target, long scope, int count) {
    if (scope == N) {
      return s[(int) target];
    }
    if (target > scope / 2) {
      // right side
      long delta = scope - target;
      long nextTarget = scope / 2 - 1 - delta;
      return getChar(nextTarget, findScopeAndCount(nextTarget), getCount);
    } else {
      // middle
      List<Integer> s2 = Arrays.asList(s);
      int count2 = 0;
      while (count - count2 >= s2.size()) {
        List<Integer> s3 = new ArrayList<>(s2);
        s3.add(s2.get(s2.size()-1));
        for (int i = 0; i < s2.size()-2; i++) {
          s3.add(s2.get(i));
        }
        s2 = s3;
        count2++;
      }

      return s2.get(s2.size() - (count-count2));
    }
  }

  private static long findScopeAndCount(long idx) {
    long scope = N;
    getCount = 0;
    while (idx >= scope) {
      // expand scope
      scope *= 2;
      getCount++;
    }
    return scope;
  }
}