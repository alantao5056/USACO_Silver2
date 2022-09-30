import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Meetings {
  static StreamTokenizer st;
  static int N;
  static int L;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("meetings.out"));
    N = nextInt();
    L = nextInt();
    
    // solve
    Cow[] cows = new Cow[N];
    List<Integer> leftCows = new ArrayList<>();
    List<Integer> rightCows = new ArrayList<>();
    long weightSum = 0;
    for (int i = 0; i < N; i++) {
      int weight = nextInt();
      weightSum += weight;
      cows[i] = new Cow(weight, nextInt(), nextInt());
      if (cows[i].right) {
        rightCows.add(cows[i].pos);
      } else {
        leftCows.add(cows[i].pos);
      }
    }

    Arrays.sort(cows, new Comparator<Cow>() {
      public int compare(Cow a, Cow b) {
        return a.pos - b.pos;
      }
    });
    Collections.sort(leftCows);
    Collections.sort(rightCows);

    int leftIdx = 0;
    for (int i = 0; i < N; i++) {
      if (!cows[i].right) {
        cows[leftIdx].arriveTime = cows[i].pos;
        leftIdx++;
      }
    }

    int rightIdx = N-1;
    for (int i = N-1; i >= 0; i--) {
      if (cows[i].right) {
        cows[rightIdx].arriveTime = L-cows[i].pos;
        rightIdx--;
      }
    }

    Arrays.sort(cows, new Comparator<Cow>() {
      public int compare(Cow a, Cow b) {
        return a.arriveTime - b.arriveTime;
      }
    });

    long curTotal = 0;
    int T = 0;
    for (int i = 0; i < N; i++) {
      curTotal += cows[i].weight;
      if (curTotal >= (double) weightSum/2) {
        T = cows[i].arriveTime;
        break;
      }
    }

    if (T == 0) {
      T = cows[N-1].arriveTime;
    }
    T=2*T;

    long total = 0;
    for (int i = 0; i < rightCows.size(); i++) {
      int start = rightCows.get(i);
      int startIdx = Collections.binarySearch(leftCows, start);
      startIdx = startIdx < 0 ? (-startIdx - 2) : startIdx;
      int end = start + T;
      int endIdx = Collections.binarySearch(leftCows, end);
      endIdx = endIdx < 0 ? (-endIdx - 2) : endIdx;
      total += endIdx - startIdx;
    }

    pw.println(total);

    br.close();
    pw.close();
  }

  private static class Cow {
    boolean right;
    int weight;
    int pos;
    int arriveTime;

    public Cow(int weight, int pos, int direction) {
      this.weight = weight;
      this.pos = pos;
      this.right = direction == 1;
    }
  }

  private static int nextInt() throws Exception {
    st.nextToken();
    return (int) st.nval;
  }
}