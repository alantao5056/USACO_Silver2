import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Paint {
  static int N;
  static int Q;

  public static void main(String[] args) throws Exception {
    // read input
    // Scanner sc = new Scanner(new File("paint.in"));
    Scanner sc = new Scanner(System.in);
    // PrintWriter pw = new PrintWriter("paint.out");
    N = sc.nextInt();
    Q = sc.nextInt();
    
    char[] fence = sc.next().toCharArray();

    int[] fenceNum = new int[N];
    for (int i = 0; i < N; i++) {
      fenceNum[i] = fence[i] - 'A';
    }

    int[] leftToRight = new int[N];
    
    int[] lastSeenLetters = new int[26];
    Arrays.fill(lastSeenLetters, -1);
    
    int count = 0;
    for (int i = 0; i < N; i++) {
      if (lastSeenLetters[fenceNum[i]] != -1) {
        boolean oneStroke = true;
        for (int j = lastSeenLetters[fenceNum[i]] + 1; j < i; j++) {
          if (fenceNum[j] < fenceNum[i]) {
            // not one stroke
            oneStroke = false;
            break;
          }
        }
        
        if (!oneStroke) {
          count++;
        }
      } else {
        count++;
      }
      
      leftToRight[i] = count;
      lastSeenLetters[fenceNum[i]] = i;
    }
    
    int[] rightToLeft = new int[N];
    Arrays.fill(lastSeenLetters, -1);
    
    count = 0;
    for (int i = N-1; i > -1; i--) {
      if (lastSeenLetters[fenceNum[i]] != -1) {
        boolean oneStroke = true;
        for (int j = lastSeenLetters[fenceNum[i]] - 1; j > i; j--) {
          if (fenceNum[j] < fenceNum[i]) {
            // not one stroke
            oneStroke = false;
            break;
          }
        }
        
        if (!oneStroke) {
          count++;
        }
      } else {
        count++;
      }
      
      rightToLeft[i] = count;
      lastSeenLetters[fenceNum[i]] = i;
    }

    // solve
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < Q; i++) {
      int a = sc.nextInt() - 1;
      int b = sc.nextInt() - 1;

      int left = a == 0 ? 0 : leftToRight[a-1];
      int right = b == N-1 ? 0 : rightToLeft[b+1];


      sb.append(left + right).append('\n');
    }

    System.out.print(sb);

    sc.close();
    // pw.close();
  }
}