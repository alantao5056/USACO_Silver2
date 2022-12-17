import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Visits {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("visits.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("visits.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    Cow[] cows = new Cow[N];

    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(i);
    }

    long totalWeight = 0;
    for (int i = 0; i < N; i++) {
      Cow next = cows[nextInt()-1];
      int weight = nextInt();
      totalWeight += weight;

      cows[i].next = next;
      cows[i].weight = weight;
    }

    for (int i = 0; i < N; i++) {
      if (cows[i].visited) continue;

      // dfs
      Set<Integer> set = new HashSet<>();
      Cow curCow = cows[i];

      while (!set.contains(curCow.id) && !curCow.visited) {
        set.add(curCow.id);
        curCow = curCow.next;
      }

      if (set.contains(curCow.id)) {
        // loop
        Cow startingCow = curCow;
        int minWeight = Integer.MAX_VALUE;
        do {
          minWeight = Math.min(minWeight, curCow.weight);
          curCow = curCow.next;
        } while (curCow != startingCow);

        totalWeight -= minWeight;
      }

      for (int id : set) {
        cows[id].visited = true;
      }
    }

    pw.println(totalWeight);

    br.close();
    pw.close();
  }

  private static class Cow {
    Cow next;
    int weight;
    int id;
    boolean visited;

    public Cow(int id) {
      this.id = id;
    }
  }

  private static int nextInt() throws Exception {
    st.nextToken();
    return (int) st.nval;
  }

  private static String nextString() throws Exception {
    st.nextToken();
    return st.sval;
  }
}