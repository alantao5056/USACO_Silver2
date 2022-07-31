import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Snowboots {
  private static int N;
  private static int B;
  private static int[] bootsDepth;
  private static int[] bootsForward;
  private static int[] snow;
  private static int minDiscard = Integer.MAX_VALUE;

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("snowboots.in"));
    PrintWriter pw = new PrintWriter(new FileWriter("snowboots.out"));

    N = sc.nextInt();
    B = sc.nextInt();

    snow = new int[N];

    for (int i = 0; i < N; i++) {
      snow[i] = sc.nextInt();
    }

    bootsDepth = new int[B];
    bootsForward = new int[B];

    for (int i = 0; i < B; i++) {
      bootsDepth[i] = sc.nextInt();
      bootsForward[i] = sc.nextInt();
    }

    getNumOfDiscard(0, 0);
    pw.println(minDiscard);

    sc.close();
    pw.close();
  }

  private static int getNumOfDiscard(int bootsIdx, int snowIdx) {
    if (snowIdx == N-1) {
      // finished and found a way
      minDiscard = Math.min(minDiscard, bootsIdx);
      return bootsIdx;
    }

    boolean validBoot = false;
    for (int i = 1; snowIdx+i < N && i < bootsForward[bootsIdx]+1; i++) {
      if (bootsDepth[bootsIdx] >= snow[snowIdx+i]) {
        // can step into here
        int curDiscarded = getNumOfDiscard(bootsIdx, snowIdx+i);
        if (curDiscarded != -1) {
          // not dead end
          validBoot = true;
        }
      }
    }
    if (!validBoot) {
      // can't step anywhere
      if (bootsIdx == B-1) {
        // dead end
        return -1;
      }
      bootsIdx++;
      getNumOfDiscard(bootsIdx, snowIdx);
    }
    return bootsIdx;
  }
}