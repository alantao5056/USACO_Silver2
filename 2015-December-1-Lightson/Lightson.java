import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Lightson {
  static int N;
  static int M;
  static Room[][] rooms;
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(new java.io.File("lightson.in"));
    PrintWriter pw = new PrintWriter("lightson.out");

    N = sc.nextInt();
    M = sc.nextInt();

    rooms = new Room[N+2][N+2];

    for (int i = 1; i < N+1; i++) {
      for (int j = 1; j < N+1; j++) {
        rooms[i][j] = new Room(i, j);
      }
    }

    for (int i = 0; i < M; i++) {
      int x1 = sc.nextInt();
      int y1 = sc.nextInt();
      int x2 = sc.nextInt();
      int y2 = sc.nextInt();
      rooms[x1][y1].switches.add(rooms[x2][y2]);
    }

    for (int i = 1; i < N+1; i++) {
      for (int j = 1; j < N+1; j++) {
        if (rooms[i][j].switches.size() == 0) {
          rooms[i][j].flicked = true;
        }
      }
    }

    // flood fill
    rooms[1][1].light = true;

    while (illuminate(rooms)) {}

    // count rooms
    int count = 0;
    for (int i = 1; i < N+1; i++) {
      for (int j = 1; j < N+1; j++) {
        count += rooms[i][j].light ? 1 : 0;
      }
    }

    pw.println(count);

    sc.close();
    pw.close();
  }

  private static boolean illuminate(Room[][] rooms) {
    Queue<Room> q = new LinkedList<>();
    q.add(rooms[1][1]);
    rooms[1][1].visited = true;
    boolean flicked = false;

    while (!q.isEmpty()) {
      Room curRoom = q.poll();
      int x = curRoom.x;
      int y = curRoom.y;
      if (!curRoom.flicked) {
        curRoom.flickSwitch();
        curRoom.flicked = true;
        flicked = true;
      }

      if (rooms[x+1][y] != null && rooms[x+1][y].light && !rooms[x+1][y].visited) {
        q.add(rooms[x+1][y]);
        rooms[x+1][y].visited = true;
      }
      if (rooms[x][y+1] != null && rooms[x][y+1].light && !rooms[x][y+1].visited) {
        q.add(rooms[x][y+1]);
        rooms[x][y+1].visited = true;
      }
      if (rooms[x-1][y] != null && rooms[x-1][y].light && !rooms[x-1][y].visited) {
        q.add(rooms[x-1][y]);
        rooms[x-1][y].visited = true;
      }
      if (rooms[x][y-1] != null && rooms[x][y-1].light && !rooms[x][y-1].visited) {
        q.add(rooms[x][y-1]);
        rooms[x][y-1].visited = true;
      }
    }
    
    for (int i = 1; i < N+1; i++) {
      for (int j = 1; j < N+1; j++) {
        rooms[i][j].visited = false;
      }
    }
    return flicked;
  }

  private static class Room {
    boolean light = false;
    boolean visited = false;
    boolean flicked = false;
    List<Room> switches = new ArrayList<>();
    int x;
    int y;
    public Room(int x, int y) {
      this.x = x;
      this.y = y;
    }
    public void flickSwitch() {
      for (var s : switches) {
        s.light = true;
      }
    }
    @Override
    public String toString() {
      return Boolean.toString(light);
    }
  }
}