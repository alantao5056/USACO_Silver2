import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Convention2 {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("convention2.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("convention2.out"));
    N = nextInt();
    
    // solve
    Cow[] cows = new Cow[N];
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(i, nextInt(), nextInt());
    }

    Arrays.sort(cows, (Cow a, Cow b) -> a.a - b.a);

    PriorityQueue<Cow> pq = new PriorityQueue<>(new CowComparator());

    int cowIdx = 1;
    int curTime = cows[0].a;
    int finishTime = cows[0].a+cows[0].t;
    int maxWait = 0;

    while (cowIdx < N) {
      if (cows[cowIdx].a <= finishTime) {
        // will come
        pq.add(cows[cowIdx]);
        curTime = cows[cowIdx].a;
        cowIdx++;
      } else {
        // done
        if (pq.isEmpty()) {
          curTime = cows[cowIdx].a;
          finishTime = cows[cowIdx].a+cows[cowIdx].t;
          cowIdx++;
        } else {
          Cow curEating = pq.poll();
          curTime = finishTime;
          maxWait = Math.max(maxWait, curTime-curEating.a);
          finishTime = curTime + curEating.t;
        }
      }
    }

    while (!pq.isEmpty()) {
      Cow curEating = pq.poll();
      curTime = finishTime;
      maxWait = Math.max(maxWait, curTime - curEating.a);
      finishTime = curTime + curEating.t;
    }

    pw.println(maxWait);

    br.close();
    pw.close();
  }

  private static class CowComparator implements Comparator<Cow>{
    public int compare(Cow s1, Cow s2) {
      if (s1.s < s2.s)
        return -1;
      else if (s1.s > s2.s)
        return 1;
      return 0;
    }
  }

  private static class Cow {
    int s;
    int a;
    int t;
    int waited;
    public Cow(int s, int a, int t) {
      this.s = s;
      this.a = a;
      this.t = t;
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