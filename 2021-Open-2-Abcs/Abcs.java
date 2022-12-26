import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Abcs {
  static StreamTokenizer st;
  static int T;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("abcs.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("abcs.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    T = nextInt();
    
    // solve
    for (int i = 0; i < T; i++) {
      int N = nextInt();

      int[] nums = new int[N];
      for (int j = 0; j < T; j++) {
        nums[j] = nextInt();
      }

      Set<Integer> possible = new HashSet<>();
      for (int j = 0; j < N; j++) {
        possible.add(nums[j]);
        for (int k = i+1; k < N; k++) {
          if (nums[j] < nums[k]) {
            possible.add(nums[k] - nums[j]);
          }
        }
      }

      int count = 0;
      for (int a : possible) {
        for (int b : possible) {
          for (int c : possible) {
            Set<Integer> allPossible = new HashSet<>();
            allPossible.add(a);
            allPossible.add(b);
            allPossible.add(c);
            allPossible.add(a+b);
            allPossible.add(a+c);
            allPossible.add(b+c);
            allPossible.add(a+b+c);

            boolean works = true;
            for (int j = 0; j < N; j++) {
              if (!allPossible.contains(nums[j])) {
                works = false;
              }
            }

            if (works) {
              count++;
            }
          }
        }
      }

      pw.println(count);
    }

    br.close();
    pw.close();
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