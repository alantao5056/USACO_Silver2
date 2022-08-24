import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Dance {
  static int N;
  static int K;

  public static void main(String[] args) throws Exception {
    // read input
    // Scanner sc = new Scanner(new File("dance.in"));
    Scanner sc = new Scanner(System.in);
    // PrintWriter pw = new PrintWriter("dance.out");
    N = sc.nextInt();
    K = sc.nextInt();
    List<List<Integer>> beenTo = new ArrayList<>();
    int[] afterSwap = new int[N];

    for (int i = 0; i < N; i++) {
      beenTo.add(new ArrayList<>());
      beenTo.get(i).add(i);
      afterSwap[i] = i;
    }


    // solve
    for (int i = 0; i < K; i++) {
      int a = sc.nextInt()-1;
      int b = sc.nextInt()-1;

      beenTo.get(afterSwap[a]).add(b);
      beenTo.get(afterSwap[b]).add(a);

      // swap
      int temp = afterSwap[a];
      afterSwap[a] = afterSwap[b];
      afterSwap[b] = temp;
    }

    boolean[] visited = new boolean[N];
    int[] result = new int[N];

    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;

      // cycle
      int cur = i;
      Set<Integer> cycleSet = new HashSet<>();
      List<Integer> cycle = new ArrayList<>();
      
      while (!visited[cur]) {
        visited[cur] = true;
        cycle.add(cur);
        cycleSet.addAll(beenTo.get(cur));
        cur = afterSwap[cur];
      }

      for (int c : cycle) {
        result[c] = cycleSet.size();
      }
    }

    StringBuilder out = new StringBuilder();
    for (int i = 0; i < N; i++) {
      out.append(result[i]).append('\n');
    }
    System.out.print(out);

    sc.close();
    // pw.close();
  }
}