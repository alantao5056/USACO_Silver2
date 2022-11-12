import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Sort {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("sort.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("sort.out"));
    N = nextInt();
    
    // solve
    Num[] nums = new Num[N];
    for (int i = 0; i < N; i++) {
      nums[i] = new Num(nextInt(), i);
    }

    Arrays.sort(nums, (Num a, Num b) -> a.value - b.value);

    int result = 0;
    for (int i = 0; i < N; i++) {
      if (nums[i].idx > i) {
        result = Math.max(result, nums[i].idx-i);
      }
    }

    pw.println(result+1);

    br.close();
    pw.close();
  }

  private static class Num {
    int value;
    int idx;

    public Num(int value, int idx) {
      this.value = value;
      this.idx = idx;
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