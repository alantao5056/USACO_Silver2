import java.util.Scanner;

public class Intervals {
  public static void main(String[] args) throws Exception {
//    Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    
    int[] firstCounter = new int[M+1];
    int[] secondCounter = new int[M+1];
    for (int i = 0; i < N; i++) {
      firstCounter[sc.nextInt()]++;
      secondCounter[sc.nextInt()]++;
    }
    
    long[] prefix = new long[2 * M + 2];
    
    for (int i = 0; i < M+1; i++) {
      for (int j = 0; j < M+1; j++) {
        prefix[i+j] += (long) firstCounter[i] * firstCounter[j];
        prefix[i+j+1] -= (long) secondCounter[i] * secondCounter[j];
      }
    }
    
    System.out.println(prefix[0]);
    
    for (int i = 1; i < 2*M+1; i++) {
      prefix[i] += prefix[i-1];
      System.out.println(prefix[i]);
    }
    
    sc.close();
  }
}
