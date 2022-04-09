import java.util.Arrays;
import java.util.Scanner;

public class Dice {
  public static void main(String[] args) throws Exception {
//    Scanner sc = new Scanner(new java.io.File("1.in"));
    Scanner sc = new Scanner(System.in);
    
    int T = sc.nextInt();
    
    for (int i = 0; i < T; i++) {
      int[] d1 = new int[4];
      int[] d2 = new int[4];
      for (int j = 0; j < 4; j++) {
        d1[j] = sc.nextInt();
      }
      for (int j = 0; j < 4; j++) {
        d2[j] = sc.nextInt();
      }
      boolean result = false;
      if (canBeat(d1, d2)) {
        result = solve(d1, d2);
      } else {
        result = solve(d2, d1);
      }
      System.out.println(result? "yes" : "no");
    }
    
    sc.close();
  }
  
  private static boolean solve(int[] d1, int[] d2) {
    for (int i = 1; i < 11; i++) {
      for (int j = 1; j < 11; j++) {
        for (int k = 1; k < 11; k++) {
          for (int l = 1; l < 11; l++) {
            int[] d3 = {i, j, k, l};
            Boolean r1 = canBeat(d3, d1);
            Boolean r2 = canBeat(d3, d2);
            if (r1 != null && r2 != null && r1 && !r2) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  private static Boolean canBeat(int[] d1, int[] d2) {
    int count1 = 0;
    for (int d : d1) {
      for (int i = 0; i < 4; i++) {
        if (d > d2[i]) {
          count1++;
        }
      }
    }
    int count2 = 0;
    for (int d : d2) {
      for (int i = 0; i < 4; i++) {
        if (d > d1[i]) {
          count2++;
        }
      }
    }
    if (count1 == count2) {
      return null;
    }
    return count1 > count2;
  }
}
