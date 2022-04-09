import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("1.in"));

    int N = sc.nextInt();

    int[] ints = new int[N];

    for (int i = 0; i < N; i++) {
      ints[i] = sc.nextInt();
    }

    Arrays.sort(ints);

    int maxLength = 1;
    int length = 1;
    for (int i = 0; i < N-1; i++) {
      if (ints[i] == ints[i+1]-1) {
        // found consecutive
        length++;
      } else if (ints[i] < ints[i+1]-1) {
        // not consecutive
        maxLength = Math.max(maxLength, length);
        length = 1;
      }
    }

    System.out.println(maxLength);

    sc.close();
  }
}