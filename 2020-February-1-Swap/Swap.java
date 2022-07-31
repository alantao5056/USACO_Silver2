import java.util.*;
import java.io.PrintWriter;

public class Swap {
  static int N;
  static int M;
  static int K;
  static int[] instructionA;
  static int[] instructionB;
  static int[] pos;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("swap.in"));
    PrintWriter pw = new PrintWriter("swap.out");
    // Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    K = sc.nextInt();

    instructionA = new int[M];
    instructionB = new int[M];
    
    for (int i = 0; i < M; i++) {
      instructionA[i] = sc.nextInt()-1;
      instructionB[i] = sc.nextInt()-1;
    }

    pos = new int[N];
    for (int i = 0; i < N; i++) {
      pos[i] = i;
    }

    doOnce();

    int[] to = new int[N];

    for (int i = 0; i < N; i++) {
      to[i] = pos[i];
    }

    // find cycles
    boolean[] visited = new boolean[N];
    
    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;
      
      int cur = i;
      int count = 0;
      List<Integer> cycle = new ArrayList<>();
      do {
        visited[cur] = true;
        cycle.add(cur);
        count++;
        cur = to[cur];
      } while (cur != i);
      
      int mod = K % count;
      for (int j = 0; j < count; j++) {
        to[cycle.get(j)] = cycle.get((j+mod) % cycle.size());
      }
    }

    for (int i = 0; i < N; i++) {
      pw.println(to[i]+1);
    }

    pw.close();
    sc.close();
  }

  private static void doOnce () {
    for (int i = 0; i < M; i++) {
      int a = instructionA[i];
      int b = instructionB[i];

      while (a < b) {
        // swap
        int temp = pos[a];
        pos[a] = pos[b];
        pos[b] = temp;
        a++;
        b--;
      }
    }
  }
}
