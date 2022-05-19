import java.util.*;
import java.io.PrintWriter;

public class Maxcross {
  static int N;
  static int K;
  static int B;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("maxcross.in"));
    PrintWriter pw = new PrintWriter("maxcross.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    K = sc.nextInt();
    B = sc.nextInt();

    // sliding window
    boolean[] lights = new boolean[N];

    for (int i = 0; i < B; i++) {
      lights[sc.nextInt()-1] = true;
    }

    int minCount = 0;
    for (int i = 0; i < K; i++) {
      if (lights[i]) minCount++;
    }

    int count = minCount;
    int i = 0;
    int j = K-1;
    while (j < N-1) {
      j++;
      count += lights[j] ? 1 : 0;
      count -= lights[i] ? 1 : 0;
      i++;
      minCount = Math.min(minCount, count);
    }
    
    pw.println(minCount);

    pw.close();
    sc.close();
  }
}
