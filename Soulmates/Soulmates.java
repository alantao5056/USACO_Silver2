import java.util.Scanner;

public class Soulmates {

  public static void main(String[] args) throws Exception {
//    Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    
    for (int i = 0; i < N; i++) {
      long a = sc.nextLong();
      long b = sc.nextLong();
      
      System.out.println(getMinChange(a, b));
    }
    
    sc.close();
  }
  
  private static long getMinChange(long a, long b) {
    long origA = a;
    boolean aSmaller = false;
    long count = 0;
    if (a > b) {
      while (a > b) {
        if ((a & 1) == 0) {
          // even
          a /= 2;
          count++;
        } else {
          a++;
          a /= 2;
          count += 2;
        }
      }
    } else {
      aSmaller = true;
      if (a * 2 <= b) {
        a *= 2;
        count++;
      }
    }
    
    if (a == b) return count;
    if ((a & 1) != 0) a++;
    
    long dividedBy = 0;
    long minChange = Long.MAX_VALUE;
    if (aSmaller) dividedBy = count;
    while (dividedBy < a) {
      long curA = a;
      for (long i = 0; i < dividedBy; i++) {
        if ((curA & 1) == 0) {
          // even
          curA /= 2;
        } else {
          curA++;
          curA /= 2;
        }
      }
      
      long add = (long) Math.pow(2, dividedBy);
      long numOfAdds = (b-a)/add;
      long totalChange = 0;
      if (!aSmaller) {
        totalChange = count + numOfAdds + (b - (a + numOfAdds * add)) + dividedBy * 2;
      } else {
        totalChange = numOfAdds + (b - (a + numOfAdds * add)) + (dividedBy - count) + 1;
      }
      if (totalChange >= minChange) {
        return minChange;
      }
      minChange = totalChange;
      dividedBy++;
    }
    
    return -1;
  }
}