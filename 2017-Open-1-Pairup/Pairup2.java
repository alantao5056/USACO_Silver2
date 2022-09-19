import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Pairup2 {
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
    PrintWriter pw = new PrintWriter("pairup.out");
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    
    // solve
    int[][] cows = new int[N][2]; // 0 is amount, 1 is milk
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      cows[i][0] = Integer.parseInt(st.nextToken());
      cows[i][1] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(cows, new Comparator<int[]>() {
      public int compare(int[] a, int[] b) {
        return a[1] - b[1];
      }
    });

    int i = 0;
    int j = N-1;
    int maxTime = 0;

    while (i < j) {
      maxTime = Math.max(maxTime, cows[i][1] + cows[j][1]);
      if (cows[i][0] == cows[j][0]) {
        // same amount
        i++;
        j--;
      } else if (cows[i][0] > cows[j][0]) {
        cows[i][0] -= cows[j][0];
        j--;
      } else {
        // cows j more than cows i
        cows[j][0] -= cows[i][0];
        i++;
      }
    }
    if (i == j) {
      maxTime = Math.max(maxTime, cows[i][1] * 2);
    }

    pw.println(maxTime);

    br.close();
    pw.close();
  }
}