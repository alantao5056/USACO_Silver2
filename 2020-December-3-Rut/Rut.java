import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Rut {
  private static int N;
  private static List<List<Integer>> stops;
  private static List<Cow> upFacing;
  private static List<Cow> rightFacing;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("rut.in"));
    // Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    
    upFacing = new ArrayList<>();
    rightFacing = new ArrayList<>();
    stops = new ArrayList<>();

    // solve
    for (int i = 0; i < N; i++) {
      if (sc.next().charAt(0) == 'N') {
        // up facing
        upFacing.add(new Cow(sc.nextInt(), sc.nextInt(), true, i));
      } else{
        rightFacing.add(new Cow(sc.nextInt(), sc.nextInt(), false, i));
      }
      stops.add(new ArrayList<>());
    }

    Collections.sort(upFacing, (a, b) -> {return a.x - b.x;});
    Collections.sort(rightFacing, (a, b) -> {return a.y - b.y;});

    for (Cow c : upFacing) {
      if (c.visited) continue;
      checkCowStop(c, Integer.MAX_VALUE);
    }

    for (Cow c : rightFacing) {
      if (c.visited) continue;
      checkCowStop(c, Integer.MAX_VALUE);
    }

    for (int i = 0; i < N; i++) {
      System.out.println(i + ": ");
      for (int id : stops.get(i)) {
        System.out.println(id);
      }
    }

    int[] result = new int[N];

    for (int i = 0; i < N; i++) {
      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.add(i);

      while (!q.isEmpty()) {
        int curId = q.poll();
        for (int id : stops.get(curId)) {
          q.add(id);
        }
        result[i] += stops.get(curId).size();
      }
    }

    sc.close();
  }

  private static boolean checkCowStop(Cow cow, int range) {
    if (cow.visited) {
      return true;
    }
    if (cow.facingUp) {
      // north
      for (Cow c : rightFacing) {
        if (c.y >= range) break;
        if (c.y < cow.y || c.x > cow.x || cow.x - c.x >= c.y - cow.y) continue; // impossible
        
        if (!checkCowStop(c, cow.x)) {
          // we have answer
          stops.get(c.id).add(cow.id);
          cow.visited = true;
          return true;
        }
        return false;
      }
    } else {
      // east
      for (Cow c : upFacing) {
        if (c.x >= range) break;
        if (c.x < cow.x || c.y > cow.y || cow.y - c.y >= c.x - cow.x) continue; // impossible
        
        if (!checkCowStop(c, cow.y)) {
          // we have answer
          stops.get(c.id).add(cow.id);
          cow.visited = true;
          return true;
        }
        return false;
      }
    }
    return false; // does nothing
  }

  private static class Cow {
    int x;
    int y;
    int id;
    boolean facingUp;
    boolean visited = false;

    public Cow(int x, int y, boolean facingUp, int id) {
      this.x = x;
      this.y = y;
      this.id = id;
      this.facingUp = facingUp;
    }

    @Override
    public String toString() {
      return String.format("%d %d", x, y);
    }
  }
}