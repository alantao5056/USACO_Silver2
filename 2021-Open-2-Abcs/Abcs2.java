import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Abcs2 {
  static StreamTokenizer st;
  static int T;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("abcs.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("abcs.out"));
    PrintWriter pw = new PrintWriter(System.out);
    T = nextInt();
    
    // solve
    for (int i = 0; i < T; i++) {
      int N = nextInt();
      Set<Integer> possible = new HashSet<>();
      Integer[] ints = new Integer[N];
      for (int j = 0; j < N; j++) {
        ints[j] = nextInt();
        possible.add(ints[j]);
      }

      Arrays.sort(ints, Collections.reverseOrder());

      for (int j = 0; j < N; j++) {
        for (int k = j +1; k < N; k++) {
          possible.add(ints[j] - ints[k]);
        }
      }
      int count = 0;
      for (int j : possible) {
        for (int k : possible) {
          for (int l : possible) {
            if (j <= k && k <= l) {
              Map<Integer, Integer> seven = new HashMap<>();
              seven.put(j, seven.getOrDefault(j, 0)+1);
              seven.put(k, seven.getOrDefault(k, 0)+1);
              seven.put(l, seven.getOrDefault(l, 0)+1);
              seven.put(j+k, seven.getOrDefault(j+k, 0)+1);
              seven.put(j+l, seven.getOrDefault(j+l, 0)+1);
              seven.put(k+l, seven.getOrDefault(k+l, 0)+1);
              seven.put(j+k+l, seven.getOrDefault(j+k+l, 0)+1);
              
              boolean found = true;
              for (int m = 0; m < N; m++) {
                if (seven.containsKey(ints[m])) {
                  if (seven.get(ints[m]) == 1) {
                    seven.remove(ints[m]);
                  } else {
                    seven.put(ints[m], seven.get(ints[m])+1);
                  }
                } else {
                  found = false;
                  break;
                }
              }

              if (found) {
                // System.out.print(j);
                // System.out.print(" ");
                // System.out.print(k);
                // System.out.print(" ");
                // System.out.println(l);
                count++;
              }
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