import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Cowjump {
  static StreamTokenizer st;
  static int N;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("cowjump.out"));
    // PrintWriter pw = new PrintWriter(System.out);
    N = nextInt();
    
    // solve
    List<Point> points = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      int x1 = nextInt();
      int y1 = nextInt();
      int x2 = nextInt();
      int y2 = nextInt();

      if (x2 < x1) {
        int temp = x1;
        x1 = x2;
        x2 = temp;
        temp = y1;
        y1 = y2;
        y2 = temp;
      }

      Point a = new Point(true, x1, y1, i+1);
      Point b = new Point(false, x2, y2, i+1);
      a.other = b;
      b.other = a;

      points.add(a);
      points.add(b);
    }

    Collections.sort(points, (Point a, Point b) -> a.x > b.x ? 1 : a.x < b.x ? -1 : b.y - a.y);

    Set<Point> active = new HashSet<>();

    Point interX1 = null;
    Point interX2 = null;
    for (Point p : points) {
      Point cur = p;
      if (!p.isStart) {
        active.remove(p.other);
        cur = p.other;
      }
      int minUp = Integer.MAX_VALUE;
      Point up = null;
      int minDown = Integer.MAX_VALUE;
      Point down = null;
      for (Point p_ : active) {
        if (p_.y > cur.y) {
          if (minUp > p_.y-cur.y) {
            minUp = p_.y-cur.y;
            up = p_;
          }
        } else {
          if (minDown > cur.y-p_.y) {
            minDown = cur.y-p_.y;
            down = p_;
          }
        }
      }

      if (p.isStart) {
        // check p to the two nbs
        if (up != null) {
          // check up
          if (doIntersect(up, up.other, p, p.other)) {
            interX1 = up;
            interX2 = p;
            break;
          }
        }
        if (down != null) {
          // check down
          if (doIntersect(down, down.other, p, p.other)) {
            interX1 = down;
            interX2 = p;
            break;
          }
        }

        active.add(p);
      } else {
        // check if p's nbs cross
        if (up != null && down != null) {
          if (doIntersect(up, up.other, down, down.other)) {
            interX1 = up;
            interX2 = down;
            break;
          }
        }
      }
    }

    boolean found = false;
    for (Point p : points) {
      if (p.isStart) {

        if (p != interX2 && doIntersect(interX1, interX1.other, p, p.other)) {
          // it's this one
          pw.println(interX1.idx);
          found = true;
          break;
        }
        if (p != interX1 && doIntersect(interX2, interX2.other, p, p.other)) {
          // it's this one
          pw.println(interX2.idx);
          found = true;
          break;
        }
      }
    }

    if (!found) {
      pw.println(0);
    }

    br.close();
    pw.close();
  }

  static boolean onSegment(Point p, Point q, Point r)
{
    if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
        q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
    return true;
  
    return false;
}
  
// To find orientation of ordered triplet (p, q, r).
// The function returns following values
// 0 --> p, q and r are collinear
// 1 --> Clockwise
// 2 --> Counterclockwise
static int orientation(Point p, Point q, Point r)
{
    // See https://www.geeksforgeeks.org/orientation-3-ordered-points/
    // for details of below formula.
    BigInteger val = new BigInteger(Long.toString(q.y - p.y))
    .multiply(new BigInteger(Long.toString(r.x - q.x)))
    .subtract(new BigInteger(Long.toString(q.x - p.x))
    .multiply(new BigInteger(Long.toString(r.y - q.y))));
  
    if (val.compareTo(new BigInteger("0")) == 0) return 0; // collinear
  
    return (val.compareTo(new BigInteger("0")) == 1)? 1: 2; // clock or counterclock wise
}
  
// The main function that returns true if line segment 'p1q1'
// and 'p2q2' intersect.
static boolean doIntersect(Point p1, Point q1, Point p2, Point q2)
{
    // Find the four orientations needed for general and
    // special cases
    int o1 = orientation(p1, q1, p2);
    int o2 = orientation(p1, q1, q2);
    int o3 = orientation(p2, q2, p1);
    int o4 = orientation(p2, q2, q1);
  
    // General case
    if (o1 != o2 && o3 != o4)
        return true;
  
    // Special Cases
    // p1, q1 and p2 are collinear and p2 lies on segment p1q1
    if (o1 == 0 && onSegment(p1, p2, q1)) return true;
  
    // p1, q1 and q2 are collinear and q2 lies on segment p1q1
    if (o2 == 0 && onSegment(p1, q2, q1)) return true;
  
    // p2, q2 and p1 are collinear and p1 lies on segment p2q2
    if (o3 == 0 && onSegment(p2, p1, q2)) return true;
  
    // p2, q2 and q1 are collinear and q1 lies on segment p2q2
    if (o4 == 0 && onSegment(p2, q1, q2)) return true;
  
    return false; // Doesn't fall in any of the above cases
}

  private static class Point {
    boolean isStart;
    int x;
    int y;
    int idx;
    Point other;
    public Point(boolean isStart, int x, int y, int idx) {
      this.isStart = isStart;
      this.x = x;
      this.y = y;
      this.idx = idx;
    }
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