import java.util.Scanner;

public class Visit {
  private static int N;

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
      Cow curCow = cows[i];
      if (curCow.visited) continue;
      long minMoo = Long.MAX_VALUE;
      while (!curCow.visited) {
        result += curCow.visitValue;
        minMoo = Math.min(minMoo, curCow.visitValue);
        curCow.visited = true;
        curCow = curCow.nextCow;
      }
      result -= minMoo;
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