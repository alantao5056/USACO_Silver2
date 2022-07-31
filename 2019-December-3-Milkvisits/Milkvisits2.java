import java.io.PrintWriter;
import java.io.File;
import java.util.*;
/**
 * splitting into groups of same type
 * and checking if given start and finish are in same groups
 */
public class Milkvisits2 {
  static int N;
  static int M;
  static String type;
  static List<Integer>[] connection;
  
  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("milkvisits.in"));
    PrintWriter pw = new PrintWriter("milkvisits.out");
    N = sc.nextInt();
    M = sc.nextInt();
    
    // solve
    type = " " +sc.next();

    connection = new ArrayList[N+1];

    for (int i = 1; i < N; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();

      if (connection[a] == null) {
        connection[a] = new ArrayList<>();
      }
      connection[a].add(b);

      if (connection[b] == null) {
        connection[b] = new ArrayList<>();
      }
      connection[b].add(a);
    }

    // split into groups
    int[] groups = new int[N+1];
    int curGroupNum = 0;

    for (int i = 1; i <= N; i++) {
      if (groups[i] != 0) continue;

      curGroupNum++;
      groups[i] = curGroupNum;
      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.add(i);
      char curType = type.charAt(i);

      while (!q.isEmpty()) {
        int cur = q.poll();

        for (int nb : connection[cur]) {
          if (groups[nb] == 0 && type.charAt(nb) == curType) {
            q.add(nb);
            groups[nb] = curGroupNum;
          }
        }
      }
    }

    // check if connected by right type
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= M; i++) {
      int start = sc.nextInt();
      int end = sc.nextInt();
      char curType = sc.next().charAt(0);

      if (groups[start] != groups[end]) {
        // yes
        sb.append(1);
      } else {
        if (type.charAt(start) == curType) {
          // yes
          sb.append(1);
        } else {
          // no
          sb.append(0);
        }
      }
    }

    pw.println(sb.toString());

    sc.close();
    pw.close();
  }
}