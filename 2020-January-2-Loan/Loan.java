import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Loan {
  static long N;
  static long K;
  static long M;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("loan.in"));
    PrintWriter pw = new PrintWriter("loan.out");
    N = sc.nextLong();
    K = sc.nextLong();
    M = sc.nextLong();
    
    // solve
    long l = 0, r = (long) Math.pow(10, 12);
    // long l = 0, r = 10;
    while (l <= r) {
      long m = l + (r - l) / 2;
 
      if (check(m)) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }

    if (check(l)) {
      pw.println(l);
    } else {
      pw.println(r);
    }

    sc.close();
    pw.close();
  }

  private static boolean check(long X) {
    long given = 0;
    long days = 0;
    while (days < K && given < N) {
      long curGive = (N-given)/X;
      if (curGive <= M) {
        // need to give M units every time
        break;
      }
      given += curGive;
      days++;
    }

    if (given >= N) return true;
    if (days == K) return true;
    days += (long) Math.ceil((double)(N-given) / M);

    return days <= K;
  }
}