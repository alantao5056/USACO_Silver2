import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Lemonade {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("lemonade.out"));
    N = nextInt();
    
    // solve
    Integer[] cows = new Integer[N];
    for (int i = 0; i < N; i++) {
      cows[i]=nextInt();
    }

    Arrays.sort(cows, Collections.reverseOrder());

    int curCowCount = 0;
    int result = N;
    for (int i = 0; i < N; i++) {
      if (cows[i] >= curCowCount) {
        curCowCount++;
      } else {
        result = i;
        break;
      }
    }

    pw.println(result);

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