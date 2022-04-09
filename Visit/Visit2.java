import java.util.Scanner;

public class Visit2 {
  public static void main(String[] args) throws Exception {
    // Scanner sc = new Scanner(new java.io.File("2.in"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    Cow[] cows = new Cow[N+1];
    for (int i = 1; i <= N; i++) {
      cows[i] = new Cow(sc.nextInt(), sc.nextInt());
    }

    for (int i = 1; i <= N; i++) {
      cows[i].nextCow = cows[cows[i].idx];
    }

    long result = 0;
    for (int i = 1; i <= N; i++) {
      Cow origCow = cows[i];
      if (origCow.visited) continue;
      Cow curCow = origCow;
      while (!curCow.visited) {
        origCow.visitValue += curCow.visitValue;
        curCow.visited = true;
        curCow = curCow.nextCow;
      }
    }

    System.out.println(result);

    sc.close();
  }

  public static class Cow {
    int idx;
    Cow nextCow;
    int visitValue;
    // boolean departed = false;
    boolean visited = false;
    int cycleValue = -1;

    @Override
    public String toString() {
      return Integer.toString(idx);
    }

    public Cow (int idx, int visitValue) {
      this.idx = idx;
      this.visitValue = visitValue;
    }

    public void reset() {
      visited = false;
    }
  }
}
