import java.io.PrintWriter;
import java.util.Scanner;

public class Hps {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("hps.in"));
    PrintWriter pw = new PrintWriter("hps.out");

    int N = sc.nextInt();

    char[] fj = new char[N+1];
    int[] HPrefix = new int[N+1];
    int[] PPrefix = new int[N+1];
    int[] SPrefix = new int[N+1];

    for (int i = 1; i <= N; i++) {
      fj[i] = sc.next().charAt(0);
      HPrefix[i] = HPrefix[i-1];
      PPrefix[i] = PPrefix[i-1];
      SPrefix[i] = SPrefix[i-1];
      if (fj[i] == 'H') {
        HPrefix[i]++;
      } else if (fj[i] == 'P') {
        PPrefix[i]++;
      } else {
        SPrefix[i]++;
      }
    }

    int maxWins = 0;
    for (int i = 1; i < N; i++) {
      maxWins = Math.max(maxWins, Math.max(Math.max(getPrefix(HPrefix, 1, i), getPrefix(PPrefix, 1, i)), getPrefix(SPrefix, 1, i)) + Math.max(Math.max(getPrefix(HPrefix, i+1, N), getPrefix(PPrefix, i+1, N)), getPrefix(SPrefix, i+1, N)));
    }

    pw.println(maxWins);

    sc.close();
    pw.close();
  }

  private static int getPrefix(int[] prefix, int start, int end) {
    return prefix[end] - prefix[start-1];
  }
}