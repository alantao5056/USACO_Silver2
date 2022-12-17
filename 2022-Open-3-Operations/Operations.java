import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Operations {
  static StreamTokenizer st;
  static char[] str;

  public static void main(String[] args) throws Exception {
    // read input
    // BufferedReader br = new BufferedReader(new FileReader("operations.in"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    // PrintWriter pw = new PrintWriter(new File("operations.out"));
    PrintWriter pw = new PrintWriter(System.out);
    str = nextString().toCharArray();

    boolean[] cPrefix = new boolean[str.length+1];
    boolean[] oPrefix = new boolean[str.length+1];
    boolean[] wPrefix = new boolean[str.length+1];

    for (int i = 1; i <= str.length; i++) {
      boolean cInvert = false;
      boolean oInvert = false;
      boolean wInvert = false;
      if (str[i-1] == 'C') {
        cInvert = true;
      } else if (str[i-1] == 'O') {
        oInvert = true;
      } else {
        wInvert = true;
      }

      cPrefix[i] = cPrefix[i-1] ^ cInvert;
      oPrefix[i] = oPrefix[i-1] ^ oInvert;
      wPrefix[i] = wPrefix[i-1] ^ wInvert;
    }
    
    // solve
    int Q = nextInt();
    for (int i = 0; i < Q; i++) {
      int a = nextInt();
      int b = nextInt();

      boolean c = cPrefix[b] == cPrefix[a-1];
      boolean o = oPrefix[b] == oPrefix[a-1];
      boolean w = wPrefix[b] == wPrefix[a-1];

      if (c && !o && !w) {
        pw.print('Y');
      } else if (!c && o && w) {
        pw.print('Y');
      } else {
        pw.print('N');
      }
    }

    pw.println();

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