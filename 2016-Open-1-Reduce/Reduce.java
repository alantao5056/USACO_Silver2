import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Reduce {
  static StreamTokenizer st;
  static int N;
  static int minX;
  static Set<Cow> minXSet;
  static int maxX;
  static Set<Cow> maxXSet;
  static int minY;
  static Set<Cow> minYSet;
  static int maxY;
  static Set<Cow> maxYSet;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("reduce.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    Cow[] cows = new Cow[N];
    for (int i = 0; i < N; i++) {
      cows[i] = new Cow(nextInt(), nextInt());
    }

    get(cows, null, null);

    int init = (maxX - minX) * (maxY - minY);

    Set<Cow> remove = new HashSet<>();
    if (minXSet.size() <= 3) {
      remove.addAll(minXSet);
    }

    if (maxXSet.size() <= 3) {
      remove.addAll(maxXSet);
    }

    if (minYSet.size() <= 3) {
      remove.addAll(minYSet);
    }

    if (maxYSet.size() <= 3) {
      remove.addAll(maxYSet);
    }

    List<Cow> removeList = new ArrayList<>();
    removeList.addAll(remove);

    if (removeList.size() > 2) {
      int result = Integer.MAX_VALUE;
      for (int i = 0; i < removeList.size(); i++) {
        for (int j = i+1; j < removeList.size(); j++) {
          for (int k = j+1; k < removeList.size(); k++) {
            int minX_ = Integer.MAX_VALUE;
            int maxX_ = -1;
            int minY_ = Integer.MAX_VALUE;
            int maxY_ = -1;

            for (int l = 0; l < N; l++) {
              if (cows[l] != removeList.get(i) && cows[l] != removeList.get(j) && cows[l] != removeList.get(k)) {
                minX = Math.min(minX, cows[l].x);
                maxX = Math.max(maxX, cows[l].x);
                minY = Math.min(minY, cows[l].y);
                maxY = Math.max(maxY, cows[l].y);
              }
            }

            result = Math.min(result, (maxX_-minX_) * (maxY_-minY_));
          }
        }
      }
      pw.println(result);
    } else {
      if (removeList.size() == 0) {
        pw.println(init);
      } else if (removeList.size() == 1) {
        get(cows, removeList.get(0), null);
        if (maxXSet.size() <= 2) {

        }
      }
    }

    br.close();
    pw.close();
  }

  private static void get(Cow[] arr, Cow n1, Cow n2) {
    minX = Integer.MAX_VALUE;
    minXSet = new HashSet<>();
    maxX = -1;
    maxXSet = new HashSet<>();
    minY = Integer.MAX_VALUE;
    minYSet = new HashSet<>();
    maxY = -1;
    maxYSet = new HashSet<>();

    for (int i = 0; i < N; i++) {
      if (arr[i] == n1 || arr[i] == n2) continue;
      if (arr[i].x < minX) {
        minX = arr[i].x;
        minXSet = new HashSet<>();
        minXSet.add(arr[i]);
      } else if (arr[i].x == minX) {
        minXSet.add(arr[i]);
      }

      if (arr[i].x > maxX) {
        maxX = arr[i].x;
        maxXSet = new HashSet<>();
        maxXSet.add(arr[i]);
      } else if (arr[i].x == maxX) {
        maxXSet.add(arr[i]);
      }

      if (arr[i].y < minY) {
        minY = arr[i].y;
        minYSet = new HashSet<>();
        minYSet.add(arr[i]);
      } else if (arr[i].y == minY) {
        minYSet.add(arr[i]);
      }

      if (arr[i].y > maxY) {
        maxY = arr[i].y;
        maxYSet = new HashSet<>();
        maxYSet.add(arr[i]);
      } else if (arr[i].y == maxY) {
        maxYSet.add(arr[i]);
      }
    }
  }

  private static class Cow {
    int x;
    int y;
    public Cow(int x, int y) {
      this.x = x;
      this.y = y;
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