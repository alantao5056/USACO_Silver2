import java.util.Scanner;

public class RotationAlanTao {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("rotation.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    
    for (int i = 0; i < N; i++) {
      char[] s = sc.next().toCharArray();

      int[] newS = new int[s.length];
      for (int j = 0; j < s.length; j++) {
        newS[j] = s[j]-'0';
      }

      System.out.println(getFixes(newS));
    }

    sc.close();
  }

  private static int getFixes(int[] newS) {
    // target: all the same
    int[] numHash = new int[10];
    for (int s : newS) {
      numHash[s]++;
    }

    int maxNum = 0;
    for (int n : numHash) {
      maxNum = Math.max(maxNum, n);
    }

    int steps = newS.length - maxNum;

    // target: ex 2323232323
    // brute force

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (i == j) continue;

        // target: ijijijijij...

        boolean iOrJ = true;
        int count = 0;
        for (int s : newS) {
          if (iOrJ) {
            if (s == i) {
              iOrJ=false;
            }
          } else {
            if (s == j) {
              iOrJ = true;
              count++;
            }
          }
        }

        steps = Math.min(steps, newS.length - count*2);
      }
    }

    return steps;
  }
}
