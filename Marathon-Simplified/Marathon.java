import java.io.PrintWriter;
import java.util.Scanner;

public class Marathon {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("marathon.in"));
    PrintWriter pw = new PrintWriter("marathon.out");

    int N = sc.nextInt();

    Checkpoint[] checkpoints = new Checkpoint[N];
    int totalDistance = 0;
    checkpoints[0] = new Checkpoint(sc.nextInt(), sc.nextInt());
    for (int i = 1; i < N; i++) {
      checkpoints[i] = new Checkpoint(sc.nextInt(), sc.nextInt());
      totalDistance += checkpoints[i-1].getDistance(checkpoints[i]);
    }

    int minDistance = Integer.MAX_VALUE;
    for (int i = 1; i < N-1; i++) {
      int origDistance = checkpoints[i-1].getDistance(checkpoints[i]) + checkpoints[i].getDistance(checkpoints[i+1]);
      int afterDistance = checkpoints[i-1].getDistance(checkpoints[i+1]);

      minDistance = Math.min(minDistance, totalDistance - (origDistance-afterDistance));
    }

    pw.println(minDistance);

    sc.close();
    pw.close();
  }

  public static class Checkpoint {
    int x;
    int y;

    public Checkpoint (int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getDistance(Checkpoint c) {
      return Math.abs(x - c.x) + Math.abs(y - c.y);
    }
  }
}