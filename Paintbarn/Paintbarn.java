import java.io.PrintWriter;
import java.util.Scanner;

public class Paintbarn {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("paintbarn.in"));
    PrintWriter pw = new PrintWriter("paintbarn.out");

    int N = sc.nextInt();
    int K = sc.nextInt();

    int[][] barn = new int[1003][1003];
    for (int i = 0; i < N; i++) {
      int x1 = sc.nextInt();
      int y1 = sc.nextInt();
      int x2 = sc.nextInt();
      int y2 = sc.nextInt();

      barn[x1][y1]++;
      barn[x1][y2]--;
      barn[x2][y1]--;
      barn[x2][y2]++;
    }

    int count = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        if (i != 0) barn[i][j]+=barn[i-1][j];
        if (j != 0) barn[i][j]+=barn[i][j-1];
        if (i != 0 && j != 0) barn[i][j]-=barn[i-1][j-1];
        if (barn[i][j] == K) {
          count++;
        }
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }
}