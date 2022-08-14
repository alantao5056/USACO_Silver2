import java.io.PrintWriter;
import java.io.File;
import java.util.*;

public class Mountains {
  static int N;
  static Segment[] segments;

  public static void main(String[] args) throws Exception {
    // read input
    Scanner sc = new Scanner(new File("mountains.in"));
    PrintWriter pw = new PrintWriter("mountains.out");
    N = sc.nextInt();
    
    // solve
    segments = new Segment[N];

    for (int i = 0; i < N; i++) {
      segments[i] = new Segment(sc.nextInt(), sc.nextInt());
    }

    Arrays.sort(segments, new Comparator<Segment>() {
      public int compare(Segment a, Segment b) {
        return a.start > b.start ? 1 : a.start < b.start ? -1 : b.end - a.end;
      }
    });

    // cycle segments & keep track of maxEnd
    int maxEnd = segments[0].end;
    int count = 1;
    for (int i = 1; i < N; i++) {
      if (segments[i].end > maxEnd) {
        count++;
        maxEnd = segments[i].end;
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }
  private static class Segment {
    int start;
    int end;

    public Segment(int x, int y) {
      this.start = x - y;
      this.end = x + y;
    }

    @Override
    public String toString() {
      return String.format("%d %d", start, end);
    }
  }
}