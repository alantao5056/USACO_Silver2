import java.util.Scanner;

public class Soulmates2 {

  public static void main(String[] args) throws Exception {
//    Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    
    for (int i = 0; i < N; i++) {
      long a = sc.nextLong();
      long b = sc.nextLong();
      
      System.out.println(solve(a, b));
    }
    
    sc.close();
  }
  
  
  private static long solve(long a, long b) {
    long minCount = Long.MAX_VALUE;
    long count = 0;
    if (a == b) {
      return 0;
    }
    while (a > 1 && b > 1) {
      if (a == b) {
        minCount = Math.min(count, minCount);
        break;
      }
      if (a > b) {
        if (a%2 == 1) {
          a++;
          count++;
        }
        a /= 2;
        count++;
      } else { // b > a
        minCount = Math.min(count + b-a, minCount);
        if (b%2 == 1) {
          b--;
          count++;
        }
        b /= 2;
        count++;
      }
    }
    
    return minCount;
  }

}
