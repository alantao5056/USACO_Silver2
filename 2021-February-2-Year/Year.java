import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Year {
  static StreamTokenizer st;
  static int N;
  static int K;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("year.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("year.out"));
    PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    K = nextInt();
    
    // solve
    int[] years = new int[N];
    for (int i = 0; i < N; i++) {
      years[i] = nextInt()/12;
    }

    Arrays.sort(years);

    int last = years[0];
    List<Integer> gaps = new ArrayList<>();
    gaps.add(last);
    int count = 1;
    for (int i = 1; i < N; i++) {
      if (years[i] != last) {
        gaps.add(years[i] - last - 1);
        last = years[i];
        count++;
      }
    }

    int total = count * 12;
    if (count+1 <= K) {
      pw.println(total);
    } else {
      Collections.sort(gaps);
      for (int i = 0; i < count+1-K; i++) {
        total += gaps.get(i)*12;
      }
      pw.println(total);
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