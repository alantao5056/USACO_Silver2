import java.util.Scanner;

public class Drought {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("1.in"));
//    Scanner sc = new Scanner(System.in);
    
    int T = sc.nextInt();
    
    for (int i = 0; i < T; i++) {
      int N = sc.nextInt();
      int[] cows = new int[N];
      for (int j = 0; j < N; j++) {
        cows[i] = sc.nextInt(); 
      }
      
      System.out.println(solve(N, cows));
    }
    
    sc.close();
  }
  
  private static long solve(int N, int[] cows) {
    
  }
}
