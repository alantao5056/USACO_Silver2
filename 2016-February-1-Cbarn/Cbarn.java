import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Cbarn {
  static int N;
  static StringTokenizer st;
  static BufferedReader br;

  public static void main(String[] args) throws Exception {
    // read input
    br = new BufferedReader(new FileReader("cbarn.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter("cbarn.out");
    nextLine();
    N = nextInt();
    
    int[] rooms = new int[N];
    // solve
    for (int i = 0; i < N; i++) {
      nextLine();
      rooms[i] = nextInt();
    }

    long minTotal = Long.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      long total = 0;
      int cp = 0;
      for (int j = 0; j < N; j++) {
        if (rooms[j] == 0) continue;

        boolean flag = false;
        for (int k = 0; k < rooms[j]; k++) {
          if (cp-j < 0) {
            total = Long.MAX_VALUE;
            flag = true;
            break;
          }
          total += Math.pow((cp-j), 2);
          cp++;
        }
        if (flag) break;
      }
      minTotal = Math.min(minTotal, total);

      // rotate
      int first = rooms[0];
      for (int j = 1; j < N; j++) {
        rooms[j-1] = rooms[j];
      }
      rooms[N-1] = first;
    }

    pw.println(minTotal);

    br.close();
    pw.close();
  }

  private static void nextLine() throws Exception {
    st = new StringTokenizer(br.readLine());
  }

  private static int nextInt() {
    return Integer.parseInt(st.nextToken());
  }
}