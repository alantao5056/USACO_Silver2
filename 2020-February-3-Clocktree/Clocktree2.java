import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Clocktree2 {
  static StreamTokenizer st;
  static int N;

  static int count1; 
  static int sum1;
  static int count2;
  static int sum2;

  public static void main(String[] args) throws Exception {
    // read input
    BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StreamTokenizer(br);
    PrintWriter pw = new PrintWriter(new File("clocktree.out"));
    N = nextInt();
    
    // solve
    Room[] rooms = new Room[N];
    for (int i = 0; i < N; i++) {
      rooms[i] = new Room(nextInt());
    }

    for (int i = 0; i < N-1; i++) {
      int a = nextInt()-1;
      int b = nextInt()-1;

      rooms[a].nbs.add(rooms[b]);
      rooms[b].nbs.add(rooms[a]);
    }

    count1 = 0;
    sum1 = 0;
    count2 = 0;
    sum2 = 0;

    dfs(null, rooms[0], true);

    sum1 %= 12;
    sum2 %= 12;

    if (sum1 == sum2) {
      pw.println(N);
    } else if ((sum1+1)%12 == sum2) {
      pw.println(count2);
    } else if ((sum2+1)%12 == sum1) {
      pw.println(count1);
    } else {
      pw.println(0);
    }

    br.close();
    pw.close();
  }

  private static void dfs(Room parent, Room r, boolean group) {
    if (group) {
      count1++;
      sum1 += r.clock;
    } else {
      count2++;
      sum2 += r.clock;
    }
    for (Room n : r.nbs) {
      if (n != parent) {
        dfs(r, n, !group);
      }
    }
  }

  private static class Room {
    int clock;
    List<Room> nbs = new ArrayList<>();

    public Room(int clock) {
      this.clock = clock;
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