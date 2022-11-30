import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Clocktree {
  static StreamTokenizer st;
  static int N;
  static Room startRoom;

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

    int count = 0;
    for (int i = 0; i < N; i++) {
      startRoom = rooms[i];
      if (check(null, startRoom)) {
        count++;
      }

      for (int j = 0; j < N; j++) {
        rooms[j].newClock = rooms[j].clock;
      }
    }

    pw.println(count);

    br.close();
    pw.close();
  }

  private static boolean check(Room parent, Room room) {
    for (Room n : room.nbs) {
      if (n == parent) continue;
      if (n.nbs.size() != 1) {
        n.newClock++;
        n.newClock %= 12;
        if (n.newClock == 0) n.newClock = 12;
        check(room, n);
        // room.newClock++;
        // room.newClock %= 12;
        // if (room.newClock == 0) room.newClock = 12;
      }

      room.newClock += 12-n.newClock;
      room.newClock %= 12;
      if (room.newClock == 0) room.newClock = 12;
    }
    if (room == startRoom) {
      return room.newClock == 12 || room.newClock == 1;
    }
    return false;
  }

  private static class Room {
    int clock;
    int newClock;
    List<Room> nbs = new ArrayList<>();

    public Room(int clock) {
      this.clock = clock;
      this.newClock = clock;
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