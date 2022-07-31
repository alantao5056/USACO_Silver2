import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Haybales {
  static int N;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("haybales.in"));
    PrintWriter pw = new PrintWriter("haybales.out");

    N = sc.nextInt();
    int Q = sc.nextInt();

    int[] haybales = new int[N];
    for (int i = 0; i < N; i++) {
      haybales[i] = sc.nextInt();
    }

    Arrays.sort(haybales);

    for (int i = 0; i < Q; i++) {
      int start = sc.nextInt();
      int end = sc.nextInt();

      pw.println(getNumOfHaybales(start, end, haybales));
    }

    sc.close();
    pw.close();
  }

  private static int getNumOfHaybales(int start, int end, int[] haybales) {
    int startIdx = binarySearch(haybales, start);
    int endIdx = binarySearch2(haybales, end);

    return endIdx - startIdx+1;
  }

  private static int binarySearch(int[] haybales, int key) {
    int low = 0;
    int high = N-1;
    if (key < haybales[low]) {
      return 0;
    } else if (key > haybales[high]) {
      return N;
    }

    while (low + 1 < high) {
      int mid = (low+high)/2;

      if (key > haybales[mid]) {
        low = mid;
      } else if (key < haybales[mid]) {
        high = mid;
      } else {
        return mid;
      }
    }
    if (haybales[low] == key) {
      return low;
    } else {
      return high;
    }
  }

  private static int binarySearch2(int[] haybales, int key) {
    int low = 0;
    int high = N-1;

    if (key < haybales[low]) {
      return -1;
    } else if (key > haybales[high]) {
      return N-1;
    }

    while (low + 1 < high) {
      int mid = (low+high)/2;

      if (key > haybales[mid]) {
        low = mid;
      } else if (key < haybales[mid]) {
        high = mid;
      } else {
        return mid;
      }
    }
    if (haybales[high] == key) {
      return high;
    } else {
      return low;
    }
  }
}