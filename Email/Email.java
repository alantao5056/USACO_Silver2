import java.util.Scanner;

public class Email {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("1.in"));
    // Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    for (int i = 0; i < T; i++) {
      int M = sc.nextInt();
      int N = sc.nextInt();
      int K = sc.nextInt();
      int[] emails = new int[N+1];
      int[] folderNeed = new int[N+1];
      for (int j = 1; j <= N; j++) {
        emails[j] = sc.nextInt();
        folderNeed[emails[j]]++;
      }

      Folders folders = new Folders(N, folderNeed);

      System.out.println(checkIfCanFile(M, N, K, emails, folders) ? "YES" : "NO");
    }

    sc.close();
  }

  private static boolean checkIfCanFile(int M, int N, int K, int[] emails, Folders folders) {
    int emailStartIdx = 0;
    int emailEndIdx = K;
    while () {
      // folder scroll as much as possible
      if (folders.scroll()) {
        
      }


    }
  }

  public static class Folders {
    int folderStart = 1;
    int folderEnd;
    int[] folderNeed;
    int[] folderFiled;
    public Folders(int N, int[] folderNeed) {
      folderEnd = N;
      this.folderNeed = folderNeed;
    }
    public boolean scroll() {
      boolean scrolled = false;
      while (folderFiled[folderStart] == folderNeed[folderStart]) {
        folderStart++;
        folderEnd++;
        scrolled = true;
      }
      return scrolled;
    }
  }
}