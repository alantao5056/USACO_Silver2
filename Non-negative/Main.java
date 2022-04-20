import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("1.in"));

    int N = sc.nextInt();

    long[] prefix = new long[N];
    int[] arr = new int[N];
    int cur = 0;
    int min = Integer.MAX_VALUE;
    int minIdx = 0;
    for (int i = 0; i < N; i++) {
      int num = sc.nextInt();
      cur += num;
      arr[i] = num;
      prefix[i] = cur;
      if (cur < min) {
        min = cur;
        minIdx = i;
      }
    }
    if (cur < 0) {
      System.out.println(-1);
    } else if (min >= 0) {
      System.out.println(1);
    } else {
      System.out.println(minIdx+1);
    }

    sc.close();
  }
}